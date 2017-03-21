package cn.com.qytx.workflow.dao;

import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.workflow.domain.HotWorkflowVar;

@Repository("hotWorkflowVarDao")
public class HotWorkflowVarDao extends BaseDao<HotWorkflowVar, Integer> {
	
	/**
	 * 根据流程实例查找对象
	 * @param instanceId
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public HotWorkflowVar findByInstanceId(final String instanceId){
		//String hql = "from HotWorkflowVar where instanceId = ?";
		return super.findUnique("from HotWorkflowVar where instanceId = ?", instanceId);
	}
	
	@SuppressWarnings("deprecation")
	public void deleteByInstanceId(final String instanceId){
		//String hql = "delete from HotWorkflowVar where instanceId = ?";
		super.bulkDelete("delete from HotWorkflowVar where instanceId = ?", instanceId);
	}
}
