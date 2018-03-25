package cn.porkchop.bos.service;

import cn.porkchop.bos.dao.UserDao;
import cn.porkchop.bos.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class BOSRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 授权
     *
     * @date 2018/3/24 15:13
     * @author porkchop
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //授权
        info.addStringPermission("staff-list");
        //后期修改,从数据库中读权限
        User user1 = (User) SecurityUtils.getSubject().getPrincipal();
        User user2 = (User) principalCollection.getPrimaryPrincipal();
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
