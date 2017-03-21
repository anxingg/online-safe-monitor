package cn.com.qytx.hotline.ivr.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.ivr.dao.MsiWorkloadDao;
import cn.com.qytx.hotline.ivr.dao.MsiserviceUserDao;
import cn.com.qytx.hotline.ivr.domain.MsiWorkload;
import cn.com.qytx.hotline.ivr.domain.MsiserviceUser;
import cn.com.qytx.hotline.ivr.domain.Msiuser;
import cn.com.qytx.hotline.ivr.domain.SeatWorkload;
import cn.com.qytx.hotline.ivr.service.IMsiUser;
import cn.com.qytx.hotline.ivr.service.IMsiWorkload;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.PageImpl;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;

@Service
@Transactional
public class MsiWorkloadImpl extends BaseServiceImpl<MsiWorkload> implements
		IMsiWorkload, Serializable {
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 4675233156796453098L;

	private static final String TIMEBEGIN = ":00:00";
	
	@Autowired
	private MsiWorkloadDao msiWorkloadDao;

	/**
	 * 坐席service
	 */
	@Autowired
	private transient IMsiUser msiuserService;

	@Autowired
	private MsiserviceUserDao msiserviceUserDao;

	@Override
	/**
	 * 功能：根据状态统计坐席工作量
	 * @return List<MsiWorkload>
	 */
	public List<MsiWorkload> findMsiWorkloadGroupByState(Timestamp beginTime,
			Timestamp endTime ,Integer companyId) {
		return msiWorkloadDao.findMsiWorkloadGroupByState(beginTime, endTime,companyId);
	}

	private Long getDurationByState(List<MsiWorkload> list, Integer vid,
			Integer state) {
		Long totle = 0L;
		if (null != list && !list.isEmpty()) {
			for (MsiWorkload tempMsiWorkload : list) {
				Integer msiUserID = tempMsiWorkload.getMsiUserID();
				Integer msiState = tempMsiWorkload.getMsiState();
				if (null != msiUserID && msiUserID.intValue() == vid
						&& null != msiState && msiState.intValue() == state) {
					totle += tempMsiWorkload.getLength();
				}
			}
		}
		return totle;
	}


	/**
	 * 功能：根据时间队列统计坐席工作量
	 * 
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param queueId
	 *            队列Id
	 * @return 结果信息
	 */
	public Page<SeatWorkload> findSeatWorkloadGroupByState(Pageable page,
			Timestamp beginTime, Timestamp endTime, Integer queueId,
			Integer groupId, String workIds ,Integer isForkGroup,Integer companyId) {
		List<MsiWorkload> list = msiWorkloadDao.findMsiWorkloadGroupByState(
				beginTime, endTime,companyId);

		// 查询坐席列表
		// Page<Msiuser> msiuserPageInfo = new
		// Page<Msiuser>(pageInfo.getiDisplayLength());
		// msiuserPageInfo.setPageNo(pageInfo.getPageNo());
		Page<Msiuser> msiuserPage = msiuserService.findMsiuserByPage(page,
				null, null, queueId, groupId, workIds,isForkGroup);

		Map<Integer, SeatWorkload> resultMap = msiWorkloadDao
				.findSeatWorkloadGroupByState(beginTime, endTime, queueId ,companyId);

		// 查询坐席列表
		List<Msiuser> msiuserList = msiuserPage.getContent();
		List<SeatWorkload> seatWorkloadList = new ArrayList<SeatWorkload>();
		if (msiuserList != null) {
			for (Msiuser tempMsiuser : msiuserList) {
				SeatWorkload tempSeatWorkload = resultMap.get(tempMsiuser
						.getVid());
				if (null == tempSeatWorkload) {
					tempSeatWorkload = new SeatWorkload();
				}

				// 坐席ID
				Integer vid = tempMsiuser.getVid();
				tempSeatWorkload.setMsiuserId(vid);

				// 坐席工号
				tempSeatWorkload.setWorkNo(tempMsiuser.getWorkNo());

				// 坐席姓名
				tempSeatWorkload.setName(tempMsiuser.getName());

				// 置忙时长
				Long busy = getDurationByState(list, vid, 5);
				if(busy!=null){
					tempSeatWorkload.setTotleBusyMinute(busy.intValue() / 60);
				}

				// 置闲时长
				Long idle = getDurationByState(list, vid, 4);
				if(idle!=null){
					tempSeatWorkload.setTotleFreeMinute(idle.intValue() / 60);
				}

				// 通话时长
				Long call = getDurationByState(list, vid, 3);
				Long callOut = getDurationByState(list, vid, 13);

				// 登录时长
				Long online = 0L;
				if (null != busy || null != idle || null != call
						|| null != callOut) {
					if (null != busy) {
						online = online + busy;
					}
					if (null != idle) {
						online = online + idle;
					}
					if (null != call) {
						online = online + call;
					}
					if (null != callOut) {
						online = online + callOut;
					}
					tempSeatWorkload
							.setTotleLoginMinute(online.intValue() / 60);
				}
				seatWorkloadList.add(tempSeatWorkload);
			}
		}
		Page<SeatWorkload> pageInfo = new PageImpl<SeatWorkload>(
				seatWorkloadList, page, msiuserPage.getTotalElements());

		return pageInfo;
	}

	/**
	 * 功能：根据时间队列统计某个坐席一天的工作量
	 * 
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param queueId
	 *            队列Id
	 * @return 结果信息
	 */
	@Override
	public Map<String, SeatWorkload> findSeatWorkloadGroupByDayState(
			Page<SeatWorkload> pageInfo, Timestamp beginTime,
			Timestamp endTime, Integer queueId, Integer groupId, String workIds) {
		Map<String, SeatWorkload> seatMap = new HashMap<String, SeatWorkload>();
		Msiuser mu = msiuserService.getMsiuerByWorkNo(workIds);
		boolean isgroup = true;// 默认是包含用户的
		// 根据排队服务器查询
		if (queueId > 0) {
			isgroup = false;
			List<MsiserviceUser> listMsi = msiserviceUserDao.findListById(
					mu.getVid(), queueId);
			if (listMsi.size() > 0) {
				isgroup = true;
			}
		}

		if (isgroup && mu != null) {
			// 初始化坐席map
			seatMap = this.initSeatMap(beginTime, endTime);
			seatMap = this.getSeatCallNumber(seatMap, beginTime, endTime,
					queueId, mu);
		}

		return seatMap;
	}

	/**
	 * 初始化坐席map 功能：根据开始时间和结束时间获取时间类别
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	private Map<String, SeatWorkload> initSeatMap(Timestamp beginTime,
			Timestamp endTime) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH");
		long cha = (endTime.getTime() - beginTime.getTime()) / (60 * 60 * 1000);
		Map<String, SeatWorkload> map = new LinkedHashMap<String, SeatWorkload>();
		for (int i = 0; i < cha + 1; i++) {
			map.put(sf.format(beginTime.getTime() + (long)i * 60 * 60 * 1000), null);
		}

		return map;
	}

	/**
	 * 功能： //根据时间队列统计坐席工作量 呼入数 外呼数 // 统计 接听数 平均振铃时长（秒） 平均呼入通话时长（秒） 呼入通话总时长（分）
	 * 外呼成功数 平均外呼通话时长（秒） 外呼通话总时长（分）
	 * 
	 * @param seatMap
	 * @param beginTime
	 * @param endTime
	 * @param queueId
	 * @param id
	 *            坐席id
	 * @return
	 */
	private Map<String, SeatWorkload> getSeatCallNumber(
			Map<String, SeatWorkload> seatMap, Timestamp beginTime,
			Timestamp endTime, Integer queueId, Msiuser mu) {
		String newTimeStr = "";
		String nextTimeStr = "";
		Timestamp newStartTime = null;
		Timestamp newEndTime = null;
		SimpleDateFormat ssf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		List<String> timeKey = new ArrayList<String>();
		// 把key转换为list形式
		for (Iterator<String> iterator = seatMap.keySet().iterator(); iterator
				.hasNext();) {
			String key = (String) iterator.next();
			timeKey.add(key);
		}
		List<MsiWorkload> list = null;
		SeatWorkload seatWork = null;
		try {
			for (int i = 0; i < timeKey.size(); i++) {
				newTimeStr = timeKey.get(i);
				if (timeKey.size() == 1) {
					// 根据时间队列统计坐席工作量 呼入数 外呼数
					// 统计 接听数 平均振铃时长（秒） 平均呼入通话时长（秒） 呼入通话总时长（分） 外呼成功数 平均外呼通话时长（秒）
					// 外呼通话总时长（分）
					seatWork = msiWorkloadDao.findSeatWorkloadGroupByDay(
							beginTime, endTime, queueId, mu.getVid());
					// 置忙时长 置闲时长 通话时长
					list = msiWorkloadDao.findMsiWorkloadGroupByDay(beginTime,
							endTime, mu.getVid());
				} else if (i == 0) {
					nextTimeStr = (String) timeKey.get(i + 1);
					newEndTime = new Timestamp(ssf
							.parse(nextTimeStr + TIMEBEGIN).getTime());
					seatWork = msiWorkloadDao.findSeatWorkloadGroupByDay(
							beginTime, newEndTime, queueId, mu.getVid());
					list = msiWorkloadDao.findMsiWorkloadGroupByDay(beginTime,
							newEndTime, mu.getVid());
				} else if (timeKey.size() == i + 1) {
					newStartTime = new Timestamp(ssf.parse(
							newTimeStr + TIMEBEGIN).getTime());
					seatWork = msiWorkloadDao.findSeatWorkloadGroupByDay(
							newStartTime, endTime, queueId, mu.getVid());
					list = msiWorkloadDao.findMsiWorkloadGroupByDay(
							newStartTime, endTime, mu.getVid());
				} else {
					newStartTime = new Timestamp(ssf.parse(
							newTimeStr + TIMEBEGIN).getTime());
					nextTimeStr = (String) timeKey.get(i + 1);
					newEndTime = new Timestamp(ssf
							.parse(nextTimeStr + TIMEBEGIN).getTime());
					seatWork = msiWorkloadDao.findSeatWorkloadGroupByDay(
							newStartTime, newEndTime, queueId, mu.getVid());
					list = msiWorkloadDao.findMsiWorkloadGroupByDay(
							newStartTime, newEndTime, mu.getVid());
				}
				seatWork = this.getSeatWork(seatWork, list, mu);
				seatMap.put(newTimeStr, seatWork);
			}
		}catch (ParseException e) {
//			e.printStackTrace();
		} catch (Exception e) {
//			e.printStackTrace();
		}

		return seatMap;
	}

	/**
	 * 功能：更新时长
	 * 
	 * @param seatWork
	 * @param list
	 * @return
	 */
	private SeatWorkload getSeatWork(SeatWorkload tempSeatWorkload,
			List<MsiWorkload> list, Msiuser tempMsiuser) {
		if (null == tempSeatWorkload) {
			tempSeatWorkload = new SeatWorkload();
		}
		tempSeatWorkload.setMsiuserId(tempMsiuser.getVid());

		// 坐席工号
		tempSeatWorkload.setWorkNo(tempMsiuser.getWorkNo());

		// 坐席姓名
		tempSeatWorkload.setName(tempMsiuser.getName());

		// 置忙时长
		Long busy = getDurationByState(list, tempMsiuser.getVid(), 5);
		if(busy!=null){
			tempSeatWorkload.setTotleBusyMinute(busy.intValue() / 60);
		}

		// 置闲时长
		Long idle = getDurationByState(list, tempMsiuser.getVid(), 4);
		if(idle!=null){
			tempSeatWorkload.setTotleFreeMinute(idle.intValue() / 60);
		}

		// 通话时长
		Long call = getDurationByState(list, tempMsiuser.getVid(), 3);
		Long callOut = getDurationByState(list, tempMsiuser.getVid(), 13);

		// 登录时长
		Long online = 0L;
		if (null != busy || null != idle || null != call || null != callOut) {
			if (null != busy) {
				online = online + busy;
			}
			if (null != idle) {
				online = online + idle;
			}
			if (null != call) {
				online = online + call;
			}
			if (null != callOut) {
				online = online + callOut;
			}
			tempSeatWorkload.setTotleLoginMinute(online.intValue() / 60);
		}
		return tempSeatWorkload;
	}

	/**
	 * 功能：
	 * 
	 * @param msiUserID
	 * @param msiState
	 * @return
	 */
	@Override
	public MsiWorkload getMsiWorkload(Integer msiUserID, Integer msiState) {
		MsiWorkload mwk = msiWorkloadDao.getMsiWorkload(msiUserID, msiState);
		return mwk;
	}

}
