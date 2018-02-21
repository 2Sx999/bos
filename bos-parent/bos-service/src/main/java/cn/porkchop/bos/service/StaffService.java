package cn.porkchop.bos.service;

import cn.porkchop.bos.domain.EasyUIDataGridResult;
import cn.porkchop.bos.domain.Staff;

public interface StaffService {
    /**
     * 添加员工
     *
     * @date 2018/2/19 20:45
     * @author porkchop
     */
    void add(Staff model);

    /**
     * 分页查询所有员工
     *
     * @date 2018/2/21 11:28
     * @author porkchop
     */
    EasyUIDataGridResult<Staff> findAllByPagination(int page, int rows);

    /**
     * 批量逻辑删除
     *
     * @date 2018/2/21 15:57
     * @author porkchop
     */
    void batchDelete(String ids);

    /**
     * 编辑员工
     *
     * @date 2018/2/21 19:36
     * @author porkchop
     */
    void edit(Staff model);
}
