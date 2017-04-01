package cn.com.qytx.hotline.systemtime.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;



import org.apache.log4j.Logger;

import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.UserInfo;

import com.google.gson.Gson;

/**
 * 
 * @author lihuawei
 *
 */
public class GetSystemTime extends BaseActionSupport {

	private static final long serialVersionUID = -4192970577134350049L;

	 private final static MonitorLogger logger =new Log4jImpl(GetSystemTime.class);
	//输入
	private String timeFormat;
	
	//输出
	private String currentTimeStr;
	private Long currentTimeMillis;
	private String currentTimeHMS;
	
	
	public String getSystemTime(){
		try{
			UserInfo userInfo = this.getSessionSupport().getCurrentLoginUser();
			if(userInfo!=null){
				if(timeFormat==null || "".equals(timeFormat)){
					timeFormat="yyyy-MM-dd";
				}
				currentTimeStr=getCurrentTimeByLong(System.currentTimeMillis(), timeFormat);
				currentTimeHMS = getCurrentTimeByLong(System.currentTimeMillis(),"HH:mm:ss");
				Map<String, Object> jsonMap = new HashMap<String, Object>();
                jsonMap.put("currentTimeStr", currentTimeStr);
                jsonMap.put("currentTimeMillis", System.currentTimeMillis());
                jsonMap.put("currentTimeHMS", currentTimeHMS);

                Gson json = new Gson();
                String jsons = json.toJson(jsonMap);
                ajax(jsons);
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 根据时间和格式化字符串生成格式代的字符串
	 * @param longTime
	 * @param timeFormat
	 * @return
	 */
	public String getCurrentTimeByLong(long longTime, String timeFormat){
		SimpleDateFormat sdf=new SimpleDateFormat(timeFormat);
		TimeZone tz = TimeZone.getTimeZone("GMT+08:00");//设置默认采用东8区区时。
		sdf.setTimeZone(tz);
		Date date=new Date(longTime);
		return sdf.format(date);
	}

	/* get set */
	public String getTimeFormat() {
		return timeFormat;
	}

	public String getCurrentTimeStr() {
		return currentTimeStr;
	}

	public void setCurrentTimeStr(String currentTimeStr) {
		this.currentTimeStr = currentTimeStr;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	public Long getCurrentTimeMillis() {
		return currentTimeMillis;
	}

	public void setCurrentTimeMillis(Long currentTimeMillis) {
		this.currentTimeMillis = currentTimeMillis;
	}
	public String getCurrentTimeHMS() {
		return currentTimeHMS;
	}

	public void setCurrentTimeHMS(String currentTimeHMS) {
		this.currentTimeHMS = currentTimeHMS;
	}
	
}
