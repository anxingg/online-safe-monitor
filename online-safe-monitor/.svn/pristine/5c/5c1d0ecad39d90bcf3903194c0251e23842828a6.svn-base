package cn.com.qytx.configure.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.domain.BlacklistFunction;
import cn.com.qytx.platform.domain.IvrAndACDSet;
import cn.com.qytx.platform.domain.OutcallFunction;
import cn.com.qytx.platform.domain.SeatFunction;
import cn.com.qytx.platform.domain.SystemBasisSet;
import cn.com.qytx.platform.service.IPlatformParameterService;

/**
 * 功能: 系统启动时初始化数据
 * 版本: 1.0
 * 开发人员: 李华伟
 * 创建日期: 2014-4-2
 * 修改日期: 2014-4-2
 * 修改列表:
 */
public class InitializationListener implements ServletContextListener
{


    /**
     * log4j日志对象
     */
    private final static MonitorLogger logger =new Log4jImpl(InitializationListener.class);
 
    @Override
    public void contextDestroyed(ServletContextEvent arg0)
    {

    }

    /**
     * 监听器初始化, 加载
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0)
    {
        try
        {
            logger.info("contextInitialized init.");
            WebApplicationContext springContext = WebApplicationContextUtils
                   .getWebApplicationContext(arg0.getServletContext());                              
            IPlatformParameterService ips = (IPlatformParameterService)springContext.getBean("platformParameter");
            ServletContext hsr = arg0.getServletContext();
            
            List<Object> list = ips.getAllPar();
            if (null != list && !list.isEmpty())
            {
                for (Object obj : list)
                {
                    // 加载坐席功能配置
                    if (obj instanceof SeatFunction)
                    {
                        SeatFunction sf = (SeatFunction)obj;
                        hsr.setAttribute("seatFunction", sf);
                    }
                    
                    // 加载IVR和ACD功能 
                    else if (obj instanceof IvrAndACDSet)
                    {
                        IvrAndACDSet ivr = (IvrAndACDSet)obj;
                        hsr.setAttribute("ivrAndACDSet", ivr);
                    }
                    
                    // 加载外呼功能设置
                    else if (obj instanceof OutcallFunction)
                    {
                        OutcallFunction outcallFunction = (OutcallFunction)obj;
                        hsr.setAttribute("outcallFunction", outcallFunction);
                    }
                    
                    // 加载系统基础设置设置
                    else if (obj instanceof SystemBasisSet)
                    {
                        SystemBasisSet systemBasisSet = (SystemBasisSet)obj;
                        hsr.setAttribute("systemBasisSet", systemBasisSet);
                    }
                    //加载黑名单设置
                    else if(obj instanceof BlacklistFunction){
                    	BlacklistFunction blacklistFunction = (BlacklistFunction) obj;
                    	hsr.setAttribute("blacklistFunction", blacklistFunction);
                    }
                    
                }
            }
            logger.info("contextInitialized end.");
        }
        catch (Exception ex)
        {
            logger.error("contextInitialized error.", ex);
        }
    }

}
