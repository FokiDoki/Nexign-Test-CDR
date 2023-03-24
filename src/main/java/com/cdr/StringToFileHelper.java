package com.cdr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class StringToFileHelper {
    private final File outputFile;

    public StringToFileHelper(File outputFile) {
        this.outputFile = outputFile;
    }

    private Boolean pathIsExists(){
        return outputFile.getParentFile().exists();
    }



    public void write(String content) throws IOException {
        if (!pathIsExists()){
            outputFile.getParentFile().mkdirs();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        writer.write(content);
        writer.close();
    }
}
