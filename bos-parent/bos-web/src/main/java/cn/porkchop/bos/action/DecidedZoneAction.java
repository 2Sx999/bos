package cn.porkchop.bos.action;

import cn.porkchop.bos.domain.Decidedzone;
import cn.porkchop.bos.domain.EasyUIDataGridResult;
import cn.porkchop.bos.service.DecidedZoneService;
import cn.porkchop.crm.Customer;
import cn.porkchop.crm.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
public class DecidedZoneAction extends BaseAction<Decidedzone> {
    @Autowired
    private DecidedZoneService decidedZoneService;
    @Autowired
    private CustomerService customerService;
    private String[] subareaId;

    public void setSubareaId(String[] subareaId) {
        this.subareaId = subareaId;
    }

    /**
     * 添加定区
     *
     * @date 2018/3/16 12:00
     * @author porkchop
     */
    public String add() {
        decidedZoneService.add(getModel(), subareaId);
        return "list";
    }

    /**
     * 分页查询定区
     *
     * @date 2018/3/16 13:07
     * @author porkchop
     */
    public String findAllByPagination() throws IOException {
        EasyUIDataGridResult<Decidedzone> result = decidedZoneService.findAllByPagination(rows, page);
        String json = toJson(result, new String[]{"subareas", "decidedzones"});
        sendJson(json);
        return NONE;
    }

    /**
     * 查询未关联的客户
     *
     * @date 2018/3/20 15:13
     * @author porkchop
     */
    public String findUnassociatedCustomer() throws IOException {
        List<Customer> list = customerService.findUnassociated();
        String json = toJson(list, null);
        sendJson(json);
        return NONE;
    }

    /**
     * 查询与指定定区关联的客户
     *
     * @date 2018/3/20 15:13
     * @author porkchop
     */
    public String findCustomerByDecidedZoneId() throws IOException {
        List<Customer> list = customerService.findByDecidedZoneId(getModel().getId());
        String json = toJson(list==null?new ArrayList<Customer>():list, null);
        sendJson(json);
        return NONE;
    }

    private List<Integer> customerIds;

    /**
     * 重新关联客户与定区
     *
     * @date 2018/3/20 17:16
     * @author porkchop
     */
    public String reassociateCustomerToDecidedZone() {
        customerService.associateCustomerToDecidedZone(getModel().getId(), customerIds);
        return "list";
    }

    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }
}
