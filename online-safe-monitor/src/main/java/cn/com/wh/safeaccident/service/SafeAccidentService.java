package cn.com.wh.safeaccident.service;

import java.sql.Timestamp;
import java.util.Date;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.safeaccident.domain.SafeAccident;
import cn.com.wh.safeaccident.domain.SafeAccidentStatisticsResult;

public interface SafeAccidentService {

	/**
	 * 分页查询安全生产事故
	 * 
	 * @param pageable
	 * @param keyword
	 *            关键字
	 * @param accidentCharacter
	 *            事故性质
	 * @param accidentName
	 *            事故名称
	 * @param whroletype
	 *            角色
	 * @param groupId
	 *            企业ID
	 * @param beginDate
	 *            事故查询开始时间
	 * @param endDate
	 *            事故查询结束时间
	 * @return Page<SafeAccident>
	 */
	Page<SafeAccident> findByPage(Pageable pageable, String keyword,
			Integer accidentCharacter, String accidentName, Integer whroletype,
			Integer groupId, Date beginDate, Date endDate);

	/**
	 * 分页统计查询
	 * 
	 * @param pageable
	 * @param occurredTimeBegin
	 *            事故发生的开始时间
	 * @param occurredTimeEnd
	 *            事故发生的结果时间
	 * @param accidentCharacter
	 *            事故性质
	 * @param whroletype
	 *            角色
	 * @param groupId
	 *            企业ID
	 * @return Page<SafeAccidentStatisticsResult>
	 */
	Page<SafeAccidentStatisticsResult> findStatisticsResultByPage(
			Pageable pageable, Timestamp occurredTimeBegin,
			Timestamp occurredTimeEnd, Integer accidentCharacter,
			Integer whroletype, Integer groupId);

	/**
	 * @param safeAccident
	 * @return
	 */
	SafeAccident saveOrUpdate(SafeAccident safeAccident);

	/**
	 * @param vid
	 * @return
	 */
	SafeAccident findOne(Integer vid);
}
