package base;

import org.bouncycastle.cert.ocsp.Req;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v144.network.Network;
import org.openqa.selenium.devtools.v144.network.model.Request;


import org.openqa.selenium.devtools.v144.network.model.Response;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Map;
import java.util.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;





import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BaseTest {
    protected static WebDriver driver;
    protected static String browserName;

    public WebDriver getDriver() {
        return driver;
    }

    public static String getBrowserName() {
        return browserName;
    }

    @Parameters("browser")
    @BeforeMethod
    public void setup(@org.testng.annotations.Optional("chrome:false") String browser) {
        String[] parts = browser.split(":");
        browserName = parts[0];
        boolean runOnGrid = Boolean.parseBoolean(parts[1]);

        try {
            if (runOnGrid) {
                // Run tests on Selenium Grid
                switch (browserName.toLowerCase()) {
                    case "firefox":
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        firefoxOptions.addArguments("--headless");
                        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);
                        break;

                    case "edge":
                        EdgeOptions edgeOptions = new EdgeOptions();
                        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), edgeOptions);
                        break;

                    case "chrome":
                    default:
                        ChromeOptions chromeOptions = new ChromeOptions();
                        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
                        break;
                }
            } else {
                // Run tests locally
                switch (browserName.toLowerCase()) {
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        FirefoxOptions fireFoxOptions = new FirefoxOptions();
                        fireFoxOptions.addArguments("--headless");
                        driver = new FirefoxDriver(fireFoxOptions);
                        break;

                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        EdgeOptions edgeOptions = new EdgeOptions();
                        edgeOptions.addArguments("--headless");
                        driver = new EdgeDriver(edgeOptions);
                        break;

                    case "chrome":
                    default:
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions options = new ChromeOptions();
//                        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
                        driver = new ChromeDriver();
                        break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Grid URL is malformed or browser setup failed", e);
        }

        driver.manage().window().maximize();
//        driver.get("https://www.amazon.in/");
        driver.get("https://ui.shadcn.com/docs/components/dropdown-menu");
    }

    public DevTools startDevTools(WebDriver driver) {
        DevTools devTools = ((HasDevTools)driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(
           Optional.empty(),
           Optional.empty(),
           Optional.empty(),
                Optional.empty(),
                Optional.empty()
        ));
        Map<String ,String> requestMethodMap = new ConcurrentHashMap<>();

        devTools.addListener(Network.requestWillBeSent(),event -> {

            String requestId=event.getRequestId().toString();
            String method= event.getRequest().getMethod();
            requestMethodMap.put(requestId,method);

//            Request request = event.getRequest();
//            System.out.println("URL : " + event.getRequest().getUrl());


        });
        devTools.addListener(Network.responseReceived(),event -> {
            String requestId = event.getRequestId().toString();
            Response response = event.getResponse();
            String url = response.getUrl();
            int status = response.getStatus().intValue();
            String method = requestMethodMap.getOrDefault(requestId,"NA");

            if (url.contains("www.amazon.in") && url.contains("fetch") || url.contains("XHR") ) {

                System.out.println("URL    : " + url);
                System.out.println("Method : " + method);
                System.out.println("Status : " + status);
                System.out.println("-----------------------------------");
            }
        });
        return devTools;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
