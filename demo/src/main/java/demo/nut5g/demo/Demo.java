package demo.nut5g.demo;

import cn.onekit.cloud.nut5g.notification.NotifyInfoNotification;
import cn.onekit.cloud.nut5g.request.*;
import cn.onekit.cloud.nut5g.response.*;
import cn.onekit.cloud.nut5gsdk.Nut5GSDK;
import cn.onekit.thekit.FileDB;
import cn.onekit.thekit.JSON;
import cn.onekit.thekit.STRING;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.*;


@RestController
@RequestMapping("/nut5g")
public class Demo {
    private Nut5GSDK sdk = new Nut5GSDK(STRING.format("http://{serverRoot}/bot/{apiVersion}/{chatbotId}",new HashMap<String,Object>(){{
        put("serverRoot","maap.5g-msg.com:30001");
        put("apiVersion","v1");
        put("chatbotId","sip%3A2021020501%40botplatform.rcs.chinaunicom.cn");
    }}));

    @RequestMapping("/getAccessToken")
    public AccessTokenResponse getAccessToken() throws Exception{
        AccessTokenRequest request = new AccessTokenRequest();
        request.setAppId(Nut5GAccount.appid);
        request.setAppKey(Nut5GAccount.secret);
        AccessTokenResponse response = sdk.accessToken(request);
        FileDB.set("demo","accessToken",response.getAccessToken());
        return response;
    }

    @RequestMapping("/chatBotInfo")
    public void chatBotInfo() throws Exception {
        ChatBotInfoRequest request = new ChatBotInfoRequest();
        request.setCallBackNumber("12345678912");
        request.setThemeColour("#000000");
        request.setEmailAddress("example@test.com");
        request.setBackgroundImage("https://xxxx/xx.png");
        String accessToken = FileDB.get("demo","accessToken").value;
        sdk.chatBotInfo(accessToken,request);
    }


    @RequestMapping("/chatBotInfomenu")
    public void chatBotInfomenu() throws Exception {
        ChatBotInfomenuRequest request = new ChatBotInfomenuRequest();
        String accessToken = FileDB.get("demo","accessToken").value;
        sdk.chatBotInfomenu(accessToken,request);
    }

    @RequestMapping("/findchatBotInfo")
    public FindchatBotInfoResponse findchatBotInfo() throws Exception {
        String accessToken = FileDB.get("demo","accessToken").value;
        return sdk.findchatBotInfo(accessToken);
    }

    @RequestMapping("/mediasupload")
    public MediasuploadResponse mediasupload() throws Exception {
        String accessToken = FileDB.get("demo","accessToken").value;
        File file = new File("E:\\result.png");
        BufferedImage read = ImageIO.read(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(read, "png", baos);
        byte[] bytes = baos.toByteArray();
        return sdk.mediasupload(accessToken,"temp",new HashMap<String, byte[]>(){{
            put("file1",bytes);
        }});

    }

    @RequestMapping("/mediasdownload")
    public byte[] mediasdownload() throws Exception {
        String accessToken = FileDB.get("demo","accessToken").value;
        String url = "https://www.baidu.com/img/dong_8f1d47bcb77d74a1e029d8cbb3b33854.gif";
        return sdk.mediasdownload(accessToken,url);
    }

    @RequestMapping("/mediasdelete")
    public MediasdeleteResponse mediasdelete() throws Exception {
        String accessToken = FileDB.get("demo","accessToken").value;
        String url = "http://maap.5g-msg.com:30001/bot/v1/medias/fid/524397271800463360";
        return sdk.mediasdelete(accessToken,url);
    }

    @RequestMapping("/messages")
    public MessagesResponse messages() throws Exception {
        String accessToken = FileDB.get("demo","accessToken").value;
        MessagesRequest request = new MessagesRequest();
        request.setMessageId("cb1188a3-37ec-1037-9054-2dc66e44375b");
        ArrayList<String> address = new ArrayList<>();
        address.add("tel:+8618161274077");
        request.setDestinationAddress(address);
        request.setSenderAddress("sip:2021020501@botplatform.rcs.chinaunicom.cn");
        request.setSmsSupported(false);
        request.setSmsContent("hello world");
        request.setContributionId("SFF$#REGFY7&^%THT");
        request.setConversationId("XSFDSFDFSAFDSAS^%");
        MessagesRequest.ServiceCapability serviceCapability = new MessagesRequest.ServiceCapability();
        serviceCapability.setVersion("+g.gsma.rcs.botversion=\\\"#=1\\\"");
        request.setServiceCapability(Arrays.asList(serviceCapability));

        MessagesRequest.TextMessage textMessage = new MessagesRequest.TextMessage();
        textMessage.setContentText("hello world");

        MessagesRequest.BotsuggestionMessage botsuggestionMessage = new MessagesRequest.BotsuggestionMessage();
        MessagesRequest.BotsuggestionMessage.ContentText contentText = new MessagesRequest.BotsuggestionMessage.ContentText();
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion suggestion = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion();
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Reply reply = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Reply();
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Reply.Postback postback = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Reply.Postback();
        postback.setData("set_by_chatbot_reply_yes");
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action action1 = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action();
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.Postback postback1 = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.Postback();
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.UrlAction urlAction = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.UrlAction();
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.UrlAction.OpenUrl openUrl = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.UrlAction.OpenUrl();
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.DialerAction dialerAction = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.DialerAction();
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.DialerAction.DialPhoneNumber dialPhoneNumber = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.DialerAction.DialPhoneNumber();
        dialPhoneNumber.setPhoneNumber("+8617928222350");
        dialerAction.setDialPhoneNumber(dialPhoneNumber);
        openUrl.setUrl("https://www.10010.com");
        urlAction.setOpenUrl(openUrl);
        postback1.setData("set_by_chatbot_open_url");

        action1.setDisplayText("Open website or deep link");
        action1.setUrlAction(urlAction);
        action1.setPostback(postback1);

        reply.setDisplayText("Yes");
        reply.setPostback(postback);

        suggestion.setReply(reply);
        suggestion.setAction(action1);
        List<MessagesRequest.BotsuggestionMessage.ContentText.Suggestion> suggestionlist = new  ArrayList<>();
        suggestionlist.add(suggestion);


        contentText.setSuggestions(suggestionlist);
        botsuggestionMessage.setContentText(contentText);

        List<MessagesRequest.Message> messageList = new ArrayList<>();
        messageList.add(textMessage);
        messageList.add(botsuggestionMessage);

        request.setMessageList(messageList);
        System.out.println(JSON.object2string(messageList));
        return sdk.messages(accessToken,request);
    }

    @RequestMapping("/messagesrevoke")
    public MessagesrevokeResponse messagesrevoke() throws Exception {
        String accessToken = FileDB.get("demo","accessToken").value;
        MessagesrevokeRequest messagesrevokeRequest = new MessagesrevokeRequest();
        messagesrevokeRequest.setMessageId("cb1188a3-37ec-1037-9054-2dc66e44375b");
        List<String> list = new ArrayList<>();
        list.add("tel:+8618161274077");
        messagesrevokeRequest.setDestinationAddress(list);
        return sdk.messagesrevoke(accessToken,messagesrevokeRequest);
    }
//
//    @RequestMapping("/messageNotification/sip%3A2021020501%40botplatform.rcs.chinaunicom.cn/messages")
//    public void recievemessage(
//            @RequestParam HttpServletRequest request
//    ) throws Exception {
//       // String accessToken = FileDB.get("demo","accessToken").value;
//       // Nut5GNotify.receivemessage(request,DemoApplication.accessToken);
//
//    }
//    @RequestMapping("/deliveryNotification/sip%3A2021020501%40botplatform.rcs.chinaunicom.cn/status")
//    public void status(
//            @RequestParam HttpServletRequest request
//    ) throws Exception {
//        //String accessToken = FileDB.get("demo","accessToken").value;
//       // Nut5GNotify.status(request,DemoApplication.accessToken);
//    }
//    @RequestMapping("/notifyInfoNotification")
//    public void informationChange(
//            @RequestParam HttpServletRequest request
//    ) throws Exception {
//        //String accessToken = FileDB.get("demo","accessToken").value;
//        NotifyInfoNotification.informationChange(request,DemoApplication.accessToken);
//    }
//
//    @RequestMapping("/notifyInfoNotification/sip%3A2021020501%40botplatform.rcs.chinaunicom.cn/notice/rcsspam")
//    public void rcsspam(
//            @RequestParam HttpServletRequest request
//    ) throws Exception {
//    //    String accessToken = FileDB.get("demo","accessToken").value;
//        NotifyInfoNotification.rcsspam(request,DemoApplication.accessToken);
//    }
//
//    @RequestMapping("/notifyInfoNotification/sip%3A2021020501%40botplatform.rcs.chinaunicom.cn/check")
//    public void checkmessage(
//            @RequestParam HttpServletRequest request
//    ) throws Exception {
//       // String accessToken = FileDB.get("demo","accessToken").value;
//        NotifyInfoNotification.check(request,DemoApplication.accessToken);
//    }






}
