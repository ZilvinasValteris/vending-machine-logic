package com.smart421;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PropertiesManager {

    private Map<Integer, Integer> coinsAvailable;
    private InputStream inputStream;
    private FileOutputStream fileOutputStream;
    private Properties properties;

    // Should potentially live in Enum class or just some one common place
    // The problem is that in ChangeCalculator class these coin denominations are needed as ints
    private List<String> COIN_VALUES = Arrays.asList("100", "50", "20", "10", "5", "2", "1");
    private String propertiesFileName;

    public PropertiesManager(String propertiesFileName) {
        this.propertiesFileName = propertiesFileName;
        this.properties = new Properties();
    }

    public Map<Integer, Integer> loadProperties() throws IOException {

        coinsAvailable = new HashMap<Integer, Integer>();
        inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);

        if(inputStream != null)
        {
            properties.load(inputStream);
        }
        else
        {
            throw new FileNotFoundException("Properties file " + propertiesFileName + " not found");
        }

        try
        {
            for (String COIN_VALUE : COIN_VALUES)
            {
                Integer key = Integer.parseInt(COIN_VALUE);
                Integer value = Integer.parseInt(properties.getProperty(COIN_VALUE));
                coinsAvailable.put(key, value);
            }
        }
        catch (NumberFormatException e)
        {
            throw new NumberFormatException("Only integers expected in the property file!");
        }
        finally
        {
            inputStream.close();
        }

        return coinsAvailable;
    }

    public void updateProperties(Map<String, String> coinsRemaining) throws IOException {

        // Shall I deal with some exceptions here?
        fileOutputStream = new FileOutputStream(propertiesFileName);

        for(int i = 0; i < coinsRemaining.size(); i++)
        {
            String key = COIN_VALUES.get(i);
            String value = coinsRemaining.get(key);
            properties.setProperty(key, value);
        }

        properties.store(fileOutputStream, null);
        fileOutputStream.close();
    }

}
