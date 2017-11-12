package menus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class TalentMenu extends BasePage {

    @FindBy(how = How.XPATH, using = "//span[contains(.,'Category')]")
    private WebElement category;

    @FindBy(how = How.XPATH, using = "//span[contains(.,'Personal')]")
    private WebElement personal;

    @FindBy(how = How.XPATH,using = "//span[contains(.,'Talent Traits')]")
    private WebElement talentTraits;

    @FindBy(how = How.XPATH, using = "//span[contains(.,'Personality Traits')]")
    private WebElement personalityTraits;

    @FindBy(how = How.XPATH,using = "//span[contains(.,'Story')]")
    private WebElement story;

    @FindBy(how = How.XPATH,using = "//span[contains(.,'Evidence')]")
    private WebElement evidence;

    @FindBy(how = How.XPATH,using = "//li[contains(.,'Training')]")
    private WebElement training;

    @FindBy(how = How.XPATH,using = "//span[contains(.,'Rating')]")
    private WebElement rating;

    @FindBy(how = How.XPATH,using = "//span[contains(.,'Social Background')]")
    private WebElement socialBackground;

    @FindBy(how = How.XPATH, using = "//button[contains(.,'Previous')]")
    private WebElement previous;

    @FindBy(how = How.XPATH, using = "//button[contains(.,'Next')]")
    private WebElement next;

    @FindBy(how = How.XPATH, using = "//button[contains(.,'Finish')]")
    private WebElement finish;

    @FindBy(how = How.CSS, using = "li.ng-scope:nth-child(1)")
    private WebElement activeTabCategory;

    @FindBy(how = How.CSS, using = "li.ng-scope:nth-child(2)")
    private WebElement activeTabPersonal;

    @FindBy(how = How.CSS, using = "li.ng-scope:nth-child(3)")
    private WebElement activeTabTalentTraits;

    @FindBy(how = How.CSS, using = "li.ng-scope:nth-child(4)")
    private WebElement activeTabPersonalityTraits;

    @FindBy(how = How.CSS, using = "li.ng-scope:nth-child(5)")
    private WebElement activeTabStory;

    @FindBy(how = How.CSS, using = "li.ng-scope:nth-child(6)")
    private WebElement activeTabEvidence;

    @FindBy(how = How.CSS, using = "li.ng-scope:nth-child(7)")
    private WebElement activeTabTraining;

    @FindBy(how = How.CSS, using = "li.ng-scope:nth-child(8)")
    private WebElement activeTabRating;

    @FindBy(how = How.CSS, using = "li.ng-scope:nth-child(9)")
    private WebElement activeTabSocialBackground;


    public TalentMenu(WebDriver driver) {
       super(driver);
    }

    public void clickCategory(){
        wait.until(ExpectedConditions.elementToBeClickable(category)).click();
    }

    public void clickPersonal(){
        wait.until(ExpectedConditions.elementToBeClickable(personal));
        executor.executeScript("arguments[0].click()", personal);
    }

    public void clickTalentTraits(){
        wait.until(ExpectedConditions.elementToBeClickable(talentTraits));
        executor.executeScript("arguments[0].click()", talentTraits);
//                talentTraits.click();
    }

    public void clickPersonalityTraits(){
        wait.until(ExpectedConditions.elementToBeClickable(personalityTraits)).click();
    }

    public void clickStory() {
        wait.until(ExpectedConditions.elementToBeClickable(story)).click();
    }

    public void clickEvidence(){

        wait.until(ExpectedConditions.elementToBeClickable(evidence)).click();
    }

    public void clickTraining(){

        wait.until(ExpectedConditions.elementToBeClickable(training)).click();
    }

    public void clickRating(){

        wait.until(ExpectedConditions.elementToBeClickable(rating)).click();
    }

    public void clickSocialBackground(){
        wait.until(ExpectedConditions.elementToBeClickable(socialBackground));
//        executor.executeScript("arguments[0].click()", socialBackground);

        socialBackground.click();
    }

    public void clickPrevious(){

        wait.until(ExpectedConditions.elementToBeClickable(previous)).click();
    }

    public void clickNext(){
        wait.until(ExpectedConditions.elementToBeClickable(next));
        executor.executeScript("arguments[0].click()", next);
    }

    public void clickFinish(){
        wait.until(ExpectedConditions.elementToBeClickable(finish));
        executor.executeScript("arguments[0].click()", finish);
    }

    // get active tab attribute category
    public String getActiveTabCategory(){
        wait.until(ExpectedConditions.visibilityOf(activeTabCategory));
        return  activeTabCategory.getAttribute("class");
    }

    // get active tab attribute category
    public String getActiveTabPersonal(){
        wait.until(ExpectedConditions.attributeToBe(activeTabPersonal, "class", "ng-scope active"));
        return  activeTabPersonal.getAttribute("class");
    }


    // get active tap talent traits
    public String getActiveTabTalentTraits(){
        wait.until(ExpectedConditions.attributeToBe(activeTabTalentTraits, "class", "ng-scope active"));
        return  activeTabTalentTraits.getAttribute("class");
    }

    // get active tap Personality Traits
    public String getActiveTabPersonalityTraits(){
        wait.until(ExpectedConditions.attributeToBe(activeTabPersonalityTraits, "class", "ng-scope active"));
        return  activeTabPersonalityTraits.getAttribute("class");
    }

    // get active tab attribute social background
    public String getActiveTabSocialBackgroung(){
        wait.until(ExpectedConditions.attributeToBe(activeTabSocialBackground, "class", "ng-scope active"));
        return  activeTabSocialBackground.getAttribute("class");
    }
}