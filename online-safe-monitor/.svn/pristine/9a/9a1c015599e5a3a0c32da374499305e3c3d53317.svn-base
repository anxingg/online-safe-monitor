package cn.com.qytx.hotline.ivr.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.com.qytx.hotline.ivr.domain.MsiserviceUser;
import cn.com.qytx.platform.base.dao.BaseDao;

/**
 * 项目名称：wzerp 类名称：呼入队列员工 类描述： 创建人：WangBin 创建时间：2012-11-21
 * @version
 */
@SuppressWarnings("serial")
@Component
public class MsiserviceUserDao extends BaseDao<MsiserviceUser, Integer> implements Serializable {

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
		String hql = " from MsiserviceUser m where m.msiid = ? and m.serviceId = ?";
		return this.findAll(hql, new Object[] { msiuserId, serviceId });
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
		String hql = " from MsiserviceUser m where m.msiid = ?";
		return this.findAll(hql, vid);
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
	 * 功能：根据坐席ID删除对应的服务队列中间表
	 * @param workNo
	 */
	public void deleteByMsiid(Integer msiid) {
		String hql = "delete from MsiserviceUser m where m.msiid = ?";
		executeQuery(hql, msiid);
	}

	/**
	 * 功能：根据队列ID删除对应的服务队列中间表
	 * @param msiserviceid
	 */
	public void deleteByMsiserviceid(Integer msiserviceid) {
		String hql = "delete from MsiserviceUser m where m.serviceId = ?";
		executeQuery(hql, msiserviceid);
	}
}
