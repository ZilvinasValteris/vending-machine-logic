package com.smart421;

import java.io.IOException;
import java.util.*;

public class ChangeCalculator {

    private List<Integer> COIN_VALUES = Arrays.asList(100, 50, 20, 10, 5, 2, 1);

    public Collection<Coin> getOptimalChangeFor(int pence)
    {
        Coin coin;
        int denominationCount;
        int penceRemaining = pence;
        List<Coin> coins = new ArrayList<Coin>();

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


    // What is the standard practice reading from the properties file? Do I need a separate service/class for that?
    // Shall use in-class map of coins just to nail the logic first?
    // TODO: throw InsufficientCoinageException
    public Collection<Coin> getChangeFor(int pence) throws IOException, InsufficientCoinageException
    {

        PropertiesManager propertiesManager = new PropertiesManager("coin-inventory.properties");

        Map<Integer, Integer> coinsAvailable = propertiesManager.loadProperties();


        // Filthy copy from the method above for now!!!

        Coin coin;
        int denominationCount;
        int penceRemaining = pence;
        List<Coin> coins = new ArrayList<Coin>();

        for (Integer COIN_VALUE : COIN_VALUES)
        {
            denominationCount = penceRemaining / COIN_VALUE;
            if (denominationCount > coinsAvailable.get(COIN_VALUE)) {
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
