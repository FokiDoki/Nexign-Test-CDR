package com.cdr.Tariffs;

import com.cdr.CDRReport;
import com.cdr.CDRReportContainer;
import com.cdr.Tariffs.components.Tariff;

public class UnlimitedTariff implements Tariff {
    public static final String CODE = "06";
    public static final double COST_PER_PERIOD = 100;
    public static final double COST_PER_MINUTE = 1.0;
    @Override
    public double getCostPerPeriod() {
        return COST_PER_PERIOD;
    }

    @Override
    public String getTariffCode() {
        return CODE;
    }

    /**
     * Tariff 06 - 100 per month, 1 per minute after 300 minutes
     * @param cdrReportContainer
     */
    @Override
    public void calculateCallCostsFromCDRReportContainer(CDRReportContainer cdrReportContainer) {
        int allCallsInContainerDuration = 0;
        for (CDRReport cdrReport : cdrReportContainer.getReports()) {
            allCallsInContainerDuration = calculateCallCostFromCDRReport(cdrReport, allCallsInContainerDuration);
        }
    }


    private int calculateCallCostFromCDRReport(CDRReport cdrReport, int allCallsDuration){
        double CurrentCallCost = 0;
        double CurrentCallDuration = cdrReport.getDurationMinutesRoundedDown();
        if (CurrentCallDuration+allCallsDuration>300){
            CurrentCallCost = (CurrentCallDuration-Math.max(0,(300-allCallsDuration)))*COST_PER_MINUTE;
        }
        allCallsDuration+=CurrentCallDuration;
        cdrReport.setCost(CurrentCallCost);
        return allCallsDuration;
    }
}
