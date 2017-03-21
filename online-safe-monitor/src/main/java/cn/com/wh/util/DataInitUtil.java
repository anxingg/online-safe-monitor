package cn.com.wh.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.qytx.cbb.dict.domain.Dict;
import cn.com.qytx.cbb.dict.service.IDict;
import cn.com.qytx.platform.base.application.SpringContextHolder;
import cn.com.qytx.platform.org.domain.ModuleInfo;
import cn.com.qytx.platform.org.domain.RoleModule;
import cn.com.qytx.platform.org.service.IModule;
import cn.com.qytx.platform.org.service.IRoleModule;
import cn.com.qytx.platform.org.service.impl.ModuleImpl;
import cn.com.qytx.platform.org.service.impl.RoleModuleImpl;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.company.service.IWHCompany;
import cn.com.wh.login.dao.WhEnterpriseDao;
import cn.com.wh.login.domain.WhEnterprise;

/**
 * @ClassName:     DataInitUtil.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         wangxj
 * @version        V1.0  
 * @Date           2015-6-30 下午04:49:15 
 */
public class DataInitUtil {
	
	public static List<WHCompany> companyList = new ArrayList<WHCompany>(); 
	public static Map<Integer,String> companyMap = new HashMap<Integer,String>();
	public static List<Dict> dictlist = new ArrayList<Dict>();
	
	public static Map<String,List<Dict>> dictMap= new HashMap<String,List<Dict>>();
	
	
	public static List<ModuleInfo> moduleInfoList = new ArrayList<ModuleInfo>();
	public static Map<Integer,ModuleInfo> moduleMap = new HashMap<Integer,ModuleInfo>();
	public static List<ModuleInfo> sortList = new ArrayList<ModuleInfo>();
	public static List<RoleModule> roleModuleInfoList = new ArrayList<RoleModule>();
	public static Map<Integer,RoleModule> roleModuleMap_zh = new HashMap<Integer,RoleModule>();
	public static Map<Integer,RoleModule> roleModuleMap_zhc = new HashMap<Integer,RoleModule>();
	public static Map<Integer,RoleModule> roleModuleMap_qy = new HashMap<Integer,RoleModule>();
	
	public static int zhcroleid = 4;
	public static int zhroleid = 3;
	public static int qyroleid = 2;
	
	public static void getCompanyList(){
		IWHCompany wHCompanyImpl = (IWHCompany) SpringContextHolder.getBean("companyImpl");
		companyList = wHCompanyImpl.findAll();
		
		//获得客户的企业信息
		WhEnterpriseDao whEnterpriseDao = (WhEnterpriseDao) SpringContextHolder.getBean("whEnterpriseDao");
		List<WhEnterprise> enterList = whEnterpriseDao.findAll();
		Map<String, WhEnterprise> map = new HashMap<String, WhEnterprise>();
		if (enterList!=null && enterList.size() > 0) {
			for (WhEnterprise whEnterprise : enterList) {
				map.put(whEnterprise.getCode(), whEnterprise);
			}
			if (companyList!=null&&companyList.size()>0) {
				for (WHCompany cpy : companyList) {
					if (map!=null&&map.get(cpy.getLinkId())!=null) {
						WhEnterprise enterprise = map.get(cpy.getLinkId());
						cpy.setCompanyName(enterprise.getEnterpriseName());
						cpy.setRegistrationAddress(enterprise.getRegisteredAddress());
						cpy.setCityId(enterprise.getAreaCode());
						if (enterprise.getSetUpDate()!=null) {
							cpy.setEstablishmentTime(new Timestamp(enterprise.getSetUpDate().getTime()));
						}else {
							cpy.setEstablishmentTime(null);
							
						}
						cpy.setCompanyProperty(enterprise.getNature());
						cpy.setBusinessLicence(enterprise.getLicenseCode());
						cpy.setProductionScope(enterprise.getProductionRange());
						cpy.setLegalRepresentative(enterprise.getLegalPerson());
						cpy.setEconomicType(enterprise.getEconomyType());
						cpy.setUnitCode(enterprise.getBaseOrgCode());
						cpy.setProductAddress(enterprise.getProductionAddress());
						cpy.setWebsite(enterprise.getWebUrl());
						cpy.setPostalcode(enterprise.getPostCode());
						cpy.setIndustryClassification(enterprise.getIndustryTypes());
						cpy.setIsIn(enterprise.getIsInGarden());
						cpy.setPrecision(enterprise.getLongitude());
						cpy.setDimension(enterprise.getLatitude());
					}
				}
			}
		}
		
		int companysize = companyList.size();
		for(int i=0;i<companysize;i++){
			WHCompany wHCompany = companyList.get(i);
			companyMap.put(wHCompany.getGroupId(), wHCompany.getCompanyName());
		}
	}
	
	public static void getModuleInfo(){
		IModule wHCompanyImpl = (IModule) SpringContextHolder.getBean(ModuleImpl.class);
		moduleInfoList = wHCompanyImpl.findAll();
		System.out.println("moduleInfoList="+moduleInfoList.size());
		int moduleInfoListsize = moduleInfoList.size();
		for(int i=0;i<moduleInfoListsize;i++){
			ModuleInfo moduleInfo = moduleInfoList.get(i);
			if(2==moduleInfo.getModuleLevel()&&moduleInfo.getIsDelete()==0){
				moduleMap.put(moduleInfo.getModuleId(), moduleInfo);
				sortList.add(moduleInfo);
				int parent_id = moduleInfo.getModuleId();
				for(int n=0;n<moduleInfoListsize;n++){
					ModuleInfo moduleInfo2 = moduleInfoList.get(n);
					if(parent_id==moduleInfo2.getParentId()&&moduleInfo2.getIsDelete()==0){
						moduleMap.put(moduleInfo2.getModuleId(), moduleInfo2);
						sortList.add(moduleInfo2);
					}
				}
			}
		}
	}
	
	public static void getRoleModule(){
		IRoleModule wHCompanyImpl = (IRoleModule) SpringContextHolder.getBean(RoleModuleImpl.class);
		roleModuleInfoList = wHCompanyImpl.findAll();
		roleModuleMap_zh = new HashMap<Integer,RoleModule>();
		roleModuleMap_zhc = new HashMap<Integer,RoleModule>();
		roleModuleMap_qy = new HashMap<Integer,RoleModule>();
		int roleModuleInfoListsize = roleModuleInfoList.size();
		for(int i=0;i<roleModuleInfoListsize;i++ ){
			RoleModule roleModule = roleModuleInfoList.get(i);
			if(zhroleid == roleModule.getRoleId()){
					roleModuleMap_zh.put(roleModule.getModuleId(),roleModule);
			}else if(qyroleid == roleModule.getRoleId()){
				roleModuleMap_qy.put(roleModule.getModuleId(),roleModule);
			}else if(zhcroleid == roleModule.getRoleId()){
				roleModuleMap_zhc.put(roleModule.getModuleId(),roleModule);
			}
		}
		
		System.out.println("roleModuleInfo="+roleModuleInfoList.size());
	}
	
	
	public static void getDict(){
		IDict dictservice =(IDict) SpringContextHolder.getBean("dictService");;
		
		dictlist = dictservice.unDeleted().findAll();
		int dictlistsize = dictlist.size();
		List<Dict> sortDict = new ArrayList<Dict>();
		for(int i=0;i<dictlistsize;i++){
			Dict dict = dictlist.get(i);
			if(dict.getSysTag()!=-1){
				if(dictMap.containsKey(dict.getInfoType())){
					sortDict = dictMap.get(dict.getInfoType());
				}else{
					sortDict = new ArrayList<Dict>();
				}
				sortDict.add(dict);
				dictMap.put(dict.getInfoType(), sortDict);
			}
		}
	}
	
	public static List<Dict> getDictFromKey(String key){
		
		return dictMap.get(key);
	}
	
	public static String getNamefromDict(String info_type,Integer value){
		List<Dict> queryDictList = getDictFromKey(info_type);
		for(Dict dict : queryDictList){
			if(dict.getValue()==value){
				return dict.getName();
			}
		}
		return "";
	}
	
	
	/** 
	 * @Title:        flush 
	 * @Description:  TODO(这里用一句话描述这个方法的作用) 
	 * @param:            
	 * @return:       void    
	 * @throws 
	 * @author        wangxj
	 * @Date          2015-7-3 上午11:08:30 
	 */
	public synchronized static void flush(){
		 companyList = new ArrayList<WHCompany>(); 
		 companyMap = new HashMap<Integer,String>();
		 dictlist = new ArrayList<Dict>();
		 moduleInfoList = new ArrayList<ModuleInfo>();
		 roleModuleInfoList = new ArrayList<RoleModule>();
		 moduleMap = new HashMap<Integer,ModuleInfo>();
		 roleModuleMap_qy = new HashMap<Integer,RoleModule>();
		 roleModuleMap_zh = new HashMap<Integer,RoleModule>();
		 sortList = new ArrayList<ModuleInfo>();
		 dictMap= new HashMap<String,List<Dict>>();
		getCompanyList();
		getDict();
		getModuleInfo();
		getRoleModule();
	}
	
	
	
	
	
	
}
