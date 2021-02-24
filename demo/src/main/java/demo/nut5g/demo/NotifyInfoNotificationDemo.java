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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/nut5g/notifyInfoNotification/sip:2021020501@botplatform.rcs.chinaunicom.cn/notice")
public class NotifyInfoNotificationDemo {

    @RequestMapping("/informationChange")
    public void informationChange(
            @RequestParam HttpServletRequest request
    ) throws Exception {
        //String accessToken = FileDB.get("demo","accessToken").value;
        NotifyInfoNotification.informationChange(request,DemoApplication.accessToken);
    }

    @RequestMapping("/rcsspam")
    public void rcsspam(
            @RequestParam HttpServletRequest request
    ) throws Exception {
    //    String accessToken = FileDB.get("demo","accessToken").value;
        NotifyInfoNotification.rcsspam(request,DemoApplication.accessToken);
    }

    @RequestMapping("/check")
    public void checkmessage(
            @RequestParam HttpServletRequest request
    ) throws Exception {
       // String accessToken = FileDB.get("demo","accessToken").value;
        NotifyInfoNotification.check(request,DemoApplication.accessToken);
    }






}
