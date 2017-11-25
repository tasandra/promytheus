package data;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;

public class DataproviderClass {
    @DataProvider(name = "PersonalInfo")
    public static Object[][] getDataFromDataProvider() {
        // date of birth
        int yearBegin = 1950;
        int yearEnd = 2017 - yearBegin;
        String dOfB;
        int month = (1 + (int) (Math.random() * 12));
        int day = (1 + (int) (Math.random() * 30));
        if ( month >= 10 && day >= 10){
            dOfB = "" + month + "/" + day + "/"
                    + (yearBegin + (int) (Math.random() * yearEnd));
        }
        else if (month < 10 && day >= 10){
            dOfB = "0" + month + "/" + day + "/"
                    + (yearBegin + (int) (Math.random() * yearEnd));
        }
        else if (day < 10 && month >= 10){
            dOfB = ""+ month + "/0" + day + "/"
                    + (yearBegin + (int) (Math.random() * yearEnd));
        }
        else if (month < 10 && day < 10){
            dOfB = "0" + month + "/0" + day + "/"
                    + (yearBegin + (int) (Math.random() * yearEnd));
        }else {
            dOfB = "" +  month + "/" + day + "/"
                    + (yearBegin + (int) (Math.random() * yearEnd));
        }

        return new Object[][]{
                {"0",
                        // first name
                        RandomStringUtils.random(3,true,false),
                        // middle name
                        RandomStringUtils.random(2,true,false),
                        // last name
                        RandomStringUtils.random(6,true,false),
                        // date of birth
                       dOfB,
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
                        RandomStringUtils.randomAlphabetic(6).toLowerCase() + "@cmail.club",
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
                        dOfB,
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
                        RandomStringUtils.randomAlphabetic(9).toLowerCase() + "@cmail.club",
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
                        RandomStringUtils.random(2, true, false),
                        // middle name
                        RandomStringUtils.random(5, true, false),
                        // last name
                        RandomStringUtils.random(10, true, false),
                        // date of birth
                        dOfB,
                        // plece of birth
                        RandomStringUtils.random(4, true, false),
                        // country
                        "Russia",
                        // address 1
                        RandomStringUtils.random(4, false, true) + " " +
                                RandomStringUtils.random(4, true, false) + " " +
                                RandomStringUtils.random(2, true, false) + ".",
                        // address 2
                        "",
                        // city
                        RandomStringUtils.random(7, true, false),
                        // state
                        RandomStringUtils.random(2, true, false).toUpperCase(),
                        // zip
                        RandomStringUtils.random(5, false, true),
                        // email
                        RandomStringUtils.random(8, true, true).toLowerCase() + "@cmail.club",
                        // phone
                        "(" + RandomStringUtils.random(3, false, true) + ") " +
                                RandomStringUtils.random(3, false, true) + "-" +
                                RandomStringUtils.random(4, false, true),
                        // social network
                        "Facebook",
                        // height
                        RandomStringUtils.random(2, false, true),
                        // weight
                        RandomStringUtils.random(1, false, true)}
                };
    }
    }

