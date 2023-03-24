package com.cdr;

import com.cdr.Tariffs.components.Tariff;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CDRReportGenerator {

    private String header =
            "Tariff index: %s\n" +
            "-----------------------------------------------------------------------------\n" +
            "Report for phone number %s\n" +
            "-----------------------------------------------------------------------------\n" +
            "-----------------------------------------------------------------------------\n" +
            "| Call Type |   Start Time        |     End Time        | Duration |  Cost  |\n"+
            "-----------------------------------------------------------------------------\n";
    private String row =
            "|     %s    | %s | %s | %s |  %.2f\t|\n";
    private String footer =
            "-----------------------------------------------------------------------------\n" +
            "|                                           Total Cost: |     %.2f rubles\t|\n" +
            "-----------------------------------------------------------------------------\n";


    public String generateReportForAll(CDRReportContainerSingle containerForPhoneNumber) {
        StringBuilder builder = new StringBuilder();
        Tariff tariff = containerForPhoneNumber.getTariff();
        builder.append(String.format(this.header,
                tariff.getTariffCode(),
                containerForPhoneNumber.getPhoneNumber()));
        tariff.calculateCallCostsFromCDRReportContainer(containerForPhoneNumber);
        for (CDRReport report : containerForPhoneNumber.getReports()) {
            builder.append(String.format(this.row,
                    report.getCallType().getCode(),
                    dateToString(report.getCallStartDate()),
                    dateToString(report.getCallEndDate()),
                    getTimeFromMilliSeconds(report.getDuration()),
                    report.getCost()));
        }
        builder.append(String.format(this.footer, containerForPhoneNumber.getTotalCost()));
        return builder.toString();
    }

    private String getTimeFromMilliSeconds(long milliseconds){
        int seconds = (int) (milliseconds / 1000);
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secondsLeft = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secondsLeft);
    }

    private String dateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd kk:mm:ss");
        return dateFormat.format(date);
    }



}
