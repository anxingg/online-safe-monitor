/** 
 * @author 作者姓名  E-mail: email地址 
 * @version 创建时间：2015-9-1 上午11:16:04 
 * 类说明 
 */
package cn.com.wh.login.action;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.ModuleInfo;
import cn.com.qytx.platform.session.Constants;
import cn.com.wh.WHConstant;
import cn.com.wh.support.SessionSupport;
/**
 * @ClassName:     MenuAction.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         wangxj
 * @version        V1.0  
 * @Date           2015-9-1 上午11:16:04 
 */
public class MenuAction extends BaseActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 首页菜单或子系统菜单
	 */
	public List<ModuleInfo> showModuleList;
	/**
	 * 子系统名称
	 */
	public String  subSystem;
	
	public String getSubSystem() {
		return subSystem;
	}
	public void setSubSystem(String subSystem) {
		this.subSystem = subSystem;
	}
	/**
	 * 返回首页的主九宫格菜单
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String fetchOSMIndexMenu(){
		showModuleList= new ArrayList<ModuleInfo>();
		List<ModuleInfo> moduleList = (List<ModuleInfo>) this.getSessionSupport().getSession().getAttribute(Constants.CURRENT_LOGIN_MODULELIST);
		for(ModuleInfo moduleInfo : moduleList){
			if((moduleInfo.getModuleLevel()==1) && 
					(WHConstant.SYS_NAME.equals(moduleInfo.getSysName()))){
				if(moduleInfo.getModuleCode().equals("269")){//环保在线
					String url;
					if(moduleInfo.getUrl().indexOf("?")==-1){
						url=moduleInfo.getUrl()+"?_clientType=wap&token="+((SessionSupport)this.getSessionSupport()).getSSOToken();
					}
					else{
						url=moduleInfo.getUrl()+"&_clientType=wap&token="+((SessionSupport)this.getSessionSupport()).getSSOToken();
					}
					moduleInfo.setUrl(url);
				}
				showModuleList.add(moduleInfo);
			}
		}
		return "menu";
	}
	/**
	 * 返回某子系统菜单
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String fetchSubSystemMenu(){
		if(StringUtils.isEmpty(subSystem)){
			subSystem=WHConstant.MODULE_SYSTEMMANAGE;
		}
		String cacheKey="cache_fetchSubSystemMenu_"+subSystem;
		//由于这个逻辑复杂，且重用度很高，因此放入缓存中
		if(this.getSessionSupport().getSession().getAttribute(cacheKey)!=null)
			showModuleList=(List<ModuleInfo>)this.getSessionSupport().getSession().getAttribute(cacheKey);
		else{
			showModuleList= new ArrayList<ModuleInfo>();
			List<ModuleInfo> moduleList = (List<ModuleInfo>) this.getSessionSupport().getSession().getAttribute(Constants.CURRENT_LOGIN_MODULELIST);
			for(ModuleInfo moduleInfo : moduleList){
				//增加二级菜单以及他的子菜单，为了前台菜单的排序
				if((moduleInfo.getModuleLevel()==2)
						&&(subSystem.equals(moduleInfo.getSysName()))){
					showModuleList.add(moduleInfo);
					Integer parentId=moduleInfo.getModuleId();
					//添加他的子菜单
					for(ModuleInfo moduleInfo2 : moduleList){
						if(moduleInfo2.getParentId().equals(parentId)){
							showModuleList.add(moduleInfo2);
						}
					}
				}
			}
			this.getSessionSupport().getSession().setAttribute(cacheKey, showModuleList);
		}
		return "menu";
	}
	public List<ModuleInfo> getShowModuleList() {
		return showModuleList;
	}

	public void setShowModuleList(List<ModuleInfo> showModuleList) {
		this.showModuleList = showModuleList;
	}
	
}
