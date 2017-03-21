package cn.com.qytx.workflow.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.XMLErrorHandler;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.workflow.constans.WorkflowConstans;
import cn.com.qytx.workflow.dao.HotProcessAttributeDao;
import cn.com.qytx.workflow.domain.HotNodeFormAttribute;
import cn.com.qytx.workflow.domain.HotProcessAttribute;
import cn.com.qytx.workflow.service.ProcessAttributeService;

/**
 * Created by izerui.com on 14-4-29.
 */
@Service("hotprocessAttributeService")
@Transactional
public class ProcessAttributeServiceImpl extends BaseServiceImpl<HotProcessAttribute>
		implements ProcessAttributeService {
	/**
	 * log4j日志
	 */
    private final static MonitorLogger logger =new Log4jImpl(ProcessAttributeServiceImpl.class);

	@Resource(name="hotprocessAttributeDao")
	private HotProcessAttributeDao processAttributeDao;


	@Override
	public List<HotProcessAttribute> findAll(Integer companyId) {
		return processAttributeDao
				.getAllProcessAttributesByCompanyId(companyId);
	}

	@Resource
	private transient ProcessEngine processEngine;

	/**
	 * 功能：启用
	 * 
	 * @param processAttributeId
	 */
	@Override
	public void start(int processAttributeId) {
		HotProcessAttribute pa = processAttributeDao.findOne(processAttributeId);
		pa.setProcessState(HotProcessAttribute.DEPLOY_STATE);
		processAttributeDao.saveOrUpdate(pa);
	}

	/**
	 * 功能：停止
	 * 
	 * @param processAttributeId
	 */
	@Override
	public void stop(int processAttributeId) {
		HotProcessAttribute pa = processAttributeDao.findOne(processAttributeId);
		pa.setProcessState(HotProcessAttribute.STOP_STATE);
		processAttributeDao.saveOrUpdate(pa);

	}

	/**
	 * 功能：根据流程定义ID查询流程定义
	 * 
	 * @param processId
	 * @return
	 */
	/*@Override
	public ProcessAttribute getProcessById(Integer processId) {
		return processAttributeDao.findById(ProcessAttribute.class, processId);
	}
*/
	/**
	 * 功能：发布流程定义
	 * 
	 * @param processAttributeId
	 */
	@Override
	public void deploy(int processAttributeId) {
		HotProcessAttribute pa = processAttributeDao.findOne(processAttributeId);
		String oldDefineId = pa.getProcessDefineId();
		if (oldDefineId != null && !oldDefineId.equals("")) {
			try {
				ProcessDefinition oldDefine = processEngine
						.getRepositoryService().createProcessDefinitionQuery()
						.processDefinitionId(oldDefineId).uniqueResult();
				if (oldDefine != null) {
					String oldDefploymentId = oldDefine.getDeploymentId();
					processEngine.getRepositoryService()
							.deleteDeploymentCascade(oldDefploymentId);
				}
			} catch (Exception e) {
				logger.error("工单处理发布流程处理异常",e);
			}
		}
		String xml = pa.getProcessDefineByXML();
		File tempFile = new File(pa.getProcessName() + ".jpdl.xml");
		FileWriterWithEncoding fw;
		try {
			fw = new FileWriterWithEncoding(tempFile, "GBK", false);
			fw.write(xml);
			fw.flush();
			fw.close();
		} catch (IOException e) {
		    logger.error("工单处理生成"+pa.getProcessName() + ".jpdl.xml异常",e);
		}
		String deployId = processEngine.getRepositoryService()
				.createDeployment().addResourceFromFile(tempFile).deploy();
		ProcessDefinition define = processEngine.getRepositoryService()
				.createProcessDefinitionQuery().deploymentId(deployId)
				.uniqueResult();
		pa.setProcessDefineId(define.getId());
		// 如果未发布，则修改状态为发布
		if (pa.getProcessState().intValue() == HotProcessAttribute.NOT_DEPLOY) {
			pa.setProcessState(HotProcessAttribute.DEPLOY_STATE);
		};
		boolean result = tempFile.delete();
		if (!result) {
			return;
		}
		processAttributeDao.saveOrUpdate(pa);

	}
    /**
     * 
     * 功能：根据id 删除流程定义
     * @param processAttributeId
     */
	/*@Override
	public void delete(int processAttributeId) {
		processAttributeDao.delete(processAttributeId);
	}*/
	/**
	 * 功能：保存流程
	 * @param
	 * @return
	 * @throws   
	 */
	/*@Override
	public void save(ProcessAttribute processAttribute) {
		processAttributeDao.save(processAttribute);
	}*/
	/**
	 * 
	 * 功能：验证流程名是否重复
	 * @param processName
	 * @param processAttributeId
	 * @return
	 */
	@Override
	public boolean checkProcessNameIsRepeat(String processName,Integer processAttributeId) {
		return processAttributeDao.checkProcessNameIsRepeat(processName,processAttributeId);
	}
	
	/**
	 * 功能：根据ID检测流程定义文件的合法性
	 * @param
	 * @return
	 * @throws   
	 */
	@Override
	public boolean checkJpdlXmslByXSD(int processAttributeId) {
		HotProcessAttribute pa = processAttributeDao.findOne(processAttributeId);
		String jpdlXml=pa.getProcessDefineByXML();
		if (null==jpdlXml||"".endsWith(jpdlXml)) {
			return false;
		}
		String xsdFileName = this.getClass().getResource("/jpdl.xsd").getFile();
        try { 
            //创建默认的XML错误处理器 
             XMLErrorHandler errorHandler = new XMLErrorHandler(); 
            //获取基于 SAX 的解析器的实例 
             SAXParserFactory factory = SAXParserFactory.newInstance(); 
            //解析器在解析时验证 XML 内容。 
             factory.setValidating(true); 
            //指定由此代码生成的解析器将提供对 XML 名称空间的支持。 
             factory.setNamespaceAware(true); 
            //使用当前配置的工厂参数创建 SAXParser 的一个新实例。 
             SAXParser parser = factory.newSAXParser(); 
            //创建一个读取工具 
             SAXReader xmlReader = new SAXReader(); 
            //获取要校验xml文档实例 
             StringReader sr = new StringReader(jpdlXml);
             Document xmlDocument = (Document) xmlReader.read(sr);
             
            //设置 XMLReader 的基础实现中的特定属性。核心功能和属性列表可以在 [url]http://sax.sourceforge.net/?selected=get-set[/url] 中找到。 
             parser.setProperty( 
            		 "http://java.sun.com/xml/jaxp/properties/schemaLanguage", 
            		 "http://www.w3.org/2001/XMLSchema"); 
             parser.setProperty( 
            		 "http://java.sun.com/xml/jaxp/properties/schemaSource", 
            		 "file:" + xsdFileName); 
            //创建一个SAXValidator校验工具，并设置校验工具的属性 
             SAXValidator validator = new SAXValidator(parser.getXMLReader()); 
            //设置校验工具的错误处理器，当发生错误时，可以从处理器对象中得到错误信息。 
             validator.setErrorHandler(errorHandler); 
            //校验 
             validator.validate(xmlDocument); 

           //  XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint()); 
            //如果错误信息不为空，说明校验失败，打印错误信息 
            if (errorHandler.getErrors().hasContent()) { 
        	  XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint()); 
        	  writer.write(errorHandler.getErrors()); 
            	return false;
             } else { 
                return true;
             } 
         } catch (SAXException ex) { 
        	 logger.error("根据ID检测流程定义文件的合法性",ex);
         } catch (ParserConfigurationException ex) { 
             logger.error("根据ID检测流程定义文件的合法性",ex);
         } catch (DocumentException ex) { 
             logger.error("根据ID检测流程定义文件的合法性",ex);
         }   catch(Exception e){
             logger.error("根据ID检测流程定义文件的合法性",e);
         }      
        return false;
	}
	/**
	 * 功能：校验是否已设置经办权限
	 * @param
	 * @return
	 * @throws   
	 */
	@Override
	public Map<String,List<String>> checkIsSetCandidate(int processAttributeId) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> auth = null;
		List<String> value = null;
		HotProcessAttribute pa = processAttributeDao.findOne(processAttributeId);
		if(pa==null){
			return null;
		}
		Set<HotNodeFormAttribute> nodeset = pa.getNodeSet();
		for(Iterator<HotNodeFormAttribute> it = nodeset.iterator(); it.hasNext();){
			HotNodeFormAttribute nfa = it.next();
			if((!nfa.getNodeType().equals("task")) &&(!nfa.getNodeType().equals(WorkflowConstans.NODE_TYPE_MULTISIGN)) &&(!nfa.getNodeType().equals(WorkflowConstans.NODE_TYPE_START)) ){
				continue;
			}
			String str1 = nfa.getCandidate();
			String str2 = nfa.getRoles();
			String str3 = nfa.getDepts();
			String str4 = nfa.getOperateName();
			String str5 = nfa.getOperateUrl();
			if((str1 == null||"".equals(str1)) && (str2 == null||"".equals(str2)) && (str3 == null||"".equals(str3))){
				if(auth==null){
					auth = new ArrayList<String>();
				}
				auth.add(nfa.getNodeName());
			}
			if (null == str4||"".equals(str4)||null == str5||"".equals(str5)) {
				if (value==null) {
					value = new ArrayList<String>();
				}
				value.add(nfa.getNodeName());
			}
		}
		map.put("auth", auth);
		map.put("value", value);
		return map;
	}


	@Override
	public Set<HotNodeFormAttribute> findNodeFormsByProcessDefinitionId(String processDefinitionId){
		HotProcessAttribute attribute = processAttributeDao.getProcessAttributeByDefineId(processDefinitionId);
		return attribute.getNodeSet();
	}
}
