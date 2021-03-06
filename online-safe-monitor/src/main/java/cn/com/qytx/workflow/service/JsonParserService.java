package cn.com.qytx.workflow.service;

import java.io.Serializable;
import java.util.List;

import cn.com.qytx.workflow.jpdl.parsejson.NodeObject;


/**
 * 功能：格式化JSON格式的字符串
 * 版本: 1.0
 * 开发人员：贾永强
 * 创建日期: 2013-3-22 上午11:37:34 
 * 修改日期：2013-3-22 上午11:37:34 
 * 修改列表：
 */
public interface JsonParserService extends Serializable {
	
	/**
	 * 功能：解析从前台传送过来的JSON数据,解析成java对象
	 * @param
	 * @return
	 * @throws   
	 */
	List<NodeObject> parser(String json);
	
	/**
	 * 功能：根绝Java对象和ftl模板生成XML数据
	 * @param
	 * @return
	 * @throws   
	 */
	String generateJpdl(List<NodeObject> nodelist);
	
	/**
	 * 功能：根绝JSON数据生成XML数据
	 * @param
	 * @return
	 * @throws   
	 */
	String parseJsonToJpdl(String json);
	
	/**
	 * 功能：检测XML文件的合法性
	 * @param
	 * @return：true，检测合法，false，检测不合法
	 * @throws   
	 */
	boolean checkJpdlXmlByXSD(String jpdlXml);
	
	/**
	 * 功能：根据ID检测流程定义文件的合法性
	 * @param
	 * @return
	 * @throws   
	 */
	boolean checkJpdlXmslByXSD(int processAttributeId);
}
