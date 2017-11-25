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


// select number of rows per page and checked list size
    @Test  (groups = "p1", dependsOnMethods = "validLogin")
    public void numberOfRows(){
        talentsPage.selectRowsOnPage("25");
        int numberOfRows25 = talentsPage.getNumberOfRow();
        assertEquals(25, numberOfRows25, "wrong number of rows per page");

        // select 10 rows per page
        talentsPage.selectRowsOnPage("10");
        int numberOfRows10 = talentsPage.getNumberOfRow();
        assertEquals(10, numberOfRows10, "wrong number of rows per page");
    }
    // click disable button
    @Test (groups = "p1", dependsOnMethods = "numberOfRows")
    public void disableTalent(){
        // click checkbox and click disable
        talentsPage.clickCheckbox();
        talentsPage.clickDisable();
        // assert status became disable - red circle
        String status = talentsPage.getStatus();
        assertEquals("INACTIVE", status, "disable button not changed status to INACTIVE");
        // uncheck checkbox
        talentsPage.clickCheckbox();
    }

    // click enable button
    @Test (groups = "p1", dependsOnMethods = "disableTalent")
    public void enableTalent(){
        // click checkbox and click enable
        talentsPage.clickCheckbox();
        talentsPage.clickEnable();
        // assert status became enable - green circle
        String status = talentsPage.getStatus();
        assertEquals("ACTIVE", status, "enable button not changed status to ACTIVE");
        // uncheck checkbox
        talentsPage.clickCheckbox();

    }

    // click on archive button
    @Test (enabled = false)
    public void clickArchive(){
        // TODO
    }

    // TODO check all checkboxes

// check all checkboxes through the list one by one
    @Test (groups = "p1", dependsOnMethods = "enableTalent")
    public void checkboxAll(){
// check all boxes
          List<WebElement> boxes = talentsPage.clickCheckboxes();
            for (int i = 1; i < boxes.size() + 1; i++) {
                JavascriptExecutor executor = (JavascriptExecutor)driver;
                executor.executeScript("arguments[0].click()",
                        driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]/div/label")));

            }
            // asset all boxes checked
               List<WebElement> boxesClasses = talentsPage.getCheckboxClass();
                for (int j = 1; j < boxesClasses.size() + 1; j++){
                    String classes = driver.findElement(
                            By.xpath("//table/tbody/tr[" + j + "]/td[1]/div/label/input")).getAttribute("class");
                    assertTrue(classes.contains("ng-not-empty"), " all checkboxes are not checked ");
                }
                // uncheck all boxes
        List<WebElement> boxesUncheck = talentsPage.clickCheckboxes();
        for (int i = 1; i < boxesUncheck.size() + 1; i++) {
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click()",
                    driver.findElement(By.xpath("//table/tbody/tr[" + i + "]/td[1]/div/label")));
        }
        // asset all boxes unchecked
        List<WebElement> boxesClassesUnckecked = talentsPage.getCheckboxClass();
        for (int j = 1; j < boxesClassesUnckecked.size() + 1; j++){
            String classes = driver.findElement(
                    By.xpath("//table/tbody/tr[" + j + "]/td[1]/div/label/input")).getAttribute("class");
            assertTrue(classes.contains("ng-empty"), " all checkboxes are checked ");
        }
    }

// insert name for search and check list contains searching name
    @Test (groups = "p1", dependsOnMethods = "checkboxAll")
    public void search(){
        // insert name to search
        talentsPage.enterSearch("smith");
        // get result list of names and assert all of them contains name to search
        List<WebElement> names = talentsPage.getNames();
        for (WebElement searchName : names){
            String name = searchName.getText();
            assertTrue(name.contains("smith"), "search failed");
        }
    }

// click on new button and assert new tap opened
    @Test  ( dependsOnMethods = "search")
    public void getNew(){
        talentsPage.clickNew();

        // assert after clicking "new", user redirect to "Category" page with "New" header
        String newHeader = categoryPage.getHeaderNew();
        assertEquals("New" , newHeader, " no new header displayed ");
        // click on home button - talents page
        home.clickHome();
        String header = talentsPage.getHeader();
        assertEquals("Talents", header, "user is not back to talents page");
    }
// click on edit button and assert editing tap opened
    @Test ( dependsOnMethods = "getNew")
    public void edit(){
        // copy first row name and click on first row edit icon
        String name1 = talentsPage.getFirstName();
        List<WebElement> edit = talentsPage.allEdit();
        edit.get(0).click();

        // assert copied name displayed on category page header
        String header = categoryPage.getHeaderName();
        assertEquals(name1, header, " talent name not displayed on category page header");

        // click on home button - talents page
        home.clickHome();
        String headerTalents = talentsPage.getHeader();
        assertEquals("Talents", headerTalents, "user is not back to talents page");
    }
}
