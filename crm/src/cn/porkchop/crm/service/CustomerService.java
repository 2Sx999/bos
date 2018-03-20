package cn.porkchop.crm.service;


import cn.porkchop.crm.pojo.Customer;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface CustomerService {
    /**
     * 查询所有客户
     *
     * @date 2018/3/20 13:28
     * @author porkchop
     */
    List<Customer> findAll();

    /**
     * 查询未关联的客户
     *
     * @date 2018/3/20 13:39
     * @author porkchop
     */
    List<Customer> findUnassociated();


    /**
     * 根据定区查询客户
     *
     * @date 2018/3/20 13:39
     * @author porkchop
     */
    List<Customer> findByDecidedZoneId(String decidedZoneId);

    /**
     * 客户关联定区的id
     *
     * @date 2018/3/20 16:59
     * @author porkchop
     */
    void associateCustomerToDecidedZone(String decidedZoneId, Integer[] customerIds);
}
