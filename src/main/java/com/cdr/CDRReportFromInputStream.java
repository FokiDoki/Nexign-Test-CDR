package com.cdr;

import com.cdr.Tariffs.components.TariffFactory;
import com.cdr.Tariffs.components.TariffName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * Parses CDR report from input stream
 */
public class CDRReportFromInputStream  {
    /**
     * Parse CDR report from input stream. Each line of input stream is a CDR report
     * Format TYPE_OF_CALL,NUMBER,START_TIME,END_TIME,TARIFF_CODE
     * Time format is YYYYMMDDHH24MMSS
     * Type of call: 01 - incoming, 02 - outgoing
     * Example: 02, 79876543221, 20230321160455, 20230321163211, 11
     * @param input - input stream
     * @return CDRReportContainer with parsed CDR reports
     * @throws IOException if data from input stream in wrong format
     */
    public static CDRReportContainer parse(InputStream input) throws IOException {
        CDRReportContainer container = new CDRReportContainer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (reader.ready()){
            String line = reader.readLine();
            CDRReport report = null;
            try {
                report = CDRReportFromString(line);
            } catch (ParseException e) {
                throw new IOException("Error while parsing CDR report from string: " + line, e);
            }
            container.add(report);
        }
        return container;
    }

    private static CDRReport CDRReportFromString(String line) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddkkmmss");
        line = line.replace(" ", "");
        String[] lineParts = line.split(",");
        return new CDRReport(
                CDRReport.CallType.fromCode(lineParts[0]),
                lineParts[1],
                formatter.parse(lineParts[2]),
                formatter.parse(lineParts[3]),
                TariffFactory.getTariff(Objects.requireNonNull(TariffName.getByCode(lineParts[4]))));
    }
    
}
