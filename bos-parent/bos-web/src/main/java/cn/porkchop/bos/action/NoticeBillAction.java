package cn.porkchop.bos.action;

import cn.porkchop.bos.domain.Noticebill;
import cn.porkchop.bos.service.NoticeBillSerivce;
import cn.porkchop.crm.Customer;
import cn.porkchop.crm.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@Scope("prototype")
public class NoticeBillAction extends BaseAction<Noticebill> {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private NoticeBillSerivce noticeBillSerivce;

    /**
     * 根据手机号查询用户信息
     *
     * @date 2018/3/23 20:07
     * @author porkchop
     */
    public String findCustomerByTelephone() throws IOException {
        Customer customer = customerService.findCustomerByTelephone(getModel().getTelephone());
        String json = toJson(customer, null);
        sendJson(json);
        return NONE;
    }

    /**
     * 添加业务通知单
     *
     * @date 2018/3/23 21:04
     * @author porkchop
     */
    public String add() {
        noticeBillSerivce.add(getModel());
        return "add";
    }
}
