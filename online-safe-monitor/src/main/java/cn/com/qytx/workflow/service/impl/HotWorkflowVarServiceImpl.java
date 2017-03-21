package cn.com.qytx.workflow.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.workflow.dao.HotWorkflowVarDao;
import cn.com.qytx.workflow.domain.HotWorkflowVar;
import cn.com.qytx.workflow.service.HotWorkflowVarService;

/**
 * @author jiayongqiang
 *
 */
/**
 * @author jiayongqiang
 *
 */
@Service
@Transactional
public class HotWorkflowVarServiceImpl extends BaseServiceImpl<HotWorkflowVar> implements
		HotWorkflowVarService {

	@Resource
	private HotWorkflowVarDao hotWorkflowVarDao;
	
	@Override
	public HotWorkflowVar findByInstanceId(String instanceId) {
		// TODO Auto-generated method stub
		return hotWorkflowVarDao.findByInstanceId(instanceId);
	}

}
