package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyProfilePage extends BasePage{

    @FindBy(how = How.XPATH, using = "//h3[contains(.,'My Profile')]")
    private WebElement header;

    @FindBy(how = How.ID, using = "firstName")
    private WebElement firstName;

    @FindBy(how = How.ID, using = "lastName")
    private WebElement lastName;

    @FindBy(how = How.ID, using = "email")
    private WebElement email;

    @FindBy(how = How.ID, using = "phone")
    private WebElement phone;

    @FindBy(how = How.XPATH, using = "//span[@class='ui-select-match-text pull-left']")
    private WebElement country;

    @FindBy(how = How.ID, using = "address")
    private WebElement address;

    @FindBy(how = How.ID, using = "city")
    private WebElement city;

    @FindBy(how = How.ID, using = "state")
    private WebElement state;

    @FindBy(how = How.ID, using = "zip")
    private WebElement zip;
// constructor
    public MyProfilePage(WebDriver driver) {
        super(driver);
    }
// get values from fields
    public String getHeader() {
        return wait.until(ExpectedConditions.visibilityOf(header)).getText();
    }

    public String getFirstName() {
        return wait.until(ExpectedConditions.visibilityOf(firstName)).getAttribute("value");
    }

    public String getLastName() {
        return wait.until(ExpectedConditions.visibilityOf(lastName)).getAttribute("value");
    }

    public String getEmail() {
        return wait.until(ExpectedConditions.visibilityOf(email)).getAttribute("value");
    }

    public String getPhone() {
        return wait.until(ExpectedConditions.visibilityOf(phone)).getAttribute("value");
    }

    public String getCountry() {
        return wait.until(ExpectedConditions.visibilityOf(country)).getText();
    }

    public String getAddress() {
        return wait.until(ExpectedConditions.visibilityOf(address)).getAttribute("value");
    }

    public String getCity() {
        return wait.until(ExpectedConditions.visibilityOf(city)).getAttribute("value");
    }

    public String getState() {
        return wait.until(ExpectedConditions.visibilityOf(state)).getAttribute("value");
    }

    public String getZip() {
        return wait.until(ExpectedConditions.visibilityOf(zip)).getAttribute("value");
    }
}
