package cn.porkchop.bos.action;

import cn.porkchop.bos.domain.EasyUIDataGridResult;
import cn.porkchop.bos.domain.Function;
import cn.porkchop.bos.service.FunctionService;
import cn.porkchop.bos.utils.BOSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {
    @Autowired
    private FunctionService functionService;

    /**
     * 查询所有的功能
     *
     * @date 2018/3/25 12:55
     * @author porkchop
     */
    public String findAll() throws IOException {
        List<Function> list = functionService.findAll();
        String json = toJson(list, new String[]{"parentFunction", "children", "roles"});
        sendJson(json);
        return NONE;
    }

    /**
     * 添加
     *
     * @date 2018/3/25 13:19
     * @author porkchop
     */
    public String add() {
        functionService.add(getModel());
        return "list";
    }

    private String fucntionPage;

    /**
     * 分页查询所有
     *
     * @date 2018/3/25 14:02
     * @author porkchop
     */
    public String findAllByPagination() throws IOException {
        EasyUIDataGridResult<Function> result = functionService.findAllByPagination(Integer.parseInt(getModel().getPage()), rows);
        String json = toJson(result, new String[]{"parentFunction", "roles", "children"});
        sendJson(json);
        return NONE;
    }

    /**
     * 根据当前用户的权限查询菜单
     *
     * @date 2018/3/29 15:59
     * @author porkchop
     */
    public String findMenu() throws IOException {
        List<Function> list = functionService.findMenu(BOSUtils.getLoginUser());
        sendJson(toJson(list,new String[]{"parentFunction","roles","children"}));
        return NONE;
    }

    public void setFucntionPage(String fucntionPage) {
        this.fucntionPage = fucntionPage;
    }
}
