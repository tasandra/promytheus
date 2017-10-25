package data;

import org.testng.annotations.DataProvider;
public class DataproviderClass {
    @DataProvider(name = "SearchProvider")
    public static Object[][] getDataFromDataProvider() {
        return new Object[][]{
                {"0", "Bill", "Jn", "Forward", "12/12/2012", "Moscow", "Kazakhstan", "111 Main St.", "Apr. 223",
                        "San Francisco", "CA", "92003", "kusiwa@cmail.club", "(123) 456-7891", "Linkedin", "123", "999"},
                {"1", "Nancy", "", "Black", "01/03/1999", "San Francisco", "United Kingdom", "11234 Blossom Way.", "",
                        "New York", "New York", "01208", "new@cmail.club", "(444) 444-4444", "Google", "999", "999.99"},
                {"2", "Alexander", "", "Ivanov", "12/30/2012", "Vladivostok", "Russia", "90905 El Camino Real", "",
                        "Palo Alto", "CA", "940404", "alexander@cmail.club", "(999) 999-9999", "Facebook", "77", "9"}
        };
    }
}

