package cn.com.qytx.hotline.ivr.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.qytx.hotline.ivr.domain.CallCenterConst;
import cn.com.qytx.hotline.ivr.domain.MsicallLog;
import cn.com.qytx.hotline.ivr.domain.MsicallLogArea;
import cn.com.qytx.hotline.ivr.domain.Msiuser;
import cn.com.qytx.oa.util.DataFilterUtilForSql;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.utils.datetime.DateTimeUtil;

/**
 * 项目名称：wzerp 类名称：通话记录Dao 类描述： 创建人：WangBin 创建时间：2012-11-22
 * 
 * @version
 */
@SuppressWarnings("serial")
@Component
public class MsicallLogDao extends BaseDao<MsicallLog, Integer> implements
		Serializable {
	
//	private static final String DEPRECATION = "deprecation";

//	private transient Logger logger = Logger.getLogger(MsicallLogDao.class);
	
	@Autowired
	MsiuserDao userDao;

	/**
	 * @Title:查询通话记录分页记录
	 * @Description:
	 * @param @param pageInfo
	 * @param @param call
	 * @param @param callType
	 * @param @param begDate
	 * @param @param endDate
	 * @param @return
	 * @return 返回类型
	 * @throws
	 */
	public Page<MsicallLog> findCallLogByPage(Pageable pageInfo, String call,
			int callType, String begDate, String endDate, UserInfo u,
			String cclSn) {
		String hql = " vid is not null ";
		List<Object> params = new ArrayList<Object>();
		if (!isEmpty(call)) {
			hql += " and call like ? ";
			params.add("%" + call + "%");
		}
		if (callType != 0) {
			hql += " and callType = ? ";
			params.add(callType);
		}
		if (!isEmpty(begDate)) {
			hql += " and CONVERT(varchar,callInTime, 23)>= ?";
			params.add(begDate);
		}
		if (!isEmpty(endDate)) {
			hql += " and CONVERT(varchar,callEndTime, 23)<= ?";
			params.add(endDate);
		}
		hql += " order by callInTime desc";
		if (null != params && params.size() > 0) {
			return this.findAll(hql, pageInfo, params.toArray());
		} else {
//			return this.findAll(hql, pageInfo);
			return this.dataFilter().findAll(hql, pageInfo);
		}

	}

	public Page<MsicallLog> findCallLogByPage(Pageable pageInfo, String call,
			int callType, String begDate, String endDate, String workNo) {
		String hql = " where vid is not null ";
		List<Object> params = new ArrayList<Object>();
		if (!isEmpty(call)) {
			hql += " and call like ?";
			params.add("%" + call + "%");
		}
		if (callType != 0) {
			hql += " and callType = ?";
			params.add(callType);
		}
		if (!isEmpty(begDate)) {
			hql += " and CONVERT(varchar,callInTime, 20)>= ?";
			params.add(begDate);
		}
		if (!isEmpty(endDate)) {
			hql += " and CONVERT(varchar,callEndTime, 20)<= ?";
			params.add(endDate);
		}
		if (!isEmpty(workNo)) {
			hql += " and  msiuserId in (select vid from Msiuser mu where mu.workNo like ?) ";
			params.add("%" + workNo + "%");
		}
		hql += " order by callInTime desc";
		if (null != params && params.size() > 0) {
			return this.findAll(hql, pageInfo, params.toArray());
		} else {
			return this.findAll(hql, pageInfo);
		}

	}

	public Page<MsicallLog> findCallLogByPage_called(Pageable pageInfo,
			String searchkey, int callType, String begDate, String endDate,
			String calledNum,Integer companyId) {
		String hql = " vid is not null  and companyId=?";
		List<Object> params = new ArrayList<Object>();
		params.add(companyId);
		if (!isEmpty(searchkey)) {
			hql += " and (call like ? or msiuserId in (select vid from Msiuser mu where mu.workNo like ?))";
			params.add("%" + searchkey + "%");
			params.add("%" + searchkey + "%");
		}
		if (!isEmpty(calledNum)) {
			hql += " and called like ?";
			params.add("%" + calledNum + "%");
		}
		if (callType != 0) {
			hql += " and callType = ?";
			params.add(callType);
		}
		if (!isEmpty(begDate)) {
			hql += " and CONVERT(varchar,callInTime, 20)>= ?";
			params.add(begDate);
		}
		if (!isEmpty(endDate)) {
			hql += " and CONVERT(varchar,callEndTime, 20)<= ?";
			params.add(endDate);
		}
		if (null != params && params.size() > 0) {
			return super.dataFilter().findAll(hql, pageInfo, params.toArray());
		} else {
			return super.dataFilter().findAll(hql, pageInfo);
		}

	}

	/**
	 * @Title:获取通话记录分页总记录数
	 * @Description:
	 * @param @param call
	 * @param @param parseInt
	 * @param @param begDate
	 * @param @param endDate
	 * @param @return
	 * @return 返回类型
	 * @throws
	 */
	public int getTotalCount(String call, int callType, String begDate,
			String endDate) {
		String hql = " vid is not null ";
		List<Object> params = new ArrayList<Object>();
		if (!isEmpty(call)) {
			hql += " and call like ?";
			params.add("%" + call + "%");
		}
		if (callType != 0) {
			hql += " and callType = ?";
			params.add(callType);
		}
		if (!isEmpty(begDate)) {
			hql += " and CONVERT(varchar,callInTime, 23)>= ?";
			params.add(begDate);
		}
		if (!isEmpty(endDate)) {
			hql += " and CONVERT(varchar,callEndTime, 23)<= ?";
			params.add(endDate);
		}
		List<MsicallLog> callLogList;
		if (null != params && params.size() > 0) {
			callLogList = this.findAll(hql, params.toArray());
		} else {
			callLogList = this.findAll(hql);
		}
		if (null != callLogList && callLogList.size() > 0) {
			return callLogList.size();
		} else {
			return 0;
		}
	}

	/**
	 * 字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public boolean isEmpty(String str) {
		if (str == null || str.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @Title:查询通话记录分页记录
	 * @Description:
	 * @param @param pageInfo
	 * @param @param call
	 * @param @param callType
	 * @param @param workNo
	 * @param @param begDate
	 * @param @param endDate
	 * @param @return
	 * @return 返回类型
	 * @throws
	 */
	public Page<MsicallLog> findCallLogByPage(Pageable pageInfo, String call,
			String workNo, String begDate, String endDate, UserInfo u,
			String cclSn) {
		String hql = "vid is not null ";
		List<Object> params = new ArrayList<Object>();
		if (!isEmpty(call)) {
			hql += " and call like ?";
			params.add("%" + call + "%");
		}
		if (!isEmpty(workNo)) {
			hql += "and msiuserId in ( select m.vid from Msiuser m where m.workNo like ? )";

			// 根据工号获取坐席ID
			params.add("%" + workNo + "%");
		}
		if (!isEmpty(begDate)) {
			hql += " and CONVERT(varchar,callInTime, 23)>= ?";
			params.add(begDate);
		}
		if (!isEmpty(endDate)) {
			hql += " and CONVERT(varchar,callEndTime, 23)<= ?";
			params.add(endDate);
		}

		if (null != params && params.size() > 0) {
			return super.findAll(hql, pageInfo, params.toArray());
		} else {
			return super.findAll(hql, pageInfo);
		}
	}

	/**
	 * 功能：根据呼叫Id和坐席Id获取唯一的话单记录
	 * 
	 * @param callId
	 *            callId
	 * @param uid
	 *            uid
	 * @return MsicallLog
	 */
	public MsicallLog findByCallIdUid(String callId, Integer uid) {
		String hql = "  callId = ? and msiuserId = ?";
		return (MsicallLog) super.findOne(hql, callId, uid);
	}

	/**
	 * 功能：获取时间段内的排队总次数
	 * 
	 * @param begin
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return Integer
	 */
	public Integer getAllInQueueByTime(Timestamp begin, Timestamp end) {
		String hql = " inQueueSeconds != -1 and callInTime >= ? and callInTime <= ?";
		return super.dataFilter().count(hql, begin, end);
	}

	/**
	 * 功能：获取时间段内呼损总次数
	 * 
	 * @param begin
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return Integer
	 */
	public Integer getAllCallLoseByTime(Timestamp begin, Timestamp end,
			List<Msiuser> msiUserList) {
		StringBuffer hql = new StringBuffer();
		hql.append(" linkCalled != 0 and seconds = 0 and msiuserId > 0 and callInTime >= ? and callInTime <= ?");
		if (null == msiUserList) {
			return super.count(hql.toString(), begin, end);
		} else if (msiUserList.isEmpty()) {
			return 0;
		} else {
			hql.append(" and msiuserId in (");
			for (int i = 0; i < msiUserList.size(); i++) {
				hql.append(msiUserList.get(i).getVid());
				if (i == msiUserList.size() - 1) {
					hql.append(")");
				} else {
					hql.append(",");
				}
			}
			return super.dataFilter().count(hql.toString(), begin, end);
		}
	}

	/**
	 * 功能：获取时间段内呼入总次数
	 * 
	 * @param begin
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return Integer
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getCallTimeByTime(Timestamp begin, Timestamp end,
			List<Msiuser> msiUserList) {
		StringBuffer hql = new StringBuffer();
		hql.append("select callType, sum(c.seconds) as sum_seconds, count(*) as call_count from MsicallLog c where c.seconds > 0 and c.callInTime >= cast('"
				+ begin.toString()
				+ "' as datetime) and c.callInTime <= cast('"
				+ end.toString()
				+ "' as datetime) ");
		if (null != msiUserList && !msiUserList.isEmpty()) {
			hql.append(" and msiuserId in (");
			for (int i = 0; i < msiUserList.size(); i++) {
				hql.append(msiUserList.get(i).getVid());
				if (i == msiUserList.size() - 1) {
					hql.append(")");
				} else {
					hql.append(",");
				}
			}
		}
		hql.append(DataFilterUtilForSql.sqlDataFilter());
		hql.append(" group by callType");
		Query query = entityManager.createNativeQuery(hql.toString());
		return query.getResultList();
	}

	/**
	 * 功能：分天统计话务量综合统计表
	 * 
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param queueId
	 *            队列Id
	 * @return List<CallQuantity>
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findCallQuantity(Timestamp beginTime,
			Timestamp endTime, Integer queueId,Integer isForkGroup) {
		StringBuffer hql = new StringBuffer();
		hql.append("select CONVERT(varchar(100), callEndTime, 23) a, CallType b, COUNT(*) c from MSICallLog where seconds > 0");
		if (null != beginTime) {
			hql.append(" and CONVERT(varchar,callEndTime, 23) >= '"
					+ DateTimeUtil.timestampToString(beginTime,
							CallCenterConst.DATE_FORMAT_STR) + "'");
		}
		if (null != endTime) {
			hql.append(" and CONVERT(varchar,callEndTime, 23) <= '"
					+ DateTimeUtil.timestampToString(endTime,
							CallCenterConst.DATE_FORMAT_STR) + "'");
		}
		if (null != queueId && -1 != queueId) {
			hql.append(" and serviceId =  ");
			hql.append(queueId);
		}
		//若坐席不属于省级部门，只查询本部门下的数据
//		if(isForkGroup!=2){
//			hql.append(" and MSIUserId in (select VId from MSIUser where is_fork_group = "+isForkGroup+" )");
//		}
		hql.append(DataFilterUtilForSql.sqlDataFilter());
		hql.append(" group by CONVERT(varchar(100), callEndTime, 23), CallType order by CONVERT(varchar(100), callEndTime, 23)");
		return super.entityManager.createNativeQuery(hql.toString()).getResultList();
	}

	/**
	 * 功能：分天统计话务量综合统计表
	 * 新需求修改此方法添加呼入呼出总量
	 * 
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param queueId
	 *            队列Id
	 * @return List<CallQuantity>
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findCallQuantityOne(Timestamp beginTime,
			Timestamp endTime, Integer queueId,Integer isForkGroup,Integer companyId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select CONVERT(varchar(100), callEndTime, 23) a, CallType b, COUNT(*) c ,SUM(seconds) d from MSICallLog where seconds > 0");
		
		hql.append(" and company_id =");
		hql.append(companyId);
		
		if (null != beginTime) {
			hql.append(" and CONVERT(varchar,callEndTime, 23) >= '"
					+ DateTimeUtil.timestampToString(beginTime,
							CallCenterConst.DATE_FORMAT_STR) + "'");
		}
		if (null != endTime) {
			hql.append(" and CONVERT(varchar,callEndTime, 23) <= '"
					+ DateTimeUtil.timestampToString(endTime,
							CallCenterConst.DATE_FORMAT_STR) + "'");
		}
		if (null != queueId && -1 != queueId) {
			hql.append(" and serviceId = ");
			hql.append(queueId);
		}
		//若坐席不属于省级部门，只查询本部门下的数据
//		if(isForkGroup!=2){
//			hql.append(" and MSIUserId in (select VId from MSIUser where is_fork_group = "+isForkGroup+" )");
//		}
		hql.append(DataFilterUtilForSql.sqlDataFilter());
		hql.append(" group by CONVERT(varchar(100), callEndTime, 23), CallType ");
		
		return super.entityManager.createNativeQuery(hql.toString()).getResultList();
	}
	
	/**
	 * 功能：分天统计话务量综合统计表
	 * 	获得呼损总量
	 * @param beginTime
	 * @param endTime
	 * @param queueId
	 * @param isForkGroup
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getCallLossSum(Timestamp beginTime,
			Timestamp endTime, Integer queueId,Integer isForkGroup , Integer companyId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select CONVERT(varchar(100), callEndTime, 23) a, COUNT(*) b  from MSICallLog where seconds = 0 and linkCalled >0");
		
		hql.append(" and company_id =");
		hql.append(companyId);
		
		if (null != beginTime) {
			hql.append(" and CONVERT(varchar,callEndTime, 23) >= '"
					+ DateTimeUtil.timestampToString(beginTime,
							CallCenterConst.DATE_FORMAT_STR) + "'");
		}
		if (null != endTime) {
			hql.append(" and CONVERT(varchar,callEndTime, 23) <= '"
					+ DateTimeUtil.timestampToString(endTime,
							CallCenterConst.DATE_FORMAT_STR) + "'");
		}
		if (null != queueId && -1 != queueId) {
			hql.append(" and serviceId = ");
			hql.append(queueId);
		}
		//若坐席不属于省级部门，只查询本部门下的数据
//		if(isForkGroup!=2){
//			hql.append(" and MSIUserId in (select VId from MSIUser where is_fork_group = "+isForkGroup+" )");
//		}
		hql.append(DataFilterUtilForSql.sqlDataFilter());
		hql.append(" group by CONVERT(varchar(100), callEndTime, 23), CallType ");
		return super.entityManager.createNativeQuery(hql.toString()).getResultList();
	}
	
	/**
	 * 功能：分天统计话务量综合统计表
	 * 	获得登录总时长，置忙总时长，空闲总时长字段
	 * @param beginTime
	 * @param endTime
	 * @param queueId
	 * @param isForkGroup
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findCallQuantityTwo(Timestamp beginTime,
			Timestamp endTime, Integer queueId,Integer isForkGroup,Integer companyId) {
		StringBuffer hql = new StringBuffer();
		/**
		 * 结束时间不为空的情况
		 */
		hql.append("select convert(VARCHAR(100),BeginTime,23) a, MSIState , sum(length) from MSIWorkload where EndTime is not null and BeginTime is not null ");
		
		hql.append(" and company_id =");
		hql.append(companyId);
		
		if (null != beginTime) {
			hql.append(" and CONVERT(varchar,EndTime, 23) >= '"
					+ DateTimeUtil.timestampToString(beginTime,
							CallCenterConst.DATE_FORMAT_STR) + "'");
		}
		if (null != endTime) {
			hql.append(" and CONVERT(varchar,EndTime, 23) <= '"
					+ DateTimeUtil.timestampToString(endTime,
							CallCenterConst.DATE_FORMAT_STR) + "'");
		}
		hql.append(DataFilterUtilForSql.sqlDataFilter());
		hql.append(" group by CONVERT(varchar(100), BeginTime, 23), MSIState ");
		return super.entityManager.createNativeQuery(hql.toString()).getResultList();
		
	}
	/**
	 * 功能：分天统计话务量综合统计表
	 * 	结束时间为空 暂不用
	 * @param beginTime
	 * @param endTime
	 * @param queueId
	 * @param isForkGroup
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getWlByEndTimeIsNull(Timestamp beginTime,
			Timestamp endTime, Integer queueId,Integer isForkGroup){
		StringBuffer hql2 = new StringBuffer();
		hql2.append("select convert(VARCHAR(100) , BeginTime,20) , MSIState  from MSIWorkload where EndTime is null and BeginTime is not null ");
		hql2.append(DataFilterUtilForSql.sqlDataFilter());
		return super.entityManager.createNativeQuery(hql2.toString()).getResultList();
	}
	
	
	/**
	 * 功能：根据工单Id查询关联话单
	 * @param cclId  工单Id
	 * @return List<MsicallLog>
	 */
	public List<MsicallLog> findAllByCclId(Integer cclId) {
		if (null == cclId) {
			return null;
		}
		StringBuffer hql = new StringBuffer();
		hql.append("x.vid in (select cl.msiLogId from CclMsiLog cl where cl.cclId =?)");
		List<MsicallLog> result = super.findAll(hql.toString(), cclId);

		// 获取坐席工号和坐席姓名
		if (null != result && !result.isEmpty()) {
			for (MsicallLog ml : result) {
				Msiuser temp = userDao.findOne(ml.getMsiuserId());
				if (null != temp) {
					ml.setWorkNo(temp.getWorkNo());
					ml.setMsiuserName(temp.getName());
				}
			}
		}

		return result;
	}

	/**
	 * 功能：根据时间段分队列统计呼损原因
	 * @param beginTime   开始时间
	 * @param endTime    结束时间
	 * @param queueId   队列Id
	 * @param callphone 主叫电话
	 * @param queueId   被叫电话
	 * @return List<CallQuantity> 结果信息
	 */
	public Page<MsicallLog> findCallBlockList(Pageable pageInfo,
			Timestamp beginTime, Timestamp endTime, Integer queueId,
			String searchkey) {
		StringBuffer hql = new StringBuffer();
		hql.append(" linkCalled !=0 and seconds = 0 ");
		List<Object> params = new ArrayList<Object>();
		// 呼入开始时间
		if (null != beginTime) {
			hql.append(" and callInTime >= ?");
			params.add(beginTime);
		}
		// 呼入结束时间
		if (null != endTime) {
			hql.append(" and callInTime <= ?");
			params.add(endTime);
		}
		if (!isEmpty(searchkey)) {
			hql.append(" and (call like ? or msiuserId in (select m.vid from Msiuser m where m.workNo like ?))");
			params.add("%" + searchkey + "%");
			params.add("%" + searchkey + "%");
		}
		if (params.isEmpty()) {
			return super.dataFilter().findAll(hql.toString(), pageInfo);
		} else {
			return super.dataFilter().findAll(hql.toString(), pageInfo, params.toArray());
		}
	}

	/**
	 * 功能：分时段统计排队信息
	 * @param beginTime统计开始时间
	 * @param endTime统计结束时间
	 * @param queueId队列Id
	 * @return List<Object>
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findInQueueInfo(Timestamp beginTime, Timestamp endTime,
			Integer queueId, Integer groupId,Integer isForkGroup ,Integer companyId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select SUBSTRING(CONVERT(varchar(200), callEndTime, 120), 12, 2) a, COUNT(*) c from MSICallLog where inQueueSeconds != -1 ");
		hql.append(sqlQureyByCompanyId(companyId));
		if (null != beginTime) {
			hql.append(" and CONVERT(varchar,callEndTime, 20) >= '"
					+ DateTimeUtil.timestampToString(beginTime,
							CallCenterConst.TIME_FORMAT_STANDARD_STR) + "'");
		}
		if (null != endTime) {
			hql.append(" and CONVERT(varchar,callEndTime, 20) <= '"
					+ DateTimeUtil.timestampToString(endTime,
							CallCenterConst.TIME_FORMAT_STANDARD_STR) + "'");
		}
		if (null != queueId && -1 != queueId) {
			hql.append(" and serviceId = ");
			hql.append(queueId);
		}
//		if(isForkGroup!=2&&isForkGroup!=null){
//			hql.append(" and MSIUserId in (select VId from MSIUser where is_fork_group = "+isForkGroup+" )");
//		}
		hql.append(DataFilterUtilForSql.sqlDataFilter());
		hql.append(" group by SUBSTRING(CONVERT(varchar(200), callEndTime, 120), 12, 2)");
		return super.entityManager.createNativeQuery(hql.toString()).getResultList();
	}

	public List<MsicallLog> findListByIds(String msiIds) {
		if (msiIds != null && !"".equals(msiIds)) {
			String hql = "vid in (" + msiIds + ")";
			return this.findAll(hql);
		} else {
			return null;
		}
	}
	
	/**
	 * 功能：来电时段分布统计
	 * @param fields 字段    0: 呼入总量   1:人工受理数量    2: 呼叫放弃数  3：自动查询 数量
	 * @param beginTime
	 * @param endTime
	 * @param isFormGroup
	 * @param timeFrame 时间段（0---23）
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getTimeFrame(Integer field,String beginTime,String endTime,Integer isFormGroup,Integer companyId){
		StringBuffer sql = new StringBuffer();
		sql.append("select SUBSTRING(CONVERT(varchar(200), c.CallInTime, 120), 12, 2) as a, count(*) as b from MsicallLog c where 1=1  and c.CallType = 1");
		
		sql.append(" and c.company_id =");
		sql.append(companyId);
		sql.append(" ");
		
		if(field != null && field != 0){
			if(field == 1){//人工受理
				sql.append(" and ServiceId>0 and MSIUserID>0");
			}
			if(field == 2){
				sql.append(" and c.Seconds = 0 and c.InQueueSeconds > 0 and MSIUserId <0");
			}
			if(field == 3){
				sql.append(" and c.LinkCalled = 0 and c.Seconds = 0 and c.MSIUserId = -1 and c.InQueueSeconds < 0");
			}
		}
		if(beginTime != null && !"".equals(beginTime)){
			sql.append(" and c.CallInTime >= ' ");
			sql.append(beginTime);
			sql.append("' ");
		}
		if(endTime != null && !"".equals(endTime)){
			sql.append( " and c.CallInTime <= ' ");
			sql.append( endTime );
			sql.append( "' ");
		}
		if(isFormGroup != null){
			sql.append(" and c.is_fork_group = ' ");
			sql.append(isFormGroup);
			sql.append("' ");
		}
		sql.append(" group by SUBSTRING(CONVERT(varchar(200), c.CallInTime, 120), 12, 2)");
		sql.append(" order by SUBSTRING(CONVERT(varchar(200), c.CallInTime, 120), 12, 2)");
		Query query = entityManager.createNativeQuery(sql.toString());
		return query.getResultList();
	}	
	/**
	 * 功能：来电区域统计表
	 * @param beginTime：开始时间
	 * @param endTime：结束时间
	 * @param isForkGroup：区域
	 * @return
	 */
	public List<MsicallLogArea> findCallInByArea(String beginTime,String endTime,Integer isForkGroup , Integer companyId){
		StringBuffer sql = new StringBuffer();
		sql.append(" with cte as( ");
		//呼入量
		sql.append(" select a.name as name,isnull(b.total,0) as allcount,1 as result from (select group_name as name from tb_group_info where is_delete=0 and parent_id=0 and group_id !=2 ");
		sql.append(sqlQureyByIsfork(isForkGroup));
		sql.append(sqlQureyByCompanyId(companyId));
		sql.append(" ) a  " );
		sql.append(" left join ");
		sql.append(" (select gi.group_name as name,count(*) as total from tb_group_info gi left join MSICallLog msi on gi.group_id=msi.is_fork_group where gi.is_delete=0 and gi.parent_id=0 and gi.group_id !=2 ");
		sql.append(sqlQureyByCompanyId2(companyId));
		sql.append(sqlQuery(beginTime, endTime));
		sql.append(" group by gi.group_name) b ");
		sql.append(" on a.name = b.name ");
		sql.append(" union ");
		//人工受理
		sql.append(" select a.name as name,isnull(b.total,0) as allcount,2 as result from (select group_name as name from tb_group_info where is_delete=0 and parent_id=0 and group_id !=2 ");
		sql.append(sqlQureyByCompanyId(companyId));
		sql.append(sqlQureyByIsfork(isForkGroup));
		sql.append(" ) a left join ");
		sql.append(" (select gi.group_name as name,count(*) as total from tb_group_info gi ");
		sql.append(" left join MSICallLog msi on gi.group_id=msi.is_fork_group where gi.is_delete=0 and gi.parent_id=0 and gi.group_id !=2 and msi.ServiceId>0 and msi.MSIUserId>0 ");
		sql.append(sqlQureyByCompanyId2(companyId));
		sql.append(sqlQuery(beginTime, endTime));
		sql.append(" group by gi.group_name) b on a.name = b.name ");
		sql.append(" union ");
		//放弃数
		sql.append(" select a.name as name,isnull(b.total,0) as allcount,3 as result from (select group_name as name from tb_group_info where is_delete=0 and parent_id=0 and group_id !=2 ");
		sql.append(sqlQureyByCompanyId(companyId));
		sql.append(sqlQureyByIsfork(isForkGroup));
		sql.append(" ) a ");
		sql.append(" left join ");
		sql.append(" (select gi.group_name as name,count(*) as total from tb_group_info gi ");
		sql.append(" left join MSICallLog msi on gi.group_id=msi.is_fork_group where gi.is_delete=0 and gi.parent_id=0 and gi.group_id !=2 and msi.Seconds=0 and msi.InQueueSeconds>0 and msi.MSIUserId<0 ");
		sql.append(sqlQureyByCompanyId2(companyId));
		sql.append(sqlQuery(beginTime, endTime));
		sql.append(" group by gi.group_name) b on a.name = b.name ");
		sql.append(" union ");
		//自动查询
		sql.append(" select a.name as name,isnull(b.total,0) as allcount,4 as result from (select group_name as name from tb_group_info where is_delete=0 and parent_id=0 and group_id !=2 ");
		sql.append(sqlQureyByCompanyId(companyId));
		sql.append(sqlQureyByIsfork(isForkGroup));
		sql.append(" ) a ");
		sql.append(" left join ");
		sql.append(" (select gi.group_name as name,count(*) as total from tb_group_info gi ");
		sql.append(" left join MSICallLog msi on gi.group_id=msi.is_fork_group where gi.is_delete=0 and gi.parent_id=0 and gi.group_id !=2 and msi.LinkCalled=0 and msi.Seconds=0 and msi.MSIUserId=-1 and msi.InQueueSeconds<0 ");
		sql.append(sqlQureyByCompanyId2(companyId));
		sql.append(sqlQuery(beginTime, endTime));
		sql.append(" group by gi.group_name) b on a.name = b.name ");
		
		sql.append(" ), ");
		sql.append(" cte2 as ( ");
		sql.append(" select t0.name as areaName, ");
		sql.append(" isnull( ( select sum (t.allcount) from cte t where t.name = t0.name and t.result = 1 ), 0 ) as callInNum, ");
		sql.append(" isnull( ( select sum (t.allcount) from cte t where t.name = t0.name and t.result = 2 ), 0 ) as acceptNum, ");
		sql.append(" isnull( ( select sum (t.allcount) from cte t where t.name = t0.name and t.result = 3 ), 0 ) as giveUpNum, ");
		sql.append(" isnull( ( select sum (t.allcount) from cte t where t.name = t0.name and t.result = 4 ), 0 ) as autoQueryNum ");
		sql.append(" from cte t0 group by t0.name ");
		sql.append(" ) select * from cte2 t1 order by t1.callInNum desc ");
		
		List<MsicallLogArea> maList = getMaList(sql);
		return maList;
	}
	/**
	 * 功能：用于findCallInByArea
	 * @param isForkGroup
	 * @return
	 */
	public List<MsicallLogArea> getMaList(StringBuffer sql){
		List<MsicallLogArea> maList = new ArrayList<MsicallLogArea>();
		Query query = super.entityManager.createNativeQuery(sql.toString());
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.getResultList();
		if (list != null && !list.isEmpty()) {
			for(Object[] objs : list){
				MsicallLogArea ma = new MsicallLogArea((String)objs[0],
						(Integer)objs[1],(Integer)objs[2],(Integer)objs[3],(Integer)objs[4]);
				maList.add(ma);
			}
		}
		return maList;
	}
	
	public String sqlQureyByIsfork(Integer isForkGroup){
		StringBuffer sql = new StringBuffer();
		if(isForkGroup!=null&&isForkGroup!=2&&isForkGroup!=-1){
			sql.append(" and group_id=");
			sql.append(isForkGroup);
		}
		return sql.toString();
	}
	
	public String sqlQuery(String beginTime,String endTime){
		StringBuffer sql = new StringBuffer();
		if(StringUtils.isNotBlank(beginTime)){
			sql.append(" and msi.CallInTime >= cast ( '"+beginTime+"' as datetime ) ");
			
		}
		if(StringUtils.isNotBlank(endTime)){
			sql.append(" and msi.CallInTime <= cast ( '"+endTime+"' as datetime ) ");
		}
		return sql.toString();
	}
	
	public String sqlQureyByCompanyId(Integer companyId){
		StringBuffer sql = new StringBuffer();
		sql.append(" and company_id =");
		sql.append(companyId);
		return sql.toString();
	}
	
	public String sqlQureyByCompanyId2(Integer companyId){
		StringBuffer sql = new StringBuffer();
		sql.append(" and gi.company_id =");
		sql.append(companyId);
		sql.append(" and msi.company_id =");
		sql.append(companyId);
		return sql.toString();
	}
	
}
