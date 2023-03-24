package com.cdr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class StringToFileHelper {
    private String outputFilePath;

    public StringToFileHelper(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    private String getFilePathFromPath(){
        return outputFilePath.substring(0, outputFilePath.lastIndexOf("\\"));
    }

    public void write(String content) throws IOException {
        new File(getFilePathFromPath()).mkdirs();
        BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(outputFilePath));
        writer.write(content);
        writer.close();
    }
}
