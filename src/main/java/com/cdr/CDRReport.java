package com.cdr;

import com.cdr.Tariffs.components.Tariff;

import java.util.Date;

/**
 * Class for storing CDR report data
 */
public class CDRReport {
    private final CallType callType;
    private final String phoneNumber;
    private final Date callStartDate;
    private final Date callEndDate;
    private final Tariff tariff;
    private double cost;

    /**
     * @param callType - type of call (incoming, outgoing)
     * @param phoneNumber - phone number of the caller
     * @param callStartDate - date when the call started
     * @param callEndDate - date when the call ended
     * @param tariff - tariff for this call
     */
    public CDRReport(CallType callType, String phoneNumber, Date callStartDate, Date callEndDate, Tariff tariff) {
        this.callType = callType;
        this.phoneNumber = phoneNumber;
        this.callStartDate = callStartDate;
        this.callEndDate = callEndDate;
        this.tariff = tariff;
    }

    /**
     * @return duration of the call in milliseconds
     */
    public int getDuration() {
        return (int) (callEndDate.getTime() - callStartDate.getTime());
    }

    /**
     * @return duration of the call in minutes rounded down
     */
    public int getDurationMinutesRoundedDown() {
        return (int) Math.floor(getDuration() / 60000);
    }

    /**
     * @return type of the call (incoming, outgoing)
     */
    public CallType getCallType() {
        return callType;
    }

    /**
     * @return cost of the call (if it was not calculated, returns null)
     */
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

    /**
     * Sets cost of the call
     * @param cost
     */
    public void setCost(double cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative");
        }
        this.cost = cost;
    }

    /**
     * @return phone number of the caller
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return date when the call started
     */
    public Date getCallStartDate() {
        return callStartDate;
    }

    /**
     * @return date when the call ended
     */
    public Date getCallEndDate() {
        return callEndDate;
    }

    /**
     * @return Tariff for this report
     */
    public Tariff getTariff() {
        return tariff;
    }

    /**
     * Enum for call type
     * 02 - incoming
     * 01 - outgoing
     */
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

        /**
         * Returns CallType from code
         * @param code string
         * @return CallType
         */
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
