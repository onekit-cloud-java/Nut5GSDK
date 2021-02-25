package demo.nut5g.demo;

import cn.onekit.cloud.nut5g.notification.NotifyInfoNotification;
import cn.onekit.cloud.nut5g.notification.request.CheckNotification;
import cn.onekit.cloud.nut5g.notification.request.RcsspamNotification;
import cn.onekit.cloud.nut5g.request.*;
import cn.onekit.cloud.nut5g.response.*;
import cn.onekit.cloud.nut5gsdk.Nut5GSDK;
import cn.onekit.thekit.FileDB;
import cn.onekit.thekit.JSON;
import cn.onekit.thekit.STRING;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.*;


@RestController
@RequestMapping("/nut5g/notifyInfoNotification/sip:2021020501@botplatform.rcs.chinaunicom.cn/notice")
public class NotifyInfoNotificationDemo {

    @RequestMapping(value = "/informationChange",method = RequestMethod.POST)
    public void informationChange(
             HttpServletRequest request
    ) throws Exception {
        //String accessToken = FileDB.get("demo","accessToken").value;
        NotifyInfoNotification.informationChange(request,DemoApplication.accessToken);
    }

    @RequestMapping(value = "/rcsspam",method = RequestMethod.POST)
    public void rcsspam(
            HttpServletRequest request
    ) throws Exception {
    //    String accessToken = FileDB.get("demo","accessToken").value;
        RcsspamNotification data =  NotifyInfoNotification.rcsspam(request,DemoApplication.accessToken);
        String json = JSON.object2string(data);
        System.out.println(json);
        FileDB.set("rcsspam",new Date().toString(),json);
    }

    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public void checkmessage(
            HttpServletRequest request
    ) throws Exception {
       // String accessToken = FileDB.get("demo","accessToken").value;
        CheckNotification data = NotifyInfoNotification.check(request,DemoApplication.accessToken);
        String json = JSON.object2string(data);
        System.out.println(json);
        FileDB.set("checkmessage",new Date().toString(),json);
    }






}