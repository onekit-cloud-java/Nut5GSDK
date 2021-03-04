package demo.nut5g.demo;

import cn.onekit.cloud.nut5g.notification.request.ReceivemessageNotification;
import cn.onekit.thekit.AJAX;
import cn.onekit.thekit.FileDB;
import cn.onekit.thekit.JSON;
import cn.onekit.thekit.SIGN;

import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.util.*;

import static cn.onekit.cloud.nut5g.notification.request.ReceivemessageNotification.*;

public class CallNotifyDemo {
    public static void main(String[] args) throws Exception {
        disableSslVerification();
      //  informationChange();
       recievemessage();
    }

    public static void disableSslVerification() {
        try
        {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }
            };
            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static Map<String,String> _sign() throws Exception {
        long timestamp = new Date().getTime();
        UUID nonce = UUID.randomUUID();
        ///////////////////////////////////////////////
        List<String> list = Arrays.asList(Nut5GAccount.signKey, String.valueOf(timestamp), nonce.toString());
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o1);
            }
        });
        String str = String.join("", list);


        String signature = new SIGN(SIGN.Method.HMACSHA256).sign(str);
        ////////////////////////////////////
        return new HashMap<String, String>() {{
            put("timestamp", String.valueOf(timestamp));
            put("nonce", String.valueOf(nonce));
            put("signature", signature);
            put("Content-Type","application/json");
        }};

    }

    public static void  informationChange() throws Exception {
        String url = "http://app.onekit.cn:9080/nut5g/notifyInfoNotification/sip:2021020501@botplatform.rcs.chinaunicom.cn/notice/informationChange";
        AJAX.headers = _sign();
        AJAX.request(url, "post", "");

    }
    public static void  rcsspam() throws Exception {
        String url = "https://app.onekit.cn:9443/nut5g/notifyInfoNotification/sip:2021020501@botplatform.rcs.chinaunicom.cn/notice/rcsspam";
        AJAX.headers = _sign();
        AJAX.request(url);
    }
    public static void  check() throws Exception {
        String url = "https://app.onekit.cn:9443/nut5g/notifyInfoNotification/sip:2021020501@botplatform.rcs.chinaunicom.cn/notice/check";
        AJAX.headers = _sign();
        AJAX.request(url);
    }

    public static void  recievemessage() throws Exception {
        String url = "http://app.onekit.cn:9080/nut5g/messageNotification/sip:2021020501@botplatform.rcs.chinaunicom.cn/messages";
        AJAX.headers = _sign();
        ReceivemessageNotification receivemessageNotification = new ReceivemessageNotification();
        receivemessageNotification.setMessageId("4BF4F950-A0B6-4CC3-86B4-5A9580399BCA");
        receivemessageNotification.setDateTime("2020-01-17T14:42:20.840+08:00");
                receivemessageNotification.setDestinationAddress(Nut5GAccount.chatbotId);
        receivemessageNotification.setSenderAddress("tel:+8618161274077");
                receivemessageNotification.setContributionId("SFF$#REGFY7&^%THT");
                receivemessageNotification.setConversationId("XSFDSFDFSAFDSAS^%");
        TextMessage textMessage = new TextMessage();
        textMessage.setContentText("hello world");
        receivemessageNotification.setMessageList(new ArrayList<Message>(){{
            add(textMessage);
        }});
        AJAX.request(url,"post", JSON.object2string(receivemessageNotification));

    }

    public static void  status() throws Exception {
        String url = "https://localhost:9443/nut5g/deliveryNotification/sip:2021020501@botplatform.rcs.chinaunicom.cn/status";
        AJAX.headers = _sign();
        AJAX.request(url);
    }
}
