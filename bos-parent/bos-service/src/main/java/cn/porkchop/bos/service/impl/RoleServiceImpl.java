package cn.porkchop.bos.service.impl;

import cn.porkchop.bos.dao.RoleDao;
import cn.porkchop.bos.domain.EasyUIDataGridResult;
import cn.porkchop.bos.domain.Function;
import cn.porkchop.bos.domain.Role;
import cn.porkchop.bos.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public void add(Role model, String functionIds) {
        if (StringUtils.isNotBlank(functionIds)) {
            for (String id : functionIds.split(",")) {
                Function function = new Function();
                function.setId(id);
                model.getFunctions().add(function);
            }
        }
        roleDao.save(model);
    }

    @Override
    public EasyUIDataGridResult<Role> findAllByPagination(int rows, int page) {
        DetachedCriteria countCriteria = DetachedCriteria.forClass(Role.class);
        countCriteria.setProjection(Projections.rowCount());
        //获得总记录数
        Long count = (Long) roleDao.findByCriteria(countCriteria).get(0);
        DetachedCriteria listCriteria = DetachedCriteria.forClass(Role.class);
        //获得当前页的数据
        List<Role> list = (List<Role>) roleDao.findByCriteria(listCriteria, rows * (page - 1), rows);
        return new EasyUIDataGridResult<>(count, list);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
