package menus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class HomeMenu extends BasePage{

    @FindBy(how = How.CSS,using = ".brand-logo > img")
    private WebElement home;

    @FindBy(how = How.XPATH, using = "//header/nav/div[2]/ul[2]/li[2]")
    private WebElement userIcon;

    @FindBy(how = How.XPATH, using = "//header/nav/div[2]/ul[2]/li[2]/ul/li/div/a[1]/div/div[2]/p")
    private WebElement profile;

    @FindBy(how = How.XPATH,using = "//header/nav/div[2]/ul[2]/li[2]/ul/li/div/a[2]/div/div[2]/p")
    private WebElement signOut;

    // constructor
    public HomeMenu(WebDriver driver){
       super(driver);
    }

// get home button logo
    public WebElement getHomeMenuLogo() {
        return wait.until(ExpectedConditions.visibilityOf(home));
    }
// click on home button
    public void clickHome(){
        home.click();
    }
// click on user icon
    public void clickUserIcon(){
        wait.until(ExpectedConditions.elementToBeClickable(userIcon));
//        executor.executeScript("arguments[0].click()", userIcon);
        userIcon.click();
    }
    // click on my profile
    public void myProfileClick(){
        wait.until(ExpectedConditions.elementToBeClickable(profile)).click();
    }
    // click on my profile button with javascript
    public void myProfileJs(){
        wait.until(ExpectedConditions.elementToBeClickable(profile));
        executor.executeScript("arguments[0].click()", profile);
    }
    // click on logout button
    public void logoutClick(){
        clickUserIcon();
        wait.until(ExpectedConditions.elementToBeClickable(signOut)).click();
    }

    // click on logout button with javascript
    public void logoutJs(){
        clickUserIcon();
        wait.until(ExpectedConditions.elementToBeClickable(signOut));
       executor.executeScript("arguments[0].click()", signOut);
    }



}
