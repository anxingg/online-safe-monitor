package cn.com.qytx.hotline.phonetask.service;

import cn.com.qytx.hotline.phonetask.domain.PhoneTask;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 
 * 功能:外呼记录任务 版本: 1.0 开发人员: 徐长江 创建日期: 2014-2-12 修改日期: 2014-2-12 修改列表:
 */
public interface IPhoneTask extends BaseService<PhoneTask> {
	/**
	 * 
	 * 功能：任务的保存
	 * 
	 * @param phoneTask
	 */
	void saveOrUpdatePhoneTask(PhoneTask phoneTask, UserInfo userInfo);

	/**
	 * 
	 * 功能：是否开始
	 * 
	 * @param state
	 *            1 开始 2 暂停 3 重新开始 4 结束
	 */
	void updatePhoneTaskStast(Integer vid, int state);

	/**
	 * 
	 * 功能：更改任务的成功或者失败数量
	 * 
	 * @param state
	 *            1 回访成功 2 回访失败 3 重复回访成功 4 重复回访失败
	 */
	void updatePhoneTaskCount(Integer vid, int state);

	/**
	 * 
	 * 功能：得到任务对象
	 * 
	 * @param vid
	 * @return
	 */
	PhoneTask getPhoneTaskByVid(Integer vid);

	/**
	 * 
	 * 功能：删除任务
	 * 
	 * @param vid
	 * @return
	 */
	void delPhoneTaskByVid(Integer vid);

	/**
	 * 
	 * 功能：根据任务的属性获取任务列表
	 * 
	 * @param page
	 * @param seatId
	 *            坐席id 0所有的 》0与自己相关的
	 * @param vo
	 * @return
	 */
	Page<PhoneTask> findPageByVo(Pageable page, int seatId, PhoneTask vo);

}
