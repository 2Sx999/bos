package cn.porkchop.bos.service.realm;

import cn.porkchop.bos.dao.FunctionDao;
import cn.porkchop.bos.dao.UserDao;
import cn.porkchop.bos.domain.Function;
import cn.porkchop.bos.domain.User;
import cn.porkchop.bos.service.FunctionService;
import cn.porkchop.bos.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BOSRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private FunctionService functionService;

    /**
     * 授权
     *
     * @date 2018/3/24 15:13
     * @author porkchop
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前登录用户对象
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        // User user = (User) principalCollection.getPrimaryPrincipal();
        //从市数据库中查询权限
        List<Function> list = null;
        if (user.getUsername().equals("root")) {
            list = functionService.findAll();
        } else {
            list = functionService.findByUserId(user.getId());
        }
        for (Function function : list) {
            info.addStringPermission(function.getCode());
        }
        return info;
    }

    /**
     * 认证
     *
     * @date 2018/3/21 17:10
     * @author porkchop
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userService.findByUsername(username);
        return user == null ? null : new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
