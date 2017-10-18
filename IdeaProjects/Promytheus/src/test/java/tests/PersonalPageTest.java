package tests;

import menus.TalentMenu;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.DataproviderClass;
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

    // go to personal page
    @Test (priority = 1)
    public void goPersonal(){
        talents.clickEdit();
        String active1 = menu.getActiveTabCategory();
        assertEquals("ng-scope active", active1);

        String header = category.getHeader();
        assertTrue(header.contains("Talent Strength"));

//        menu.clickPersonal();
        menu.clickNext();
//        String active2 = menu.getActiveTabPersonal();

//        assertEquals("ng-scope active", active2);
    }

    // insert personal information
    @Test (priority = 9,dataProvider="SearchProvider",dataProviderClass= DataproviderClass.class)
    public void insertInformation(String firstName, String middleName, String lastName, String dOfB, String placeOfB,
                            String country, String address1, String  address2, String city, String state, String zip,
                            String email, String phone, String social, String height, String weight
                            )
            throws InterruptedException, AWTException{

        String[] personalInfo = new String[16];

        personal.insertNames(firstName, middleName, lastName);

        personalInfo[0] = personal.getFirstName();
        personalInfo[1] = personal.getMiddleName();
        personalInfo[2] = personal.getLastName();

        personal.uploadImage("C:\\Users\\Alexandra\\Desktop\\checks\\china.jpg");

        personal.insertDatePlaceBirth(dOfB,placeOfB);

        personalInfo[3] = personal.getDateOfB();
        personalInfo[4] = personal.getPlaceOfB();

        personal.selectCountry(country);

        personalInfo[5] = personal.getCountryValue();

        personal.enterAddress(address1,address2,city, state, zip);

        personalInfo[6] = personal.getAddress1();
        personalInfo[7] = personal.getAddress2();
        personalInfo[8] = personal.getCity();
        personalInfo[9] = personal.getState();
        personalInfo[10] = personal.getZip();

        String rural = personal.selectRural();
        assertTrue(rural.contains("ng-valid-parse"));

        String urban = personal.selectUrban();
        assertTrue(urban.contains("ng-valid-parse"));

        String privileged = personal.selectPrivileged();
        assertTrue(privileged.contains("ng-valid-parse"));

        String underprivileged = personal.selectUnderprivileged();
        assertTrue(underprivileged.contains("ng-valid-parse"));

        personal.enterEmail(email);

        personalInfo[11] = personal.getEmail();

        personal.enterPhone(phone);

        personalInfo[12] = personal.getPhone();

        personal.clickAddSocial();
        personal.selectSocial(social);

        personalInfo[13] = personal.getSocialValue();

        personal.insertHeight(height);

        personalInfo[14] = personal.getHight();

        personal.insertWeight(weight);

        personalInfo[15] = personal.getWeight();

        for (int i = 0; i < personalInfo.length; i++){
            System.out.println(personalInfo[i]);
        }


    }


    // enter more then 50 chat first name
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

    // upload file with not incorrect file type
    @Test (priority = 3)
    public void getIncorrectFileType() throws InterruptedException, AWTException {
        personal.uploadImage("C:\\Users\\Alexandra\\Downloads\\SampleVideo_1280x720_10mb.mp4");

        String alertHeader = personal.getPopUpHeader();

        assertEquals("Incorrect file type!", alertHeader);
    }

    // enter more then 255 char place of birth
    @Test (priority = 4)
    public void getPlaceBirthError(){
        personal.insertDatePlaceBirth("12122222", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. " +
                "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis " +
                "dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, " +
                "pellentesque eu, pretium quis, + 1");

        String error = personal.getPlaceBirthError();

        assertEquals("Please enter up to a maximum of 255 characters.", error);
    }

    // enter invalid email
    @Test (priority = 5)
    public void enterInvalidEmail(){
        // check error message
        personal.enterEmail("abs@df");
        String error = personal.getEmailError();

        assertEquals("Please enter a valid email address.", error);
    }

    // enter invalid phone number
    @Test (priority = 6)
    public void enterInvalidPhone(){
        // check error message
        personal.enterPhone("111");
        String error = personal.getError();

        assertEquals("Invalid phone.", error);
    }

    // insert invalid height
    @Test (priority = 7)
    public void insertInvalidHeight(){
        // check error message
        personal.insertHeight("1234");
        String error = personal.getHeightError();

        assertEquals("This value should be lower than or equal to 999.", error);
    }

    // insert invalid weight
    @Test (priority = 8)
    public void insertInvalidWeight() {
        // check error message
        personal.insertWeight("1234");
        String error = personal.getWeightError();

        assertEquals("This value should be lower than or equal to 999.99.", error);
    }

    // save user input
    @Test (priority = 10)
    public void saveInput(){
//        menu.clickTalentTraits();
        menu.clickNext();
        String active = menu.getActiveTabTalentTraits();
        assertEquals("active ng-binding ng-scope", active);
    }

}