package cn.porkchop.json;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToJsonValueProcessor implements JsonValueProcessor {

    public static final String Default_DATE_PATTERN = "yyyy-MM-dd";
    private DateFormat dateFormat;

    public DateToJsonValueProcessor(String datePattern) {
        try {
            dateFormat = new SimpleDateFormat(datePattern);
        } catch (Exception e) {
            dateFormat = new SimpleDateFormat(Default_DATE_PATTERN);
        }
    }

    @Override
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    @Override
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    private Object process(Object value) {
        return dateFormat.format((Date) value);

    }


}
