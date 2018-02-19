package cn.porkchop.bos.action;

import cn.porkchop.bos.domain.Staff;
import cn.porkchop.bos.service.StaffService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class StaffAction extends BaseAction<Staff> {
    @Autowired
    private StaffService staffService;

    /**
     * 添加员工
     *
     * @date 2018/2/19 20:44
     * @author porkchop
     */
    public String addStaff() {
        if("1".equals(getModel().getHaspda())){
            getModel().setHaspda("1");
        }else{
            getModel().setHaspda("0");
        }

        staffService.addStaff(getModel());
        return "list";
    }
}
