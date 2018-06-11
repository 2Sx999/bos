package cn.porkchop.bos.action;

import cn.porkchop.bos.domain.EasyUIDataGridResult;
import cn.porkchop.bos.domain.Role;
import cn.porkchop.bos.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
    @Autowired
    private RoleService roleService;
    private String functionIds;

    /**
     * 添加
     *
     * @date 2018/3/28 21:37
     * @author porkchop
     */
    public String add() {
        roleService.add(getModel(), functionIds);
        return "list";
    }

    /**
     * 分页查询所有
     *
     * @date 2018/3/28 22:29
     * @author porkchop
     */
    public String findAllByPagination() throws IOException {
        EasyUIDataGridResult<Role> result = roleService.findAllByPagination(rows, page);
        String json = toJson(result, new String[]{"functions", "users"});
        sendJson(json);
        return NONE;
    }

    /**
     * 查询所有
     *
     * @date 2018/3/29 10:41
     * @author porkchop
     */
    public String findAll() throws IOException {
        List<Role> list = roleService.findAll();
        sendJson(toJson(list, new String[]{"functions", "users"}));
        return NONE;
    }

    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }
}
