package cn.porkchop.bos.service;

import cn.porkchop.bos.domain.EasyUIDataGridResult;
import cn.porkchop.bos.domain.Function;
import cn.porkchop.bos.domain.User;

import java.util.List;

public interface FunctionService {
    /**
     * 查询所有的功能
     *
     * @date 2018/3/25 12:55
     * @author porkchop
     */
    List<Function> findAll();

    /**
     * 添加
     *
     * @param model
     * @date 2018/3/25 13:19
     * @author porkchop
     */
    void add(Function model);

    /**
     * 分页查询所有
     *
     * @param page
     * @param rows
     * @date 2018/3/25 14:01
     * @author porkchop
     */
    EasyUIDataGridResult<Function> findAllByPagination(int page, int rows);

    /**
     * 根据用户id查权限
     *
     * @param id
     * @date 2018/3/29 15:13
     * @author porkchop
     */
    List<Function> findByUserId(String id);

    /**
     * 根据当前用户的权限查询菜单
     *
     * @date 2018/3/29 16:23
     * @author porkchop
     * @param loginUser
     */
    List<Function> findMenu(User loginUser);
}
