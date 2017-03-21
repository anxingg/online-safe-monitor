package cn.com.qytx.hotline.ivr.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.qytx.hotline.ivr.domain.MsiWorkload;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;

/**
 * 功能:坐席考勤统计dao
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-12-22
 * 修改日期: 2014-12-22
 * 修改列表:
 */
@Component
public class SeatWorkReportDao extends BaseDao<MsiWorkload, Integer> implements Serializable {

	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 7670675680073733559L;
	
	public Page<MsiWorkload> findSeatWorkByPage(Pageable page,Integer isForkGroup,String beginTimeStr,String endTimeStr,String msiUserIdsStr){
		StringBuilder hql = new StringBuilder();
		hql.append(" msiState = 1 ");
		if(isForkGroup!=null&&isForkGroup!=-1&&isForkGroup!=2){
			hql.append(" and isForkGroup= "+isForkGroup);
		}else{
			hql.append(" and isForkGroup not in (-1,2) ");
		}
		if(beginTimeStr!=null){
			Timestamp beginTime = getTimestampByString(beginTimeStr, "yyyy-MM-dd HH:mm:ss");
			hql.append(" and ( beginTime >= '"+beginTime+"' or endTime >= '"+beginTime+"' )");
		}
		if(endTimeStr!=null){
			Timestamp endTime = getTimestampByString(endTimeStr, "yyyy-MM-dd HH:mm:ss");
			hql.append(" and ( beginTime <= '"+endTime+"' or endTime <= '"+endTime+"' )");
		}
		if(StringUtils.isNotBlank(msiUserIdsStr)){
			hql.append(" and msiUserID in ( "+msiUserIdsStr+" ) ");
		}
		return super.findAll(hql.toString(), page);
	}
	
	/**
	 * 把 指定 格式的字符串转成 Timestamp
	 * @param timeString
	 * @return
	 */
	private Timestamp getTimestampByString(String timeString,String timeFormat){//yyyy-MM-dd HH:mm
		if(timeString==null || "".equals(timeString)){
			return null;
		}
		Timestamp ts=null;
		SimpleDateFormat sdf= new SimpleDateFormat(timeFormat);
		try {
			java.util.Date date=sdf.parse(timeString);
			ts=new Timestamp(date.getTime());
		} catch (Exception e) {
		}
		return ts;
	}
	
	/**
	 * 2015-01-21马恺添加
	 * 获得需要插入数据的登陆状态信息
	 * @return
	 */
	public List<MsiWorkload> getLoginedMsiUser(){
		StringBuilder sql = new StringBuilder();
		sql.append("select vid,MSIUserID,MSIState,BeginTime,EndTime,Length,is_fork_group ,GETDATE(), company_id");
		sql.append(" from MSIWorkload");
		sql.append(" where (EndTime is null or (EndTime>DATEADD(MI,-3,GETDATE()))) and MSIState=1 ");
		sql.append(" order by BeginTime desc");
		
		@SuppressWarnings("unchecked")
		List<Object[]> objList = super.entityManager.createNativeQuery(sql.toString()).getResultList();
		List<MsiWorkload> mwkList = new ArrayList<MsiWorkload>();
		
		for(Object[] obj:objList){
			MsiWorkload tempMwk = new MsiWorkload();
			tempMwk.setVid((Integer)obj[0]);
			tempMwk.setMsiUserID((Integer)obj[1]);
			tempMwk.setMsiState((Integer)obj[2]);
			tempMwk.setBeginTime((Timestamp)obj[3]);
			tempMwk.setEndTime((Timestamp)obj[4]);
			tempMwk.setLength(obj[5]==null?null:Long.valueOf((Integer)obj[5]));
			tempMwk.setIsForkGroup((Integer)obj[6]);
			tempMwk.setNativeTime((Timestamp)obj[7]);
			tempMwk.setCompanyId((Integer)obj[8]);
			mwkList.add(tempMwk);
		}
		
		return mwkList;
	}
	
	/**
	 * 2015-01-21马恺添加
	 * 得到登陆和退出之间用户的坐席状态集合
	 * @return
	 */
	public List<MsiWorkload> getLoginedMsistate(Integer msiUserId,Timestamp beginTime,Timestamp endTime){
		StringBuilder sql = new StringBuilder();
		sql.append("select vid,MSIUserID,MSIState,BeginTime,EndTime,Length,is_fork_group,GETDATE(), company_id ");
		sql.append(" from MSIWorkload");
		sql.append(" where MSIUserID = "+msiUserId+" and BeginTime>= CAST ( '"+beginTime+"' AS datetime ) and (EndTime <= CAST ( '"+endTime+"' AS datetime ) or EndTime is null) and MSIState in (4,5)");
		sql.append(" order by BeginTime desc");
		
		@SuppressWarnings("unchecked")
		List<Object[]> objList = super.entityManager.createNativeQuery(sql.toString()).getResultList();
		List<MsiWorkload> mwkList = new ArrayList<MsiWorkload>();
		
		for(Object[] obj:objList){
			MsiWorkload tempMwk = new MsiWorkload();
			tempMwk.setVid((Integer)obj[0]);
			tempMwk.setMsiUserID((Integer)obj[1]);
			tempMwk.setMsiState((Integer)obj[2]);
			tempMwk.setBeginTime((Timestamp)obj[3]);
			tempMwk.setEndTime((Timestamp)obj[4]);
			tempMwk.setLength(obj[5]==null?null:Long.valueOf((Integer)obj[5]));
			tempMwk.setIsForkGroup((Integer)obj[6]);
			tempMwk.setNativeTime((Timestamp)obj[7]);
			tempMwk.setCompanyId((Integer)obj[8]);
			mwkList.add(tempMwk);
		}
		return mwkList;
	}
	
}
