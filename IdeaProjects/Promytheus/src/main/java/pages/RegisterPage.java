package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage {

    @FindBy(how = How.TAG_NAME, using = "strong")
    private WebElement header;

    @FindBy(how = How.NAME, using = "firstName")
    private WebElement firstName;

    @FindBy(how = How.NAME, using = "lastName")
    private WebElement lastName;

    @FindBy(how = How.NAME, using = "email")
    private WebElement emailAddress;

    @FindBy(how = How.NAME, using = "phone")
    private WebElement phone;

    @FindBy(how = How.NAME, using = "password")
    private WebElement password;

    @FindBy(how = How.NAME, using = "confirmPassword")
    private WebElement confirmPassword;

    @FindBy(how = How.XPATH, using = "//span[@class='ui-select-match-text pull-left']")
    private WebElement country;

    @FindBy(how = How.NAME, using = "address")
    private WebElement address;

    @FindBy(how = How.NAME, using = "city")
    private WebElement city;

    @FindBy(how = How.NAME, using = "state")
    private WebElement state;

    @FindBy(how = How.NAME, using = "zip")
    private WebElement zip;

    @FindBy(how = How.ID, using = "createAccount")
    private WebElement createAccount;

    @FindBy(how = How.XPATH, using = "//*[@id='registerForm']/div[2]/div[1]/div[1]/div[4]/div/span[3]")
    private WebElement emailError;
// constructor
    public RegisterPage(WebDriver driver){
        super(driver);
    }
// get header
    public String  getHeader() {
        wait.until(ExpectedConditions.titleIs("ProMytheUs - Sign Up"));
        return header.getText();
    }
// set first name
    public void setFirstName(String fName) {

        wait.until(ExpectedConditions.visibilityOf(firstName)).clear();
        firstName.sendKeys(fName);
    }
// set last name
    public void setLastName(String  lName) {

        wait.until(ExpectedConditions.visibilityOf(lastName)).clear();
        lastName.sendKeys(lName);
    }
// set email address
    public void setEmailAddress(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailAddress)).clear();
        emailAddress.sendKeys(email);
    }
// get invalid email format error message
    public String getEmailError() {
        return wait.until(ExpectedConditions.visibilityOf(emailError)).getText();
    }

// set phone number
    public void setPhone(String phoneNumber) {
        wait.until(ExpectedConditions.visibilityOf(phone)).clear();
        phone.sendKeys(phoneNumber);
    }
// set password
    public void setPassword(String pass) {
        wait.until(ExpectedConditions.visibilityOf(password)).clear();
        password.sendKeys(pass);
    }
// set password confirmation
    public void setConfirmPassword(String confirmPass) {
        wait.until(ExpectedConditions.visibilityOf(confirmPassword)).clear();
        confirmPassword.sendKeys(confirmPass);
    }
// select and click on country
    public void setCountry(String countryName) {
        wait.until(ExpectedConditions.elementToBeClickable(country)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(.,'" + countryName + "')]"))).click();
    }
// set address
    public void setAddress(String address1) {
        wait.until(ExpectedConditions.visibilityOf(address)).clear();
        address.sendKeys(address1);
    }
// set city
    public void setCity(String cityName) {
        wait.until(ExpectedConditions.visibilityOf(city)).clear();
        city.sendKeys(cityName);
    }
// set state
    public void setState(String stateName) {
        wait.until(ExpectedConditions.visibilityOf(state)).clear();
        state.sendKeys(stateName);
    }
// set zip code
    public void setZip(String postal) {
        wait.until(ExpectedConditions.visibilityOf(zip)).clear();
        zip.sendKeys(postal);
    }
// click on create account button
    public void clickCreateAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(createAccount)).click();
    }

}
