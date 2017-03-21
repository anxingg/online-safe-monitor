package cn.com.qytx.workflow.action;

import javax.annotation.Resource;

import cn.com.qytx.workflow.action.param.ProcessDesignerParam;
import cn.com.qytx.workflow.constans.WorkflowConstans;
import cn.com.qytx.workflow.domain.HotProcessAttribute;
import cn.com.qytx.workflow.service.ProcessAttributeService;
import cn.com.qytx.workflow.service.ProcessDesignerService;

/**
 * Created by izerui.com on 14-4-29.
 */
@SuppressWarnings("serial")
public class ProcessDesignerAction extends BasePageAction<ProcessDesignerParam>
		implements WorkflowConstans {

	@Resource(name = "hotprocessDesignerService")
	private ProcessDesignerService processDesignerService;

	@Resource(name = "hotprocessAttributeService")
	private ProcessAttributeService processAttributeService;


	/**
	 * 转向流程设计页面
	 * 
	 * @return
	 */
	public String define() {

		HotProcessAttribute processAttribute = processAttributeService
				.findOne(param.getProcessAttributeId());

		getRequest().setAttribute("process", processAttribute);
		return "myflow";
	}

	/**
	 * 功能：解析JSON格式的JPDL定义，解析成XML格式的JPDL定义
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public String parseJsonToJpdl() throws Exception {

		processDesignerService.updateProcessAttributeByJsonData(
				param.getProcessAttributeId(), param.getJsonTypeJpdl());
		ajax("{states:\"success\"}");
		return null;
	}

	/**
	 * 
	 * 功能：判断流程是否已经设好
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public String isDefine() throws Exception {
		HotProcessAttribute attribute = processAttributeService.findOne(param
				.getProcessAttributeId());
		if (attribute != null && attribute.getProcsssDefinByJSON() != null) {
			ajax("success");
		} else {
			ajax("error");
		}

		return null;
	}

	/**
	 * 功能：进入节点属性编辑
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public String nodeEdit() {

		HotProcessAttribute attribute = processAttributeService.findOne(param
				.getProcessAttributeId());

		getRequest().setAttribute("processAttribute", attribute);

		return "node_edit";
	}

}
