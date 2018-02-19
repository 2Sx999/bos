package cn.porkchop.bos.action;

import cn.porkchop.bos.domain.User;
import cn.porkchop.bos.service.UserService;
import cn.porkchop.bos.utils.BOSUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserAction extends BaseAction<User> {

    private static final String HOME = "home";
    private static final String LOGOUT = "logout";
    private String captcha;
    @Autowired
    private UserService userService;

    /**
     * 登陆
     *
     * @date 2018/2/18 12:35
     * @author porkchop
     */
    public String login() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        String captchaInSession = (String) session.getAttribute("captcha");
        if (StringUtils.isNotBlank(captcha) && captcha.equals(captchaInSession)) {
            //验证码正确
            User user = userService.login(getModel());
            if (user == null) {
                //用户名或密码错误
                addActionError("用户名或密码错误");
                return LOGIN;
            } else {
                //登陆成功
                session.setAttribute("user", user);
                return HOME;
            }
        } else {
            //非法验证码
            addActionError("验证码错误");
            return LOGIN;
        }
    }

    /**
     * 登出
     *
     * @date 2018/2/19 14:53
     * @author porkchop
     */
    public String logout() {
        ServletActionContext.getRequest().getSession().invalidate();
        return LOGOUT;
    }

    /**
     * 修改密码
     *
     * @date 2018/2/19 14:53
     * @author porkchop
     */
    public String editPassword() throws IOException {
        User user = BOSUtils.getLoginUser();
        user.setPassword(DigestUtils.md5DigestAsHex(getModel().getPassword().getBytes()));
        try {
            userService.editPassword(user);
            ServletActionContext.getResponse().getWriter().write("true");
        } catch (Exception e) {
            e.printStackTrace();
            ServletActionContext.getResponse().getWriter().write("false");
        }
        return NONE;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
