package cn.porkchop.bos.action;

import cn.porkchop.bos.domain.Workordermanage;
import cn.porkchop.bos.service.WorkOrderManageService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class WorkOrderManageAction extends BaseAction<Workordermanage> {
    @Autowired
    private WorkOrderManageService workOrderManageService;

    /**
     * 添加工作单
     *
     * @date 2018/3/24 14:20
     * @author porkchop
     */
    public String add() throws IOException {
        try {
            workOrderManageService.save(getModel());
            ServletActionContext.getResponse().getWriter().write("success");
        } catch (Exception e) {
            ServletActionContext.getResponse().getWriter().write("false");
        }

        return NONE;
    }


}
