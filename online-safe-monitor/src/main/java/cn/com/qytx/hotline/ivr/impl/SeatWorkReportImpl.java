package cn.com.qytx.hotline.ivr.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.ivr.dao.MsiuserDao;
import cn.com.qytx.hotline.ivr.dao.SeatWorkReportDao;
import cn.com.qytx.hotline.ivr.domain.MsiWorkload;
import cn.com.qytx.hotline.ivr.domain.Msiuser;
import cn.com.qytx.hotline.ivr.service.ISeatWorkReport;
import cn.com.qytx.oa.util.StringUtil;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;

/**
 * 功能:坐席考勤统计实现类
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-12-22
 * 修改日期: 2014-12-22
 * 修改列表:
 */
@Service
@Transactional
public class SeatWorkReportImpl extends BaseServiceImpl<MsiWorkload> implements ISeatWorkReport,Serializable {
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -9130713645906647042L;
	/**
	 * 坐席考勤统计dao
	 */
	@Autowired
	SeatWorkReportDao seatWorReportkDao;
	/**
	 * 用户dao
	 */
	@Autowired
	MsiuserDao msiUserDao;

	@Override
	/**
	 * 功能：分页查询坐席考勤统计
	 * @param page
	 * @param vo
	 * @return
	 */
	public Page<MsiWorkload> findSeatWorkByPage(Pageable page,Integer isForkGroup,String beginTimeStr,String endTimeStr,String searchKey) {
		String msiUserIdsStr = "";
		if(StringUtils.isNotBlank(searchKey)){
			List<Msiuser> muList = msiUserDao.findMsiUserBySearchKey(searchKey);
			StringBuffer msiuserIds = new StringBuffer();
			if(muList!=null){
				for(Msiuser m : muList){
					Integer msiUserId = m.getVid();
					if(msiUserId!=null){
						msiuserIds.append(msiUserId);
						msiuserIds.append(",");
					}
				}
			}
			String msiuserIdsStr = msiuserIds.toString();
			if (StringUtil.isEmpty(msiuserIdsStr))
			{
			    msiUserIdsStr = "-1";
			}
			else
			{
			    msiUserIdsStr = msiuserIdsStr.substring(0, msiuserIdsStr.length()-1);
			}
			
		}
		return seatWorReportkDao.findSeatWorkByPage(page, isForkGroup, beginTimeStr, endTimeStr, msiUserIdsStr);
	}

	@Override
	public List<MsiWorkload> getLoginedMsiUser() {
		
		return seatWorReportkDao.getLoginedMsiUser();
	}

	@Override
	public List<MsiWorkload> getLoginedMsistate(Integer msiUserId,Timestamp beginTime,Timestamp endTime) {
		
		return seatWorReportkDao.getLoginedMsistate(msiUserId,beginTime, endTime);
	}

}
