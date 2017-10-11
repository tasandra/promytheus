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

public class TalentsTest extends ValidLoginTest{
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
    @Test (priority = 0)
    public void clickArchive(){
        // TODO
    }
// check all checkboxes through the list
    @Test (priority = 4)
    public void checkboxAll(){

          List<WebElement> boxes = talents.clickCheckboxes();
            for (int i = 1; i < boxes.size(); i++) {
                JavascriptExecutor executor = (JavascriptExecutor)driver;
                executor.executeScript("arguments[0].click()",
                        driver.findElement(By.xpath("//table/tbody/tr[\" + i + \"]/td[1]/div/label")));
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
        talents.clickEdit();
        String header = categoryPage.getHeaderName();

        assertEquals(name1, header);
        menu.clickHome();
    }
}
