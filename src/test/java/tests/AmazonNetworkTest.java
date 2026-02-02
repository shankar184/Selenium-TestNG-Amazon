package tests;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import pages.AmazonNetworkPage;

public class AmazonNetworkTest extends BaseTest {

    @Test
    public void captureAmazonNetworkCalls() throws InterruptedException {
         driver = new ChromeDriver();
        startDevTools(driver);
        AmazonNetworkPage amazonNetworkPage = new AmazonNetworkPage(driver);
        amazonNetworkPage.clickCartIcon();



    }

}
