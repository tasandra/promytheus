package tests;

import menus.TalentMenu;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.TalentsPage;

import static org.testng.Assert.assertEquals;

public class CategoryPageTest extends ValidLoginTest {
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
    @Test (priority = 1, dataProvider="SearchProvider")
    public void selectCategory1(String category){
        talents.clickNew();
        categoryPage.selectCategory(category);

        String categorySelected = categoryPage.getCategory();
        assertEquals(category, categorySelected);

        menu.clickNext();
        categoryPage.clickTalents();
    }

    // check error message
    @Test (priority = 2)
    public void getError(){
        talents.clickNew();
        menu.clickNext();
        String error = categoryPage.getError();
        assertEquals("Please select a talent category first.", error);
    }
    @DataProvider(name="SearchProvider")
    public Object[][] getDataFromDataprovider(){
        return new Object[][]
                {
                        { "KAZAKHSTAN" },
                        { "UNITED_KINGDOM" },
                        { "RUSSIA" }
                };

    }
}
