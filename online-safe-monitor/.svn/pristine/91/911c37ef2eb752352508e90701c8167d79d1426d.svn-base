package cn.com.qytx.workflow.service;

import java.util.List;
import java.util.Map;

import cn.com.qytx.workflow.service.impl.ComparableMap;

/**
 * Created by izerui on 14-5-5.
 */
public interface WorkflowService{


    /**
     * 启动流程
     * @param processDefinitionId 流程定义的key
     * @param nextNodeKey 下个节点id
     * @param dealHistory 处理信息对象
     * @return 流程实例ID
     */
    String startProcessInstance(String processDefinitionId,String nextNodeKey,String nextUserId);


    /**
     * 完成任务，并且设置流程转向
     * @param taskId 任务ID
     * @param nextAction 下个节点流转
     * @param nextUserId:下一步候选人
     * @param comment:评论
     * @return 成功、失败
     */
    Boolean takeAndcompleteTask(String taskId,String nextAction,String nextUserId,String comment);


    /**
     * 根据流程id删除下面的所有的流程实例
     * @param processDefinitionId
     * @return 成功、失败
     */
    Boolean deleteProcessInstanceCascadeByDefinitionId(String processDefinitionId);


    /**
     * 根据任务ID 直接结束当前流程
     * @param taskId 任务ID
     * @return 成功、失败
     */
    Boolean endProcessInstance(String taskId);


    /**
     * 通过ID删除流程实例
     * @param processInstanceId
     * @return
     */
    Boolean deleteProcessInstanceById(String processInstanceId);



    /**获取下一步操作的名称
     * @param processId
     * @param taskName
     * @return
     */
    List<ComparableMap> getNextActions(int nodeId);
    
    /**仅仅启动流程
     * @param processDefineId
     */
    String startProcessInstanceOnly(String processDefineId);
    
    /**
     * @param instanceId
     * @return
     */
    String getTaskIdByInstanceId(String instanceId,String userId);
    
    /**
     * @param instanceId
     * @return
     */
    int getProcessIdByInstanceId(String instanceId);
    
    /**
     * 获取处理过的节点列表
     * @param instanceId
     * @return
     */
    List<Map<String,String>> getProcesseredNodes(String instanceId);
}
