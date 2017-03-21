package cn.com.qytx.workflow.jpdl.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.com.qytx.workflow.jpdl.parsejson.NodeObject;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


/**
 * 功能：ftl文件解析模板类
 * 版本: 1.0
 * 开发人员：贾永强
 * 创建日期: 2013-3-22 上午8:50:13 
 * 修改日期：2013-3-22 上午8:50:13 
 * 修改列表：
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractFreemarkerTemplate {

	//freemarker配置器
	private Configuration config = new Configuration();
	
	//xml模板  pmd插件修改
//	private String xmlTemplateStringPath ;
	
	//数据模型
	private Map root;

	
	/**
	 * 功能：抽象方法，获取模板文件路径
	 * @param
	 * @return：模板文件路径
	 * @throws   
	 */
	public abstract String getXmlTemplatePath();
	
	/**
	 * 功能：抽象方法，根绝节点对象，生成模板文件的数据模型
	 * @param:nodeObject，节点对象
	 * @return：ftl的数据模型
	 * @throws   
	 */
	public abstract Map generateRoot(NodeObject nodeObject);

	
	/**
	 * 功能：抽象方法，获取节点类型
	 * @param
	 * @return，节点类型
	 * @throws   
	 */
	public abstract String getNodeType();


	/**
	 * 功能：模板方法，调用抽象类，根据模板文件生成JPDL文件
	 * @param：nodelist，模板数据；
	 * @return:根据模板生成的jpdl文件
	 * @throws   
	 */
	public String execute(List<NodeObject> nodelist){
		String xmlTemplateStringPath ;
		try {
			StringBuilder resultString = new StringBuilder();
			xmlTemplateStringPath = getXmlTemplatePath();
			InputStream is = this.getClass().getResourceAsStream("/cn/com/qytx/workflow/jpdl/template/"+xmlTemplateStringPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuffer sb =new StringBuffer();
			String temp = br.readLine();
			while(temp!=null){
				sb.append(temp);
				temp = br.readLine();
			}
			br.close();
			for(Iterator iterator = nodelist.iterator(); iterator.hasNext();){
				NodeObject nodeObject = (NodeObject) iterator.next();
				if(nodeObject.getType().equals(getNodeType())){
					this.root 			   = generateRoot(nodeObject);
					StringReader reader= new StringReader(sb.toString());
				    Template template = new Template(xmlTemplateStringPath,reader,config);
					StringWriter stringWriter = new StringWriter();  
				//	BufferedWriter writer = new BufferedWriter(stringWriter);  
					template.setEncoding("UTF-8");  
					template.process(root,stringWriter);
					resultString.append(stringWriter.toString()+"\r\n");
				}
			}
			
			return resultString.toString();
		} catch (IOException e) {
//			e.printStackTrace();
		} catch (TemplateException e) {
//			e.printStackTrace();
		}
		return null;
	}
	
	
}
