-----

# Test Automation Engine

***A production-grade, end-to-end test automation framework built on Java, Selenium, and TestNG.***

## Table of Contents

  * [Overview](https://www.google.com/search?q=%23overview)
  * [Key Features](https://www.google.com/search?q=%23key-features)
  * [Technologies](https://www.google.com/search?q=%23technologies)
  * [Getting Started](https://www.google.com/search?q=%23getting-started)
  * [Project Structure](https://www.google.com/search?q=%23project-structure)
  * [Reports and Logs](https://www.google.com/search?q=%23reports-and-logs)
  * [Contributing](https://www.google.com/search?q=%23contributing)
  * [Contact](https://www.google.com/search?q=%23contact)
  * [License](https://www.google.com/search?q=%23license)

-----

## Overview

This repository contains a robust, scalable, and maintainable test automation framework designed for web application testing. The framework is built with industry best practices, including the **Page Object Model (POM)**, and is designed for easy integration with various CI/CD pipelines.

## Key Features

  * **Data-Driven Testing:** Supports test data from various sources (e.g., `.xlsx`, `.csv`).
  * **Cross-Browser Support:** Execute tests on multiple browsers including Chrome, Firefox, and Edge.
  * **Parallel Execution:** Configurable parallel execution of tests to reduce run time.
  * **Advanced Reporting:** Generates comprehensive and interactive **Extent Reports** with test case details, screenshots, and logs.
  * **Retry Mechanism:** Automatically retries failed test cases for improved stability and reduced flakiness.
  * **CI/CD Integration:** Ready for integration with Jenkins, GitHub Actions, or other CI tools.

## Technologies

  * **Language:** Java
  * **Automation Library:** Selenium WebDriver
  * **Test Framework:** TestNG
  * **Build Tool:** Maven
  * **Reporting:** ExtentReports
  * **Logging:** Log4j2
  * **Web Drivers:** WebDriverManager

## Getting Started

Follow these instructions to get a copy of the project up and running on your local machine.

### Prerequisites

  * **Java Development Kit (JDK) 11+**
  * **Maven 3.6+**
  * Your preferred IDE (IntelliJ IDEA, Eclipse)

### Installation

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/PrabodhGhosh/test-automation-engine.git
    ```
2.  **Navigate to the project directory:**
    ```sh
    cd test-automation-engine
    ```
3.  **Build the project:**
    ```sh
    mvn clean install
    ```

### How to Run Tests

  * **Run via TestNG XML:**
    ```sh
    mvn test -DsuiteXmlFile=testng.xml
    ```
  * **Run via Maven Profile:**
    ```sh
    mvn test -P&lt;profile_name&gt;
    ```
    (You will need to define your profiles in `pom.xml`)

## Project Structure

A brief overview of the project directory.

  * `src/main/java`: Contains core framework classes, utilities, and base pages.
  * `src/test/java`: Contains test classes and Page Object Model classes.
  * `src/test/resources`: Holds configuration files (`log4j2.xml`, `config.properties`) and test data.
  * `pom.xml`: Maven configuration file with all dependencies.
  * `testng.xml`: TestNG suite file for running tests.

## Reports and Logs

After a test run, the generated reports can be found in the `test-output` directory:

  * **Extent Report:** `test-output/ExtentReport.html`

## Contributing

Feel free to open issues or submit pull requests. All contributions are welcome.

## Contact

Prabodh Ghosh - `ghosh.prabodh@gmail.com`

-----

## License

This project is licensed under the MIT License - see the `LICENSE` file for details.

This video [explains what a `README.md` file is](https://www.google.com/search?q=%5Bhttps://www.youtube.com/watch%3Fv%3DacZHXGAZHls%5D\(https://www.youtube.com/watch%3Fv%3DacZHXGAZHls\)) and how it's used to provide an overview of a GitHub project.
http://googleusercontent.com/youtube_content/16
