/**
 * 
 */
package cn.com.qytx.hotline.phonetask.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.crm.domaim.CRM;
import cn.com.qytx.hotline.crm.service.ICRM;
import cn.com.qytx.hotline.phonetask.dao.PhoneTaskDao;
import cn.com.qytx.hotline.phonetask.domain.PhoneTask;
import cn.com.qytx.hotline.phonetask.domain.Questionnaire;
import cn.com.qytx.hotline.phonetask.service.IPhoneTask;
import cn.com.qytx.hotline.phonetask.service.IPhoneTaskUser;
import cn.com.qytx.hotline.phonetask.service.IQuestionnaireService;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 功能: 电话任务业务处理类
 * 版本: 1.0 
 * 开发人员: 彭小东 
 * 创建日期: 2014-9-16
 * 修改日期: 2014-9-16 
 * 修改列表:
 */
@Service
@Transactional
public class PhoneTaskImpl extends BaseServiceImpl<PhoneTask> implements
		IPhoneTask {

    /**
     * 电话任务dao
     */
	@Autowired
	private PhoneTaskDao taskDao;
	/**
	 * 试卷业务处理接口
	 */
	@Autowired
	private IQuestionnaireService questionnaire;
	/**
	 * 用户档案业务处理接口
	 */
	@Autowired
	private ICRM crmservice;
	/**
	 * 外呼任务用户接口
	 */
	@Resource(name = "phoneTaskUser")
	private IPhoneTaskUser phoneTaskUser;

	/**
	 * 功能：保存和修改任务
	 * @param phoneTask
	 */
	@Override
	public void saveOrUpdatePhoneTask(PhoneTask phoneTask, UserInfo userInfo) {
		boolean flage = false;// 是否是修改
		Questionnaire qaire = null;
		if (phoneTask.getVid() != null && phoneTask.getVid() > 0) {//如果是修改
			flage = true;
			qaire = taskDao.findOne(phoneTask.getVid()).getQuestionnaire();
		} else {//新增
			phoneTask.setTaskCallCount(0);
			phoneTask.setTaskSuccessCount(0);
			phoneTask.setTaskFailureCount(0);
		}
		
		//新增外呼任务页面 中导入的外呼对象
		//	其中：callUserIds导入的外呼对象
		String callUserIds = phoneTask.getCallUserIds();
		//	crmUserIds是从读取的ＣＲＭ的外呼对象（不是导入的）
		String crmUserIds = phoneTask.getCrmUserIds();
		String[] crmUsers = crmUserIds.split(",");
		String[] callUsers = callUserIds.split(",");
		// 生成试卷
		if (qaire != null) {
			qaire.setTitle(phoneTask.getTaskName());
			qaire.setDes(phoneTask.getTaskExplain());
			qaire.setState(1);
			questionnaire.update(qaire, qaire.getId());
		} else {
			qaire = new Questionnaire();
			qaire.setTitle(phoneTask.getTaskName());
			qaire.setDes(phoneTask.getTaskExplain());
			questionnaire.saveOrUpdate(qaire);

		}
		if(userInfo!=null && userInfo.getCompanyId() != null){
			qaire.setCompanyId(userInfo.getCompanyId());
		}
		phoneTask.setQuestionnaire(qaire);
		taskDao.saveOrUpdate(phoneTask);
		// 修改任务
		if (flage) {
			// 根据外呼任务id 清空外呼任务列表
			phoneTaskUser.clearTaskUser(phoneTask.getVid());
			// 更新外呼任务id
			String phoneUserIds = phoneTask.getPhoneUserIds();
			String[] phoneusers = phoneUserIds.split(",");
			if (phoneusers.length > 0) {
				phoneTaskUser.updateUsers(phoneUserIds, phoneTask.getVid(), phoneTask.getCompanyId());
			}
			addCrmUser(crmUsers,phoneTask);
			// 根据导入的外呼用户
			addImportUser(callUsers,phoneTask);
		} else {
			// 添加任务
			addCrmUser(crmUsers,phoneTask);
			addImportUser(callUsers,phoneTask);

		}
	}
	
	/**
	 * 保存crm的用户
	 * @param crmUsers
	 * @param phoneTask
	 */
	public void addCrmUser(String[] crmUsers,PhoneTask phoneTask){
		// 保存crm的用户
		if (crmUsers.length > 0) {
			for (String crmu : crmUsers) {
				if (!"".equals(crmu)) {
					CRM crm = crmservice.findOne(Integer.valueOf(crmu));
					phoneTaskUser.add(crm, phoneTask);
				}
			}
		}
	}
	/**
	 * 根据导入的外呼用户
	 * @param callUsers
	 * @param phoneTask
	 */
	public void addImportUser(String[] callUsers,PhoneTask phoneTask){
		if (callUsers.length > 0) {
			for (String call : callUsers) {
				if (!"".equals(call)) {
					phoneTaskUser.update(call, phoneTask);
				}
			}
		}
	}

	/**
	 * 功能：根据任务id获取任务
	 * 
	 * @param vid
	 * @return
	 */
	@Override
	public PhoneTask getPhoneTaskByVid(Integer vid) {
		return taskDao.findOne(vid);
	}

	/**
	 * 功能：删除任务
	 * 
	 * @param vid
	 */
	@Override
	public void delPhoneTaskByVid(Integer vid) {
		taskDao.delete(vid, false);
	}

	/**
	 * 功能:分页查询外呼任务
	 * @param page
	 * @param vo
	 * @return
	 */
	@Override
	public Page<PhoneTask> findPageByVo(Pageable page, int seatId, PhoneTask vo) {

		return taskDao.findPageByVo(page, seatId, vo);
	}

	/**
	 * 功能：更新外呼任务的状态
	 * 
	 * @param state
	 *            1 开始 2 暂停 3 重新开始 4 结束
	 */
	@Override
	public void updatePhoneTaskStast(Integer vid, int state) {
		Boolean flage = false;
		String sb = "";
		List<Object> params = new LinkedList<Object>();
		if (state == 1) {
			flage = true;
			sb = " taskStartTime=? , taskState=3 ";
			params.add(new Date());
		} else if (state == 2) {
			flage = true;
			sb = " taskState=4 ";
		} else if (state == 3) {
			flage = true;
			sb = " taskState=3 ";
		} else if (state == 4) {
			flage = true;
			sb = " taskEndTime=? , taskState=5 ";
			params.add(new Date());
		}
		params.add(vid);
		if (flage) {
			taskDao.updatePhoneTaskByHql(sb, params.toArray());
		}
	}

	/**
	 * 功能：是否回访成功
	 * @param state
	 *  1 回访成功 2 回访失败 3 重呼回访成功 4 重呼回访失败
	 */
	@Override
	public void updatePhoneTaskCount(Integer vid, int state) {
		Boolean flage = false;// 是否要更新数据库
		Boolean updateSuc = false;// 是否已经更新过数据库
		PhoneTask pt = null;
		int taskC = 0;
		int taskt = 0;
		// 当回访任务完成后的操作
		if (state == 1 || state == 2) {
			pt = this.findOne(vid);
			taskC = pt.getTaskCallCount() + 1;
			taskt = pt.getTaskTotal();
			if (taskC == taskt) {
				pt.setTaskEndTime(new Timestamp(System.currentTimeMillis()));
				pt.setTaskState(5);
				pt.setTaskCallCount(pt.getTaskCallCount() + 1);
				if (state == 1) {
					/* 李立泼 2014年10月30日 修改。添加是否为null的判断 。当是null的时候，改为0 。否则，会报空指针。 */
					pt.setTaskSuccessCount(( pt.getTaskSuccessCount() == null ? 0: pt.getTaskSuccessCount() ) + 1);
				} else {
					pt.setTaskFailureCount(pt.getTaskFailureCount() + 1);
				}
				this.saveOrUpdate(pt);
				updateSuc = true;
			}
		}
		// 正常的回访
		if (!updateSuc) {
			pt = this.findOne(vid);
			String sb = "";
			List<Object> params = new LinkedList<Object>();
			Integer taskSuccessCount = pt.getTaskSuccessCount()==null?0:pt.getTaskSuccessCount();
			Integer taskCallCount = pt.getTaskCallCount()==null?0:pt.getTaskCallCount();
			Integer taskFailureCount = pt.getTaskFailureCount()==null?0:pt.getTaskFailureCount();
			if (state == 1) {
				flage = true;
				sb = " taskSuccessCount="+(taskSuccessCount+1)+" , taskCallCount="+(taskCallCount+1)+" ";
			} else if (state == 2) {
				flage = true;
				sb = " taskFailureCount="+(taskFailureCount+1)+" , taskCallCount="+(taskCallCount+1)+" ";
			} else if (state == 3) {
				flage = true;
				sb = " taskSuccessCount="+(taskSuccessCount+1)+" , taskFailureCount="+(taskFailureCount-1)+" ";
			}
			params.add(vid);
			if (flage) {
				taskDao.updatePhoneTaskByHql(sb, params.toArray());
			}
		}

	}

}
