package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;

public class TalentsPage extends BasePage {

    @FindBy(how = How.CSS, using = ".content-wrapper > h3")
    private WebElement header;

    @FindBy(how = How.XPATH, using = "//div[@class = 'col-md-12']/select")
    private WebElement selectNumOfRows;

    @FindBy(how = How.XPATH, using = "//table/tbody/tr")
    private List<WebElement> rows;

    @FindBy(how = How.XPATH, using = "//table/tbody/tr/td[2]/a")
    private List<WebElement> names;

    @FindBy(how = How.XPATH, using = "//table/tbody/tr[1]/td[2]/a")
    private WebElement name1;

    @FindBy(how = How.XPATH, using = "//table/tbody/tr/td[1]/div/label")
    private List<WebElement> checkboxes;

    @FindBy(how = How.XPATH, using = "//table/tbody/tr/td[1]/div/label")
    private List<WebElement> checkboxClass;

    @FindBy(how = How.LINK_TEXT, using = "New")
    private WebElement newButton;

    @FindBy(how = How.XPATH, using = "//table/tbody/tr/td[9]/a")
    private List<WebElement> edit;

    @FindBy(how = How.XPATH, using = "//div[@class = 'btn-group']/button[1]")
    private WebElement enable;

    @FindBy(how = How.XPATH, using = "//div[@class = 'btn-group']/button[2]")
    private WebElement disable;

    @FindBy(how = How.XPATH, using = "//div[@class = 'btn-group']/button[3]")
    private WebElement archive;

    @FindBy(how = How.XPATH, using =  "//table/tbody/tr[1]/td[1]/div/label")
    private WebElement checkbox1;

    @FindBy(how = How.XPATH,using = "//table/tbody/tr[1]/td[7]/em")
    private WebElement status;

    @FindBy(how = How.NAME, using = "search")
    private WebElement search;

    @FindBy(how = How.XPATH,using = "//button[contains(.,'Search')]")
    private WebElement searchButton;

// constructor
    public TalentsPage(WebDriver driver){
       super(driver);
    }

    // page resources
// get header
    public String getHeader(){
        return header.getText();
    }

// select 10,25,50,100 rows displayed on page
    public void selectRowsOnPage(String rowsCount){
        Select numOfRows =new Select(selectNumOfRows);
        numOfRows.selectByVisibleText(rowsCount);
    }
// get number of rows displayed per page
    public int getNumberOfRow(){
       return rows.size();
    }

// get all names displayed on page
    public List<WebElement> getNames(){
        wait.until(ExpectedConditions.visibilityOfAllElements(names));
        return names;
    }
    // get first name
    public String getFirstName(){
        return wait.until(ExpectedConditions.visibilityOf(name1)).getText();
    }

// get all checkboxes
    public List<WebElement> clickCheckboxes(){
        wait.until(ExpectedConditions.visibilityOfAllElements(checkboxes));
        return checkboxes;
    }

    // get checkBoxes class values
    public List<WebElement> getCheckboxClass(){
       wait.until(ExpectedConditions.visibilityOfAllElements(checkboxClass));
       return checkboxClass;
    }

// click on first checkbox
    public void clickCheckbox(){
        wait.until(ExpectedConditions.elementToBeClickable(checkbox1));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", checkbox1);
    }

// get status of talent
    public String getStatus(){
        return wait.until(ExpectedConditions.visibilityOf(status)).getAttribute("title");
    }

// click enable button
    public void clickEnable(){
        wait.until(ExpectedConditions.elementToBeClickable(enable));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", enable);
    }

// click disable button
    public void clickDisable(){
        wait.until(ExpectedConditions.visibilityOf(disable)).click();
    }

// click archive button
    public void clickArchive(){
        wait.until(ExpectedConditions.visibilityOf(archive)).click();
    }

// insert name to search field and click on it
    public void enterSearch(String name){
        search.clear();
        search.sendKeys(name);
        searchButton.click();
    }

// click new button
    public void clickNew() {
        wait.until(ExpectedConditions.elementToBeClickable(newButton)).click();
    }

// click edit button
    public List<WebElement> clickEdit(){
        return edit;
    }

}
