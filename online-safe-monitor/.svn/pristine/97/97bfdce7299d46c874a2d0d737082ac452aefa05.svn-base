package cn.com.qytx.workflow.service.impl;

import java.io.Serializable;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.XMLErrorHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.workflow.dao.HotProcessAttributeDao;
import cn.com.qytx.workflow.domain.HotProcessAttribute;
import cn.com.qytx.workflow.jpdl.impl.BeginTaskGenerateImpl;
import cn.com.qytx.workflow.jpdl.impl.DecisionGenerateImpl;
import cn.com.qytx.workflow.jpdl.impl.FooterGenerateImpl;
import cn.com.qytx.workflow.jpdl.impl.ForkGenerateImpl;
import cn.com.qytx.workflow.jpdl.impl.HeaderGenerateImpl;
import cn.com.qytx.workflow.jpdl.impl.JoinGenerateImpl;
import cn.com.qytx.workflow.jpdl.impl.MultiSignGenerateImpl;
import cn.com.qytx.workflow.jpdl.impl.TaskGenerateImpl;
import cn.com.qytx.workflow.jpdl.parsejson.NodeObject;
import cn.com.qytx.workflow.jpdl.parsejson.ParseResult;
import cn.com.qytx.workflow.service.JsonParserService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 * 功能：JSON格式转为XML格式的JPDL解析器实现
 * 版本: 1.0
 * 开发人员：贾永强
 * 创建日期: 2013-3-21 下午5:27:14 
 * 修改日期：2013-3-21 下午5:27:14 
 * 修改列表：
 */
@Service("hotjsonParserService")
@Transactional
public class JsonParserServiceImpl implements JsonParserService,Serializable {
	
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -889431005572619263L;

	/**
     * log4j日志对象
     */
    @SuppressWarnings("unused")
    private final static MonitorLogger logger =new Log4jImpl(JsonParserServiceImpl.class);

	@Resource(name="hotprocessAttributeDao")
	private HotProcessAttributeDao processAttributeDao;


	/**
	 * 功能：将JSON格式的字符串转为节点对象列表
	 * @param：json,json格式的字符串，浏览器定义的JSON格式的JPDL
	 * @return：节点对象列表
	 * @throws   
	 */
	@Override
	public List<NodeObject> parser(String json) {
		if(json==null || json.equals("")){
			return new ArrayList<NodeObject>();
		}
		Gson gson = new Gson();
		Type type = new TypeToken<ParseResult>(){}.getType();
		ParseResult parser = gson.fromJson(json, type);
		return parser.getJpdlNode();
	}

	/**
	 * 功能：根据节点对象列表构建xml格式的JPDL
	 * @param：nodelist,节点对象列表
	 * @return：xml格式的字符串
	 * @throws   
	 */
	@Override
	public String generateJpdl(List<NodeObject> nodelist) {
		StringBuffer sbBuffer = new StringBuffer();
		//从Header开始，footer结束，其它节点的顺序 无所谓。
		sbBuffer.append(new HeaderGenerateImpl().execute(nodelist));
		sbBuffer.append(new BeginTaskGenerateImpl().execute(nodelist));
		sbBuffer.append(new DecisionGenerateImpl().execute(nodelist));
		sbBuffer.append(new ForkGenerateImpl().execute(nodelist));
		sbBuffer.append(new JoinGenerateImpl().execute(nodelist));
		sbBuffer.append(new TaskGenerateImpl().execute(nodelist));
		sbBuffer.append(new MultiSignGenerateImpl().execute(nodelist));
		sbBuffer.append(new FooterGenerateImpl().execute(nodelist));
		//System.out.println(sbBuffer.toString());
		return sbBuffer.toString();
	}


	/**
	 * 功能：将json格式的字符串转为xml格式的字符串
	 * @param：json，json格式的字符串
	 * @return：xml格式的字符串
	 * @throws   
	 */
	@Override
	public String parseJsonToJpdl(String json) {
		return generateJpdl(parser(json));
	}


	/**
	 * 功能：检测xml格式的JPDL文件格式是否正确
	 * @param，jpdlxml,xml格式的JPDL定义
	 * @return，true,格式正确，反之，格式错误
	 * @throws   
	 */
	@Override
	public boolean checkJpdlXmlByXSD(String jpdlXml) {
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
//        	 logger.equals(ex);
         } catch (ParserConfigurationException ex) { 
//        	 logger.equals(ex);
         } catch (DocumentException ex) { 
//        	 logger.equals(ex);
         }   catch(Exception e){
//        	 logger.equals(e);
         }      
        return false;
	}

	/**
	 * 功能：检测xml格式的JPDL文件格式是否正确
	 * @param，processAttributeId,流程定义ID
	 * @return，true,格式正确，反之，格式错误
	 * @throws   
	 */
	@Override
	public boolean checkJpdlXmslByXSD(int processAttributeId) {
		HotProcessAttribute pa = processAttributeDao.findOne(processAttributeId);
		boolean result = checkJpdlXmlByXSD(pa.getProcessDefineByXML());
		return result;
	}
	

}
