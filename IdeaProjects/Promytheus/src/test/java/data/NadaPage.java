package data;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NadaPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor executor;

    @FindBy(how = How.XPATH, using = "//div[@class='ui huge secondary button']")
    private WebElement getEmail;

    @FindBy(how = How.XPATH, using = "//*[@id='avatar-col']/div")
    private WebElement avatar;

    @FindBy(how = How.XPATH, using = "//*[@id='body']//h4/button")
    private WebElement copyEmail;

    @FindBy(how = How.CSS, using = ".active > a:nth-child(1) > span:nth-child(2) > span:nth-child(1)")
    private WebElement email;

    @FindBy(how = How.XPATH, using = "//*[@id='body']//span[2]")
    private WebElement welcome;


    public NadaPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
        executor = (JavascriptExecutor) driver;
    }

    public void clickGetEmail() {
        wait.until(ExpectedConditions.elementToBeClickable(getEmail)).click();
    }

    public WebElement getAvatar() {
        return avatar;
    }

    public void clickCopyEmail() {
        wait.until(ExpectedConditions.elementToBeClickable(copyEmail)).click();
    }

    public String getEmail() {
        return wait.until(ExpectedConditions.visibilityOf(email)).getText();
    }

    public String getWelcomeMessage() {
        return wait.until(ExpectedConditions.visibilityOf(welcome)).getText();
    }
}