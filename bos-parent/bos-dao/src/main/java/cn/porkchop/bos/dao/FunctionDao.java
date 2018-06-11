package cn.porkchop.bos.dao;

import cn.porkchop.bos.domain.Function;

import java.util.List;

public interface FunctionDao extends BaseDao<Function> {
    /**
     * 根据用户id查询权限
     *
     * @date 2018/3/29 15:14
     * @author porkchop
     */
    List<Function> findByUserId(String id);

    /**
     * 根据当前用户的权限查询菜单
     *
     * @date 2018/3/29 16:23
     * @author porkchop
     * @param id
     */
    List<Function> findMenuByUserId(String id);

    /**
     * 查询所有菜单
     *
     * @date 2018/3/29 16:29
     * @author porkchop
     */
    List<Function> findAllMenu();
}
