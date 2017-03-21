package cn.com.qytx.hotline.customercall.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.cbb.util.StringUtil;
import cn.com.qytx.hotline.crm.dao.CRMDao;
import cn.com.qytx.hotline.crm.domaim.CRM;
import cn.com.qytx.hotline.customercall.dao.CclMsiLogDao;
import cn.com.qytx.hotline.customercall.dao.CustomerCallDealHistoryDao;
import cn.com.qytx.hotline.customercall.dao.CustomerCallLogDao;
import cn.com.qytx.hotline.customercall.domain.CclMsiLog;
import cn.com.qytx.hotline.customercall.domain.CustomerCallDealHistory;
import cn.com.qytx.hotline.customercall.domain.CustomerCallLog;
import cn.com.qytx.hotline.customercall.service.ICustomerCallLog;
import cn.com.qytx.hotline.ivr.dao.MsicallLogDao;
import cn.com.qytx.hotline.ivr.domain.CallCenterConst;
import cn.com.qytx.hotline.ivr.domain.MsicallLog;
import cn.com.qytx.hotline.messagemanagement.domain.VoxMail;
import cn.com.qytx.hotline.messagemanagement.service.IVoxMail;
import cn.com.qytx.hotline.util.UpdateFieldUtil;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.application.TransportUser;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.org.dao.GroupDao;
import cn.com.qytx.platform.org.dao.UserDao;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.utils.datetime.DateTimeUtil;
import cn.com.qytx.workflow.domain.HotProcessAttribute;
import cn.com.qytx.workflow.service.ProcessAttributeService;
import cn.com.qytx.workflow.service.WorkflowService;
/**
 * 功能: 工单服务Impl
 * 版本: 1.0
 * 开发人员: 李华伟
 * 创建日期: 2013-9-12
 * 修改日期: 2013-9-12 修改列表:
 */
@Service
@Transactional
public class CustomerCallLogImpl extends BaseServiceImpl<CustomerCallLog> implements ICustomerCallLog,Serializable
{
	private static final long serialVersionUID = 1L;
	private static Map<String,String> cclSnMap = new HashMap<String,String>();
	/**
     * 日志
     */
	private final static MonitorLogger LOGGER =new Log4jImpl(CustomerCallLogImpl.class);
//    private final static Logger LOGGER = Logger.getLogger(CustomerCallLogImpl.class);
    /**
     * 工单服务类
     */
    @Autowired
    private CustomerCallLogDao customerCallLogDao;
   /**
     * 工单服务类
    */
    @Autowired
    private CustomerCallDealHistoryDao customerCallDealHistoryDao;
	@Autowired
	private transient IVoxMail voxMailService;
    @Autowired
    private MsicallLogDao msiCallLogDao;
    /**
    * 部门,群组管理接口
     */
    @SuppressWarnings("rawtypes")
	@Autowired
    GroupDao groupInfoVOImpl;
    /**
     * 短信服务service
    */
   @SuppressWarnings("rawtypes")
   @Autowired
   private UserDao userDao;
    /**
     * 话单与工单关联Dao
     */
    @Autowired
    private CclMsiLogDao cclMsiLogDao;
    /**
     * 工作流接口
     */
    @Autowired
    private transient WorkflowService workflowService;
    @Resource(name="hotprocessAttributeService")
    private transient ProcessAttributeService processAttributeService;
    @Autowired
	private CRMDao crmdao;
    static{
    	cclSnMap.put("01", "");
    	cclSnMap.put("02", "");
    	cclSnMap.put("03", "");
    	cclSnMap.put("04", "");
    	cclSnMap.put("05", "");
    	cclSnMap.put("06", "");
    	cclSnMap.put("07", "");
    	cclSnMap.put("08", "");
    	cclSnMap.put("09", "");
    	cclSnMap.put("10", "");
    	cclSnMap.put("11", "");
    	cclSnMap.put("12", "");
    	cclSnMap.put("13", "");
    	cclSnMap.put("14", "");
    	cclSnMap.put("15", "");
    	cclSnMap.put("16", "");
    	cclSnMap.put("17", "");
    	cclSnMap.put("18", "");
    }
   /**
    * 保存CustomerCallLog
    */
   public synchronized void save(CustomerCallLog ccl)
   {
       Timestamp now = new Timestamp(System.currentTimeMillis());
       ccl.setRecordTime(now);
       ccl.setLastUpdateTime(now);
       ccl.setLastUpdateUser(ccl.getRecordUser());
       ccl.setIsDelete(0);
       if (ccl.getState() == null)
       {
           ccl.setState(CallCenterConst.STATE_ACCEPTED);
           ccl.setTimeLimit(CallCenterConst.MAX_LIMIT_TIME);
       }
       // 获取工单编号, 并保存工单信息
       UserInfo user = ccl.getRecordUser();
       ccl.setCclSn(getCclSn(user));
       customerCallLogDao.saveOrUpdate(ccl);
       LOGGER.info("创建工单"+ccl);
       // 同时保存话单和工单对应关系
       String msiSessionId = ccl.getMsiSessionId();
       Integer msiUserId = ccl.getMsiUserId();
       if (!StringUtils.isEmpty(msiSessionId) && null != msiUserId)
       {
           CclMsiLog cclMsiLog = new CclMsiLog();
           cclMsiLog.setCclId(ccl.getVid());
           cclMsiLog.setCompanyId(user.getCompanyId());
           cclMsiLog.setMsiSessionId(msiSessionId);
           cclMsiLog.setMsiUserId(msiUserId);
           MsicallLog msicallLog = msiCallLogDao.findByCallIdUid(msiSessionId, msiUserId);
           if (null != msicallLog)
           {
               cclMsiLog.setMsiLogId(msicallLog.getVid());
           }
           cclMsiLogDao.saveOrUpdate(cclMsiLog);
           LOGGER.info(user.getUserName()+"保存工单和话单记录"+cclMsiLog);
       }
   }

   private void saveCCH(CustomerCallLog ccl)
   {
       CustomerCallDealHistory cch = new CustomerCallDealHistory();
       cch.setCustomerCallLog(ccl);
       // 设置记录人及记录时间
       cch.setRecordTime(ccl.getRecordTime());
       cch.setRecordUser(ccl.getRecordUser());
       cch.setCompanyId(ccl.getRecordUser().getCompanyId());
       cch.setDealInfo(ccl.getReplyInfo());
       customerCallDealHistoryDao.saveOrUpdate(cch);
       LOGGER.info(ccl.getRecordUser()+"保存工单处理历史记录"+cch);
   }
   /**
    * 功能：更新工单,包含咨询单的转移,办结;投诉单的办结;维修工单的派修,重新派修,维修结果录入,回访办结等
    * @param vo
    *            CustomerCallLog
    * @param u
    *            UserInfo
    */
   public int updateCCL(CustomerCallLog vo, UserInfo u)
   {
       // 根据流程步骤 确认处理逻辑
       Integer updateState = vo.getUpdateStateVo();
       // 获取老工单信息
       CustomerCallLog ccl = customerCallLogDao.findOne(vo.getVid());
       Integer oldState = ccl.getState();
       Timestamp now = new Timestamp(System.currentTimeMillis());
       ccl.setLastUpdateTime(now);
       ccl.setLastUpdateUser(u);
       CustomerCallDealHistory cch = new CustomerCallDealHistory();
       cch.setCompanyId(u.getCompanyId());
       cch.setRecordUser(u);
       cch.setRecordTime(now);
       cch.setCustomerCallLog(ccl);
       switch (updateState)
       {
       // 转坐席回访
       case CallCenterConst.DEAL_STATE_REVISIT:
           // 判断该工单是否还能办理, 完结状态不能再办理？？？
           // if (oldState != CallCenterConst.STATE_ACCEPTED)
           if (oldState == CallCenterConst.STATE_FINISH)
           {
               LOGGER.info("updateCCL transfer error : ccl sn = " + ccl.getCclSn());
               return 1;
           }
           // 设置需要更新的字段
           UpdateFieldUtil.updateObjFields(vo, ccl, "replyInfo");
           cch.setAction(CallCenterConst.DEAL_STATE_REVISIT);
           cch.setDealInfo(ccl.getReplyInfo());
           // 更新工单
           ccl.setState(CallCenterConst.STATE_TRANSFER);
           customerCallLogDao.saveOrUpdate(ccl);
           LOGGER.info("工单处理转坐席回访"+ccl);
           break;
       // 转后台处理 3
       case CallCenterConst.DEAL_STATE_HANDLE:
           // 判断该工单是否还能办理
           if (oldState != CallCenterConst.STATE_TRANSFER)
           {
               LOGGER.info("updateCCL handle finish error : ccl sn = " + ccl.getCclSn());
               return 1;
           }
           cch.setAction(CallCenterConst.DEAL_STATE_HANDLE);
           cch.setDealInfo(vo.getReplyInfo());
           // 更新工单状态 更新为 待办结
           ccl.setState(CallCenterConst.STATE_REVISIT);
           // 更新时间
           customerCallLogDao.saveOrUpdate(ccl);
           LOGGER.info("工单处理 转后台处理 3"+ccl);
           break;
       // 工单办结
       case CallCenterConst.DEAL_STATE_FINISH:
           // 更新结果信息
           ccl.setReplyInfo(vo.getReplyInfo());
           cch.setAction(CallCenterConst.DEAL_STATE_FINISH);
           cch.setDealInfo(ccl.getReplyInfo());
           // 更新工单状态
           ccl.setState(CallCenterConst.STATE_FINISH);
           ccl.setTimeLimit(CallCenterConst.MAX_LIMIT_TIME + 1);
           customerCallLogDao.saveOrUpdate(ccl);
           LOGGER.info("工单处理工单办结"+ccl);
           break;
       default:
           break;
       }
       // 是否保存处理历史记录
       if (0 != cch.getAction())
       {
           customerCallDealHistoryDao.saveOrUpdate(cch);
           LOGGER.info(u.getUserName()+"工单处理历史"+cch);
       }
       return 0;
   }
   /**
    * 功能：根据过滤条件分页获取工单信息
    * @param page
    *            分页信息
    * @param vo
    *            查询条件
    * @param u
    *            当前用户
    * @return Page<CustomerCallLog>
    */
   public Page<Object[]> findCCLByPage(Pageable page, CustomerCallLog vo,
           UserInfo u,String iscomplete,String fromPage,UserInfo userInfo)
   {
       return customerCallLogDao.findCCLByPage(page, vo, u, iscomplete,fromPage,userInfo);
   }
   public int listCount(CustomerCallLog vo,
   		UserInfo u,String iscomplete,String fromPage){
	   return customerCallLogDao.listCount(vo, u, iscomplete, fromPage);
   }
   @Override
   public CustomerCallLog findOne(Integer vid)
   {
       CustomerCallLog ccl = customerCallLogDao.findOne(vid);
       // 查询关联话单情况
       ccl.setMsicallLogList(msiCallLogDao.findAllByCclId(vid));
       return ccl;
   }
   // 获取工单编号
private String getCclSn(UserInfo user)
   {
	   /* 2014年12月25日 李立泼修改： 工单编号调用存储过程读tb_ccl_sn_lock 这张表中某个区域中某天的最大工单编号，并生成新的工单编号。上面的cclSnMap不有用。 */
	   List<Object> params = new ArrayList<Object>();
	   params.add(user.getUserId());
	   params.add( DateTimeUtil.timestampToString( new Timestamp(System.currentTimeMillis()), "yyyyMMdd") );
	   return customerCallLogDao.myExecuteProcedure( "sq_generateCCLSN", params);
   }
   /**
    * 功能：保存工单与通话记录中间表
    * @param ccl
    */
   public void saveCclMsiLog(CclMsiLog ccl)
   {
       if (null != ccl)
       {
           // 工单与话单关联表维护
           MsicallLog msicallLog = msiCallLogDao.findByCallIdUid(ccl.getMsiSessionId(),
                   ccl.getMsiUserId());
           if (null != msicallLog)
           {
               ccl.setMsiLogId(msicallLog.getVid());
           }
           cclMsiLogDao.saveOrUpdate(ccl);
           LOGGER.info(ccl.getMsiUserId()+"工单与通话记录"+ccl);
       }
   }

   /**
    * 功能：根据电话号码查询工单
    * @param vid
    */
   public CustomerCallLog getCustomerCallLogByPhone(String phone)
   {
       return customerCallLogDao.getCustomerCallLogByPhone(phone);
   }

   /**
    * 功能：工单的删除
    * @param vid
    */
   public void deleteCustomerCallLog(Integer vid)
   {
      customerCallLogDao.delete(vid, false);
      UserInfo  user=(UserInfo) ServletActionContext.getRequest().getSession().getAttribute("adminUser");
      LOGGER.info(user.getUserName()+"工单删除 vid="+vid);
      //删除流程实例
       CustomerCallLog ccl = customerCallLogDao.findOne(vid);
       if(ccl!=null&&ccl.getAccessSourceId()!=null){
    	   VoxMail voxM=voxMailService.findOne(ccl.getAccessSourceId());
    	   voxM.setState(3);
    	   voxMailService.saveOrUpdate(voxM);
       }
       //只删除这一个工单 实例
       if(ccl!=null){
    	   workflowService.deleteProcessInstanceById(ccl.getWorkflowId());
       }
   }
   
   /**
    * 保存CustomerCallLog
    */
   @Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED)
   public synchronized String save(CustomerCallLog ccl, String workFlowName, int processId,String nextAction,String nextUserId,UserInfo userInfo)
   {
	   HotProcessAttribute hpa = processAttributeService.findOne(processId);
	   String instanceId = ccl.getWorkflowId();
	   //新增
	   if(instanceId == null){
		   //暂存
		   if(nextAction.equals("-1")){
			   instanceId = workflowService.startProcessInstanceOnly(hpa.getProcessDefineId());
		   }else{
			   instanceId =workflowService.startProcessInstance(hpa.getProcessDefineId(), nextAction,nextUserId);
		   }
	       if(instanceId == null){
	       	//没有启用流程
	       	return "unsaved";
	       }
	       ccl.setWorkflowId(instanceId);
	   }else{
		   //暂存
		   if(!nextAction.equals("-1")){
			   String taskId = workflowService.getTaskIdByInstanceId(instanceId, TransportUser.get().getUserId().toString());
			   workflowService.takeAndcompleteTask(taskId, nextAction,nextUserId,null);
		   }
	   }
       Timestamp now = new Timestamp(System.currentTimeMillis());
       ccl.setRecordTime(now);
       ccl.setLastUpdateTime(now);
       ccl.setLastUpdateUser(ccl.getRecordUser());
       ccl.setIsDelete(0);
       // 获取工单编号, 并保存工单信息
       if(StringUtil.isEmpty(ccl.getCclSn())){
    	   UserInfo user = ccl.getRecordUser();
    	   ccl.setCclSn(getCclSn(user));
       }
       //保存这条工单的CRM信息
       ccl.setCrm(this.getCrm(ccl,userInfo));
       customerCallLogDao.saveOrUpdate(ccl);
       //暂存
       if(nextAction.equals("-1")){
           LOGGER.info("暂存工单"+ccl);
       }else{
           LOGGER.info("保存工单"+ccl);     
       }
      
       // 保存工单处理记录
       saveCCH(ccl);
       // 同时保存话单和工单对应关系
       String msiSessionId = ccl.getMsiSessionId();
       Integer msiUserId = ccl.getMsiUserId();
       if (!StringUtils.isEmpty(msiSessionId) && null != msiUserId)
       {
           CclMsiLog cclMsiLog = new CclMsiLog();
           cclMsiLog.setCclId(ccl.getVid());
           cclMsiLog.setMsiSessionId(msiSessionId);
           cclMsiLog.setMsiUserId(msiUserId);
           cclMsiLog.setIsForkGroup(ccl.getIsForkGroup());
           if(userInfo.getCompanyId()!=null){
        	   cclMsiLog.setCompanyId(userInfo.getCompanyId());
           }
           MsicallLog msicallLog = msiCallLogDao.findByCallIdUid(msiSessionId, msiUserId);
           if (null != msicallLog)
           {
               cclMsiLog.setMsiLogId(msicallLog.getVid());
           }
          cclMsiLogDao.saveOrUpdate(cclMsiLog);
          LOGGER.info( msiUserId+"操作日志功能"+cclMsiLog);
       }
       return "saved";
   }
   /**
    * 获取crm
    * 功能：
    * @return
    */
   private CRM  getCrm(CustomerCallLog ccl,UserInfo userInfo){
		CRM crmf =ccl.getCrm();
		crmf.setCompanyId(userInfo.getCompanyId());
		//现根据手机号来判断是否已经存在
		//客户手机号
		String mobile = ccl.getPhone();
		//客户座机号
		String backPhone=ccl.getTelephone();
		//客户姓名
		String name = crmf.getName();
		CRM crm =null;
		if(mobile!=null&&!"".equals(mobile)){
//			crm = crmdao.findCRMByMobile(mobile);
			crm = crmdao.findByMobileAndName(mobile, name);
		}
		if(crm==null&&backPhone!=null&&!"".equals(backPhone)){
			//如果按手机号查找为空则按照座机号查询	 
//			crm	 =crmdao.findCRMByTelephone(backPhone);
			crm	 =crmdao.findByBackPhoneAndName(backPhone, name);
		 }
			if (crm != null) {
				// 如果用户信息存在则在原来用户信息的基础上更新
				getCrmNew(ccl, crmf, crm);
			} else {
				// 如果用户信息不存在则新增
				CRM crmNew = new CRM();
				// 存入客户地址
				if(ccl.getAddress()!=null){
					crmNew.setAddress(ccl.getAddress());
				}
				
				// 存入性别
				if (ccl.getSex() != null) {
					crmNew.setGender(ccl.getSex());
				}
//				crmNew.setIsDelete(0);
				// 存入手机号
				if (ccl.getPhone() != null) {
					crmNew.setMobile(ccl.getPhone());
				}
				// 存入座机号 userInfo
				if(ccl.getTelephone()!=null){
					crmNew.setBackPhone(ccl.getTelephone());
				}
				// 存入姓名
				if(ccl.getName()!=null){
					crmNew.setName(ccl.getName());
				}
				if(crmf.getCardId()!=null){
					crmNew.setCardId(crmf.getCardId());
				}
				if(crmf.getPersonType()>0){
					crmNew.setPersonType(crmf.getPersonType());
				}
				if(crmf.getLinkedId()!=null&&crmf.getLinkedId()>0){
				    crmNew.setLinkedId(crmf.getLinkedId());
				}
				if(crmf.getHkAddress()!=null){
					crmNew.setHkAddress(crmf.getHkAddress());
				}
				if(crmf.getCompany()!=null){
					crmNew.setCompany(crmf.getCompany());
				}
				if(crmf.getJob()!=null){
					crmNew.setJob(crmf.getJob());
				}
				if(crmf.getReceiveMoney()!=null){
					crmNew.setReceiveMoney(crmf.getReceiveMoney());
				}
				if(crmf.getAge()!=null){
					crmNew.setAge(crmf.getAge());
				}
				if(crmf.getNote()!=null){
					crmNew.setNote(crmf.getNote());
				}
				if(userInfo.getCompanyId()!=null){
					crmNew.setCompanyId(userInfo.getCompanyId());
				}
				crmNew.setCreateTime(new Timestamp(System.currentTimeMillis()));
				crmNew.setIsDelete(0);
				crmNew.setIsForkGroup(ccl.getIsForkGroup());
				crmdao.saveOrUpdate(crmNew);
				  LOGGER.info( "操作客户档案"+crmNew);
                crm=crmNew;
			}
			return crm;
	}

private void getCrmNew(CustomerCallLog ccl, CRM crmf, CRM crm) {
	CRM crmNew = new CRM();
	crmNew.setVid(crm.getVid());
	// 存入地址
	if(ccl.getAddress()!=null&&!"".equals(ccl.getAddress())){
		crmNew.setAddress(ccl.getAddress());
	}
	// 存入性别
	if (ccl.getSex() != null ) {
		crmNew.setGender(ccl.getSex());
	}
	// 存入座机号 userInfo
	// 存入姓名
	if(ccl.getName()!=null&&!"".equals(ccl.getName())){
		crmNew.setName(ccl.getName());
	}
	//存入联系电话
	if(ccl.getPhone()!=null &&!"".equals(ccl.getPhone())){
		crmNew.setMobile(ccl.getPhone());
	}
	//存入备用电话
	if(ccl.getTelephone()!=null&&!"".equals(ccl.getTelephone())){
		crmNew.setBackPhone(ccl.getTelephone());
	}
	 System.out.println(crmf);
	if(crmf.getCardId()!=null &&!"".equals(crmf.getCardId())){
		crmNew.setCardId(crmf.getCardId());
	}

        if(crmf.getLinkedId()!=null&&crmf.getLinkedId()>0){
            crmNew.setLinkedId(crmf.getLinkedId());
        }
   
  
	if(crmf.getPersonType()!=0){
		crmNew.setPersonType(crmf.getPersonType());
	}
	if(crmf.getAge()!=null){
		crmNew.setAge(crmf.getAge());
	}
	if(crmf.getHkAddress()!=null&&!"".equals(crmf.getHkAddress())){
		crmNew.setHkAddress(crmf.getHkAddress());
	}
	if(crmf.getCompany()!=null &&!"".equals(crmf.getCompany())){
		crmNew.setCompany(crmf.getCompany());
	}
	if(crmf.getJob()!=null&&!"".equals(crmf.getJob())){
		crmNew.setJob(crmf.getJob());
	}
	if(crmf.getReceiveMoney()!=null){
		crmNew.setReceiveMoney(crmf.getReceiveMoney());
	}
	if(crmf.getNote()!=null&&!"".equals(crmf.getNote())){
		crmNew.setNote(crmf.getNote());
	}
	crmNew.setCompanyId(crmf.getCompanyId());
	crmNew.setCreateTime(new Timestamp(System.currentTimeMillis()));
	crmNew.setIsDelete(0);
	crmNew.setIsForkGroup(ccl.getIsForkGroup());
	
        crmdao.saveOrUpdate(crmNew);
 
	  LOGGER.info( "操作客户档案"+crmNew);
}
   /**
    * 功能：工单流转
    * @param vo 工单信息 工单变更字段
    * @param modifyFields
    * @param u 当前处理用户
    * @param taskId 任务Id 当前任务Id
    * @param nextNodeKey 跳转节点
    * @param dealHistory 处理历史信息
    * @return int 0表示成功 1表示失败
    */
   public int updateCCL(CustomerCallLog vo, String instanceId,String nextAction,String nextUserId,String comment){
	   String taskId = workflowService.getTaskIdByInstanceId(instanceId,TransportUser.get().getUserId().toString());
       boolean result = workflowService.takeAndcompleteTask(taskId, nextAction,nextUserId,comment);
       if (result)
       {
           // 获取老工单信息
           CustomerCallLog ccl = customerCallLogDao.findOne(vo.getVid());            
           Timestamp now = new Timestamp(System.currentTimeMillis());
           ccl.setLastUpdateTime(now);
           ccl.setLastUpdateUser(TransportUser.get());
           customerCallLogDao.saveOrUpdate(ccl);
           LOGGER.info( "操作处理工单"+ccl);
           CustomerCallDealHistory cch = new CustomerCallDealHistory();
           cch.setRecordUser(TransportUser.get());
           cch.setCompanyId(TransportUser.get().getCompanyId());
           cch.setRecordTime(now);
           cch.setDealInfo(ccl.getReplyInfo());
           if(ccl.getCallBackUserId()!=null&&userDao.getUserById(ccl.getCallBackUserId())!=null){
        	   cch.setNextUser(userDao.getUserById(ccl.getCallBackUserId()));
           }
           cch.setCustomerCallLog(ccl);
           customerCallDealHistoryDao.saveOrUpdate(cch);
           LOGGER.info(ccl.getRecordUser().getUserName()+"操作工单历史记录"+cch);
           return 0;
       }
       else
       {
           return 1;
       }
   }
   /**
    * 功能：根据工作流ID删除工单
    * @param workflowId 工作流Id
    * @return boolean
    */
   public boolean deleteCCLByWorkflowId(String workflowId,Integer state)
   {
       UserInfo  user=(UserInfo) ServletActionContext.getRequest().getSession().getAttribute("adminUser");
       LOGGER.info(user.getUserName()+"删除工作流="+workflowId);
       return workflowService.deleteProcessInstanceById(workflowId);
 
   }
   /**
    * 功能：根据工作流实例Id获取封装好的业务信息
    * @param workflowId 实例Id
    * @return CustomerCallLog
    */
   public CustomerCallLog findCCLByWorkflowId(String workflowId)
   {
	   return customerCallLogDao.findByInstanceId(workflowId);
   }

	public CustomerCallLogDao getCustomerCallLogDao() {
		return customerCallLogDao;
	}

	public void setCustomerCallLogDao(CustomerCallLogDao customerCallLogDao) {
		this.customerCallLogDao = customerCallLogDao;
	}
	public CustomerCallDealHistoryDao getCustomerCallDealHistoryDao() {
		return customerCallDealHistoryDao;
	}
	public void setCustomerCallDealHistoryDao(
			CustomerCallDealHistoryDao customerCallDealHistoryDao) {
		this.customerCallDealHistoryDao = customerCallDealHistoryDao;
	}
	public MsicallLogDao getMsiCallLogDao() {
		return msiCallLogDao;
	}
	public void setMsiCallLogDao(MsicallLogDao msiCallLogDao) {
		this.msiCallLogDao = msiCallLogDao;
	}
	public CclMsiLogDao getCclMsiLogDao() {
		return cclMsiLogDao;
	}
	public void setCclMsiLogDao(CclMsiLogDao cclMsiLogDao) {
		this.cclMsiLogDao = cclMsiLogDao;
	}
	public CRMDao getCrmdao() {
		return crmdao;
	}
	public void setCrmdao(CRMDao crmdao) {
		this.crmdao = crmdao;
	}
	@Override
	public CustomerCallLog findByAccessSourceid(Integer accessSourceId,
			Integer accessType) {
		return customerCallLogDao.findByAccessSourceid(accessSourceId, accessType);
	}
	@Override
	public String getMaxCclSnForArea(String groupArea) {
		
		return customerCallLogDao.getMaxCclSnForArea(groupArea);
	}
}
