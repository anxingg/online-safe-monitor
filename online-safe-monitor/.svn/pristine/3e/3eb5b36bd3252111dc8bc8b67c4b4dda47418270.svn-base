package cn.com.qytx.workflow.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import cn.com.qytx.cbb.util.StringUtil;
import cn.com.qytx.workflow.action.param.NodeParam;
import cn.com.qytx.workflow.constans.CandidateEnum;
import cn.com.qytx.workflow.constans.WorkflowConstans;
import cn.com.qytx.workflow.domain.HotNodeFormAttribute;
import cn.com.qytx.workflow.service.ProcessDesignerService;
import cn.com.qytx.workflow.service.WorkflowService;
import cn.com.qytx.workflow.service.impl.ComparableMap;

import com.google.gson.Gson;

/**
 * Created by izerui.com on 14-4-30.
 */
@SuppressWarnings("serial")
public class NodeAttributeManagerAction extends BasePageAction<NodeParam>
		implements WorkflowConstans {

	@Resource(name = "hotprocessDesignerService")
	private ProcessDesignerService processDesignerService;

//	@Resource(name = "userService")
//	private IUser userService;

	@Resource(name = "hotworkflowService")
	private WorkflowService workflowService;

	/**
	 * 功能：进入编辑候选人页面
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public String candidate() throws Exception {

		HotNodeFormAttribute node = processDesignerService.findOne(param
				.getNodeId());
		getRequest().setAttribute("node", node);

		// 放入userInfos , groupInfos , roleInfos
		for (CandidateEnum candidateEnum : candidate_list) {
			String candidate = (String) PropertyUtils.getProperty(node,
					candidateEnum.getParamName());
			if (StringUtils.isNotEmpty(candidate)) {
				// 判断兼容非正常格式数据
				if (candidate.endsWith(",")) {
					candidate = candidate.substring(0, candidate.length() - 1);
				}
				@SuppressWarnings("unchecked")
				List list = processDesignerService.findByHql(candidateEnum
						.getdClass(),
						candidateEnum.getHql().replace("?", candidate));
				getRequest().setAttribute(candidateEnum.getName(), list);
			}
		}
		return "candidate";
	}

	/**
	 * @return
	 */
	public String nodeProperties() {
		HotNodeFormAttribute node = processDesignerService.findOne(param
				.getNodeId());
		getRequest().setAttribute("node", node);
		return "nodeProperties";
	}

	/**
	 * 获取指定节点的下一步分支
	 * 
	 * @return
	 */
	public List<ComparableMap> getNextActions() {
		return workflowService.getNextActions(param.getNodeId());
	}

	/**
	 * 功能：修改流程节点属性设置
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public String update() {
		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		int index = 0;
		while (true) {
			String code = getRequest().getParameter("operationCode_" + index);
			String name = getRequest().getParameter("operationShow_" + index);
			String order = getRequest().getParameter("operationIndex_" + index);
			if (StringUtil.isEmpty(code)) {
				break;
			} else {
				Map<String, String> map = new HashMap<String, String>();
				map.put("actionCode", code);
				map.put("actionShow", name);
				map.put("actionIndex", order);
				maps.add(map);
			}
			index++;
		}
		Gson gson = new Gson();
		String operation = gson.toJson(maps);
		param.getNode().setOperateName(operation);
		processDesignerService.saveOupdate(param.getNode());
		return "node_edit";
	}

	/**
	 * 修改候选人信息
	 * 
	 * @return
	 */
	public String updateCandidate() {
		processDesignerService.saveOupdate(param.getNode());
		return "node_edit";
	}

	/**
	 * 获取指定节点的处理页面地址，参数processId,nodeName
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getOperationUrl() throws Exception {
		HotNodeFormAttribute nfa = processDesignerService.findByNodeName(
				param.getProcessId(), param.getNodeName());
		ajax(nfa.getOperateUrl());
		return null;
	}

}
