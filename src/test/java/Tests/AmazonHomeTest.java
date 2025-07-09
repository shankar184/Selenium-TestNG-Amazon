package Tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AmazonHomePage;
import utils.ExtentTestListener;

@Listeners(ExtentTestListener.class)
public class AmazonHomeTest extends BaseTest {

    AmazonHomePage homePage;

    @BeforeMethod
    public void setUpPageObjects() {
        homePage = new AmazonHomePage(driver);
    }

    @Test(description = "click on the search field and search the product")
    public void AmazonSearch() {
        homePage.searchProducts("Boxing gloves");
        Assert.assertTrue(homePage.isSearchResultDisplayed(), "Search results are not visible!");
    }

    @Test(description = "navigation to add to cart button")
    public void addToCartNavigation() {
        homePage.addToCartScreen();
        Assert.assertTrue(homePage.isAddToCartPageVisible(), "Add to Cart page is not visible!");
    }

    @Test(description = "navigation to past orders page")
    public void navigationToOrdersPage() {
        homePage.pastOrderPage();
//        Assert.assertTrue(homePage.isOrdersPageDisplayed(), "Past Orders page is not displayed!");
    }

    @Test(description = "Scroll the amazon page")
    public void scrollTheAmazonPage() {
        homePage.scrollAmazonPage();
        Assert.assertTrue(homePage.isFooterVisible(), "Footer is not visible after scroll!");
    }
}
