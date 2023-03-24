package com.cdr.Tariffs.components;

import com.cdr.CDRReportContainer;

public interface Tariff {

    double getCostPerPeriod();

    String getTariffCode();

    void calculateCallCostsFromCDRReportContainer(CDRReportContainer cdrReportContainer);


}
