package cn.com.qytx.workflow.action;

import java.util.List;

import javax.annotation.Resource;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.workflow.constans.WorkflowConstans;
import cn.com.qytx.workflow.domain.HotFormCategory;
import cn.com.qytx.workflow.domain.HotProcessAttribute;
import cn.com.qytx.workflow.service.FormCategoryService;
import cn.com.qytx.workflow.service.ProcessAttributeService;

/**
 * Created by izerui.com on 14-4-28.
 */
@SuppressWarnings("serial")
public class FormCategoryAction extends BaseActionSupport implements
		WorkflowConstans {

	@Resource(name = "hotformCategoryService")
	private FormCategoryService formCategoryService;

	@Resource(name = "hotprocessAttributeService")
	private ProcessAttributeService processAttributeService;

	@Override
	/**
	 * 点击流程管理，进入主页面
	 */
	public String execute() throws Exception {

		UserInfo user = getLoginUser();

		List<HotFormCategory> formCategories = formCategoryService
//				.findByTypeCompanyId(user.getCompanyId(),
				  .findByTypeCompanyId(null,
						WORKFLOW_TYPE.getValue());

		// 获取所有流程定义
		List<HotProcessAttribute> processAttributes = processAttributeService
				.findAll(null);

		for (HotFormCategory formCategory : formCategories) {
			formCategory.addProcessAttributeList(processAttributes);
		}

		getRequest().setAttribute("procssCategorys", formCategories);
		return SUCCESS;
	}

}
