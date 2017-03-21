package cn.com.wh.util;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

/**
 * @ClassName:     SpringInfo.java
 * @Description:   加载spring配置文件
 * 
 * @author         wangxj
 * @version        V1.0  
 * @Date           2015-7-1 下午03:45:00 
 */
public class SpringInfo {
	public static WebApplicationContext SPRING_CONTEXT = null;

	/**
	 * 初始化springcontext
	 *
	 * @param servletContext
	 */
	public static void init(ServletContext servletContext) {
		SPRING_CONTEXT = (WebApplicationContext) servletContext
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	}

	/**
	 * 获取spring bean
	 *
	 * @param beanName
	 */
	public static Object getBean(String beanName) {
		 if (SPRING_CONTEXT == null) {
		 /* 非web方式测试使用 */
		 ApplicationContext context = new ClassPathXmlApplicationContext(
		 "myJunit.xml");
		 return context.getBean(beanName);
		 }
		return SpringInfo.SPRING_CONTEXT.getBean(beanName);
	}
}
