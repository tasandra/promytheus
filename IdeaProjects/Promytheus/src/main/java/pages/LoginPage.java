package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{

    @FindBy(how = How.XPATH,using = "//auth-zone/div/img")
    private WebElement logo;

    @FindBy(how = How.NAME, using = "email")
    private WebElement email;

    @FindBy(how = How.NAME, using = "password")
    private WebElement password;

    @FindBy(how = How.ID, using = "login")
    private WebElement login;

    @FindBy(how = How.CSS, using = ".text-danger")
    private WebElement error;

    @FindBy(how = How.XPATH, using =  "//form/div[2]/span[2]")
    private WebElement error2;

    @FindBy(how = How.CSS, using = ".alert")
    private WebElement invalidError;

    @FindBy(how = How.LINK_TEXT, using = "Register Now")
    private WebElement register;

    // constructor
    public LoginPage(WebDriver driver){
       super(driver);
    }

    // page resource

    public WebElement getLogo() {
       return wait.until(ExpectedConditions.visibilityOf(logo));
    }

    private void enterUsername(String user){
        wait.until(ExpectedConditions.visibilityOf(email)).clear();
        email.sendKeys(user);
    }

    private void enterPassword(String pass){
        wait.until(ExpectedConditions.visibilityOf(password)).clear();
        password.sendKeys(pass);
    }

    private void clickLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(login));
//        executor.executeScript("arguments[0].click()", login);
        login.click();
    }

    private void clickLoginButtonJs(){
        wait.until(ExpectedConditions.elementToBeClickable(login));
        executor.executeScript("arguments[0].click()", login);
    }

    public void submitLogin(String user, String pass){
        enterUsername(user);
        enterPassword(pass);
        clickLoginButton();
    }

    public void clickRegister(){
        wait.until(ExpectedConditions.elementToBeClickable(register)).click();
    }
// get first error
    public String getError(){
       return wait.until(ExpectedConditions.visibilityOf(error)).getText();
    }
// get second error
    public String getEmptyPassError(){
        return wait.until(ExpectedConditions.visibilityOf(error2)).getText();
    }
 // get invalid format error
    public String getInvalidError(){
        return wait.until(ExpectedConditions.visibilityOf(invalidError)).getText();
    }

}
