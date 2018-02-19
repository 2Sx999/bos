package cn.porkchop.bos.service.impl;

import cn.porkchop.bos.dao.StaffDao;
import cn.porkchop.bos.domain.Staff;
import cn.porkchop.bos.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional()
@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;
    @Override
    public void addStaff(Staff model) {
        staffDao.save(model);
    }
}
