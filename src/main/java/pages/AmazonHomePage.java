package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.Assertion;
import utils.waitUtils;

public class AmazonHomePage {
    WebDriver driver;
    waitUtils waitUtils;


//    private By searchProduct = By.xpath("//input[@placeholder='Search Amazon.in']");
//    private  By addCartBtn = By.xpath("//span[@id='nav-cart-count']");
//    private  By pastOrders= By.id("nav-orders");
//    @FindBy(xpath = "//input[@placeholder='Search Amazon.in']")
//    WebElement searchProduct;

    @FindBy(id = "nav-orders")
    WebElement pastOrders;

    @FindBy(xpath = "//span[@id='nav-cart-count']")
    WebElement addCartBtn;

    @FindBy(xpath = "//input[@placeholder='Search Amazon.in']")
    WebElement searchProduct;

    @FindBy(xpath = "//a[contains(text(),'Conditions of Use & Sale')]")
    WebElement footer;

    @FindBy(xpath = "//h1[text()='Conditions of Use']")
    WebElement headerText;

    @FindBy(xpath = "//h2[text()='Results']")
    WebElement searchResultElement;

    @FindBy(xpath = "//a[text()='Cart']")
    WebElement addToCartElement;

    @FindBy(xpath = "//h1[text()='Your Orders']")
    WebElement ordersHeader;

    @FindBy(xpath = "//h3[normalize-space()='Your Amazon Cart is empty']")
    WebElement cartPageText;


    public AmazonHomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
       waitUtils= new waitUtils(driver);
    }

    public void searchProducts(String productName){
        waitUtils.waitForElementToBeClickable(searchProduct,5).click();
        waitUtils.waitForElementToBeClickable( searchProduct,3).sendKeys(productName+ Keys.ENTER);
    }

    public void addToCartScreen(){
        waitUtils.waitForElementToBeVisible(addCartBtn,3).click();
    }

    public void pastOrderPage(){
        waitUtils.waitForElementToBeVisible(pastOrders,3).click();
    }

    public void scrollAmazonPage(){
        waitUtils.scrollToBottom();
        WebElement visibleFooter = waitUtils.waitForElementToBeVisible(footer, 10);
        visibleFooter.click();
        waitUtils.waitForElementToBeVisible(headerText,5)
;        if (headerText.isDisplayed()){
            System.out.println(headerText.getText());
        }
    }
    public boolean isSearchResultDisplayed() {
        return waitUtils.waitForElementToBeVisible(searchResultElement,3).isDisplayed();  // Replace with actual locator
    }

    public boolean isAddToCartPageVisible() {
        return waitUtils.waitForElementToBeVisible(cartPageText,3).isDisplayed();   // Replace with actual locator
    }

//    public boolean isOrdersPageDisplayed() {
//        return waitUtils.waitForElementToBeVisible(ordersHeader,3).isDisplayed();   // Replace with actual locator
//    }

    public boolean isFooterVisible() {
        return waitUtils.waitForElementToBeVisible(footer,3).isDisplayed();
    }


}
