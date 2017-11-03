package tests;

import data.ExcelReadApi;
import data.ExcelWriteApi;
import data.NadaPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.util.Iterator;
import java.util.Set;

import static org.testng.Assert.*;

public class RegisterPageTest extends BaseTest{
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private NadaPage emailSite;
    private  String window1;
    private  String window2;

    @BeforeClass
    public void generateUser()  throws Exception{
        emailSite = new NadaPage(driver);
        firstName = RandomStringUtils.random(10, true, false);
        lastName = RandomStringUtils.random(10, true, false);
        String phone1 = RandomStringUtils.random(3, false, true);
        String phone2 = RandomStringUtils.random(3, false, true);
        String phone3 = RandomStringUtils.random(4, false, true);
        phone = "(" + phone1 + ") " + phone2 + "-" + phone3;
        password = RandomStringUtils.random(10, true, true);

       String url = "https://getnada.com/";
       ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", url);

        Set<String> windows = driver.getWindowHandles();
        Iterator<String> itr = windows.iterator();

//window1 will provide you parent window
        window1 = itr.next();
//window2 will provide you child window
        window2 = itr.next();

        driver.switchTo().window(window2);
        emailSite.clickGetEmail();
        email = emailSite.getEmail();
//        System.out.print(email);

        ExcelWriteApi write = new ExcelWriteApi("promy.xlsx");
        int rows = write.getRowCount("users");
        //System.out.print(rows);
        write.setCellData("users",0,rows ,email);
        write.setCellData("users",1,rows ,password);

        driver.switchTo().window(window1);
    }



}