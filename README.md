-----

# Test Automation Engine

***A production-grade, end-to-end test automation framework built on Java, Selenium, and TestNG.***

## Table of Contents

 * [Overview](#overview)
* [Key Features](#key-features)
* [Technologies](#technologies)
* [Getting Started](#getting-started)
* [Project Structure](#project-structure)
* [Reports and Logs](#reports-and-logs)
* [Contributing](#contributing)
* [Contact](#contact)

-----

### Overview

Built with industry best practices, including the Page Object Model (POM) and TestNG, this framework is designed for easy integration into various CI/CD pipelines. It is highly resilient, able to handle transient issues and dynamic web elements, and supports advanced data management for complex test scenarios.

### Key Features

**Advanced Data Management:** Supports flexible, structured test data from JSON files, allowing for complex and nested test scenarios.

**Resilient Execution:** Automatically retries failed tests to mitigate flakiness and uses a custom Fluent Wait utility to gracefully handle dynamic web elements and transient network delays.

**Cross-Browser Support:** Execute tests on multiple browsers including Chrome, Firefox, and Edge.

**Parallel Execution:** Configurable parallel execution of tests to significantly reduce run time.

**Advanced Reporting:** Generates comprehensive and interactive Extent Reports with test case details, screenshots, and logs.

**CI/CD Integration:** Ready for integration with Jenkins, GitHub Actions, or other CI tools.

### Technologies

**Language:** Java

**Automation Library:** Selenium WebDriver

**Test Framework:** TestNG

**Build Tool:** Maven

**Reporting:** ExtentReports

**Logging:** Log4j2

**JSON Parsing:** Gson

**Web Drivers:** WebDriverManager

### Getting Started

Follow these instructions to get a copy of the project up and running on your local machine.

#### Prerequisites

Java Development Kit (JDK) 11+

Maven 3.6+

Your preferred IDE (IntelliJ IDEA, Eclipse)

#### Installation

Clone the repository:

```
git clone 

https://www.google.com/search?q=https://github.com/PrabodhGhosh/test−automation−engine.git
(https://www.google.com/search?q=https://github.com/PrabodhGhosh/test-automation-engine.git)

```

Navigate to the project directory:

```
cd test-automation-engine

```

Build the project:

```
mvn clean install

```

### How to Run Tests

**Run via TestNG XML:**

```
mvn test -DsuiteXmlFile=testng.xml

```

**Run via Maven Profile:**

```
mvn test -P<profile_name>

```

(You will need to define your profiles in `pom.xml`)

### Project Structure

A brief overview of the project directory.

`src/main/java`: Contains core framework classes, utilities, and base pages.

`src/test/java`: Contains test classes and Page Object Model classes.

`src/test/resources`: Holds configuration files (`log4j2.xml`, `config.properties`) and test data (`.json` files).

`pom.xml`: Maven configuration file with all dependencies.

`testng.xml`: TestNG suite file for running tests.

### Reports and Logs

After a test run, the generated reports can be found in the `test-output` directory:

**Extent Report:** `test-output/ExtentReport.html`

### Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

Feel free to open issues or submit pull requests. All contributions are welcome.

### Contact

Prabodh Ghosh - 

ghosh.prabodh@gmail.com
(mailto:ghosh.prabodh@gmail.com)

Project Link: 

https://www.google.com/search?q=https://github.com/PrabodhGhosh/test−automation−engine
(https://www.google.com/search?q=https://www.google.com/search?q=https://github.com/PrabodhGhosh/test-automation-engine)
-----
