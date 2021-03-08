package demo.nut5g.demo;

import cn.onekit.cloud.notificationsdk.NotifyInfoNotificationSDK;

import cn.onekit.thekit.ERROR;
import cn.onekit.thekit.FileDB;
import cn.onekit.thekit.JSON;

import com.msg5g.maap.notification.receive.CheckNotification;
import com.msg5g.maap.notification.receive.RcsspamNotification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;


@RestController
@RequestMapping("/nut5g/notifyInfoNotification/sip:2021020501@botplatform.rcs.chinaunicom.cn/notice")
public class NotifyInfoNotificationDemo {

    @RequestMapping(value = "/informationChange",method = RequestMethod.POST)
    public void informationChange(
             HttpServletRequest request,
             HttpServletResponse response
    ) throws Exception {
        try {

            new NotifyInfoNotificationSDK(Nut5GAccount.appid,request,response, Nut5GAccount.signKey).informationChange();
        } catch (Exception e) {
            e.printStackTrace();
            FileDB.set("error", new Date().toString(), ERROR.toString(e));
            throw e;
        }
    }

    @RequestMapping(value = "/rcsspam",method = RequestMethod.POST)
    public void rcsspam(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {
        try {

            RcsspamNotification data = new NotifyInfoNotificationSDK(Nut5GAccount.appid,request,response, Nut5GAccount.signKey).rcsspam();
            String json = JSON.object2string(data);
            System.out.println(json);
            FileDB.set("rcsspam", new Date().toString(), json);
        } catch (Exception e) {
            e.printStackTrace();
            FileDB.set("error", new Date().toString(), ERROR.toString(e));
            throw e;
        }
    }








}
