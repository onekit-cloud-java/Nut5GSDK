package cn.onekit.cloud.notificationsdk;


import cn.onekit.thekit.AJAX;
import cn.onekit.thekit.JSON;
import com.msg5g.maap.notification.DeliveryNotificationAPI;
import com.msg5g.maap.notification.MessageNotificationAPI;
import com.msg5g.maap.notification.receive.ReceivemessageNotification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("unused")
public class MessageNotificationSDK extends NotificationSDK implements MessageNotificationAPI {


    public MessageNotificationSDK(String appId, HttpServletRequest request, HttpServletResponse response, String token) throws Exception {
        super(appId, request, response, token);
        _checkSign(request,token);
    }

    public ReceivemessageNotification receivemessage() throws Exception{
        return JSON.string2object(AJAX.reveive(request),ReceivemessageNotification.class);
    }






}
