package cn.porkchop.bos.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    private T model;

    public BaseAction() {
        ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] actualTypeArguments = superclass.getActualTypeArguments();
        Class<T> entityClass = (Class<T>) actualTypeArguments[0];
        try {
            model = entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected int page;
    protected int rows;

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * 对象转换为json
     *
     * @date 2018/3/15 21:30
     * @author porkchop
     */
    public String toJson(Object object, String[] excludes) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes);
        return JSONObject.fromObject(object, jsonConfig).toString();
    }

    /**
     * list转换为json
     *
     * @date 2018/3/15 21:30
     * @author porkchop
     */
    public String toJson(List<?> list, String[] excludes) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes);
        return JSONArray.fromObject(list, jsonConfig).toString();
    }

    public void sendJson(String json) throws IOException {
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().write(json);
    }

    @Override
    public T getModel() {
        return model;
    }
}
