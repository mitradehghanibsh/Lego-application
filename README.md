# Lego Robot Smart Parking System

A full-stack Java application where a Lego EV3 robot communicates with a web server to receive settings and report parking activities.

## Team Responsibilities

### Eljona Pacolli - Lead Web & Database Developer
* **Incident Reporting:** Developed the `SendData` logic on the robot to send JSON data to the server after successful parking.
* **Database Management:** Expanded the JPA entity (`Lego.java`) and created the `/getall` REST endpoint to manage statistics history.
* **Statistics UI:** Built the `statistics.html` dashboard using JavaScript Fetch API to display live parking reports from the database.
* **GitHub Management:** Organized the repository structure and managed code merges for the web-services repo.

### Allan Jakubovits - Hardware & Core Logic Specialist
* **Robot Assembly:** Handled the physical construction and sensor calibration of the EV3 robot.
* **Core Driving Logic:** Developed the movement functions, speed control, and ultrasonic sensor triggers in `RunLego.java`.
* **Parking Maneuver:** Programmed the 180-degree parking logic to ensure the robot parks safely when it detects a wall.

### Ermira Zhitia - Robot Systems Developer
* **Multithreading:** Implemented the `SettingsReader` thread to allow the robot to fetch server updates while driving.
* **Dynamic Updates:** Wrote the logic that applies new speed and turn values to the robot during runtime.

### Mitra Dehghani - Project Manager & Documentation
* **Infrastructure:** Created the GitHub repositories for the course.
* **Project Management:** Led the Jira and Confluence documentation, tracking tasks and project milestones.

## Technologies
* **Backend:** JAX-RS (Jersey), JPA (Hibernate).
* **Robot:** Java leJOS, Multithreading, HTTP.
* **Database:** MariaDB via Docker.
* **Frontend:** HTML5, JavaScript (Fetch API).