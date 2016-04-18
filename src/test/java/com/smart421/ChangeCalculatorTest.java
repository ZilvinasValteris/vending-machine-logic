package com.smart421;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChangeCalculatorTest {

    private ChangeCalculator changeCalculator;

    @Before
    public void setup()
    {
        changeCalculator = new ChangeCalculator();
    }

    @Test
    public void correctDenominationsAreSet()
    {
        List<Coin> coins = (List<Coin>) changeCalculator.getOptimalChangeFor(300);
        List<Integer> denominations = new ArrayList<Integer>();

        for (Coin coin: coins)
        {
            denominations.add(coin.getDenomination());
        }

        List<Integer> expectedDenominations = Arrays.asList(100, 50, 20, 10, 5, 2, 1);
        assertEquals(expectedDenominations, denominations);
    }


    @Test
    public void returnsCoinCollectionWith3CoinsOf100()
    {
        List<Coin> coins = (List<Coin>) changeCalculator.getOptimalChangeFor(300);
        List<Integer> coinCount = new ArrayList<Integer>();

        for (Coin coin: coins)
        {
            coinCount.add(coin.getCount());
        }

        List<Integer> expectedCoinCount = Arrays.asList(3, 0, 0, 0, 0, 0, 0);
        assertEquals(expectedCoinCount, coinCount);
    }

    @Test
    public void returnsCoinCollectionWith3CoinsOf100And1CoinOf1()
    {
        List<Coin> coins = (List<Coin>) changeCalculator.getOptimalChangeFor(301);
        List<Integer> coinCount = new ArrayList<Integer>();

        for (Coin coin: coins)
        {
            coinCount.add(coin.getCount());
        }

        List<Integer> expectedCoinCount = Arrays.asList(3, 0, 0, 0, 0, 0, 1);
        assertEquals(expectedCoinCount, coinCount);
    }

    @Test
    public void returnsCoinCollectionWith3CoinsOf100And1CoinOf5And1CoinOf1()
    {
        List<Coin> coins = (List<Coin>) changeCalculator.getOptimalChangeFor(306);
        List<Integer> coinCount = new ArrayList<Integer>();

        for (Coin coin: coins)
        {
            coinCount.add(coin.getCount());
        }

        List<Integer> expectedCoinCount = Arrays.asList(3, 0, 0, 0, 1, 0, 1);
        assertEquals(expectedCoinCount, coinCount);
    }

    @Test
    public void returnsCoinCollectionWithOneOfEachPossibleCoinDenominations()
    {
        List<Coin> coins = (List<Coin>) changeCalculator.getOptimalChangeFor(188);
        List<Integer> coinCount = new ArrayList<Integer>();

        for (Coin coin: coins)
        {
            coinCount.add(coin.getCount());
        }

        List<Integer> expectedCoinCount = Arrays.asList(1, 1, 1, 1, 1, 1, 1);
        assertEquals(expectedCoinCount, coinCount);
    }

    @Test
    public void returnsCoinCollectionWithOneOfTopFiveHighestCoinsTwoCoinsOfTwoAndZeroCoinsOfOne()
    {
        List<Coin> coins = (List<Coin>) changeCalculator.getOptimalChangeFor(189);
        List<Integer> coinCount = new ArrayList<Integer>();

        for (Coin coin: coins)
        {
            coinCount.add(coin.getCount());
        }

        List<Integer> expectedCoinCount = Arrays.asList(1, 1, 1, 1, 1, 2, 0);
        assertEquals(expectedCoinCount, coinCount);
    }

    @Test
    public void returnsCoinCollectionWithZeroCoinsOfEachDenomination()
    {
        List<Coin> coins = (List<Coin>) changeCalculator.getOptimalChangeFor(0);
        List<Integer> coinCount = new ArrayList<Integer>();

        for (Coin coin: coins)
        {
            coinCount.add(coin.getCount());
        }

        List<Integer> expectedCoinCount = Arrays.asList(0, 0, 0, 0, 0, 0, 0);
        assertEquals(expectedCoinCount, coinCount);
    }

}
