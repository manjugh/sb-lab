package com.sb.lab.prometheus.scheduler;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.random.RandomGenerator;

@Component
@AllArgsConstructor
public class SchedulerMetricExample {
    private final MeterRegistry meterRegistry;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private SchedulerMetrics schedulerMetrics;

    private static int count = 1;
    @Scheduled(cron = "0 0/10 * * * *")
    @Timed(value = "beep")
    public void testRun() throws InterruptedException {
        System.out.println("Scheduer triggered"+ Instant.now());
        Thread.sleep(60000);

       // throw new IllegalArgumentException();
    }


   // @Scheduled(cron = "0/2 * * * * *")
    //@Timed("beep")
    public void testRun2() throws InterruptedException {

        Timer testRun2 = meterRegistry.timer("testRun2");
        System.out.println("Scheduer triggered"+ Instant.now());
        Thread.sleep(20000L);
        testRun2.record(Duration.ofSeconds(1000));

        //throw new IllegalArgumentException();
    }

    @Scheduled(cron = "0 */2 * * * *")
    public void testRun3(){
        Status status = new Status();
        System.out.println("Incremented value "+count);

        if(count == 2){
            schedulerMetrics.setStatus("S");
        }else if(count == 4) {
            schedulerMetrics.setStatus("F");
        }else{
            schedulerMetrics.setStatus("P");
        }

        count++;



        //meterRegistry.gauge("testrun3_gauge",status.floatValue());
        List list = new ArrayList<>();
        //Gauge.builder("test_run3",list, List::size).register(meterRegistry);
        Gauge.builder("test_run3",() -> schedulerMetrics.getCode()).register(meterRegistry);
       // meterRegistry.gauge("ttt",schedulerMetrics::getCode)

    }

    class Status {
        private static AtomicInteger atomicInteger = new AtomicInteger(0);
        private static int count = 1;



        public void setStatus(String value){
            if(value.equals("S")){
                atomicInteger.set(0);
            }else if(value.equals("F")){
                atomicInteger.set(4);
            }else {
                atomicInteger.set(8);
            }
        }




        public Supplier<Number> floatValue() {
            return () -> atomicInteger.floatValue();
        }


    }
}
