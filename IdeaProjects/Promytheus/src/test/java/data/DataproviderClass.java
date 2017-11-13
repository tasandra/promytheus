package data;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;
public class DataproviderClass {
    @DataProvider(name = "PersonalInfo")
    public static Object[][] getDataFromDataProvider() {
        return new Object[][]{
                {"0",
                        // first name
                        RandomStringUtils.random(3,true,false),
                        // middle name
                        RandomStringUtils.random(2,true,false),
                        // last name
                        RandomStringUtils.random(6,true,false),
                        // date of birth
                        RandomStringUtils.random(2,1,12,false,true) + "/" +
                                RandomStringUtils.random(2,1,31,false,true) + "/" +
                        // place of birth
                        RandomStringUtils.random(10,true, false),
                        // country
                        "Kazakhstan",
                        // address 1
                        RandomStringUtils.randomNumeric(4) + " " +
                                RandomStringUtils.randomAlphabetic(4) + " " +
                                RandomStringUtils.randomAlphabetic(2) + ".",
                        // address 2
                        RandomStringUtils.randomAlphabetic(3) + "." +
                                RandomStringUtils.randomNumeric(4),
                        // city
                        RandomStringUtils.randomAlphabetic(3) + " " +
                                RandomStringUtils.randomAlphabetic(6),
                        // state
                        RandomStringUtils.randomAlphabetic(2).toUpperCase(),
                        // zip
                        RandomStringUtils.randomNumeric(5),
                        // email
                        RandomStringUtils.randomAlphabetic(6) + "@cmail.club",
                        // phone
                        "(" + RandomStringUtils.randomNumeric(3) + ") " +
                                RandomStringUtils.randomNumeric(3) + "-" +
                                RandomStringUtils.randomNumeric(4),
                        // social network
                        "Linkedin",
                        // height
                        RandomStringUtils.randomNumeric(3),
                        // weight
                        RandomStringUtils.randomNumeric(3)
                        },
                {"1",
                        // first name
                        RandomStringUtils.random(7,true,false),
                        // middle name
                        RandomStringUtils.random(0,true,false),
                        // last name
                        RandomStringUtils.random(3,true,false),
                        // date of birth
                        RandomStringUtils.random(2,1,12,false,true) + "/" +
                                RandomStringUtils.random(2,1,31,false,true) + "/" +
                                // place of birth
                                RandomStringUtils.random(4,true, false),
                        // country
                        "United Kingdom",
                        // address 1
                        RandomStringUtils.randomNumeric(2) + " " +
                                RandomStringUtils.randomAlphabetic(9) + " " +
                                RandomStringUtils.randomAlphabetic(3) + ".",
                        // address 2
                        RandomStringUtils.randomAlphabetic(3) + "." +
                                RandomStringUtils.randomNumeric(4),
                        // city
                        RandomStringUtils.randomAlphabetic(9),
                        // state
                        RandomStringUtils.randomAlphabetic(2).toUpperCase(),
                        // zip
                        RandomStringUtils.randomNumeric(5),
                        // email
                        RandomStringUtils.randomAlphabetic(9) + "@cmail.club",
                        // phone
                        "(" + RandomStringUtils.randomNumeric(3) + ") " +
                                RandomStringUtils.randomNumeric(3) + "-" +
                                RandomStringUtils.randomNumeric(4),
                        // social network
                        "Google",  // height
                        RandomStringUtils.randomNumeric(3),
                        // weight
                        RandomStringUtils.randomNumeric(3) + "." + RandomStringUtils.randomNumeric(2)},
                {"2",
                        // first name
                        RandomStringUtils.random(2,true,false),
                        // middle name
                        RandomStringUtils.random(5,true,false),
                        // last name
                        RandomStringUtils.random(10,true,false),
                        // date of birth
                        RandomStringUtils.random(2,1,12,false,true) + "/" +
                                RandomStringUtils.random(2,1,31,false,true) + "/" +
                                // plece of birth
                                RandomStringUtils.random(4,true, false),
                        // country
                        "Russia",
                        RandomStringUtils.randomNumeric(5) + " " +
                                RandomStringUtils.randomAlphabetic(3) + " " +
                                RandomStringUtils.randomAlphabetic(3) + ".",
                        // address 2
                        "",
                        // city
                        RandomStringUtils.randomAlphabetic(7),
                        // state
                        RandomStringUtils.randomAlphabetic(2).toUpperCase(),
                        // zip
                        RandomStringUtils.randomNumeric(5),
                        // email
                        RandomStringUtils.random(8, true, true) + "@cmail.club",
                        // phone
                        "(" + RandomStringUtils.randomNumeric(3) + ") " +
                                RandomStringUtils.randomNumeric(3) + "-" +
                                RandomStringUtils.randomNumeric(4),
                        // social network
                        "Facebook",
                        // height
                        RandomStringUtils.randomNumeric(2),
                        // weight
                        RandomStringUtils.randomNumeric(1)}
        };
    }
}

