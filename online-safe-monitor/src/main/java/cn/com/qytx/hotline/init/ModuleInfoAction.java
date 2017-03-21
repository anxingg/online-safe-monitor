/**
 * 
 */
package cn.com.qytx.hotline.init;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.ModuleInfo;

/**
 * 功能:
 * 版本: 1.0
 * 开发人员: 彭小东
 * 创建日期: 2014-8-28
 * 修改日期: 2014-8-28
 * 修改列表: 
 */
public class ModuleInfoAction extends BaseActionSupport {

	private static final long serialVersionUID = 1L;
	/**
     * log4j日志对象
     */
	 private final static MonitorLogger logger =new Log4jImpl(ModuleInfoAction.class);
	    /**
	     * 登陆后跳转
	     * @return
	     */
	    @SuppressWarnings("unchecked")
		public void sendOutModule()
	    {
	        try
	        {
	        	
	            //获取保存的菜单信息
	            List<ModuleInfo> moduleList = new ArrayList<ModuleInfo>();
	            if(this.getSession().getAttribute("moduleList")!=null)
	            {
	                moduleList=( List<ModuleInfo>)this.getSession().getAttribute("moduleList");
	            }
	            String menu=getModuleUrl(moduleList);
	            this.getSession().setAttribute("menu",menu);
	            super.ajax(menu);
	        }
	        catch (Exception ex)
	        {
	            logger.error("获取保存的菜单信息",ex);
	        }
	    }
	    /**

	     * @Title: 根据 list中的菜单转换成字符串
	     * @Description:
	     * @param @return
	     * @return   返回类型
	     * @throws
	     */
	    private String getModuleUrl(List<ModuleInfo> moduleList) {
	        HttpServletRequest request = this.getRequest();
	        String path = request.getContextPath();
	        String basePath = request.getScheme() + "://"
	                + request.getServerName() + ":" + request.getServerPort()
	                + path+"/" ;
	        StringBuffer showUrl=new StringBuffer();
	        showUrl.append(" <ul class=\"l_f_menu\">");
	        List<ModuleInfo> listFirst = getModuleListByPid(moduleList,0);
//	        int emint = 0;
	        for(ModuleInfo m :listFirst){ //遍历第一层
//	        	emint ++;
	            showUrl.append("<li>");
	            List<ModuleInfo> listSecondList = getModuleListByPid(moduleList,m.getModuleId());
	            if(listSecondList!=null&&listSecondList.size()>0){
	            	showUrl.append("<p  class=\"open\"><em class=\""+m.getIcon()+"\"></em>"+m.getModuleName()+"</p>");
	            	showUrl.append("<dl>");
	            }else{
	            	showUrl.append("<p><em class=\""+m.getIcon()+"\"></em><a href=\""+basePath+m.getUrl()+"\" target=\"iframe_main\" style=\"text-decoration: none;\">"+m.getModuleName()+"</a></p>");
	            }
	            
	            for(ModuleInfo m2 :listSecondList){ //遍历第2层

	                List<ModuleInfo> listThreeList = getModuleListByPid(moduleList,m2.getModuleId()); //获取第三层
	                
	                //如果有第三层
                	/*用于短信通菜单样式自动加载*/
	                String returnshwUrl = returnShouUrl(m2.getModuleName());
	                showUrl.append(returnshwUrl);
//                	if("新增".equals(m2.getModuleName())){
//                		 showUrl.append("<dt id=\"xzSMS\">");
//                	}else if("待发送".equals(m2.getModuleName())){
//                		showUrl.append("<dt id=\"dfsSMS\">");
//                	}else if("已发送".equals(m2.getModuleName())){
//                		showUrl.append("<dt id=\"yfsSMS\">");
//                	}else if("草稿".equals(m2.getModuleName())){
//                		showUrl.append("<dt id=\"cgSMS\">");
//                	}else if("已取消".equals(m2.getModuleName())){
//                		showUrl.append("<dt id=\"yqxSMS\">");
//                	}else{
//                		showUrl.append("<dt>");
//                	}
                	/*样式自动加载结束*/
                	
                    if(m2.getUrl()!=null&&!"".equals(m2.getUrl())){
                    	
                        showUrl.append("<span><a href=\""+basePath+m2.getUrl()+"\" title=\"\" target=\"iframe_main\">"+m2.getModuleName()+"</a></span>");
                    }else{
                        showUrl.append(m2.getModuleName());
                    }
                    showUrl.append("</dt>");
	                
//	                if(true){
//	                }
	                if(listThreeList!=null&&listThreeList.size()>0){
	                    for(ModuleInfo m3 :listThreeList){ //遍历第3层
	                        showUrl.append("<dd>");
	                        if(m3.getUrl()!=null&&!"".equals(m3.getUrl())){
	                            showUrl.append("<a href=\""+basePath+m3.getUrl()+"\" target=\"iframe_main\" title=\"\">"+m3.getModuleName()+"</a>");
	                        }else{
	                            showUrl.append("<a href='javascript:void(0);' target=\"iframe_main\" title=\"\">"+m3.getModuleName()+"</a>");
	                        }
	                        showUrl.append("</dd>");
	                    }
	                }
	               
	            }
	            if(listSecondList.size()>0){
	            	showUrl.append("</dl>");
	            }
	            showUrl.append("</li>");
	        }
	        showUrl.append("</ul>");
//	        System.out.println(showUrl.toString());

	        return showUrl.toString();
	    }
	    
	    private String returnShouUrl(String moduleName){
	    	StringBuffer showUrl = new StringBuffer();
	    	if("新增".equals(moduleName)){
       		 	showUrl.append("<dt id=\"xzSMS\">");
	       	}else if("待发送".equals(moduleName)){
	       		showUrl.append("<dt id=\"dfsSMS\">");
	       	}else if("已发送".equals(moduleName)){
	       		showUrl.append("<dt id=\"yfsSMS\">");
	       	}else if("草稿".equals(moduleName)){
	       		showUrl.append("<dt id=\"cgSMS\">");
	       	}else if("已取消".equals(moduleName)){
	       		showUrl.append("<dt id=\"yqxSMS\">");
	       	}else{
	       		showUrl.append("<dt>");
	       	}
	    	return showUrl.toString();
	    }

	    /**
	     * @Title: 查询ParentId = pid 的所有菜单
	     * @Description:
	     * @param @param i
	     * @param @return
	     * @return   返回类型
	     * @throws
	     */
	    private List<ModuleInfo> getModuleListByPid(final List<ModuleInfo> moduleList,int pid) {
	        List<ModuleInfo> list = new ArrayList<ModuleInfo>();
	        for(ModuleInfo mmModule :moduleList){
	            if(mmModule!=null){
	                int ppid = mmModule.getParentId();
	                if(ppid == pid){
	                    list.add(mmModule);
	                }
	            }
	        }
	        return list;
	    }
}
