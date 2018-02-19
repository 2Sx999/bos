package cn.porkchop.bos.interceptor;

import cn.porkchop.bos.domain.User;
import cn.porkchop.bos.utils.BOSUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 登陆拦截器,未登录则跳转到登陆页面
 * @date 2018/2/19 14:00
 * @author porkchop
 */
public class LoginInterceptor extends MethodFilterInterceptor {
    private static final String LOGIN = "login";

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        User user = BOSUtils.getLoginUser();
        return user == null ? LOGIN : invocation.invoke();
    }
}
