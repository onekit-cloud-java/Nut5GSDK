package demo.nut5g.demo;

import cn.onekit.cloud.nut5g.notification.MessageNotification;
import cn.onekit.cloud.nut5g.notification.NotifyInfoNotification;
import cn.onekit.cloud.nut5g.notification.request.ReceivemessageNotification;
import cn.onekit.thekit.FileDB;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/nut5g/messageNotification/sip:2021020501@botplatform.rcs.chinaunicom.cn")
public class MessageNotificationDemo {

    @RequestMapping(value = "/messages",method = RequestMethod.POST)
    public ReceivemessageNotification recievemessages(
            HttpServletRequest request
    ) throws Exception {
        //String accessToken = FileDB.get("demo","accessToken").value;
        return MessageNotification.receivemessage(request, DemoApplication.accessToken);
    }

}
