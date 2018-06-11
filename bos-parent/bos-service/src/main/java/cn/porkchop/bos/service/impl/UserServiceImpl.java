package cn.porkchop.bos.service.impl;

import cn.porkchop.bos.dao.UserDao;
import cn.porkchop.bos.domain.EasyUIDataGridResult;
import cn.porkchop.bos.domain.Role;
import cn.porkchop.bos.domain.User;
import cn.porkchop.bos.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(User user) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("username", user.getUsername()));
        criteria.add(Restrictions.eq("password", DigestUtils.md5DigestAsHex(user.getPassword().getBytes())));
        List<User> list = (List<User>) userDao.findByCriteria(criteria);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void editPassword(User user) {
        userDao.executeUpdate("user.editPassword", user.getPassword(), user.getId());
    }

    @Override
    public User findByUsername(String username) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("username", username));
        List<User> list = (List<User>) userDao.findByCriteria(criteria);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void add(User model, String[] roleIds) {
        model.setPassword(DigestUtils.md5DigestAsHex(model.getPassword().getBytes()));
        for (String id : roleIds) {
            Role role = new Role(id);
            //添加关联
            model.getRoles().add(role);
        }
        userDao.save(model);
    }

    @Override
    public EasyUIDataGridResult<User> findAllByPagination(int rows, int page) {
        DetachedCriteria countCriteria = DetachedCriteria.forClass(User.class);
        countCriteria.setProjection(Projections.rowCount());
        //获得总记录数
        Long count = (Long) userDao.findByCriteria(countCriteria).get(0);
        DetachedCriteria listCriteria = DetachedCriteria.forClass(User.class);
        //获得当前页的数据
        List<User> list = (List<User>) userDao.findByCriteria(listCriteria, rows * (page - 1), rows);
        return new EasyUIDataGridResult<>(count, list);
    }
}
