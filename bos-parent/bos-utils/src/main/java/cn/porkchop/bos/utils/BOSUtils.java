package cn.porkchop.bos.utils;

import cn.porkchop.bos.domain.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class BOSUtils {
    /**
     * 获得session中的user
     * @return 如果未登陆,返回null
     * @date 2018/2/19 13:55
     * @author porkchop
     */
    public static User getLoginUser() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        return (User) session.getAttribute("user");
    }
}
