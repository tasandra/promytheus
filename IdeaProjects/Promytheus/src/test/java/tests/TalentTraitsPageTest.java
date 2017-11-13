package tests;

import menus.HomeMenu;
import menus.TalentMenu;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.TalentTraitsPage;
import pages.TalentsPage;

import java.util.Iterator;

import java.util.List;

import static org.testng.Assert.*;

public class TalentTraitsPageTest extends ValidLoginTest {

// go to talent traits page
    @Test (priority = 1)
    public void goTalentTraits(){
        // click edit and assert user redirect to the category page
        List<WebElement> edit = talentsPage.clickEdit();
        edit.get(0).click();
        String active1 = menu.getActiveTabCategory();
        assertEquals("ng-scope active", active1);

        String header = categoryPage.getHeader();
        assertTrue(header.contains("Talent Strength"));

        // click on talent traits and assert user redirect to the talents traits page
        menu.clickTalentTraits();
        String active2 = menu.getActiveTabTalentTraits();
        assertEquals("ng-scope active", active2);
    }

// assert errors - all fields empty
    @Test (priority = 2)
    public void getEmptyErrors(){
        // all fields empty and click next
        menu.clickNext();
        // get all error messages
        List<WebElement> errors = talentTraitsPage.getTraitsErrors();
        Iterator<WebElement> iterator = errors.iterator();
        while(iterator.hasNext()) {
            String error = iterator.next().getText();
            assertEquals("This field is required.",error);
        }
    }

    // assert errors - scale more than 10
    @Test (priority = 3)
    public void insert11(){
        // refresh browser and insert scales more than 10 in each fields
        driver.navigate().refresh();
        talentTraitsPage.insetScales("11");
        // assert error messages
        List<WebElement> errors = talentTraitsPage.getTraitsErrors();
        Iterator<WebElement> itr = errors.iterator();
        while(itr.hasNext()) {
            String error = itr.next().getText();
            assertEquals("This field is required.",error);
        }
    }

    // assert errors - scale more less than 1
    @Test (priority = 4)
    public void insert0(){
        // refresh browser and insert scales less than 1 in each fields
        driver.navigate().refresh();
        talentTraitsPage.insetScales("0");
        // assert error messages
        List<WebElement> errors = talentTraitsPage.getTraitsErrors();
        Iterator<WebElement> itr = errors.iterator();
        while(itr.hasNext()) {
            String error = itr.next().getText();
            assertEquals("This field is required.",error);
        }
    }

    // insert valid scales
    @Test (priority = 5)
    public void insertScales(){
        // refresh browser and insert scales in range  1 - 10 for each fields
        driver.navigate().refresh();
        int[] numbers = new int[20];

        //Generates 10 Random Numbers in the range 1 - 10
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = (int)(Math.random() * 10 + 1);
        }
//        System.out.print(Arrays.toString(numbers));

        // insert random numbers to scales
        List<WebElement> rows = talentTraitsPage.getTraitsRowsInput();
        Iterator<WebElement> itr = rows.iterator();
        int i = 0;
        while(itr.hasNext()) {
            itr.next().sendKeys(Integer.toString(numbers[i]));
            i++;
        }
        // click next to save scales and assert user redirect to next page
        menu.clickNext();
        String active3 = menu.getActiveTabPersonalityTraits();
        assertEquals("ng-scope active", active3);
    }

}