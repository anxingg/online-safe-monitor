package cn.com.qytx.hotline.holiday.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.holiday.domain.Holiday;
import cn.com.qytx.hotline.holiday.service.IHoliday;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.UserInfo;


/**
 * 作者：李贺
 * 		节假日action
 * 创建时间：2014年12月7日
 * 修改时间：2014年12月7日
 *
 */
public class HolidayAction extends BaseActionSupport{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5662208205594776190L;
	
	/**
     * log4j日志对象
     */
	 private final static MonitorLogger logger =new Log4jImpl(HolidayAction.class);

	@Autowired
	private transient IHoliday holidayService;
	
	/**节假日向前能显示的月数*/
	private Integer showBeforeMonths;
	/**节假日向后能修改的月数*/
	private Integer alterAfterMonths;
	
	/**节假日实体类*/
	private Holiday holiday;
	
	/**节假日*/
	private Date holidayTime;
	
	/**
	 * 初始化参数
	 */
	public HolidayAction(){
		Properties p = new Properties();
		try {
			p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("holiday.properties"));
			showBeforeMonths = Integer.valueOf(p.getProperty("showBeforeMonths"));
			alterAfterMonths = Integer.valueOf(p.getProperty("alterAfterMonths"));
		} catch (IOException e) {
			logger.error("初始化参数  error . " , e );
		}
	}
	
	/**
	 * 功能获得服务器时间
	 * 返回格式 “yyyy-MM-dd”
	 * @return
	 */
	public String getServerTime(){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			ajax( format.format(new Date()));
		} catch (Exception e) {
			logger.error("getServerTime error . " , e );
			ajax(null);
		}
		return null;
	}
	/**
	 * 功能获得日期控件最大最小限制
	 * 返回格式 “yyyy-MM-dd”
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String getLimitMonth(){
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			Map<String , Object> map = new HashMap<String, Object>();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			/*最小限制向前查看SHOWBEFOREMONTHS 月的*/
			Date beforeDate = new Date();
			beforeDate.setMonth(beforeDate.getMonth() - showBeforeMonths);
			beforeDate.setDate(1);
			map.put("beforeDate", format.format(beforeDate));
			
			/*最大   向后查看ALTERAFTERMONTHS 月的*/
			Date nextMonth = new Date();
			nextMonth.setMonth(nextMonth.getMonth() + alterAfterMonths + 1);
			nextMonth.setDate(1);
			nextMonth.setDate(nextMonth.getDate()-1);
			map.put("nextMonth", format.format(nextMonth));
			///为空怎么办
			List<String> allHoliday = getAllHoliday();
			if(allHoliday != null){
				map.put("allHo", allHoliday);
			}else{
				logger.info( " 节假日为空  " );
			}
			list.add(map);
			ajax(list);
		} catch (Exception e) {
			logger.error("getLimitMonth error . " , e );
		}
		return null;
	}
	
	/**
	 * 节假日获得
	 * @return
	 */
	public List<String> getAllHoliday(){
		List<String> hoNo = new ArrayList<String>();//不是节假日
		List<String> hoYes = new ArrayList<String>();//是节假日
		List<Holiday> hoList = null;//获得所有节假日数据
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			Integer isHoliday = null;
			isHoliday = 1; //1 为：是节假日
			hoList = holidayService.findHolidayList(isHoliday);
			for(Holiday ho : hoList){
				if(ho.getHoliday() != null){
					hoYes.add(format.format(ho.getHoliday()));
				}
			}
			isHoliday = 0; //0 为：非节假日
			hoList = holidayService.findHolidayList(isHoliday);
			for(Holiday ho : hoList){
				if(ho.getHoliday() != null){
					hoNo.add(format.format(ho.getHoliday()));
				}
			}
			/*获得全部周六周日*/
			List<String> all = getDefaultHoliday();
			/*去除周六周日非节假日*/
			all.removeAll(hoNo);
			/*获得全部节假日*/
			hoYes.addAll(all);
		} catch (Exception e) {
			logger.error("getAllHoliday error . " , e );
		}
		
		return hoYes;
	}
	
	
	/**
	 * 获得默认周六周日
	 * @return
	 */
	 @SuppressWarnings("deprecation")
	public List<String> getDefaultHoliday()
	    {
		 List<String> list = new ArrayList<String>();
		 	/*
		 	 * 获得往前SHOWBEFOREMONTHS 减去一个的月的最后一天
		 	 */
			Date beforeDate = new Date();
			beforeDate.setMonth(beforeDate.getMonth() - showBeforeMonths);
			beforeDate.setDate(1);
			beforeDate.setDate(beforeDate.getDate()-1);
			/*
			 * 获得往前SHOWBEFOREMONTHS 加上一个的月的第一天
			 * */
			Date nextMonth = new Date();
			nextMonth.setMonth(nextMonth.getMonth() + alterAfterMonths + 1);
			nextMonth.setDate(1);					
			
	        Calendar calendarSat =Calendar.getInstance(); //当前日期,用于周六
	        Calendar calendar =Calendar.getInstance(); //当前日期,用于日
	        Calendar cstart =Calendar.getInstance(); 
	        Calendar cend =Calendar.getInstance(); 
	        cstart.setTime(beforeDate);
	        cend.setTime(nextMonth);
	        /*
	         * 所有周六日期
	         * Calendar.DAY_OF_WEEK因为本地区为周日开始算为1 周一为2
	         */
	        calendarSat.add(Calendar.DAY_OF_MONTH,-calendarSat.get(Calendar.DAY_OF_WEEK)); 
	        Calendar d = (Calendar)calendarSat.clone(); //复制当前日历
	        //向前  在限制时间之内每次向前7天
	        for(;calendarSat.before(cend)&&calendarSat.after(cstart);calendarSat.add(Calendar.DAY_OF_YEAR, -7))
	        {
	        	list.add(getStrHo(calendarSat));
	        }
	        //向后在限制时间之内每次向后7天
	        for(;d.before(cend)&&d.after(cstart);d.add(Calendar.DAY_OF_YEAR, 7))
	        {
	        	list.add(getStrHo(d));
	        }
	        /*
	         * 所有周日日期
	         * Calendar.DAY_OF_WEEK因为本地区为周日开始算为1 周一为2
	         */
	        calendar.add(Calendar.DAY_OF_MONTH,-calendar.get(Calendar.DAY_OF_WEEK)+1); 
	        Calendar cal = (Calendar)calendar.clone(); //复制当前日历
	        //向前 在限制时间之内每次向前7天
	        for(;calendar.before(cend)&&calendar.after(cstart);calendar.add(Calendar.DAY_OF_YEAR, -7))
	        {
	        	list.add(getStrHo(calendar));
	        }
	        //向后 在限制时间之内每次向后7天
	        for(;cal.before(cend)&&cal.after(cstart);cal.add(Calendar.DAY_OF_YEAR, 7))
	        {
	        	list.add(getStrHo(cal));
	        }
	        
	        return list;
	    }
	     
	   /**
	    * 获得拼装好的日期
	    * @param calendar
	    * @return 格式：YYYY-MM-DD
	    */
	    @SuppressWarnings("static-access")
		public String getStrHo(Calendar calendar)
	    {
	    	String holiday = "";
        	holiday += calendar.get(calendar.YEAR)+"-";
        	if(calendar.get(calendar.MONTH)+1 >= 10){ //拼装月
        		holiday += calendar.get(calendar.MONTH)+1;
        	}else{
        		holiday += "0"+(calendar.get(calendar.MONTH)+1);
        	}
        	if(calendar.get(calendar.DATE) >= 10){
        		holiday += "-"+calendar.get(calendar.DATE);
        	}else{
        		holiday += "-0"+calendar.get(calendar.DATE);
        	}
        	return holiday;
	    }
	
	    /**
	     * 功能：修改节假日
	     */
	    public String updateHoliday(){
	    		UserInfo user = getLoginUser();
	    	try {
	    		List<Holiday> list = holidayService.findByHoliday(holidayTime);
	    		Timestamp nowTime = new Timestamp(new Date().getTime());
	    		//是否如果查询出数据进行修改
	    		if(list != null && list.size() > 0){
	    			Holiday oldHo = list.get(0);
	    			//如果存在节假日   则修改
	    			if(holiday != null&&oldHo != null){
	    				oldHo.setHoliday(new Timestamp(holidayTime.getTime()));
    					oldHo.setIsHoliday(holiday.getIsHoliday());
    					oldHo.setLastUpdateTime(nowTime);
    					oldHo.setLastUpdateUserId(user.getUserId());
    					holidayService.updateHoliday(oldHo);
    					
    					if(holiday.getIsHoliday() == 0){
    						logger.info("节假日 ， 取消。 当前操作人：" + user.getUserId() + oldHo);
    					}else{
    						logger.info("节假日 ，  新增。当前操作人：" + user.getUserId() + oldHo);
    					}
	    			}
	    		}else{//没有数据添加
					Holiday ho = new Holiday();
					ho.setCreateTime(nowTime);
					ho.setCreateUserId(user.getUserId());
					ho.setHoliday(new Timestamp(holidayTime.getTime()));
					ho.setIsDelete(0);
					ho.setIsForkGroup(user.getIsForkGroup());
					ho.setIsHoliday(holiday.getIsHoliday());
					ho.setCompanyId(user.getCompanyId());
					holidayService.updateHoliday(ho);
					logger.info("节假日 ， 新增 。 当前操作人：" + user.getUserId() + ho);
				}
	    		ajax(1);//修改成功
			} catch (Exception e) {
				logger.error("updateHoliday error. 当前操作人：" + user.getUserId(), e);
				ajax(0);//修改失败
			}
	    	
	    	return null;
	    }
		public Holiday getHoliday() {
			return holiday;
		}
		public void setHoliday(Holiday holiday) {
			this.holiday = holiday;
		}
		public Date getHolidayTime() {
			return holidayTime;
		}
		public void setHolidayTime(Date holidayTime) {
			this.holidayTime = holidayTime;
		}

}
