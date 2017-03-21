package cn.com.qytx.platform.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.impl.IPlatformParameterServiceImpl;


public class PlatformParameterFactory {
	
    private final static MonitorLogger logger =new Log4jImpl(PlatformParameterFactory.class);
	
	@Autowired
	private IPlatformParameterServiceImpl parmsService;
	
	private PlatformParameterFactory(){
		
	} 
	 private final static PlatformParameterFactory pf=new PlatformParameterFactory();

	public static PlatformParameterFactory getFactoryInstance(){
		return  pf;
	}

	 
	@SuppressWarnings("unused")
	private Map<String ,Object> getAllParMap(){
		Map<String, Object> parsMap=null;
		 try {
			
		parsMap=new HashMap<String, Object>();
		List<Object>  objList=parmsService.getAllPar();
		
		if(objList!=null&&objList.size()>0){
			
			for(Object oj:objList){
				
				String className=oj.getClass().getName();
			//转义class名称将"."替换成"-"	
				className=className.replace(".", "-");
				parsMap.put(className, oj);
				
			}
			return  parsMap;	
			
		}
		
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		

			return  parsMap;	
	}
	
	
	
	
	
}
