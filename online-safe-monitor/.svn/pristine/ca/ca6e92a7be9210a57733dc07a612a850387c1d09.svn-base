package cn.com.qytx.workflow.service.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;

import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.utils.spring.SpringUtil;
import cn.com.qytx.workflow.cfg.WorkflowGlobalCfg;
import cn.com.qytx.workflow.domain.HotNodeFormAttribute;
import cn.com.qytx.workflow.service.ProcessDesignerService;
import cn.com.qytx.workflow.service.WorkflowService;
import cn.com.qytx.workflow.service.impl.ComparableMap;

/**获取下一步操作列表
 * @author jiayongqiang
 *
 */
public class NextActionTag extends BaseTag {

	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -2807654914410184653L;
	/**
	 * log4j日志
	 */
	 private final static MonitorLogger logger =new Log4jImpl(NextActionTag.class);
	
	
	@Override
	public int doStartTag() throws JspException {
		ProcessDesignerService service = SpringUtil.getBean(ProcessDesignerService.class);
		WorkflowService workflowService = SpringUtil.getBean(WorkflowService.class);
		HotNodeFormAttribute hnfa = service.findByNodeName(processId, nodeName);
		List<ComparableMap> list = workflowService.getNextActions(hnfa.getId());
		JspWriter out=pageContext.getOut();

		for(int i=0; i<list.size(); i++){
			ComparableMap map = list.get(i);
			try {
				String checked = "";
				if(i == 0){
					checked = "checked";
				}
				out.print("<label class='radio'><input type='radio' name='nextAction' "+checked+" value='"+map.get("actionCode")+"'  show='"+map.get("actionShow")+"'/>"+map.get("actionShow")+"</label>");
			} catch (IOException e) {
				logger.error("工单处理获取下一步操作错误", e);
			}
		}
		if(processId!=WorkflowGlobalCfg.ON_VOICE_PROCESSID && "发起流程".equals(nodeName)){
			try {
				out.println("<label class='radio'><input type='radio'  name='nextAction' value='-1'/>"+hnfa.getState()+"</label>");
			} catch (IOException e) {
			    logger.error("工单处理获取下一步操作错误", e);
			}
		}
		return super.doStartTag();
	}

}
