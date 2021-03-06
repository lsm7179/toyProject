package com.lsm.toyproject.movie;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
public class ListController {
    String clientId = "clientId";//애플리케이션 클라이언트 아이디값";
    String clientSecret = "clientSecret";//애플리케이션 클라이언트 시크릿값";

    @RequestMapping("/")
    public void movieList(){
        try {
            String text= URLEncoder.encode("스파이더맨","UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/movie.json?query="+text;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            con.setDoOutput(true);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
