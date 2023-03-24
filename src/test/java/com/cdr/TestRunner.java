package com.cdr;

public class TestRunner {
    public static void main(String[] args) {
        CDRReportTest cdrReportTest = new CDRReportTest();
        cdrReportTest.CDRReportDurationTest1();
        cdrReportTest.CDRReportDurationTest2();
        cdrReportTest.CDRReportDurationTest3();
        cdrReportTest.CDRReportDurationTest4();
        cdrReportTest.CDRReportDurationTest5();

        MinuteTariffTest MinuteTariffTest = new MinuteTariffTest();
        MinuteTariffTest.MinuteTariffTest1();
        MinuteTariffTest.MinuteTariffTest2();
        MinuteTariffTest.MinuteTariffTest3();
        MinuteTariffTest.MinuteTariffTest4();
        MinuteTariffTest.MinuteTariffTest5();

        DefaultTariffTest defaultTariffTest = new DefaultTariffTest();
        defaultTariffTest.test1();
        defaultTariffTest.test2();
        defaultTariffTest.test3();
        defaultTariffTest.test4();
        defaultTariffTest.test5();
        defaultTariffTest.test6();
        defaultTariffTest.test7();

        UnlimitedTariffTest unlimitedTariffTest = new UnlimitedTariffTest();
        unlimitedTariffTest.test1();
        unlimitedTariffTest.test2();
        unlimitedTariffTest.test3();
        unlimitedTariffTest.test4();
    }

}
