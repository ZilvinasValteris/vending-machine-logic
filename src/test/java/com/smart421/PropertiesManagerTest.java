package com.smart421;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PropertiesManagerTest {

    private PropertiesManager propertiesManager;

    @Before
    public void setup()
    {
        propertiesManager = new PropertiesManager("test.properties");
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
}
