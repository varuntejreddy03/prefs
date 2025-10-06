from flask import Flask, render_template, jsonify, request, session
import json
import random
import os
from datetime import datetime, timedelta

app = Flask(__name__)
app.secret_key = os.environ.get('SECRET_KEY', 'exam_helper_secret_key_change_in_production')

# Security headers middleware
@app.after_request
def add_security_headers(response):
    response.headers['X-Content-Type-Options'] = 'nosniff'
    response.headers['X-Frame-Options'] = 'DENY'
    response.headers['X-XSS-Protection'] = '1; mode=block'
    response.headers['Referrer-Policy'] = 'strict-origin-when-cross-origin'
    return response

def load_questions():
    with open('qeutions.json', 'r') as f:
        return json.load(f)

def get_basic_stats():
    data = load_questions()
    return {
        'total_days': len(data),
        'total_questions': sum(len(day['questions']) for day in data)
    }

@app.route('/')
def index():
    data = load_questions()
    progress = get_basic_stats()
    return render_template('index.html', days=data, progress=progress)

@app.route('/study/<int:day_num>')
def day_questions(day_num):
    data = load_questions()
    day_data = next((day for day in data if day['day'] == day_num), None)
    if not day_data:
        return "Day not found", 404
    return render_template('day.html', day=day_data)

# Redirect old URLs
@app.route('/day/<int:day_num>')
def redirect_old_day(day_num):
    from flask import redirect, url_for
    return redirect(url_for('day_questions', day_num=day_num), code=301)

@app.route('/quiz')
def quiz():
    return render_template('quiz.html')

@app.route('/api/random-question')
def random_question():
    data = load_questions()
    all_questions = []
    for day in data:
        for q in day['questions']:
            all_questions.append({**q, 'day': day['day'], 'date': day['date']})
    
    if not all_questions:
        return jsonify({'error': 'No questions available'})
    
    question = random.choice(all_questions)
    return jsonify(question)

@app.route('/api/quiz-session', methods=['POST'])
def create_quiz_session():
    data = load_questions()
    all_questions = []
    for day in data:
        for q in day['questions']:
            all_questions.append({**q, 'day': day['day'], 'date': day['date']})
    
    num_questions = min(int(request.json.get('count', 10)), len(all_questions))
    quiz_questions = random.sample(all_questions, num_questions)
    
    session['quiz_questions'] = quiz_questions
    session['current_question'] = 0
    session['score'] = 0
    
    return jsonify({'total': len(quiz_questions), 'started': True})

@app.route('/api/quiz-question')
def get_quiz_question():
    if 'quiz_questions' not in session:
        return jsonify({'error': 'No active quiz session'})
    
    current = session.get('current_question', 0)
    questions = session['quiz_questions']
    
    if current >= len(questions):
        return jsonify({'finished': True, 'score': session.get('score', 0), 'total': len(questions)})
    
    return jsonify({
        'question': questions[current],
        'current': current + 1,
        'total': len(questions)
    })

@app.route('/api/next-question', methods=['POST'])
def next_question():
    if 'quiz_questions' not in session:
        return jsonify({'error': 'No active quiz session'})
    
    session['current_question'] = session.get('current_question', 0) + 1
    return jsonify({'success': True})

@app.route('/code/<int:question_id>')
def code_editor(question_id):
    data = load_questions()
    question = None
    
    for day in data:
        for q in day['questions']:
            if q['id'] == question_id:
                question = {**q, 'day': day['day'], 'date': day['date']}
                break
    
    if not question:
        return "Question not found", 404
    
    return render_template('editor.html', question=question)

# Redirect old URLs
@app.route('/editor/<int:question_id>')
def redirect_old_editor(question_id):
    from flask import redirect, url_for
    return redirect(url_for('code_editor', question_id=question_id), code=301)

@app.route('/answer/<int:question_id>')
def view_solution(question_id):
    data = load_questions()
    question = None
    
    for day in data:
        for q in day['questions']:
            if q['id'] == question_id:
                question = {**q, 'day': day['day'], 'date': day['date']}
                break
    
    if not question:
        return "Question not found", 404
    
    # Try to find Java solution file
    java_code = get_java_solution(question['date'], question['title'])
    
    return render_template('solution.html', question=question, java_code=java_code)

# Redirect old URLs
@app.route('/solution/<int:question_id>')
def redirect_old_solution(question_id):
    from flask import redirect, url_for
    return redirect(url_for('view_solution', question_id=question_id), code=301)

def get_java_solution(date, title):
    import os
    import glob
    
    # Search all PFS folders for matching Java files by name
    pfs_path = "PFS"
    if os.path.exists(pfs_path):
        # Get all Java files in all folders
        all_java_files = glob.glob(f"{pfs_path}/**/*.java", recursive=True)
        
        # Try to match by filename
        title_variations = [
            title.lower().replace(' ', ''),
            title.lower().replace(' ', '_'),
            title.lower().replace(' ', '-'),
            title.replace(' ', ''),
            title.replace(' ', '_'),
            title.replace(' ', '-')
        ]
        
        for java_file in all_java_files:
            filename = os.path.basename(java_file).replace('.java', '').lower()
            
            # Check various matching patterns
            for variation in title_variations:
                if variation in filename or filename in variation:
                    try:
                        with open(java_file, 'r', encoding='utf-8') as f:
                            return f.read()
                    except:
                        continue
        
        # If no match found, try keyword matching
        keywords = [word.lower() for word in title.split() if len(word) > 3]
        for java_file in all_java_files:
            filename = os.path.basename(java_file).lower()
            if any(keyword in filename for keyword in keywords):
                try:
                    with open(java_file, 'r', encoding='utf-8') as f:
                        return f.read()
                except:
                    continue
    
    return None

def get_additional_java_files():
    import os
    import glob
    
    additional_files = []
    
    # Get all question titles from JSON
    data = load_questions()
    json_titles = set()
    for day in data:
        for question in day['questions']:
            json_titles.add(question['title'].lower())
    
    # Get all Java files
    pfs_path = "PFS"
    if os.path.exists(pfs_path):
        all_java_files = glob.glob(f"{pfs_path}/**/*.java", recursive=True)
        
        for java_file in all_java_files:
            filename = os.path.basename(java_file)
            title = filename.replace('.java', '')
            
            # Check if this Java file matches any JSON question
            is_matched = False
            for json_title in json_titles:
                title_variations = [
                    json_title.replace(' ', ''),
                    json_title.replace(' ', '_'),
                    json_title.replace(' ', '-')
                ]
                
                filename_lower = filename.lower()
                for variation in title_variations:
                    if variation in filename_lower or filename_lower.replace('.java', '') in variation:
                        is_matched = True
                        break
                
                if is_matched:
                    break
            
            # If not matched, it's an additional file
            if not is_matched:
                folder_name = os.path.basename(os.path.dirname(java_file))
                additional_files.append({
                    'filename': filename,
                    'title': title,
                    'path': java_file,
                    'folder': folder_name
                })
    
    return additional_files



@app.route('/search')
def search():
    query = request.args.get('q', '').lower()
    data = load_questions()
    results = []
    
    for day in data:
        for q in day['questions']:
            if query in q['title'].lower():
                results.append({**q, 'day': day['day'], 'date': day['date']})
    
    return render_template('search.html', results=results, query=query)



@app.route('/java-file/<path:file_path>')
def view_java_file(file_path):
    import os
    
    try:
        if os.path.exists(file_path) and file_path.startswith('PFS/'):
            with open(file_path, 'r', encoding='utf-8') as f:
                content = f.read()
            
            filename = os.path.basename(file_path)
            title = filename.replace('.java', '')
            
            return render_template('java_file.html', 
                                 title=title, 
                                 filename=filename, 
                                 content=content)
    except:
        pass
    
    return "File not found", 404

if __name__ == '__main__':
    # Production settings
    debug_mode = os.environ.get('FLASK_ENV') != 'production'
    port = int(os.environ.get('PORT', 5000))
    host = '0.0.0.0' if not debug_mode else '127.0.0.1'
    
    app.run(debug=debug_mode, host=host, port=port, threaded=True)