package cn.porkchop.bos.service.impl;

import cn.porkchop.bos.dao.SubareaDao;
import cn.porkchop.bos.domain.Subarea;
import cn.porkchop.bos.service.SubareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubareaServiceImpl implements SubareaService {
    @Autowired
    private SubareaDao subareaDao;
    @Override
    public void add(Subarea model) {
        subareaDao.save(model);
    }
}
