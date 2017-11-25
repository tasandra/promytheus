package tests;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import data.ExcelReadApi;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginPageTest extends BaseTest {

    @Test(priority = 1, dataProvider = "userData")
    // check error massages
    public void getErrors(String email, String pass) {
        loginPage.submitLogin(email, pass);
        try {
            String error = loginPage.getError();
            // assert correct error is displayed
            assertEquals("This value is required.", error,"required error message not displayed");
        } catch (Exception e) {
            String error = loginPage.getInvalidError();
            // assert correct error is displayed
            assertEquals("Invalid Email or Password.", error, "Incorrect error message not displayed");
        }
    }

    // check empty password error
    @Test(priority = 2)
    public void emptyPass(){
        loginPage.submitLogin("kusiwa@cmail.club", "");
        String error = loginPage.getEmptyPassError();
        assertEquals("This value is required.", error, "required error message not displayed");
    }

    @Test( groups = "p1", dependsOnMethods = "checkTitle")
    // log in with valid credentials
    public void validLogin() {

        loginPage.submitLogin("kusiwa@cmail.club", "password");

        WebElement logo = home.getHomeMenuLogo();
        assertTrue(logo.isDisplayed(), "Logo on Talents page not displayed");
    }

    @Test(groups = "p1", dependsOnMethods = "validLogin")
    // log out
    public void logout(){
        try {
            home.logoutClick();
        }
        catch(WebDriverException e){
            home.logoutJs();
            System.out.println(" click on log out with javascript execution");
        }
        catch(Exception e){
            System.out.print("WebDriver did not click on log out");
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
