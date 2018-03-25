package cn.porkchop.bos.service;

import cn.porkchop.bos.domain.EasyUIDataGridResult;
import cn.porkchop.bos.domain.Function;

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
     * @date 2018/3/25 14:01
     * @author porkchop
     * @param page
     * @param rows
     */
    EasyUIDataGridResult<Function> findAllByPagination(int page, int rows);
}
