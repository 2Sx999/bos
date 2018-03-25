package cn.porkchop.bos.service.impl;

import cn.porkchop.bos.dao.WorkOrderManageDao;
import cn.porkchop.bos.domain.Workordermanage;
import cn.porkchop.bos.service.WorkOrderManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WorkOrderManageServiceImpl implements WorkOrderManageService {
    @Autowired
    private WorkOrderManageDao workOrderManageDao;

    @Override
    public void save(Workordermanage model) {
        workOrderManageDao.save(model);
    }
}
