package tests;

import menus.HomeMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.LoginPage;
import pages.TalentsPage;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TalentsPageTest extends ValidLoginTest{
    private LoginPage loginPage;
    private TalentsPage talents;
    private CategoryPage categoryPage;
    private HomeMenu menu;

    @BeforeClass
    public void beforeClass(){
        loginPage = new LoginPage(driver);
        talents = new TalentsPage(driver);
        categoryPage = new CategoryPage(driver);
        menu = new HomeMenu(driver);
    }
// select number of rows per page and checked list size
    @Test  (priority = 1)
    public void numberOfRows(){
        talents.selectRowsOnPage("25");
        //TODO select 25 and assert

        // select 10 rows per page
        talents.selectRowsOnPage("10");
        int numberOfRows = talents.getNumberOfRow();
        assertEquals(10, numberOfRows, "wrong number of rows per page");
    }
    // click disable button
    @Test (priority = 2)
    public void disableTalent(){
        // click checkbox and click disable
        talents.clickCheckbox();
        talents.clickDisable();
        // assert status became disable - red circle
        String status = talents.getStatus();
        assertEquals("INACTIVE", status, "disable button not changed status to INACTIVE");
        // uncheck checkbox
        talents.clickCheckbox();
    }

    // click enable button
    @Test (priority = 3)
    public void enableTalent(){
        // click checkbox and click enable
        talents.clickCheckbox();
        talents.clickEnable();
        // assert status became enable - green circle
        String status = talents.getStatus();
        assertEquals("ACTIVE", status, "enable button not changed status to ACTIVE");
        // uncheck checkbox
        talents.clickCheckbox();

    }

    // click on archive button
    @Test (enabled = false)
    public void clickArchive(){
        // TODO
    }

    // TODO check all checkboxes

// check all checkboxes through the list one by one
    @Test (priority = 4)
    public void checkboxAll(){
// check all boxes
          List<WebElement> boxes = talents.clickCheckboxes();
            for (int i = 1; i < boxes.size() + 1; i++) {
                JavascriptExecutor executor = (JavascriptExecutor)driver;
                executor.executeScript("arguments[0].click()",
                        driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]/div/label")));

            }
            // asset all boxes checked
               List<WebElement> boxesClasses = talents.getCheckboxClass();
                for (int j = 1; j < boxesClasses.size() + 1; j++){
                    String classes = driver.findElement(
                            By.xpath("//table/tbody/tr[" + j + "]/td[1]/div/label/input")).getAttribute("class");
                    assertTrue(classes.contains("ng-not-empty"), " all checkboxes are not checked ");
                }
                // uncheck all boxes
        List<WebElement> boxesUncheck = talents.clickCheckboxes();
        for (int i = 1; i < boxesUncheck.size() + 1; i++) {
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click()",
                    driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]/div/label")));
        }
        // asset all boxes unchecked
        List<WebElement> boxesClassesUnckecked = talents.getCheckboxClass();
        for (int j = 1; j < boxesClassesUnckecked.size() + 1; j++){
            String classes = driver.findElement(
                    By.xpath("//table/tbody/tr[" + j + "]/td[1]/div/label/input")).getAttribute("class");
            assertTrue(classes.contains("ng-empty"), " all checkboxes are checked ");
        }
    }

// insert name for search and check list contains searching name
    @Test (priority = 5)
    public void search(){
        // insert name to search
        talents.enterSearch("smith");
        // get result list of names and assert all of them contains name to search
        List<WebElement> names = talents.getNames();
        for (WebElement searchName : names){
            String name = searchName.getText();
            assertTrue(name.contains("smith"), "search failed");
        }
    }

// click on new button and assert new tap opened
    @Test  (priority = 6)
    public void getNew(){
        talents.clickNew();

        // assert after clicking "new", user redirect to "Category" page with "New" header
        String newHeader = categoryPage.getHeaderNew();
        assertEquals("New" , newHeader, " no new header displayed ");
        // click on home button - talents page
        menu.clickHome();
        String header = talents.getHeader();
        assertEquals("Talents", header, "user is not back to talents page");
    }
// click on edit button and assert editing tap opened
    @Test (priority = 7)
    public void edit(){
        // copy first row name and click on first row edit icon
        String name1 = talents.getFirstName();
        List<WebElement> edit = talents.clickEdit();
        edit.get(0).click();

        // assert copied name displayed on category page header
        String header = categoryPage.getHeaderName();
        assertEquals(name1, header, " talent name not displayed on category page header");

        // click on home button - talents page
        menu.clickHome();
        String headerTalents = talents.getHeader();
        assertEquals("Talents", headerTalents, "user is not back to talents page");
    }
}
