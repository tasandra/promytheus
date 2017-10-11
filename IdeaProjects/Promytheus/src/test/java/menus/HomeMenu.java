package menus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeMenu {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(how = How.CSS,using = ".brand-logo > img")
    WebElement home;

    @FindBy(how = How.CSS, using = ".icon-user")
    WebElement userIcon;

    @FindBy(how = How.LINK_TEXT, using = "My Profile")
    WebElement profile;

    @FindBy(how = How.LINK_TEXT, using = "Sign Out")
    WebElement signOut;

    // constructor
    public HomeMenu(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }
// get home button logo
    public WebElement getHomeMenuLogo() {
        return wait.until(ExpectedConditions.visibilityOf(home));
    }
// click on home button
    public void clickHome(){
        home.click();
    }
// click on user ici=on
    public void clickUserIcon(){
        userIcon.click();
    }
// click on my profile
    public void myProfile(){
        clickUserIcon();
        wait.until(ExpectedConditions.visibilityOf(profile)).click();
    }
// click on logout button
    public void logout(){
        clickUserIcon();
        wait.until(ExpectedConditions.visibilityOf(signOut)).click();
    }


}
