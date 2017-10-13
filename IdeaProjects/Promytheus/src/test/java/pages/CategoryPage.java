package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//li[@class='active ng-binding']")
    private WebElement headerNew;

    @FindBy(how = How.CSS, using = ".content-wrapper > h3:nth-child(1)")
    private WebElement header;

    @FindBy(how = How.XPATH, using = "//h3/ol/li[2]")
    private WebElement headerName;

    @FindBy(how = How.XPATH, using = "//h3/ol/li[3]")
    private WebElement headerCategory;

    @FindBy(how = How.CSS, using = "li.ng-scope:nth-child(1)")
    private WebElement active;

    @FindBy(how = How.XPATH, using = "//span[@class='text-danger ng-binding']")
    private WebElement error;

    @FindBy(how = How.XPATH, using = "//span[contains(.,'Enter category name...   ')]")
    private WebElement category;


    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }

    public String getHeaderNew() {
        return wait.until(ExpectedConditions.visibilityOf(headerNew)).getText();
    }

    public String getHeader(){
        return wait.until(ExpectedConditions.visibilityOf(header)).getText();
    }

    public String getHeaderName() {
        wait.until(ExpectedConditions.visibilityOf(headerCategory)).getText();
        return headerName.getText();
    }

    // get active tab attribute
    public String getActiveTab(){
        wait.until(ExpectedConditions.attributeToBe(active, "class", "ng-scope active"));
        return  active.getAttribute("class");
    }

    // get error message
    public String getError(){
        return wait.until(ExpectedConditions.visibilityOf(error)).getText();
    }

    // click and select category
    public void selectCategory(String select) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", category);

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[contains(.,'" + select + "')]"))).click();
  }

    public String getCategory() {
        return wait.until(ExpectedConditions.visibilityOf(headerCategory)).getText();
    }
}
