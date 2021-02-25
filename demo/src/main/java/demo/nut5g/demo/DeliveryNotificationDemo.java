package demo.nut5g.demo;

import cn.onekit.cloud.nut5g.notification.DeliveryNotification;
import cn.onekit.cloud.nut5g.notification.request.StatusNotification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/nut5g/deliveryNotification/sip:2021020501@botplatform.rcs.chinaunicom.cn")
public class DeliveryNotificationDemo {
    @RequestMapping(value = "/status",method = RequestMethod.POST)
    public StatusNotification status(
            HttpServletRequest request
    ) throws Exception {
        //String accessToken = FileDB.get("demo","accessToken").value;
        return DeliveryNotification.status(request, DemoApplication.accessToken);
    }
}
