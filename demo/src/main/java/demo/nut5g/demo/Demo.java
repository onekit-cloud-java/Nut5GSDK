package demo.nut5g.demo;

import cn.onekit.cloud.nut5g.request.AccessTokenRequest;
import cn.onekit.cloud.nut5g.request.ChatBotInfoRequest;
import cn.onekit.cloud.nut5g.request.ChatBotInfomenuRequest;
import cn.onekit.cloud.nut5g.response.AccessTokenResponse;
import cn.onekit.cloud.nut5g.response.FindchatBotInfoResponse;
import cn.onekit.cloud.nut5g.response.Nut5GResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Demo {
    private Nut5GSDK sdk = new Nut5GSDK("http://maap.5g-msg.com:30001/bot/v1/sip%3A2021020501%40botplatform.rcs.chinaunicom.cn");

    @RequestMapping("/getAccessToken")
    public AccessTokenResponse getAccessToken() throws Exception{
        AccessTokenRequest request = new AccessTokenRequest();
        request.setAppId(Nut5GAccount.appid);
        request.setAppKey(Nut5GAccount.secret);
        return sdk.accessToken(request);
    }

    @RequestMapping("/chatBotInfo")
    public Nut5GResponse chatBotInfo() throws Exception {
        ChatBotInfoRequest request = new ChatBotInfoRequest();
        return sdk.chatBotInfo(request);
    }

    @RequestMapping("/chatBotInfomenu")
    public Nut5GResponse chatBotInfomenu() throws Exception {
        ChatBotInfomenuRequest request = new ChatBotInfomenuRequest();
        return sdk.chatBotInfomenu(request);
    }

    @RequestMapping("/findchatBotInfo")
    public FindchatBotInfoResponse findchatBotInfo() throws Exception {
        return sdk.findchatBotInfo();
    }

    @RequestMapping("/mediasupload")
    public FindchatBotInfoResponse mediasupload() throws Exception {

        return null;
    }


}
