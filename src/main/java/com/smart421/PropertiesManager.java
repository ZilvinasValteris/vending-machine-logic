package com.smart421;

import java.io.*;
import java.util.*;

public class PropertiesManager {

    private Properties properties;
    private List<String> COIN_VALUES;
    private String propertiesFileName;

    PropertiesManager(String propertiesFileName, List<String> COIN_VALUES_AS_STRINGS)
    {
        this.propertiesFileName = propertiesFileName;
        this.properties = new Properties();
        this.COIN_VALUES = COIN_VALUES_AS_STRINGS;
    }

    public Map<Integer, Integer> loadProperties() throws IOException
    {
        Map<Integer, Integer> coinsAvailable = new HashMap<Integer, Integer>();
        FileInputStream inputStream = new FileInputStream(propertiesFileName);

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

        inputStream.close();

        return coinsAvailable;
    }

    public void updateProperties(Map<String, String> coinsRemaining) throws IOException
    {
        FileOutputStream outputStream = new FileOutputStream(propertiesFileName);

        for(int i = 0; i < coinsRemaining.size(); i++)
        {
            String key = COIN_VALUES.get(i);
            String value = coinsRemaining.get(key);
            System.out.println("key: " + key + ", value: " + value);
            properties.setProperty(key, value);
        }

        if(outputStream != null)
        {
            properties.store(outputStream, null);
        }
        else
        {
            throw new FileNotFoundException("Properties file " + propertiesFileName + " not found");
        }

        outputStream.close();
    }

}
