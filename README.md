# Devowelizer API Testing

This project is intended to automate tests for the Devowelizer RESTful API, which removes vowels (*a*, *e*, *i*, *o*, *u*) from the input string.

## API Endpoints

- **GET /:input**  This endpoint receives a string input and returns the input string without vowels.

### Example

- **Request**: `GET http://localhost:8080/input`
- **Response**: `"npt"` for input `"input"`


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Ensure that you have the following installed on your local machine:

1. Java
2. Maven
3. Docker
4. Git

### Installation and running

1. Clone the repository:
   `git clone git@github.com:nilunassiri/Casumo-SDET-Test.git`

2. Go inside the directory:
   `cd DevowelizerServiceTest`

3. Run the docker service:
   `docker run -p 8080:8080 -it casumo/devowelizer:latest`
   This will run the Devowelizer service at http://localhost:8080.

4. Run the tests:
   `mvn clean test`


## Running the tests

The tests are built using the TestNG framework. I use REST Assured to easily test HTTP/REST services.

The test class DevowelizerServiceTest.java conducts different scenarios:

- Service availability test (`serviceShouldBeUp`) verifying if the service is up and running.
- Standard functionality testing with various types of inputs.
- Edge case testing with empty strings, strings without vowels, numerical input, special character input, case-sensitive input, non-English character input.
- Testing with longer input strings.
- Response time testing for performance checks.

### Known issues

Currently, 3 tests are failing:

1. shouldNotDifferentiateCase: Test with case-sensitive input.

- Expected Result: hElL
- Actual Result: Hll

2. shouldHandleLongStringsXXL: Test with long string input of 5000 chars.

- Expected Result: Hll (for input of 5000 characters)
- Actual Result: 404 Not Found (or other server error)

3. shouldResponseIn1000ms: Test for response time 1000 ms.

- Expected Result: Response time < 1000ms
- Actual Result: Response time > 1000ms

## Built With

- [TestNG](https://testng.org/doc/) - Test framework
- [Maven](https://maven.apache.org/) - Dependency management
- [REST Assured](https://rest-assured.io/) - Used for testing HTTP/REST services
- [Docker](https://www.docker.com/) - Used to set up the service environment


## Authors

- Nilu Nassiri - *Initial work*
- nilu.nassiri@gmail.com