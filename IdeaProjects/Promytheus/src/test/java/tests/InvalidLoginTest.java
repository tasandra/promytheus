package tests;

import menus.HomeMenu;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import data.ExcelReadApi;
import pages.LoginPage;

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

    @Test(priority = 1, dataProvider = "userData")
    // check error massages
    public void getErrors(String email, String pass) {
        loginPage.submitLogin(email, pass);
        try {
            String error = loginPage.getError();
            // assert correct error is displayed
            assertEquals("This value is required.", error,"c");
        } catch (Exception e) {
            String error = loginPage.getInvalidError();
            // assert correct error is displayed
            assertEquals("Invalid Email or Password.", error, "Incorrect error message");
        }
    }

    // check empty password error
    @Test(priority = 2)
    public void emptyPass(){
        loginPage.submitLogin("kusiwa@cmail.club", "");
        String error = loginPage.getEmptyPassError();
        assertEquals("This value is required.", error, "This value is required.");
    }

    @Test(priority = 3)
    // log in with valid credentials
    public void validLogin() {

        loginPage.submitLogin("kusiwa@cmail.club", "password");

        WebElement logo = home.getHomeMenuLogo();
        assertTrue(logo.isDisplayed(), "Logo on Talents page not displayed");
    }

    @Test(priority = 4)
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

    @DataProvider(name="userData")
    public Object[][] userFormData() throws Exception
    {
        ExcelReadApi excel = new ExcelReadApi("promy.xlsx");
        Object[][] data = excel.testData("login");
        return data;
    }

}
