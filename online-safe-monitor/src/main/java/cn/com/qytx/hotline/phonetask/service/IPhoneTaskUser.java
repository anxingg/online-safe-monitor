package cn.com.qytx.hotline.phonetask.service;

import cn.com.qytx.hotline.crm.domaim.CRM;
import cn.com.qytx.hotline.phonetask.domain.PhoneTask;
import cn.com.qytx.hotline.phonetask.domain.PhoneTaskUser;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 
 * 功能:用户的任务接口 版本: 1.0 开发人员: 徐长江 创建日期: 2014-2-12 修改日期: 2014-2-12 修改列表:
 */
public interface IPhoneTaskUser extends BaseService<PhoneTaskUser> {

	/**
	 * 功能：根据crm用户 保存外呼任务用户
	 * 
	 * @param crm
	 */
	void add(CRM crm, PhoneTask phoneTask);

	/**
	 * 功能： 更新外呼任务用户的外呼任务
	 * 
	 * @param taskUserId
	 * @param phoneTask
	 */
	void update(String taskUserId, PhoneTask phoneTask);

	/**
	 * 功能：清空外呼任务
	 * 
	 * @param vid
	 */
	void clearTaskUser(Integer vid);

	/**
	 * 功能：更新外呼任务id
	 * 
	 * @param phoneUserIds
	 * @param vid
	 * @param companyId
	 */
	void updateUsers(String phoneUserIds, Integer vid, Integer companyId);

	/**
	 * 
	 * 功能：获取外呼任务的一个外呼对象
	 * 
	 * @param inquirerUser
	 *            执行坐席
	 * @param phoneTaskid
	 *            外呼任务id
	 * @param phoneUserid
	 *            任务用户id
	 * @param isInit
	 *            是否初始化
	 * @return
	 */
	PhoneTaskUser findFirst(UserInfo inquirerUser, Integer phoneTaskid,
			Integer phoneUserid, boolean isInit);

}
