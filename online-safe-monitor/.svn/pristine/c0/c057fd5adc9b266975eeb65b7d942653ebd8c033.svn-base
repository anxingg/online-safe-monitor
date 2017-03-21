package cn.com.qytx.hotline.customercall.service;

import cn.com.qytx.hotline.customercall.domain.CclMsiLog;
import cn.com.qytx.hotline.customercall.domain.CustomerCallLog;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 功能: 工单服务service 版本: 1.0 开发人员: 李华伟 创建日期: 2013-9-12 修改日期: 2013-9-12 修改列表:
 */
public interface ICustomerCallLog extends BaseService<CustomerCallLog> {
	/**
	 * 功能：根据过滤条件分页获取工单信息
	 * @param page  分页信息
	 * @param vo    查询条件
	 * @param u 当前用户
	 * @return Page<CustomerCallLog>
	 */
	Page<Object[]> findCCLByPage(Pageable page, CustomerCallLog vo, UserInfo u,String iscomplete, String fromPage,UserInfo userInfo);
	/**
	 * 功能：更新工单,包含咨询单的转移,办结;投诉单的办结;维修工单的派修,重新派修,维修结果录入,回访办结等
	 * @param vo CustomerCallLog
	 * @param u  UserInfo
	 * @return 0表示成功 1表示已处理
	 */
	int updateCCL(CustomerCallLog vo, UserInfo u);
	/**
	 * 功能：保存工单与通话记录中间表 
	 * @param ccl
	 */
	void saveCclMsiLog(CclMsiLog ccl);
	/**
	 * 根据来电号码查询工单
	 * @param phone
	 * @return
	 */	CustomerCallLog getCustomerCallLogByPhone(String phone);

	/**
	 * 功能：工单的删除
	 * @param vid
	 */
	void deleteCustomerCallLog(Integer vid);
	/**
	 * 功能：保存新工单, 并发起工作流转
	 * @param ccl    工单信息
	 * @param workFlowName
	 * @param processId :流程ID
	 * @param nextAction  :下一步操作
	 */
	String save(CustomerCallLog ccl, String workFlowName, int processId,String nextAction, String nextUserId,UserInfo userInfo);
	/**
	 * 功能：工单流转
	 * @param vo  工单信息 工单变更字段
	 * @param instanceId :流程ID
	 * @param nextAction  :下一步流转
	 * @param nextUserId :下一步任务处理人
	 * @param comment  :审批意见
	 */
	int updateCCL(CustomerCallLog vo, String instanceId, String nextAction,String nextUserId, String comment);
	/**
	 * 功能：根据工作流ID删除工单
	 * @param workflowId 工作流Id
	 * @param state 工单状态
	 * @return boolean
	 */
	boolean deleteCCLByWorkflowId(String workflowId, Integer state);
	/**
	 * 功能：根据工作流实例Id获取封装好的业务信息
	 * @param workflowId 实例Id
	 * @return CustomerCallLog
	 */
	CustomerCallLog findCCLByWorkflowId(String workflowId);
	/**
	 * 功能：
	 * @param customerCallLog
	 * @param userInfo
	 * @param object
	 * @param object2
	 */
	int listCount(CustomerCallLog vo, UserInfo u, String iscomplete,String fromPage);
	/**
	 * 功能：根据受理来源ID查询工单对象
	 * @param accessSourceId：受理来源ID
	 * @param accessType：受理类型
	 */
	CustomerCallLog findByAccessSourceid(Integer accessSourceId,Integer accessType);
	/**
	 * 获取各地市工单编号的最大值
	 */
	String getMaxCclSnForArea(String groupArea);
}
