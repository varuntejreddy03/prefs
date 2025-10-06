#!/usr/bin/env python3
"""
Data validation script for questions.json
"""

import json
import os

def validate_questions_data():
    """Validate the questions.json file structure and data"""
    
    print("🔍 Validating questions.json...")
    
    # Check if file exists
    if not os.path.exists('qeutions.json'):
        print("❌ ERROR: qeutions.json file not found!")
        return False
    
    try:
        with open('qeutions.json', 'r') as f:
            data = json.load(f)
    except json.JSONDecodeError as e:
        print(f"❌ ERROR: Invalid JSON format: {e}")
        return False
    
    # Validate structure
    if not isinstance(data, list):
        print("❌ ERROR: Root should be a list of days")
        return False
    
    total_questions = 0
    question_ids = set()
    
    for i, day in enumerate(data):
        # Validate day structure
        required_fields = ['day', 'date', 'questions']
        for field in required_fields:
            if field not in day:
                print(f"❌ ERROR: Day {i} missing field: {field}")
                return False
        
        # Validate day number
        if day['day'] != i:
            print(f"❌ ERROR: Day {i} has incorrect day number: {day['day']}")
            return False
        
        # Validate questions
        if not isinstance(day['questions'], list):
            print(f"❌ ERROR: Day {i} questions should be a list")
            return False
        
        for j, question in enumerate(day['questions']):
            # Validate question structure
            required_q_fields = ['id', 'title', 'leetcode_link']
            for field in required_q_fields:
                if field not in question:
                    print(f"❌ ERROR: Day {i}, Question {j} missing field: {field}")
                    return False
            
            # Check for duplicate IDs
            if question['id'] in question_ids:
                print(f"❌ ERROR: Duplicate question ID: {question['id']}")
                return False
            question_ids.add(question['id'])
            
            total_questions += 1
    
    print(f"✅ Data validation passed!")
    print(f"📊 Statistics:")
    print(f"   - Total days: {len(data)}")
    print(f"   - Total questions: {total_questions}")
    print(f"   - Unique question IDs: {len(question_ids)}")
    print(f"   - Average questions per day: {total_questions/len(data):.1f}")
    
    # Check for Java files
    pfs_path = "PFS"
    if os.path.exists(pfs_path):
        java_files = []
        for root, dirs, files in os.walk(pfs_path):
            for file in files:
                if file.endswith('.java'):
                    java_files.append(file)
        
        print(f"   - Java solution files found: {len(java_files)}")
    else:
        print("   - PFS folder not found (Java solutions may not be available)")
    
    return True

def validate_templates():
    """Validate that all required templates exist"""
    
    print("\n🔍 Validating templates...")
    
    required_templates = [
        'templates/base.html',
        'templates/index.html',
        'templates/day.html',
        'templates/quiz.html',
        'templates/search.html',
        'templates/editor.html',
        'templates/solution.html'
    ]
    
    missing_templates = []
    for template in required_templates:
        if not os.path.exists(template):
            missing_templates.append(template)
    
    if missing_templates:
        print("❌ ERROR: Missing templates:")
        for template in missing_templates:
            print(f"   - {template}")
        return False
    
    print("✅ All required templates found!")
    return True

def main():
    print("Running Data Validation Tests")
    print("=" * 40)
    
    data_valid = validate_questions_data()
    templates_valid = validate_templates()
    
    print(f"\n🎯 Validation Summary:")
    if data_valid and templates_valid:
        print("✅ ALL VALIDATIONS PASSED - Ready for deployment!")
        return True
    else:
        print("❌ VALIDATION FAILED - Fix issues before deployment!")
        return False

if __name__ == "__main__":
    import sys
    success = main()
    sys.exit(0 if success else 1)