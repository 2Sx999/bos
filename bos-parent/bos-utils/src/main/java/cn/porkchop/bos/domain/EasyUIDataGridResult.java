package cn.porkchop.bos.domain;

import java.util.List;

/**
 * easyui的datagrid的分页结果,用于转化为json
 *
 * @author porkchop
 * @date 2018/2/21 11:22
 */
public class EasyUIDataGridResult<T> {
    private Long total;
    private List<T> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public EasyUIDataGridResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
