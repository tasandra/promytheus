package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Iterator;
import java.util.List;

public class TalentTraitsPage extends BasePage {

    @FindBy(how = How.XPATH,using = "//*[@id='traits']/tbody/tr/td[2]/input")
    private List<WebElement> rows;

    @FindBy(how = How.XPATH,using = "//*[@id='traits']/tbody/tr/td[2]/span[2]")
    private List<WebElement> errors;

    public TalentTraitsPage(WebDriver driver){
        super(driver);
    }

    public List<WebElement> getTraitsRowsInput(){
        wait.until(ExpectedConditions.visibilityOfAllElements(rows));
        return rows;
    }

    public void insetScales(String scale){
        Iterator<WebElement> iterator = rows.iterator();
        while(iterator.hasNext()){
            iterator.next().sendKeys(scale);
        }
    }

    public List<WebElement> getTraitsErrors(){
        wait.until(ExpectedConditions.visibilityOfAllElements(errors));
        return errors;
    }
}
