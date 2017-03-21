package cn.com.wh.login.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.wh.login.domain.WhEnterprise;
import cn.com.wh.login.domain.WhUser;

/**
 * 
 * <br/>功能:客户端企业表数据库操作类DAO
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2016年5月1日
 * <br/>修改日期: 2016年5月1日
 * <br/>修改列表:
 */
@Repository("whEnterpriseDao")
public class WhEnterpriseDao extends BaseDao<WhEnterprise,String>{

	public WhEnterprise findByEnterpriseCode(String code){
		WhEnterprise info = super.findOne("code=?", code);
		return info;
	}
	
	/**
	 * 通过企业id字符串获得所有企业列表
	 * @param codes
	 * @return
	 */
	public List<WhEnterprise> findListByEnterpriseCode(String codes){
		List<WhEnterprise> list = super.findAll("code in (?)", codes);
		return list;
	}
	
	/**
	 * 通过城市编码获得城市名称
	 * @param cityCode
	 * @return
	 */
	public String getCityNameByCityCode(String cityCode){
		String sql ="select MERGERNAME  from FULLCHINADISTRICT where ID='"+cityCode+"'";
		List<Map<String, String>> list = entityManager.createNativeQuery(sql).unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		String cityName = "";
		if (list!=null&&list.size()>0) {
			for (Map<String, String> map : list) {
				cityName = map.get("MERGERNAME");
			}
		}
		if (StringUtils.isNotBlank(cityName)) {
			cityName = cityName.replaceAll(",", "");
		}
		return cityName;
	}
	
	/**
	 * 通过经济类型编码获得经济类型名称
	 * @param economyCode
	 * @return
	 */
	public String getEconomyTypeByTypeCode(String economyCode){
		String sql ="select TYPENAME from SYSDICTIONARY where CATEGORYCODE='EconomyType' and TYPECODE='"+economyCode+"'";
		List<Map<String, String>> list = entityManager.createNativeQuery(sql).unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		String economyName = "";
		if (list!=null&&list.size()>0) {
			for (Map<String, String> map : list) {
				economyName = map.get("TYPENAME");
			}
		}
		return economyName;
	}
	
	/**
	 * 通过行业分类编码获得行业分类名称
	 * @param industryCode
	 * @return
	 */
	public String getIndustryTypeByTypeCode(String industryCode){
		List<String> argList = new ArrayList<String>();
		String[] codes = industryCode.split(",");
		for(String str:codes){
			if(str!=null&&!"".equals(str)){
				argList.add(str);
			}
		}
		String sql ="select TYPENAME from SYSDICTIONARY where CATEGORYCODE='IndustryType' and TYPECODE in (?1)";
		List<Map<String, String>> list = entityManager.createNativeQuery(sql).setParameter(1,argList).unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		String industryName = "";
		if (list!=null&&list.size()>0) {
			for (Map<String, String> map : list) {
				industryName += "," + map.get("TYPENAME");
			}
		}
		if (StringUtils.isNotBlank(industryName)) {
			industryName = industryName.substring(1);
		}
		return industryName;
	}
}
