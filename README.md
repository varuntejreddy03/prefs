# Exam Helper - Flask Web Application

A comprehensive web application to help students prepare for exams using the provided questions dataset.

## Features

### 🏠 **Home Dashboard**
- Overview of total days and questions
- Quick search functionality
- Study schedule overview

### 📚 **Day-wise Study**
- Browse questions by specific days
- View question details with LeetCode links
- Practice mode for each day

### 🧠 **Interactive Quiz Mode**
- Random question selection
- Customizable quiz length (5-20 questions)
- Progress tracking
- Direct LeetCode integration

### 🔍 **Smart Search**
- Search questions by title keywords
- Filter results with question details
- Quick access to LeetCode links

### 📊 **Statistics Dashboard**
- Visual charts showing question distribution
- Day-wise breakdown analysis
- LeetCode vs Custom problem ratios

## Installation & Setup

1. **Install Dependencies**
   ```bash
   pip install -r requirements.txt
   ```

2. **Run the Application**
   ```bash
   python app.py
   ```

3. **Access the Website**
   - Open your browser and go to: `http://localhost:5000`

## File Structure

```
prefs/
├── app.py                 # Main Flask application
├── qeutions.json         # Questions dataset
├── requirements.txt      # Python dependencies
├── templates/           # HTML templates
│   ├── base.html        # Base template
│   ├── index.html       # Homepage
│   ├── day.html         # Day view
│   ├── quiz.html        # Quiz interface
│   ├── search.html      # Search results
│   └── stats.html       # Statistics
└── static/              # Static files (auto-created)
```

## Usage

### Study Mode
- Browse questions by day from the homepage
- Click on any day card to view detailed questions
- Use the "Practice This Day" feature for guided study

### Quiz Mode
- Select number of questions (5-20)
- Get random questions from all days
- Track progress with visual indicators
- Direct access to LeetCode problems

### Search & Statistics
- Use the search bar to find specific topics
- View comprehensive statistics and charts
- Analyze your study progress

## Features Highlights

- **Responsive Design**: Works on desktop and mobile
- **Bootstrap UI**: Clean, professional interface
- **Interactive Elements**: Hover effects and smooth transitions
- **Progress Tracking**: Visual progress bars in quiz mode
- **External Links**: Direct integration with LeetCode
- **Session Management**: Quiz state persistence

## Technical Details

- **Backend**: Flask (Python)
- **Frontend**: Bootstrap 5, Font Awesome icons
- **Charts**: Chart.js for statistics visualization
- **Data**: JSON-based question storage
- **Session**: Flask sessions for quiz state

Perfect for exam preparation with an intuitive interface and comprehensive features!