package cn.com.qytx.workflow.service.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;

import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.utils.spring.SpringUtil;
import cn.com.qytx.workflow.domain.HotNodeFormAttribute;
import cn.com.qytx.workflow.service.ProcessDesignerService;

/**获取指定节点的办理页面
 * @author jiayongqiang
 *
 */
public class ProcessUrl extends BaseTag {
	
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -8659358565138883455L;
	/**
	 * log4j日志
	 */
	 private final static MonitorLogger logger =new Log4jImpl(ProcessUrl.class);
	
	@Override
	public int doStartTag() throws JspException {
		ProcessDesignerService service = SpringUtil.getBean(ProcessDesignerService.class);
//		WorkflowService workflowService = SpringUtil.getBean(WorkflowService.class);
		HotNodeFormAttribute hnfa = service.findByNodeName(processId, nodeName);
		JspWriter out=pageContext.getOut();
		try {
			out.write(hnfa.getOperateUrl());
		} catch (IOException e) {
		    logger.error("工单处理获取指定节点的办理页面错误", e);
			
		}
		return super.doStartTag();
	}

	
}
