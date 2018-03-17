package cn.porkchop.bos.service.impl;

import cn.porkchop.bos.dao.SubareaDao;
import cn.porkchop.bos.domain.EasyUIDataGridResult;
import cn.porkchop.bos.domain.Region;
import cn.porkchop.bos.domain.Subarea;
import cn.porkchop.bos.service.SubareaService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubareaServiceImpl implements SubareaService {
    @Autowired
    private SubareaDao subareaDao;

    @Override
    public void add(Subarea model) {
        subareaDao.save(model);
    }

    @Override
    public EasyUIDataGridResult<Subarea> findByPaginationWithCondition(Subarea subarea, int page, int rows) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Subarea.class);
        //添加过滤信息
        String addresskey = subarea.getAddresskey();
        if (StringUtils.isNotBlank(addresskey)) {
            criteria.add(Restrictions.like("addresskey", "%" + addresskey + "%"));
        }
        Region region = subarea.getRegion();
        if (region != null) {
            String province = region.getProvince();
            String district = region.getDistrict();
            String city = region.getCity();
            criteria.createAlias("region", "r");
            if (StringUtils.isNotBlank(province)) {
                criteria.add(Restrictions.like("r.province", "%" + province + "%"));
            }
            if (StringUtils.isNotBlank(district)) {
                criteria.add(Restrictions.like("r.district", "%" + district + "%"));
            }
            if (StringUtils.isNotBlank(city)) {
                criteria.add(Restrictions.like("r.city", "%" + city + "%"));
            }
        }
        //获取总条数
        criteria.setProjection(Projections.rowCount());
        Long total = (Long) subareaDao.findByCriteria(criteria).get(0);
        //获取所有数据
        criteria.setProjection(null);
        List<Subarea> list = (List<Subarea>) subareaDao.findByCriteria(criteria, (page - 1) * rows, rows);
        return new EasyUIDataGridResult<>(total, list);
    }

    @Override
    public List<Subarea> findAll() {
        return subareaDao.findAll();
    }

    @Override
    public List<Subarea> findUnconnecteToDecidedZone() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Subarea.class);
        criteria.add(Restrictions.isNull("decidedzone"));
        return (List<Subarea>) subareaDao.findByCriteria(criteria);
    }
}
