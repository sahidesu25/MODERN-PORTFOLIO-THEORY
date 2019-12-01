package com.personalcapital.portfolio;

import com.personalcapital.portfolio.main.PortfolioStatisticsService;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.junit.Test;

import java.text.NumberFormat;
import java.util.Locale;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class PortfolioStatisticsServiceTest {


    @Test
    public void testPortfolioStatisticsService_WhenMontecarloObjectIsNull()
    {
        PortfolioStatisticsService portfolioStatisticsService = new PortfolioStatisticsService();
        try
        {
            portfolioStatisticsService.runPortFolioService(PortfolioType.AGGRESSIVE,null,new PerformanceStatisticsRunner(),100000,20,0.035);

            fail("The Monte Carlo Simulator  object can't be null exception message is not thrown");
        }
        catch (IllegalArgumentException e)
        {
            String expMessage="The Monte Carlo Simulator  object can't be null";
            assertEquals(expMessage,e.getMessage());
        }
        try
        {
            portfolioStatisticsService.runPortFolioService(PortfolioType.VERYCONSERVATIVE,null,new PerformanceStatisticsRunner(),100000,20,0.035);

            fail("The Monte Carlo Simulator  object can't be null exception message is not thrown");
        }
        catch (IllegalArgumentException e)
        {
            String expMessage="The Monte Carlo Simulator  object can't be null";
            assertEquals(expMessage,e.getMessage());
        }

    }
    @Test
    public void testPortfolioStatisticsService_WhenPortFolioTypeIsNull()
    {
        PortfolioStatisticsService portfolioStatisticsService = new PortfolioStatisticsService();
       try{
           portfolioStatisticsService.runPortFolioService(null,new MonteCarloSimulator(10),new PerformanceStatisticsRunner(),100000,20,0.035);
           fail("The Portfolio type can't be null Exception Message is not thrown");
       }catch (IllegalArgumentException e)
       {
           String expectionMessage = "The Portfolio type can't be null";
           assertEquals(expectionMessage,e.getMessage());
       }

    }

    @Test
    public void testPortfolioStatisticsService_WhenPerformanceStatisticsRunnerIsNull()
    {
        PortfolioStatisticsService portfolioStatisticsService = new PortfolioStatisticsService();
        String expMessage = "The Performance Stats runner object can't be null";
        try{
            portfolioStatisticsService.runPortFolioService(PortfolioType.AGGRESSIVE,new MonteCarloSimulator(10),null,100000,20,0.035);

            fail(expMessage+" is not thrown");
        }catch (IllegalArgumentException e)
        {
            assertEquals(expMessage,e.getMessage());
        }
        try{
            portfolioStatisticsService.runPortFolioService(PortfolioType.VERYCONSERVATIVE,new MonteCarloSimulator(10),null,100000,20,0.035);

            fail(expMessage+" is not thrown");
        }catch (IllegalArgumentException e)
        {
            assertEquals(expMessage,e.getMessage());
        }

    }


    @Test
    public void testPortFolioService_WhenIntialInvestmentIsNotGreaterThanZero()
    {
        String expMessage = "Initial Investment should be greater then zero";
        PortfolioStatisticsService portfolioStatisticsService = new PortfolioStatisticsService();
        try{
            portfolioStatisticsService.runPortFolioService(PortfolioType.AGGRESSIVE,new MonteCarloSimulator(10),new PerformanceStatisticsRunner(),0,20,0.035);

            fail(expMessage+" is not thrown");
        }catch (IllegalArgumentException e)
        {
            assertEquals(expMessage,e.getMessage());
        }
        try{
            portfolioStatisticsService.runPortFolioService(PortfolioType.VERYCONSERVATIVE,new MonteCarloSimulator(10),new PerformanceStatisticsRunner(),-90,20,0.035);
            fail(expMessage+" is not thrown");
        }catch (IllegalArgumentException e)
        {
            assertEquals(expMessage,e.getMessage());
        }
    }

    @Test
    public void testPortfolioStatisticsService_WhenNumOfYearsIsLessThenZero()
    {
        PortfolioStatisticsService portfolioStatisticsService = new PortfolioStatisticsService();
        String expMessage = "Num of years should be greater than zero";
        try{
            portfolioStatisticsService.runPortFolioService(PortfolioType.AGGRESSIVE,new MonteCarloSimulator(10),new PerformanceStatisticsRunner(),100000,0,0.035);

            fail(expMessage+" is not thrown");
        }catch (IllegalArgumentException e)
        {
            assertEquals(expMessage,e.getMessage());
        }
        try{
            portfolioStatisticsService.runPortFolioService(PortfolioType.VERYCONSERVATIVE,new MonteCarloSimulator(10),new PerformanceStatisticsRunner(),100000,-9,0.035);
            fail(expMessage+" is not thrown");
        }catch (IllegalArgumentException e)
        {
            assertEquals(expMessage,e.getMessage());
        }

    }

    @Test
    public void testPortfolioStatisticsService_WhenEmptySimulatedValuesArePassed()
    {
        //creating mock montecarlo simulator object
        MonteCarloSimulator mockMonteCarloSimulator= mock(MonteCarloSimulator.class);
        //creating simulated results
        double [] simulatedValues = {};
        PortfolioStatisticsService portfolioStatisticsService = new PortfolioStatisticsService();
        //mocking simulated Values
        when(mockMonteCarloSimulator.runMonteCarloSimulation(PortfolioType.AGGRESSIVE.getStdDev(),PortfolioType.AGGRESSIVE.getMean(),100000,0.035,20)).thenReturn(simulatedValues);
        when(mockMonteCarloSimulator.runMonteCarloSimulation(PortfolioType.VERYCONSERVATIVE.getStdDev(),PortfolioType.VERYCONSERVATIVE.getMean(),100000,0.035,20)).thenReturn(simulatedValues);

        try {
             portfolioStatisticsService.runPortFolioService(PortfolioType.AGGRESSIVE, mockMonteCarloSimulator, new PerformanceStatisticsRunner(),100000,20,0.035);

            fail("The length of simulated values can't be  zero, Exception message is not thrown");

        }catch (IllegalArgumentException e)
        {
            String expMessage = "The length of simulated values can't be  zero";
            assertEquals(expMessage,e.getMessage());
        }
        try {
            portfolioStatisticsService.runPortFolioService(PortfolioType.VERYCONSERVATIVE, mockMonteCarloSimulator, new PerformanceStatisticsRunner(),100000,20,0.035);

            fail("The length of simulated values can't be  zero, Exception message is not thrown");

        }catch (IllegalArgumentException e)
        {
            String expMessage = "The length of simulated values can't be  zero";
            assertEquals(expMessage,e.getMessage());
        }

    }
    @Test
    public void testPortfolioStatisticsService_WhenNullSimulatedValuesArePassed()
    {
        //creating mock montecarlo simulator object
        MonteCarloSimulator mockMonteCarloSimulator= mock(MonteCarloSimulator.class);
        //creating simulated results
        double [] simulatedValues =null;
        PortfolioStatisticsService portfolioStatisticsService = new PortfolioStatisticsService();
        //mocking simulated Values
        when(mockMonteCarloSimulator.runMonteCarloSimulation(PortfolioType.AGGRESSIVE.getStdDev(),PortfolioType.AGGRESSIVE.getMean(),100000,0.035,20)).thenReturn(simulatedValues);
        when(mockMonteCarloSimulator.runMonteCarloSimulation(PortfolioType.VERYCONSERVATIVE.getStdDev(),PortfolioType.VERYCONSERVATIVE.getMean(),100000,0.035,20)).thenReturn(simulatedValues);

        try {
            portfolioStatisticsService.runPortFolioService(PortfolioType.AGGRESSIVE, mockMonteCarloSimulator, new PerformanceStatisticsRunner(),100000,20,0.035);

            fail("The simulated Values array can't be null, Exception message is not thrown");

        }catch (IllegalArgumentException e)
        {
            String expMessage = "The simulated Values array can't be null";
            assertEquals(expMessage,e.getMessage());
        }
        try {
            portfolioStatisticsService.runPortFolioService(PortfolioType.VERYCONSERVATIVE, mockMonteCarloSimulator, new PerformanceStatisticsRunner(),100000,20,0.035);

            fail("The simulated Values array can't be null, Exception message is not thrown");

        }catch (IllegalArgumentException e)
        {
            String expMessage = "The simulated Values array can't be null";
            assertEquals(expMessage,e.getMessage());
        }

    }

    @Test
    public void testPortfolioStatisticsService()
    {
        //creating mock montecarlo simulator object
        MonteCarloSimulator mockMonteCarloSimulator= mock(MonteCarloSimulator.class);
        //creating simulated results
        double [] simulatedValues = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        double medianTest = new Median().evaluate(simulatedValues);
        double worstCaseTest = StatUtils.percentile(simulatedValues,10);
        double bestCaseTest = StatUtils.percentile(simulatedValues,90);
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

        PortfolioStatisticsService portfolioStatisticsService = new PortfolioStatisticsService();
        //mocking simulated Values
        when(mockMonteCarloSimulator.runMonteCarloSimulation(PortfolioType.AGGRESSIVE.getStdDev(),PortfolioType.AGGRESSIVE.getMean(),100000,0.035,20)).thenReturn(simulatedValues);
        when(mockMonteCarloSimulator.runMonteCarloSimulation(PortfolioType.VERYCONSERVATIVE.getStdDev(),PortfolioType.VERYCONSERVATIVE.getMean(),100000,0.035,20)).thenReturn(simulatedValues);

        PortfolioPerformanceStatsResult agressiveRes = portfolioStatisticsService.runPortFolioService(PortfolioType.AGGRESSIVE,mockMonteCarloSimulator,new PerformanceStatisticsRunner(),100000,20,0.035);
        assertEquals(agressiveRes.getMedian(),currencyFormat.format(medianTest));
        assertEquals(agressiveRes.getBestCase(),currencyFormat.format(bestCaseTest));
        assertEquals(agressiveRes.getWorstCase(),currencyFormat.format(worstCaseTest));

        PortfolioPerformanceStatsResult veryConservativeres = portfolioStatisticsService.runPortFolioService(PortfolioType.VERYCONSERVATIVE,mockMonteCarloSimulator,new PerformanceStatisticsRunner(),100000,20,0.035);
        assertEquals(veryConservativeres.getMedian(),currencyFormat.format(medianTest));
        assertEquals(veryConservativeres.getBestCase(),currencyFormat.format(bestCaseTest));
        assertEquals(veryConservativeres.getWorstCase(),currencyFormat.format(worstCaseTest));


    }

}
