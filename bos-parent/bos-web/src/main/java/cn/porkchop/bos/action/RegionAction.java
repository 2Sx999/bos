package cn.porkchop.bos.action;

import cn.porkchop.bos.domain.EasyUIDataGridResult;
import cn.porkchop.bos.domain.Region;
import cn.porkchop.bos.service.RegionService;
import cn.porkchop.bos.utils.PinYin4jUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {
    private File regionFile;
    @Autowired
    private RegionService regionService;

    public void setRegionFile(File regionFile) {
        this.regionFile = regionFile;
    }

    /**
     * 导入excel表格的数据
     *
     * @date 2018/2/24 13:32
     * @author porkchop
     */
    public String importFromExcel() throws IOException {
        List<Region> regionList = new ArrayList<>();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(regionFile));
        HSSFSheet hssfSheet = hssfWorkbook.getSheet("sheet1");
        for (Row row : hssfSheet) {
            if (row.getRowNum() == 0) {
                //跳过第一行
                continue;
            }
            String id = row.getCell(0).getStringCellValue();
            String province = row.getCell(1).getStringCellValue();
            String city = row.getCell(2).getStringCellValue();
            String district = row.getCell(3).getStringCellValue();
            String postcode = row.getCell(4).getStringCellValue();
            //包装一个区域对象
            Region region = new Region(id, province, city, district, postcode, null, null, null);
            //把省市区三个字去除
            province = province.substring(0, province.length() - 1);
            city = city.substring(0, city.length() - 1);
            district = district.substring(0, district.length() - 1);
            //河北石家庄桥西
            String info = province + city + district;
            String[] headByString = PinYin4jUtils.getHeadByString(info);
            //短码   HBSJZQX
            String shortCode = StringUtils.join(headByString);
            //城市编码  shijiazhuang
            String cityCode = PinYin4jUtils.hanziToPinyin(city, "");
            //设置到region对象中
            region.setShortcode(shortCode);
            region.setCitycode(cityCode);
            regionList.add(region);
        }
        regionService.batchSave(regionList);
        return NONE;
    }


    /**
     * 分页查询所有
     *
     * @date 2018/2/24 16:11
     * @author porkchop
     */
    public String findAllByPagination() throws IOException {
        EasyUIDataGridResult result = regionService.findAllByPagination(page, rows);
        String json = toJson(result, new String[]{"subareas"});
        sendJson(json);
        return NONE;
    }

    private String q;

    /**
     * 根据条件显示
     *
     * @date 2018/2/24 22:03
     * @author porkchop
     */
    public String listByCondition() throws IOException {
        List<Map<String, String>> list = regionService.findSelective(q);
        String json = JSONArray.fromObject(list).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().write(json);
        return NONE;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public File getRegionFile() {
        return regionFile;
    }

    public int getPage() {
        return page;
    }

    public int getRows() {
        return rows;
    }

    public String getQ() {
        return q;
    }
}
