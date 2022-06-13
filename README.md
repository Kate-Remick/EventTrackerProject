# Event Tracker Project

## Overview
This project is  an event tracker (Skill Distillery Week 12,13 Homework). It includes a MySQL database and utilizes Spring Data JPA and Spring Web features to make the backend fully accessible to a the front end. All entities were tested using Junit and all routes below have been tested using postman. The front end is

## Description and Use (includes expected routes)
Sleep can be hard sometimes, and getting a good night's rest is elusive to many people. One thing that can assist in achieving the dream(s) is an understanding of what things work best for you developed from data about your sleep history. This program takes data from a user about each night's sleep, records it, and can give back recommendations based on how the user felt the quality was for each night. The database in this case was pre-populated with 30 days of sleep.

Expected Routes:
* List all nights of sleep: (Get) http://localhost:8083/api/sleep
* Show one night's sleep: (Get) http://localhost:8083/api/sleep/{id}
* Add a night of sleep: (Post) http://localhost:8083/api/sleep
  JSON for adding a sleep object:
  {
    "start": "2022-05-30T21:05:00",
    "end": "2022-05-31T05:10:00",
    "hadAlcohol": false,
    "largeDinner": false,
    "excercised": true,
    "tookNap": false,
    "quality": 7,
    "eveningActivity": {
        "id": 1,
        "name": "Read a Book"
    },
    "workout": {
        "id": 1,
        "timeOfDay": "Morning"
    }
}
* Edit a night of sleep: (Put) http://localhost:8083/api/sleep/{id}
* Delete a night of sleep: (Delete) http://localhost:8083/api/sleep/{id}
* List nights of sleep by time of day worked out: (Get) http://localhost:8083/api/sleep/workout/{id}
* List nights of sleep by evening activity: (Get) http://localhost:8083/api/sleep/activity/{id}
* List nights of sleep by quality group (between first and second number): (Get) http://localhost:8083/api/sleep/4/8
* List recommended evening activities: (Get) http://localhost:8083/api/recommendations/goodActivities
* List worst evening activities for a good nights sleep: (Get) http://localhost:8083/api/recommendations/badActivities
* Show recommendation for a large or small dinner: (Get) http://localhost:8083/api/recommendations/dinner
* Show recommendation for weather user should avoid naps: (Get) http://localhost:8083/api/recommendations/nap
* Show recommendation for when a user should work out (morning, noon, night): (Get) http://localhost:8083/api/recommendations/workout
* Show recommendation for when a user should go to bed: (Get) http://localhost:8083/api/recommendations/sleepStart
* Show recommendation for when a user should get up: (Get) http://localhost:8083/api/recommendations/sleepEnd
* Show recommendation for sleep duration: (Get) http://localhost:8083/api/recommendations/duration
* List all evening activities: (Get) http://localhost:8083/api/activities
* Add/Create a new activity: (Post) http://localhost:8083/api/activities
  JSON for adding an activity:
  {
        "name": "Social Event"
    }
* List all workouts (workout times): (Get) http://localhost:8083/api/workouts

## Technologies used:
* Java
* JavaScript
* MySQL Workbench
* MySQL
* Gradle
* Git/GitHub
* Java Persistance API (JPA)
* RESTful services
* Postman
* Junit

## Lessons Learned
 * Getting elapsed time between two LocalDateTimes in Java can be difficult, as simple subtraction was not available. To get around this, I converted the hours to integers, and the minute to a decimals reflecting the percentage of the hour passed and subtracted the numbers after the conversion.
 * Always, always reboot the server before testing changes that have been made.
 * Sorting in JS is difficult with numbers due to how js reads them.
 * Keeping in mind how asynchronous calls to a server take place after all functions already on the stack to prevent null/undefined references.
