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

    @FindBy(how = How.XPATH, using = "//h3/ol/li[2]")
    private WebElement headerName;

    @FindBy(how = How.XPATH, using = "//h3/ol/li[3]")
    private WebElement headerCategory;

    @FindBy(how = How.XPATH, using = "//span[@class='ui-select-placeholder text-muted ng-binding']")
    private WebElement category;


    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public String getHeaderNew() {
        return wait.until(ExpectedConditions.visibilityOf(headerNew)).getText();
    }

    public String getHeaderName() {
        wait.until(ExpectedConditions.visibilityOf(headerCategory)).getText();
        return headerName.getText();
    }

    public void selectCategory(String select) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", category);
        driver.findElement(By.xpath("//span[contains(.,'" + select + "')]")).click();
  }

    public String getCategory() {
        return wait.until(ExpectedConditions.visibilityOf(headerCategory)).getText();
    }
}
