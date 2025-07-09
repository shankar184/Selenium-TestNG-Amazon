package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URL;

public class BaseTest {
    protected static WebDriver driver;
    protected static  String browserName;

    public WebDriver getDriver() {
        return driver;
    }
    public static String getBrowserName(){
        return browserName;
    }



    @Parameters("browser")
    @BeforeMethod
    public void setup(String browser ){
        String[] parts = browser.split(":");
        browserName = parts[0];
        boolean runOnGrid = Boolean.parseBoolean(parts[1]);
        try {
            if (runOnGrid){

//            run tests on Selenium Grid
                switch (browserName.toLowerCase()) {
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    driver= new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),firefoxOptions);
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),edgeOptions);
                    break;

                case "chrome":
                default:
                ChromeOptions chromeOptions= new ChromeOptions();
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),chromeOptions);
                break;
                }
            }
            else {
                //Run tests locally
                switch (browserName.toLowerCase()) {
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        break;
                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        driver = new EdgeDriver();
                        break;
                    case "chrome":
                    default:
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                        break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Grid url is malformed",e);
        }

        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/");

    }

    @AfterMethod
    public void tearDown() {
        if (driver !=null){
            driver.quit();
        }

    }
}
