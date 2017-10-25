package tests;

import data.DataproviderClass;
import menus.HomeMenu;
import menus.TalentMenu;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class PersonalPageTest extends ValidLoginTest {
    private LoginPage loginPage;
    private PersonalPage personal;
    private TalentsPage talents;
    private TalentMenu menu;
    private CategoryPage category;
    private HomeMenu home;

    @BeforeClass
    public void beforeClass() {
        loginPage = new LoginPage(driver);
        personal = new PersonalPage(driver);
        talents = new TalentsPage(driver);
        menu = new TalentMenu(driver);
        home = new HomeMenu(driver);
        category = new CategoryPage(driver);

    }

        // go to personal page
        @Test (priority = 1)
        public void goPersonal(){
            List<WebElement> edit = talents.clickEdit();
            edit.get(0).click();
            String active1 = menu.getActiveTabCategory();
            assertEquals("ng-scope active", active1);

            String header = category.getHeader();
            assertTrue(header.contains("Talent Strength"));

            menu.clickNext();
        }

        // enter more then 50 chat first name and assert errors
        @Test (priority = 2 )
        public void enterLongFirstName(){
            personal.insertNames("Lorem ipsum dolor sit amet, consectetuer adipiscin + 1",
                    "Lorem ipsum dolor sit amet, consectetuer adipiscin + 1",
                    "Lorem ipsum dolor sit amet, consectetuer adipiscin +1");

            String error1 = personal.getFirstNameError();
            String error2 = personal.getMiddleNameError();
            String error3 = personal.getLastNameError();

            assertEquals("Please enter up to a maximum of 50 characters.", error1);
            assertEquals("Please enter up to a maximum of 50 characters.", error2);
            assertEquals("Please enter up to a maximum of 50 characters.", error3);
        }

        // upload file with not incorrect file type and close popup
        @Test (priority = 3)
        public void getIncorrectFileType() throws InterruptedException, AWTException {
            personal.uploadImage("C:\\Users\\Alexandra\\Downloads\\SampleVideo_1280x720_10mb.mp4");

            String alertHeader = personal.getPopUpHeader();

            assertEquals("Incorrect file type!", alertHeader);
        }

        // enter more then 255 char place of birth and assert error
        @Test (priority = 4)
        public void getPlaceBirthError(){
            personal.insertDatePlaceBirth("12122222", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. " +
                    "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis " +
                    "dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, " +
                    "pellentesque eu, pretium quis, + 1");

            String error = personal.getPlaceBirthError();

            assertEquals("Please enter up to a maximum of 255 characters.", error);
        }

        // enter invalid email and assert error
        @Test (priority = 5)
        public void enterInvalidEmail(){
            // check error message
            personal.enterEmail("abs@df");
            String error = personal.getEmailError();

            assertEquals("Please enter a valid email address.", error);
        }

        // enter invalid phone number and assert error
        @Test (priority = 6)
        public void enterInvalidPhone(){
            // check error message
            personal.enterPhone("111");
            String error = personal.getError();

            assertEquals("Invalid phone.", error);
        }

        // insert invalid height and assert error
        @Test (priority = 7)
        public void insertInvalidHeight(){
            // check error message
            personal.insertHeight("1234");
            String error = personal.getHeightError();

            assertEquals("This value should be lower than or equal to 999.", error);
        }

        // insert invalid weight and assert error
        @Test (priority = 8)
        public void insertInvalidWeight() {
            // check error message
            personal.insertWeight("1234");
            String error = personal.getWeightError();

            assertEquals("This value should be lower than or equal to 999.99.", error);
            category.clickTalents();
        }

        // insert personal information with data provider class
        @Test (priority = 9,dataProvider="SearchProvider",dataProviderClass= DataproviderClass.class)
        public void insertInformation(String id,String firstName, String middleName, String lastName, String dOfB, String placeOfB,
                                String country, String address1, String  address2, String city, String state, String zip,
                                String email, String phone, String social, String height, String weight
                                )
                throws InterruptedException, AWTException{

            List<WebElement> edit = talents.clickEdit();
            edit.get(Integer.parseInt(id)).click();
            String active1 = menu.getActiveTabCategory();
            assertEquals("ng-scope active", active1);

            String header = category.getHeader();
            assertTrue(header.contains("Talent Strength"));

            menu.clickPersonal();

            personal.insertNames(firstName, middleName, lastName);

            personal.uploadImage("C:\\Users\\Alexandra\\Desktop\\checks\\china.jpg");

            personal.insertDatePlaceBirth(dOfB,placeOfB);

            personal.selectCountry(country);

            personal.enterAddress(address1,address2,city, state, zip);

            String rural = personal.selectRural();
            assertTrue(rural.contains("ng-valid-parse"));

            String urban = personal.selectUrban();
            assertTrue(urban.contains("ng-valid-parse"));

            String privileged = personal.selectPrivileged();
            assertTrue(privileged.contains("ng-valid-parse"));

            String underprivileged = personal.selectUnderprivileged();
            assertTrue(underprivileged.contains("ng-valid-parse"));

            personal.enterEmail(email);

            personal.enterPhone(phone);

            personal.clickAddSocial();
            personal.selectSocial(social);

            personal.insertHeight(height);

            personal.insertWeight(weight);

            menu.clickNext();
            String active = menu.getActiveTabTalentTraits();
            assertEquals("active ng-binding ng-scope", active);
            category.clickTalents();

        }

    // assert saved info after logout and login again
    @Test(priority = 10, dataProvider = "SearchProvider", dataProviderClass = DataproviderClass.class)
    public void assertInfo(String id, String firstName, String middleName, String lastName, String dOfB, String placeOfB,
                           String country, String address1, String address2, String city, String state, String zip,
                           String email, String phone, String social, String height, String weight) {

        String[] data = {firstName, middleName, lastName, dOfB, placeOfB, country, address1, address2, city, state,
                zip, email, phone, social, height, weight};
        String[] personalInfo = new String[16];

        // logout and login
        home.clickUserIcon();
        home.logout();
        validLogin();

        List<WebElement> edit = talents.clickEdit();
        edit.get(Integer.parseInt(id)).click();
        String active1 = menu.getActiveTabCategory();
        assertEquals("ng-scope active", active1);

        String header = category.getHeader();
        assertTrue(header.contains("Talent Strength"));

        menu.clickPersonal();
// get all information
        personalInfo[0] = personal.getFirstName();
        personalInfo[1] = personal.getMiddleName();
        personalInfo[2] = personal.getLastName();
        personalInfo[3] = personal.getDateOfB();
        personalInfo[4] = personal.getPlaceOfB();
        personalInfo[5] = personal.getCountryValue();
        personalInfo[6] = personal.getAddress1();
        personalInfo[7] = personal.getAddress2();
        personalInfo[8] = personal.getCity();
        personalInfo[9] = personal.getState();
        personalInfo[10] = personal.getZip();
        personalInfo[11] = personal.getEmail();
        personalInfo[12] = personal.getPhone();
        personalInfo[13] = personal.getSocialValue();
        personalInfo[14] = personal.getHeight();
        personalInfo[15] = personal.getWeight();

        for (int i = 0; i < personalInfo.length; i++) {
//            System.out.println(personalInfo[i] + " = " + data[i]);
            assertTrue(personalInfo[i].equals(data[i]),personalInfo[i] + " not equals " + data[i]);
        }
    }
}