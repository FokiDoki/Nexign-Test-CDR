package com.cdr.Tariffs;

import com.cdr.CDRReport;
import com.cdr.CDRReportContainer;
import com.cdr.Tariffs.components.Tariff;

public class DefaultTariff implements Tariff {
    public static final String CODE = "11";

    public static final double COST_PER_PERIOD = 0;
    @Override
    public double getCostPerPeriod() {
        return COST_PER_PERIOD;
    }
    @Override
    public String getTariffCode() {
        return CODE;
    }
    @Override
    public void calculateCallCostsFromCDRReportContainer(CDRReportContainer cdrReportContainer) {
        int totalOutgoingMinutes = 0;
        for (CDRReport cdrReport : cdrReportContainer.getReports()) {
            totalOutgoingMinutes = calculateCallCostFromCDRReport(cdrReport, totalOutgoingMinutes);
        }
    }

    /**
     * Tariff 11 - 0.5 per minute for first 100 minutes, minuteTariff after
     * @param cdrReport
     * @param totalOutgoingMinutes
     * @return
     */
    private int calculateCallCostFromCDRReport(CDRReport cdrReport, int totalOutgoingMinutes){
        double CurrentCallCost = 0;
        double CurrentCallDuration = cdrReport.getDurationMinutesRoundedDown();
        if (cdrReport.getCallType() == CDRReport.CallType.OUTGOING) {
            if (totalOutgoingMinutes+CurrentCallDuration<=100){
                CurrentCallCost = CurrentCallDuration * 0.5;
            } else {
                CurrentCallCost += Math.max((100-totalOutgoingMinutes),0)*0.5;
                CurrentCallCost += (CurrentCallDuration-Math.max((100-totalOutgoingMinutes),0))
                        *MinuteTariff.COST_PER_MINUTE;
            }
            totalOutgoingMinutes+=CurrentCallDuration;
            cdrReport.setCost(CurrentCallCost);
        }
        cdrReport.setCost(CurrentCallCost);
        return totalOutgoingMinutes;
    }


}
