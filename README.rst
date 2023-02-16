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

OpenJDK 11:

    Visit the OpenJDK 11 download page (https://jdk.java.net/11/).
    Choose the appropriate installation package for your operating system and download it.
    Follow the instructions in the installation wizard to complete the installation process.

Spring Initializr:

    Visit the Spring Initializr website (https://start.spring.io/).
    Choose the necessary project settings, including the project type, language, and dependencies.

    Click "Generate" to generate a new project.
    Extract the generated project archive and import it into your preferred IDE.

IntelliJ IDEA:

    Visit the IntelliJ IDEA download page (https://www.jetbrains.com/idea/download/#section=linux).
    Choose the appropriate installation package for your operating system and download it.
    Follow the instructions in the installation wizard to complete the installation process.

Postman:

    Visit the Postman download page (https://www.postman.com/downloads/).
    Choose the appropriate installation package for your operating system and download it.
    Follow the instructions in the installation wizard to complete the installation process.

pgadmin4:

    Visit the pgadmin4 download page (https://www.pgadmin.org/download/).
    Choose the appropriate installation package for your operating system and download it.
    Follow the instructions in the installation wizard to complete the installation process.

PostgreSQL 13:

    Visit the PostgreSQL 13 download page (https://www.enterprisedb.com/downloads/postgres-postgresql-downloads).
    Choose the appropriate installation package for your operating system and download it.
    Follow the instructions in the installation wizard to complete the installation process.

Creating Medical_Track Project
===================================


This is a step-by-step guide for creating a Medical_Track Project with Java, Spring Framework, PostgreSQL database and Web APIs.
Installation

The following tools are needed to create the Medical_Track project:

    OpenJDK11 <https://www.oracle.com/java/technologies/downloads/#java11>_
    SpringInitializr <https://start.spring.io/>_
    IntelliJ IDEA <https://www.jetbrains.com/idea/download/#section=linux>_
    Postman <https://www.postman.com/downloads/>_
    pgadmin4 <https://www.pgadmin.org/download/>_
    postgresql 13 <https://www.enterprisedb.com/downloads/postgres-postgresql-downloads>_

Please install these tools before continuing with the guide.
Fetching the Project

    Open a terminal window and navigate to the directory where you want to clone the project.

    Run the following command to clone the repository:

    .. code-block:: bash

    git clone https://github.com/markxkamau/Medical_Track.git

    After the cloning process is complete, navigate to the project directory using the following command:

    .. code-block:: bash

    cd Medical_Track

Creating the Project

    Wait for the project to load, then run it by clicking the "Run" button in the top right corner of the IntelliJ IDEA window.

    Test the API endpoints using Postman or a similar tool by sending requests to http://localhost:8080/api/sick.

    If you encounter any errors, make sure that the database connection settings in the "application.properties" file match your PostgreSQL setup.

    To modify the code, make changes to the files in the project directory, then run the updated code using the same steps as before.
