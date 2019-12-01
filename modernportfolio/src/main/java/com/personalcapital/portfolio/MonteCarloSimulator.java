package com.personalcapital.portfolio;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;

/**
 * MonteCarloSimulator class which uses MonteCarlo Simulation technique to project portfolio future
 * value for given number of years
 */
public class MonteCarloSimulator
{
   private  int numOfSimulations;

   private MonteCarloSimulator() { }

   public MonteCarloSimulator(int numOfSimulations)
   {   //number of simulations required
       this.numOfSimulations = numOfSimulations;
   }

    /**
     * Private function which generates the future rate of return for a random probability value in a
     * gaussian distribution for a  given mean, standard deviation and random probability as parameters
     * @param stdDev, standard deviation of the normal distribution curve.
     * @param mean, mean of the normal distribution curve.
     * @Param p, random probability in the normal distribution curve
     * @return rateOfReturn, for a random probability, distributed according to the normal distribution,
     *                       returns the corresponding rateOfReturn.
     * @throws NotStrictlyPositiveException, if stdDev<=0
     * @throws OutOfRangeException, if  p < 0 or p > 1
     */
    private double generateRateOfReturnForRandProbability(double stdDev, double mean, double p) throws NotStrictlyPositiveException, OutOfRangeException, IllegalArgumentException
    {
        if(stdDev<=0)
        {
            throw new IllegalArgumentException("The Standard Deviation should always be greater than zero. The given value is "+stdDev);
        }
        if(p<0 || p>1)
        {
            throw new IllegalArgumentException("The random probability value should be in between 0 to 1. The given value is "+p);
        }
        //calculates the inverse of the Cumulative Normal Distribution Function for a supplied value, generated
        // by Math.random
        NormalDistribution normalDistribution = new NormalDistribution(mean,stdDev);
        double rateOfReturn = normalDistribution.inverseCumulativeProbability(p);
        return rateOfReturn;
    }


    /**
     * Function, to  run Monte Carlo simulations for projecting given future year investment value adjusted
     * according to inflation rate.
     * @param stdDev, standard dev for the normal distributed data,past risk
     * @param mean, mean for the normal distributed data, past return
     * @param initalInvestment, initial investement made
     * @param inflationRate, rate of inflation
     * @param numOfyears, future num of years, for which the investment value needs to be predicted.
     * @throws NotStrictlyPositiveException, if stdDev<=0
     * @throws OutOfRangeException, if  p < 0 or p > 1
     * @throws IllegalArgumentException, if numOfyears<=0 and initalInvestment<=0
     * @return investmentSimulationArray, the simulated values of the investment
     */
    public double[] runMonteCarloSimulation( double stdDev, double mean, double initalInvestment,
                                         double inflationRate, int numOfyears)throws NotStrictlyPositiveException, OutOfRangeException, IllegalArgumentException
    {
        if(numOfyears<=0)
        {
            throw new IllegalArgumentException("Num of years should be greater than zero. The given value is, "+numOfyears);
        }
        if(initalInvestment<=0)
        {
            throw new IllegalArgumentException("Initial Investment should be greater then zero. The given value is, "+initalInvestment);
        }
       double[] investmentSimulationArray = new double[numOfSimulations];
       for (int i = 0; i < numOfSimulations; i++)
       {
           double currentInvestment = initalInvestment;
           for (int j = 0; j < numOfyears; j++) {
            double predictedRateOfReturn = generateRateOfReturnForRandProbability(stdDev,mean,Math.random());
            currentInvestment = currentInvestment * (1 +(predictedRateOfReturn)) ;
            //adjusting according to the inflation rate
            currentInvestment = currentInvestment-currentInvestment*inflationRate;
           }
           investmentSimulationArray[i] = currentInvestment;
       }
       return investmentSimulationArray;
   }
}
