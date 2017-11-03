package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage{

    @FindBy(how = How.NAME, using = "firstName")
    private WebElement firstName;

    @FindBy(how = How.NAME, using = "lastName")
    private WebElement lastName;

    @FindBy(how = How.NAME, using = "emailAddress")
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

    public RegisterPage(WebDriver driver){
        super(driver);
    }

    public void setFirstName(String fName) {
        wait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys(fName);
    }

    public void setLastName(String  lName) {
        wait.until(ExpectedConditions.visibilityOf(lastName)).sendKeys(lName);
    }

    public void setEmailAddress(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailAddress)).sendKeys(email);
    }

    public void setPhone(String phoneNumber) {
        wait.until(ExpectedConditions.visibilityOf(phone)).sendKeys(phoneNumber);
    }

    public void setPassword(String pass) {
        wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(pass);
    }

    public void setConfirmPassword(String confirmPass) {
        wait.until(ExpectedConditions.visibilityOf(confirmPassword)).sendKeys(confirmPass);
    }

    public void setCountry(String countryName) {
        wait.until(ExpectedConditions.elementToBeClickable(country)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(.,'" + countryName + "')]"))).click();
    }

    public void setAddress(String address1) {
        wait.until(ExpectedConditions.visibilityOf(address)).sendKeys(address1);
    }

    public void setCity(String cityName) {
        wait.until(ExpectedConditions.visibilityOf(city)).sendKeys(cityName);
    }

    public void setState(String stateName) {
        wait.until(ExpectedConditions.visibilityOf(state)).sendKeys(stateName);
    }

    public void setZip(String postal) {
        wait.until(ExpectedConditions.visibilityOf(zip)).sendKeys(postal);
    }

    public void clickCreateAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(createAccount)).click();
    }

}
