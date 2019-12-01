package com.personalcapital.portfolio.main;

import com.personalcapital.portfolio.MonteCarloSimulator;
import com.personalcapital.portfolio.PerformanceStatisticsRunner;
import com.personalcapital.portfolio.PortfolioPerformanceStatsResult;
import com.personalcapital.portfolio.PortfolioType;

import static com.personalcapital.portfolio.constants.Constants.INFLATION_RATE;
import static com.personalcapital.portfolio.constants.Constants.INITIAL_INVESTMENT;
import static com.personalcapital.portfolio.constants.Constants.NUM_OF_YEARS;
import static com.personalcapital.portfolio.constants.Constants.NUM_OF_SIMULATIONS;



public class PortfolioStatisticsService {

    /**
     * Function which runs monte carlo simulation and  all the necessary statistics like Median, Percentiles on the simulated values
     * @param portfolioType, Aggressive or VeryConservative
     * @param monteCarloSimulator , MonteCarlo Simulator Object
     * @param runner, Statistics runner object to run all the necessary statistics
     * @param intialInvestment, The initial investment made
     * @param numOfYears, The number of years for which the future investment value should be projected
     * @return PortfolioPerformanceStatsResult, the result of Median 20th Year, 10% Best Case, 10% Worst Case
     *                                          for a given portfolio type
     * @throws IllegalArgumentException, when portfolioType, monteCarloSimulator, runner is null
       */
       public PortfolioPerformanceStatsResult runPortFolioService(PortfolioType portfolioType,
                                                                  MonteCarloSimulator monteCarloSimulator,
                                                                  PerformanceStatisticsRunner runner,
                                                                  double intialInvestment,
                                                                  int numOfYears,
                                                                  double inflationRate)
                                                                  throws IllegalArgumentException
       {
           if(portfolioType==null)
           {
               throw  new IllegalArgumentException("The Portfolio type can't be null");
           }
           if(monteCarloSimulator ==null)
           {
               throw new IllegalArgumentException("The Monte Carlo Simulator  object can't be null");
           }
           if(runner == null)
           {
               throw new IllegalArgumentException("The Performance Stats runner object can't be null");
           }
           double[] simulatedValues = monteCarloSimulator.runMonteCarloSimulation(
                   portfolioType.getStdDev(), portfolioType.getMean(), intialInvestment, inflationRate, numOfYears);
           PortfolioPerformanceStatsResult res = runner.runStatistics(simulatedValues);
           return res;

       }
       /***
     * Main entry of the Program
     * @param args
     */
        public static void main(String[] args)
        {
           PortfolioStatisticsService portfolioStatisticsService = new PortfolioStatisticsService();
           try {
               System.out.println("++++++++++++++++++++++Very Conservative PortFolio++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
               PortfolioPerformanceStatsResult veryConservativeRes = portfolioStatisticsService.runPortFolioService
                               (PortfolioType.VERYCONSERVATIVE,
                                       new MonteCarloSimulator(NUM_OF_SIMULATIONS),
                                       new PerformanceStatisticsRunner(),INITIAL_INVESTMENT,NUM_OF_YEARS,INFLATION_RATE);
               System.out.println(veryConservativeRes.toString());
               System.out.println();
               System.out.println("++++++++++++++++++++++Aggressive Portfolio++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
               PortfolioPerformanceStatsResult aggresiveResult = portfolioStatisticsService.runPortFolioService
                       (PortfolioType.AGGRESSIVE,
                               new MonteCarloSimulator(NUM_OF_SIMULATIONS),
                               new PerformanceStatisticsRunner(),INITIAL_INVESTMENT,NUM_OF_YEARS,INFLATION_RATE);
               System.out.println(aggresiveResult.toString());
               System.out.println();

           }
           catch (IllegalArgumentException e)
           {
               System.out.println("There was  Exception thrown while Calculating Portfolio Statistics values from simulation: "+e);

           }
           catch (RuntimeException e )
           {
              System.out.println("There was  Exception thrown while Calculating  Portfolio Statistics values from simulation: "+e);
           }
       }
}
