package com.smart421;

import com.smart421.customexceptions.InsufficientCoinageException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChangeCalculatorTest {

    private ChangeCalculator changeCalculator;

    @Before
    public void setup()
    {
        changeCalculator = new ChangeCalculator("src\\test\\resources\\test.properties");
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


    // This test (code design) is very brittle since it relies on the values in the main properties file that can change.
    // I should at least be able to use test.properties file for tests instead of the real one
    @Test
    public void getChangeForTest() throws IOException, InsufficientCoinageException {
        List<Coin> coins = (List<Coin>) changeCalculator.getChangeFor(1200);
        List<Integer> coinCount = new ArrayList<Integer>();

//        Map<Integer, Integer> coinsAvailable = new HashMap<Integer, Integer>();
//        coinsAvailable.put(100, 11);
//        coinsAvailable.put(50, 24);
//        coinsAvailable.put(20, 0);
//        coinsAvailable.put(10, 99);
//        coinsAvailable.put(5, 200);
//        coinsAvailable.put(2, 11);
//        coinsAvailable.put(1, 23);
//
//        PropertiesManager propertiesManager = mock(PropertiesManager.class);
//        when(propertiesManager.loadProperties()).thenReturn(coinsAvailable);

        for (Coin coin: coins)
        {
            coinCount.add(coin.getCount());
        }

        List<Integer> expectedCoinCount = Arrays.asList(11, 2, 0, 0, 0, 0, 0);
        assertEquals(expectedCoinCount, coinCount);
    }

    @Test(expected = InsufficientCoinageException.class)
    public void getChangeForThrowsInsufficientCoinageException() throws IOException, InsufficientCoinageException {
        changeCalculator.getChangeFor(10000);
    }

}
