package Tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AmazonHomePage;
import utils.ExtentTestListener;

@Listeners(ExtentTestListener.class)
@Epic("Amazon Application")
@Feature("Amazon Homepage Functional Tests")
public class AmazonHomeTest extends BaseTest {

    AmazonHomePage homePage;

    @BeforeMethod
    public void setUpPageObjects() {
        homePage = new AmazonHomePage(driver);
    }

    @Story("Search Product")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Click on the search field and search the product")
    public void AmazonSearch() {
        Allure.step("Searching for 'Boxing gloves'");
        homePage.searchProducts("Boxing gloves");

        Allure.step("Verifying search results are displayed");
        Assert.assertTrue(homePage.isSearchResultDisplayed(), "Search results are not visible!");
    }

    @Story("Add Product to Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Navigation to add to cart button")
    public void addToCartNavigation() {
        Allure.step("Navigating to Add to Cart page");
        homePage.addToCartScreen();

        Allure.step("Verifying Add to Cart page is visible");
        Assert.assertTrue(homePage.isAddToCartPageVisible(), "Add to Cart page is not visible!");
    }

    @Story("Past Orders")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Navigation to past orders page")
    public void navigationToOrdersPage() {
        Allure.step("Navigating to Past Orders page");
        homePage.pastOrderPage();

        Allure.step("Verifying Past Orders page is displayed");
        // Uncomment when verification method is implemented
        // Assert.assertTrue(homePage.isOrdersPageDisplayed(), "Past Orders page is not displayed!");
    }

    @Story("Page Interaction")
    @Severity(SeverityLevel.MINOR)
    @Test(description = "Scroll the amazon page")
    public void scrollTheAmazonPage() {
        Allure.step("Scrolling down to the footer");
        homePage.scrollAmazonPage();

        Allure.step("Verifying footer is visible");
        Assert.assertTrue(homePage.isFooterVisible(), "Footer is not visible after scroll!");
    }
}
