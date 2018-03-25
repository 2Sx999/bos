package cn.porkchop.bos.service.impl;

import cn.porkchop.bos.dao.FunctionDao;
import cn.porkchop.bos.domain.EasyUIDataGridResult;
import cn.porkchop.bos.domain.Function;
import cn.porkchop.bos.domain.Staff;
import cn.porkchop.bos.service.FunctionService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FunctionServiceImpl implements FunctionService {
    @Autowired
    private FunctionDao functionDao;

    @Override
    public List<Function> findAll() {
        return functionDao.findAll();
    }

    @Override
    public void add(Function model) {
        Function parentFunction = model.getParentFunction();
        if (parentFunction != null && "".equals(parentFunction.getId())) {
            model.setParentFunction(null);
        }
        functionDao.save(model);
    }

    @Override
    public EasyUIDataGridResult<Function> findAllByPagination(int page, int rows) {
        DetachedCriteria countCriteria = DetachedCriteria.forClass(Function.class);
        countCriteria.setProjection(Projections.rowCount());
        //获得总记录数
        Long count = (Long) functionDao.findByCriteria(countCriteria).get(0);
        DetachedCriteria listCriteria = DetachedCriteria.forClass(Function.class);
        //获得当前页的数据
        List<Function> list = (List<Function>) functionDao.findByCriteria(listCriteria, rows * (page - 1), rows);
        return new EasyUIDataGridResult<>(count, list);
    }
}
