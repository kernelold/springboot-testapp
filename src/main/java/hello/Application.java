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

    @RequestMapping("/")
    public String home() {
        return "Hello \n";
    }

    @RequestMapping("/hello")
    public String hello() {
        String Region = "Region is " + System.getenv("AWS_REGION") + "\n" + "Hostname is " + System.getenv("HOSTNAME") + "\n";
        String TimeappUrl = System.getenv("TIMEAPP_URL");
        ReadWebPage(TimeappUrl); 
        String Timenow = urlt;
        String contmeta = System.getenv("ECS_CONTAINER_METADATA_URI");
        ReadWebPage(contmeta);
        String Meta = urlt;
        return "Hello \n" + "v 33 \n" + "\n" + Region + " \n " + Timenow + "\n" + "Container meta is " + Meta + "\n";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
