package cn.porkchop.bos.service;

import cn.porkchop.bos.domain.EasyUIDataGridResult;
import cn.porkchop.bos.domain.Subarea;

import java.util.List;

public interface SubareaService {
    /**
     * 添加
     *
     * @date 2018/2/25 13:43
     * @author porkchop
     */
    void add(Subarea model);

    /**
     * 给datagird传分区信息,并且包含区域信息
     *
     * @date 2018/3/14 14:18
     * @author porkchop
     */
    EasyUIDataGridResult<Subarea> findByPaginationWithCondition(Subarea subarea, int page, int rows);

    /**
     * 查询所有
     *
     * @date 2018/3/15 17:52
     * @author porkchop
     */
    List<Subarea> findAll();

    /**
     * 查询所有未与定区关联的分区
     *
     * @date 2018/3/15 22:40
     * @author porkchop
     */
    List<Subarea> findUnconnecteToDecidedZone();

    /**
     * 根据定区查找关联的分区
     *
     * @date 2018/3/20 19:21
     * @author porkchop
     */
    List<Subarea> findByDecidedZoneId(String decidedZoneId);


}
