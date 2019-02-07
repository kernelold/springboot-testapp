package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;


@SpringBootApplication
@RestController
public class Application {

    Map<String, String> env = System.getenv();

    String AZ  = "AZ is %s \n" + env ;

    @RequestMapping("/")
    public String home() {
        return "Hello \n";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello \n" + AZ ;
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
