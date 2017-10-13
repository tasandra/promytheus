package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class PersonalPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(how = How.CSS, using = "li.ng-scope:nth-child(2)")
    private WebElement activeTab;

    @FindBy(how = How.NAME, using = "firstName")
    private WebElement firstName;

    @FindBy(how = How.NAME, using = "middleName")
    private WebElement middleName;

    @FindBy(how = How.NAME, using = "lastName")
    private WebElement lastName;

    @FindBy(how = How.NAME, using ="profilePicture")
    private WebElement image;

    @FindBy(how = How.NAME, using = "dateBirth")
    private WebElement dateBirth;

    @FindBy(how = How.NAME, using = "placeBirth")
    private WebElement placeBirth;

    @FindBy(how = How.CSS, using = ".ui-select-container")
    private WebElement clickCountry;

    public PersonalPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 20);
    }


    // get active tab attribute
    public String getActiveTab(){
        wait.until(ExpectedConditions.attributeToBe(activeTab, "class", "ng-scope active"));
        return  activeTab.getAttribute("class");
    }

    // insert first name
    public void insertFirstName(String first){
        wait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys(first);
    }

    // insert middle name
    public void insertMiddleName(String middle){
        middleName.sendKeys(middle);
    }

    // insert last name
    public void insertLasttName(String last){
        lastName.sendKeys(last);
    }

    public void insertNames(String first, String middle, String last){
        insertFirstName(first);
        insertMiddleName(middle);
        insertLasttName(last);
    }

    public void uploadImage(String imagePath) throws AWTException, InterruptedException{
        image.click();

        //put path to your image in a clipboard
        StringSelection ss = new StringSelection(imagePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

//imitate mouse events like ENTER, CTRL+C, CTRL+V
        Robot robot = new Robot();
        robot.delay(15000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(1500);
        robot.keyRelease(KeyEvent.VK_ENTER);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='profile-picture mb ml']")));
    }

    public void inserDatePlaceBirth(String dateB , String placeB){
        dateBirth.sendKeys(dateB);
        placeBirth.sendKeys(placeB);
    }

    public void selectCountry(String country){
//        clickCountry.click();
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", clickCountry);

//        executor.executeScript("document.querySelector('#signUpCountry').click()");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(.,'" + country + "')]"))).click();
    }
}