package cn.porkchop.bos.service.impl;

import cn.porkchop.bos.dao.StaffDao;
import cn.porkchop.bos.domain.EasyUIDataGridResult;
import cn.porkchop.bos.domain.Staff;
import cn.porkchop.bos.service.StaffService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;

    @Override
    public void add(Staff model) {
        model.setDeltag("0");
        staffDao.save(model);
    }

    @Override
    public EasyUIDataGridResult<Staff> findAllByPagination(int page, int rows) {
        DetachedCriteria countCriteria = DetachedCriteria.forClass(Staff.class);
        countCriteria.setProjection(Projections.rowCount());
        //获得总记录数
        Long count = (Long) staffDao.findByCriteria(countCriteria).get(0);
        DetachedCriteria listCriteria = DetachedCriteria.forClass(Staff.class);
        //获得当前页的数据
        List<Staff> list = (List<Staff>) staffDao.findByCriteria(listCriteria, rows * (page - 1), rows);
        return new EasyUIDataGridResult<>(count, list);
    }

    @Override
    public void batchDelete(String ids) {
        if (StringUtils.isNotBlank(ids)) {
            for (String id : ids.split(",")) {
                staffDao.executeUpdate("staff.delete", id);
            }
        }
    }

    @Override
    public void edit(Staff model) {
        //为了保证关联关系正确,应该修改数据库里读出来的对象
        Staff staff = staffDao.findById(model.getId());
        staff.setHaspda(model.getHaspda());
        staff.setDecidedzones(model.getDecidedzones());
        staff.setDeltag(model.getDeltag());
        staff.setName(model.getName());
        staff.setStandard(model.getStandard());
        staff.setStation(model.getStation());
        staff.setTelephone(model.getTelephone());
    }

    @Override
    public List<Staff> findAllUndeletedStaff() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Staff.class);
        //0表示未删除
        criteria.add(Restrictions.eq("deltag", "0"));
        List<Staff> list = (List<Staff>) staffDao.findByCriteria(criteria);
        return list;
    }
}
