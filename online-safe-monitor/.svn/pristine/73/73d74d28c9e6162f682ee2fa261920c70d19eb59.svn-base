package cn.com.qytx.hotline.ivr.service;

import java.sql.Timestamp;
import java.util.List;

import cn.com.qytx.hotline.ivr.domain.MsiWorkload;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;

/**
 * 功能:坐席考勤统计
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-12-22
 * 修改日期: 2014-12-22
 * 修改列表:
 */
public interface ISeatWorkReport extends BaseService<MsiWorkload> {
	/**
	 * 功能：分页查询坐席考勤统计
	 * @param page
	 * @param vo
	 * @return
	 */
	Page<MsiWorkload> findSeatWorkByPage(Pageable page,Integer isForkGroup,String beginTimeStr,String endTimeStr,String searchKey);
	
	/**
	 * 获得需要插入数据的登陆状态信息
	 * @return
	 */
	List<MsiWorkload> getLoginedMsiUser();
	
	/**
	 * 得到登陆和退出之间用户的坐席状态集合
	 * @return
	 */
	List<MsiWorkload> getLoginedMsistate(Integer msiUserId,Timestamp beginTime,Timestamp endTime);
}
