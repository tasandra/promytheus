package tests;

import data.ExcelReadApi;
import data.ExcelWriteApi;
import data.NadaPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import pages.RegisterPage;

import java.util.Iterator;
import java.util.Set;

import static org.testng.Assert.*;

public class RegisterPageTest extends BaseTest{
    private RegisterPage registerPage;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private NadaPage emailSite;
    private String country;
    private String address;
    private String city;
    private String state;
    private String zip;
    private  String window1;
    private  String window2;

    @BeforeClass
    public void generateUser()  throws Exception{
        emailSite = new NadaPage(driver);
        registerPage = new RegisterPage(driver);
        // create random information
        firstName = RandomStringUtils.random(10, true, false);
        lastName = RandomStringUtils.random(10, true, false);
        String phone1 = RandomStringUtils.random(3, false, true);
        String phone2 = RandomStringUtils.random(3, false, true);
        String phone3 = RandomStringUtils.random(4, false, true);
        phone = "(" + phone1 + ") " + phone2 + "-" + phone3;
        password = RandomStringUtils.random(10, true, true);
        country = "Bangladesh";
        address = RandomStringUtils.random(10,true,true);
        city = RandomStringUtils.random(10,true,false);
        state = RandomStringUtils.random(10, true, false);
        zip = RandomStringUtils.random(5,false, true);

        // go to email website to generate email
       String emailUrl = "https://getnada.com/";
       ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", emailUrl);

       // set window handles
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> itr = windows.iterator();

//window1 will provide you parent window
        window1 = itr.next();
//window2 will provide you child window
        window2 = itr.next();

        // switch to email website
        driver.switchTo().window(window2);
        emailSite.clickGetEmail();
        email = emailSite.getEmail();
//        System.out.print(email);

        // write email and password to excel file
        ExcelWriteApi write = new ExcelWriteApi("promy.xlsx");
        int rows = write.getRowCount("users");
        //System.out.print(rows);
        write.setCellData("users",0,rows,email);
        write.setCellData("users",1,rows,password);

        // switch to application window
        driver.switchTo().window(window1);
    }
// go to register page
    @Test(priority = 1)
    public void gotoRegister(){
        loginPage.clickRegister();
        boolean breakIt = true;
        while (true) {
            breakIt = true;
            try {
                String header = registerPage.getHeader();
                assertEquals("SIGNUP TO GET INSTANT ACCESS", header, "user is not redirect to register page");
            } catch (Exception e) {
                if (e.getMessage().contains("element is not attached")) {
                    breakIt = false;
                }
            }
            if (breakIt) {
                break;
            }
        }
    }

//assert invalid emails error message
    @Test(priority = 2,  dataProvider = "invalidEmails")
    public void checkEmailError( String email){
        registerPage.setEmailAddress(email);
        String error = registerPage.getEmailError();
        assertEquals("Please enter a valid email address.", error, "user can register with invalid email format");
    }

//  register new user
    @Test(priority = 3)
    public void registerNewUser(){
        // insert data
        registerPage.setFirstName(firstName);
        registerPage.setLastName(lastName);
        registerPage.setEmailAddress(email);
        registerPage.setPhone(phone);
        registerPage.setPassword(password);
        registerPage.setConfirmPassword(password);
        registerPage.setCountry(country);
        registerPage.setAddress(address);
        registerPage.setCity(city);
        registerPage.setState(state);
        registerPage.setZip(zip);
        // click create account
        registerPage.clickCreateAccount();
        // assert user redirect to talents page
        loginPage.submitLogin(email,password);
        // assert pop up message
        String popupHeader = talentsPage.getPopupHeader();
        assertEquals("Who would you like to report?", popupHeader);
        // click report to yourself radio button
        talentsPage.clickPopupRadioYourSelf();
        talentsPage.clickPopupOK();
        String talentsHeader = talentsPage.getHeader();
        assertEquals("Talents", talentsHeader, "user is not redirect to talents page");
    }

    // check email
    @Test(priority = 4)
    public void checkEmailWelcomeMessage(){
        // switch to email window
        driver.switchTo().window(window2);
        String message = emailSite.getWelcomeMessage();
        assertTrue(message.contains("Welcome"), "user not received welcome message");
    }

    // assert my profile page
    @Test(priority = 5)
    public void assertMyProfile(){
        // switch window to application
        driver.switchTo().window(window1);

        // click on my profile
        try {
            home.myProfileClick();
        }
        catch(WebDriverException e){
            home.myProfileJs();
            System.out.println(" click on my profile with javascript execution");
        }
        catch(Exception e){
            System.out.print("WebDriver did not click on My Profile");
        }
        // create two array with input data and data from my profile page
        String[] data = { firstName,lastName,email,phone,country,address,city,state,zip};
        String[] myProfileInfo = new String[9];
        myProfileInfo[0] = profilePage.getFirstName();
        myProfileInfo[1] = profilePage.getLastName();
        myProfileInfo[2] = profilePage.getEmail();
        myProfileInfo[3] = profilePage.getPhone();
        myProfileInfo[4] = profilePage.getCountry();
        myProfileInfo[5] = profilePage.getAddress();
        myProfileInfo[6] = profilePage.getCity();
        myProfileInfo[7] = profilePage.getState();
        myProfileInfo[8] = profilePage.getZip();

        // assert two arrays are equal
        for (int i = 0; i < myProfileInfo.length; i++) {
//            System.out.println(myProfileInfo[i] + " = " + data[i]);
            assertTrue(myProfileInfo[i].equals(data[i]), myProfileInfo[i] + " not equals " + data[i]);
        }
    }

    @DataProvider(name="invalidEmails")
    public Object[][] userFormData() throws Exception {
        ExcelReadApi excel = new ExcelReadApi("promy.xlsx");
        Object[][] data = excel.testData("emails");
        return data;
    }
}