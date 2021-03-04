package demo.nut5g.demo;

import cn.onekit.cloud.nut5g.notification.NotifyPath;
import cn.onekit.thekit.ERROR;
import cn.onekit.thekit.FileDB;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("/nut5g")
public class NotifyPathDemo {
    @RequestMapping(value = "/notifyPath",method = RequestMethod.GET)
    public void notifypath(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        try {
            new NotifyPath(Nut5GAccount.appid,request,response,Nut5GAccount.signKey).notifyPath();
        } catch (Exception e) {
            e.printStackTrace();
            FileDB.set("error", new Date().toString(), ERROR.toString(e));
        }
    }
}
