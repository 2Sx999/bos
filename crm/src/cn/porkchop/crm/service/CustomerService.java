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

    /**
     * 根据手机号查询用户
     *
     * @date 2018/3/23 18:32
     * @author porkchop
     */
    Customer findCustomerByTelephone(String telephone);

    /**
     * 根据客户地址查询定区id
     *
     * @date 2018/3/23 18:33
     * @author porkchop
     */
    String findDecidedZoneIdByAddress(String address);
}
