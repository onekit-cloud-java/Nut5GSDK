package demo.nut5g.demo;

import cn.onekit.cloud.nut5g.notification.MessageNotification;
import cn.onekit.cloud.nut5g.notification.request.ReceivemessageNotification;
import cn.onekit.thekit.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/nut5g/messageNotification/sip:2021020501@botplatform.rcs.chinaunicom.cn")
public class MessageNotificationDemo {

    @RequestMapping(value = "/messages1", method = RequestMethod.POST)
    public void recievemessages1(
            HttpServletRequest request
    ) throws Exception {
        ReceivemessageNotification data = MessageNotification.receivemessage(request, DemoApplication.accessToken);

        System.out.println(JSON.object2string(data));

    }
}
