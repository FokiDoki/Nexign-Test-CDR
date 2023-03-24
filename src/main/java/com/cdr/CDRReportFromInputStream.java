package com.cdr;

import com.cdr.Tariffs.components.TariffFactory;
import com.cdr.Tariffs.components.TariffName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CDRReportFromInputStream  {
    public static CDRReportContainer parse(InputStream input) throws IOException, ParseException {
        CDRReportContainer container = new CDRReportContainer();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddkkmmss");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (reader.ready()){
            String line = reader.readLine();
            line = line.replace(" ", "");
            String[] lineParts = line.split(",");
            CDRReport report = new CDRReport(
                    CDRReport.CallType.fromCode(lineParts[0]),
                    lineParts[1],
                    formatter.parse(lineParts[2]),
                    formatter.parse(lineParts[3]),
                    TariffFactory.getTariff(TariffName.getByCode(lineParts[4]))); 
            container.add(report);
        }
        return container;
    }
    
}
