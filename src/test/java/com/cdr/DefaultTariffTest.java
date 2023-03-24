package com.cdr;

import com.cdr.Tariffs.DefaultTariff;
import com.cdr.Tariffs.DefaultTariff;

import java.util.Date;

public class DefaultTariffTest {
    private static final String testName = "DefaultTariffTest";
    public void test1() {
        CDRReportContainerSingle cdrReportContainer = new CDRReportContainerSingle();
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 10, 10, 33),
                new Date(2000, 10, 10, 10, 30, 32), new DefaultTariff()));

        new DefaultTariff().calculateCallCostsFromCDRReportContainer(cdrReportContainer);
        if (cdrReportContainer.getTotalCallsCost()!=19*0.5) {
            throw new AssertionError(testName+"1 failed");
        } else {
            System.out.println(testName+"1 passed");
        }
    }

    public void test2() {
        CDRReportContainerSingle cdrReportContainer = new CDRReportContainerSingle();
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 10, 10, 00),
                new Date(2000, 10, 10, 11, 10, 00), new DefaultTariff()));

        new DefaultTariff().calculateCallCostsFromCDRReportContainer(cdrReportContainer);

        if (cdrReportContainer.getTotalCallsCost()!=0.5*60) {
            throw new AssertionError(testName+"2 failed");
        } else {
            System.out.println(testName+"2 passed");
        }
    }

    public void test3() {
        CDRReportContainerSingle cdrReportContainer = new CDRReportContainerSingle();
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 10, 10, 33),
                new Date(2000, 10, 10, 12, 10, 33), new DefaultTariff()));

        new DefaultTariff().calculateCallCostsFromCDRReportContainer(cdrReportContainer);
        if (cdrReportContainer.getTotalCallsCost()!=100*0.5+20*1.5) {
            throw new AssertionError(testName+"3 failed");
        } else {
            System.out.println(testName+"3 passed");
        }
    }

    public void test4() {
        CDRReportContainerSingle cdrReportContainer = new CDRReportContainerSingle();
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 10, 10, 33),
                new Date(2000, 10, 10, 10, 10, 40), new DefaultTariff()));

        new DefaultTariff().calculateCallCostsFromCDRReportContainer(cdrReportContainer);

        if (cdrReportContainer.getTotalCallsCost()!=0) {
            throw new AssertionError(testName+"4 failed");
        } else {
            System.out.println(testName+"4 passed");
        }
    }

    public void test5() {
        CDRReportContainerSingle cdrReportContainer = new CDRReportContainerSingle();
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 10, 10, 00),
                new Date(2000, 10, 10, 10, 50, 00), new DefaultTariff())); // 40 min
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 11, 10, 00),
                new Date(2000, 10, 10, 11, 50, 00), new DefaultTariff())); //40 min
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 12, 10, 00),
                new Date(2000, 10, 10, 12, 30, 00), new DefaultTariff())); //20 min
        new DefaultTariff().calculateCallCostsFromCDRReportContainer(cdrReportContainer);

        if (cdrReportContainer.getTotalCallsCost()!=100*0.5) {
            throw new AssertionError(testName+"5 failed");
        } else {
            System.out.println(testName+"5 passed");
        }
    }

    public void test6() {
        CDRReportContainerSingle cdrReportContainer = new CDRReportContainerSingle();
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.INCOMING,
                "123456789", new Date(2000, 10, 10, 10, 10, 00),
                new Date(2000, 10, 10, 10, 50, 00), new DefaultTariff())); // 40 min
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.INCOMING,
                "123456789", new Date(2000, 10, 10, 11, 10, 00),
                new Date(2000, 10, 10, 11, 50, 00), new DefaultTariff())); //40 min
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 12, 10, 00),
                new Date(2000, 10, 10, 12, 30, 00), new DefaultTariff())); //20 min
        new DefaultTariff().calculateCallCostsFromCDRReportContainer(cdrReportContainer);

        if (cdrReportContainer.getTotalCallsCost()!=20*0.5) {
            throw new AssertionError(testName+"6 failed");
        } else {
            System.out.println(testName+"6 passed");
        }
    }

    public void test7() {
        CDRReportContainerSingle cdrReportContainer = new CDRReportContainerSingle();
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.INCOMING,
                "123456789", new Date(2000, 10, 10, 10, 10, 00),
                new Date(2000, 10, 10, 12, 50, 00), new DefaultTariff())); // 40 min
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 22, 10, 00),
                new Date(2000, 10, 10, 23, 50, 00), new DefaultTariff())); //100 min
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 15, 10, 00),
                new Date(2000, 10, 10, 17, 30, 00), new DefaultTariff())); //140 min
        new DefaultTariff().calculateCallCostsFromCDRReportContainer(cdrReportContainer);

        if (cdrReportContainer.getTotalCallsCost()!=100*0.5+140*1.5) {
            throw new AssertionError(testName+"7 failed");
        } else {
            System.out.println(testName+"7 passed");
        }
    }



}
