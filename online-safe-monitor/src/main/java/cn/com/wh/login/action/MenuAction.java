/** 
 * @author 作者姓名  E-mail: email地址 
 * @version 创建时间：2015-9-1 上午11:16:04 
 * 类说明 
 */
package cn.com.wh.login.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.ModuleInfo;
import cn.com.qytx.platform.org.domain.RoleModule;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.wh.util.DataInitUtil;

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
	
	public List<ModuleInfo> showModuleList;
	
	public static int zhroleid = 3;
	public static int qyroleid = 2;
	
	public String fetchMenu(){
		UserInfo info =getLoginUser();
//		List<ModuleInfo> allModuleInfoList = DataInitUtil.moduleInfoList;
		showModuleList = new ArrayList<ModuleInfo>();
		Map<Integer,RoleModule> roleModuleMap_zh = DataInitUtil.roleModuleMap_zh;
		Map<Integer,RoleModule> roleModuleMap_zhc = DataInitUtil.roleModuleMap_zhc;
		Map<Integer,RoleModule> roleModuleMap_qy = DataInitUtil.roleModuleMap_qy;
		
		List<ModuleInfo> sortList = DataInitUtil.sortList;
		
//		List<ModuleInfo> showSecModeleList_3 = new  ArrayList<ModuleInfo>();
//		List<ModuleInfo> showSecModeleList_2 = new  ArrayList<ModuleInfo>();
		int whroletype = null==this.getSession().getAttribute("whroletype")?2:(Integer)this.getSession().getAttribute("whroletype");
		int sysType = null==this.getSession().getAttribute("sysType")?1:(Integer)this.getSession().getAttribute("sysType");
//		int allModuleInfoListsize = allModuleInfoList.size();
//		for(int n=0;n<allModuleInfoListsize;n++){
//			ModuleInfo moduleInfo = allModuleInfoList.get(n);
//			if(2==moduleInfo.getModuleLevel()&&2==Integer.parseInt(moduleInfo.getModuleCode())){//getModuleCode 1 企业   2政府
//				showSecModeleList_3.add(moduleInfo);
//			}else if(2==moduleInfo.getModuleLevel()&&1==Integer.parseInt(moduleInfo.getModuleCode())){
//				showSecModeleList_2.add(moduleInfo);
//			}
//		}
		
		
		int sortListsize = sortList.size();
		
		
		for(int k=0;k<sortListsize;k++){
			ModuleInfo _moduleInfo = sortList.get(k);
			int key = _moduleInfo.getModuleId();
			if(whroletype==1){//政府端
				if (sysType==0) {//只显示公告模块
					if (_moduleInfo.getModuleName().startsWith("公告")) {
						showModuleList.add(_moduleInfo);
					}
				}else {//不显示公告模块
					if(roleModuleMap_zh.containsKey(key)){
						if (_moduleInfo.getModuleName().equals("操作日志")) {
							if (info.getIsDefault()==0) {
								showModuleList.add(_moduleInfo);
							}
						}else if (_moduleInfo.getModuleName().startsWith("公告")) {
							
						}else {
							showModuleList.add(_moduleInfo);
						}
						
					}
				}
			}else if(whroletype == 3){//政府端普通用户
				if(roleModuleMap_zhc.containsKey(key)){
					if (!_moduleInfo.getModuleName().equals("操作日志")) {
						showModuleList.add(_moduleInfo);
					}
				}
			}else{//企业端
				if (sysType==0) {//只显示公告模块
					if (_moduleInfo.getModuleName().startsWith("公告")) {
						showModuleList.add(_moduleInfo);
					}
				}else {//不显示公告模块
					if(roleModuleMap_qy.containsKey(key)&&!_moduleInfo.getModuleName().startsWith("公告")){
						showModuleList.add(_moduleInfo);
					}
				}
			}
		}
		
		
//		Iterator iter = moduleMap.entrySet().iterator();
//		
//		while(iter.hasNext()){
//			Map.Entry entry = (Map.Entry) iter.next();
//			Object key = entry.getKey();
//			if(whroletype==1){
//				if(roleModuleMap_zh.containsKey(key)){
//					showModuleList.add(moduleMap.get(key));
//				}
//			}else{
//				if(roleModuleMap_qy.containsKey(key)){
//					showModuleList.add(moduleMap.get(key));
//				}
//			}
//		}
		
		
//		int rolesize  = roleModuleInfoList.size();
//		//政府端  role_id = 3
//		if(whroletype==1){
//			for(int i=0;i<rolesize;i++){
//				RoleModule roleModule = roleModuleInfoList.get(i);
//				if(zhroleid == roleModule.getRoleId()){
//					int key = roleModule.getModuleId();
//					if(moduleMap.containsKey(key)){
//						showModuleList.add(moduleMap.get(key));						
//					}
//				}
//			}
//		}else{//企业端 role_id = 2
//			for(int i=0;i<rolesize;i++){
//				RoleModule roleModule = roleModuleInfoList.get(i);
//				if(qyroleid == roleModule.getRoleId()){
//					int key = roleModule.getModuleId();
//					if(moduleMap.containsKey(key)){
//						showModuleList.add(moduleMap.get(key));
//					}
//				}
//			}
//		}
		return "menu";
		
	}


	public List<ModuleInfo> getShowModuleList() {
		return showModuleList;
	}

	public void setShowModuleList(List<ModuleInfo> showModuleList) {
		this.showModuleList = showModuleList;
	}
	
}
