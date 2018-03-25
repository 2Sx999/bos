package cn.porkchop.bos.service.impl;

import cn.porkchop.bos.dao.UserDao;
import cn.porkchop.bos.domain.User;
import cn.porkchop.bos.service.UserService;
import org.hibernate.criterion.DetachedCriteria;
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
        userDao.executeUpdate("user.editPassword",user.getPassword(),user.getId());
    }

    @Override
    public User findByUsername(String username) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("username", username));
        List<User> list = (List<User>) userDao.findByCriteria(criteria);
        return list.isEmpty() ? null : list.get(0);
    }
}
