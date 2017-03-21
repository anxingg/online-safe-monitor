package cn.com.qytx.hotline.phonetask.dao;

import org.springframework.stereotype.Repository;

import cn.com.qytx.hotline.phonetask.domain.PhoneTaskUser;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 
 * 功能:用户任务dao 
 * 版本: 1.0 
 * 开发人员: 彭晓东 
 * 创建日期: 2014-2-12 
 * 修改日期: 2014-2-12 
 * 修改列表:
 */

@Repository("phoneTaskUserDao")
public class PhoneTaskUserDao extends BaseDao<PhoneTaskUser, Integer> {

	/**
	 * 根据主键查询（没有数据权限控制）
	 * @param vid
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public PhoneTaskUser findById(Integer vid) {
		String hql = "from PhoneTaskUser where vid=?";
		return this.findUnique(hql, vid);
	}
	
	/**
	 * 功能：获取电话任务用户
	 * @param vid  任务id
	 * @param mobile 手机号码
	 * @param name  用户名称
	 * @return
	 */
	public PhoneTaskUser findPhoneTaskUser(Integer vid, String mobile,
			String name) {
		String hql = " taskId.vid=? and phone=? and userName=?";
		return this.findOne(hql, vid, mobile, name);
	}

	/**
	 * 功能： 更新外呼任务用户的外呼任务
	 * 
	 * @param taskUserId
	 * @param vid
	 */
	public void update(String taskUserId, Integer vid, Integer companyId) {
		String hql = "update PhoneTaskUser set  taskId.vid=?1, companyId = ?2 where vid=?3 ";
		super.executeQuery(hql, vid, companyId,Integer.valueOf(taskUserId));
	}

	/**
	 * 功能：获取被自己占用回访的一个外呼用户对象
	 * 
	 * @param phoneTaskid  外呼任务id
	 * @param inquirerUser  回访人id
	 */
	public PhoneTaskUser findFirst(UserInfo inquirerUser, Integer phoneTaskid) {
		String hql = " taskId.vid=? and vid =(select qu.surveyUser.vid from  QuestionnaireUser qu  where qu.taskId.vid=? and statue=10 and qu.inquirerUser.userId=? )";
		PhoneTaskUser pt = findOne(hql, phoneTaskid, phoneTaskid,inquirerUser.getUserId());
		return pt;
	}

	/**
	 * 功能：获取一个未被占用或者占用时间超过10分钟的一个外呼用户对象
	 * 
	 * @param phoneTaskid
	 * @param phoneUserid
	 */
	public PhoneTaskUser findFirstPhoneTask(UserInfo inquirerUser,
			Integer phoneTaskid) {
		 //String hql="taskId.vid=? and surveyUser.id not in(select qu.id from  QuestionnaireUser qu  where   qu.taskId.vid=? )";
		 String hql="taskId.vid=? and surveyUser.id  is null";
			PhoneTaskUser pt = super.findOne(hql, phoneTaskid);
			if(pt==null){
				hql = " taskId.vid=? and surveyUser.id in(select qu.id from  QuestionnaireUser qu  where   qu.taskId.vid=? and statue=10 and DateDiff(s,qu.createTime, GetDate())>=600 ) ";
				pt = super.findOne(hql, phoneTaskid, phoneTaskid);
			}

	/*	
	 访问中的  10分以后才可以获取
	 if(pt==null){
			 hql = " taskId.vid=? and vid in(select qu.surveyUser.vid from  QuestionnaireUser qu  where qu.taskId.vid=? and statue=10 ) )";
			 pt = super.findOne(hql, phoneTaskid, phoneTaskid);
		}*/
		return pt;
	}

}
