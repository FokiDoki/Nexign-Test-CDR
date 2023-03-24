package com.cdr;

import com.cdr.Tariffs.UnlimitedTariff;
import com.cdr.Tariffs.UnlimitedTariff;
import com.cdr.Tariffs.UnlimitedTariff;

import java.lang.reflect.Method;
import java.util.Date;

public class UnlimitedTariffTest {
    private static final String testName = "UnlimitedTariffTest";

    public void test1(){
        CDRReportContainerSingle cdrReportContainer = new CDRReportContainerSingle();
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 10, 10, 33),
                new Date(2000, 10, 10, 10, 10, 59), new UnlimitedTariff()));
        new UnlimitedTariff().calculateCallCostsFromCDRReportContainer(cdrReportContainer);
        Assertions.assertEquals(0.0, cdrReportContainer.getTotalCallsCost(), testName + " test1");
    }

    public void test2(){
        CDRReportContainerSingle cdrReportContainer = new CDRReportContainerSingle();
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 10, 10, 33),
                new Date(2000, 10, 10, 15, 11, 33), new UnlimitedTariff()));
        new UnlimitedTariff().calculateCallCostsFromCDRReportContainer(cdrReportContainer);
        Assertions.assertEquals(1.0, cdrReportContainer.getTotalCallsCost(), testName + " test2");
    }

    public void test3(){
        CDRReportContainerSingle cdrReportContainer = new CDRReportContainerSingle();
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 10, 0, 0),
                new Date(2000, 10, 10, 13, 20, 59), new UnlimitedTariff())); // 200
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.INCOMING,
                "123456789", new Date(2000, 10, 10, 13, 0, 0),
                new Date(2000, 10, 10, 14, 41, 59), new UnlimitedTariff())); //101
        new UnlimitedTariff().calculateCallCostsFromCDRReportContainer(cdrReportContainer);
        Assertions.assertEquals(1.0, cdrReportContainer.getTotalCallsCost(), testName + " test3");
    }
    public void test4(){
        CDRReportContainerSingle cdrReportContainer = new CDRReportContainerSingle();
        cdrReportContainer.add(new CDRReport(CDRReport.CallType.INCOMING,
                "123456789", new Date(2000, 10, 10, 10, 0, 0),
                new Date(2000, 10, 10, 20, 0, 59), new UnlimitedTariff()));
        new UnlimitedTariff().calculateCallCostsFromCDRReportContainer(cdrReportContainer);
        Assertions.assertEquals(300.0, cdrReportContainer.getTotalCallsCost(), testName + " test4");
    }
}
