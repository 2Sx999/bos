package cn.porkchop.bos.service;

import cn.porkchop.bos.domain.User;

public interface UserService {
    /**
     * 登陆
     *
     * @date 2018/2/18 13:04
     * @author porkchop
     */
    User login(User user);

    /**
     * 修改密码
     *
     * @date 2018/2/19 14:53
     * @author porkchop
     */
    void editPassword(User user);

    /**
     * 根据用户名查询用户
     *
     * @date 2018/3/21 17:11
     * @author porkchop
     */
    User findByUsername(String username);
}
