package com.smart421;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ChangeCalculator {

    private List<Integer> COIN_VALUES = Arrays.asList(100, 50, 20, 10, 5, 2, 1);

    public Collection<Coin> getOptimalChangeFor(int pence)
    {
        Coin coin;
        int denominationCount;
        int penceRemaining = pence;
        int coinsTotal = 0;
        Collection<Coin> coins = new ArrayList<Coin>();

        for(int i = 0; i < COIN_VALUES.size(); i++)
        {
            denominationCount = penceRemaining/COIN_VALUES.get(i);
//            coinsTotal = coinsTotal + denominationCount; // probably not even needed for part one
            penceRemaining = penceRemaining - denominationCount * COIN_VALUES.get(i);

            coin = new Coin();
            coin.setDenomination(COIN_VALUES.get(i));
            coin.setCount(denominationCount);
            coins.add(coin);
        }

        return coins;
    }


    // What is the standard practice reading from the properties file? Do I need a separate service/class for that?
    // Shall use in-class map of coins just to nail the logic first?
    // TODO: throw InsufficientCoinageException
    public Collection<Coin> getChangeFor(int pence)
    {
        PropertiesManager propertiesManager = new PropertiesManager("coin-inventory.properties");
        return null;
    }





}
