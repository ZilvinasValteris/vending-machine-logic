package com.smart421;

import com.smart421.customexceptions.InsufficientCoinageException;

import java.io.IOException;
import java.util.*;

public class ChangeCalculator {

    private List<Integer> COIN_VALUES = Arrays.asList(100, 50, 20, 10, 5, 2, 1);
    private List<String> COIN_VALUES_AS_STRINGS = Arrays.asList("100", "50", "20", "10", "5", "2", "1");
    private String PROPERTIES_FILE_NAME = "src\\main\\resources\\coin-inventory.properties";
    private Coin coin;
    private int denominationCount;
    private int penceRemaining;
    private List<Coin> coins;

    public ChangeCalculator(String PROPERTIES_FILE_NAME) {
        this.PROPERTIES_FILE_NAME = PROPERTIES_FILE_NAME;
    }

    public Collection<Coin> getOptimalChangeFor(int pence)
    {
        penceRemaining = pence;
        coins = new ArrayList<Coin>();

        for (Integer COIN_VALUE : COIN_VALUES)
        {
            denominationCount = penceRemaining / COIN_VALUE;
            penceRemaining = penceRemaining - denominationCount * COIN_VALUE;

            coin = new Coin();
            coin.setDenomination(COIN_VALUE);
            coin.setCount(denominationCount);
            coins.add(coin);
        }

        return coins;
    }

    public Collection<Coin> getChangeFor(int pence) throws IOException, InsufficientCoinageException
    {
        PropertiesManager propertiesManager = new PropertiesManager(PROPERTIES_FILE_NAME, COIN_VALUES_AS_STRINGS);
        Map<Integer, Integer> coinsAvailable = propertiesManager.loadProperties();
        penceRemaining = pence;
        coins = new ArrayList<Coin>();

        for (Integer COIN_VALUE : COIN_VALUES)
        {
            denominationCount = penceRemaining / COIN_VALUE;
            if (denominationCount > coinsAvailable.get(COIN_VALUE))
            {
                denominationCount = coinsAvailable.get(COIN_VALUE);
            }
            penceRemaining = penceRemaining - denominationCount * COIN_VALUE;

            coin = new Coin();
            coin.setDenomination(COIN_VALUE);
            coin.setCount(denominationCount);
            coins.add(coin);
        }

        if(penceRemaining > 0)
        {
            throw new InsufficientCoinageException("Not enough coins to finish the operation!");
        }

        Map<String, String> coinsRemaining = new HashMap<String, String>();

        for(int i = 0; i < COIN_VALUES.size(); i++)
        {
            coinsRemaining.put(String.valueOf(COIN_VALUES.get(i)), String.valueOf(coins.get(i).getCount()));
        }

        propertiesManager.updateProperties(coinsRemaining);

        return coins;
    }

}
