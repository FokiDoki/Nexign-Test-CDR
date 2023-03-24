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


    /**
     * Writes string to file
     * Creates parent directories if they don't exist
     * @param content - string to write
     * @throws IOException - if function can't write to file or create directory
     */
    public void write(String content) throws IOException {
        if (!pathIsExists()){
            outputFile.getParentFile().mkdirs();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        writer.write(content);
        writer.close();
    }
}
