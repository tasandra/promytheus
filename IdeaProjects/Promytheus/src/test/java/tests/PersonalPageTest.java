package tests;

import menus.TalentMenu;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.PersonalPage;
import pages.TalentsPage;

import java.awt.*;

import static org.testng.Assert.*;

public class PersonalPageTest extends ValidLoginTest {
    private PersonalPage personal;
    private TalentsPage talents;
    private TalentMenu menu;
    private CategoryPage category;


    @BeforeClass
    public void beforeClass() {
        personal = new PersonalPage(driver);
        talents = new TalentsPage(driver);
        menu = new TalentMenu(driver);
        category = new CategoryPage(driver);
    }

    // insert names
    @Test (priority = 1)
    public void insertNames(){
        talents.clickEdit();
        String active1 = category.getActiveTab();
        assertEquals("ng-scope active", active1);

        String header = category.getHeader();
        assertTrue(header.contains("Talent Strength"));

        menu.clickPersonal();
        String active2 = personal.getActiveTab();

        assertEquals("ng-scope active", active2);
//        personal.insertNames("Bill", "Jn", "Forward");
    }
//
//    // upload image
//    @Test (priority = 2)
//    public void uploadImage() throws InterruptedException, AWTException {
//        personal.uploadImage("C:\\Users\\Alexandra\\Desktop\\checks\\china.jpg");
//
//    }

//    // insert date and place of birth
//    @Test (priority = 2)
//    public void insertDatePlaceOfBirth(){
//        personal.inserDatePlaceBirth("12122012","Moscow");
//    }

    // select country
    @Test (priority = 2)
    public void selectCountry(){
        personal.selectCountry("Kazakhstan");
        menu.clickSocialBackground();
        menu.clickFinish();
    }

}