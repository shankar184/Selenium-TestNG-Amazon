package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelUtils;

public class ExcelDrivenTest {


    @DataProvider(name = "excelData")
    public Object[][] excelDataProvider() throws Exception{
        return ExcelUtils.getExcelData(
                "src/test/java/resources/TestData.xlsx",  // your Excel file
                "Sheet1"                             // sheet with data
        );
    }

    @Test(dataProvider = "excelData")
    public void testWithExcel(String testCase,String username,String password,String searchKeyword){
        System.out.println("TestCase: " + testCase);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Search: " + searchKeyword);
    }

}
