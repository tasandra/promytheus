package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class PersonalPage extends BasePage{

    @FindBy(how = How.NAME, using = "firstName")
    private WebElement firstName;

    @FindBy(how =  How.CSS, using = "div.has-error:nth-child(2) > span:nth-child(2)")
    private WebElement firstNameError;

    @FindBy(how = How.NAME, using = "middleName")
    private WebElement middleName;

    @FindBy(how =  How.CSS, using = "div.has-error:nth-child(3) > span:nth-child(2)")
    private WebElement middleNameError;

    @FindBy(how = How.NAME, using = "lastName")
    private WebElement lastName;

    @FindBy(how = How.CSS, using = "div.col-lg-4:nth-child(4) > span:nth-child(2)")
    private WebElement lastNameError;

    @FindBy(how = How.NAME, using ="profilePicture")
    private WebElement image;

    @FindBy(how = How.NAME, using = "dateBirth")
    private WebElement dateBirth;

    @FindBy(how = How.NAME, using = "placeBirth")
    private WebElement placeBirth;

    @FindBy(how = How.CSS, using = ".col-lg-8 > span:nth-child(2)")
    private WebElement placeBirthError;

    @FindBy(how = How.XPATH, using = "//span[contains(.,'Enter country name... USA  ')]")
    private WebElement clickCountry;

    @FindBy(how = How.XPATH, using = "//*[@id='signUpCountry']/span/span[2]")
    private WebElement getCountry;

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

    @FindBy(how = How.NAME, using = "email")
    private WebElement email;

    @FindBy(how = How.XPATH, using = "//span[@class='text-danger mr-sm']")
    private WebElement emailError;

    @FindBy(how = How.NAME, using = "phone")
    private WebElement phone;

    @FindBy(how = How.XPATH, using = "//span[@class='text-danger']")
    private WebElement error;

    @FindBy(how = How.CSS, using = "div.form-group:nth-child(13) > div:nth-child(2) > span:nth-child(2)")
    private WebElement heightError;

    @FindBy(how = How.CSS, using = "div.form-group:nth-child(14) > div:nth-child(2) > span:nth-child(2)")
    private WebElement weightError;

    @FindBy(how = How.XPATH, using = "//a[@class='social-account-more ng-binding']")
    private WebElement addSocialAccount;

    @FindBy(how = How.XPATH, using = "//span[@class='ui-select-placeholder text-muted ng-binding']")
    private WebElement selectSocialAccount;

    @FindBy(how = How.XPATH, using = "//span[@class='ng-binding ng-scope']")
    private WebElement getSocial;

    @FindBy(how = How.NAME, using = "height")
    private WebElement height;

    @FindBy(how = How.NAME, using = "weight")
    private WebElement weight;


    public PersonalPage(WebDriver driver) {
       super(driver);
    }

    // insert first name
    private void insertFirstName(String first){
        wait.until(ExpectedConditions.visibilityOf(firstName)).clear();
        firstName.sendKeys(first);
    }

    public String getFirstName(){
        return firstName.getAttribute("value");
    }

    // insert middle name
    private void insertMiddleName(String middle){

        middleName.clear();
        middleName.sendKeys(middle);
    }

    public String getMiddleName(){
        return middleName.getAttribute("value");
    }

    // insert last name
    private void insertLastName(String last){

        lastName.clear();
        lastName.sendKeys(last);
    }

    public String getLastName(){
        return lastName.getAttribute("value");
    }

    public void insertNames(String first, String middle, String last){
        insertFirstName(first);
        insertMiddleName(middle);
        insertLastName(last);
    }
    // get errors
    public String getFirstNameError(){
        return firstNameError.getText();
    }

    public String getMiddleNameError(){
        return middleNameError.getText();
    }

    public String getLastNameError(){
        return lastNameError.getText();
    }

    public void uploadImage(String imagePath) throws AWTException {
        executor.executeScript("arguments[0].click()", image);
//        image.click();

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

//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='profile-picture mb ml']")));
    }

    // handle incorrect image file type pup up
    public String getPopUpHeader(){

        String header = driver.findElement(By.tagName("h2")).getText();
        driver.findElement(By.xpath("//button[@class='confirm']")).click();

        return header;
    }

    // insert date and place of birthday
    public void insertDatePlaceBirth(String dateB , String placeB){
        dateBirth.clear();
        dateBirth.sendKeys(dateB);
        placeBirth.clear();
        placeBirth.sendKeys(placeB);
    }

    public String getDateOfB(){
        return dateBirth.getAttribute("value");
    }

    public String getPlaceOfB(){
        return placeBirth.getAttribute("value");
    }

    public String getPlaceBirthError(){
        return placeBirthError.getText();
    }

    // select country
    public void selectCountry(String country){
        executor.executeScript("arguments[0].click()", clickCountry);

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[contains(.,'" + country + "')]"))).click();
    }

    public String getCountryValue(){
        return getCountry.getText();
    }

    // enter address
    public void enterAddress(String add1, String add2, String city1, String state, String postal){
        address1.sendKeys(add1);
        address2.sendKeys(add2);
        city.sendKeys(city1);
        addressState.sendKeys(state);
        zip.sendKeys(postal);
    }

    public String getAddress1(){
        return address1.getAttribute("value");
    }

    public String getAddress2(){
        return address2.getAttribute("value");
    }

    public String getCity(){
        return city.getAttribute("value");
    }

    public String getState(){
        return addressState.getAttribute("value");
    }

    public String getZip(){
        return zip.getAttribute("value");
    }

    // select radio button Rural and get class attribute
    public String selectRural(){
        wait.until(ExpectedConditions.elementToBeClickable(radioRural));
        executor.executeScript("arguments[0].click()", radioRural);

        return ruralClass.getAttribute("class");
    }

    // select radio button Urban and get class attribute
    public String selectUrban(){
        wait.until(ExpectedConditions.elementToBeClickable(radioUrban));
        executor.executeScript("arguments[0].click()", radioUrban);

        return urbanClass.getAttribute("class");
    }

    // select radio button Privileged and get class attribute
    public String selectPrivileged(){
        wait.until(ExpectedConditions.elementToBeClickable(radioPrivileged));
        executor.executeScript("arguments[0].click()", radioPrivileged);

        return privilegedClass.getAttribute("class");
    }

    // select radio button Underprivileged and get class attribute
    public String selectUnderprivileged(){
        wait.until(ExpectedConditions.elementToBeClickable(radioUnderprivileged));
        executor.executeScript("arguments[0].click()", radioUnderprivileged);

        return underprivilegedClass.getAttribute("class");
    }

    // enter email
    public void enterEmail(String useEmail){
        email.clear();
        email.sendKeys(useEmail);
    }

    public String getEmail(){
        return email.getAttribute("value");
    }

    // get email error
    public String getEmailError(){
        return emailError.getText();
    }

    // enter phone number
    public void enterPhone(String userPhone){
        phone.clear();
        phone.sendKeys(userPhone);
    }

    public String getPhone(){
        return phone.getAttribute("value");
    }

    // get phone error
    public String getError(){
        return error.getText();
    }

    // add social account
    public void clickAddSocial(){
        addSocialAccount.click();
        wait.until(ExpectedConditions.visibilityOf(selectSocialAccount));
    }

    // select social account
    public void selectSocial(String social){
//        executor.executeScript("arguments[0].click()", selectSocialAccount);
        selectSocialAccount.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[contains(.,'" + social + "')]"))).click();
    }

    public String getSocialValue(){
        return getSocial.getText();
    }

    // insert height
    public void insertHeight(String userHeight){
        height.clear();
//        height.sendKeys(Keys.BACK_SPACE);
        height.sendKeys(userHeight);
    }

    public String getHeight(){
        return height.getAttribute("value");
    }

    // insert weight
    public void insertWeight(String userWeight){
        weight.clear();
        weight.sendKeys(userWeight);
    }

    public String getWeight(){
        return weight.getAttribute("value");
    }

    // get height error
    public String getHeightError(){
        return heightError.getText();
    }

    // get weight error
    public String getWeightError(){
        return weightError.getText();
    }

}
