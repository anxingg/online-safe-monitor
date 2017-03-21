package cn.com.qytx.hotline.customercall.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.dict.domain.Dict;
import cn.com.qytx.cbb.dict.service.IDict;
import cn.com.qytx.cbb.util.StringUtil;
import cn.com.qytx.hotline.crm.domaim.CRM;
import cn.com.qytx.hotline.crm.service.ICRM;
import cn.com.qytx.hotline.crm.service.ICRMView;
import cn.com.qytx.hotline.customercall.domain.CustomerCallLog;
import cn.com.qytx.hotline.customercall.service.ICustomerCallLog;
import cn.com.qytx.hotline.ivr.domain.Msiuser;
import cn.com.qytx.hotline.messagemanagement.domain.VoxMail;
import cn.com.qytx.hotline.messagemanagement.service.IVoxMail;
import cn.com.qytx.hotline.mis.dao.PhoneAttributionDao;
import cn.com.qytx.hotline.mis.domain.PhoneAttribution;
import cn.com.qytx.oa.util.TimeUtil;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.org.domain.RoleInfo;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IRole;
import cn.com.qytx.platform.org.service.IRoleUser;
import cn.com.qytx.platform.org.service.IUser;
import cn.com.qytx.platform.utils.datetime.DateTimeUtil;
import cn.com.qytx.workflow.cfg.WorkflowGlobalCfg;
import cn.com.qytx.workflow.domain.HotNodeFormAttribute;
import cn.com.qytx.workflow.domain.HotWorkflowVar;
import cn.com.qytx.workflow.service.HotWorkflowVarService;
import cn.com.qytx.workflow.service.ProcessDesignerService;
import cn.com.qytx.workflow.service.WorkflowService;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;

/**
 * 功能:工单处理类
 */
public class WorkorderAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;
	/**
	 * 用户信息服务类
	 */
	@Autowired
	private transient IUser userService;
	/**
	 * CRM的服务类
	 */
	@Autowired
	private transient ICRM crmimpl;
	@Autowired
	private transient ICRMView crmViewimpl;
	/**
	 * 角色人员信息
	 */
	@Autowired
	private transient IRoleUser roleUserService;
	/**
	 * 角色服务类
	 */
	@Autowired
	private IRole roleService;
	/**
	 * 数据字典实现类
	 */
	@Autowired
	private IDict dictImpl;
	/**
	 * 工单服务类
	 */
	@Autowired
	private transient ICustomerCallLog customerCallLogService;
	/**
	 * 工作流变量服务类
	 */
	@Resource
	private transient HotWorkflowVarService hotWorkflowVarService;
	private CustomerCallLog customerCallLog;
	private String instanceId;
	private String nextUserId;
	private String comment;
	private final String _phone="phone";
	private final String _receiveMoney="receiveMoney";
	private final String _businessType="businessType";
	private final String _cclSn="cclSn";
	private final String _userName="userName";
	private final String _typeName="typeName";
	private final String _stateStr="stateStr";
	private final String _name="name";
	private final String _typeStr="typeStr";
	private final String _fromPage="fromPage";
	/**
	 * 来电归属地
	 */
	@Autowired
	private PhoneAttributionDao phoneAttributionDao;
	/**
	 * 流程接口
	 */
	@Autowired
	transient private WorkflowService workflowService;
	/**
	 * 留言接口
	 */
	@Autowired
	transient private IVoxMail voxMailService;
	@Resource
	transient private ProcessEngine processEngine;
	@Resource
	transient private ProcessDesignerService processDesignerService;
	private Integer vid;
	// 座机号码,主叫号码
	private String phone;
	// 来电手机号码，被叫号码
	private String callPhone;
	// 判断保存或者已完结
	private Integer checkType;
	private String callTimeString = "";
	// 工单id
	private Integer cclId;
	// 坐席工号
	private String loginName;
	// 工单转入时间
	private String turnTime;
	// 1.回访接通 2.回访没接通
	private Integer chenggong;
	/**
	 * 查询来源 seat坐席 system后台
	 */
	private String fromPage;
	/**
	 * 来电时间
	 */
	private String callTimeStr;
	private Timestamp callTime;

	private String handUpTime;
	// 工单内容
	private String cclContent;
	private String seatWelcome;
	// 前台首页显示几行
	private Integer showRowCount;
	/**
	 * 后台工单处理时的答复内容
	 */
	private String answerContent;
	// 判断是否需要回访
	private Integer checkCallBack;
	// 判断是 转回访还是 办结
	private String forward;
	//被举报坐席
	private UserInfo reportSeat;
	/**
	 * 工作流名称
	 */
	private String workflowName;
	/**
	 * 工作流 待办(candidate) 还是 已办(complete)
	 */
	private String iscomplete;
	/**
	 * 受理来源ID
	 */
	private Integer accessSourceId;
	
	private Integer allList;
	private CRM crm;
	

	/**
	 * 功能：根据用户的来电号码决定跳转到那个来电弹屏页面
	 * 
	 * @return
	 */
	public String getProductByPhoneDecideForward() {
		try {
			UserInfo userInfo = getLoginUser();
			ActionContext ctx = ActionContext.getContext();
			// 获取来电归属地
			PhoneAttribution pb = phoneAttributionDao.findByPhone(phone);
			ctx.put("mobileArea",null==pb?"未知":pb.getMobileArea());// 归属区域
			ctx.put("callTimeString", DateTimeUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));// 来电时间
			ctx.put("turnTime", DateTimeUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));// 转入时间
			ctx.put("loginName", userInfo.getUserName());// 坐席姓名
			ctx.put("cclId", cclId);// 工单id
			WorkorderActionExpand.ctxPutCrm(ctx, phone, crmimpl,crmViewimpl);
			return SUCCESS;
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
		return null;
	}
	/**
	 * 保存工单
	 * 
	 */
	public String saveCustomercallLog() {
			UserInfo userInfo = getLoginUser();
			//update by jiayq，添加修改功能
			CustomerCallLog customerCall = getCustomerCallLog(userInfo);
			// 与坐席关联
			Msiuser user = (Msiuser) this.getSession().getAttribute("seatUser");
			if (user != null) {
				customerCall.setMsiUserId(user.getVid()); // 接听电话的坐席id
			}
			// 判断如果没有接电话 或者
			if(handUpTime!=null&&handUpTime.trim().length()>0){
				customerCall.setDealLength(Integer.valueOf(handUpTime));
				customerCall.setHandupSize(Integer.valueOf(handUpTime));
			}else{
				customerCall.setDealLength(0);
				customerCall.setHandupSize(0);
			}
			customerCall.setCclContent(cclContent);
			CRM crm=customerCall.getCrm();
			if(crm == null){
				crm = customerCallLog.getCrm();
			}else{
				crm=crm.updateByCustomer(customerCallLog.getCrm());
			}
			crm.setIsForkGroup(userInfo.getIsForkGroup());
			customerCall.setCrm(crm);
			String issaved = customerCallLogService.save(customerCall,workflowName, processId, nextAction,nextUserId,userInfo);
			if ("unsaved".equals(issaved)) {
				ajax(0);
				return null;
			}
			// 保存工单的同时存入CRM
			// 保存工单的同时保存一份工单人员信息到CRM中，并与CRM建立起关联
			/**
			 * 更新留言状态改为已处理
			 */
			if(customerCallLog.getAccessType()==3&&accessSourceId!=null){
				VoxMail vm = voxMailService.findOne(accessSourceId);
				vm.setState(2);
				voxMailService.saveOrUpdate(vm);			
			}
		ajax(1);
		return null;
	}

	/**
	 * 功能：得到坐席记录
	 * @param userInfo
	 * @return
	 */
	private CustomerCallLog getCustomerCallLog(UserInfo userInfo) {
		CustomerCallLog customerCall = new CustomerCallLog();
		
		if(!StringUtil.isEmpty(instanceId)){
			customerCall = customerCallLogService.findCCLByWorkflowId(instanceId);
		}
		customerCall.setCompanyId(userInfo.getCompanyId());
		// 来电人姓名
		customerCall.setName(customerCallLog.getName());
		// 来电手机号码，即主叫号码
		customerCall.setPhone(customerCallLog.getPhone());
		//设置等级
		customerCall.setIsForkGroup(userInfo.getIsForkGroup());
		// 来电座机号码，也是可能的主叫号码
		customerCall.setTelephone(customerCallLog.getTelephone());
		// 被叫号码，即坐席端绑定的移动号码
		customerCall.setUserMobile(customerCallLog.getUserMobile());
		// 来电人性别
		customerCall.setSex(customerCallLog.getSex());
		// 地址
		customerCall.setAddress(customerCallLog.getAddress());
        // 年龄
		customerCall.setAge(customerCallLog.getAge()); 
		// 工单内容
		customerCall.setLogInfo(customerCallLog.getCclContent());

		customerCall.setLastUpdateUserId(userInfo.getUserId());
		// 受理人id
		customerCall.setRecordUser(userInfo);
		// 保存工单类别
		customerCall.setType(customerCallLog.getType());
		// 业务类别
		customerCall.setBusinessType(customerCallLog.getBusinessType());
		// 受理方式
		customerCall.setAccessType(customerCallLog.getAccessType());
		//受理来源ID(关联留言信息)
		if(customerCallLog.getAccessType()==3&&accessSourceId!=null){
			customerCall.setAccessSourceId(accessSourceId);
		}
		//坐席端回复
		customerCall.setSeatReplay(customerCallLog.getSeatReplay());
		//当工单类型为'举报坐席'（4）时，保存这个被举报的坐席id
		if( customerCallLog.getType() == 4 ){
			customerCall.setReportSeatUserId( customerCallLog.getReportSeatUserId() );
		}else{
			customerCall.setReportSeatUserId( null );
		}
		/**
		 * 工单状态 1:我的待办 2:我的已办
		 */
		customerCall.setMsiSessionId(customerCallLog.getMsiSessionId()); // 话单与工单关联
		customerCall.setCallTime(callTime);// 来电时间
		return customerCall;
	}
	/**
	 * 功能：根据电话号码获取相关联的工单
	 */
	public String getRelatedCustomerCallByPhone() {
		try {
			Object userObject = getLoginUser();
			if (userObject != null) {
				UserInfo userInfo = (UserInfo) userObject;
				Page<Object[]> pageInfo = customerCallLogService
						.findCCLByPage(getPageable(new Sort(Direction.ASC,"recordTime")), customerCallLog, userInfo,iscomplete,"related",userInfo);
				/** 得到结果 */
				List<Object[]> customerCallLogList = pageInfo.getContent();
				/** 封装list */
				List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
				List<Dict> dicList = dictImpl.findList(_businessType, 1);
				//add by jiayq
				//查询所有的待办任务,add by jiayq
			    //Map<String,Task> myActiveTask = getMyActiveTask();
				List<HotNodeFormAttribute> allHotNodeFormAttributes = processDesignerService.findAll();
				Map<String, String> bMap=new HashMap<String, String>();
	            for (Dict di : dicList) {
					bMap.put("key_"+di.getValue(), di.getName());
				}  
			    if (customerCallLogList != null) {
				   int i = getPageable().getPageNumber()* getPageable().getPageSize() + 1;
				   for (Object[] os : customerCallLogList) {
					   Map<String, Object> map = getRelateCustomer(allHotNodeFormAttributes, bMap, i, os);
					   mapList.add(map);
					   i++;
				   }
				}
				this.ajaxPage(pageInfo, mapList);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 功能：得到关联
	 * @param allHotNodeFormAttributes
	 * @param bMap
	 * @param i
	 * @param os
	 * @return
	 */
	private Map<String, Object> getRelateCustomer(
		List<HotNodeFormAttribute> allHotNodeFormAttributes,Map<String, String> bMap, int i, Object[] os) {
		CustomerCallLog customerCallLog = (CustomerCallLog) os[0];
		HotWorkflowVar var = (HotWorkflowVar) os[1];
		Map<String, Object> map = new HashMap<String, Object>();
		/** 标识符 */
		map.put("id", customerCallLog.getVid());
		/** 序号 */
		map.put("no", i);
		/** 编号 */
		String cclSn = customerCallLog.getCclSn();
		map.put(_cclSn,StringUtils.isNotEmpty(cclSn)==true?cclSn:"--");
		putRelateCustomerTwo(allHotNodeFormAttributes, bMap, customerCallLog,var, map);
		/** 受理人员*/
		if (customerCallLog.getRecordUser() != null) {
			String userName = customerCallLog.getRecordUser().getUserName();
			map.put(_userName, StringUtils.isNotEmpty(userName)==true?userName:"--");
		} else {
			map.put(_userName, "--");
		}
		map.put("instanceId", var.getInstanceId());
		// **创建时间*//*
		if (StringUtils.isNotEmpty(TimeUtil.dateToStr(customerCallLog.getRecordTime()))) {
			map.put("recordTime", TimeUtil.dateToStr(customerCallLog.getRecordTime(),"yyyy-MM-dd HH:mm"));
		} else {
			map.put("recordTime", "--");
		}
		return map;
	}

	/**
	 * 功能：
	 * @param allHotNodeFormAttributes
	 * @param bMap
	 * @param customerCallLog
	 * @param var
	 * @param map
	 */
	private void putRelateCustomerTwo(
			List<HotNodeFormAttribute> allHotNodeFormAttributes,
			Map<String, String> bMap, CustomerCallLog customerCallLog,
			HotWorkflowVar var, Map<String, Object> map) {
		/** 状态 */
		String stateStr = var.getState();
		String state = getCurrentState(allHotNodeFormAttributes, stateStr, var.getProcessId());
		if (state != null) {
			map.put(_stateStr, state);
		} else if(stateStr!=null && stateStr.equals("end")){
			map.put(_stateStr, "已办结");
		}else{
			map.put(_stateStr, "-");
		}
		Integer businessType=customerCallLog.getBusinessType();
		map.put(_businessType,businessType!=null?bMap.get("key_"+businessType):"--");
		/** 工单类型 */
		Integer ccltype = customerCallLog.getType();
		String typeName=getTypeName(ccltype);
		if (ccltype != null) {
			map.put("typeValue", ccltype);
		} 
		map.put(_typeName, typeName);
		/** 工单内容 */
		String content = customerCallLog.getLogInfo();
		map.put("content", content != null?content:"--");
	}

	/**
	 * 功能：获取类型名称
	 * @param ccltype
	 * @return
	 */
	private String getTypeName(Integer ccltype) {
		String typeName="--";
		if(ccltype!=null){
			if (ccltype == 1) {
				typeName="咨询";
			} else if (ccltype == 2) {
				typeName= "投诉";
			} else {
				typeName= "建议";
			}
		}
		return typeName;
	}

	/**
	 * 输出工单列表，包括各种工单类型
	 */
	public String list() {
		try {
			UserInfo userInfo = getLoginUser();
			if (userInfo != null) {
				/* ==== 用来判断访问者的角色权限（开始） ==== */
				String isAdmin = "false";
				List<RoleInfo> list = roleService.getRoleByUser(userInfo
						.getUserId());
				for (RoleInfo r : list) {
					String roleCode = r.getRoleCode();
					//System.out.println("roleCode:  " + roleCode);
					if ("administrator".equals(roleCode)) {
						isAdmin = "true";// isAdmin 是普通管理员
						break;
					}
				}
				if ("true".equals(seatWelcome)) {
					this.setIDisplayStart(1);
					if (showRowCount != null) {
						this.setIDisplayLength(showRowCount);
					} else {
						this.setIDisplayLength(8);
					}
				}
				Page<Object[]> pageInfo = customerCallLogService
						.findCCLByPage(getPageable(new Sort(Direction.DESC,
								"vid")), customerCallLog, userInfo, iscomplete,
								fromPage,userInfo);
				/** 得到结果 */
				List<Object[]> customerCallLogList = pageInfo
						.getContent();
				
				//查询所有的待办任务,add by jiayq
				Map<String,Task> myActiveTask = getMyActiveTask();
				List<HotNodeFormAttribute> allHotNodeFormAttributes = processDesignerService.findAll();
				
				List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
				Map<String, Object> map=null;
				if (customerCallLogList != null) {
					int i = getPageable().getPageNumber()
							* getPageable().getPageSize() + 1;
					for (Object[] temp : customerCallLogList) {
					map = getCompleMap(userInfo,
								isAdmin, myActiveTask,
								allHotNodeFormAttributes, i, temp);
						mapList.add(map);
						if (showRowCount != null && i >= showRowCount) {
							break;
						}
						i++;
					}
				}
				this.ajaxPage(pageInfo, mapList);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 功能：
	 * @param userInfo
	 * @param isAdmin
	 * @param myActiveTask
	 * @param allHotNodeFormAttributes
	 * @param i
	 * @param temp
	 * @return
	 */
	private Map<String, Object> getCompleMap(UserInfo userInfo, String isAdmin,
			Map<String, Task> myActiveTask,
			List<HotNodeFormAttribute> allHotNodeFormAttributes, int i,
			Object[] temp) {
		CustomerCallLog customerCallLog =  (CustomerCallLog) temp[0];
		HotWorkflowVar var = (HotWorkflowVar) temp[1];
		
		Map<String, Object> map = new HashMap<String, Object>();
		// 标识符
		map.put("vid", customerCallLog.getVid());
		// 序号
		map.put("orderNumber", i);
		putCustyomerCallLogOne(userInfo, customerCallLog, map);
		putCustyomerCallLogTwo(myActiveTask, allHotNodeFormAttributes,
				customerCallLog, var, map);
		// 工单类型（1咨询 2投诉 3建议）
		Integer type = customerCallLog.getType();
		if (type != null) {
			map.put("type", type);
			if (type == 1) {
				map.put(_typeStr, "咨询");
			} else if (type == 2) {
				map.put(_typeStr, "投诉");
			} else if ( type == 3 ) {
				map.put(_typeStr, "建议");
			}else if(type==4){
				map.put(_typeStr, "举报坐席");
			}else{
				map.put(_typeStr, "其他");
			}
		} else {
			map.put(_typeStr, "-");
		}
		// 前台 还是 后台
		map.put("toPage", fromPage);
		// 是不是管理员
		map.put("isAdmin", isAdmin);
		map.put("isForKGroup", userInfo.getIsForkGroup());
		// 区分后台是否查询全部
		map.put("iscomplete", iscomplete);
		map.put("processId", var.getProcessId());
		return map;
	}

	/**
	 * 功能：
	 * @param myActiveTask
	 * @param allHotNodeFormAttributes
	 * @param customerCallLog
	 * @param var
	 * @param map
	 */
	private void putCustyomerCallLogTwo(Map<String, Task> myActiveTask,
			List<HotNodeFormAttribute> allHotNodeFormAttributes,
			CustomerCallLog customerCallLog, HotWorkflowVar var,
			Map<String, Object> map) {
		// 咨询内容、投诉内容
		String logInfo = customerCallLog.getLogInfo();
		if (StringUtils.isNotEmpty(logInfo)) {
			map.put("logInfo", logInfo);
		} else {
			map.put("logInfo", "");
		}
		// 工单状态
		String stateStr = var.getState();
		String state = getCurrentState(allHotNodeFormAttributes, stateStr, var.getProcessId());
		if (state != null) {
			map.put(_stateStr, state);
		} else if(stateStr!=null && stateStr.equals("end")){
			map.put(_stateStr, "已办结");
		}else{
			map.put(_stateStr, "-");
		}
		/**
		 *  操作名称和操作的url地址
		 *  add by jiayq
		 */
		String operationName = "查看";
		String operateUrl = "/jbpmworkorder/customerCallViewBack.action";
		Task tempTask = myActiveTask.get(var.getInstanceId());
		String instanceId = var.getInstanceId();
		if(myActiveTask.containsKey(instanceId)){
			if( allList != null ){
				operationName = "查看";
			}else{
				operationName = "办理";
			}
		}
		map.put("operateName", operationName);
		if(tempTask!=null){
			if( allList != null ){
				operationName = "查看";
			}else{
				operateUrl = getCurrentOperationUrl(allHotNodeFormAttributes, tempTask.getName(), var.getProcessId());
			}
		}
		map.put("operateUrl", operateUrl);
		map.put("instanceId", var.getInstanceId());
		// 办结时间
		Date ts = var.getLastOperationTime();
		map.put("dealOverTimeStr", "");
		if(stateStr!=null&&(stateStr.equals("end") || stateStr.equals("已办结"))){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				map.put("dealOverTimeStr", sdf.format(ts));
		}
	}

	/**
	 * 功能：
	 * @param userInfo
	 * @param customerCallLog
	 * @param map
	 */
	private void putCustyomerCallLogOne(UserInfo userInfo,
			CustomerCallLog customerCallLog, Map<String, Object> map) {
		// 工单编号
		String cclSn = customerCallLog.getCclSn();
		if (StringUtils.isNotEmpty(cclSn)) {
			map.put(_cclSn, cclSn);
		} else {
			map.put(_cclSn, "");
		}

		// 用户姓名
		String name = customerCallLog.getName();
		if (StringUtils.isNotEmpty(name)) {
			map.put(_name, name);
		} else {
			map.put(_name, "");
		}
		// 来电号码
		String phone = customerCallLog.getPhone();
		if (StringUtils.isNotEmpty(phone)) {
			map.put(_phone, phone);
		} else {
			map.put(_phone, "-");
		}

		// 受理人员
		UserInfo recordUser = customerCallLog.getRecordUser();
		if (recordUser != null
				&& recordUser.getUserName() != null
				&& StringUtils.isNotEmpty(recordUser
						.getUserName())) {
			map.put("recordUserIdName",
					recordUser.getUserName());
		} else {
			map.put("recordUserIdName", "");
		}
		// 当前登录人员id
		Integer nowLoginUserId = userInfo.getUserId();
		if (nowLoginUserId != null) {
			map.put("nowLoginUserId", nowLoginUserId);
		}
		//回访坐席id
		Integer callBackUserId = customerCallLog.getCallBackUserId();
		if( callBackUserId != null ){
			map.put("callBackUserId", callBackUserId);
		}else{
			map.put("callBackUserId", "");
		}
		// 受理人员ID
		if (recordUser != null
				&& recordUser.getUserId() != null) {
			map.put("recordUserId", recordUser.getUserId());
		} else {
			map.put("recordUserId", "");
		}
		// 受理时间
		Timestamp recordTime = customerCallLog.getRecordTime();
		String recordTimeStr = DateTimeUtil.timestampToString(recordTime,"yyyy-MM-dd HH:mm");
		if (recordTimeStr != null&& StringUtils.isNotEmpty(recordTimeStr)) {
			map.put("recordTimeStr", recordTimeStr);
		} else {
			map.put("recordTimeStr", "-");
		}
	}
	
	/**获取所有候选人中包含当前用户的任务，建立是流程实例ID和任务的映射
	 * @return
	 */
	private Map<String,Task> getMyActiveTask(){
		Map<String,Task> myactiveTask = new HashMap<String, Task>();
		List<Task> tasklist = processEngine.getTaskService().createTaskQuery().candidate(getLoginUser().getUserId().toString()).list();
		if(tasklist!=null){
			for(int i=0; i<tasklist.size(); i++){
				Task temp = tasklist.get(i);
				myactiveTask.put(temp.getExecutionId(), temp);
			}
		}
		return myactiveTask;
	}
	
	/**
	 * 获取当前状态
	 * @param nodeFormAttributes
	 * @param nodeName
	 * @param processId
	 * @return
	 */
	private String getCurrentState(List<HotNodeFormAttribute> nodeFormAttributes,String nodeName,int processId){
		for(int i=0; i<nodeFormAttributes.size(); i++){
			HotNodeFormAttribute hnfa = nodeFormAttributes.get(i);
			if(hnfa.getNodeName().equals(nodeName) && hnfa.getProcessAttribute().getId().intValue() == processId){
				return hnfa.getState();
			}
		}
		return null;
	}
	
	/**
	 * 获取当前的操作URl
	 * @param nodeFormAttributes
	 * @param task
	 * @return
	 */
	private String getCurrentOperationUrl(List<HotNodeFormAttribute> nodeFormAttributes,String nodeName,int processId){
		for(int i=0; i<nodeFormAttributes.size(); i++){
			HotNodeFormAttribute hnfa = nodeFormAttributes.get(i);
			if(hnfa.getNodeName().equals(nodeName) && hnfa.getProcessAttribute().getId().intValue() == processId){
				return hnfa.getOperateUrl();
			}
		}
		return "";
	}
	
	/**
	 *前台统计待回访的工单
	 */
	
	public String listCount() {
		try {
			UserInfo userInfo = getLoginUser();
			if (userInfo != null) {
				/* ==== 用来判断访问者的角色权限（开始） ==== */
				List<RoleInfo> list = roleService.getRoleByUser(userInfo
						.getUserId());
				for (RoleInfo r : list) {
					String roleCode = r.getRoleCode();
					//System.out.println("roleCode:  " + roleCode);
					if ("administrator".equals(roleCode)) {
						break;
					}
				}
			int  count= customerCallLogService.listCount( customerCallLog, userInfo, null,null);
			this.ajax(count);
			}
		} catch (Exception e) {
		//	e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	/**
	 * 后台工单处理
	 */
	public String backdealCustomerCallLog() {
		customerCallLog = this.customerCallLogService.findOne(vid);
		if( customerCallLog.getType() == 4 ){
			if(customerCallLog.getReportSeatUserId()!=null){
				reportSeat = userService.findOne( customerCallLog.getReportSeatUserId() );
				if(reportSeat == null){
					reportSeat = new UserInfo();
				}else{
					reportSeat.getUserName();
				}
			}
		}
		ActionContext ctx = ActionContext.getContext();
		ctx.put(_fromPage, fromPage);// 判断是前台或后台
		return SUCCESS;
	}

	// 查看工单详情
	public String customerCallView() {
		customerCallLog = this.customerCallLogService.findOne(vid);
		if( customerCallLog.getType() == 4 ){
			if(customerCallLog.getReportSeatUserId()!=null){
				reportSeat = userService.findOne( customerCallLog.getReportSeatUserId() );
				if(reportSeat == null){
					reportSeat = new UserInfo();
				}else{
					reportSeat.getUserName();
				}
			}
		}
		if(customerCallLog.getRecordUser()==null){
			customerCallLog.setRecordUser(new UserInfo());
		}else{
			customerCallLog.getRecordUser().getUserName();
		}
		//用于查看处理进度
		instanceId=customerCallLog.getWorkflowId();
		ActionContext ctx = ActionContext.getContext();
		ctx.put(_fromPage, fromPage);// 判断是前台或后台

		return SUCCESS;
	}
	
	/**
	 * add by jiayq
	 * 获取流程处理进度
	 * @return
	 */
	public List<Map<String,String>> getProcessStates(){
		return workflowService.getProcesseredNodes(instanceId);
	}
	/**
	 * 来电人信息详情
	 */
	public String callNameInfo() {
		try {
			if (vid != null) {
				customerCallLog = this.customerCallLogService.findOne(vid);
				if (customerCallLog != null) {
					crm = customerCallLog.getCrm();
				}
				ActionContext ctx = ActionContext.getContext();
				ctx.put(_fromPage, fromPage);// 判断是前台或后台
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return SUCCESS;
	}
	/**
	 * 后台工单处理提交
	 */
	public String saveCustomerCallDeal() {
		CustomerCallLog cll = this.customerCallLogService.findOne(vid);
		cll.setReplyInfo(customerCallLog.getReplyInfo());
		// 利用工作流的新接口 更新工单内容
		this.customerCallLogService.updateCCL(cll,instanceId,nextAction,nextUserId,customerCallLog.getReplyInfo());
		ajax(1);
		return null;
	}
	/**
	 * 工单回访
	 */
	public String customerCallBack() {
		customerCallLog = this.customerCallLogService.findOne(vid);
		if( customerCallLog.getType() == 4 ){
			if(customerCallLog.getReportSeatUserId()!=null){
				reportSeat = userService.findOne( customerCallLog.getReportSeatUserId() );
			}
		}
		ActionContext ctx = ActionContext.getContext();
		ctx.put(_fromPage, fromPage);// 判断是前台或后台
		return SUCCESS;
	}
	/**
	 * 工单回访提交
	 */
	public String saveCustomerCallBack() {
			CustomerCallLog cll = this.customerCallLogService.findOne(vid);
			// 回访多次的话会更新回访时间
			cll.setCallBackTime(new Timestamp(System.currentTimeMillis()));
			//回访结果（0成功 1不成功  贾工为什么要删？）
			cll.setVisitResult(customerCallLog.getVisitResult());
			cll.setCallBackSuccessResult(customerCallLog.getCallBackSuccessResult());
			cll.setVisitFaildReason(customerCallLog.getVisitFaildReason());
			String comment = "";
			if(customerCallLog.getVisitResult() == 0 ){
				comment = customerCallLog.getCallBackSuccessResult();
			}else if(customerCallLog.getVisitResult() == 1){
				comment+="失败:";
				int reasion = customerCallLog.getVisitFaildReason();
				switch (reasion) {
				case 1:
					comment = "无人接听";
					break;
				case 2:
					comment = "电话正忙";
					break;
			    case 3:
					comment = "呼叫转移";
					break;
				case 4:
					comment = "电话关机";
					break;
				case 5:
					comment = "电话停机";
					break;
				case 6:
					comment = "空号";
					break;
				case 7:
					comment = "用户拒绝";
				default:
					break;
				}
			}
			customerCallLogService.updateCCL(cll, instanceId, nextAction,nextUserId,comment);
			ajax(1);
		return null;

	}

	/**
	 * 删除工单
	 */
	public String delCustomerCallLog() {
		customerCallLogService.deleteCustomerCallLog(vid);
		ajax(1);
		return null;
	}
	
	/**
	 * 一个号码关联多个客户时，选择客户信息
	 * @return
	 */
	public String selectCrms(){
			List<CRM> crms = crmimpl.findByPhone(phone);
			List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
			for(CRM crm : crms){
				Map<String,Object> map = new HashMap<String,Object>();
				//获取用户的crm id
				map.put("crmId", crm.getVid());
				//联系电话
				map.put("mobile", crm.getMobile());
				String backPhone = crm.getBackPhone();
				if(StringUtils.isNotEmpty(backPhone)){
					map.put("backPhone", backPhone);
				}
				// 获取用户的姓名
				map.put("uname", crm.getName());
				// 获取用户的地址
				map.put("uaddress", crm.getAddress());
				// 获取用户的性别
				map.put("usex", crm.getGender());
				// 获取用户类别
				map.put("personType", crm.getPersonType());
				// 获取用户身份证号
				map.put("cardId", crm.getCardId());
				// 获取用户户口所在地
				map.put("hkAddress", crm.getHkAddress());
				// 获取用户工作单位
				map.put("company", crm.getCompany());
				// 获取用户职务
				map.put("job", crm.getJob());
				// 获取用户月收入
				if (crm.getReceiveMoney() != null) {
					String receiveMoney = crm.getReceiveMoney().toString();
					map.put(_receiveMoney, receiveMoney);
				} else {
					map.put(_receiveMoney, crm.getReceiveMoney());
				}
				// 获取备注
				map.put("note", crm.getNote());
				mapList.add(map);
			}
			Gson gson = new Gson();
			String json = gson.toJson(mapList);
			ajax(json);
		return null;
	}
	
	/**获取邻村信息
	 * @return
	 * @throws Exception
	 */
	public String getTempSaveInfo() throws Exception{
		instanceId = URLDecoder.decode(instanceId, "UTF-8");
		CustomerCallLog ccl = customerCallLogService.findCCLByWorkflowId(instanceId);
		if(ccl == null){
			return null;
		}
		Map<String,String> result = new HashMap<String, String>();
		result.put(_phone, ccl.getPhone());
		result.put("name", ccl.getCrm().getName());
		result.put("sex", ccl.getSex().toString());
		result.put("age", ccl.getAge()==null?"":ccl.getAge().toString());
		result.put("address", ccl.getAddress());
		result.put("company", ccl.getCrm().getCompany());
		result.put("accessType", ccl.getAccessType().toString());
		result.put(_businessType, ccl.getBusinessType().toString());
		result.put("type", ccl.getType().toString());
		result.put("logInfo", ccl.getLogInfo());
		result.put("seatReplay", ccl.getSeatReplay());
		result.put("userId", ccl.getReportSeatUserId()==null?"":ccl.getReportSeatUserId().toString());
		if(ccl.getReportSeatUserId()!=null){
			UserInfo userInfo =  userService.findOne(ccl.getReportSeatUserId());
			if(userInfo==null){
				result.put(_userName, "");
			}else{
				result.put(_userName, userInfo.getUserName());
			}
		}
		result.put("backPhone", ccl.getCrm().getBackPhone());
		result.put("personType", String.valueOf(ccl.getCrm().getPersonType()));
		result.put("cardId", ccl.getCrm().getCardId());
		result.put("hkAddress", ccl.getCrm().getHkAddress());
		result.put("job", ccl.getCrm().getJob());
		result.put(_receiveMoney, ccl.getCrm().getReceiveMoney()==null?"":(ccl.getCrm().getReceiveMoney().toString()));
		result.put("note", ccl.getCrm().getNote());
		ajax(result);
		return null;
	}
	
	/**获取流程中的坐席人信息
	 * @return
	 * @throws Exception
	 */
	public String getTheSeat() throws Exception{
		HotWorkflowVar var =  hotWorkflowVarService.findByInstanceId(instanceId);
		String processers = var.getProcessers();
		if(processers!=null){
			List<UserInfo> userlist = userService.findUsersByIds(processers);
			for(int i=0; i<userlist.size(); i++){
				UserInfo userInfo = userlist.get(i);
				boolean flag = roleUserService.isExistRoleUser(WorkflowGlobalCfg.ROLE_SEATER_ID, userInfo.getUserId());
				if(flag){
					Map<String,String> map = new HashMap<String, String>();
					map.put("id", userInfo.getUserId().toString());
					map.put("name", userInfo.getUserName()+"("+userInfo.getLoginName()+")");
					ajax(map);
				}
			}
		}
		return null;
	}
	
	/**获取流程定义ID
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public HotWorkflowVar getWorkflowVar() throws UnsupportedEncodingException{
		instanceId = URLDecoder.decode(instanceId, "UTF-8");
		HotWorkflowVar hfv=hotWorkflowVarService.findByInstanceId(instanceId);
		return  hfv;
	}	
	/**获取处理历史
	 * approveTime,nodeName,approverName,approverId,role,comment
	 * @return
	 */
	public List<Map<String,String>> getProcessHistoryList() throws UnsupportedEncodingException{
		HotWorkflowVar var = getWorkflowVar();
		return var.getProcessHistoryList();
	}
	public Task getCurrentTask(){
		return processEngine.getTaskService().createTaskQuery().processInstanceId(instanceId).uniqueResult();
	}
	public UserInfo getReportSeat() {
		return reportSeat;
	}
	public void setReportSeat(UserInfo reportSeat) {
		this.reportSeat = reportSeat;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getAllList() {
		return allList;
	}
	public void setAllList(Integer allList) {
		this.allList = allList;
	}
	public String getIscomplete() {
		return iscomplete;
	}
	public void setIscomplete(String iscomplete) {
		this.iscomplete = iscomplete;
	}
	public String getWorkflowName() {
		return workflowName;
	}
	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}
	public String getForward() {
		return forward;
	}
	public void setForward(String forward) {
		this.forward = forward;
	}
	public Integer getCheckCallBack() {
		return checkCallBack;
	}
	public void setCheckCallBack(Integer checkCallBack) {
		this.checkCallBack = checkCallBack;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public Integer getShowRowCount() {
		return showRowCount;
	}
	public void setShowRowCount(Integer showRowCount) {
		this.showRowCount = showRowCount;
	}
	public String getSeatWelcome() {
		return seatWelcome;
	}
	public void setSeatWelcome(String seatWelcome) {
		this.seatWelcome = seatWelcome;
	}
	public String getCallPhone() {
		return callPhone;
	}
	public void setCallPhone(String callPhone) {
		this.callPhone = callPhone;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCclContent() {
		return cclContent;
	}
	public void setCclContent(String cclContent) {
		this.cclContent = cclContent;
	}
	public String getHandUpTime() {
		return handUpTime;
	}
	public void setHandUpTime(String handUpTime) {
		this.handUpTime = handUpTime;
	}
	public Timestamp getCallTime() {
		return callTime;
	}
	public void setCallTime(Timestamp callTime) {
		this.callTime = callTime;
	}
	public String getCallTimeStr() {
		return callTimeStr;
	}
	public void setCallTimeStr(String callTimeStr) {
		this.callTimeStr = callTimeStr;
	}
	public CustomerCallLog getCustomerCallLog() {
		return customerCallLog;
	}
	public void setCustomerCallLog(CustomerCallLog customerCallLog) {
		this.customerCallLog = customerCallLog;
	}
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	public Integer getCheckType() {
		return checkType;
	}
	public void setCheckType(Integer checkType) {
		this.checkType = checkType;
	}
	public String getCallTimeString() {
		return callTimeString;
	}
	public void setCallTimeString(String callTimeString) {
		this.callTimeString = callTimeString;
	}
	public Integer getCclId() {
		return cclId;
	}
	public void setCclId(Integer cclId) {
		this.cclId = cclId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getTurnTime() {
		return turnTime;
	}
	public void setTurnTime(String turnTime) {
		this.turnTime = turnTime;
	}
	public Integer getChenggong() {
		return chenggong;
	}
	public void setChenggong(Integer chenggong) {
		this.chenggong = chenggong;
	}
	public String getFromPage() {
		return fromPage;
	}
	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}
	public Integer getAccessSourceId() {
		return accessSourceId;
	}
	public void setAccessSourceId(Integer accessSourceId) {
		this.accessSourceId = accessSourceId;
	}
	private int processId;
	private String nextAction;

	public int getProcessId() {
		return processId;
	}
	public void setProcessId(int processId) {
		this.processId = processId;
	}
	public String getNextAction() {
		return nextAction;
	}
	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}
	public String getNextUserId() {
		return nextUserId;
	}
	public void setNextUserId(String nextUserId) {
		this.nextUserId = nextUserId;
	}
	public CRM getCrm() {
		return crm;
	}
	public void setCrm(CRM crm) {
		this.crm = crm;
	}
}
