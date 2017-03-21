package cn.com.qytx.hotline.ivr.service;

import java.util.List;

import cn.com.qytx.hotline.ivr.domain.MsiserviceUser;
import cn.com.qytx.platform.base.service.BaseService;


/**
*项目名称：wzerp
*类名称：呼入队列员工
*类描述：
*创建人：WangBin
*创建时间：2012-11-21
*@version
 */
public interface IMsiserviceUser extends BaseService<MsiserviceUser> {

	/**
	 *@Title:根据话务员Id、 队列Id查询记录
	 *@Description:
	 *@param @param parseInt
	 *@param @param id
	 *@param @return
	 *@return  返回类型
	 *@throws
	 */
	List<MsiserviceUser> findListById(int msiuserId, Integer serviceId);

	/**
	 *@Title:根据员工Id查询队列员工记录
	 *@Description:
	 *@param @param vid
	 *@param @return
	 *@return  返回类型
	 *@throws
	 */
	List<MsiserviceUser> findListByMsUserId(Integer vid);
	
	MsiserviceUser findById(Class<MsiserviceUser> arg0, Integer vid);
	
	/**
	 * 
	 * 功能：根据坐席ID删除对应的服务队列中间表
	 * @param workNo
	 */
	void deleteByMsiid(Integer msiid);
}
