package org.isa.holidaysweb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class HolidaysWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HolidaysWebApplication.class, args);

    }


}
