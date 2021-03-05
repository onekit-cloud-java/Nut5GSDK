package cn.onekit.cloud.notificationsdk;


import com.msg5g.maap.notification.NotificationAPI;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("unused")
public abstract class NotificationSDK implements NotificationAPI {

    protected final HttpServletRequest request;
    protected final String appId;
    protected final HttpServletResponse response;


    public NotificationSDK(String appId, HttpServletRequest request, HttpServletResponse response, String token) throws Exception {
        this.appId=appId;
        this.response=response;
        this.request=request;
        _checkSign(request,token);
    }


}
