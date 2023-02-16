Medical-Track
============
Introduction
============
 An application to collect information on the sick i.e their Condition, name and current treatment procedures
 Reminds the sick of their treatment timetable
 Records on when to take drugs or perform medical procedures
 Keeps records of missed doses and has a weekly review

 Using the Java Language, Spring Framework, PostgreSQL database and Web APIs to create the web application demo.

 Tools used:

 * `OpenJDK11 <https://www.oracle.com/java/technologies/downloads/#java11>`_
 * `SpringInitializr <https://start.spring.io/>`_
 * `IntelliJ IDEA <https://www.jetbrains.com/idea/download/#section=linux>`_
 * `Postman <https://www.postman.com/downloads/>`_
 * `pgadmin4 <https://www.pgadmin.org/download/>`_
 * `postgresql 13 <https://www.enterprisedb.com/downloads/postgres-postgresql-downloads>`_
 
Please install these tools before continuing with the guide.

Fetching the Project
--------------------

1. Open a terminal window and navigate to the directory where you want to clone the project.

2. Run the following command to clone the repository:

   .. code-block:: bash

      git clone https://github.com/markxkamau/Medical_Track.git

3. After the cloning process is complete, navigate to the project directory using the following command:

   .. code-block:: bash

      cd Medical_Track

Creating the Project
--------------------

1. Wait for the project to load, then run it by clicking the "Run" button in the top right corner of the IntelliJ IDEA window.

2. Test the API endpoints using Postman or a similar tool by sending requests to ``http://localhost:8080/patient/new_patient``.

3. If you encounter any errors, make sure that the database connection settings in the "application.properties" file match your PostgreSQL setup.

4. To modify the code, make changes to the files in the project directory, then run the updated code using the same steps as before.

I hope this helps! Let me know if you have any further questions.
