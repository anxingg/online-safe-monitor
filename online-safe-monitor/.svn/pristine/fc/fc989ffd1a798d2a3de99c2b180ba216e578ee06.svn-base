package cn.com.qytx.hotline.ivr.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.ivr.dao.MsicallLogDao;
import cn.com.qytx.hotline.ivr.dao.MsiuserDao;
import cn.com.qytx.hotline.ivr.domain.MsiWorkload;
import cn.com.qytx.hotline.ivr.domain.Msiuser;
import cn.com.qytx.hotline.ivr.domain.SeatMonitor;
import cn.com.qytx.hotline.ivr.service.IMsiUser;
import cn.com.qytx.hotline.ivr.service.IMsiWorkload;
import cn.com.qytx.hotline.util.CallCenterUtil;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * User: 黄普友 Date: 12-11-1 Time: 上午12:09
 */
@SuppressWarnings("serial")
@Service
@Transactional
public class MsiUserImpl extends BaseServiceImpl<Msiuser> implements IMsiUser,
		Serializable {

	@Autowired
	MsiuserDao userDao;

	@Autowired
	private MsicallLogDao msiCallLogDao;
	
	@Autowired
    IMsiWorkload msiWorkloadService;

	public Msiuser login(String workNo, String pass) {
		return userDao.login(workNo, pass);
	}

	public MsiuserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(MsiuserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 根据登陆人信息获取坐席号码 功能：
	 * 
	 * @param workNo
	 * @return
	 */
	public Msiuser getMsiuerByWorkNo(String workNo) {
		return userDao.findByWorkNo(workNo);
	}

	/**
	 * 功能：列表
	 * 
	 * @return
	 */
	@Override
	public List<Object> misUserList() {
		return userDao.misUserList();
	}

	/**
	 * 功能：获取坐席列表
	 * 
	 * @return List<Msiuser>
	 */
	public List<Msiuser> findAllmisUserList() {
		return userDao.findAllmisUserList();
	}

	/**
	 * 功能：根据Id获取坐席信息
	 * 
	 * @param vid
	 *            ID
	 * @return Msiuser
	 */
	public Msiuser findById(Integer vid) {
		return userDao.findOne(vid);
	}

	/**
	 * 功能：获取坐席列表
	 * 
	 * @return List<Msiuser>
	 */
	public List<Msiuser> findMymisUserList(UserInfo userInfo) {
		return userDao.findMymisUserList(userInfo);
	}

	/**
	 * 功能：获取坐席监控列表
	 * 
	 * @param u
	 *            当前用户
	 * @param scope
	 *            范围0表示全部 1表示自己所在班
	 * @return SeatMonitor
	 */
	public SeatMonitor getSeatMonitor(UserInfo u, Integer scope) {
		List<Msiuser> msiUserList = null;
		if (null != scope && 1 == scope) {
			msiUserList = findMymisUserList(u);
		} else {
			msiUserList = findMymisUserList(null); 
		}
		SeatMonitor sm = new SeatMonitor();
		if (null != msiUserList && !msiUserList.isEmpty()) {
			// 统计 签入 正在外呼 正在接听 空闲 置忙
			for (Msiuser tempMsiuser : msiUserList) {
				//坐席状态 0：离线 1：在线 2：停用 3：呼入通话 4：置闲 5：置忙 13：外呼通话
				Integer state = tempMsiuser.getState();
				setSeatMonitorContent(sm ,tempMsiuser,state);
                if(state!=null&&state>0){
                	Integer vid = tempMsiuser.getVid();
                    MsiWorkload workLoad = msiWorkloadService.getMsiWorkload(vid, state);
                    if(workLoad!=null){
                    	Timestamp beginTime = workLoad.getBeginTime();
                        Timestamp now = new Timestamp(System.currentTimeMillis());
                        Long time = (now.getTime() - beginTime.getTime())/(1000*60) ;
                        String st = time.toString();
                        if(st!=null&&!"".equals(st)){
                        	 Integer stateTime = Integer.parseInt(st);
                        	 if( stateTime < 0 ){
                            	 stateTime = 0;
                             }
                             tempMsiuser.setStateTime(stateTime);
                        }else{
                        	tempMsiuser.setStateTime(0);
                        }
                    }else{
                    	tempMsiuser.setStateTime(0);
                    }
                }
			}
		}

		// 当天系统总排队次数
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Integer allInQueue = msiCallLogDao.getAllInQueueByTime(
				CallCenterUtil.getTodayBeginTime(), now);
		sm.setTodayAllInQueue(allInQueue);

		// 当天系统总呼损次数
		Integer allCallLose = msiCallLogDao.getAllCallLoseByTime(
				CallCenterUtil.getTodayBeginTime(), now, null);
		sm.setTodayAllCallLose(allCallLose);

		// 本组的呼损次数 msiUserList 为 null 表示全部
		Integer myallCallLose = msiCallLogDao.getAllCallLoseByTime(
				CallCenterUtil.getTodayBeginTime(), now, msiUserList);
		sm.setTotleCallLose(myallCallLose);

		// 本组的呼入电话数：23 呼入时长：526分钟 外呼电话数：20 外呼时长：356分钟 msiUserList 为 null 表示全部
		List<Object> objList = msiCallLogDao.getCallTimeByTime(
				CallCenterUtil.getTodayBeginTime(), now, msiUserList);
		if (null != objList && !objList.isEmpty()) {
			for (Object obj : objList) {
				Object[] result = (Object[]) obj;
				if ((Integer) result[0] == 1) {
					sm.setTotleCallInSecond(((Integer) result[1]).intValue());
					sm.setTotleCallInNum(((Integer) result[2]).intValue());
				}
				if ((Integer) result[0] == 2) {
					sm.setTotleCallOutNum(((Integer) result[2]).intValue());
					sm.setTotleCallOutSecond(((Integer) result[1]).intValue());
				}
			}
		}
		return sm;
	}
	
	public void setSeatMonitorContent(SeatMonitor sm ,Msiuser tempMsiuser,Integer state){
		// 0：离线 1：在线 2：停用 3：呼入通话 4：置闲 5：置忙 13：外呼通话
		if (null != state && 3 == state) {
			// 正在接听
			sm.setInCalling(sm.getInCalling() + 1);
			sm.setOnLine(sm.getOnLine() + 1);
		} else if (null != state && 13 == state) {
			// 正在外呼
			sm.setOutCalling(sm.getOutCalling() + 1);
			sm.setOnLine(sm.getOnLine() + 1);
		} else if (null != state && (4 == state || 1 == state)) {
			// 空闲
			sm.setFree(sm.getFree() + 1);
			sm.setOnLine(sm.getOnLine() + 1);
		} else if (null != state && 5 == state) {
			// 置忙
			sm.setBusy(sm.getBusy() + 1);
			sm.setOnLine(sm.getOnLine() + 1);
		}

		// 区分内线和外线坐席内线坐席列表
		if (null != state
				&& (4 == state || 1 == state || 3 == state
						|| 5 == state || 13 == state)) {
			Integer msitype = tempMsiuser.getMsitype();
			Integer msiWorkType = tempMsiuser.getMsiWorkType();
			if (msitype == 0 && msiWorkType == 1) {
				sm.getInsideUserList().add(tempMsiuser);
			} else if (msitype == 1 && msiWorkType == 1) {
				sm.getOutsideUserList().add(tempMsiuser);
			}
		}
	}

	/**
	 * @Title:查询话务员分页列表
	 * @Description:
	 * @param @param pageInfo
	 * @param @param params
	 * @param @return
	 * @return 返回类型
	 * @throws
	 */
	public Page<Msiuser> findMsiuserByPage(Pageable pageInfo, String params,
			UserInfo userInfo, Integer queueId, Integer groupId, String workIds,Integer isForkGroup) {
		return userDao.findMsiuserByPage(pageInfo, params, userInfo, queueId,
				groupId, workIds,isForkGroup);
	}

	@Override
	public List<Msiuser> findMsiUserListByIds(String ids) {
		return userDao.findMsiUserListByIds(ids);
	}

	/**
	 * 功能：查询当前在线坐席的分组
	 * @param msiType:坐席类型
	 * @param isForkGroup：权限控制 
	 * @return
	 */
	@Override
	public List<Object> findMsiUserGruops(Integer msiType, Integer isForkGroup) {
		return userDao.findMsiUserGruops(msiType, isForkGroup);
	}

	@Override
	public Page<Msiuser> monitorMisuserByPage(Pageable pageInfo,
			Integer isForkGroup, String searchKey) {
		return userDao.monitorMisuserByPage(pageInfo, isForkGroup, searchKey);
	}

}
