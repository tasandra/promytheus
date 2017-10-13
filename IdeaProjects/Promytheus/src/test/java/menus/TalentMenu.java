package menus;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TalentMenu  {
    private WebDriver driver;
    private WebDriverWait wait;
    private  JavascriptExecutor executor;

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
    private WebElement reting;

    @FindBy(how = How.XPATH,using = "//span[contains(.,'Social Background')]")
    private WebElement socialBackround;

    @FindBy(how = How.XPATH, using = "//button[contains(.,'Previous')]")
    private WebElement previous;

    @FindBy(how = How.XPATH, using = "//button[contains(.,'Next')]")
    private WebElement next;

    @FindBy(how = How.XPATH, using = "//button[contains(.,'Finish')]")
    private WebElement finish;


    public TalentMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
        executor = (JavascriptExecutor)driver;
    }

    public void clickCategory(){
        category.click();
    }

    public void clickPersonal(){
//        wait.until(ExpectedConditions.visibilityOf(personal)).click();

        executor.executeScript("arguments[0].click()", personal);
    }

    public void clickTalentTraits(){
        talentTraits.click();
    }

    public void clickPersonalityTraits(){
        personalityTraits.click();
    }

    public void clickStory() {
        story.click();
    }

    public void clickEvidence(){
        evidence.click();
    }

    public void clickTraining(){
        training.click();
    }

    public void clickRating(){
        reting.click();
    }

    public void clickSocialBackground(){
//        socialBackround.click();
        executor.executeScript("arguments[0].click()", socialBackround);
    }

    public void clickPrevious(){
        previous.click();
    }

    public void clickNext(){
        next.click();
    }

    public void clickFinish(){
//        finish.click();
        executor.executeScript("arguments[0].click()", finish);
    }
}
