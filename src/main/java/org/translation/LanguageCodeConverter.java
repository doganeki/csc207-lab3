package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// import java.util.HashMap;
// import java.util.Map;

/**
 * This class provides the service of converting language codes to their names.
 */
public class LanguageCodeConverter {

    // TO-DO Task: pick appropriate instance variables to store the data necessary for this class
    private static final int INDEX_2 = 2;
    private static final int INDEX_3 = 3;

    private final List<String> languages = new ArrayList<>();
    private final List<String> countryCodes = new ArrayList<>();

    /**
     * Default constructor which will load the language codes from "language-codes.txt"
     * in the resources folder.
     */
    public LanguageCodeConverter() {
        this("language-codes.txt");
    }

    /**
     * Overloaded constructor which allows us to specify the filename to load the language code data from.
     * @param filename the name of the file in the resources folder to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public LanguageCodeConverter(String filename) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass()
                    .getClassLoader().getResource(filename).toURI()));

            // TO-DO Task: use lines to populate the instance variable
            //           tip: you might find it convenient to create an iterator using lines.iterator()
            for (int i = 1; i < lines.size(); i++) {
                int length = lines.get(i).length();

                countryCodes.add(lines.get(i).substring(length - INDEX_2, length));
                languages.add(lines.get(i).substring(0, length - INDEX_3));
            }

            // TO-DO Checkstyle: '}' on next line should be alone on a line.
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * Returns the name of the language for the given language code.
     * @param code the language code
     * @return the name of the language corresponding to the code
     */
    public String fromLanguageCode(String code) {
        // TO-DO Task: update this code to use your instance variable to return the correct value
        // return code;
        for (int i = 0; i < countryCodes.size(); i++) {
            if (code.equals(countryCodes.get(i))) {
                return languages.get(i);
            }
        }

        return "Language not found";
    }

    /**
     * Returns the code of the language for the given language name.
     * @param language the name of the language
     * @return the 2-letter code of the language
     */
    public String fromLanguage(String language) {
        // TO-DO Task: update this code to use your instance variable to return the correct value
        // return language;
        for (int i = 0; i < languages.size(); i++) {
            if (language.equals(languages.get(i))) {
                return countryCodes.get(i);
            }
        }

        return "Language not found";
    }

    /**
     * Returns how many languages are included in this code converter.
     * @return how many languages are included in this code converter.
     */
    public int getNumLanguages() {
        // TO-DO Task: update this code to use your instance variable to return the correct value
        // return 0;
        return countryCodes.size();
    }
}
