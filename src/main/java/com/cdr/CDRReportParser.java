package com.cdr;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

public interface CDRReportParser {
    CDRReportContainer parse(InputStream input) throws IOException, ParseException;
}
