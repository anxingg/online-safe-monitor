package cn.com.qytx.hotline.ivr.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.ivr.action.MsiCalllogAction;
import cn.com.qytx.hotline.ivr.dao.MsicallLogDao;
import cn.com.qytx.hotline.ivr.domain.CallCenterConst;
import cn.com.qytx.hotline.ivr.domain.CallQuantity;
import cn.com.qytx.hotline.ivr.domain.InQueueInfo;
import cn.com.qytx.hotline.ivr.domain.MsicallLog;
import cn.com.qytx.hotline.ivr.domain.MsicallLogArea;
import cn.com.qytx.hotline.ivr.service.IMsicallLog;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.utils.datetime.DateTimeUtil;

/**
 * 项目名称：wzerp 类名称：通话记录实现层 类描述： 创建人：WangBin 创建时间：2012-11-22
 * 
 * @version
 */
@Service
@Transactional
public class MsicallLogImpl extends BaseServiceImpl<MsicallLog> implements
		IMsicallLog {
	/**
	 * log4j日志对象
	 */
    private final static MonitorLogger logger =new Log4jImpl(MsiCalllogAction.class);
	@Autowired
	private MsicallLogDao msiCallLogDao;

	public Page<MsicallLog> findCallLogByPage(Pageable pageInfo,
			String searchkey, int callType, String begDate, String endDate,
			String calledNum,UserInfo userInfo) {

		return msiCallLogDao.findCallLogByPage_called(pageInfo, searchkey,
				callType, begDate, endDate, calledNum,userInfo.getCompanyId());
	}

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
			int callType, String begDate, String endDate) {

		return msiCallLogDao.findCallLogByPage(pageInfo, call, callType,
				begDate, endDate, null, null);
	}

	public MsicallLog findById(Class<MsicallLog> arg0, Integer id) {
		return msiCallLogDao.findOne(id);
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

		return msiCallLogDao.getTotalCount(call, callType, begDate, endDate);
	}

	/**
	 * @return the msiCallLogDao
	 */
	public MsicallLogDao getMsiCallLogDao() {
		return msiCallLogDao;
	}

	/**
	 * @param msiCallLogDao
	 *            the msiCallLogDao to set
	 */
	public void setMsiCallLogDao(MsicallLogDao msiCallLogDao) {
		this.msiCallLogDao = msiCallLogDao;
	}

	@Override
	public Page<MsicallLog> findCallLogByPage(Pageable pageInfo, String call,
			String workNo, String begDate, String endDate, UserInfo u,
			String cclSn) {
		return msiCallLogDao.findCallLogByPage(pageInfo, call, workNo, begDate,
				endDate, u, cclSn);
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
	public List<CallQuantity> findCallQuantity(Timestamp beginTime,
			Timestamp endTime, Integer queueId,Integer isForkGroup) {
		if (null == beginTime || null == endTime) {
			return null;
		}

		List<Object> list = msiCallLogDao.findCallQuantity(beginTime, endTime,
				queueId,isForkGroup);

		Map<String, CallQuantity> resultMap = new HashMap<String, CallQuantity>();
		List<CallQuantity> result = new ArrayList<CallQuantity>();
		// 根据开始时间和结束时间初始化结果
		while (beginTime.getTime() <= endTime.getTime()) {
			CallQuantity cq = new CallQuantity();
			cq.setCallDate(DateTimeUtil.timestampToString(beginTime,
					CallCenterConst.DATE_FORMAT_STR));
			resultMap.put(DateTimeUtil.timestampToString(beginTime,
					CallCenterConst.DATE_FORMAT_STR), cq);
			beginTime.setTime(beginTime.getTime() + 24 * 60 * 60 * 1000);
		}

		CallQuantity totle = new CallQuantity();
		totle.setCallDate("总计");
		if (null != list && !list.isEmpty()) {
			CallQuantity temp;
			for (Object obj : list) {
				Object[] objArr = (Object[]) obj;
				if (resultMap.get((String) objArr[0]) != null) {
					temp = resultMap.get((String) objArr[0]);
				} else {
					temp = new CallQuantity();
				}

				temp.setCallDate((String) objArr[0]);
				Integer callType = (Integer) objArr[1];
				if (null != callType && 1 == callType.intValue()) {
					temp.setCallInNum((Integer) objArr[2]);
				} else if (null != callType && 2 == callType.intValue()) {
					temp.setCallOutNum((Integer) objArr[2]);
				}

				resultMap.put((String) objArr[0], temp);
			}
		}
		Set<Map.Entry<String, CallQuantity>> entrySet = resultMap.entrySet();
		if (null != entrySet) {
			for (Map.Entry<String, CallQuantity> entry : entrySet) {
				CallQuantity cq = entry.getValue();
				cq.setTotleCallNum(cq.getCallInNum() + cq.getCallOutNum());
				
				// 计算汇总信息
				totle.setCallInNum(totle.getCallInNum() + cq.getCallInNum());
				totle.setCallOutNum(totle.getCallOutNum() + cq.getCallOutNum());
				
				result.add(cq);
			}
			// 汇总总呼叫量
			totle.setTotleCallNum(totle.getCallInNum() + totle.getCallOutNum());
		}

		Collections.sort(result);
		result.add(totle);
		return result;
	}
	
	/**
	 * 功能：分天统计话务量综合统计表
	 * 新需求修改
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param queueId
	 *            队列Id
	 * @return List<CallQuantity>
	 */
	public List<CallQuantity> findCallQuantityNew(Timestamp beginTime,
			Timestamp endTime, Integer queueId,Integer isForkGroup , Integer companyId) {
		if (null == beginTime || null == endTime) {
			return null;
		}
		
		//获得
		List<Object> list = msiCallLogDao.findCallQuantityOne(beginTime, endTime,queueId,isForkGroup,companyId);
		List<Object> listTwo = msiCallLogDao.getCallLossSum(beginTime, endTime,queueId,isForkGroup,companyId);
		List<Object> listThree =msiCallLogDao.findCallQuantityTwo(beginTime, endTime,queueId,isForkGroup,companyId);
//		List<Object> isNullList =msiCallLogDao.getWlByEndTimeIsNull(beginTime, endTime,queueId,isForkGroup);
		
		
		Map<String, CallQuantity> resultMap = new HashMap<String, CallQuantity>();
		List<CallQuantity> result = new ArrayList<CallQuantity>();
		// 根据开始时间和结束时间初始化结果
		while (beginTime.getTime() <= endTime.getTime()) {
			CallQuantity cq = new CallQuantity();
			cq.setCallDate(DateTimeUtil.timestampToString(beginTime,
					CallCenterConst.DATE_FORMAT_STR));
			resultMap.put(DateTimeUtil.timestampToString(beginTime,
					CallCenterConst.DATE_FORMAT_STR), cq);
			beginTime.setTime(beginTime.getTime() + 24 * 60 * 60 * 1000);
		}
		modifyMap(resultMap, list);
		modifyMap2(resultMap, listTwo);
		modifyMap3(resultMap, listThree);
		
		
		//计算所有字段总量放在length-1中
		CallQuantity totle = new CallQuantity();
		totle.setCallDate("总计");
		Set<Map.Entry<String, CallQuantity>> entrySet = resultMap.entrySet();
		if (null != entrySet) {
			for (Map.Entry<String, CallQuantity> entry : entrySet) {
				CallQuantity cq = entry.getValue();
				cq.setTotleCallNum(cq.getCallInNum() + cq.getCallOutNum());
				result.add(cq);

				// 计算合计信息
				totle.setCallInNum(totle.getCallInNum() + cq.getCallInNum());
				totle.setCallOutNum(totle.getCallOutNum() + cq.getCallOutNum());
				totle.setTotleCallLoss(totle.getTotleCallLoss() + cq.getTotleCallLoss());
				totle.setTotleCallInTime(totle.getTotleCallInTime() + cq.getTotleCallInTime());
				totle.setTotleCallOutTime(totle.getTotleCallOutTime() + cq.getTotleCallOutTime());
				totle.setTotleLoginTime(totle.getTotleLoginTime() + cq.getTotleLoginTime());
				totle.setTotleBosyTime(totle.getTotleBosyTime() + cq.getTotleBosyTime());
				totle.setTotleNoBosyTime(totle.getTotleNoBosyTime() + cq.getTotleNoBosyTime());
			}
			
			// 汇总总呼叫量
			totle.setTotleCallNum(totle.getCallInNum() + totle.getCallOutNum());
		}
		
		Collections.sort(result);
		result.add(totle);
		return result;
	}
	
	private void modifyMap(Map<String, CallQuantity> resultMap, List<Object> list){
		//拼装前1，2，3，4，6，7字段
				if (null != list && !list.isEmpty() ) {
					CallQuantity temp;
					for (Object obj : list) {
						Object[] objArr = (Object[]) obj;
						if (resultMap.get((String) objArr[0]) != null) {
							temp = resultMap.get((String) objArr[0]);
						} else {
							temp = new CallQuantity();
						}
						
						temp.setCallDate((String) objArr[0]);
						Integer callType = (Integer) objArr[1];
						if (null != callType && 1 == callType.intValue()) {
							temp.setCallInNum((Integer) objArr[2] );
							//单位    分
							temp.setTotleCallInTime((Integer)objArr[3] / 60);
						} else if (null != callType && 2 == callType.intValue()) {
							temp.setCallOutNum((Integer) objArr[2]);
							//单位    分
							temp.setTotleCallOutTime((Integer)objArr[3] / 60);
						}
						
						resultMap.put((String) objArr[0], temp);
					}
				}
	}
	
	private void modifyMap2(Map<String, CallQuantity> resultMap, List<Object> listTwo){
		//添加第5个字段 呼损总数
		if(listTwo != null){
			CallQuantity temp1 ;
			for(Object obj2 : listTwo){
				Object[] objArr2 = (Object[]) obj2;
				if(objArr2[0] == null){
					continue;
				}
				if(resultMap.get(objArr2[0]) != null){
					//不为空在本条记录中继续添加
					temp1 = resultMap.get(objArr2[0]);
					temp1.setTotleCallLoss((Integer)objArr2[1]);
				}else{
					temp1 = new CallQuantity();
					temp1.setCallDate((String)objArr2[0]);
					temp1.setTotleCallLoss((Integer)objArr2[1]);
				}
				resultMap.put((String)objArr2[0], temp1);
			}
		}
	}
	
	private void modifyMap3(Map<String, CallQuantity> resultMap, List<Object> listThree){
		//添加登录总时长，置忙总时长，空闲总时长字段
		if(listThree != null){
			CallQuantity temp2 ;
			for(Object obj3 : listThree){
				Object[] objArr3 = (Object[]) obj3;
				if(objArr3[0] == null){
					continue;
				}
				Integer msiState = (Integer) objArr3[1];
				Integer data = (Integer)objArr3[2] / 60;
				if(resultMap.get(objArr3[0]) != null){
					//不为空在本条记录中继续添加
					temp2 = resultMap.get(objArr3[0]);
					if(msiState == 1){
						//登录总时长
						temp2.setTotleLoginTime(data);
					}else if(msiState == 5){
						//置忙总时长
						temp2.setTotleBosyTime(data);
					}else if(msiState == 4){
						//空闲总时长
						temp2.setTotleNoBosyTime(data);
					}else{
						continue;
					}
					
				}else{
					temp2 = new CallQuantity();
					temp2.setCallDate((String)objArr3[0]);
					if(msiState == 1){
						//登录总时长
						temp2.setTotleLoginTime(data);
					}else if(msiState == 5){
						//置忙总时长
						temp2.setTotleBosyTime(data);
					}else if(msiState == 4){
						//空闲总时长
						temp2.setTotleNoBosyTime(data);
					}else{
						continue;
					}
					
				}
				resultMap.put((String)objArr3[0], temp2);
			}
			
		}
	}

	/**
	 * 功能：根据时间段分队列统计呼损原因
	 * 
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param queueId
	 *            队列Id
	 * @param callphone
	 *            主叫电话
	 * @param queueId
	 *            被叫电话
	 * @return List<CallQuantity> 结果信息
	 */
	public Page<MsicallLog> findCallBlockList(Pageable pageInfo,
			Timestamp beginTime, Timestamp endTime, Integer queueId,
			String searchkey) {
		return msiCallLogDao.findCallBlockList(pageInfo, beginTime, endTime,
				queueId, searchkey);
	}

	/**
	 * 功能：分时段统计排队信息
	 * 
	 * @param beginTime统计开始时间
	 * @param endTime统计结束时间
	 * @param queueId队列Id
	 * @return List<InQueueInfo>
	 */
	public List<InQueueInfo> findInQueueInfo(Timestamp beginTime,
			Timestamp endTime, Integer queueId, Integer groupId,Integer isForkGroup,Integer companyId) {
		if (null == beginTime || null == endTime) {
			return null;
		}
		List<Object> list = msiCallLogDao.findInQueueInfo(beginTime, endTime,
				queueId, groupId,isForkGroup,companyId);
		Map<String, InQueueInfo> resultMap = new HashMap<String, InQueueInfo>();
		List<InQueueInfo> result = new ArrayList<InQueueInfo>();
		// 根据开始时间和结束时间初始化结果
		while (beginTime.getTime() <= endTime.getTime()) {
			InQueueInfo inQueueInfo = new InQueueInfo();
			inQueueInfo.setInQueueHour(DateTimeUtil.timestampToString(
					beginTime, CallCenterConst.HOUR));
			resultMap.put(DateTimeUtil.timestampToString(beginTime,
					CallCenterConst.HOUR), inQueueInfo);
			beginTime.setTime(beginTime.getTime() + 60 * 60 * 1000);
		}
		InQueueInfo totle = new InQueueInfo();
		totle.setInQueueHour("总计");
		if (null != list && !list.isEmpty()) {
			InQueueInfo temp;
			for (Object obj : list) {
				Object[] objArr = (Object[]) obj;

				// 获取统计小时
				if (resultMap.get((String) objArr[0]) != null) {
					temp = resultMap.get((String) objArr[0]);
				} else {
					temp = new InQueueInfo();
					temp.setInQueueHour((String) objArr[0]);
				}
				temp.setTotleInQueueNum((Integer) objArr[1]);
				resultMap.put((String) objArr[0], temp);
			}
		}

		Set<Map.Entry<String, InQueueInfo>> resultSet = resultMap.entrySet();
		if (resultSet != null) {
			for (Map.Entry<String, InQueueInfo> entry : resultSet) {
				InQueueInfo ii = entry.getValue();
				totle.setTotleInQueueNum(totle.getTotleInQueueNum()
						+ ii.getTotleInQueueNum());
				result.add(ii);
			}
		}
		Collections.sort(result);
		result.add(totle);
		return result;
	}

	@Override
	public List<String> listFileUrls(String msiIds, String basePath) {
		if (msiIds != null && !"".equals(msiIds)&&",".equals(msiIds.substring(msiIds.length() - 1,msiIds.length()))) {
			msiIds = msiIds.substring(0, msiIds.length() - 1);
		}

		List<String> list = new ArrayList<String>();
		List<MsicallLog> callLoglist = msiCallLogDao.findListByIds(msiIds);
		if (callLoglist != null && !callLoglist.isEmpty()) {

			for (Object o : callLoglist) {
				MsicallLog msl = (MsicallLog) o;
				if (msl.getVoxFile() != null && !"".equals(msl.getVoxFile())) {
					String voxFile = basePath + getVoxFilePath() + "user/"
							+ validateIsEmpty(msl.getVoxFile());
					list.add(voxFile);
				}

			}

		}
		return list;
	}

	/**
	 * @Title:获取映射文件路径
	 * @Description:
	 * @param @return
	 * @return 返回类型
	 * @throws
	 */
	public String getVoxFilePath() {
		String voxFilePath = "";
		try {
			Properties properties = new Properties();
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("voxFilePath.properties"));
			voxFilePath = (String) properties.get("filepath");
		} catch (IOException e) {
//			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return voxFilePath;
	}

	/**
	 * 验证是否为空
	 * 
	 * @param str
	 * @return
	 */
	public String validateIsEmpty(String str) {
		if (str == null || str.equals("")) {
			return "";
		} else {
			return str;
		}
	}

	@Override
	public List<Map<String, Object>> findTimeFrame(Timestamp beginTime, Timestamp endTime,
			Integer isFormGroup,Integer companyId) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		String beginTimeStr = "";
		if(beginTime != null ){
			beginTimeStr = beginTime.toString();
		}
		String endTimeStr = "";
		if(endTime != null){
			endTimeStr = endTime.toString();
		}
		//用于去掉时间  . 后边的毫秒
		if(beginTimeStr.indexOf('.') >= 0){
			beginTimeStr = beginTimeStr.substring(0,beginTimeStr.indexOf('.'));
		}
		if(endTimeStr.indexOf('.') >= 0){
			endTimeStr = endTimeStr.substring(0,endTimeStr.indexOf('.'));
		}
		//获得CallInTime字段中有的所有时段的呼入总量
		List<Object> callInNumList =  msiCallLogDao.getTimeFrame(0,beginTimeStr, endTimeStr, isFormGroup ,companyId);
		//获得全时段（0-23）的呼入总量
		map.put("callInNumMap", getTimeFrameNum(callInNumList));
		//获得CallInTime字段中有的所有时段的人工受理数量
		List<Object> acceptedNumList =  msiCallLogDao.getTimeFrame(1,beginTimeStr, endTimeStr, isFormGroup ,companyId);
		map.put("acceptedNumMap", getTimeFrameNum(acceptedNumList));
		//获得CallInTime字段中有的所有时段的呼叫放弃数
		List<Object> surrenderNumList =  msiCallLogDao.getTimeFrame(2,beginTimeStr, endTimeStr, isFormGroup ,companyId);
		map.put("surrenderNumMap", getTimeFrameNum(surrenderNumList));
		//获得CallInTime字段中有的所有时段的自动查询 数量
		List<Object> autoCheckList =  msiCallLogDao.getTimeFrame(3,beginTimeStr, endTimeStr, isFormGroup ,companyId);
		map.put("autoCheckMap", getTimeFrameNum(autoCheckList));
		list.add(map);
		return list;
	}
	
	/**
	 * 作者：李贺
	 * 时间：2014年12月18日
	 * 功能：封装传过来的字段，封装为24个时段的map
	 * @return
	 */
	public Map<Integer, Object> getTimeFrameNum(List<Object> numList){
		Map<Integer, Object> map = new TreeMap<Integer, Object>();
			//获得全时段（0-23）传入字段总量  没有的时段补0
			Integer length = 24;
			Integer j = 0;
			for(int i = 0;i<length;i++){
				if(numList != null && numList.size() > 0){
					Object[] numArray =  (Object[]) numList.get(j);
					//dao层使用时间段分组，对没有的数据库没有的时间段添加0
					if(Integer.parseInt(numArray[0].toString()) == i){
						if(numList.size()-1 != j){
							j += 1 ;
						}
						map.put(i, numArray[1]);
						
					}else{
						map.put(i,0);
					}
				}else{
					map.put(i,0);
				}
			}
		
		return map;
	}

	@Override
	public List<MsicallLogArea> findCallInByArea(String beginTime,
			String endTime, Integer isForkGroup ,Integer companyId) {
		return msiCallLogDao.findCallInByArea(beginTime, endTime, isForkGroup ,companyId);
	}

}
