package cn.porkchop.bos.action;

import cn.porkchop.bos.domain.EasyUIDataGridResult;
import cn.porkchop.bos.domain.Staff;
import cn.porkchop.bos.service.StaffService;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

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
        if ("1".equals(getModel().getHaspda())) {
            getModel().setHaspda("1");
        } else {
            getModel().setHaspda("0");
        }

        staffService.add(getModel());
        return "list";
    }

    private int page;
    private int rows;

    /**
     * 分页查询所有员工
     *
     * @date 2018/2/21 11:13
     * @author porkchop
     */
    public String findAllByPagination() throws IOException {
        EasyUIDataGridResult<Staff> result = staffService.findAllByPagination(page, rows);
        String json = JSONObject.fromObject(result).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().write(json);
        return NONE;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    private String ids;

    /**
     * 批量逻辑删除员工
     *
     * @date 2018/2/21 15:54
     * @author porkchop
     */
    public String batchDelete() {
        staffService.batchDelete(ids);
        return "list";
    }

    /**
     * 编辑员工
     *
     * @date 2018/2/21 19:35
     * @author porkchop
     */
    public String edit() {
        staffService.edit(getModel());
        return "list";
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
