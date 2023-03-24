package com.cdr;

import com.cdr.Tariffs.components.Tariff;
import com.cdr.Tariffs.UnlimitedTariff;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Application {
    public static void main(String[] args) throws ParseException, IOException {
        Tariff tariff = new UnlimitedTariff();
        CDRReportContainer cdrReportContainer = new CDRReportContainer();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy, MM, dd, k, m, ss");
        CDRReport report = new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", formatter.parse("2000, 01, 01, 22, 10, 33"),
                                         formatter.parse("2000, 01, 01, 23, 10, 33"), tariff);
        cdrReportContainer.add(report);
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", formatter.parse("2000, 10, 10, 10, 10, 33"),
                                         formatter.parse("2000, 10, 10, 12, 10, 33"), tariff));
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.INCOMING,
                "123456789", formatter.parse("2000, 10, 10, 12, 10, 33"),
                                         formatter.parse("2000, 10, 10, 12, 23, 56"), tariff));
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", formatter.parse("2000, 10, 10, 02, 10, 33"),
                                         formatter.parse("2000, 10, 10, 05, 10, 33"), tariff));
        CDRReportGenerator cdrReportGenerator = new CDRReportGenerator();
        FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\cdr.txt");
        String reportPath = "reports\\report_%s.txt";
        StringToFileHelper stringToFileHelper = new StringToFileHelper(reportPath);
        CDRReportContainer reportContainer2 = CDRReportFromInputStream.parse(fileInputStream);
        for (String phoneNumber : reportContainer2.getUniquePhoneNumbers()) {
            CDRReportContainerSingle containerForPhoneNumber = reportContainer2.getAllCallsForPhoneNumber(phoneNumber);
            String Report = cdrReportGenerator.generateReportForAll(containerForPhoneNumber);
            stringToFileHelper.setOutputFilePath(String.format(reportPath, phoneNumber));
            stringToFileHelper.write(Report);
            System.out.println(Report);
        }
    }
}
