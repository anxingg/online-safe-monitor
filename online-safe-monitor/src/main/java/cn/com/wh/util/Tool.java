package cn.com.wh.util;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;

import cn.com.qytx.cbb.dict.domain.Dict;
import cn.com.qytx.platform.log.domain.Log;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.util.DataInitUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Tool {
	
	private static Gson gson = new Gson();

	/**
	 * 把 yyyy-MM-dd HH:mm:ss 格式的字符串转成 Timestamp（默认表示时间的字符串表示的时间中东八区区时）
	 * @param timeString 表示时间的字符串
	 * @param timeFormat 时间字符串格式，如：yyyy-MM-dd HH:mm:ss
	 * @return Timestamp 返回字符串表示的东八区区时。当传的表示时间的字符串为空字符串或NULL时，返回NULL。
	 * @throws ParseException 解析字符串时出错
	 */
	public static Timestamp getTimestampByString(String timeString,String timeFormat) throws ParseException{
		if(timeString==null || "".equals(timeString)){
			return null;
		}
		Timestamp ts=null;
		SimpleDateFormat sdf= new SimpleDateFormat(timeFormat);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		java.util.Date date=sdf.parse(timeString);
		ts=new Timestamp(date.getTime());
		return ts;
	}
	
	/**
	 * 设置时间
	 * @param date 表示时间的字符串。当格式中有mis时，对应的部分可以写 1 位数字至 3 位数字
	 * @param timeZone 时区，传null时，默认为东八区
	 * @param timeFormat 表示时间的字符串格式。示例：yyyy-MM-dd HH:mm:ss.mis
	 * @return java.util.Date 返回字符串表示的时间（字符串格式为空时，返回NULL）
	 * @exception 当 data 和 timeFormat 格式不一致时，抛异常。
	 */
	public static Date getDateByString(String date, TimeZone timeZone, String timeFormat){
		if(StringUtils.isBlank(timeFormat)){
			return null;
		}
		
		//各种时间的默认值
		int year = 1, month = 1, day = 1, hour = 0, minute = 0, second = 0, millisecond = 0;
		
		if(StringUtils.isNotBlank(date)){
			if(date.indexOf(".")>-1){
				date = date.substring(0, date.indexOf(".")+1) + StringUtils.leftPad(date.substring(date.indexOf(".")+1), 3, "0");
			}
			if( !date.replaceAll("\\d", "x").equals(timeFormat.replaceAll("[a-zA-Z]", "x")) ){
				throw new RuntimeException("data 和 timeFormat 格式不一致");
			}
			
			//年
			String yearStr="";
			if(timeFormat.indexOf("y")>-1){
				yearStr=date.substring(timeFormat.indexOf("y"), timeFormat.lastIndexOf("y")+1);
			}
			if(StringUtils.isNotBlank(yearStr)){
				year = Integer.parseInt(yearStr);
			}
			
			//月
			String monthStr="";
			if(timeFormat.indexOf("M")>-1){
				monthStr=date.substring(timeFormat.indexOf("M"), timeFormat.lastIndexOf("M")+1);
			}
			if(StringUtils.isNotBlank(monthStr)){
				month = Integer.parseInt(monthStr);
			}
			
			//日
			String dayStr="";
			if(timeFormat.indexOf("d")>-1){
				dayStr=date.substring(timeFormat.indexOf("d"), timeFormat.lastIndexOf("d")+1);
			}
			if(StringUtils.isNotBlank(dayStr)){
				day = Integer.parseInt(dayStr);
			}
			
			//时
			String hourStr="";
			if(timeFormat.indexOf("H")>-1){
				hourStr=date.substring(timeFormat.indexOf("H"), timeFormat.lastIndexOf("H")+1);
			}
			if(StringUtils.isNotBlank(hourStr)){
				hour = Integer.parseInt(hourStr);
			}
			
			//分
			String minuteStr="";
			if(timeFormat.indexOf("m")>-1){
				if(timeFormat.indexOf(".")>-1){
					minuteStr=date.substring(timeFormat.indexOf("m"), timeFormat.lastIndexOf("m", timeFormat.indexOf("."))+1);
				}else{
					minuteStr=date.substring(timeFormat.indexOf("m"), timeFormat.lastIndexOf("m")+1);
				}
			}
			if(StringUtils.isNotBlank(minuteStr)){
				minute = Integer.parseInt(minuteStr);
			}
			
			//秒
			String secondStr="";
			if(timeFormat.indexOf("s")>-1){
				if(timeFormat.indexOf(".")>-1){
					secondStr=date.substring(timeFormat.indexOf("s"), timeFormat.lastIndexOf("s", timeFormat.indexOf("."))+1);
				}else {
					secondStr=date.substring(timeFormat.indexOf("s"), timeFormat.lastIndexOf("s")+1);
				}
			}
			if(StringUtils.isNotBlank(secondStr)){
				second = Integer.parseInt(secondStr);
			}
			
			//毫秒
			String millisecondStr="";
			if(timeFormat.indexOf("mis")>-1){
				millisecondStr=date.substring(timeFormat.indexOf("mis"));
			}
			if(StringUtils.isNotBlank(millisecondStr)){
				millisecond = Integer.parseInt(millisecondStr);
			}
		}
		
		Calendar cal = Calendar.getInstance();
		//设置时区
		if(timeZone == null){
			cal.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		}else {
			cal.setTimeZone(timeZone);
		}
		//设置时间
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, millisecond);
		
		return cal.getTime();
	}
	
	/**
	 * 返回季度
	 * @param cal
	 * @return
	 */
	public static int getQuarter(Calendar cal){
		int month = cal.get(Calendar.MONTH);
		if(month < 3){
			return 1;
		}else if(month < 6){
			return 2;
		}else if(month < 9){
			return 3;
		}else {
			return 4;
		}
	}
	
	/**
	 * @return
	 */
	public static Map<Integer, String> loadaccidentCharacterTypemap(){
		Map<Integer, String> map = new HashMap<Integer, String>();
		Map<String, List<Dict>> dictMap = DataInitUtil.dictMap;
		List<Dict> dictlist = dictMap.get("accidentCharacterType");
		for (Dict d : dictlist) {
			if(d != null && d.getSysTag() == 1){
				map.put(d.getValue(), d.getName());
			}
		}
		return map;
	}
	
	/**
	 * 加载数据字典， 返回Map形式。 Map<value, name>
	 * @return Map<Integer, String>
	 */
	public static Map<Integer, String> loadaccidentCharacterTypemap(String infoType){
		if(StringUtils.isBlank(infoType)){
			return null;
		}
		Map<Integer, String> map = new HashMap<Integer, String>();
		Map<String, List<Dict>> dictMap = DataInitUtil.dictMap;
		List<Dict> dictlist = dictMap.get(infoType);
		for (Dict d : dictlist) {
			if(d != null && d.getSysTag() == 1){
				map.put(d.getValue(), d.getName());
			}
		}
		return map;
	}
	
	/**
	 * 加载数据字典， 返回Map形式。 Map<ID, name>
	 * @param infoType 数据字典类型
	 * @param parentId 父节点ID，
	 * @return Map<Integer, String>
	 */
	public static Map<Integer, String> loadTreeDictMap(String infoType, Integer parentId){
		if(StringUtils.isBlank(infoType)){
			return null;
		}
		Map<Integer, String> map = new HashMap<Integer, String>();
		Map<String, List<Dict>> dictMap = DataInitUtil.dictMap;
		List<Dict> dictlist = dictMap.get(infoType);
		for (Dict d : dictlist) {
			if(d != null && d.getSysTag() == 1){
				if(parentId == null){
					if(d.getGrade() == 1){
						map.put(d.getId(), d.getName());
					}
				}else if (parentId == -1){
					map.put(d.getId(), d.getName());
				}else {
					if(d.getParentId().intValue() == parentId.intValue()){
						map.put(d.getId(), d.getName());
					}
				}
			}
		}
		return map;
	}
	
	/**
	 * 获取公司Map（过滤了已删除的）
	 * @return Map<Integer,String> key: groupId, value: companyName
	 */
	public static Map<Integer,String> loadCompanyMap(){
		Map<Integer, String> result = new HashMap<Integer, String>();
		
		List<WHCompany> companyList = Tool.loadCompanyList();
		Iterator<WHCompany> iterator = companyList.iterator();
		while (iterator.hasNext()) {
			WHCompany company = iterator.next();
			result.put(company.getGroupId(), company.getCompanyName());
		}
		return result;
	}
	
	/**
	 * 获取公司集合（过滤了已删除的）
	 * @return List<WHCompany>
	 */
	public static List<WHCompany> loadCompanyList(){
		List<WHCompany> result = new LinkedList<WHCompany>();
		List<WHCompany> companyList = DataInitUtil.companyList;
		Iterator<WHCompany> iterator = companyList.iterator();
		while (iterator.hasNext()) {
			WHCompany company = iterator.next();
			if(company != null && company.getIsDelete() != null && company.getIsDelete() == 0){
				result.add(company);
			}
		}
		return result;
	}
	
	/**
	 * 生成一个日志对象
	 * @param userInfo 操作人员，用来记录操作人员的公司ID、人员ID、人员姓名
	 * @param ip 操作人员所在的IP地址
	 * @param remark 日志备注。格式如：登录成功；新增人员成功，ID：123
	 * @param logType 取LogType类的中常量。没有对应的则添加
	 * @param obj 操作影响的对象
	 * @param refId 操作影响的对象的ID
	 * @return
	 */
	public static Log generateLog(UserInfo userInfo, String ip, String remark, int logType, Object obj, Integer refId){
        Log log = new Log();
        log.setCompanyId(userInfo.getCompanyId());
        //log.setInsertTime(new Timestamp(new Date().getTime()));
        log.setModuleName("user");
        log.setSysName("wuhai");
        log.setIp(ip/*this.getRequest().getRemoteAddr()*/);
        log.setIsDelete(0);
        log.setLogType(logType);
        log.setRefId(refId);
        if(obj!=null)
        	log.setLogContent(gson.toJson(obj));
        else
        	log.setLogContent("");
//        log.setLogContent(cutJson(gson, gson.toJson(obj), 250, "@@@内容过长，已截取@@@"));
        //System.out.println("gson="+gson.toJson(obj));
        log.setRemark(remark/*"登录成功"*/);
        log.setUserId(userInfo.getUserId());
        log.setUserName(userInfo.getUserName());
        log.setType(0);// 手动添加系统日志
        return log;
	}
	
	/**
	 * 截取过长的json
	 * @param gson Gson对象
	 * @param json 验证的json字符串
	 * @param nodelength 每一个属性的最长限制
	 * @param tips 过长时，添加的提示语
	 * @return String 当每个属性的长度都通过时，返回原json字符串，否则，返回截取后的json字符串。
	 */
	private static String cutJson(Gson gson, String json, int nodelength, String tips){
		if(StringUtils.isBlank(json) || nodelength < 0){
			return json;
		}
		final Type type = new TypeToken<Map<String, String>>() {}.getType();
		final Map<String, String> map = gson.fromJson(json, type);
		//System.out.println(map);
		if(map == null){
			return json;
		}
		Set<Entry<String, String>> entrySet = map.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		while(iterator.hasNext()){
			Entry<String, String> entry = iterator.next();
			//System.out.println(entry.getKey());
			entry.setValue(
			( StringUtils.isNotBlank( entry.getValue() ) && entry.getValue().length() > nodelength )
			? entry.getValue().subSequence(0, nodelength) + StringUtils.defaultString(tips): entry.getValue()
					);
		}
		//System.out.println(map);
		return gson.toJson(map);
	}
	
	/**
	 * 去前后逗号
	 * @param str
	 * @return
	 */
	public static String trimComma(String str){
		return StringUtils.isBlank(str) ? null : (str.startsWith(",") ? (str.endsWith(",")?(str.length() == 1 ? "" :str.substring(1, str.length()-1)):str.substring(1)) : (str.endsWith(",")?str.substring(0, str.length()-1):str) );
	}
}
