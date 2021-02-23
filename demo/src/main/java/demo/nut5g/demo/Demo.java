package demo.nut5g.demo;

import cn.onekit.cloud.nut5g.request.AccessTokenRequest;
import cn.onekit.cloud.nut5g.request.ChatBotInfoRequest;
import cn.onekit.cloud.nut5g.request.ChatBotInfomenuRequest;
import cn.onekit.cloud.nut5g.response.AccessTokenResponse;
import cn.onekit.cloud.nut5g.response.FindchatBotInfoResponse;
import cn.onekit.cloud.nut5g.response.MediasuploadResponse;
import cn.onekit.cloud.nut5gsdk.Nut5GSDK;
import cn.onekit.thekit.STRING;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;

@RestController
@RequestMapping("/")
public class Demo {
    private Nut5GSDK sdk = new Nut5GSDK(STRING.format("http://{serverRoot}/bot/{apiVersion}/{chatbotId}",new HashMap<String,Object>(){{
        put("serverRoot","maap.5g-msg.com:30001");
        put("apiVersion","v1");
        put("chatbotId","sip%3A2021020501%40botplatform.rcs.chinaunicom.cn");
    }}));

    @RequestMapping("/getAccessToken")
    public AccessTokenResponse getAccessToken(HttpSession session) throws Exception{
        AccessTokenRequest request = new AccessTokenRequest();
        request.setAppId(Nut5GAccount.appid);
        request.setAppKey(Nut5GAccount.secret);
        AccessTokenResponse response = sdk.accessToken(request);
        session.setAttribute("accessToken",response.getAccessToken());
        return response;
    }

    @RequestMapping("/chatBotInfo")
    public void chatBotInfo(HttpSession session) throws Exception {
        ChatBotInfoRequest request = new ChatBotInfoRequest();
        request.setCallBackNumber("12345678912");
        request.setThemeColour("#000000");
        request.setEmailAddress("example@test.com");
        request.setBackgroundImage("https://xxxx/xx.png");
        String accessToken = session.getAttribute("accessToken").toString();
        sdk.chatBotInfo(accessToken,request);
    }

    @RequestMapping("/chatBotInfomenu")
    public void chatBotInfomenu(HttpSession session) throws Exception {
        ChatBotInfomenuRequest request = new ChatBotInfomenuRequest();
        String accessToken = session.getAttribute("accessToken").toString();
        sdk.chatBotInfomenu(accessToken,request);
    }

    @RequestMapping("/findchatBotInfo")
    public FindchatBotInfoResponse findchatBotInfo(HttpSession session) throws Exception {
        String accessToken = session.getAttribute("accessToken").toString();
        return sdk.findchatBotInfo(accessToken);
    }

    @RequestMapping("/mediasupload")
    public MediasuploadResponse mediasupload(HttpSession session) throws Exception {
        String accessToken = session.getAttribute("accessToken").toString();
        File file = new File("E:\\result.png");
        BufferedImage read = ImageIO.read(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(read, "png", baos);
        byte[] bytes = baos.toByteArray();
        return sdk.mediasupload(accessToken,"temp",new HashMap<String, byte[]>(){{
            put("file1",bytes);
        }});

    }


}
