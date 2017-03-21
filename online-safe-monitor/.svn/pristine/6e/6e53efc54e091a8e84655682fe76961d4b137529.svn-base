package cn.com.qytx.hotline.ivr.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.ivr.dao.MsiserviceDao;
import cn.com.qytx.hotline.ivr.dao.MsiserviceUserDao;
import cn.com.qytx.hotline.ivr.dao.MsiuserDao;
import cn.com.qytx.hotline.ivr.domain.Msiservice;
import cn.com.qytx.hotline.ivr.domain.MsiserviceUser;
import cn.com.qytx.hotline.ivr.domain.Msiuser;
import cn.com.qytx.hotline.ivr.service.IMsiservice;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.org.dao.UserDao;
import cn.com.qytx.platform.org.domain.UserInfo;

@SuppressWarnings("serial")
@Service
@Transactional
public class MsiserviceImpl extends BaseServiceImpl<Msiservice> implements
		IMsiservice, Serializable {
	/**
	 * 队列维护
	 */
	@Autowired
	private MsiserviceDao msiserviceDao;

	/**
	 * 队列坐席中间表维护
	 */
	@Autowired
	private MsiserviceUserDao msiserviceUserDao;

	@Autowired
	private MsiuserDao msiuserDao;

	@SuppressWarnings("rawtypes")
	@Autowired
	private UserDao userDao;

	public Msiservice saveOrUpdate(Msiservice arg0) {
		msiserviceDao.updateMsiservice(arg0);

		// 更新坐席和队列的对应关系
		msiserviceUserDao.deleteByMsiserviceid(arg0.getVid());
		String msiUserIds = arg0.getMsiUserIds();
		if (!StringUtils.isEmpty(msiUserIds)) {
			// 根据用户Id 获取对应坐席id

			String[] msiUserIdArr = msiUserIds.split(",");
			List<Msiuser> msiUserList = msiuserDao
					.findMsiUserByUserIds(msiUserIdArr);

			for (Msiuser msiuser : msiUserList) {
				MsiserviceUser mu = new MsiserviceUser();
				mu.setMsiid(msiuser.getVid());
				mu.setServiceId(arg0.getVid());
				mu.setCompanyId(1);
				msiserviceUserDao.saveOrUpdate(mu);
			}
		}
		return null;
	}

	/**
	 * 功能：获取所有队列信息
	 * isFilter == true 过滤
	 * isFilter == false 不过滤
	 * @return List<Msiservice>
	 */
	@SuppressWarnings("unchecked")
	public List<Msiservice> findAllDao(boolean isFilter) {
		List<Msiservice> msiserviceList = new ArrayList<Msiservice>();
		if(isFilter){
			msiserviceList = msiserviceDao.findAllDao();
		}else{
			msiserviceList = msiserviceDao.findNoFilter();
		}
		if (null != msiserviceList && !msiserviceList.isEmpty()) {
			for (Msiservice msiservice : msiserviceList) {
				// 将坐席的ID转成用户表中保存的记录
				List<Msiuser> msiuserList = msiuserDao
						.findMsiUserByServiceId(msiservice.getVid().toString());
				if (null != msiuserList && !msiuserList.isEmpty()) {
					String[] loginNames = new String[msiuserList.size()];
					StringBuffer loginStrs =new StringBuffer();
					for (int i = 0; i < msiuserList.size(); i++) {
						loginNames[i] = msiuserList.get(i).getWorkNo();
						loginStrs.append(msiuserList.get(i).getWorkNo());
						loginStrs.append(",");
					}
					List<UserInfo> userInfoList = userDao
							.findByLoginNames(loginStrs.toString());
					msiservice.setUserInfoList(userInfoList);
				}
			}
		}
		return msiserviceList;
	}
	/**
	 * 功能：根据队列Id获取队列信息
	 * 
	 * @param vid
	 *            vid
	 * @return Msiservice
	 */
	@SuppressWarnings("unchecked")
	public Msiservice findById(Integer vid) {
		Msiservice msiservice = msiserviceDao.findOne(vid);
		if (null != msiservice) {
			// 将坐席的ID转成用户表中保存的记录
			List<Msiuser> msiuserList = msiuserDao
					.findMsiUserByServiceId(msiservice.getVid().toString());
			if (null != msiuserList && !msiuserList.isEmpty()) {
				String[] loginNames = new String[msiuserList.size()];
				StringBuffer loginStrs = new StringBuffer();
				for (int i = 0; i < msiuserList.size(); i++) {
					loginNames[i] = msiuserList.get(i).getWorkNo();
					loginStrs.append(msiuserList.get(i).getWorkNo());
					loginStrs.append(",");
				}
				List<UserInfo> userInfoList = userDao
						.findByLoginNames(loginStrs.toString());
				msiservice.setUserInfoList(userInfoList);
			}
		}
		return msiservice;
	}

	@Override
	public List<Object[]> findAllByMsiUserIds(String msiids) {
		return msiserviceDao.findAllByMsiUserIds(msiids);
	}

	@Override
	public List<Object[]> findAllByIdsAndIsFork(String msiids,
			Integer isForkGroup) {
		return msiserviceDao.findAllByIdsAndIsFork(msiids, isForkGroup);
	}

	@Override
	public List<Object[]> findMsiServiceIdAndNameByForkGroupId(
			Integer isForkGroup) {
		return msiserviceDao.findMsiServiceIdAndNameByForkGroupId(isForkGroup);
	}
	

}
