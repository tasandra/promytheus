package data;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;
public class DataproviderClass {
    @DataProvider(name = "SearchProvider")
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
                        // plece of birth
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
                {"1", "Nancy", "", "Black", "01/03/1999", "San Francisco", "United Kingdom", "11234 Blossom Way.", "",
                        "New York", "New York", "01208", "new@cmail.club", "(444) 444-4444", "Google", "999", "999.99"},
                {"2", "Alexander", "", "Ivanov", "12/30/2012", "Vladivostok", "Russia", "90905 El Camino Real", "",
                        "Palo Alto", "CA", "940404", "alexander@cmail.club", "(999) 999-9999", "Facebook", "77", "9"}
        };
    }
}

