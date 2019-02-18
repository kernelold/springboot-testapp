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
       
        String AZ = "unknown";
        String Hostname = System.getenv("HOSTNAME");
        String Region = System.getenv("AWS_REGION");
        if(Hostname.matches("(.*)ip-10-0-0(.*)"))
        {
            AZ = "us-west-1a" ;
        } else if(Hostname.matches("(.*)ip-10-0-1(.*)"))
        {
            AZ = "us-west-1c" ;
        } else {
            AZ = "not match";
        }  
        String Placement = "Region is " + Region + "\n" + "Hostname is " + Hostname + "\n" + "AZ is " + AZ + "\n";
        
        String TimeappUrl = System.getenv("TIMEAPP_URL");
        ReadWebPage(TimeappUrl);
        String Timenow = urlt;

        return "Hello \n" + Placement + "Time now is " + Timenow + "\n";
    }
    
    @RequestMapping("/meta")
    public String meta() {

        String contmeta = System.getenv("ECS_CONTAINER_METADATA_URI");
        ReadWebPage(contmeta);
        String Meta = urlt;

        return Meta + "\n";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
