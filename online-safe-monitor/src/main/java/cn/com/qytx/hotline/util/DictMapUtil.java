/**
 * 
 */
package cn.com.qytx.hotline.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.qytx.cbb.dict.domain.Dict;
import cn.com.qytx.cbb.dict.service.IDict;
import cn.com.qytx.platform.utils.spring.SpringUtil;

/**
 * 功能: 数据字典接口  获取map
 * 版本: 1.0
 * 开发人员: 彭小东
 * 创建日期: 2015-2-4
 * 修改日期: 2015-2-4
 * 修改列表: 
 */
public class DictMapUtil {

	private static IDict dict=null;
	static{
		if(dict==null){
			dict=(IDict) SpringUtil.getBean("dictService");
		}
	}
	/**
	 * 功能：根据数据类型  查询数据字典
	 * @param typeName  数据类型
	 * @param sysTag   系统值
	 * @return
	 */
	public static List<Dict> findListDict(String typeName, Integer sysTag) {
		// TODO Auto-generated method stub
		List<Dict> dicList =dict.findList(typeName, sysTag);
		return dicList;
	}
	/**
    * 功能：根据数据类型  查询数据字典
	 * @param typeName  数据类型
	 * @param sysTag   系统值
	 * @return  
	 */
	public static Map<String,String> getMapDict(String typeName, Integer sysTag) {
		
		Map<String,String> map=new HashMap<String, String>();
		List<Dict> dicList=findListDict(typeName, sysTag);
		for (Dict dict : dicList) {
			map.put("key_"+dict.getValue(),dict.getName() );
		}
		return map;
	}


	/**
	 * 功能：根据数据类型  查询数据字典
	 * @param typeName  数据类型
	 * @return  
	 */
	public static Map<String,String> getMapDict(String typeName) {
		
		Map<String,String> map=new HashMap<String, String>();
		List<Dict> dicList=findListDict(typeName, 1);
		for (Dict dict : dicList) {
			map.put("key_"+dict.getValue(),dict.getName() );
		}
		return map;
	}
	
	/**
	 * 功能：根据数据类型  查询数据字典
	 * @param typeName  数据类型
	 * @return
	 */
	public static List<Dict> findListDict(String typeName) {
		// TODO Auto-generated method stub
		List<Dict> dicList =findListDict(typeName, 1);
		return dicList;
	}
	/**
	 * 功能：根据类型名称  值查询数据字段的信息
	 * @param typeName
	 * @param sysTag
	 * @param taskType
	 * @return
	 */
	public static Dict loadByTypeTagValue(String typeName, int sysTag, Integer val) {
		Dict dic=dict.loadByTypeTagValue(typeName, sysTag,val);
		return dic;
	}

	
	
}
