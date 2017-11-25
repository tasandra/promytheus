package tests;

import data.ExcelReadApi;
import menus.TalentMenu;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CategoryPage;
import pages.TalentsPage;

import java.util.NoSuchElementException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CategoryPageTest extends BaseTest {

    @Test
    public void login(){
        loginPage.submitLogin("kusiwa@cmail.club", "password");
    }

    // create new talent and select category
    @Test (groups = "p1", dataProvider="category",invocationCount = 3)
    public void selectCategory1(String category){
        // create new users
        talentsPage.clickNew();
// get category page tap turns blue
        try {
            String className = categoryPage.getActiveTab();
            assertTrue(className.contains("active"), "Category tap is not active");
        }
        catch (AssertionError  e){
            System.out.println(e);
        }
        catch (StaleElementReferenceException e){
            System.out.println(e);
        }
        catch (NoSuchElementException e){
            System.out.println(e);
        }

// select category from data provider
        categoryPage.selectCategory(category);
// assert selected category appeared on the header
        String categorySelected = categoryPage.getCategory();
        assertEquals(category, categorySelected);
// save new user and back to the talents page
        menu.clickNext();
        categoryPage.clickTalents();
        String header = talentsPage.getHeader();
        assertEquals("Talents", header, " user is not back to talents page ");
    }

    // check error message
    @Test (priority = 2)
    public void getError(){
  // leave category field empty, click next and assert error message
        talentsPage.clickNew();
        menu.clickNext();
        String error = categoryPage.getError();
        assertEquals("Please select a talent category first.", error, "Error message not displayed");
    }

    @DataProvider(name="category")
    public Object[][] getCategory() throws Exception
    {
        ExcelReadApi excel = new ExcelReadApi("promy.xlsx");
        Object[][] data = excel.rowData("category");
        return data;
    }

}
