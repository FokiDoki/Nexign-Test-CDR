package com.cdr;

import com.cdr.Tariffs.MinuteTariff;
import com.cdr.Tariffs.UnlimitedTariff;

import java.util.Date;

public class CDRReportTest {
    private final String testName = "CDRReportTest";
    public void CDRReportDurationTest1(){
        CDRReport cdrReport = new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 10, 10, 0),
                new Date(2000, 10, 10, 10, 11, 0), new MinuteTariff());
        Assertions.assertEquals(1, cdrReport.getDurationMinutesRoundedDown(), testName + " test1");
    }
    public void CDRReportDurationTest2(){
        CDRReport cdrReport = new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 10, 10, 0),
                new Date(2000, 10, 10, 10, 11, 23), new MinuteTariff());

        Assertions.assertEquals(1, cdrReport.getDurationMinutesRoundedDown(), testName + " test2");

    }
    public void CDRReportDurationTest3(){
        CDRReport cdrReport =new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 10, 10, 0),
                new Date(2000, 10, 10, 10, 11, 59), new MinuteTariff());

        Assertions.assertEquals(1, cdrReport.getDurationMinutesRoundedDown(), testName + " test3");

    }
    public void CDRReportDurationTest4(){
        CDRReport cdrReport = new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 10, 10, 0),
                new Date(2000, 10, 10, 10, 10, 32), new MinuteTariff());

        Assertions.assertEquals(0, cdrReport.getDurationMinutesRoundedDown(), testName + " test4");

    }

    public void CDRReportDurationTest5(){
        CDRReport cdrReport = new CDRReport(CDRReport.CallType.OUTGOING,
                "123456789", new Date(2000, 10, 10, 10, 10, 33),
                new Date(2000, 10, 10, 10, 30, 32), new MinuteTariff());

        Assertions.assertEquals(19, cdrReport.getDurationMinutesRoundedDown(), testName + " test5");

    }
}
