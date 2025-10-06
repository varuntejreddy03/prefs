#!/usr/bin/env python3
"""
Security and Load Test for Exam Helper Flask Application
Tests security vulnerabilities and performance for 200 concurrent users
"""

import requests
import json
import time
import threading
from concurrent.futures import ThreadPoolExecutor
import sys

class SecurityTester:
    def __init__(self, base_url="http://localhost:5000"):
        self.base_url = base_url
        self.session = requests.Session()
        self.results = []
        
    def log_result(self, test_name, status, message):
        result = {
            'test': test_name,
            'status': status,
            'message': message,
            'timestamp': time.strftime('%Y-%m-%d %H:%M:%S')
        }
        self.results.append(result)
        print(f"[{status}] {test_name}: {message}")
    
    def test_basic_endpoints(self):
        """Test all basic endpoints are accessible"""
        endpoints = [
            ('/', 'Homepage'),
            ('/day/0', 'Day 0 page'),
            ('/day/10', 'Day 10 page'),
            ('/quiz', 'Quiz page'),
            ('/search', 'Search page'),
            ('/editor/201', 'Code editor'),
            ('/solution/201', 'Solution viewer')
        ]
        
        for endpoint, name in endpoints:
            try:
                response = self.session.get(f"{self.base_url}{endpoint}")
                if response.status_code == 200:
                    self.log_result(f"Endpoint {name}", "PASS", f"Status: {response.status_code}")
                else:
                    self.log_result(f"Endpoint {name}", "FAIL", f"Status: {response.status_code}")
            except Exception as e:
                self.log_result(f"Endpoint {name}", "ERROR", str(e))
    
    def test_api_endpoints(self):
        """Test API endpoints"""
        api_tests = [
            ('/api/random-question', 'GET', 'Random question API'),
            ('/api/quiz-session', 'POST', 'Quiz session creation', {'count': 10}),
        ]
        
        for endpoint, method, name, *data in api_tests:
            try:
                if method == 'GET':
                    response = self.session.get(f"{self.base_url}{endpoint}")
                else:
                    payload = data[0] if data else {}
                    response = self.session.post(f"{self.base_url}{endpoint}", json=payload)
                
                if response.status_code in [200, 201]:
                    self.log_result(f"API {name}", "PASS", f"Status: {response.status_code}")
                else:
                    self.log_result(f"API {name}", "FAIL", f"Status: {response.status_code}")
            except Exception as e:
                self.log_result(f"API {name}", "ERROR", str(e))
    
    def test_security_headers(self):
        """Test security headers"""
        try:
            response = self.session.get(self.base_url)
            headers = response.headers
            
            security_checks = [
                ('X-Content-Type-Options', 'nosniff'),
                ('X-Frame-Options', ['DENY', 'SAMEORIGIN']),
                ('X-XSS-Protection', '1; mode=block'),
            ]
            
            for header, expected in security_checks:
                if header in headers:
                    if isinstance(expected, list):
                        if headers[header] in expected:
                            self.log_result(f"Security Header {header}", "PASS", f"Value: {headers[header]}")
                        else:
                            self.log_result(f"Security Header {header}", "WARN", f"Unexpected value: {headers[header]}")
                    else:
                        if headers[header] == expected:
                            self.log_result(f"Security Header {header}", "PASS", f"Value: {headers[header]}")
                        else:
                            self.log_result(f"Security Header {header}", "WARN", f"Value: {headers[header]}")
                else:
                    self.log_result(f"Security Header {header}", "WARN", "Header missing")
        except Exception as e:
            self.log_result("Security Headers", "ERROR", str(e))
    
    def test_input_validation(self):
        """Test input validation and XSS protection"""
        xss_payloads = [
            "<script>alert('xss')</script>",
            "javascript:alert('xss')",
            "<img src=x onerror=alert('xss')>",
            "'; DROP TABLE users; --"
        ]
        
        for payload in xss_payloads:
            try:
                # Test search endpoint
                response = self.session.get(f"{self.base_url}/search", params={'q': payload})
                if payload in response.text:
                    self.log_result("XSS Protection", "FAIL", f"Payload reflected: {payload[:20]}...")
                else:
                    self.log_result("XSS Protection", "PASS", f"Payload filtered: {payload[:20]}...")
            except Exception as e:
                self.log_result("XSS Protection", "ERROR", str(e))
    
    def test_error_handling(self):
        """Test error handling"""
        error_tests = [
            ('/day/999', 'Non-existent day'),
            ('/editor/999999', 'Non-existent question'),
            ('/solution/999999', 'Non-existent solution'),
            ('/nonexistent', 'Non-existent route')
        ]
        
        for endpoint, name in error_tests:
            try:
                response = self.session.get(f"{self.base_url}{endpoint}")
                if response.status_code in [404, 400]:
                    self.log_result(f"Error Handling {name}", "PASS", f"Status: {response.status_code}")
                else:
                    self.log_result(f"Error Handling {name}", "WARN", f"Status: {response.status_code}")
            except Exception as e:
                self.log_result(f"Error Handling {name}", "ERROR", str(e))

def load_test_user(base_url, user_id, results):
    """Simulate a single user session"""
    session = requests.Session()
    start_time = time.time()
    
    try:
        # Simulate user journey
        # 1. Visit homepage
        response = session.get(f"{base_url}/")
        if response.status_code != 200:
            results[user_id] = {'status': 'FAIL', 'step': 'homepage', 'time': time.time() - start_time}
            return
        
        # 2. Visit a day page
        response = session.get(f"{base_url}/day/{user_id % 20}")
        if response.status_code != 200:
            results[user_id] = {'status': 'FAIL', 'step': 'day_page', 'time': time.time() - start_time}
            return
        
        # 3. Start a quiz
        response = session.post(f"{base_url}/api/quiz-session", json={'count': 5})
        if response.status_code != 200:
            results[user_id] = {'status': 'FAIL', 'step': 'quiz_start', 'time': time.time() - start_time}
            return
        
        # 4. Get quiz questions
        for _ in range(3):
            response = session.get(f"{base_url}/api/quiz-question")
            if response.status_code == 200:
                session.post(f"{base_url}/api/next-question")
        
        # 5. Search
        response = session.get(f"{base_url}/search", params={'q': 'binary'})
        
        results[user_id] = {'status': 'PASS', 'step': 'completed', 'time': time.time() - start_time}
        
    except Exception as e:
        results[user_id] = {'status': 'ERROR', 'step': 'exception', 'time': time.time() - start_time, 'error': str(e)}

def run_load_test(base_url="http://localhost:5000", num_users=200):
    """Run load test with specified number of concurrent users"""
    print(f"\n🚀 Starting load test with {num_users} concurrent users...")
    
    results = {}
    start_time = time.time()
    
    with ThreadPoolExecutor(max_workers=50) as executor:
        futures = []
        for user_id in range(num_users):
            future = executor.submit(load_test_user, base_url, user_id, results)
            futures.append(future)
        
        # Wait for all users to complete
        for future in futures:
            future.result()
    
    total_time = time.time() - start_time
    
    # Analyze results
    passed = sum(1 for r in results.values() if r['status'] == 'PASS')
    failed = sum(1 for r in results.values() if r['status'] == 'FAIL')
    errors = sum(1 for r in results.values() if r['status'] == 'ERROR')
    
    avg_response_time = sum(r['time'] for r in results.values()) / len(results)
    
    print(f"\n📊 Load Test Results:")
    print(f"Total Users: {num_users}")
    print(f"Passed: {passed} ({passed/num_users*100:.1f}%)")
    print(f"Failed: {failed} ({failed/num_users*100:.1f}%)")
    print(f"Errors: {errors} ({errors/num_users*100:.1f}%)")
    print(f"Average Response Time: {avg_response_time:.2f}s")
    print(f"Total Test Time: {total_time:.2f}s")
    
    if failed > num_users * 0.05:  # More than 5% failure rate
        print("⚠️  WARNING: High failure rate detected!")
    
    if avg_response_time > 5.0:  # More than 5 seconds average
        print("⚠️  WARNING: High response times detected!")
    
    return passed >= num_users * 0.95  # 95% success rate required

def main():
    base_url = "http://localhost:5000"
    
    print("🔒 Starting Security and Load Testing for Exam Helper")
    print("=" * 60)
    
    # Security Tests
    print("\n🛡️  Running Security Tests...")
    tester = SecurityTester(base_url)
    
    try:
        tester.test_basic_endpoints()
        tester.test_api_endpoints()
        tester.test_security_headers()
        tester.test_input_validation()
        tester.test_error_handling()
    except requests.exceptions.ConnectionError:
        print("❌ ERROR: Cannot connect to application. Make sure Flask app is running on localhost:5000")
        return False
    
    # Count results
    passed = sum(1 for r in tester.results if r['status'] == 'PASS')
    failed = sum(1 for r in tester.results if r['status'] == 'FAIL')
    warnings = sum(1 for r in tester.results if r['status'] == 'WARN')
    errors = sum(1 for r in tester.results if r['status'] == 'ERROR')
    
    print(f"\n📋 Security Test Summary:")
    print(f"Passed: {passed}")
    print(f"Failed: {failed}")
    print(f"Warnings: {warnings}")
    print(f"Errors: {errors}")
    
    if failed > 0 or errors > 0:
        print("⚠️  Security issues detected! Review failed tests before deployment.")
    
    # Load Test
    load_test_success = run_load_test(base_url, 200)
    
    # Final Assessment
    print(f"\n🎯 Final Assessment:")
    security_ok = failed == 0 and errors == 0
    
    if security_ok and load_test_success:
        print("✅ APPLICATION READY FOR DEPLOYMENT")
        print("✅ Security tests passed")
        print("✅ Load test passed (200 users)")
        return True
    else:
        print("❌ APPLICATION NOT READY FOR DEPLOYMENT")
        if not security_ok:
            print("❌ Security issues need to be addressed")
        if not load_test_success:
            print("❌ Performance issues need to be addressed")
        return False

if __name__ == "__main__":
    success = main()
    sys.exit(0 if success else 1)