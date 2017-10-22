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
        talents.selectRowsOnPage("10");
        int numberOfRows = talents.getNumberOfRow();
        assertEquals(10, numberOfRows);
    }
    // click disable button
    @Test (priority = 2)
    public void disableTalent(){
        talents.clickCheckbox();
        talents.clickDisable();
        String status = talents.getStatus();
        assertEquals("INACTIVE", status);

        talents.clickCheckbox();
    }

    // click enable button
    @Test (priority = 3)
    public void enableTalent(){
        talents.clickCheckbox();
        talents.clickEnable();
        String status = talents.getStatus();
        assertEquals("ACTIVE", status);

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
                    assertTrue(classes.contains("ng-not-empty"));
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
            assertTrue(classes.contains("ng-empty"));
        }
    }

// insert name for search and check list contains searching name
    @Test (priority = 5)
    public void search(){
        talents.enterSearch("smith");

        List<WebElement> names = talents.getNames();
        for (WebElement searchName : names){
            String name = searchName.getText();
            assertTrue(name.contains("smith"));
        }
    }

// click on new button and assert new tap opened
    @Test  (priority = 6)
    public void getNew(){
        talents.clickNew();
        String newHeader = categoryPage.getHeaderNew();

        assertEquals("New" , newHeader);
        menu.clickHome();
    }
// click on edit button and assert editing tap opened
    @Test (priority = 7)
    public void edit(){
        String name1 = talents.getFirstName();
        List<WebElement> edit = talents.clickEdit();
        edit.get(0).click();
        String header = categoryPage.getHeaderName();

        assertEquals(name1, header);
        menu.clickHome();
    }
}
