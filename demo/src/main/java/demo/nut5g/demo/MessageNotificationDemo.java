package demo.nut5g.demo;

import cn.onekit.cloud.nut5g.notification.MessageNotification;
import cn.onekit.cloud.nut5g.notification.request.ReceivemessageNotification;
import cn.onekit.cloud.nut5g.request.MessagesRequest;
import cn.onekit.thekit.FileDB;
import cn.onekit.thekit.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/nut5g/messageNotification/sip:2021020501@botplatform.rcs.chinaunicom.cn")
public class MessageNotificationDemo {

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public void recievemessages(
            HttpServletRequest request
    ) throws Exception {
        //String accessToken = FileDB.get("demo","accessToken").value;
        ReceivemessageNotification data = MessageNotification.receivemessage(request, DemoApplication.accessToken);
        ReceivemessageNotification.GeoMessage geoMessage = new ReceivemessageNotification.GeoMessage();
        ReceivemessageNotification.GeoMessage.ContentText contentText = new ReceivemessageNotification.GeoMessage.ContentText();
        contentText.setLatitude((float) 7.0914591);
        contentText.setLongitude((float) 50.7311865);
        contentText.setCrs("gcj02");
        contentText.setU(10);
        contentText.setRcs_l("Qingfeng%20Steamed%20Dumpling%20Shop%20%F0%9F%8D%9A");

        geoMessage.setContentText(contentText);

        List<ReceivemessageNotification.Message> messageList = new ArrayList<>();
        messageList.add(geoMessage);

        data.setMessageList(messageList);
        String json = JSON.object2string(data);
        System.out.println(JSON.object2string(contentText));
        FileDB.set("recievemessages",new Date().toString(),json);
    }
}
