package com.smart421;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChangeCalculatorTest {

    private ChangeCalculator changeCalculator;
    private int denomination;

    @Before
    public void setup()
    {
        changeCalculator = new ChangeCalculator();
        denomination = 0;
    }

    @Test
    public void returnsCoinObjectWithDenominationOf3When300PassedIn()
    {
        List<Coin> coins = (List<Coin>) changeCalculator.getOptimalChangeFor(300);

        denomination = coins.get(0).getDenomination();

        assertEquals(3, denomination);
    }

    @Test
    public void returnsCoinObjectWithDenominationOf4When301PassedIn()
    {
        List<Coin> coins = (List<Coin>) changeCalculator.getOptimalChangeFor(301);

        denomination = coins.get(0).getDenomination();

        assertEquals(4, denomination);
    }

    @Test
    public void returnsCoinObjectWithDenominationOf5When306PassedIn()
    {
        List<Coin> coins = (List<Coin>) changeCalculator.getOptimalChangeFor(306);

        denomination = coins.get(0).getDenomination();

        assertEquals(5, denomination);
    }

    @Test
    public void returnsCoinObjectWithDenominationOf7When188PassedIn()
    {
        List<Coin> coins = (List<Coin>) changeCalculator.getOptimalChangeFor(188);

        denomination = coins.get(0).getDenomination();

        assertEquals(7, denomination);
    }

    @Test
    public void returnsCoinObjectWithDenominationOf7When189PassedIn()
    {
        List<Coin> coins = (List<Coin>) changeCalculator.getOptimalChangeFor(189);

        denomination = coins.get(0).getDenomination();

        assertEquals(7, denomination);
    }

    @Test
    public void returnsCoinObjectWithDenominationOf0When0PassedIn()
    {
        List<Coin> coins = (List<Coin>) changeCalculator.getOptimalChangeFor(0);

        denomination = coins.get(0).getDenomination();

        assertEquals(0, denomination);
    }


}
