package cn.porkchop.bos.service;

import cn.porkchop.bos.domain.Decidedzone;
import cn.porkchop.bos.domain.EasyUIDataGridResult;

public interface DecidedZoneService {

    /**
     * 添加定区
     *
     * @date 2018/3/16 12:00
     * @author porkchop
     */
    void add(Decidedzone model, String[] subareaId);

    /**
     * 分页查询定区
     *
     * @date 2018/3/16 13:07
     * @author porkchop
     */
    EasyUIDataGridResult<Decidedzone> findAllByPagination(int rows, int page);
}
