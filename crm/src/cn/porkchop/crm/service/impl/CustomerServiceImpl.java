package cn.porkchop.crm.service.impl;


import cn.porkchop.crm.pojo.Customer;
import cn.porkchop.crm.service.CustomerService;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Transactional
public class CustomerServiceImpl extends JdbcDaoSupport implements CustomerService {

    private RowMapper<Customer> rowMapper = new RowMapper<Customer>() {
        @Override
        public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String station = resultSet.getString("station");
            String telephone = resultSet.getString("telephone");
            String address = resultSet.getString("address");
            String decidedzoneId = resultSet.getString("decidedzone_id");
            return new Customer(id, name, station, telephone, address, decidedzoneId);
        }
    };

    @Override
    public List<Customer> findAll() {
        return getJdbcTemplate().query("SELECT * FROM t_customer", rowMapper);
    }

    @Override
    public List<Customer> findUnassociated() {
        return getJdbcTemplate().query("SELECT * FROM t_customer WHERE decidedzone_id IS NULL", rowMapper);
    }


    @Override
    public List<Customer> findByDecidedZoneId(String decidedZoneId) {
        return getJdbcTemplate().query("SELECT * FROM t_customer WHERE decidedzone_id=?", rowMapper, decidedZoneId);
    }

    @Override
    public void associateCustomerToDecidedZone(String decidedZoneId, Integer[] customerIds) {
        getJdbcTemplate().update("UPDATE t_customer SET decidedzone_id=NULL  WHERE decidedzone_id=?", decidedZoneId);
        if (customerIds == null) {
            return;
        }
        for (Integer customerId : customerIds) {
            getJdbcTemplate().update("UPDATE t_customer SET decidedzone_id=? WHERE id=?", decidedZoneId, customerId);
        }
    }

    @Override
    public Customer findCustomerByTelephone(String telephone) {
        List<Customer> query = getJdbcTemplate().query("SELECT * FROM t_customer WHERE telephone=?", rowMapper, telephone);
        return query.isEmpty() ? null : query.get(0);
    }

    @Override
    public String findDecidedZoneIdByAddress(String address) {
        try {
            return getJdbcTemplate().queryForObject("SELECT decidedzone_id FROM t_customer WHERE address=?", String.class, address);
        } catch (DataAccessException e) {
            return null;
        }
    }
}
