package cn.porkchop.bos.service.impl;

import cn.porkchop.bos.dao.DecidedZoneDao;
import cn.porkchop.bos.dao.SubareaDao;
import cn.porkchop.bos.domain.Decidedzone;
import cn.porkchop.bos.domain.EasyUIDataGridResult;
import cn.porkchop.bos.domain.Staff;
import cn.porkchop.bos.domain.Subarea;
import cn.porkchop.bos.service.DecidedZoneService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DecidedZoneServiceImpl implements DecidedZoneService {
    @Autowired
    private DecidedZoneDao decidedZoneDao;
    @Autowired
    private SubareaDao subareaDao;

    @Override
    public void add(Decidedzone model, String[] subareaId) {
        for (String id : subareaId) {
            Subarea subarea = subareaDao.findById(id);
            subarea.setDecidedzone(model);

        }
        decidedZoneDao.save(model);
    }

    @Override
    public EasyUIDataGridResult<Decidedzone> findAllByPagination(int rows, int page) {
        DetachedCriteria countCriteria = DetachedCriteria.forClass(Decidedzone.class);
        countCriteria.setProjection(Projections.rowCount());
        //获得总记录数
        Long count = (Long) decidedZoneDao.findByCriteria(countCriteria).get(0);
        DetachedCriteria listCriteria = DetachedCriteria.forClass(Decidedzone.class);
        //获得当前页的数据
        List<Decidedzone> list = (List<Decidedzone>) decidedZoneDao.findByCriteria(listCriteria, rows * (page - 1), rows);
        return new EasyUIDataGridResult<>(count, list);
    }
}
