package com.sb.lab.prometheus.scheduler;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class Test {
    public static void main(String[] args) {
        /*LocalDateTime now = LocalDateTime.now();
        String format = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println("format" +format);*/
        //LocalDateTime.parse(now.toString(),format);

        //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
       /* DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println("Format "+localDateTime.format(DateTimeFormatter.ISO_LOCAL_TIME));


        String isoFormatted = DateTimeFormatter.ISO_INSTANT.format(ZonedDateTime.now(ZoneId.of("Asia/Kolkata")));
        System.out.println("ISO Formatted = " + isoFormatted);
*/
        OffsetDateTime offsetDateTime = OffsetDateTime.now().withOffsetSameLocal(ZoneOffset.UTC);

        System.out.println("Offset "+offsetDateTime);

        System.out.println(LocalDateTime.now().atOffset(ZoneOffset.UTC));
    }
}
