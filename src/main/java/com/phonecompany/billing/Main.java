package com.phonecompany.billing;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        TelephoneBillCalculator calculator = new TelephoneBillCalculatorImpl();

        // Example phone log
        String phoneLog = "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                "420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00";

        BigDecimal total = calculator.calculate(phoneLog);

        System.out.println("Total cost: " + total);
    }
}
