package com.cdr;

import com.cdr.Tariffs.components.Tariff;

import java.util.Date;

public class CDRReport {
    private final CallType callType;
    private final String phoneNumber;
    private final Date callStartDate;
    private final Date callEndDate;
    private final Tariff tariff;
    private double cost;

    public CDRReport(CallType callType, String phoneNumber, Date callStartDate, Date callEndDate, Tariff tariff) {
        this.callType = callType;
        this.phoneNumber = phoneNumber;
        this.callStartDate = callStartDate;
        this.callEndDate = callEndDate;
        this.tariff = tariff;
    }

    public int getDuration() {
        return (int) (callEndDate.getTime() - callStartDate.getTime());
    }

    public int getDurationMinutesRoundedDown() {
        return (int) Math.floor(getDuration() / 60000);
    }

    public CallType getCallType() {
        return callType;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "CDRReport{" +
                "callType=" + callType +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", callStartDate=" + callStartDate +
                ", callEndDate=" + callEndDate +
                ", tariff=" + tariff +
                ", cost=" + cost +
                '}';
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getCallStartDate() {
        return callStartDate;
    }

    public Date getCallEndDate() {
        return callEndDate;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public enum CallType {
        INCOMING("02"),
        OUTGOING("01");

        private final String code;

        public String getCode() {
            return code;
        }

        CallType(String code) {
            this.code = code;
        }

        public static CallType fromCode(String code) {
            for (CallType callType : CallType.values()) {
                if (callType.code.equals(code)) {
                    return callType;
                }
            }
            return null;
        }
    }


}
