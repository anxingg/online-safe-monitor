package cn.com.wh.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName:     DateUtil.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         wangxj
 * @version        V1.0  
 * @Date           2015-8-23 下午05:17:35 
 */
public class DateUtil {
	private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	private static final String DAY_DEFAULT_FORMAT = "yyyy-MM-dd";
	private static final String BOTH_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static Date str2Date(String str, String format){
	  if (null != str && !"".equals(str)) {
	    SimpleDateFormat sdf = new SimpleDateFormat(format);
	    Date date = null;
	    try {
	     date = sdf.parse(str);
	     return date;
	   } catch (ParseException e) {
	     e.printStackTrace();
	  }
	}
	return null;
	}

	public static String date2Str(Date date, String format) {
	  if (null != date && !"".equals(date)) {
	    SimpleDateFormat sdf = new SimpleDateFormat(format);
	    return sdf.format(date);
	  }
	  return null;
	}
	
	public static String date2Str(Date date) {
		  if (null != date && !"".equals(date)) {
		    SimpleDateFormat sdf = new SimpleDateFormat(DAY_DEFAULT_FORMAT);
		    return sdf.format(date);
		  }
		  return null;
		}
	
	public static String timestamp2Str(Timestamp time) {
	  if(null != time && !"".equals(time)){
	    Date date = new Date(time.getTime());
	    return date2Str(date, BOTH_DEFAULT_FORMAT);
	  }
	  return null;
	}
	
	public static String timestamp2yyyymmddStr(Timestamp time) {
		  if(null != time && !"".equals(time)){
		    Date date = new Date(time.getTime());
		    return date2Str(date, DAY_DEFAULT_FORMAT);
		  }
		  return null;
		}

	public static Timestamp str2Timestamp(String str) {
	  if(null==str||"".equals(str)){
		 return new Timestamp(System.currentTimeMillis());
	  }
	  if(null != str && !"".equals(str)){
	    Date date = str2Date(str, DEFAULT_FORMAT);
	    return new Timestamp(date.getTime());
	  }
	  return null;
	}
	
	public static Date str2Dateofday(String str) throws ParseException{
		 if (null != str && !"".equals(str)) {
			    SimpleDateFormat sdf = new SimpleDateFormat(DAY_DEFAULT_FORMAT);
			    Date date = null;
			    try {
			     date = sdf.parse(str);
			     return date;
			   } catch (ParseException e) {
			     e.printStackTrace();
			  }
			}
			return null;
	}
	public static Date str2DateofBoth(String str) throws ParseException{
		 if (null != str && !"".equals(str)) {
			    SimpleDateFormat sdf = new SimpleDateFormat(BOTH_DEFAULT_FORMAT);
			    Date date = null;
			    try {
			     date = sdf.parse(str);
			     return date;
			   } catch (ParseException e) {
			     e.printStackTrace();
			  }
			}
			return null;
	}
	
	
	
	public static void main(String[] args) throws ParseException{
		String str = "2015-08-24";
		//System.out.println(DateUtil.str2Timestamp(str));
		
		SimpleDateFormat sdf = new SimpleDateFormat(DAY_DEFAULT_FORMAT);
		System.out.println(sdf.parse(str));
		
		 
	}
	
	
}
