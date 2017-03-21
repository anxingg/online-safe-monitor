package cn.com.qytx.hotline.holiday.service;

import java.util.Date;
import java.util.List;

import cn.com.qytx.hotline.holiday.domain.Holiday;
import cn.com.qytx.platform.base.service.BaseService;

/**
 * 作者：李贺
 * 		节假日action
 * 创建时间：2014年12月7日
 * 修改时间：2014年12月7日
 *
 */
public interface IHoliday extends BaseService<Holiday>{

	/**
	 * 功能：获得节假日数据
	 * @param isHoliday
	 * @return
	 */
	List<Holiday> findHolidayList (Integer isHoliday);
	
	/**
	 * 功能：修改节假日数据
	 */
	String updateHoliday(Holiday ho);
	/**
	 * 查找已经存在的节假日
	 */
	List<Holiday> findByHoliday(Date nowTime);
	
}
