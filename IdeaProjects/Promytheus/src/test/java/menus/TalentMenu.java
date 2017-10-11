package menus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class TalentMenu  {
    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//span[contains(.,'Category')]")
    WebElement category;

    @FindBy(how = How.XPATH, using = "//span[contains(.,'Personal')]")
    WebElement personal;

    @FindBy(how = How.XPATH,using = "//span[contains(.,'Talent Traits')]")
    WebElement talentTraits;

    @FindBy(how = How.XPATH, using = "//span[contains(.,'Personality Traits')]")
    WebElement personalityTraits;

    @FindBy(how = How.XPATH,using = "//span[contains(.,'Story')]")
    WebElement story;

    @FindBy(how = How.XPATH,using = "//span[contains(.,'Evidence')]")
    WebElement evidence;

    @FindBy(how = How.XPATH,using = "//li[contains(.,'Training')]")
    WebElement training;

    @FindBy(how = How.XPATH,using = "//span[contains(.,'Rating')]")
    WebElement reting;

    @FindBy(how = How.XPATH,using = "//span[contains(.,'Social Background')]")
    WebElement socialBackround;

    @FindBy(how = How.XPATH, using = "//button[contains(.,'Previous')]")
    WebElement previous;

    @FindBy(how = How.XPATH, using = "//button[contains(.,'Next')]")
    WebElement next;


    public TalentMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCategory(){
        category.click();
    }

    public void clickPersonal(){
        personal.click();
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
        socialBackround.click();
    }

    public void clickPrevious(){
        previous.click();
    }

    public void clickNext(){
        next.click();
    }
}
