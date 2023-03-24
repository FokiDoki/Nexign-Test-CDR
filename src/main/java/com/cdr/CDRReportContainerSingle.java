package com.cdr;

import com.cdr.Tariffs.components.Tariff;

public class CDRReportContainerSingle extends CDRReportContainer{
    private String phoneNumber;

    public CDRReportContainerSingle(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CDRReportContainerSingle() {

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getTotalCallsCost() {
        double totalCost = 0;
        for (CDRReport report : reports) {
            totalCost += report.getCost();
        }
        return totalCost;
    }


    public Tariff getTariff(){
        return reports.first().getTariff();
    }

    public double getTotalCost(){
        return getTotalCallsCost()+this.getTariff().getCostPerPeriod();
    }

}
