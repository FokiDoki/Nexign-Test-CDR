package com.cdr;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Application {
    private static final String DEFAULT_SOURCE_FILE_PATH = "src\\main\\resources\\cdr.txt";
    private static final String DEFAULT_REPORT_PATH = "reports\\report_%s.txt";

    public static void main(String[] args) throws IOException {
        
        File inputFile =  new File(DEFAULT_SOURCE_FILE_PATH);
        File outputFile = new File(DEFAULT_REPORT_PATH);
        if (args.length > 0){
            File fileFromArgument = new File(args[0]);
            if (fileFromArgument.exists()){
                inputFile = fileFromArgument;
            }
            if (args.length == 2){
                outputFile = new File(args[1]);
            }
        } else {
            System.out.println("No arguments. " +
                    "\nUsing default CDR file path: " + DEFAULT_SOURCE_FILE_PATH
                    + "\nUsing default report path: " + DEFAULT_REPORT_PATH);
        }

        CDRReportContainer container = CDRReportFromInputStream.parse(new FileInputStream(inputFile));
        CDRReportGenerator.toFile(container, outputFile);
        

    }
}
