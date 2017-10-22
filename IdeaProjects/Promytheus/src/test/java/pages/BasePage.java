package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    final WebDriver driver;
    protected final WebDriverWait wait;
    protected final JavascriptExecutor executor;

    protected BasePage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
        executor = (JavascriptExecutor)driver;
    }


}
