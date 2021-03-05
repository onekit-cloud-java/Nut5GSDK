package cn.onekit.cloud.notificationsdk;


import cn.onekit.thekit.AJAX;
import cn.onekit.thekit.JSON;
import com.msg5g.maap.notification.DeliveryNotificationAPI;
import com.msg5g.maap.notification.receive.StatusNotification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("unused")
public class DeliveryNotificationSDK extends NotificationSDK implements DeliveryNotificationAPI  {


    public DeliveryNotificationSDK(String appId, HttpServletRequest request, HttpServletResponse response, String token) throws Exception {
        super(appId, request, response, token);
        _checkSign(request,token);
    }

    public StatusNotification status() throws Exception{
        return JSON.string2object(AJAX.reveive(request),StatusNotification.class);
    }


}
