package com.smart421;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PropertiesManagerTest {

    private PropertiesManager propertiesManager;
    private List<String> COIN_VALUES_AS_STRINGS = Arrays.asList("100", "50", "20", "10", "5", "2", "1");

    @Before
    public void setup()
    {
        //TODO: Shall I just create properties file and delete it after the test instead?
        propertiesManager = new PropertiesManager("src\\test\\resources\\test.properties", COIN_VALUES_AS_STRINGS);
    }

    @Test
    public void mapWithPropertiesReturnedByLoadPropertiesMethod() throws IOException {
        Map<Integer, Integer> expectedCoinsAvailable = new HashMap<Integer, Integer>();
        expectedCoinsAvailable.put(100, 11);
        expectedCoinsAvailable.put(50, 24);
        expectedCoinsAvailable.put(20, 0);
        expectedCoinsAvailable.put(10, 99);
        expectedCoinsAvailable.put(5, 200);
        expectedCoinsAvailable.put(2, 11);
        expectedCoinsAvailable.put(1, 23);

        Map<Integer, Integer> actualCoinsAvailable = propertiesManager.loadProperties();

        assertEquals(expectedCoinsAvailable, actualCoinsAvailable);
    }

    // You'll need to read values from properties file after the update to test this properly
    @Test
    public void updatePropertiesTest() throws IOException {
        Map<String, String> expectedCoinsAvailable = new HashMap<String, String>();
        expectedCoinsAvailable.put("100", "11");
        expectedCoinsAvailable.put("50", "24");
        expectedCoinsAvailable.put("20", "0");
        expectedCoinsAvailable.put("10", "99");
        expectedCoinsAvailable.put("5", "200");
        expectedCoinsAvailable.put("2", "1");
        expectedCoinsAvailable.put("1", "0");

        propertiesManager.updateProperties(expectedCoinsAvailable);
    }
}
