package cn.porkchop.bos.dao.impl;

import cn.porkchop.bos.dao.FunctionDao;
import cn.porkchop.bos.domain.Function;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements FunctionDao {
    @Override
    public List<Function> findByUserId(String id) {
        return (List<Function>) getHibernateTemplate().find("select distinct f from Function f left join f.roles r left join r.users u where u.id=?", id);
    }

    @Override
    public List<Function> findMenuByUserId(String id) {
        return (List<Function>) getHibernateTemplate().find("select distinct f from Function f left join f.roles r left join r.users u" +
                " where u.id=? and f.generatemenu='1' ", id);
    }

    @Override
    public List<Function> findAllMenu() {
        return (List<Function>) getHibernateTemplate().find("from Function where generatemenu='1' ");
    }
}
