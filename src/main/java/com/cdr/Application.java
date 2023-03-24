package com.cdr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Application {
    private static final String DEFAULT_SOURCE_FILE_PATH = "cdr.txt";
    private static final String DEFAULT_REPORT_PATH = "reports\\report_%s.txt";

    /**
     * Main method
     * Arguments are optional
     * First argument is path to CDR file
     * Second argument is path to report file, %s will be replaced with phone number of the caller
     * If folder for report file does not exist, it will be created
     * If no arguments are specified, default CDR file path and report path will be used
     * @throws IOException If file cannot be read
     */
    public static void main(String[] args) throws IOException {
        
        File inputFile =  new File(DEFAULT_SOURCE_FILE_PATH);
        String outputFilePathPattern = DEFAULT_REPORT_PATH;
        if (!inputFile.exists()){
            throw new FileNotFoundException("File " + DEFAULT_SOURCE_FILE_PATH + " does not exist");
        }
        if (args.length > 0){
            File fileFromArgument = new File(args[0]);
            if (fileFromArgument.exists()){
                inputFile = fileFromArgument;
            } else {
                System.out.println("File " + args[0] + " does not exist. " +
                        "Using default CDR file path: " + DEFAULT_SOURCE_FILE_PATH);
            }
            if (args.length == 2){
                outputFilePathPattern = args[1];
            } else {
                System.out.println("No output file specified. " +
                        "Using default report path: " + DEFAULT_REPORT_PATH);
            }
        } else {
            System.out.println("No arguments. " +
                    "\nUsing default CDR file path: " + DEFAULT_SOURCE_FILE_PATH
                    + "\nUsing default report path: " + DEFAULT_REPORT_PATH);
        }

        CDRReportContainer container = CDRReportFromInputStream.parse(new FileInputStream(inputFile));
        System.out.println("Parsed " + container.size() + " CDR reports");
        System.out.println("Unique phone numbers: " + container.getUniquePhoneNumbers().size());
        System.out.println("Generating reports...");
        CDRReportGenerator.toFile(container, outputFilePathPattern);
        String storeDirectory = new File(outputFilePathPattern).getParentFile().getAbsolutePath();
        System.out.println("Reports saved to " + storeDirectory);

    }
}
