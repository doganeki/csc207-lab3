package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// import java.util.HashMap;
// import java.util.Map;

// TO-DO CheckStyle: Wrong lexicographical order for 'java.util.HashMap' import (remove this comment once resolved)

/**
 * This class provides the service of converting country codes to their names.
 */
public class CountryCodeConverter {

    // TO-DO Task: pick appropriate instance variable(s) to store the data necessary for this class
    private static final int INDEX_4 = 4;
    private static final int INDEX_7 = 7;
    private static final int INDEX_11 = 11;

    private final List<String> countries = new ArrayList<>();
    private final List<String> countryCodes = new ArrayList<>();

    /**
     * Default constructor which will load the country codes from "country-codes.txt"
     * in the resources folder.
     */
    public CountryCodeConverter() {
        this("country-codes.txt");
    }

    /**
     * Overloaded constructor which allows us to specify the filename to load the country code data from.
     * @param filename the name of the file in the resources folder to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public CountryCodeConverter(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass()
                    .getClassLoader().getResource(filename).toURI()));

            // TO-DO Task: use lines to populate the instance variable(s)
            for (int i = 1; i < lines.size(); i++) {
                int length = lines.get(i).length();

                countryCodes.add(lines.get(i).substring(length - INDEX_7, length - INDEX_4));
                countries.add(lines.get(i).substring(0, length - INDEX_11));
            }
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * Returns the name of the country for the given country code.
     * @param code the 3-letter code of the country
     * @return the name of the country corresponding to the code
     */
    public String fromCountryCode(String code) {
        // TO-DO Task: update this code to use an instance variable to return the correct value
        // return code;
        for (int i = 0; i < countryCodes.size(); i++) {
            if (code.toUpperCase().equals(countryCodes.get(i))) {
                return countries.get(i);
            }
        }

        return "Country not found";
    }

    /**
     * Returns the code of the country for the given country name.
     * @param country the name of the country
     * @return the 3-letter code of the country
     */
    public String fromCountry(String country) {
        // TO-DO Task: update this code to use an instance variable to return the correct value
        // return country;
        for (int i = 0; i < countries.size(); i++) {
            if (country.toUpperCase().equals(countries.get(i))) {
                return countryCodes.get(i);
            }
        }

        return "Country not found";
    }

    /**
     * Returns how many countries are included in this code converter.
     * @return how many countries are included in this code converter.
     */
    public int getNumCountries() {
        // TO-DO Task: update this code to use an instance variable to return the correct value
        // return 0;
        return countryCodes.size();
    }
}
