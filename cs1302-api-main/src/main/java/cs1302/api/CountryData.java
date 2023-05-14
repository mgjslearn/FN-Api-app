package cs1302.api;

/**
 * This class contains the variables and getter methods to retrieve data
 * about the capital, nativeName, and nationality name of countries in the API.
 */
public class CountryData {
    private String name;
    private String capital;
    private String nativeName;
    private String demonym;

    /**
     * This method returns the value of the name of the country.
     * @return name the name of the country.
     */
    public String getName() {
        return name;
    }

     /**
     * This method gets and returns the capital city of the country.
     * @return capital the capital of the country.
     */
    public String getCapital() {
        return capital;
    }

     /**
     * This method returns the native language name for the country.
     * @return nativeName the name in the native language of the country.
     */
    public String getNativeName() {
        return nativeName;
    }

     /**
     * This method returns the demonym/nationality name of those who live in the country.
     * @return demonym the nationality of those in the country.
     */
    public String getDemonym() {
        return demonym;
    }
}
