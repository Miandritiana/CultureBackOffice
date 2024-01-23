package back.backoffice_culture.Controller;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UrlCheckController {
    private final String siteIsUp = "Yes";
    private final String siteIsDown = "No";
    private final String incorrectUrl = "Incorrect";
    @GetMapping("/check")

    public String getUrl(@RequestParam String url) {
        String returnMessage="";
        try {
            URL urlObj = new URL(url);
            try {
                HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                int responseCodeCategory=conn.getResponseCode() / 100;
                if(responseCodeCategory != 2 || responseCodeCategory != 3 )  {
                    returnMessage= siteIsDown;
                }else{
                    returnMessage= siteIsUp;
                }
            } catch (IOException e) {
                returnMessage= siteIsDown;
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
           returnMessage = incorrectUrl;
            e.printStackTrace();
        } 
        return returnMessage;
    }
    

}
