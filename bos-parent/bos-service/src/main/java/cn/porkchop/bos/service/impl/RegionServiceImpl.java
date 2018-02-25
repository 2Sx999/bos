package cn.porkchop.bos.service.impl;

import cn.porkchop.bos.dao.RegionDao;
import cn.porkchop.bos.domain.EasyUIDataGridResult;
import cn.porkchop.bos.domain.Region;
import cn.porkchop.bos.service.RegionService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionDao regionDao;

    @Override
    public void batchSave(List<Region> regionList) {
        for (Region region : regionList) {
            regionDao.saveOrUpdate(region);
        }
    }

    @Override
    public EasyUIDataGridResult findAllByPagination(int page, int rows) {
        EasyUIDataGridResult<Region> result = new EasyUIDataGridResult<>();
        //封装总数
        DetachedCriteria countCriteria = DetachedCriteria.forClass(Region.class);
        countCriteria.setProjection(Projections.rowCount());
        Long count = (Long) regionDao.findByCriteria(countCriteria).get(0);
        result.setTotal(count);
        //封装数据
        DetachedCriteria listCriteria = DetachedCriteria.forClass(Region.class);
        List<Region> list = (List<Region>) regionDao.findByCriteria(listCriteria, (page - 1) * rows, rows);
        result.setRows(list);
        return result;
    }


    @Override
    public List<Map<String, String>> findSelective(String q) {
        List<Region> list;
        List<Map<String, String>> resultList = new ArrayList<>();
        if (StringUtils.isNotBlank(q)) {
            list = regionDao.find("from Region r where r.city like ? or r.citycode like ? or r.district like ?  or r.province like ? or r.shortcode like ?",
                    new Object[]{"%" + q + "%", "%" + q + "%", "%" + q + "%", "%" + q + "%", "%" + q + "%"});
        } else {
            list = regionDao.findAll();
        }
        for (Region region : list) {
            HashMap<String, String> map = new HashMap<>();
            map.put("id", region.getId());
            map.put("name", region.getProvince() + " " + region.getCity() + " " + region.getDistrict());
            resultList.add(map);
        }
        return resultList;
    }
}
