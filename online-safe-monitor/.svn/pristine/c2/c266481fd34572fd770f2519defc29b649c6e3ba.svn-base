package cn.com.qytx.workflow.action;

import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;

import org.jbpm.api.ProcessEngine;

import cn.com.qytx.workflow.action.param.ProcessAttributeParam;
import cn.com.qytx.workflow.constans.WorkflowConstans;
import cn.com.qytx.workflow.domain.HotProcessAttribute;
import cn.com.qytx.workflow.service.FormCategoryService;
import cn.com.qytx.workflow.service.ProcessAttributeService;

public class ProcessAtributeAction extends
		BasePageAction<ProcessAttributeParam> implements WorkflowConstans {
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String SUCCESS="success";
	
	@Resource(name = "hotprocessAttributeService")
	private transient  ProcessAttributeService processAttributeService;
	@Resource(name = "processEngine")
	private transient  ProcessEngine processEngine;
	@Resource(name = "hotformCategoryService")
	private transient  FormCategoryService formCategoryService;

	/**
	 * 功能：跳转到流程修改界面
	 */
	public String editProcess() {
		if (param.getProcessAttributeId() == null) {
			return "wizard";
		} else {
			getRequest().setAttribute(
					"processAttribute",
					processAttributeService.findOne(param
							.getProcessAttributeId()));
			return "edit_process";
		}
	}

	/**
	 * 功能：发布流程
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public String deploy() {
		processAttributeService.deploy(param.getProcessAttributeId());
		try {
			ajax(SUCCESS);
		} catch (Exception e) {
//			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 功能：启用指定流程
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public String start() throws Exception {
		processAttributeService.start(param.getProcessAttributeId());
		ajax(SUCCESS);
		return null;
	}

	/**
	 * 功能：停用指定流程
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public String stop() throws Exception {
		processAttributeService.stop(param.getProcessAttributeId());
		ajax(SUCCESS);
		return null;
	}

	/**
	 * 功能：转向创建流程定义页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String createDefine() throws Exception {
		getRequest().setAttribute(
				"formCategories",
				formCategoryService.findByTypeCompanyId(getLoginUser()
						.getCompanyId(), WORKFLOW_TYPE.getValue()));
		return "new_define";
	}

	/**
	 * 功能：保存流程定义属性
	 * 
	 * @param：无
	 * @return
	 * @throws
	 */
	public String saveProcess() throws Exception {
		processAttributeService.saveOrUpdate(param.getProcessAttribute());
		return "option_success";
	}

	/**
	 * 功能：修改流程定义
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public String update() {
		getRequest().setAttribute("processAttribute",
				processAttributeService.findOne(param.getProcessAttributeId()));
		getRequest().setAttribute(
				"formCategories",
				formCategoryService.findByTypeCompanyId(getLoginUser()
						.getCompanyId(), WORKFLOW_TYPE.getValue()));
		return "update";
	}

	/**
	 * 功能：删除流程定义
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public String deleteProcess() throws Exception {
		processAttributeService.delete(param.getProcessAttributeId(), true);
		ajax(SUCCESS);
		return null;
	}

	/**
	 * 功能：验证流程定义名称是否重复
	 * 
	 * @param
	 * @return success:重复；fail:不重复
	 * @throws
	 */
	public String checkProcessNameIsRepeat() throws Exception {
		boolean result = processAttributeService.checkProcessNameIsRepeat(
				URLDecoder.decode(param.getProcessName(), "UTF-8"),
				param.getProcessAttributeId());
		String str = "false";
		if (result) {
			str = SUCCESS;
		}
		ajax(str);
		return null;
	}

	/**
	 * 功能：判断是否可删除流程定义，如果状态是停用,未发布，可删除
	 * 
	 * @param
	 * @return:success 可删除；false不可删除
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public String isCanDelete() throws Exception {
		String state = "false";
		HotProcessAttribute pa = processAttributeService.findOne(param
				.getProcessAttributeId());
		// 未发布，可以删除
		if (pa.getProcessState().intValue() == HotProcessAttribute.NOT_DEPLOY
				.intValue()) {
			state = SUCCESS;
			// 停用 没有未完成的实例，可以删除
		} else if (pa.getProcessState().intValue() == HotProcessAttribute.STOP_STATE
				.intValue()) {
			List list = processEngine.getExecutionService()
					.createProcessInstanceQuery()
					.processDefinitionId(pa.getProcessDefineId()).list();
			if (list == null || list.size() == 0) {
				state = SUCCESS;
			}
		}
		ajax(state);
		return null;
	}

	/**
	 * 功能：检测 流程是否合法、节点权限和属性是否配置
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public String check() throws Exception {
		getRequest().setAttribute(
				"fileCheck",
				processAttributeService.checkJpdlXmslByXSD(param
						.getProcessAttributeId()));
		getRequest().setAttribute(
				"check",
				processAttributeService.checkIsSetCandidate(param
						.getProcessAttributeId()));
		return "checkResult";
	}

	/**
	 * 功能：预览流程定义
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public String view() throws Exception {
		String processView = processAttributeService.findOne(
				param.getProcessAttributeId()).getProcsssDefinByJSON();
		getRequest().setAttribute("jsonData",
				null == processView ? "false" : processView);
		return "view";
	}

}
