package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(how = How.CSS,using = ".logo")
    WebElement logo;

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

    // constructor
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }

    // page resource

    public WebElement getLogo() {
       return wait.until(ExpectedConditions.visibilityOf(logo));
    }

    public void enterUsername(String user){
        email.clear();
        email.sendKeys(user);
    }

    public void enterPassword(String pass){
        password.clear();
        password.sendKeys(pass);
    }

    public void clickLoginButton(){
        login.click();
    }

    public void submitLogin(String user, String pass){
        enterUsername(user);
        enterPassword(pass);
        clickLoginButton();
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
