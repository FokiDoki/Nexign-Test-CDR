package com.cdr;

import com.cdr.Tariffs.components.Tariff;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CDRReportGenerator {

    private static final String header =
            "Tariff index: %s\n" +
            "-----------------------------------------------------------------------------\n" +
            "Report for phone number %s\n" +
            "-----------------------------------------------------------------------------\n" +
            "-----------------------------------------------------------------------------\n" +
            "| Call Type |   Start Time        |     End Time        | Duration |  Cost  |\n"+
            "-----------------------------------------------------------------------------\n";
    private static final String row =
            "|     %s    | %s | %s | %s |  %.2f\t|\n";
    private static final String footer =
            "-----------------------------------------------------------------------------\n" +
            "|                                           Total Cost: |     %.2f rubles\t|\n" +
            "-----------------------------------------------------------------------------\n";


    /**
     * Writes CDRReportContainer to file
     * @param reportContainer - CDRReportContainer to write
     * @param file - file to write
     * @throws IOException if can't write to file
     */
    public static void toFile(CDRReportContainer reportContainer, File file) throws IOException {
        StringToFileHelper stringToFileHelper = new StringToFileHelper(file);
        for (String phoneNumber : reportContainer.getUniquePhoneNumbers()) {
            CDRReportContainerSingle containerForPhoneNumber = reportContainer.getAllCallsForPhoneNumber(phoneNumber);
            String Report = generateCDRReportForNumber(containerForPhoneNumber);
            stringToFileHelper.write(Report);
        }
    }

    /**
     * Generates CDRReport for single phone number
     * @param containerForPhoneNumber CDRReportContainerSingle object
     * @return String formatted CDRReport
     */
    public static String generateCDRReportForNumber(CDRReportContainerSingle containerForPhoneNumber) {
        StringBuilder builder = new StringBuilder();
        Tariff tariff = containerForPhoneNumber.getTariff();
        builder.append(String.format(header,
                tariff.getTariffCode(),
                containerForPhoneNumber.getPhoneNumber()));
        tariff.calculateCallCostsFromCDRReportContainer(containerForPhoneNumber);
        for (CDRReport report : containerForPhoneNumber.getReports()) {
            builder.append(String.format(row,
                    report.getCallType().getCode(),
                    dateToString(report.getCallStartDate()),
                    dateToString(report.getCallEndDate()),
                    getTimeFromMilliSeconds(report.getDuration()),
                    report.getCost()));
        }
        builder.append(String.format(footer, containerForPhoneNumber.getTotalCost()));
        return builder.toString();
    }

    private static String getTimeFromMilliSeconds(long milliseconds){
        int seconds = (int) (milliseconds / 1000);
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secondsLeft = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secondsLeft);
    }

    private static String dateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd kk:mm:ss");
        return dateFormat.format(date);
    }



}
