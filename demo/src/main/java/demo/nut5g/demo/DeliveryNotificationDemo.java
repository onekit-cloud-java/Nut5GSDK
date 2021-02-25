package demo.nut5g.demo;

import cn.onekit.cloud.nut5g.notification.DeliveryNotification;
import cn.onekit.cloud.nut5g.notification.request.StatusNotification;
import cn.onekit.thekit.FileDB;
import cn.onekit.thekit.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/nut5g/deliveryNotification/sip:2021020501@botplatform.rcs.chinaunicom.cn")
public class DeliveryNotificationDemo {
    @RequestMapping(value = "/status",method = RequestMethod.POST)
    public void status(
            HttpServletRequest request
    ) throws Exception {
        //String accessToken = FileDB.get("demo","accessToken").value;
        StatusNotification data =  DeliveryNotification.status(request, DemoApplication.accessToken);
        String json = JSON.object2string(data);
        System.out.println(json);
        FileDB.set("status",new Date().toString(),json);
    }
}
