package cn.com.wh.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * 读取配置文件的工具
 * @author 李立泼
 *
 */
public class ReadProperties {
	/**
     * 读取properties配置文件中的value值
     * @param propertiesFile  properties配置文件的路径
     * @param key  properties配置文件中的key
     * @return  properties配置文件中的value
     */
    public static String readPropertiesValue(String propertiesFile,String key){
    	String result = "";
    	try {
    		Properties properties = new Properties();
    		String path = Thread.currentThread().getContextClassLoader().getResource(propertiesFile).getPath();  
    		path=URLDecoder.decode(path, "utf-8");
    		InputStream is = new FileInputStream(path);  
    		properties.load(is);
    		result = (String) properties.get(key);
    		is.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return result;
    }
    
//	public static void main(String[] args) {
//		String defaultHoliday = readPropertiesValue("holiday.properties", "defaultHoliday");
//		String[] holidays = defaultHoliday.split(",");
//		for(String h:holidays){
//			System.out.println(readPropertiesValue("holiday.properties", h));
//		}
//	}
}
