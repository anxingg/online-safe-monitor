package cn.com.qytx.hotline.holiday.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.com.qytx.hotline.holiday.domain.Holiday;
import cn.com.qytx.platform.base.dao.BaseDao;

/**
 * 作者：李贺
 * 创建时间：2014年12月7日
 * 修改时间：2014年12月7日
 *
 */
@Component
public class HolidayDao extends BaseDao<Holiday, Integer> implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -547710940596139838L;

	/**
	 * 功能：获得节假日数据
	 * @param isHoliday
	 * @return
	 */
	public List<Holiday> findHolidayList (Integer isHoliday){
		StringBuilder hql = new StringBuilder();
		hql.append(" 1=1 ");
		if(isHoliday != null){
			hql.append(" and isDelete = 0 and isHoliday = " + isHoliday);
		}
		return super.dataFilter().findAll(hql.toString()) ;
	}
	
	/**
	 * 功能：修改节假日数据
	 */
	public String updateHoliday(Holiday ho){
		super.saveOrUpdate(ho);
		return "";
	}
	
	/**
	 * 查找已经存在的节假日
	 * @param nowTime
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public List<Holiday> findByHoliday(Date nowTime){
		
		StringBuilder hql = new StringBuilder();
		hql.append(" 1 = 1 ");
		if(nowTime != null){
			//比较当前年月日
			hql.append(" and YEAR(holiday) = " +(nowTime.getYear()+1900));
			hql.append(" and MONTH(holiday) = " +(nowTime.getMonth()+1));
			hql.append(" and DAY(holiday) = " +nowTime.getDate());
		}
		return super.findAll(hql.toString());
	}
	
	
}
