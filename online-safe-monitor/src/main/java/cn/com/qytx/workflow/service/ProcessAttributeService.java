package cn.com.qytx.workflow.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.com.qytx.platform.base.service.BaseService;
import cn.com.qytx.workflow.domain.HotNodeFormAttribute;
import cn.com.qytx.workflow.domain.HotProcessAttribute;

/**
 * Created by izerui.com on 14-4-29.
 */
public interface ProcessAttributeService extends BaseService<HotProcessAttribute> {
    /**
     * 功能：根绝公司ID查询所有流程
     * @param
     * @return
     * @throws
     */
    List<HotProcessAttribute> findAll(Integer companyId);
	/**
	 * 功能：启用流程
	 * @param
	 * @return
	 * @throws   
	 */
	void start(int processAttributeId);
    /**
	 * 功能：停用指定的流程
	 * @param
	 * @return
	 * @throws   
	 */
	void stop(int processAttributeId);
	/**
	 * 功能：根据流程定义ID查询流程定义
	 * @param，processId,流程定义ID
	 * @return
	 * @throws   
	 */
	
	/*public ProcessAttribute getProcessById(Integer processId);*/
	/**
	 * 功能：根据流程定义ID发布流程定义
	 * @param
	 * @return
	 * @throws   
	 */
	void deploy(int processAttributeId);	
	/**
	 * 功能：根据id删除流程定义
	 * @param processAttributeId
	 */
	/*public void delete(int processAttributeId);*/
	/**
	 * 功能：保存流程
	 * @param
	 * @return
	 * @throws   
	 */
	/*public void save(ProcessAttribute processAttribute);*/
	/**
	 * 功能：验证流程定义名称是否重复
	 * @param
	 * @return true:重复
	 * @throws   
	 */
	boolean checkProcessNameIsRepeat(String processName,Integer processAttributeId);
	/**
	 * 功能：根据ID检测流程定义文件的合法性
	 * @param
	 * @return
	 * @throws   
	 */
	boolean checkJpdlXmslByXSD(int processAttributeId);
	/**
	 * 功能：校验是否已设置经办权限
	 * @param
	 * @return
	 * @throws   
	 */
	Map<String,List<String>> checkIsSetCandidate(int processAttributeId);
	
	/**
	 * 
	 * 功能：根据流程定义id查询节点信息
	 * @param processAttributeId
	 * @return
	 */
	Set<HotNodeFormAttribute> findNodeFormsByProcessDefinitionId(String processDefinitionId);
}
