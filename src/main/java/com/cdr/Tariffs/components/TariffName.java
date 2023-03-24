package com.cdr.Tariffs.components;

public enum TariffName {
    DEFAULT("11"),
    MINUTE("03"),
    UNLIMITED("06");

    private final String code;

    TariffName(String code){
        this.code = code;
    }

    public static TariffName getByCode(String code){
        for (TariffName tariffName : TariffName.values()){
            if (tariffName.code.equals(code)){
                return tariffName;
            }
        }
        return null;
    }
}
