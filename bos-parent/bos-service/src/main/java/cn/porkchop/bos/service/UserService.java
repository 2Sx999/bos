package cn.porkchop.bos.service;

import cn.porkchop.bos.domain.EasyUIDataGridResult;
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

    /**
     * 添加用户
     *
     * @param model
     * @param roleIds
     * @date 2018/3/28 20:55
     * @author porkchop
     */
    void add(User model, String[] roleIds);

    /**
     * 分页查询所有
     *
     * @param rows
     * @param page
     * @date 2018/3/29 11:16
     * @author porkchop
     */
    EasyUIDataGridResult<User> findAllByPagination(int rows, int page);
}
