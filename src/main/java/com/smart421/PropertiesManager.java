package com.smart421;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PropertiesManager {

    private Map<Integer, Integer> coinsAvailable;
    private InputStream inputStream;

    // Should potentially live in Enum class or just some one common place
    // The problem is that in ChangeCalculator class these coin denominations are needed as ints
    private List<String> coinValues = Arrays.asList("100", "50", "20", "10", "5", "2", "1");
    private String propertiesFileName;

    public PropertiesManager(String propertiesFileName) {
        this.propertiesFileName = propertiesFileName;
    }

    public Map<Integer, Integer> loadProperties() throws IOException {

        coinsAvailable = new HashMap<Integer, Integer>();
        Properties properties = new Properties();
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
            for (int i = 0; i < coinValues.size(); i++)
            {
                Integer key = Integer.parseInt(coinValues.get(i));
                Integer value = Integer.parseInt(properties.getProperty(coinValues.get(i)));
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

    public void updateProperties()
    {

    }

}
