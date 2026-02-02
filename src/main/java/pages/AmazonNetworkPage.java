package pages;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.waitUtils;

public class AmazonNetworkPage {

    WebDriver driver;
    waitUtils waitUtils;
    @FindBy(xpath="//span[@class='nav-cart-icon nav-sprite']")
    WebElement cartIcon;



    public AmazonNetworkPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
        waitUtils = new waitUtils(driver);
    }

    public void clickCartIcon(){
        driver.get("https://www.amazon.com");
        waitUtils.waitForElementToBeClickable(cartIcon,3).click();


    }
}
