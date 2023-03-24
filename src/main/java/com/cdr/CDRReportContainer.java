package com.cdr;

import java.util.*;

public class CDRReportContainer {


    private class reportSortByCallDateComparator implements Comparator<CDRReport> {
        @Override
        public int compare(CDRReport o1, CDRReport o2) {
            return o1.getCallStartDate().compareTo(o2.getCallStartDate());
        }
    }

    protected TreeSet<CDRReport> reports = new TreeSet<CDRReport>(new reportSortByCallDateComparator());

    private Set<String> uniquePhoneNumbers = new TreeSet<>();


    public Set<String> getUniquePhoneNumbers() {
        return uniquePhoneNumbers;
    }

    public void add(CDRReport report) {
        reports.add(report);
        uniquePhoneNumbers.add(report.getPhoneNumber());
    }


    public CDRReportContainerSingle getAllCallsForPhoneNumber(String phoneNumber) {
        CDRReportContainerSingle container = new CDRReportContainerSingle(phoneNumber);
        for (CDRReport report : reports) {
            if (report.getPhoneNumber().equals(phoneNumber)) {
                container.add(report);
            }
        }
        return container;
    }
    public TreeSet<CDRReport> getReports() {
        return reports;
    }
}
