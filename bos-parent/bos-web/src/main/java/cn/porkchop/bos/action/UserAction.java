package cn.porkchop.bos.action;

import cn.porkchop.bos.domain.EasyUIDataGridResult;
import cn.porkchop.bos.domain.User;
import cn.porkchop.bos.service.UserService;
import cn.porkchop.bos.utils.BOSUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")
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
            //使用shiro框架提供的方式进行认证操作
            //获得当前用户对象,状态为“未认证”
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(getModel().getUsername(), DigestUtils.md5DigestAsHex(getModel().getPassword().getBytes()));
            try {
                subject.login(token);
                User user = (User) subject.getPrincipal();
                ServletActionContext.getRequest().getSession().setAttribute("user", user);
                return HOME;
            } catch (Exception e) {
                //用户名或密码错误
                addActionError("用户名或密码错误");
                return LOGIN;
            }
        } else {
            //非法验证码
            addActionError("验证码错误");
            return LOGIN;
        }
    }
    /*public String login() {
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
    }*/

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
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        return NONE;
    }

    private String[] roleIds;

    /**
     * 添加用户
     *
     * @date 2018/3/28 20:55
     * @author porkchop
     */
    public String add() {
        userService.add(getModel(), roleIds);
        return "list";
    }

    /**
     * 分页查询所有
     *
     * @date 2018/3/29 11:04
     * @author porkchop
     */
    public String findAllByPagination() throws IOException {
        EasyUIDataGridResult<User> result = userService.findAllByPagination(rows, page);
        sendJson(toJson(result, new String[]{"noticebills", "roles"}));
        return NONE;
    }


    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
