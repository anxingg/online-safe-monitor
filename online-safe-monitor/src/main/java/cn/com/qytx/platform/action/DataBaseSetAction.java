package cn.com.qytx.platform.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.domain.DataBaseSet;
import cn.com.qytx.platform.impl.IPlatformParameterServiceImpl;
import cn.com.qytx.platform.service.IPlatformParameterService;

/**
 * @author Administrator/严正
 *完成对连接数据库的基本操作
 *
 */
public class DataBaseSetAction extends BaseActionSupport{
	
	  /**
	 * 描述含义
	 */
	private static final long serialVersionUID = -617216072235064027L;
	/**
     * 参数平台化service
     */
	@Autowired
   private transient IPlatformParameterService paramsService;
   
public IPlatformParameterService getParamsService() {
	return paramsService;
}




public void setParamsService(IPlatformParameterService paramsService) {
	this.paramsService = paramsService;
}




public void setParamsService(IPlatformParameterServiceImpl paramsService) {
	this.paramsService = paramsService;
}


/**
 * 数据库参数的实体
 */

	private DataBaseSet dbset;
	
	  public DataBaseSet getDbset() {
		return dbset;
	}



	public void setDbset(DataBaseSet dbset) {
		this.dbset = dbset;
	}





	/**
	 * @return 保存数据库参数的实体
	 * 操作config.properties，输入输出流操作properties文件
	 */
	public String dataBaseSetSave(){
		
		PrintWriter out = null;
		InputStream fis = null;
		FileOutputStream fos =null;
		try {
			out = this.getResponse().getWriter();
			//初始化输入流
//			String fileSt=DataBaseSetAction.class.getClassLoader().getResource("config.properties").getPath();
			String fileSt=Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
			File fileTem= new File(fileSt);
			  fis = new FileInputStream(fileTem);   
		   Properties properties = new Properties();
		   //加载
			properties.load(fis);
		//设置properties属性
			properties.setProperty("ip", dbset.getDbHostIp());
			properties.setProperty("port", dbset.getDbPort());
			properties.setProperty("database", dbset.getDbName());
			properties.setProperty("username", dbset.getDbUsername());
			properties.setProperty("password", dbset.getDbPsw());
			//关闭输入流 
			 fis.close();  
			// 文件输出流 
						File file=new File(fileSt);
						 fos = new FileOutputStream(file); 
						// 将Properties集合保存到流中 
						properties.store(fos, "Copyright (c) "); 
						fos.close();// 关闭流 
			
						out.print(1);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}finally{
			if(out!=null){
			    out.close();
			}
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage());
				}	
			}
			if(fos!=null){
				
				try {
					fos.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage());
				}
			}
		}
		   return null;
	  }
	
	/**
	 * @return  从config.properties取出内容并展示
	 */
	public String dataBaseSetView(){
		InputStream fis = null;
		try {
			//文件输入流
//			String fileSt=DataBaseSetAction.class.getClassLoader().getResource("config.properties").getPath();
			String fileSt=Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
			File file= new File(fileSt);
			  fis = new FileInputStream(file);   

			Properties properties = new Properties();
			//加载文件
				properties.load(fis);
			//取出属性
			String ip=	properties.getProperty("ip");
			String port=	properties.getProperty("port");
			String database=	properties.getProperty("database");
			String username=	properties.getProperty("username");
			String password=	properties.getProperty("password");
			DataBaseSet dbs=new DataBaseSet();
			dbs.setDbHostIp(ip);
			dbs.setDbName(database);
			dbs.setDbPort(port);
			dbs.setDbPsw(password);
			dbs.setDbUsername(username);
			
			this.getRequest().setAttribute("dbset", dbs);
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			}finally{
				if(fis!=null){
					try {
						fis.close();
					} catch (IOException e) {
						LOGGER.error(e.getMessage());
					}
				}
			}
		
		return SUCCESS;
	}
	
	public String testMyParm(){
		ApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getSession().getServletContext());
		IPlatformParameterService paramsService=	(IPlatformParameterService) ac1.getBean("parmsService");
		DataBaseSet dbs=new DataBaseSet();
		dbs.setParItems("cn.com.qytx.platform.domain.DataBaseSet");
		dbs.setParDescribe("描述");
		dbs.setDbHostIp("10.10.10.10");
		dbs.setDbName("name"); 
		dbs.setDbPort("8080");
		dbs.setDbPsw("777777");
		dbs.setDbUsername("user");
		Object o=dbs;
		paramsService.saveObj(o);
		
//	Object otem1=	paramsService.findByParItems("cn.com.qytx.platform.domain.DataBaseSet");
		 
//		System.out.println(otem1);
		
//	List<?> list=	paramsService.	getAllPar();
//		System.out.println(list.size());
		return SUCCESS;
	}
	
	
}
