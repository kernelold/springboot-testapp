package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@SpringBootApplication
@RestController
public class Application {

    Map<String, String> env = System.getenv();

    String AZ = "Region is" + System.getenv("AWS_REGION") + "\n" + "Env is " + env + "\n"   ;

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
