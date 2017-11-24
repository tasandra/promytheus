package tests;

import data.DataproviderClass;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.List;


import static org.testng.Assert.*;

public class PersonalPageTest extends BaseTest {


    @Test(priority = 1)
    public void login(){
        loginPage.submitLogin("kusiwa@cmail.club", "password");
    }

        // go to personal page
        @Test (dependsOnMethods = "login")
        public void goPersonal(){
        // click edit first row
            List<WebElement> edit = talentsPage.allEdit();
            edit.get(0).click();
            // assert user redirect to category page - category page tap turns blue
            boolean breakIt;
            while (true) {
                breakIt = true;
                try {
                    String active1 = menu.getActiveTabCategory();
                    assertTrue(active1.contains("ng-scope active"), "user not redirect to category page");
                } catch (Exception e) {
                    if (e.getMessage().contains("element is not attached")) {
                        breakIt = false;
                    }
                }
                if (breakIt) {
                    break;
                }
            }

            // click next and assert user redirect ro personal page - personal page tap turns blue
            menu.clickNext();
            boolean breakIt2;
            while (true) {
                breakIt2 = true;
                try {
                    String active2 = menu.getActiveTabPersonal();
                    assertTrue(active2.contains("ng-scope active"), "user not redirect to personal page - class: " + active2);
                } catch (Exception e) {
                    if (e.getMessage().contains("element is not attached")) {
                        breakIt2 = false;
                    }
                }
                if (breakIt2) {
                    break;
                }
            }
        }

        // enter more then 50 char first name and assert errors
        @Test (dependsOnMethods = "goPersonal")
        public void enterLongFirstName(){
            // insert first, last and middle names more then 50 char
            personalPage.insertNames("Lorem ipsum dolor sit amet, consectetuer adipiscin + 1",
                    "Lorem ipsum dolor sit amet, consectetuer adipiscin + 1",
                    "Lorem ipsum dolor sit amet, consectetuer adipiscin +1");

            // get error messages
            String error1 = personalPage.getFirstNameError();
            String error2 = personalPage.getMiddleNameError();
            String error3 = personalPage.getLastNameError();

            // assert errors
            assertEquals("Please enter up to a maximum of 50 characters.", error1,
                    "first name error message not displayed");
            assertEquals("Please enter up to a maximum of 50 characters.", error2 ,
                    "middle name error message not displayed");
            assertEquals("Please enter up to a maximum of 50 characters.", error3,
                    "last name error message not displayed");
        }

//        // upload file with incorrect file type and close popup window
        @Test (dependsOnMethods = "enterLongFirstName")
        public void getIncorrectFileType() throws InterruptedException, AWTException {
            // upload file
            personalPage.uploadImage("C:\\Users\\Alexandra\\Downloads\\SampleVideo_1280x720_10mb.mp4");

            // read error message on popup window and click OK
            String alertHeader = personalPage.getPopUpHeaderAndClick();

            // assert message
            assertEquals("Incorrect file type!", alertHeader,"file upload error popup window is not displayed");
        }

        // enter more then 255 char place of birth and assert error
        @Test (dependsOnMethods = "getIncorrectFileType")
        public void getPlaceBirthError(){
            // insert more then 255 chat in place of birth field
            personalPage.insertDatePlaceBirth("12122222", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. " +
                    "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis " +
                    "dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, " +
                    "pellentesque eu, pretium quis, + 1");

            // get error message
            String error = personalPage.getPlaceBirthError();

            // assert error message
            assertEquals("Please enter up to a maximum of 255 characters.", error,
                    "place of birth error message is not displayed");
        }

//        // enter invalid email and assert error
        @Test (dependsOnMethods = "getPlaceBirthError")
        public void enterInvalidEmail(){
            // insert invalid email
            personalPage.enterEmail("abs@df");
            // get error message
            String error = personalPage.getEmailError();

            // assert error message
            assertEquals("Please enter a valid email address.", error,
                    "invalid email error message is not displayed");
        }

//        // enter invalid phone number and assert error
        @Test ( dependsOnMethods = "enterInvalidEmail")
        public void enterInvalidPhone(){
            // insert invalid phone
            personalPage.enterPhone("111");
            // get error message
            String error = personalPage.getError();

            // assert error message
            assertEquals("Invalid phone.", error ,
                    " invalid phone error message is not displayed");
        }

//        // insert invalid height and assert error
        @Test (dependsOnMethods = "enterInvalidPhone")
        public void insertInvalidHeight(){
            // insert invalid height
            personalPage.insertHeight("1234");
            // get error message
            String error = personalPage.getHeightError();

            // assert error message
            assertEquals("This value should be lower than or equal to 999.", error,
                    "invalid height error message is not displayed");
        }

//        // insert invalid weight and assert error
        @Test (dependsOnMethods = "insertInvalidHeight")
        public void insertInvalidWeight() {
            // insert invalid weight
            personalPage.insertWeight("1234");
            // get error message
            String error = personalPage.getWeightError();

           // assert error message
            assertEquals("This value should be lower than or equal to 999.99.", error,
                    "invalid weight error message is not displayed");

            // click on talents to save personal information
            categoryPage.clickTalents();
        }

        // insert and assert personal information with data provider class
        @Test (groups = "p1", dependsOnMethods = {"goPersonal","insertInvalidWeight"},
                dataProvider="PersonalInfo",dataProviderClass= DataproviderClass.class)
        public void insertInformation(String id,String firstName, String middleName, String lastName, String dOfB, String placeOfB,
                                String country, String address1, String  address2, String city, String state, String zip,
                                String email, String phone, String social, String height, String weight
                                )
                throws InterruptedException, AWTException{

            // array takes data from data provider class
          String[] data = {firstName, middleName, lastName, dOfB, placeOfB, country, address1, address2, city, state,
                   zip, email, phone, social, height, weight};

            // click edit depends on id from data provider class
            List<WebElement> edit = talentsPage.allEdit();
            edit.get(Integer.parseInt(id)).click();

            // click on personal
            menu.clickPersonal();
            // insert first, middle and last name
            personalPage.insertNames(firstName, middleName, lastName);
            // upload image
            personalPage.uploadImage("C:\\Users\\Alexandra\\Desktop\\checks\\china.jpg");
            // insert date and place of birth
            personalPage.insertDatePlaceBirth(dOfB,placeOfB);
            // insert county
            personalPage.selectCountry(country);
            // insert address
            personalPage.enterAddress(address1,address2,city, state, zip);
            // select rural radio button
            String rural = personalPage.selectRural();
            assertTrue(rural.contains("ng-valid-parse"), "rural radio button not selected");
            // select urban radio button
            String urban = personalPage.selectUrban();
            assertTrue(urban.contains("ng-valid-parse"), "urban radio button not selected");
            // select privileged
            String privileged = personalPage.selectPrivileged();
            assertTrue(privileged.contains("ng-valid-parse"), "privileged not selected");
            // select Underprivileged
            String underprivileged = personalPage.selectUnderprivileged();
            assertTrue(underprivileged.contains("ng-valid-parse"), "Underprivileged not selected");
            // insert email
            personalPage.enterEmail(email);
            // insert phone
            personalPage.enterPhone(phone);
            // click on add social and select
            personalPage.clickAddSocial();
            personalPage.selectSocial(social);
            // insert height
            personalPage.insertHeight(height);
            // insert weight
            personalPage.insertWeight(weight);

            // click next to save information
            menu.clickNext();

            // assert user redirect
            boolean breakIt;
            while (true) {
                breakIt = true;
                try {
                    String active = menu.getActiveTabTalentTraits();
                    assertTrue(active.contains("active"), "user not redirect to personal page - class: " + active);
                } catch (Exception e) {
                    if (e.getMessage().contains("element is not attached")) {
                        breakIt = false;
                    }
                }
                if (breakIt) {
                    break;
                }
            }

            // back to talents page
            categoryPage.clickTalents();

    // assert saved info after logout and login again

        // array takes data from personal page - saved user information
        String[] personalInfo = new String[16];

// click edit and assert user redirect to the category page
        List<WebElement> edit2 = talentsPage.allEdit();
        edit2.get(Integer.parseInt(id)).click();
        String active2 = menu.getActiveTabCategory();
        assertEquals("ng-scope active", active2, "category page tap not active");

// click personal and assert user redirect to the personal page
        menu.clickPersonal();
        String active3 = menu.getActiveTabPersonal();
        assertEquals("ng-scope active", active3, "personal page tap not active");

// get all information
        personalInfo[0] = personalPage.getFirstName();
        personalInfo[1] = personalPage.getMiddleName();
        personalInfo[2] = personalPage.getLastName();
        personalInfo[3] = personalPage.getDateOfB();
        personalInfo[4] = personalPage.getPlaceOfB();
        personalInfo[5] = personalPage.getCountryValue();
        personalInfo[6] = personalPage.getAddress1();
        personalInfo[7] = personalPage.getAddress2();
        personalInfo[8] = personalPage.getCity();
        personalInfo[9] = personalPage.getState();
        personalInfo[10] = personalPage.getZip();
        personalInfo[11] = personalPage.getEmail();
        personalInfo[12] = personalPage.getPhone();
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

        // back to talents page
            categoryPage.clickTalents();
    }
}