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
    private JavascriptExecutor executor;

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

    @FindBy(how = How.XPATH, using = "//span[contains(.,'Enter country name... USA  ')]")
    private WebElement clickCountry;

    @FindBy(how = How.ID,using = "address")
    private WebElement address1;

    @FindBy(how =  How.NAME, using = "address2")
    private WebElement address2;

    @FindBy(how = How.NAME , using = "city")
    private WebElement city;

    @FindBy(how = How.NAME, using = "addressState")
    private WebElement addressState;

    @FindBy(how = How.NAME, using = "zip")
    private WebElement zip;

    @FindBy(how = How.CSS, using = "div.form-group:nth-child(9) > div:nth-child(2) > label:nth-child(1) > span:nth-child(2)")
    private WebElement radioRural;

    @FindBy(how = How.XPATH, using = "//input[@value='RURAL']")
    private WebElement ruralClass;

    @FindBy(how = How.CSS, using = "div.form-group:nth-child(9) > div:nth-child(2) > label:nth-child(2) > span:nth-child(2)")
    private WebElement radioUrban;

    @FindBy(how = How.XPATH, using = "//input[@value='URBAN']")
    private WebElement urbanClass;

    @FindBy(how = How.CSS, using = "div.form-group:nth-child(10) > div:nth-child(2) > label:nth-child(1) > span:nth-child(2)")
    private WebElement radioPrivileged;

    @FindBy(how = How.XPATH, using = "//input[@value='PRIVILEGED']")
    private WebElement privilegedClass;

    @FindBy(how = How.CSS, using = "div.form-group:nth-child(10) > div:nth-child(2) > label:nth-child(2) > span:nth-child(2)")
    private WebElement radioUnderprivileged;

    @FindBy(how = How.XPATH, using = "//input[@value='UNDERPRIVILEGED']")
    private WebElement underprivilegedClass;


    public PersonalPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 20);
        executor = (JavascriptExecutor)driver;
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
    public void insertLastName(String last){
        lastName.sendKeys(last);
    }

    public void insertNames(String first, String middle, String last){
        insertFirstName(first);
        insertMiddleName(middle);
        insertLastName(last);
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

    // insert date and place of birthday
    public void insertDatePlaceBirth(String dateB , String placeB){
        dateBirth.sendKeys(dateB);
        placeBirth.sendKeys(placeB);
    }

    // select country
    public void selectCountry(String country){
        executor.executeScript("arguments[0].click()", clickCountry);

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[contains(.,'" + country + "')]"))).click();
    }

    // enter address
    public void enterAddress(String add1, String add2, String city1, String state, String postal){
        address1.sendKeys(add1);
        address2.sendKeys(add2);
        city.sendKeys(city1);
        addressState.sendKeys(state);
        zip.sendKeys(postal);
    }

    // select radio button Rural and get class attribute
    public String selectRural(){
        wait.until(ExpectedConditions.visibilityOf(radioRural));
        executor.executeScript("arguments[0].click()", radioRural);

        return ruralClass.getAttribute("class");
    }

    // select radio button Urban and get class attribute
    public String selectUrban(){
        wait.until(ExpectedConditions.visibilityOf(radioUrban));
        executor.executeScript("arguments[0].click()", radioUrban);

        return radioRural.getAttribute("class");
    }

    // select radio button Privileged and get class attribute
    public String selectPrivileged(){
        wait.until(ExpectedConditions.visibilityOf(radioPrivileged));
        executor.executeScript("arguments[0].click()", radioPrivileged);

        return radioPrivileged.getAttribute("class");
    }

    // select radio button Underprivileged and get class attribute
    public String selectUnderprivileged(){
        wait.until(ExpectedConditions.visibilityOf(radioUnderprivileged));
        executor.executeScript("arguments[0].click()", radioUnderprivileged);

        return radioUnderprivileged.getAttribute("class");

    }

}