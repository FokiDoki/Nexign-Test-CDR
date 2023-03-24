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

public class CDRReportFromInputStream  {
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
