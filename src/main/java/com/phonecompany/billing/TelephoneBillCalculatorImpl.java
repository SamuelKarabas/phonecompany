package com.phonecompany.billing;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TelephoneBillCalculatorImpl implements TelephoneBillCalculator {

    private static final BigDecimal NORMAL_RATE = new BigDecimal("1.00");
    private static final BigDecimal REDUCED_RATE = new BigDecimal("0.50");
    private static final BigDecimal LONG_CALL_RATE = new BigDecimal("0.20");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public BigDecimal calculate(String phoneLog) {
        String[] lines = phoneLog.split("\n");
        Map<String, List<Call>> calls = new HashMap<>();
        for (String line : lines) {
            String[] parts = line.split(",");
            String phoneNumber = parts[0];
            LocalDateTime start = LocalDateTime.parse(parts[1], formatter);
            LocalDateTime end = LocalDateTime.parse(parts[2], formatter);
            Call call = new Call(phoneNumber, start, end);
            if (!calls.containsKey(phoneNumber)) {
                calls.put(phoneNumber, new ArrayList<>());
            }
            calls.get(phoneNumber).add(call);
        }

        String mostCalledNumber = null;
        int maxCalls = 0;
        for (Map.Entry<String, List<Call>> entry : calls.entrySet()) {
            if (entry.getValue().size() > maxCalls) {
                maxCalls = entry.getValue().size();
                mostCalledNumber = entry.getKey();
            }
        }

        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<String, List<Call>> entry : calls.entrySet()) {
            if (entry.getKey().equals(mostCalledNumber)) {
                continue;
            }
            for (Call call : entry.getValue()) {
                total = total.add(calculateCallCost(call));
            }
        }

        return total;
    }

    private BigDecimal calculateCallCost(Call call) {
        BigDecimal cost = BigDecimal.ZERO;
        LocalDateTime time = call.start;
        while (time.isBefore(call.end)) {
            if (time.getHour() >= 8 && time.getHour() < 16) {
                cost = cost.add(NORMAL_RATE);
            } else {
                cost = cost.add(REDUCED_RATE);
            }
            time = time.plusMinutes(1);
        }
        if (cost.compareTo(new BigDecimal("5.00")) > 0) {
            cost = new BigDecimal("5.00").add(cost.subtract(new BigDecimal("5.00")).multiply(LONG_CALL_RATE));
        }
        return cost;
    }
    
}
