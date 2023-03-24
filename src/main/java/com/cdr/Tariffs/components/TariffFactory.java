package com.cdr.Tariffs.components;

import com.cdr.Tariffs.DefaultTariff;
import com.cdr.Tariffs.MinuteTariff;
import com.cdr.Tariffs.UnlimitedTariff;

public class TariffFactory {
    /**
     * Get tariff by name
     * @param tariffName - enum TariffName
     * @return Tariff
     */
    public static Tariff getTariff(TariffName tariffName){
        switch (tariffName){
            case DEFAULT:
                return new DefaultTariff();
            case MINUTE:
                return new MinuteTariff();
            case UNLIMITED:
                return new UnlimitedTariff();
            default:
                return null;
        }
    }
}
