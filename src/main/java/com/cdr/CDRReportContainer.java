package com.cdr;

import java.util.*;

/**
 * Container for CDRReport
 * Can contain multiple reports for different phone numbers
 * Reports sorted by callStartDate
 */
public class CDRReportContainer {


    private static class reportSortByCallDateComparator implements Comparator<CDRReport> {
        @Override
        public int compare(CDRReport o1, CDRReport o2) {
            return o1.getCallStartDate().compareTo(o2.getCallStartDate());
        }
    }

    protected TreeSet<CDRReport> reports = new TreeSet<CDRReport>(new reportSortByCallDateComparator());

    private final Set<String> uniquePhoneNumbers = new TreeSet<>();


    /**
     * Returns unique phone numbers from all reports
     * @return Set<String> of unique phone numbers
     */
    public Set<String> getUniquePhoneNumbers() {
        return uniquePhoneNumbers;
    }

    /**
     * Adds report to container
     * Same report can be added only once
     * @throws IllegalArgumentException If report has null call start or call end time
     * @param report
     */
    public void add(CDRReport report) {
        if (report.getCallStartDate() == null | report.getCallEndDate() == null)
            throw new IllegalArgumentException("CDRReport call start or call end time cannot be null");
        reports.add(report);
        uniquePhoneNumbers.add(report.getPhoneNumber());
    }

    /**
     * Return CDRReportContainerSingle for specified phone number
     * @param phoneNumber - phone number to search for
     * @return CDRReportContainerSingle with all reports for specified phone number
     */
    public CDRReportContainerSingle getAllCallsForPhoneNumber(String phoneNumber) {
        CDRReportContainerSingle container = new CDRReportContainerSingle();
        for (CDRReport report : reports) {
            if (report.getPhoneNumber().equals(phoneNumber)) {
                container.add(report);
            }
        }
        return container;
    }

    /**
     * Returns size of container
     * @return int size of container
     */
    public int size() {
        return reports.size();
    }

    /**
     * Returns all reports
     * @return TreeSet<CDRReport> of all reports
     */
    public TreeSet<CDRReport> getReports() {
        return reports;
    }
}
