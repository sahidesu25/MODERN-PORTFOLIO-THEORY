package com.personalcapital.portfolio;

/**
 * Class, which stores the final result of the Portfolio
 * Median 20th Year, 10 % Best Case,10 % Worst Case
 */
public class PortfolioPerformanceStatsResult
{
    private String median;
    private String bestCase;
    private String worstCase;


    public PortfolioPerformanceStatsResult(String median, String bestCase, String worstCase)
    {
        this.median = median;
        this.bestCase = bestCase;
        this.worstCase = worstCase;
    }

    public String getMedian()
    {
        return median;
    }

    public String getBestCase()
    {
        return bestCase;
    }

    public String getWorstCase()
    {
        return worstCase;
    }

    @Override
    public String toString()
    {
        return "PortfolioPerformanceStatsResult{" +
                "median=" + median +
                ", bestCase=" + bestCase +
                ", worstCase=" + worstCase +
                '}';
    }
}
