package cn.com.qytx.workflow.jpdl.template;

import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;

/**
 * Created by izerui.com on 14-5-7.
 */
public class TaskCreateHandler implements AssignmentHandler {

    /**
	 * 描述含义
	 */
	private static final long serialVersionUID = 8364436879801037558L;

	@Override
    public void assign(Assignable assignable, OpenExecution execution) throws Exception {
//        HotTaskHistory taskHistory = new HotTaskHistory();
//      /*  CustomerCallLog customerCallLog = new CustomerCallLog();
//        customerCallLog.setWorkflowId(execution.getId());
//        taskHistory.setCustomerCallLog(customerCallLog);*/
//        taskHistory.setProcessInstanceId(execution.getId());
//        taskHistory.setProcessDefinitionId(execution.getProcessDefinitionId());
//        taskHistory.setProcessInstanceId(execution.getId());
//        taskHistory.setActivityId(execution.getActivity().getName());//放入工单状态、就是节点名称
//
//        taskHistory.setCreateTime(new Timestamp(System.currentTimeMillis()));
//        TaskImpl taskImpl = (TaskImpl) assignable;
//        taskHistory.setTaskId(taskImpl.getId());
//
//        HotProcessAttributeDao processAttributeDao = cn.com.qytx.platform.base.application.SpringContextHolder.getBean(HotProcessAttributeDao.class);
//        HotProcessAttribute processAttribute =processAttributeDao.findOne("processDefineId = ?",execution.getProcessDefinitionId());
//        Set<HotNodeFormAttribute> nodeSet = processAttribute.getNodeSet();
//
//        Iterator<HotNodeFormAttribute> iterator = nodeSet.iterator();
//        while(iterator.hasNext()){
//            HotNodeFormAttribute next = iterator.next();
//            if(taskHistory.getActivityId().equals(next.getNodeName())){
//                taskHistory.setOperateName(next.getOperateName());
//                taskHistory.setOperateUrl(next.getOperateUrl());
//                break;
//            }
//        }
//
//
//        cn.com.qytx.platform.base.application.SpringContextHolder.getBean(HotTaskHistoryDao.class).saveOrUpdate(taskHistory);
    }
}
