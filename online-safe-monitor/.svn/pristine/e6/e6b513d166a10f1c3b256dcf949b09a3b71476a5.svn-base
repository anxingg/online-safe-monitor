package cn.com.qytx.oa.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;
/**
 * 
 * 功能: struts date converter
 * 版本: 1.0
 * 开发人员: liang hui bin
 * 创建日期: 2013-7-9
 * 修改日期: 2013-7-9
 * 修改列表:
 */
public class TimestampConverter extends StrutsTypeConverter {

    private final static SimpleDateFormat[] FORM_DATE_FORMATS = {new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), new SimpleDateFormat("yyyy-MM-dd")};  
    @SuppressWarnings("rawtypes")
	@Override  
    public Object convertFromString(Map context, String[] values, Class toClass) {  
        Timestamp date = null;  
        String dateString = null;  
        Date date2 = null;
        if (values != null && values.length > 0) {  
            dateString = values[0];  
            if (dateString != null) {  
            	
            	for (DateFormat dateFormat : FORM_DATE_FORMATS) {
    				try {
    					date2 = dateFormat.parse(dateString);
    					date=new Timestamp(date2.getTime());
                    	if (date != null) {
                    		break;
                    	}
                    } catch (Exception ignore) {
                    }
                }
                // 匹配IE浏览器  
            }  
        }  
        return date;  
    }  
  
    @SuppressWarnings("rawtypes")
	@Override  
    public String convertToString(Map context, Object o) {  
        // 格式化为date格式的字符串  
        Timestamp timestamp=(Timestamp)o;
       Date date = new Date(timestamp.getTime());
        return DateUtils.date2LongStr(date);  
    }  
}
