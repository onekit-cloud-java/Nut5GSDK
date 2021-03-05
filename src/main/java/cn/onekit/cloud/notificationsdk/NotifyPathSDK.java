package cn.onekit.cloud.notificationsdk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NotifyPathSDK extends NotificationSDK {



    public NotifyPathSDK(String appId, HttpServletRequest request, HttpServletResponse response, String token) throws Exception {
        super(appId,request,response,token);
        _checkSign(request,token);
    }

    public void notifyPath() throws IOException {
         final String echoStr=request.getParameter("echoStr");
        response.setHeader("appid",appId);
        response.setHeader("echoStr",echoStr);
        response.flushBuffer();
    }
}
