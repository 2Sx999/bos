package cn.porkchop.bos.action;

import cn.porkchop.bos.domain.Decidedzone;
import cn.porkchop.bos.domain.EasyUIDataGridResult;
import cn.porkchop.bos.service.DecidedZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@Scope("prototype")
public class DecidedZoneAction extends BaseAction<Decidedzone> {
    @Autowired
    private DecidedZoneService decidedZoneService;
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
}
