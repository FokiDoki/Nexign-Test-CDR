package com.cdr.Tariffs;

import com.cdr.CDRReport;
import com.cdr.CDRReportContainer;
import com.cdr.Tariffs.components.Tariff;

public class MinuteTariff implements Tariff {
    public static final String CODE = "03";
    public static final double COST_PER_MINUTE = 1.5;
    public static final double COST_PER_PERIOD = 0;
    @Override
    public double getCostPerPeriod() {
        return COST_PER_PERIOD;
    }

    @Override
    public String getTariffCode() {
        return CODE;
    }

    /**
     * Tariff 03 - 1.5 per minute
     * @param cdrReportContainer
     */
    @Override
    public void calculateCallCostsFromCDRReportContainer(CDRReportContainer cdrReportContainer) {
        for (CDRReport cdrReport : cdrReportContainer.getReports()) {
            cdrReport.setCost(cdrReport.getDurationMinutesRoundedDown() * COST_PER_MINUTE);
        }
    }
}