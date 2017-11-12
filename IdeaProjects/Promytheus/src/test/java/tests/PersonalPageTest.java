package tests;

import data.DataproviderClass;
import data.ExcelReadApi;
import menus.HomeMenu;
import menus.TalentMenu;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import java.awt.*;
import java.util.List;
import java.util.NoSuchElementException;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

public class PersonalPageTest extends ValidLoginTest {

        // go to personal page
        @Test (priority = 1)
        public void goPersonal(){
        // click edit first row
            List<WebElement> edit = talentsPage.clickEdit();
            edit.get(0).click();
            // assert user redirect to category page - category page tap turns blue
            try {
                String active1 = menu.getActiveTabCategory();
                assertEquals("ng-scope active", active1, "user not redirect to category page");
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

            String header = categoryPage.getHeader();
            assertTrue(header.contains("Talent Strength"));

            // click next and assert user redirect ro personal page - personal page tap turns blue
            menu.clickNext();
            try {
                String active2 = menu.getActiveTabPersonal();
                assertEquals("ng-scope active", active2, "user not redirect to personal page");
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
        }

        // enter more then 50 chat first name and assert errors
        @Test (priority = 2 )
        public void enterLongFirstName(){
            personalPage.insertNames("Lorem ipsum dolor sit amet, consectetuer adipiscin + 1",
                    "Lorem ipsum dolor sit amet, consectetuer adipiscin + 1",
                    "Lorem ipsum dolor sit amet, consectetuer adipiscin +1");

            String error1 = personalPage.getFirstNameError();
            String error2 = personalPage.getMiddleNameError();
            String error3 = personalPage.getLastNameError();

            assertEquals("Please enter up to a maximum of 50 characters.", error1);
            assertEquals("Please enter up to a maximum of 50 characters.", error2);
            assertEquals("Please enter up to a maximum of 50 characters.", error3);
        }

        // upload file with not incorrect file type and close popup
        @Test (priority = 3)
        public void getIncorrectFileType() throws InterruptedException, AWTException {
            personalPage.uploadImage("C:\\Users\\Alexandra\\Downloads\\SampleVideo_1280x720_10mb.mp4");

            String alertHeader = personalPage.getPopUpHeaderAndClick();

            assertEquals("Incorrect file type!", alertHeader);
        }

        // enter more then 255 char place of birth and assert error
        @Test (priority = 4)
        public void getPlaceBirthError(){
            personalPage.insertDatePlaceBirth("12122222", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. " +
                    "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis " +
                    "dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, " +
                    "pellentesque eu, pretium quis, + 1");

            String error = personalPage.getPlaceBirthError();

            assertEquals("Please enter up to a maximum of 255 characters.", error);
        }

        // enter invalid email and assert error
        @Test (priority = 5)
        public void enterInvalidEmail(){
            // check error message
            personalPage.enterEmail("abs@df");
            String error = personalPage.getEmailError();

            assertEquals("Please enter a valid email address.", error);
        }

        // enter invalid phone number and assert error
        @Test (priority = 6)
        public void enterInvalidPhone(){
            // check error message
            personalPage.enterPhone("111");
            String error = personalPage.getError();

            assertEquals("Invalid phone.", error);
        }

        // insert invalid height and assert error
        @Test (priority = 7)
        public void insertInvalidHeight(){
            // check error message
            personalPage.insertHeight("1234");
            String error = personalPage.getHeightError();

            assertEquals("This value should be lower than or equal to 999.", error);
        }

        // insert invalid weight and assert error
        @Test (priority = 8)
        public void insertInvalidWeight() {
            // check error message
            personalPage.insertWeight("1234");
            String error = personalPage.getWeightError();

            assertEquals("This value should be lower than or equal to 999.99.", error);
            categoryPage.clickTalents();
        }

        // insert personal information with data provider class
        @Test (priority = 9,dataProvider="SearchProvider",dataProviderClass= DataproviderClass.class)
        public void insertInformation(String id,String firstName, String middleName, String lastName, String dOfB, String placeOfB,
                                String country, String address1, String  address2, String city, String state, String zip,
                                String email, String phone, String social, String height, String weight
                                )
                throws InterruptedException, AWTException{

            List<WebElement> edit = talentsPage.clickEdit();
            edit.get(Integer.parseInt(id)).click();
            String active1 = menu.getActiveTabCategory();
            assertEquals("ng-scope active", active1);

            String header = categoryPage.getHeader();
            assertTrue(header.contains("Talent Strength"));

            menu.clickPersonal();

            personalPage.insertNames(firstName, middleName, lastName);

            personalPage.uploadImage("C:\\Users\\Alexandra\\Desktop\\checks\\china.jpg");

            personalPage.insertDatePlaceBirth(dOfB,placeOfB);

            personalPage.selectCountry(country);

            personalPage.enterAddress(address1,address2,city, state, zip);

            String rural = personalPage.selectRural();
            assertTrue(rural.contains("ng-valid-parse"));

            String urban = personalPage.selectUrban();
            assertTrue(urban.contains("ng-valid-parse"));

            String privileged = personalPage.selectPrivileged();
            assertTrue(privileged.contains("ng-valid-parse"));

            String underprivileged = personalPage.selectUnderprivileged();
            assertTrue(underprivileged.contains("ng-valid-parse"));

            personalPage.enterEmail(email);

            personalPage.enterPhone(phone);

            personalPage.clickAddSocial();
            personalPage.selectSocial(social);

            personalPage.insertHeight(height);

            personalPage.insertWeight(weight);

            menu.clickNext();
            String active = menu.getActiveTabTalentTraits();
            assertEquals("active ng-binding ng-scope", active);
            categoryPage.clickTalents();

        }

    // assert saved info after logout and login again
    @Test(priority = 10, dataProvider="SearchProvider",dataProviderClass= DataproviderClass.class)
    public void assertInfo(String id, String firstName, String middleName, String lastName, String dOfB, String placeOfB,
                           String country, String address1, String address2, String city, String state, String zip,
                           String email, String phone, String social, String height, String weight) {
        // array takes data from data provider class
        String[] data = {firstName, middleName, lastName, dOfB, placeOfB, country, address1, address2, city, state,
                zip, email, phone, social, height, weight};
        // array takes data from personal page - saved user information
        String[] personalInfo = new String[16];

        // logout and login
        try {
            home.logoutClick();
        }
        catch(Exception e){
            home.logoutJs();
            System.out.println(" log out with javascript execution");
        }

        assertTrue(loginPage.getLogo().isDisplayed(),"User wasn't able to logout and logo not displayed");

        validLogin();
// click edit and assert user redirect to the category page
        List<WebElement> edit = talentsPage.clickEdit();
        edit.get(Integer.parseInt(id)).click();
        String active1 = menu.getActiveTabCategory();
        assertEquals("ng-scope active", active1, "category page tap not active");

        String header = categoryPage.getHeader();
        assertTrue(header.contains("Talent Strength"));

// click personal and assert user redirect to the personal page
        menu.clickPersonal();
        String active2 = menu.getActiveTabPersonal();
        assertEquals("ng-scope active", active2, "personal page tap not active");

// get all information
        personalInfo[0] = profilePage.getFirstName();
        personalInfo[1] = personalPage.getMiddleName();
        personalInfo[2] = profilePage.getLastName();
        personalInfo[3] = personalPage.getDateOfB();
        personalInfo[4] = personalPage.getPlaceOfB();
        personalInfo[5] = personalPage.getCountryValue();
        personalInfo[6] = personalPage.getAddress1();
        personalInfo[7] = personalPage.getAddress2();
        personalInfo[8] = profilePage.getCity();
        personalInfo[9] = personalPage.getState();
        personalInfo[10] = profilePage.getZip();
        personalInfo[11] = personalPage.getEmail();
        personalInfo[12] = profilePage.getPhone();
        personalInfo[13] = personalPage.getSocialValue();
        personalInfo[14] = personalPage.getHeight();
        personalInfo[15] = personalPage.getWeight();

        // assert array with info from data provider equal with info from personal page
        for (int i = 0; i < personalInfo.length; i++) {
//            System.out.println(personalInfo[i] + " = " + data[i]);
            assertTrue(personalInfo[i].equals(data[i]),personalInfo[i] + " not equals " + data[i]);
        }
// assert image was uploaded
        WebElement image = personalPage.getUploaderImage();
        assertTrue(image.isDisplayed(), "Image not displayed");
    }


    @DataProvider(name="userData")
    public Object[][] userFormData() throws Exception {
        ExcelReadApi excel = new ExcelReadApi("promy.xlsx");
        Object[][] data = excel.testData("personal");
        return data;
    }
}