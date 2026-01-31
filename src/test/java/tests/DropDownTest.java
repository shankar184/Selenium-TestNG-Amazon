package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.dropDownPage;

public class DropDownTest extends BaseTest {

    dropDownPage dropDownPage;

    @BeforeMethod
    public void setUpTest() {
        dropDownPage = new dropDownPage(driver);
    }



    @Test
    public void verifyClickingDropDownButton() {

        dropDownPage.openDropDownPage();
    }
}
