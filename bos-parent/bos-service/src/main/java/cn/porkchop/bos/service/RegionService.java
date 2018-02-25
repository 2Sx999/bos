package cn.porkchop.bos.service;

import cn.porkchop.bos.domain.EasyUIDataGridResult;
import cn.porkchop.bos.domain.Region;

import java.util.List;
import java.util.Map;

public interface RegionService {

    /**
     * 批量保存地区数据
     *
     * @date 2018/2/24 14:49
     * @author porkchop
     */
    void batchSave(List<Region> regionList);

    /**
     * 分页查询所有数据
     *
     * @date 2018/2/24 16:13
     * @author porkchop
     */
    EasyUIDataGridResult findAllByPagination(int page,int rows);



    /**
     * 根据单个条件查询每个字段
     * @date 2018/2/24 22:32
     * @author porkchop
     */
    List<Map<String,String>> findSelective(String q);
}
