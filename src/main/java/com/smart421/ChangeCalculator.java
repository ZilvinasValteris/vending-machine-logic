package com.smart421;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ChangeCalculator {

    public Collection<Coin> getOptimalChangeFor(int pence)
    {
        Coin coin;
        List<Integer> coinValues = Arrays.asList(100, 50, 20, 10, 5, 2, 1);
        int denominationCount;
        int penceRemaining = pence;
        int coinsTotal = 0;
        Collection<Coin> coins = new ArrayList<Coin>();

        for(int i = 0; i < coinValues.size(); i++)
        {
            denominationCount = penceRemaining/coinValues.get(i);
//            coinsTotal = coinsTotal + denominationCount; // probably not even needed for part one
            penceRemaining = penceRemaining - denominationCount * coinValues.get(i);

            coin = new Coin();
            coin.setDenomination(coinValues.get(i));
            coin.setCount(denominationCount);
            coins.add(coin);
        }

        return coins;
    }

}
