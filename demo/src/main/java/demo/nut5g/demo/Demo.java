package demo.nut5g.demo;

import cn.onekit.cloud.nut5g.request.*;
import cn.onekit.cloud.nut5g.response.*;
import cn.onekit.cloud.nut5gsdk.Nut5GSDK;
import cn.onekit.thekit.FileDB;
import cn.onekit.thekit.JSON;
import cn.onekit.thekit.STRING;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.*;

import static cn.onekit.cloud.nut5g.request.MessagesRequest.*;


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
        request.setAppKey(Nut5GAccount.appKey);
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
        String userHome = System.getProperty("user.home");
        File file = new File(userHome+"\\a.png");
        BufferedImage read = ImageIO.read(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(read, "png", baos);
        byte[] bytes = baos.toByteArray();
        MediasuploadResponse  response =  sdk.mediasupload(accessToken,"temp",new HashMap<String, byte[]>(){{
            put("file1",bytes);
        }});
        List<MediasuploadResponse.FileInfo> fileInfo = response.getFileInfo();
        String url = fileInfo.get(0).getUrl();
        FileDB.set("mediasupload","url",url);
        return response;

    }

    @RequestMapping("/mediasdownload")
    public byte[] mediasdownload() throws Exception {
        String accessToken = FileDB.get("demo","accessToken").value;
        System.out.println(accessToken);
        String url = "https://www.baidu.com/img/dong_8f1d47bcb77d74a1e029d8cbb3b33854.gif";
        return sdk.mediasdownload(accessToken,url);
    }

    @RequestMapping("/mediasdelete")
    public MediasdeleteResponse mediasdelete() throws Exception {
        String accessToken = FileDB.get("demo","accessToken").value;
        String url = FileDB.get("mediasupload","url").value;
        return sdk.mediasdelete(accessToken,url);
    }

    @RequestMapping("/messages1")
    public MessagesResponse messages1() throws Exception {
        String accessToken = FileDB.get("demo","accessToken").value;
        MessagesRequest request = new MessagesRequest();
        request.setMessageId("cb1188a3-37ec-1037-9054-2dc66e44375b");
        ArrayList<String> address = new ArrayList<>();
        address.add("tel:+8618161274077");
        request.setDestinationAddress(address);
        request.setSenderAddress("sip:2021020501@botplatform.rcs.chinaunicom.cn");
        request.setSmsSupported(false);
        request.setSmsContent("hello world!");
        request.setContributionId("SFF$#REGFY7&^%THT");
        request.setConversationId("XSFDSFDFSAFDSAS^%");
        ServiceCapability serviceCapability = new ServiceCapability();
        serviceCapability.setVersion("+g.gsma.rcs.botversion=\\\"#=1\\\"");
        request.setServiceCapability(Arrays.asList(serviceCapability));
        //////////////////////////////
        FileMessage fileMessage = new FileMessage();

        FileMessage.ContentText contentText1 = new FileMessage.ContentText();
        contentText1.setType("thumbnail");
        contentText1.setFileSize("7427");
        contentText1.setContentType("image/png");
        contentText1.setUrl("http://maap.5g-msg.com:30001/bot/v1/medias/fid/526265517512179712");
        contentText1.setUntil("2019-04-25T12:17:07Z");
        FileMessage.ContentText contentText2 = new FileMessage.ContentText();
        contentText2.setType("file");
        contentText2.setFileSize("6617");
        contentText2.setFileName("result.png");
        contentText2.setContentType("image/png");
        contentText2.setUrl("http://maap.5g-msg.com:30001/bot/v1/medias/fid/526265517512179712");
        contentText2.setUntil("2019-04-25T12:17:07Z");
        List<FileMessage.ContentText> contentTextlist = new ArrayList<>();
        contentTextlist.add(contentText1);
        contentTextlist.add(contentText2);
        fileMessage.setContentText(contentTextlist);
        fileMessage.setContentEncoding("utf8");

        List<MessagesRequest.Message> messageList = new ArrayList<>();
        messageList.add(fileMessage);
        request.setMessageList(messageList);
        System.out.println(JSON.object2string(request));
        return sdk.messages(accessToken,request);
    }

    @RequestMapping("/messages2")
    public MessagesResponse messages2() throws Exception {
        String accessToken = FileDB.get("demo", "accessToken").value;
        MessagesRequest request = new MessagesRequest();
        request.setMessageId("cb1188a3-37ec-1037-9054-2dc66e44375b");
        ArrayList<String> address = new ArrayList<>();
        address.add("tel:+8618161274077");
        request.setDestinationAddress(address);
        request.setSenderAddress("sip:2021020501@botplatform.rcs.chinaunicom.cn");
        request.setSmsSupported(false);
        request.setSmsContent("hello world!");
        request.setContributionId("SFF$#REGFY7&^%THT");
        request.setConversationId("XSFDSFDFSAFDSAS^%");
        ServiceCapability serviceCapability = new ServiceCapability();
        serviceCapability.setVersion("+g.gsma.rcs.botversion=\\\"#=1\\\"");
        request.setServiceCapability(Arrays.asList(serviceCapability));
        ////////////文本消息+消息回落///////////////
        TextMessage textMessage = new TextMessage();

        textMessage.setContentText("hello world");
        textMessage.setContentEncoding("utf-8");

        List<MessagesRequest.Message> messageList = new ArrayList<>();
        messageList.add(textMessage);

        request.setMessageList(messageList);
        System.out.println(JSON.object2string(request));
        return sdk.messages(accessToken, request);
    }

    @RequestMapping("/messages3")
    public MessagesResponse messages3() throws Exception {
        String accessToken = FileDB.get("demo", "accessToken").value;
        MessagesRequest request = new MessagesRequest();
        request.setMessageId("cb1188a3-37ec-1037-9054-2dc66e44375b");
        ArrayList<String> address = new ArrayList<>();
        address.add("tel:+8618161274077");
        request.setDestinationAddress(address);
        request.setSenderAddress("sip:2021020501@botplatform.rcs.chinaunicom.cn");
        request.setSmsSupported(false);
        request.setSmsContent("hello world!");
        request.setContributionId("SFF$#REGFY7&^%THT");
        request.setConversationId("XSFDSFDFSAFDSAS^%");
        ServiceCapability serviceCapability = new ServiceCapability();
        serviceCapability.setVersion("+g.gsma.rcs.botversion=\\\"#=1\\\"");
        request.setServiceCapability(Arrays.asList(serviceCapability));
        ////////////文本消息+悬浮菜单/////////////////
        TextMessage textMessage = new TextMessage();

        textMessage.setContentText("hello world");
        textMessage.setContentEncoding("utf-8");
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
        openUrl.setUrl("https://www.9443.com");
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

        request.setMessageList(messageList);
        System.out.println(JSON.object2string(messageList));

        request.setMessageList(messageList);
        System.out.println(JSON.object2string(request));
        return sdk.messages(accessToken, request);
    }

    @RequestMapping("/messages4")
    public MessagesResponse messages4() throws Exception {
        String accessToken = FileDB.get("demo", "accessToken").value;
        MessagesRequest request = new MessagesRequest();
        request.setMessageId("cb1188a3-37ec-1037-9054-2dc66e44375b");
        ArrayList<String> address = new ArrayList<>();
        address.add("tel:+8618161274077");
        request.setDestinationAddress(address);
        request.setSenderAddress("sip:2021020501@botplatform.rcs.chinaunicom.cn");
        request.setSmsSupported(false);
        request.setSmsContent("hello world!");
        request.setContributionId("SFF$#REGFY7&^%THT");
        request.setConversationId("XSFDSFDFSAFDSAS^%");
        ServiceCapability serviceCapability = new ServiceCapability();
        serviceCapability.setVersion("+g.gsma.rcs.botversion=\\\"#=1\\\"");
        request.setServiceCapability(Arrays.asList(serviceCapability));
        ///////地理消息////////////////////
        GeoMessage geoMessage = new GeoMessage();
        GeoMessage.ContentText contentText = new GeoMessage.ContentText();
        contentText.setLatitude(7.0914591f);
        contentText.setLongitude(50.7311865f);
        contentText.setCrs("gcj02");
        contentText.setU(10);
        contentText.setRcs_l("Qingfeng Steamed Dumpling Shop \uD83C\uDF5A");


        geoMessage.setContentText(contentText);

        List<MessagesRequest.Message> messageList = new ArrayList<>();
        messageList.add(geoMessage);

        request.setMessageList(messageList);
        System.out.println(JSON.object2string(request));

        return sdk.messages(accessToken, request);
    }

    @RequestMapping("/messages5")
    public MessagesResponse messages5() throws Exception {
        String accessToken = FileDB.get("demo", "accessToken").value;
        MessagesRequest request = new MessagesRequest();
        request.setMessageId("cb1188a3-37ec-1037-9054-2dc66e44375b");
        List<String> list = new ArrayList<>();
                list.add("tel:+8618161274077");
        request.setDestinationAddress(list);
        request.setTrafficType("advertisement");
        request.setSenderAddress("sip:2021020501@botplatform.rcs.chinaunicom.cn");
        request.setContributionId("SFF$#REGFY7&^%THT");
        request.setConversationId("XSFDSFDFSAFDSAS^%");
        ServiceCapability serviceCapability = new ServiceCapability();
        serviceCapability.setVersion("+g.gsma.rcs.botversion=\\\"#=1\\\"");
        request.setServiceCapability(Arrays.asList(serviceCapability));
        ///////卡片消息////////////////////
        MessagesRequest.Botmessage botMessage = new MessagesRequest.Botmessage();
        MessagesRequest.Botmessage.ContentText contentText = new MessagesRequest.Botmessage.ContentText();
        Botmessage.ContentText.RichMessage cssMessage = new Botmessage.ContentText.RichMessage();
        Botmessage.ContentText.RichMessage.GeneralPurposeCard generalPurposeCard = new Botmessage.ContentText.RichMessage.GeneralPurposeCard();
        Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content content = new Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content();
        Botmessage.ContentText.RichMessage.GeneralPurposeCard.Layout layout = new Botmessage.ContentText.RichMessage.GeneralPurposeCard.Layout();
        layout.setCardOrientation("HORIZONTAL");
        layout.setImageAlignment("LEFT");
        layout.setTitleFontStyle(new ArrayList<String>(){{
            add("underline");
            add("bold");
        }});
        layout.setDescriptionFontStyle(new ArrayList<String>(){{
            add("calibri");
        }});
        layout.setStyle("http://example.com/default.css");
        Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Media media = new Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Media();
        media.setMediaUrl("http://maap.5g-msg.com:30001/bot/v1/medias/fid/526573781936357376");
        media.setMediaContentType("image/png");
        media.setMediaFileSize(6617);
        media.setThumbnailUrl("http://maap.5g-msg.com:30001/bot/v1/medias/fid/526573781936357376");
        media.setThumbnailContentType("image/png");
        media.setThumbnailFileSize(6617);
        media.setHeight("MEDIUM_HEIGHT");
        media.setContentDescription("Textual description of media content, e. g. for use with screen readers");
        Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion suggestion1 = new Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion();
        Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion suggestion2 = new Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion();
        Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion suggestion3 = new Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion();
       ///////////////////////
        Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion.Action action1 = new Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion.Action();
        Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion.Reply reply = new Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion.Reply();

        Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion.Reply.Postback postback = new Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion.Reply.Postback();
        postback.setData("set_by_chatbot_reply_no");
        reply.setDisplayText("No");
        reply.setPostback(postback);
        Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion.Action.UrlAction urlAction = new Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion.Action.UrlAction();
        Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion.Action.UrlAction.OpenUrl openUrl = new Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion.Action.UrlAction.OpenUrl();
        openUrl.setUrl("https://www.9443.cn");
        openUrl.setApplication("webview");
        openUrl.setViewMode("half");
        urlAction.setOpenUrl(openUrl);
        Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion.Action.Postback postback1 = new Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion.Action.Postback();
        postback1.setData("set_by_chatbot_open_url");
        action1.setUrlAction(urlAction);
        action1.setDisplayText("Open website or deep link");
        action1.setPostback(postback1);
        suggestion1.setAction(action1);
        ///////////////////////
        Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion.Action action2 = new Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion.Action();
        Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion.Action.DialerAction dialerAction = new Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion.Action.DialerAction();
        Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion.Action.DialerAction.DialPhoneNumber dialPhoneNumber = new Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion.Action.DialerAction.DialPhoneNumber();
        dialPhoneNumber.setPhoneNumber("+8617928222350");

        dialerAction.setDialPhoneNumber(dialPhoneNumber);
        Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion.Action.Postback postback2 = new Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion.Action.Postback();
        postback2.setData("set_by_chatbot_open_dialer");
        action2.setDialerAction(dialerAction);
        action2.setDisplayText("Call a phone number");
        action2.setPostback(postback2);

        suggestion1.setAction(action1);
        suggestion2.setAction(action2);
        suggestion3.setReply(reply);

        content.setMedia(media);
        content.setSuggestions(new ArrayList<Botmessage.ContentText.RichMessage.GeneralPurposeCard.Content.Suggestion>(){{
            add(suggestion1);
            add(suggestion2);
            add(suggestion3);
        }});
        content.setDescription("This is the description of the rich card. It's the first field that will be truncated if it exceeds the maximum width or height of a card");
        content.setTitle("This is a single rich card");


        generalPurposeCard.setContent(content);
        generalPurposeCard.setLayout(layout);

        cssMessage.setGeneralPurposeCard(generalPurposeCard);
        contentText.setMessage(cssMessage);

        botMessage.setContentText(contentText);
        List<MessagesRequest.Message> messageList = new ArrayList<>();
        messageList.add(botMessage);
        request.setMessageList(messageList);
        System.out.println(JSON.object2string(messageList));

        return sdk.messages(accessToken, request);
    }

    @RequestMapping("/messages6")
    public MessagesResponse messages6() throws Exception {
        String accessToken = FileDB.get("demo", "accessToken").value;
        MessagesRequest request = new MessagesRequest();
        request.setMessageId("cb1188a3-37ec-1037-9054-2dc66e44375b");
        List<String> list = new ArrayList<>();
        list.add("tel:+8618161274077");
        request.setDestinationAddress(list);
        request.setTrafficType("advertisement");
        request.setSenderAddress("sip:2021020501@botplatform.rcs.chinaunicom.cn");
        request.setContributionId("SFF$#REGFY7&^%THT");
        request.setConversationId("XSFDSFDFSAFDSAS^%");
        ServiceCapability serviceCapability = new ServiceCapability();
        serviceCapability.setVersion("+g.gsma.rcs.botversion=\\\"#=1\\\"");
        request.setServiceCapability(Arrays.asList(serviceCapability));
        ///////多卡片消息////////////////////
        MessagesRequest.Botmessage botMessage = new MessagesRequest.Botmessage();
        MessagesRequest.Botmessage.ContentText contentText = new MessagesRequest.Botmessage.ContentText();
        Botmessage.ContentText.RichMessage cssMessage = new Botmessage.ContentText.RichMessage();
        Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel generalPurposeCardCarousel  = new Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel();
        Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content content1 = new Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content();
        Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content content2 = new Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content();
        content2.setDescription("This is the description of the rich card. It's the first field that will be truncated if it exceeds the maximum width or height of a card");
        content2.setTitle("This is a single rich card");

        Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Layout layout = new Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Layout();
        layout.setCardWidth("MEDIUM_WIDTH");
        generalPurposeCardCarousel.setLayout(layout);


        Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Media media = new Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Media();
        media.setMediaUrl("http://maap.5g-msg.com:30001/bot/v1/medias/fid/526573781936357376");
        media.setMediaContentType("image/png");
        media.setMediaFileSize(6617);
        media.setThumbnailUrl("http://maap.5g-msg.com:30001/bot/v1/medias/fid/526573781936357376");
        media.setThumbnailContentType("image/png");
        media.setThumbnailFileSize(6617);
        media.setHeight("SHORT_HEIGHT");
        content1.setMedia(media);
        content1.setDescription("This is the description of the rich card. It's the first field that will be truncated if it exceeds the maximum width or height of a card");
        content1.setTitle("This is a single rich card");

        Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion suggestion1 = new Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion();
        Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion.Action actiona = new Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion.Action();
        Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion.Action.Postback postbacka = new Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion.Action.Postback();
        postbacka.setData("set_by_chatbot_open_map");
        Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion.Action.MapAction mapAction = new Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion.Action.MapAction();
        Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion.Action.MapAction.ShowLocation showLocation = new Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion.Action.MapAction.ShowLocation();
        Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion.Action.MapAction.ShowLocation.Location location = new Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion.Action.MapAction.ShowLocation.Location();
        location.setLatitude(37.4220041f);
        location.setLongitude(-122.0862515f);
        location.setLabel("Googleplex");
        showLocation.setLocation(location);
        showLocation.setFallbackUrl("https://www.google.com/maps/@37.4219162,-122.078063,15z");
        mapAction.setShowLocation(showLocation);
        actiona.setMapAction(mapAction);
        actiona.setDisplayText("Show location on a map");
        actiona.setPostback(postbacka);
        suggestion1.setAction(actiona);

        Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion suggestion2 = new Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion();
        Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion.Action actionb = new Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion.Action();
        Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion.Action.Postback postbackb = new Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion.Action.Postback();
        postbackb.setData("set_by_chatbot_create_calendar_event");
        Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion.Action.CalendarAction calendarAction = new Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion.Action.CalendarAction();
        Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion.Action.CalendarAction.CreateCalendarEvent createCalendarEvent = new Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion.Action.CalendarAction.CreateCalendarEvent();
        createCalendarEvent.setStartTime("2021-03-14T00:00:00Z");
        createCalendarEvent.setEndTime("2021-03-14T23:59:59Z");
        createCalendarEvent.setTitle("Meeting");
        createCalendarEvent.setDescription("GSG review meeting");
        calendarAction.setCreateCalendarEvent(createCalendarEvent);
        actionb.setPostback(postbackb);
        actionb.setDisplayText("Schedule Meeting");
        actionb.setCalendarAction(calendarAction);
        suggestion2.setAction(actionb);

        List<Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content.Suggestion> suggestionlist = new  ArrayList<>();
        suggestionlist.add(suggestion1);
        suggestionlist.add(suggestion2);
        content1.setSuggestions(suggestionlist);
        List<Botmessage.ContentText.RichMessage.GeneralPurposeCardCarousel.Content> contentList = new ArrayList<>();
        contentList.add(content1);
        contentList.add(content2);


        generalPurposeCardCarousel.setContent(contentList);


        cssMessage.setGeneralPurposeCardCarousel(generalPurposeCardCarousel);
        contentText.setMessage(cssMessage);

        botMessage.setContentText(contentText);
        ////////////////////////////////////////////
        MessagesRequest.BotsuggestionMessage botsuggestionMessage = new MessagesRequest.BotsuggestionMessage();
        MessagesRequest.BotsuggestionMessage.ContentText botcontentText = new MessagesRequest.BotsuggestionMessage.ContentText();
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion botsuggestion1 = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion();
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion botsuggestion2 = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion();
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion botsuggestion3 = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion();
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion botsuggestion4 = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion();

        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Reply botreply1 = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Reply();
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Reply.Postback botpostback1 = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Reply.Postback();
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Reply botreply2 = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Reply();
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Reply.Postback botpostback2 = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Reply.Postback();
        botpostback1.setData("set_by_chatbot_reply_yes");
        botreply1.setPostback(botpostback1);
        botreply1.setDisplayText("Yes");
        botsuggestion1.setReply(botreply1);
        botpostback2.setData("set_by_chatbot_reply_no");
        botreply2.setPostback(botpostback2);
        botreply2.setDisplayText("No");
        botsuggestion2.setReply(botreply2);

        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action botaction1 = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action();
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.Postback botpostback3 = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.Postback();
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.UrlAction boturlAction1 = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.UrlAction();
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.UrlAction.OpenUrl botopenUrl1 = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.UrlAction.OpenUrl();

        botopenUrl1.setUrl("https://www.9443.com");
        boturlAction1.setOpenUrl(botopenUrl1);
        botpostback3.setData("set_by_chatbot_open_url");
        botaction1.setPostback(botpostback3);
        botaction1.setUrlAction(boturlAction1);
        botaction1.setDisplayText("Open website or deep link");
        botsuggestion3.setAction(botaction1);

        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action botaction2 = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action();
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.DialerAction botdialerAction = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.DialerAction();
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.DialerAction.DialPhoneNumber botdialPhoneNumber = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.DialerAction.DialPhoneNumber();
        MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.Postback botpostback4 = new MessagesRequest.BotsuggestionMessage.ContentText.Suggestion.Action.Postback();
        botpostback4.setData("set_by_chatbot_open_dialer");
        botdialPhoneNumber.setPhoneNumber("+8617928222350");
        botdialerAction.setDialPhoneNumber(botdialPhoneNumber);
        botaction2.setDialerAction(botdialerAction);
        botaction2.setPostback(botpostback4);
        botaction2.setDisplayText("Call a phone number");
        botsuggestion4.setAction(botaction2);

        List<MessagesRequest.BotsuggestionMessage.ContentText.Suggestion> suggestionlistb = new  ArrayList<>();
        suggestionlistb.add(botsuggestion1);
        suggestionlistb.add(botsuggestion2);
        suggestionlistb.add(botsuggestion3);
        suggestionlistb.add(botsuggestion4);

        botcontentText.setSuggestions(suggestionlistb);
        botsuggestionMessage.setContentText(botcontentText);
        ////////////////////////////////////////////


        ///////////////
        List<MessagesRequest.Message> messageList = new ArrayList<>();
        messageList.add(botMessage);

        request.setMessageList(messageList);

        System.out.println(JSON.object2string(request));

        return sdk.messages(accessToken, request);
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




}
