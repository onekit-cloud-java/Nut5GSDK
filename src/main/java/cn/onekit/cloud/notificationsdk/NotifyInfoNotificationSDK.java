package cn.onekit.cloud.notificationsdk;

import cn.onekit.thekit.AJAX;
import cn.onekit.thekit.JSON;
import com.msg5g.maap.notification.NotifyInfoNotificationAPI;
import com.msg5g.maap.notification.receive.CheckNotification;
import com.msg5g.maap.notification.receive.RcsspamNotification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("unused")
public class NotifyInfoNotificationSDK extends NotificationSDK implements NotifyInfoNotificationAPI {


    public NotifyInfoNotificationSDK(String appId, HttpServletRequest request, HttpServletResponse response, String token) throws Exception {
        super(appId, request, response, token);
        _checkSign(request,token);
    }

    public  void informationChange() throws Exception{

    }

    public RcsspamNotification rcsspam() throws Exception{
        return JSON.string2object(AJAX.reveive(request),RcsspamNotification.class);
    }

    public CheckNotification check() throws Exception {
        return JSON.string2object(AJAX.reveive(request),CheckNotification.class);
    }


}
