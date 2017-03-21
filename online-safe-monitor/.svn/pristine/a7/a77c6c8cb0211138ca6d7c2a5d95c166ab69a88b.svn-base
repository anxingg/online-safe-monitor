package cn.com.wh.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import cn.com.wh.util.DataInitUtil;
import cn.com.wh.util.SpringInfo;

/**
 * @ClassName:     PortalStartListener.java
 * @Description:   静态数据工程启动加载类
 * 
 * @author         wangxj
 * @version        V1.0  
 * @Date           2015-7-1 下午02:09:44 
 */
public class PortalStartListener implements ServletContextListener{
	static Logger logger = Logger.getLogger(PortalStartListener.class);

	public void contextDestroyed(ServletContextEvent arg0) {}

	public void contextInitialized(ServletContextEvent arg0) {
		SpringInfo.init(arg0.getServletContext());
		logger.info("######################Spring启动##############################"+System.currentTimeMillis());
		
		DataInitUtil.flush();
		
		logger.info("######################Spring结束##############################"+System.currentTimeMillis());
	}
}
