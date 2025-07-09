✅ **Features Implemented**:
✅ Page Object Model (POM) for clean code structure
✅ TestNG for managing test execution
✅ ExtentReports for rich HTML reporting
✅ Screenshots captured on test pass, fail, and skip
✅ Cross-browser support via TestNG XML parameters
✅ Maven for build and dependency management
✅ Dynamic browser name displayed in Extent Reports
✅ Parallel execution ready (can be extended easily)
✅ Clean src/main/java for core utilities and src/test/java for tests
✅ GitHub Actions CI integration
✅ Selenium Grid support for distributed test execution

🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧

**🛠 Technologies Used**
Java 23+
Selenium WebDriver 4.18.1
TestNG 7.9.0
ExtentReports 5.1.1
Maven
WebDriverManager for automatic driver handling

🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧

🚦 **Running the Tests**
From IntelliJ:
_Open the project.
Right-click on testng.xml → Run.
From Maven CLI:
bash
Copy
Edit
mvn clean test
Ensure mvn is installed and configured in your terminal._

🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧

📁 src
┣ 📁 main
┃ ┗ 📁 java
┃   ┣ 📁 base         # BaseTest class (browser setup)
┃   ┣ 📁 pages        # All Page Object classes
┃   ┗ 📁 utils        # WaitUtils, ExtentManager
┣ 📁 test
┃ ┗ 📁 java
┃   ┣ 📁 Tests        # All TestNG test classes
┃   ┗ 📁 utils        # ScreenshotUtils, ExtentTestListener

![GitHub Workflow Status](https://github.com/shankar184/Selenium-TestNG-Amazon/actions/workflows/selenium.yml/badge.svg?branch=develop)
