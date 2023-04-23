package com.sb.lab.prometheus.scheduler;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SchedulerMetrics {

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    public void setStatus(String success) {
        if (success.equalsIgnoreCase("Sucess")) {
            atomicInteger.set(0);
        } else if (success.equalsIgnoreCase("Failed")) {
            atomicInteger.set(8);
        }
    }

    public double getCode() {
        return atomicInteger.doubleValue();
    }
}
