package tests;

import menus.HomeMenu;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import sun.rmi.runtime.Log;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InvalidLoginTest extends BaseTest {
    private LoginPage loginPage;
    private HomeMenu home;

    @BeforeClass
    public void beforeClass(){
        loginPage = new LoginPage(driver);
        home = new HomeMenu(driver);
    }

    @Test(priority = 1)
    // log in with empty fields
    public void emptyEmail() {
        loginPage.submitLogin("", "");
        String error = loginPage.getError();
// assert correct error is displayed
        assertEquals("This value is required.", error, "Incorrect error message");
    }

    @Test(priority = 2)
    // log in with empty email field
    public void emptyFields() {
        loginPage.submitLogin("", "password");
        String error = loginPage.getError();
// assert correct error is displayed
        assertEquals("This value is required.", error, "Incorrect error message");
    }

    @Test(priority = 3)
    // log in with empty password field
    public void emptyPassword() {
        loginPage.submitLogin("kusiwa@cmail.club", "");
        String error = loginPage.getEmptyPassError();
// assert correct error is displayed
        assertEquals("This value is required.", error, "Incorrect error message");
    }

    @Test(priority = 4)
    // log in with invalid email
    public void invalidEmail() {
        loginPage.submitLogin("kusiwa@", "password");
        String error = loginPage.getInvalidError();
// assert correct error is displayed
            assertEquals("Invalid Email or Password.", error, "Incorrect error message");
    }

    @Test(priority = 5)
    // log in with invalid password
    public void invalidPassword() {
        loginPage.submitLogin("kusiwa@cmail.club", "pass");
        String error = loginPage.getInvalidError();
// assert correct error is displayed
        assertEquals("Invalid Email or Password.", error,"Incorrect error message");
    }

    @Test(priority = 6)
    // log in with valid credentials
    public void validLogin() {

        loginPage.submitLogin("kusiwa@cmail.club", "password");

        WebElement logo = home.getHomeMenuLogo();
        assertTrue(logo.isDisplayed(), "Logo on Talents page not displayed");
    }

    @Test(priority = 7)
    public void logout(){
        home.clickUserIcon();
        try {
            home.logoutClick();
        }
        catch(Exception e){
            home.logoutJs();
            System.out.println(" log out with javascript execution");
        }

        assertTrue(loginPage.getLogo().isDisplayed(),"User wasn't able to logout and logo not displayed");
    }

}
