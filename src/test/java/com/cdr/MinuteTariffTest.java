package com.cdr;

import com.cdr.Tariffs.MinuteTariff;


import java.util.Date;

public class MinuteTariffTest {
    public void MinuteTariffTest1() {
        CDRReportContainerSingle cdrReportContainer = new CDRReportContainerSingle();
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 10, 10, 33),
                new Date(2000, 10, 10, 10, 30, 32), new MinuteTariff()));

        new MinuteTariff().calculateCallCostsFromCDRReportContainer(cdrReportContainer);
        System.out.println(cdrReportContainer.getTotalCallsCost());
        if (cdrReportContainer.getTotalCallsCost()!=28.5) {
            throw new AssertionError("MinuteTariffTest1 failed");
        } else {
            System.out.println("MinuteTariffTest2 passed");
        }
    }

    public void MinuteTariffTest2() {
        CDRReportContainerSingle cdrReportContainer = new CDRReportContainerSingle();
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 10, 10, 33),
                new Date(2000, 10, 10, 10, 10, 36), new MinuteTariff()));

        new MinuteTariff().calculateCallCostsFromCDRReportContainer(cdrReportContainer);

        if (cdrReportContainer.getTotalCallsCost()!=0) {
            throw new AssertionError("MinuteTariffTest2 failed");
        } else {
            System.out.println("MinuteTariffTest2 passed");
        }
    }

    public void MinuteTariffTest3() {
        CDRReportContainerSingle cdrReportContainer = new CDRReportContainerSingle();
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 10, 10, 33),
                new Date(2000, 10, 10, 10, 11, 32), new MinuteTariff()));

        new MinuteTariff().calculateCallCostsFromCDRReportContainer(cdrReportContainer);

        if (cdrReportContainer.getTotalCallsCost()!=0) {
            throw new AssertionError("MinuteTariffTest3 failed");
        } else {
            System.out.println("MinuteTariffTest3 passed");
        }
    }

    public void MinuteTariffTest4() {
        CDRReportContainerSingle cdrReportContainer = new CDRReportContainerSingle();
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 10, 10, 33),
                new Date(2000, 10, 10, 10, 11, 33), new MinuteTariff()));

        new MinuteTariff().calculateCallCostsFromCDRReportContainer(cdrReportContainer);

        if (cdrReportContainer.getTotalCallsCost()!=1.5) {
            throw new AssertionError("MinuteTariffTest4 failed");
        } else {
            System.out.println("MinuteTariffTest4 passed");
        }
    }

    public void MinuteTariffTest5() {
        CDRReportContainerSingle cdrReportContainer = new CDRReportContainerSingle();
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 10, 10, 00),
                new Date(2000, 10, 10, 10, 11, 59), new MinuteTariff()));

        new MinuteTariff().calculateCallCostsFromCDRReportContainer(cdrReportContainer);

        if (cdrReportContainer.getTotalCallsCost()!=1.5) {
            throw new AssertionError("MinuteTariffTest5 failed");
        } else {
            System.out.println("MinuteTariffTest5 passed");
        }
    }


}
