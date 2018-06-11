package cn.porkchop.bos.service;

import cn.porkchop.bos.domain.EasyUIDataGridResult;
import cn.porkchop.bos.domain.Role;

import java.util.List;

public interface RoleService {
    /**
     * 添加角色
     *
     * @date 2018/3/28 21:38
     * @author porkchop
     */
    void add(Role model, String functionIds);

    /**
     * 分页查询所有
     *
     * @date 2018/3/28 22:29
     * @author porkchop
     */
    EasyUIDataGridResult<Role> findAllByPagination(int rows, int page);

    /**
     * 查询所有
     *
     * @date 2018/3/29 10:50
     * @author porkchop
     */
    List<Role> findAll();
}
