
###Hobby Project###
Week 10 - Comic book Collection

This is the week 10 hobby project, which allows end user to keep a record of their comic books and organise them into universes. 
The project is designed to let users: insert, read, update and delete entries. 

##Getting Started

A copy of the project can be found at git clone https://github.com/Tayyibah906/HobbyProjectBackend
for you to download on your local machine for development and testing purposes. 
This project was built with the SpringTools Suite version (4.7.0)

##Prerequisites

What things you need to install the software:
•You need to have Spring Tools Suite installed
•To run it locally you need MySQL 5.7 or 8
•mvn - :run


##Installing

Install Spring Boot a full set of intructions to download and install Spring Boot can be found here:
https://docs.spring.io/spring-boot/docs/2.0.1.BUILD-SNAPSHOT/reference/html/getting-started-installing-spring-boot.html

##Unit Tests

A unit test is a piece of code written by a developer that executes a specific functionality in the code to be tested and asserts a certain behaviour or state. 
The percentage of code which is tested by unit tests is typically called test coverage. A unit test targets a small unit of code, e.g., a method or a class. 
External dependencies should be removed from unit tests by replacing the dependency with a test implementation or a (mock) object created by a test framework using technology such as Mockito. 
In this project Mockito was used to do unit tests.

##Integration Tests

Unit tests are not suitable for testing complex user interface or component interaction. For this, we should develop integration tests. 
An integration test aims to test the behaviour of a component or the integration between a set of components.
Integration tests check that the whole system works as intended, therefore they are reducing the need for intensive manual tests.
These kinds of tests allow you to translate your user stories into a test suite. The test would resemble an expected user interaction with the application. 
Integration testing with MockMVC was carried out on the controllers of this project. 

##Continuous Integration 

Jenkins would be used to automate the testing and building phase of the project.
Sonarqube would be used to for inspection of the code to highlight any code smell, bugs and vulnerabilities, 
while Nexus would be used as a private artefact repository to hold the project.
For this project sonarqube was used to inspect the quality of the code.


##Built With
•Maven - Dependency Management

##Versioning

For this project Git was used for versioning

##Authors
•Tayyibah Ali

##Acknowledgments
•Jordan Harrison
•Piers Barber
