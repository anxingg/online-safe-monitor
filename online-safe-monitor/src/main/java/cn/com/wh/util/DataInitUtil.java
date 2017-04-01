package cn.com.wh.util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.com.qytx.cbb.dict.domain.Dict;
import cn.com.qytx.cbb.dict.service.IDict;
import cn.com.qytx.platform.base.application.SpringContextHolder;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.company.service.IWHCompany;

/**
 * @ClassName:     DataInitUtil.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         wangxj
 * @version        V1.0  
 * @Date           2015-6-30 下午04:49:15 
 */
public class DataInitUtil {
	//企业列表
	public static List<WHCompany> companyList = new ArrayList<WHCompany>(); 
	//企业名称MAP，由于很多地方都需要显示企业信息
	public static Map<Integer,String> companyMap = new HashMap<Integer,String>();
	public static List<Dict> dictlist = new ArrayList<Dict>();
	public static Map<String,List<Dict>> dictMap= new HashMap<String,List<Dict>>();
	
	public static void getCompanyList(){
		IWHCompany wHCompanyImpl = (IWHCompany) SpringContextHolder.getBean("companyImpl");
		companyList = wHCompanyImpl.findAll();
		int companysize = companyList.size();
		for(int i=0;i<companysize;i++){
			WHCompany wHCompany = companyList.get(i);
			companyMap.put(wHCompany.getGroupId(), wHCompany.getCompanyName());
		}
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
		dictMap= new HashMap<String,List<Dict>>();
		getCompanyList();
		getDict();
	}
}
