package com.smart421;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ChangeCalculator {

    public Collection<Coin> getOptimalChangeFor(int pence)
    {
        Coin coin = new Coin();
        List<Integer> coinValues = Arrays.asList(100, 50, 20, 10, 5, 2, 1);
        int denomination = 0;
        int numOfCoins;
        int penceRemaining = pence;

        for(int i = 0; i < coinValues.size(); i++)
        {
            numOfCoins = penceRemaining/coinValues.get(i);
            denomination = denomination + numOfCoins;

            penceRemaining = penceRemaining - numOfCoins * coinValues.get(i);

        }

        coin.setDenomination(denomination);
        Collection<Coin> coins = new ArrayList<Coin>();
        coins.add(coin);

        return coins;
    }

}
