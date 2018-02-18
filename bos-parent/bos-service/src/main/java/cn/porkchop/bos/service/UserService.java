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
}
