package data;

import org.testng.annotations.DataProvider;
public class DataproviderClass {
    @DataProvider(name = "SearchProvider")
    public static Object[][] getDataFromDataProvider() {
        return new Object[][]{
                {"0", "Bill", "Jn", "Forward", "12122012", "Moscow", "Kazakhstan", "111 Main St.", "Apr. 223",
                        "San Francisco", "CA", "92003", "kusiwa@cmail.club", "1234567891", "Linkedin", "123", "999"},
                {"1", "Nancy", "", "Black", "01031999", "San Francisco", "Aland Islands", "11234 Blossom Way.", "",
                        "New York", "New York", "01208", "new@cmail.club", "4444444444", "Google", "999", "999.99"},
                {"2", "Alexander", "", "Ivanov", "12302012", "Vladivostok", "Russia", "90905 El Camino Real", "",
                        "Palo Alto", "CA", "940404", "alexander@cmail.club", "9999999999", "Facebook", "77", "9"}
        };
    }
}

