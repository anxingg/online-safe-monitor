package cn.com.qytx.workflow.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryActivityInstance;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.model.TransitionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.com.qytx.platform.org.dao.UserDao;
import cn.com.qytx.platform.org.domain.RoleInfo;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IRole;
import cn.com.qytx.platform.org.service.IUser;
import cn.com.qytx.workflow.constans.WorkflowConstans;
import cn.com.qytx.workflow.dao.HotNodeFormAttributeDao;
import cn.com.qytx.workflow.dao.HotProcessAttributeDao;
import cn.com.qytx.workflow.dao.HotWorkflowVarDao;
import cn.com.qytx.workflow.domain.HotNodeFormAttribute;
import cn.com.qytx.workflow.domain.HotProcessAttribute;
import cn.com.qytx.workflow.domain.HotWorkflowVar;
import cn.com.qytx.workflow.service.WorkflowService;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

/**
 * Created by izerui.com on 14-5-5.
 */
@Service("hotworkflowService")
@Transactional
public class WorkFlowServiceImpl implements WorkflowService,WorkflowConstans,Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8847329538031326393L;

	private static Logger logger = LoggerFactory.getLogger(WorkFlowServiceImpl.class);

//    @Resource(name = "taskService")
    private transient TaskService taskService;
//
//    @Resource(name="repositoryService")
    @SuppressWarnings("unused")
	private transient RepositoryService repositoryService;
//
//    @Resource(name = "historyService")
//    private HistoryService historyService;
//
//    @Resource(name = "executionService")
    private transient ExecutionService executionService;
    
    @Resource
    private transient ProcessEngine processEngine;

    @Resource(name="hotnodeFormAttributeDao")
    private HotNodeFormAttributeDao nodeFormAttributeDao;
    @Resource(name="hotprocessAttributeDao")
    private HotProcessAttributeDao processAttributeDao;
    @Resource
    private transient HotWorkflowVarDao hotWorkflowVarDao;
    @Resource
    private UserDao<UserInfo> userDao;
    @Resource
    private transient IUser userService;
    @Resource
    private IRole roleService;
//    @Resource
//    private IRoleUser roleUserService;

    @Transactional(propagation=Propagation.REQUIRED)
    public String startProcessInstance(String processDefinitionId,String nextNodeKey,String nextUserId) {
        try{
        	taskService=processEngine.getTaskService();
        	executionService=processEngine.getExecutionService();
            if(nextNodeKey==null){
                throw new WorkflowException("没有指定目标审批节点!");
            }

            HotProcessAttribute processAttribute = processAttributeDao.getProcessAttributeByDefineId(processDefinitionId);
            if(processAttribute!=null && processAttribute.getProcessState()!=2){
                throw  new WorkflowException("流程不能启动，请发布并启用最新流程！");
            }

            ProcessInstance processInstance = executionService.startProcessInstanceById(processDefinitionId,
                    mapUserVariable());
            HotWorkflowVar var = new HotWorkflowVar();
            var.setInstanceId(processInstance.getId());
            var.setState("发起流程");
            var.setLastOperationTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            if(processAttribute != null){
            	var.setProcessId(processAttribute.getId());
            }

            hotWorkflowVarDao.saveOrUpdate(var);
            takeAndcompleteTask(taskService.createTaskQuery().processInstanceId(processInstance.getId()).uniqueResult().getId(),nextNodeKey,nextUserId,null);

            return processInstance.getId();
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error(e.getMessage(),e);
            return null;
        }

    }

   
    /**接受任务并转到下一步
     * @param taskId
     * @param nextNodeId
     * @return
     */
    /* (non-Javadoc)
     * @see cn.com.qytx.workflow.service.WorkflowService#takeAndcompleteTask(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public Boolean takeAndcompleteTask(String taskId, String nextAction,String nextUserId,String comment) {
        try{
        	taskService=processEngine.getTaskService();
        	executionService=processEngine.getExecutionService();
            if(nextAction==null){
                throw new WorkflowException("没有指定目标审批节点!");
            }
            Task task = taskService.getTask(taskId);
            //接受任务
            taskService.takeTask(taskId, getSessionUser().getUserId().toString());
            HotProcessAttribute processAttribute = getProcessAttributeByInstanceId(task.getExecutionId());
            //获取连线
            VariableMap map = new VariableMap();
            //放入下一个节点的审批人集合
            if(nextUserId == null || nextUserId.length() == 0){
            	HotNodeFormAttribute nodeFormAttribute = nodeFormAttributeDao.findByProcessIdAndName(processAttribute.getId(), nextAction);
            	String candidates = getCandidates(nodeFormAttribute);
            	if(!"".equals(candidates)){
            		map.put(CANDIDATE_USERS,candidates);
            	}
            	
            }else{
            	map.put(CANDIDATE_USERS, nextUserId);
            }
            //完成任务，并执行下一步
            taskService.completeTask(taskId, nextAction, map);
            //更新状态
            HotWorkflowVar var =  hotWorkflowVarDao.findByInstanceId(task.getExecutionId());
            
            var.setLastOperationTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            String processer = var.getProcessers();
            if(processer == null){
            	processer = "";
            }
            processer+=","+getSessionUser().getUserId()+",";
            var.setProcessers(processer);
            
            HistoryProcessInstance hpi = processEngine.getHistoryService().createHistoryProcessInstanceQuery().processInstanceId(var.getInstanceId()).uniqueResult();
            if(hpi.getState().equals(HistoryProcessInstance.STATE_ACTIVE)){
	            Task nextTask = taskService.createTaskQuery().processInstanceId(var.getInstanceId()).uniqueResult();
	            var.setState(nextTask.getName());
            }else{
            	var.setState("end");
            }
            
            aa(task, comment,var);

        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error(e.getMessage(),e);
            return false;
        }
        return true;
    }
    
    public void aa(Task task,String comment, HotWorkflowVar var){
    	try{
	    	  //处理意见
	        Map<String,String> commentMap = new HashMap<String, String>();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        commentMap.put("approveTime", sdf.format(new Timestamp(Calendar.getInstance().getTimeInMillis())));
	        commentMap.put("nodeName", task.getName());
	        commentMap.put("approverName", getSessionUser().getUserName());
	        commentMap.put("approverId", getSessionUser().getUserId().toString());
	        List<RoleInfo> rolelist = roleService.findRolesByUserId(getSessionUser().getUserId(), 1);
	        StringBuffer sb = new StringBuffer("");
	        for(int i=0; i<rolelist.size(); i++){
	        	RoleInfo r = rolelist.get(i);
	        	sb.append(r.getRoleName());
	        	sb.append(",");
	        }
	        commentMap.put("role", sb.toString());
	        commentMap.put("comment", comment);
	        var.addProcessHistory(commentMap);
	        hotWorkflowVarDao.saveOrUpdate(var);
	
	    }catch (Exception e){
	        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	        logger.error(e.getMessage(),e);
	    }
    }


    /**
     * 验证是否是结束之前的最后一个任务节点
     * @param activity  节点
     * @return true 是（要自动完成）、false（不自动完成）
     */
//    private boolean isActivityToEnd(ActivityImpl activity){
//        List<TransitionImpl> outgoingTransitions = (List<TransitionImpl>) activity.getOutgoingTransitions();
//        //如果有多条连线，说明该节点不可直接完成
//        if(outgoingTransitions.size()>1){
//            return false;
//        }
//        TransitionImpl transition = outgoingTransitions.get(0);
//
//        //如果有且只有一条连线，并且连线指向结束节点
//        if(transition.getDestination().getType().equals(NODE_TYPE_END)){
//            return true;
//        }else{
//            return false;
//        }
//    }

    @Override
    public Boolean deleteProcessInstanceCascadeByDefinitionId(String processDefinitionId) {
        try{
        	executionService=processEngine.getExecutionService();
            List<ProcessInstance> list = executionService.createProcessInstanceQuery().processDefinitionId(processDefinitionId).list();
            for(ProcessInstance pi : list){
                executionService.deleteProcessInstanceCascade(pi.getId());
            }
            //taskHistoryDao.bulkDelete("delete from HotTaskHistory where processDefinitionId = '" + processDefinitionId + "'");

        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }
    @Override
    public Boolean deleteProcessInstanceById(String processInstanceId) {
        try{
        	executionService=processEngine.getExecutionService();
        	HistoryProcessInstance hpi = processEngine.getHistoryService().createHistoryProcessInstanceQuery().processInstanceId(processInstanceId).uniqueResult();
        	if(hpi.getState().equals(HistoryProcessInstance.STATE_ACTIVE)){
        		executionService.deleteProcessInstanceCascade(processInstanceId);
        	}else{
        		processEngine.execute(new DeleteInstanceCmd(processInstanceId));
        	}
        	hotWorkflowVarDao.deleteByInstanceId(processInstanceId);
           
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }


    @Override
    public Boolean endProcessInstance(String processInstanceId) {
        try{
        	executionService=processEngine.getExecutionService();
            executionService.endProcessInstance(processInstanceId, "人工结束");
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    /**
     * 根据流程定义ID 获取流程定义
     * @param processDefinitionId
     * @return
     */
//    private ProcessDefinitionImpl getProcessDefinition(String processDefinitionId){
//    	repositoryService = processEngine.getRepositoryService();
//        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId);
//        ProcessDefinitionImpl processDefinition = (ProcessDefinitionImpl) processDefinitionQuery.uniqueResult();
//        return processDefinition;
//    }

    /**
     * 根据nextNodeKey获取下一步节点设置的用户
     */
    private String getCandidates(HotNodeFormAttribute nodeFormAttribute){
    	if(nodeFormAttribute==null){
    		return "";
    	}
        String candidates = appendCandidates(userDao.findUsersByGroupId(nodeFormAttribute.getDepts()));
        candidates += appendCandidates(userDao.findUsersByRoleId(nodeFormAttribute.getRoles()));

        if(StringUtils.isNotEmpty(nodeFormAttribute.getCandidate())){
            candidates += nodeFormAttribute.getCandidate();
        }
        return candidates;
    }

    private String appendCandidates(List<UserInfo> users){
        if(users==null){
            return "";
        }
        StringBuilder candidates = new StringBuilder();
        for(UserInfo userInfo:users){
        	candidates.append(userInfo.getUserId()).append(",");
        }
        return candidates.toString();
    }



    private Map<String,Object> mapUserVariable(){
       return new VariableMap().put(CREATER, getSessionUser().getUserId().toString());
    }


    /**
     * 根据源节点， 跟目标节点， 获取中间的连线
     * @param processDefinitionId 流程定义ID
     * @param sourceNodeId 源节点
     * @param targetNodeId 目标节点
     * @return 连线的id
     */
//    private TransitionImpl getTransitionIdBySourceAndTarget(String processDefinitionId,String sourceNodeId,String targetNodeId){
//        ProcessDefinitionImpl processDefinition = getProcessDefinition(processDefinitionId);
//        ActivityImpl sourceActivity = processDefinition.findActivity(sourceNodeId);
//        List<TransitionImpl> outgoingTransitions = (List<TransitionImpl>) sourceActivity.getOutgoingTransitions();
//        System.err.println(outgoingTransitions.size());
//        for(TransitionImpl transition: outgoingTransitions){
//            if(transition.getDestination().getName().equals(targetNodeId)){
//                return transition;
//            }
//        }
//
//        return null;
//
//    }

    /**
     * 根据流程实例ID 获取流程定义
     */
    private HotProcessAttribute getProcessAttributeByInstanceId(String processInstanceId){
    	executionService=processEngine.getExecutionService();
        ProcessInstance processInstance = executionService.findProcessInstanceById(processInstanceId);
        HotProcessAttribute processAttribute = processAttributeDao.getProcessAttributeByDefineId(processInstance.getProcessDefinitionId());

        return processAttribute;
    }



    /**
     * 获取session中的用户信息
     * @return
     */
    private UserInfo getSessionUser(){
        //for local test
        if(Authentication.getAuthor()!=null){
            return Authentication.getAuthor();
        }
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        return (UserInfo)request.getSession().getAttribute("adminUser");
    }

   @SuppressWarnings("serial")
static class  VariableMap extends HashMap<String,Object> {

        @Override
        public Map<String,Object> put(String key, Object value) {
            super.put(key, value);
            return this;
        }
    }

	@SuppressWarnings("unchecked")
	public List<ComparableMap> getNextActions(int nodeId){
		List<ComparableMap> maplist = new ArrayList<ComparableMap>();
		HotNodeFormAttribute hnfa = nodeFormAttributeDao.findOne(nodeId);
		String taskName = hnfa.getNodeName();
		int processId = hnfa.getProcessAttribute().getId();
		String names = hnfa.getOperateName();
		Gson gson = new Gson();
		@SuppressWarnings("serial")
		List<HashMap<String, String>> namesmap = gson.fromJson(names, new TypeToken<List<Map<String,String>>>() {}.getType());
		if(namesmap == null){
			namesmap = new ArrayList<HashMap<String, String>>();
		}
		
		HotProcessAttribute hpa = processAttributeDao.findOne(processId);
		String processDefineId = hpa.getProcessDefineId();
		ProcessDefinitionImpl pd = (ProcessDefinitionImpl) processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionId(processDefineId).uniqueResult();
		ActivityImpl acti = pd.findActivity(taskName);
		List<TransitionImpl> list = (List<TransitionImpl>) acti.getOutgoingTransitions();
		String key = "actionIndex";
		for(int i=0; i<list.size(); i++){
			TransitionImpl tran = list.get(i);
			ComparableMap map = new ComparableMap();
			map.put("actionCode", tran.getName());
			map.put("actionShow","");
			map.put(key, "0");
			for(int j=0; j<namesmap.size(); j++){
				Map<String,String> temp = namesmap.get(j);
				if(temp.get("actionCode").equals(tran.getName())){
					map.put("actionShow", temp.get("actionShow"));
					map.put(key, temp.get(key)==null?"0":temp.get(key));
					break;
				}
			}
			maplist.add(map);
		}
		Collections.sort(maplist);
		return maplist;
	}


	@Override
	public String startProcessInstanceOnly(String processDefinitionId) {
		taskService=processEngine.getTaskService();
    	executionService=processEngine.getExecutionService();

        HotProcessAttribute processAttribute = processAttributeDao.getProcessAttributeByDefineId(processDefinitionId);
        if(processAttribute!=null && processAttribute.getProcessState()!=2){
            throw  new WorkflowException("流程不能启动，请发布并启用最新流程！");
        }

        ProcessInstance processInstance = executionService.startProcessInstanceById(processDefinitionId,
                mapUserVariable());
        HotWorkflowVar var = new HotWorkflowVar();
        var.setInstanceId(processInstance.getId());
        var.setState("发起流程");
        var.setLastOperationTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        if(processAttribute != null ){
        	var.setProcessId(processAttribute.getId());
        }
        
        hotWorkflowVarDao.saveOrUpdate(var);
        return processInstance.getId();
	}


	@Override
	public String getTaskIdByInstanceId(String instanceId,String userId) {
		Task task = processEngine.getTaskService().createTaskQuery().processInstanceId(instanceId).candidate(userId).uniqueResult();
		return task.getId();
	}


	@Override
	public int getProcessIdByInstanceId(String instanceId) {
		ProcessInstance pi = processEngine.getExecutionService().createProcessInstanceQuery().processInstanceId(instanceId).uniqueResult();
		String defineId = pi.getProcessDefinitionId();
		HotProcessAttribute pa = processAttributeDao.getProcessAttributeByDefineId(defineId);
		if(pa!=null){
			return pa.getId();
		}
		return 0;
	}


	@Override
	public List<Map<String, String>> getProcesseredNodes(String instanceId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		List<HistoryActivityInstance> hailist = processEngine.getHistoryService().createHistoryActivityInstanceQuery().processInstanceId(instanceId).orderAsc("startTime").list();
		List<HistoryTask> htlist = processEngine.getHistoryService().createHistoryTaskQuery().executionId(instanceId).orderAsc("createTime").list();
		for(int i=0; i<hailist.size(); i++){
			Map<String,String> map = new HashMap<String, String>();
			HistoryActivityInstance ht = hailist.get(i);
			String key = "name";
			map.put(key, ht.getActivityName());
			HistoryTask temp = htlist.get(i);
			if(temp.getState()!=null && temp.getState().equals("completed")){
				map.put("time", sdf.format(ht.getEndTime()));
				UserInfo u =  userService.findOne(Integer.parseInt(temp.getAssignee()));
				map.put("user", u.getUserName());
				map.put("classs", "");
			}else{
				map.put("classs", "red");
			}
			String activeName = map.get(key);
			if(activeName!=null){
				map.put(key, getNumbers(activeName));
			}		
			list.add(map);
		}
		HistoryProcessInstance hpi = processEngine.getHistoryService().createHistoryProcessInstanceQuery().processInstanceId(instanceId).uniqueResult();
		if(hpi.getState().equals(HistoryProcessInstance.STATE_ENDED)){
			Map<String,String> map = new HashMap<String, String>();
			map.put("name", "结束");
			map.put("time", "");
			map.put("classs", "red");
			list.add(map);
		}
		return list;
	}
	
	 //截取数字  
    public String getNumbers(String content) {  
        Pattern pattern = Pattern.compile("\\D+");  
        Matcher matcher = pattern.matcher(content);  
        while (matcher.find()) {  
            return matcher.group(0);  
        }  
        return "";  
    }

}
