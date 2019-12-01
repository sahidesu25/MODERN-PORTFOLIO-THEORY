package com.personalcapital.portfolio;

import static com.personalcapital.portfolio.constants.Constants.MEAN_OF_VERYCONSERVATIVE_PORTFOLIO;
import static com.personalcapital.portfolio.constants.Constants.STDDEV_OF_VERYCONSERVATIVE_PORTFOLIO;
import static com.personalcapital.portfolio.constants.Constants.MEAN_OF_AGGRESSIVE_PORTFOLIO;
import static com.personalcapital.portfolio.constants.Constants.STDDEV_OF_AGGRESSIVE_PORTFOLIO;


public enum PortfolioType {

    AGGRESSIVE(MEAN_OF_AGGRESSIVE_PORTFOLIO,STDDEV_OF_AGGRESSIVE_PORTFOLIO),
    VERYCONSERVATIVE(MEAN_OF_VERYCONSERVATIVE_PORTFOLIO,STDDEV_OF_VERYCONSERVATIVE_PORTFOLIO);

    private final double  mean;
    private  final double  stdDev;

    PortfolioType(double  mean, double stdDev)
    {
        this.mean = mean;
        this.stdDev = stdDev;
    }

    public double getMean()
    {
        return mean;
    }

    public double getStdDev()
    {
        return stdDev;
    }
}
