package demo.nut5g.demo;

import cn.onekit.cloud.nut5g.BadSignException;
import cn.onekit.thekit.SIGN;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class DemoApplication {

public static  String accessToken = "52bf3f57-5bea-481f-9389-6425fade6778";
//    @Bean
//    public ServletWebServerFactory webServerFactory() {
//        TomcatServletWebServerFactory fa = new TomcatServletWebServerFactory();
//        fa.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> connector.setProperty("relaxedQueryChars", "[]{}:@%."));
//        return fa;
//    }

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);


        long timestamp = new Date().getTime() ;
        UUID nonce = UUID.randomUUID();
        ///////////////////////////////////////////////
        List<String> list = Arrays.asList(accessToken, String.valueOf(timestamp), nonce.toString());
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o1);
            }
        });
        String str = String.join("", list);


        try {
            String signature = new SIGN(SIGN.Method.HMACSHA256).sign(str);
            ////////////////////////////////////
            System.out.println("timestamp "+timestamp);
            System.out.println("nonce "+nonce);
            System.out.println("signature "+signature);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
