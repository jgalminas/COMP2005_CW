# COMP2005 Assignment 2 - Web Service
A web service built using Java and Spring Boot while following the Test-Driven Development approach.
Additionally, a CI/CD pipeline was configured using Git and GitHub Actions and hosted on the Render hosting
provider using Docker.

## Functionality
The web service connects to an external Maternity API and exposes 4 endpoints which process
and manipulate the maternity data:

- <kbd>/patients-by-employee/{id}</kbd> - Get a list of patients seen by a specific employee.
- <kbd>/discharged-within-3-days</kbd> - Get a list of patients which were discharged within 3 days of admission.
- <kbd>/day-with-most-admissions</kbd> - Get the day of the week which has the most admissions.
- <kbd>/avg-patient-time/{id}</kbd> - Get the average admission patient duration for a specific employee.

## Testing
The web service includes a suite of automated unit, integration and functional tests achieving <kbd>97% line coverage</kbd>.

## CI/CD
GitHub's rules and actions have been configured to test the code on every pull request, only allowing
to merge if the action is completed successfully. Once new code is merged to the master branch a
deployment action is triggered which instructs Render to build the Docker container and run it.

## Tools, Platforms & Technologies Used
- Git
- GitHub & GitHub Actions
- Render Hosting Provider
- Docker
- Java
- Spring Boot
