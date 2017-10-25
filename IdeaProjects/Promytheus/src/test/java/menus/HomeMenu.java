package menus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class HomeMenu extends BasePage{

    @FindBy(how = How.CSS,using = ".brand-logo > img")
    private
    WebElement home;

    @FindBy(how = How.XPATH, using = "//header/nav/div[2]/ul[2]/li[2]")
    private
    WebElement userIcon;

    @FindBy(how = How.LINK_TEXT, using = "My Profile")
    private
    WebElement profile;

    @FindBy(how = How.XPATH, using = "//p[contains(.,'Sign Out')]")
    private
    WebElement signOut;

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
// click on user ici=on
    public void clickUserIcon(){
        wait.until(ExpectedConditions.elementToBeClickable(userIcon));
        executor.executeScript("arguments[0].click()", userIcon);
//        userIcon.click();
    }
// click on my profile
    public void myProfile(){
        clickUserIcon();
        wait.until(ExpectedConditions.visibilityOf(profile)).click();
    }
// click on logout button
    public void logout(){
        clickUserIcon();
        wait.until(ExpectedConditions.elementToBeClickable(signOut));
        executor.executeScript("arguments[0].click()", signOut);
//        wait.until(ExpectedConditions.visibilityOf(signOut)).click();
    }


}
