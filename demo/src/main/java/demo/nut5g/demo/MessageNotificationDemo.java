package demo.nut5g.demo;

import cn.onekit.cloud.nut5g.notification.MessageNotification;
import cn.onekit.cloud.nut5g.notification.request.ReceivemessageNotification;
import cn.onekit.thekit.FileDB;
import cn.onekit.thekit.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@RestController
@RequestMapping("/nut5g/messageNotification/sip:2021020501@botplatform.rcs.chinaunicom.cn")
public class MessageNotificationDemo {

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public void recievemessages(
            HttpServletRequest request
    ) {
        try {
            ReceivemessageNotification data = MessageNotification.receivemessage(request, DemoApplication.accessToken);
            FileDB.set("recievemessages", new Date().toString(), JSON.object2string(data));
            System.out.println(JSON.object2string(data));
        } catch (Exception e) {
            e.printStackTrace();
            FileDB.set("error", new Date().toString(), e.getMessage()
                    +"\n"+e.getStackTrace()[0].toString()
                    +"\n"+e.getStackTrace()[1].toString());
        }

    }
}
