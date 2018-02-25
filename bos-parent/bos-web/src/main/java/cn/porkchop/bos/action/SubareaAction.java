package cn.porkchop.bos.action;

import cn.porkchop.bos.domain.Subarea;
import cn.porkchop.bos.service.SubareaService;
import org.springframework.beans.factory.annotation.Autowired;


public class SubareaAction extends BaseAction<Subarea>{
    @Autowired
    private SubareaService subareaService;
    /**
     * 添加
     * @date 2018/2/25 13:43
     * @author porkchop
     */
    public String add(){
        subareaService.add(getModel());
        return "list";
    }
}
