package com.cdr.Tariffs.components;

import com.cdr.CDRReportContainer;

public interface Tariff {

    double getCostPerPeriod();

    String getTariffCode();

    /**
     * Calculate call cost from CDRReport container and set it to each report
     * @param cdrReportContainer
     */
    void calculateCallCostsFromCDRReportContainer(CDRReportContainer cdrReportContainer);


}
