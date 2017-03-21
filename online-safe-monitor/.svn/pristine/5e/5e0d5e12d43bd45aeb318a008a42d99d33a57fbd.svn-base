package cn.com.qytx.hotline.holiday.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.holiday.dao.HolidayDao;
import cn.com.qytx.hotline.holiday.domain.Holiday;
import cn.com.qytx.hotline.holiday.service.IHoliday;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;

/**
 * 作者：李贺
 * 创建时间：2014年12月7日
 * 修改时间：2014年12月7日
 *
 */
@Service
@Transactional
public class HolidayImpl extends BaseServiceImpl<Holiday> implements IHoliday ,Serializable{

	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -8449352152401537619L;
	@Autowired
	private HolidayDao holidayDao;
	
	
	@Override
	public List<Holiday> findHolidayList(Integer isHoliday) {
		return holidayDao.findHolidayList(isHoliday);
	}

	@Override
	public String updateHoliday(Holiday ho) {
		return holidayDao.updateHoliday(ho);
	}

	@Override
	public List<Holiday> findByHoliday(Date nowTime) {
		return holidayDao.findByHoliday(nowTime);
	}

}
