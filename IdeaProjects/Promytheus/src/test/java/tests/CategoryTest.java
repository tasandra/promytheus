package tests;

import menus.TalentMenu;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.LoginPage;
import pages.TalentsPage;

import static org.testng.Assert.assertEquals;

public class CategoryTest extends ValidLoginTest {
    private TalentsPage talents;
    private TalentMenu menu;
    private CategoryPage categoryPage;

    @BeforeClass
    public void beforeClass(){
        talents = new TalentsPage(driver);
        menu = new TalentMenu(driver);
        categoryPage = new CategoryPage(driver);
    }

    // create new talent and select category
    @Test (priority = 1)
    public void selectCategory(){
        talents.clickNew();
        categoryPage.selectCategory("Gymnast");

        String categorySelected = categoryPage.getCategory();
        assertEquals("Gymnast", categorySelected);
    }
}
