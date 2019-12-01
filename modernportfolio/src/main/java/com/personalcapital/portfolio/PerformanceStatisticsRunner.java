package com.personalcapital.portfolio;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.rank.Median;

import java.text.NumberFormat;
import java.util.Locale;

import static com.personalcapital.portfolio.constants.Constants.BEST_CASE_PERCENTILE;
import static com.personalcapital.portfolio.constants.Constants.WORST_CASE_PERCENTILE;

public class PerformanceStatisticsRunner {

    private Median median ;
    private NumberFormat currencyFormat;

    public PerformanceStatisticsRunner()
    {
        median = new Median();
        currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
    }

    /**
     * Function which runs all the necessary statistics like Median, Percentiles on the simulated values
     * @param simulatedValues, the simulated investment values after performing MonteCarlo Simulation
     * @return
     */
    public PortfolioPerformanceStatsResult runStatistics(double[]simulatedValues)throws IllegalArgumentException
    {
        if(simulatedValues.length==0)
        {
            throw new IllegalArgumentException("The length of simulated values is zero");
        }
        double resultMedian = median.evaluate(simulatedValues);
        double resultBest = StatUtils.percentile(simulatedValues,BEST_CASE_PERCENTILE);
        double worst = StatUtils.percentile(simulatedValues,WORST_CASE_PERCENTILE);
        return new PortfolioPerformanceStatsResult(currencyFormat.format(resultMedian),
                currencyFormat.format(resultBest),currencyFormat.format(worst));

    }

}
