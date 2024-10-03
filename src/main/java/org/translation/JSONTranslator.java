package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * An implementation of the Translator interface which reads in the translation
 * data from a JSON file. The data is read in once each time an instance of this class is constructed.
 */
public class JSONTranslator implements Translator {

    // TO-DO Task: pick appropriate instance variables for this class
    private final List<String> countries = new ArrayList<>();
    private final JSONArray translations = new JSONArray();

    /**
     * Constructs a JSONTranslator using data from the sample.json resources file.
     */
    public JSONTranslator() {
        this("sample.json");
    }

    /**
     * Constructs a JSONTranslator populated using data from the specified resources file.
     * @param filename the name of the file in resources to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public JSONTranslator(String filename) {
        // read the file to get the data to populate things...
        try {

            String jsonString = Files.readString(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));

            JSONArray jsonArray = new JSONArray(jsonString);

            // TO-DO Task: use the data in the jsonArray to populate your instance variables
            //            Note: this will likely be one of the most substantial pieces of code you write in this lab.
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                final String country = jsonObject.getString("alpha3");

                jsonObject.remove("alpha3");
                jsonObject.remove("alpha2");
                jsonObject.remove("id");

                countries.add(country);
                translations.put(jsonObject);
            }
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<String> getCountryLanguages(String country) {
        // TO-DO Task: return an appropriate list of language codes,
        //            but make sure there is no aliasing to a mutable object
        // return new ArrayList<>();
        List<String> languages = new ArrayList<>();
        JSONObject foo;

        int index = countries.indexOf(country);
        foo = translations.getJSONObject(index);
        Iterator<String> keys = foo.keys();

        while (keys.hasNext()) {
            languages.add(keys.next());
        }

        return languages;
    }

    @Override
    public List<String> getCountries() {
        // TO-DO Task: return an appropriate list of country codes,
        //            but make sure there is no aliasing to a mutable object
        // return new ArrayList<>();
        return countries;
    }

    @Override
    public String translate(String country, String language) {
        // TO-DO Task: complete this method using your instance variables as needed
        // return null;
        JSONObject foo;
        int index = countries.indexOf(country);
        foo = translations.getJSONObject(index);

        return foo.getString(language);
    }
}
