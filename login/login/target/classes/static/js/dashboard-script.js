document.addEventListener('DOMContentLoaded', function() {
    const courseList = document.getElementById('course-list');
    
    // Simulated course data (in a real application, this would come from the server)
    const courses = [
        { id: 1, name: 'Introduction to Computer Science' },
        { id: 2, name: 'Web Development Fundamentals' },
        { id: 3, name: 'Data Structures and Algorithms' }
    ];

    // Populate the course list
    courses.forEach(course => {
        const li = document.createElement('li');
        li.textContent = course.name;
        courseList.appendChild(li);
    });
});