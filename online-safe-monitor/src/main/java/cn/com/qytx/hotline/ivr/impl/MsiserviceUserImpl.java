package cn.com.qytx.hotline.ivr.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.ivr.dao.MsiserviceUserDao;
import cn.com.qytx.hotline.ivr.domain.MsiserviceUser;
import cn.com.qytx.hotline.ivr.service.IMsiserviceUser;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;

/**
 * 项目名称：wzerp 类名称：呼入队列员工实现类 类描述： 创建人：WangBin 创建时间：2012-11-21
 * 
 * @version
 */

@SuppressWarnings("serial")
@Service
@Transactional
public class MsiserviceUserImpl extends BaseServiceImpl<MsiserviceUser>
		implements IMsiserviceUser, Serializable {

	@Autowired
	private MsiserviceUserDao msiserviceUserDao;

	@SuppressWarnings("deprecation")
	public void delete(int id) {
		msiserviceUserDao.bulkDelete("delete MsiserviceUser m where m.vid = ?",
				id);
	}

	@Override
	public MsiserviceUser findById(Class<MsiserviceUser> arg0, Integer id) {

		return (MsiserviceUser) msiserviceUserDao.findOne(id);
	}

	public void save(MsiserviceUser arg0) {
		msiserviceUserDao.saveOrUpdate(arg0);
	}

	@Override
	public MsiserviceUser saveOrUpdate(MsiserviceUser arg0) {
		return msiserviceUserDao.saveOrUpdate(arg0);
	}

	/**
	 * @Title:根据话务员Id、 队列Id查询记录
	 * @Description:
	 * @param @param parseInt
	 * @param @param id
	 * @param @return
	 * @return 返回类型
	 * @throws
	 */
	public List<MsiserviceUser> findListById(int msiuserId, Integer serviceId) {

		return msiserviceUserDao.findListById(msiuserId, serviceId);
	}

	/**
	 * @Title:根据员工Id查询队列员工记录
	 * @Description:
	 * @param @param vid
	 * @param @return
	 * @return 返回类型
	 * @throws
	 */
	public List<MsiserviceUser> findListByMsUserId(Integer vid) {

		return msiserviceUserDao.findListByMsUserId(vid);
	}

	/**
	 * @return the msiserviceUserDao
	 */
	public MsiserviceUserDao getMsiserviceUserDao() {
		return msiserviceUserDao;
	}

	/**
	 * @param msiserviceUserDao
	 *            the msiserviceUserDao to set
	 */
	public void setMsiserviceUserDao(MsiserviceUserDao msiserviceUserDao) {
		this.msiserviceUserDao = msiserviceUserDao;
	}

	/**
	 * 
	 * 功能：根据坐席ID删除对应的服务队列中间表
	 * 
	 * @param workNo
	 */
	public void deleteByMsiid(Integer msiid) {
		msiserviceUserDao.deleteByMsiid(msiid);
	}
}
