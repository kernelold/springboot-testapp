package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

@SpringBootApplication
@RestController
public class Application {

    String AZ  = " notyet ";

    Date date = new Date();
    String strDateFormat = "hh:mm:ss a";
    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
    String formattedDate = dateFormat.format(date);

    @RequestMapping("/")
    public String home() {
        return "Hello";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello" + AZ + formattedDate;
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

