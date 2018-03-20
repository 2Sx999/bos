package cn.porkchop.bos.action;

import cn.porkchop.bos.domain.EasyUIDataGridResult;
import cn.porkchop.bos.domain.Subarea;
import cn.porkchop.bos.service.SubareaService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
    @Autowired
    private SubareaService subareaService;

    /**
     * 添加
     *
     * @date 2018/2/25 13:43
     * @author porkchop
     */
    public String add() {
        subareaService.add(getModel());
        return "list";
    }

    /**
     * 给datagird传分区信息,并且包含区域信息
     *
     * @date 2018/3/14 14:18
     * @author porkchop
     */
    public String findByPaginationWithCondition() throws IOException {
        EasyUIDataGridResult<Subarea> result = subareaService.findByPaginationWithCondition(getModel(), page, rows);
        String json = toJson(result, new String[]{"subareas", "decidedzone"});
        sendJson(json);
        return NONE;
    }

    /**
     * 导出为xls
     *
     * @date 2018/3/15 17:50
     * @author porkchop
     */
    public String exportToXls() throws IOException {
        List<Subarea> list = subareaService.findAll();
        //生成xls文件
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("分区数据");
        HSSFRow firstRow = sheet.createRow(0);
        firstRow.createCell(0).setCellValue("分区编号");
        firstRow.createCell(1).setCellValue("开始编号");
        firstRow.createCell(2).setCellValue("结束编号");
        firstRow.createCell(3).setCellValue("位置信息");
        firstRow.createCell(4).setCellValue("省市区");
        for (Subarea subarea : list) {
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            dataRow.createCell(0).setCellValue(subarea.getId());
            dataRow.createCell(1).setCellValue(subarea.getStartnum());
            dataRow.createCell(2).setCellValue(subarea.getEndnum());
            dataRow.createCell(3).setCellValue(subarea.getPosition());
            dataRow.createCell(4).setCellValue(subarea.getRegion().getProvince() + " " +
                    subarea.getRegion().getCity() + " " +
                    subarea.getRegion().getDistrict());
        }
        String filename = "分区数据.xls";
        String contentType = ServletActionContext.getServletContext().getMimeType(filename);
        //设置相应头等信息
        ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
        ServletActionContext.getResponse().setContentType(contentType);
        filename = URLEncoder.encode(filename, "utf-8");
        ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename=\"" + filename + "\"");
        hssfWorkbook.write(outputStream);
        return NONE;
    }

    /**
     * 查询所有未与定区关联的分区
     *
     * @date 2018/3/15 22:40
     * @author porkchop
     */
    public String findUnconnecteToDecidedZone() throws IOException {
        List<Subarea> list = subareaService.findUnconnecteToDecidedZone();
        String json = toJson(list, new String[]{"decidedzone", "region"});
        sendJson(json);
        return NONE;
    }

    private String decidedZoneId;

    /**
     * 根据定区查找关联的分区
     *
     * @date 2018/3/20 19:21
     * @author porkchop
     */
    public String findByDecidedZoneId() throws IOException {
        List<Subarea> list = subareaService.findByDecidedZoneId(decidedZoneId);
        String json = toJson(list,new String[]{"decidedzone","subareas"});
        sendJson(json);
        return NONE;
    }

    public void setDecidedZoneId(String decidedZoneId) {
        this.decidedZoneId = decidedZoneId;
    }
}
