package tests;

import menus.HomeMenu;
import menus.TalentMenu;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.TalentTraitsPage;
import pages.TalentsPage;

import java.util.Iterator;

import java.util.List;

import static org.testng.Assert.*;

public class TalentTraitsPageTest extends ValidLoginTest {
    private TalentsPage talents;
    private TalentMenu menu;
    private CategoryPage category;
    private HomeMenu home;
    private TalentTraitsPage talentTraits;

    @BeforeMethod
    public void beforeClass() throws Exception {
        talents = new TalentsPage(driver);
        menu = new TalentMenu(driver);
        home = new HomeMenu(driver);
        category = new CategoryPage(driver);
        talentTraits = new TalentTraitsPage(driver);

    }
// go to talent traits page
    @Test (priority = 1)
    public void goTalentTraits(){
        List<WebElement> edit = talents.clickEdit();
        edit.get(0).click();
        String active = menu.getActiveTabCategory();
        assertEquals("ng-scope active", active);

        String header = category.getHeader();
        assertTrue(header.contains("Talent Strength"), " Wrong page");

        menu.clickTalentTraits();
    }

// assert errors - all fields empty
    @Test (priority = 2)
    public void getEmptyErrors(){
        menu.clickNext();
        List<WebElement> errors = talentTraits.getTraitsErrors();
        Iterator<WebElement> iterator = errors.iterator();
        while(iterator.hasNext()) {
            String error = iterator.next().getText();
            assertEquals("This field is required.",error);
        }
    }

    // assert errors - scale more than 10
    @Test (priority = 3)
    public void insert11(){
        driver.navigate().refresh();
        talentTraits.insetScales("11");

        List<WebElement> errors = talentTraits.getTraitsErrors();
        Iterator<WebElement> itr = errors.iterator();
        while(itr.hasNext()) {
            String error = itr.next().getText();
            assertEquals("This field is required.",error);
        }
    }

    // assert errors - scale more less than 1
    @Test (priority = 4)
    public void insert0(){
        driver.navigate().refresh();
        talentTraits.insetScales("0");

        List<WebElement> errors = talentTraits.getTraitsErrors();
        Iterator<WebElement> itr = errors.iterator();
        while(itr.hasNext()) {
            String error = itr.next().getText();
            assertEquals("This field is required.",error);
        }
    }

    // insert valid scales
    @Test (priority = 5)
    public void insertScales(){
        driver.navigate().refresh();
        int[] numbers = new int[20];

        //Generates 10 Random Numbers in the range 1 - 10
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = (int)(Math.random() * 10 + 1);
        }
//        System.out.print(Arrays.toString(numbers));
        List<WebElement> rows = talentTraits.getTraitsRowsInput();
        Iterator<WebElement> itr = rows.iterator();
        int i = 0;
        while(itr.hasNext()) {
            itr.next().sendKeys(Integer.toString(numbers[i]));
            i++;
        }
        menu.clickNext();

        home.clickUserIcon();
        home.logout();
        validLogin();
    }

}