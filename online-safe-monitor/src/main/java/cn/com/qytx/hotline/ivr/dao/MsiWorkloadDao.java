package cn.com.qytx.hotline.ivr.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import cn.com.qytx.hotline.ivr.domain.MsiWorkload;
import cn.com.qytx.hotline.ivr.domain.SeatWorkload;
import cn.com.qytx.platform.base.dao.BaseDao;

/**
 * 功能: 坐席状态统计表 版本: 1.0 开发人员: 李华伟 创建日期: 2013-8-21 修改日期: 2013-8-21 修改列表:
 */
@SuppressWarnings("serial")
@Component
public class MsiWorkloadDao extends BaseDao<MsiWorkload, Integer> implements Serializable {
	/**
	 * 功能：根据状态统计坐席工作量
	 * 
	 * @return List<MsiWorkload>
	 */
	public List<MsiWorkload> findMsiWorkloadGroupByState(Timestamp beginTime,
			Timestamp endTime ,Integer companyId ) {
		List<MsiWorkload> msiWorkloadList = new ArrayList<MsiWorkload>();

		// 查询sql语句
		StringBuilder hqlSb = new StringBuilder();
		hqlSb.append("select msiUserID, msiState, sum(length) from MSIWorkload where length is not null ");

		hqlSb.append(" and company_id =");
		hqlSb.append(companyId);
		hqlSb.append(" ");
		
		if (null != beginTime) {
			hqlSb.append(" and beginTime >= '" + beginTime + "' ");
		}

		if (null != endTime) {
			hqlSb.append(" and endTime <= '" + endTime + "' ");
		}

		hqlSb.append(" group by msiUserID, msiState");
		Query query = entityManager.createNativeQuery(hqlSb.toString());
		@SuppressWarnings("rawtypes")
		List result = query.getResultList();

		if (null != result) {
			for (Object obj : result) {
				Object[] temp = (Object[]) obj;
				MsiWorkload tempMsiWorkload = new MsiWorkload();
				tempMsiWorkload.setMsiUserID((Integer) (temp[0]));
				tempMsiWorkload.setMsiState((Integer) (temp[1]));
				tempMsiWorkload.setLength(Long.parseLong((temp[2]).toString()));
				msiWorkloadList.add(tempMsiWorkload);
			}
		}

		// 获取已结束,但是未统计在result列表中的数据
		StringBuilder hqlSb2 = new StringBuilder();
		List<Object> params2 = new ArrayList<Object>();
		hqlSb2.append(" length is not null ");

		if (null != beginTime) {
			hqlSb2.append(" and");
			if (null != endTime) {
				hqlSb2.append(" (");
			}
			hqlSb2.append(" (beginTime < ?  and endTime > ?)");
			params2.add(beginTime);
			params2.add(beginTime);
		}

		if (null != endTime) {

			if (null != beginTime) {
				hqlSb2.append(" or (beginTime < ?  and endTime > ?))");
			} else {
				hqlSb2.append(" and (beginTime < ?  and endTime > ?)");
			}
			params2.add(endTime);
			params2.add(endTime);
		}

		if (null != beginTime || null != endTime) {
			List<MsiWorkload> spanList = super.dataFilter().findAll(hqlSb2.toString(),
					params2.toArray());
			if (null != spanList && !spanList.isEmpty()) {
				updateSpanMsiWorkload(spanList, beginTime, endTime);
				msiWorkloadList.addAll(spanList);
			}
		}

		// 获取所有未结束的统计情况
		String hql = " length is null";
		List<MsiWorkload> unCompleteList = super.dataFilter().findAll(hql);
		if (null != unCompleteList && !unCompleteList.isEmpty()) {
			updateMsiWorkload(unCompleteList, beginTime, endTime);
			msiWorkloadList.addAll(unCompleteList);
		}

		return msiWorkloadList;
	}

	private void updateSpanMsiWorkload(List<MsiWorkload> unCompleteList,
			Timestamp beginTimeSrc, Timestamp endTimeSrc) {
		for (MsiWorkload temp : unCompleteList) {
			// 获取开始时间
			Timestamp beginTime = temp.getBeginTime();
			Timestamp endTime = temp.getEndTime();

			if (null != beginTimeSrc && beginTimeSrc.after(beginTime)) {
				beginTime = beginTimeSrc;
			}

			if (null != endTimeSrc && endTimeSrc.before(endTime)) {
				endTime = endTimeSrc;
			}

			if (beginTime.before(endTime)) {
				// 计算时长
				long length = (endTime.getTime() - beginTime.getTime()) / 1000;
				temp.setLength(length);
			} else {
				temp.setLength(0L);
			}
		}
	}

	private void updateMsiWorkload(List<MsiWorkload> unCompleteList,
			Timestamp beginTimeSrc, Timestamp endTimeSrc) {
		for (MsiWorkload temp : unCompleteList) {
			// 获取开始时间
			Timestamp beginTime = temp.getBeginTime();
			Timestamp endTime = new Timestamp(System.currentTimeMillis());

			if (null != beginTimeSrc && beginTimeSrc.after(beginTime)) {
				beginTime = beginTimeSrc;
			}

			if (null != endTimeSrc && endTimeSrc.before(endTime)) {
				endTime = endTimeSrc;
			}

			if (beginTime.before(endTime)) {
				// 计算时长
				long length = (endTime.getTime() - beginTime.getTime()) / 1000;
				temp.setLength(length);
			} else {
				temp.setLength(0L);
			}
		}
	}

	/**
	 * 功能：根据时间队列统计坐席工作量
	 * @param beginTime  开始时间
	 * @param endTime   结束时间
	 * @param queueId   队列Id
	 * @return 结果信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<Integer, SeatWorkload> findSeatWorkloadGroupByState(
			Timestamp beginTime, Timestamp endTime, Integer queueId ,Integer companyId) {
		Map<Integer, SeatWorkload> resultMap = new HashMap<Integer, SeatWorkload>();
		// 查询sql语句 呼入数 外呼数
		StringBuilder hqlSb = new StringBuilder();
		hqlSb.append("select msiuserId, callType, count(vid) from MSICallLog where 1=1");
		
		hqlSb.append(" and company_id =");
		hqlSb.append(companyId);
		hqlSb.append(" ");
		
		if (null != beginTime) {
			hqlSb.append(" and callInTime >= '" + beginTime + "' ");
		}
		if (null != endTime) {
			hqlSb.append(" and callInTime <= '" + endTime + "' ");
		}
		if (null != queueId && -1 != queueId) {
			hqlSb.append(" and ");
			hqlSb.append(" serviceId = " + queueId);
		}
		hqlSb.append(" group by msiuserId, callType");
		Query query1 = entityManager.createNativeQuery(hqlSb.toString());
		List resultCallNum = query1.getResultList();
		calculateResultCallNum(resultMap, resultCallNum);
		// 统计 接听数 平均振铃时长（秒） 平均呼入通话时长（秒） 呼入通话总时长（分） 外呼成功数 平均外呼通话时长（秒） 外呼通话总时长（分）
		// 注:只统计接通的电话
		hqlSb = new StringBuilder();
		hqlSb.append("select msiuserId, callType, count(vid) as count1 , avg(cast(linkCalled as int)) as avg1, sum(seconds) as sum1, avg(seconds) as avg2  from MSICallLog where 1=1");
		
		hqlSb.append(" and company_id =");
		hqlSb.append(companyId);
		hqlSb.append(" ");
		
		if (null != beginTime) {
			hqlSb.append(" and callInTime >= '" + beginTime + "' ");
		}
		if (null != endTime) {
			hqlSb.append(" and callInTime <= '" + endTime + "' ");
		}
		if (null != queueId && -1 != queueId) {
			hqlSb.append(" and serviceId = " + queueId);
		}
		hqlSb.append(" and seconds > 0 group by msiuserId, callType");
		Query query = entityManager.createNativeQuery(hqlSb.toString());
		List resultConnectNum = query.getResultList();
		calculateResultConnectNum(resultMap, resultConnectNum);
		return resultMap;
	}
	private void calculateResultCallNum(Map<Integer, SeatWorkload> resultMap,
			List<Object> resultCallNum) {
		if (null != resultCallNum && !resultCallNum.isEmpty()) {
			for (Object obj : resultCallNum) {
				Object[] temp = (Object[]) obj;
				SeatWorkload tempSeatWorkload = resultMap.get((Integer) temp[0]);
				if (tempSeatWorkload == null) {
					tempSeatWorkload = new SeatWorkload();
					resultMap.put((Integer) temp[0], tempSeatWorkload);
				}
				// 是否为呼入数, 外呼数
				if ((Integer) temp[1] == 1) {
					tempSeatWorkload.setCallIn(((Integer) temp[2]).intValue());
				} else if ((Integer) temp[1] == 2) {
					tempSeatWorkload.setCallOut(((Integer) temp[2]).intValue());
				}
			}
		}
	}

	private void calculateResultConnectNum(
			Map<Integer, SeatWorkload> resultMap, List<Object> resultCallNum) {
		if (null != resultCallNum && !resultCallNum.isEmpty()) {
			for (Object obj : resultCallNum) {
				Object[] temp = (Object[]) obj;
				SeatWorkload tempSeatWorkload = resultMap.get((Integer) temp[0]);
				if (tempSeatWorkload == null) {
					tempSeatWorkload = new SeatWorkload();
					resultMap.put((Integer) temp[0], tempSeatWorkload);
				}
				// 是否为呼入数, 外呼数
				if ((Integer) temp[1] == 1) {
					// 接听数
					tempSeatWorkload.setAnswercallIn(((Integer) temp[2]).intValue());
					// 平均振铃时长（秒）
					tempSeatWorkload.setAverageRingingSecond(((Integer) temp[3]).intValue());
					// 平均呼入通话时长（秒）
					tempSeatWorkload.setAverageCallInSecond(((Integer) temp[5]).intValue());
					// 呼入通话总时长（分）
					tempSeatWorkload.setTotalCallInMinute((((Integer) temp[4])
							.intValue() < 60 ? 60 : ((Integer) temp[4])
							.intValue()) / 60);
				} else if ((Integer) temp[1] == 2) {
					// 外呼成功数
					tempSeatWorkload.setCallOutSuccess(((Integer) temp[2])
							.intValue());

					// 平均外呼通话时长（秒）
					tempSeatWorkload
							.setAverageCallOutSecond(((Integer) temp[5])
									.intValue());

					// 外呼通话总时长（分）
					tempSeatWorkload.setTotleCallOutMinute((((Integer) temp[4])
							.intValue() < 60 ? 60 : ((Integer) temp[4])
							.intValue()) / 60);
				}
			}
		}
	}

	/**
	 * 功能：
	 * 
	 * @param beginTime
	 * @param endTime
	 * @param queueId
	 * @return
	 */
	@SuppressWarnings(value={"unchecked", "rawtypes"})
	public SeatWorkload findSeatWorkloadGroupByDay(Timestamp beginTime,
			Timestamp endTime, Integer queueId, Integer id) {
		Map<Integer, SeatWorkload> resultMap = new HashMap<Integer, SeatWorkload>();
		// 查询sql语句 呼入数 外呼数
		StringBuilder hqlSb = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		hqlSb.append("select msiuserId, callType, count(vid) from MsicallLog where msiuserId=?");
		params.add(id);
		if (null != beginTime) {
			hqlSb.append(" and callInTime >= ? ");
			params.add(beginTime);
		}

		if (null != endTime) {
			hqlSb.append(" and callInTime <= ? ");
			params.add(endTime);
		}
		if (null != queueId && -1 != queueId) {
			hqlSb.append(" and serviceId = " + queueId);
		}
		hqlSb.append(" group by msiuserId,callType");
		List resultCallNum = super.findAll(hqlSb.toString(), params.toArray());
		calculateResultCallNum(resultMap, resultCallNum);

		// 统计 接听数 平均振铃时长（秒） 平均呼入通话时长（秒） 呼入通话总时长（分） 外呼成功数 平均外呼通话时长（秒） 外呼通话总时长（分）
		// 注:只统计接通的电话
		params = new ArrayList<Object>();
		hqlSb = new StringBuilder();
		hqlSb.append("select msiuserId, callType, count(vid), avg(cast(linkCalled as int)), sum(seconds), avg(seconds)  from MsicallLog where  msiuserId=?");
		params.add(id);
		if (null != beginTime) {
			hqlSb.append(" and callInTime >= ? ");
			params.add(beginTime);
		}

		if (null != endTime) {
			hqlSb.append(" and callInTime <= ? ");
			params.add(endTime);
		}
		if (null != queueId && -1 != queueId) {
			hqlSb.append(" and serviceId = " + queueId);
		}
		hqlSb.append(" and seconds > 0 group by  msiuserId,callType");
		
		List resultConnectNum = super.findAll(hqlSb.toString(),
				params.toArray());
		calculateResultConnectNum(resultMap, resultConnectNum);
		return resultMap.get(id);
	}

	/**
	 * 功能：获取通话时长
	 * 
	 * @param beginTime
	 * @param endTime
	 * @param id
	 * @return
	 */
	public List<MsiWorkload> findMsiWorkloadGroupByDay(Timestamp beginTime,
			Timestamp endTime, Integer id) {
		List<MsiWorkload> msiWorkloadList = new ArrayList<MsiWorkload>();
		// 查询sql语句
		// 数据库时间大于 查询的开始时间 数据库的结束时间 小查询的结束时间
		StringBuilder hqlSb = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		hqlSb.append("select msiState, length from MsiWorkload where length is not null and msiUserID=?");
		params.add(id);
		if (null != beginTime) {
			hqlSb.append(" and beginTime >= ? ");
			params.add(beginTime);
		}

		if (null != endTime) {
			hqlSb.append(" and endTime <= ? ");
			params.add(endTime);
		}
		// 这句可能会更新数据
		List<MsiWorkload> result = super.findAll(hqlSb.toString(), params.toArray());
		if (null != result) {
			for (Object obj : result) {
				Object[] temp = (Object[]) obj;
				MsiWorkload tempMsiWorkload = new MsiWorkload();
				tempMsiWorkload.setMsiUserID(id);
				tempMsiWorkload.setMsiState((Integer) (temp[0]));
				tempMsiWorkload.setCountlength((Long) (temp[1]));
				msiWorkloadList.add(tempMsiWorkload);
			}
		}
		// 数据库开始时间 小于 查询的开始时间 数据库的结束时间 大于查询的开始时间
		StringBuilder hqlSb2 = new StringBuilder();
		List<Object> params2 = new ArrayList<Object>();
		hqlSb2.append("from MsiWorkload where length is not null and msiUserID=? and ( (beginTime <= ?  and endTime >= ?) or ( beginTime<= ?  and endTime>=? ) )");
		params2.add(id);
		params2.add(beginTime);
		params2.add(beginTime);
		params2.add(endTime);
		params2.add(endTime);
		if (null != beginTime || null != endTime) {
			List<MsiWorkload> spanList = super.findAll(hqlSb2.toString(),
					params2.toArray());
			if (null != spanList && !spanList.isEmpty()) {
				setSpanMsiWorkload(spanList, beginTime, endTime);
				msiWorkloadList.addAll(spanList);
			}
		}

		return msiWorkloadList;
	}

	private void setSpanMsiWorkload(List<MsiWorkload> unCompleteList,
			Timestamp beginTimeSrc, Timestamp endTimeSrc) {
		for (MsiWorkload temp : unCompleteList) {
			// 获取开始时间
			Timestamp beginTime = temp.getBeginTime();
			Timestamp endTime = temp.getEndTime();

			if (null != beginTimeSrc && beginTimeSrc.after(beginTime)) {
				beginTime = beginTimeSrc;
			}

			if (null != endTimeSrc && endTimeSrc.before(endTime)) {
				endTime = endTimeSrc;
			}

			if (beginTime.before(endTime)) {
				// 计算时长
				long length = (endTime.getTime() - beginTime.getTime()) / 1000;
				temp.setCountlength(length);
			} else {
				temp.setCountlength(0L);
			}
		}
	}
	
	
    public MsiWorkload getMsiWorkload(Integer msiUserID,Integer msiState){
   	 // 查询sql语句
       StringBuilder hqlSb = new StringBuilder();
       List<Object> params = new ArrayList<Object>();
       //hqlSb.append("from MsiWorkload where endTime is null ");
       hqlSb.append(" endTime is null ");
       if (null != msiUserID){
           hqlSb.append(" and msiUserID = ? ");
           params.add(msiUserID);
       }
       
       if (null != msiState){
           hqlSb.append(" and msiState = ? ");
           params.add(msiState); 
       }
       
       List<MsiWorkload> list = super.dataFilter(null).findAll(hqlSb.toString(),params.toArray());
       if(list.size()>0){
    	   //System.out.println("MsiWorkload集合："+list.size());
    	   MsiWorkload thisMsiWorkload = list.get(0);
    	   return thisMsiWorkload;
       }else{
    	   return null;
       }
       
   }
}
