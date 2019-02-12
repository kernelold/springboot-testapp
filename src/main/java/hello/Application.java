package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.net.*;
import java.io.*;

@SpringBootApplication
@RestController
public class Application {
  String urlt;
  public void ReadWebPage(String urlText) {
    BufferedReader in = null;
    try {
      URL url = new URL(urlText);
      in = new BufferedReader(new InputStreamReader(url.openStream()));

      String inputLine;
      while ((inputLine = in.readLine()) != null) {
      urlt = inputLine;
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }


    Map<String, String> env = System.getenv();

    String AZ = "Region is " + System.getenv("AWS_REGION") + "\n" + "Env is " + env + "\n"   ;

    @RequestMapping("/")
    public String home() {
        return "Hello \n";
    }

    @RequestMapping("/hello")
    public String hello() {
        ReadWebPage("http://localhost:8081/"); 
        return "Hello \n" + " v 15 \n" + "\n" + AZ + " \n " + urlt + "\n" ;
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
