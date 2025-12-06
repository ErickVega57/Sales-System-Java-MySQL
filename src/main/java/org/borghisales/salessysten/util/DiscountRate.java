package org.borghisales.salessysten.util;

public enum DiscountRate {
    CERO (1.00),
    DIEZ (0.90),
    QUINCE (0.85),
    VEINTE (0.80),
    CINCUENTA (0.50);

    private final double rate;

    DiscountRate(double rate){
        this.rate = rate;
    }
    public double getRate(){
        return rate;
    }

}
