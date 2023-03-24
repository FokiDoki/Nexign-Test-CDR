package com.cdr;

import com.cdr.Tariffs.components.Tariff;

/**
 * Report container for one phone number
 * Assigned phone number is the phone number of the first report added to the container
 * Tariff is the tariff of the first report added to the container
 */
public class CDRReportContainerSingle extends CDRReportContainer{

    public CDRReportContainerSingle() {
    }

    /**
     * get phone number of the caller. If it was not set, returns the phone number of the first report added to the container
     * @return
     */
    public String getPhoneNumber() {
        return reports.first().getPhoneNumber();
    }

    /**
     * get total cost of all calls in the container. Includes tariff cost
     * @return total cost of all calls in the container
     */
    public double getTotalCallsCost() {
        double totalCost = 0;
        for (CDRReport report : reports) {
            totalCost += report.getCost();
        }
        return totalCost;
    }

    public CDRReport getFirstReport(){
        return reports.first();
    }

    /**
     * Adds CDRReport to container
     * @param CDRReport report
     * @throws IllegalArgumentException if report has different phone number than the phone number of the first report added to the container
     */
    @Override
    public void add(CDRReport report) {
        if (reports.isEmpty()){
            reports.add(report);
        } else {
            if (getPhoneNumber().equals(report.getPhoneNumber())){
                reports.add(report);
            } else {
                throw new IllegalArgumentException("CDRReportContainerSingle can contain only one phone number");
            }
        }
    }


    /**
     * @return Tariff for this report
     */
    public Tariff getTariff(){
        return reports.first().getTariff();
    }

    /**
     * Returns total cost of all calls in the container + tariff cost
     * @return
     */
    public double getTotalCost(){
        return getTotalCallsCost()+this.getTariff().getCostPerPeriod();
    }

}
