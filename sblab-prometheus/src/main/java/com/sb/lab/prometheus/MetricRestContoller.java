package com.sb.lab.prometheus;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("metrics")
@Slf4j
@RequiredArgsConstructor
public class MetricRestContoller {

    private final MeterRegistry meterRegistry;

    @GetMapping(path = "/hello")
    public ResponseEntity<Greeting> sayHello(@RequestParam(name = "greeting",defaultValue = "hello") String greeting){
        meterRegistry.config().commonTags("application","sblab-prometheus");
        Counter counter;
        if(greeting.equals("hello")){
           counter  = meterRegistry.counter("metrics_greeting","greetingType","hello");
        }else{
            counter  = meterRegistry.counter("metrics_greeting","greetingType","hi");
        }


        counter.increment();
        log.info("Hello Services has been invoked");
        return ResponseEntity.ok(new Greeting("hello",Instant.now()));
    }


}

record Greeting(String greeting, Instant instant){

}
