package cn.com.qytx.hotline.area.dao;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.com.qytx.hotline.area.domain.city.City;
import cn.com.qytx.hotline.area.domain.county.County;
import cn.com.qytx.hotline.area.domain.province.Province;
import cn.com.qytx.hotline.area.domain.town.Town;
import cn.com.qytx.hotline.area.domain.village.Village;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.dao.BaseDao;

/**
 * 类名称：FiveGrageAreaDao
 * 类描述：   五级地址
 * 创建人：李立泼
 * 创建时间：2014-01-03 
 */
@Component
public class FiveGrageAreaDao extends BaseDao<City,Integer> implements Serializable{
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -277152600238105955L;
	
	private static final String PROVINCE = "province";
	private static final String CITY = "city";
    private static final String COUNTY = "county";
    private static final String TOWN = "town";
    private static final String VILLAGE = "village";
    private static final String UNCHECKED = "unchecked";
    private static final String COMMA = "','";//逗号
    /**
     * log4j日志
     */
    private final static MonitorLogger logger =new Log4jImpl(FiveGrageAreaDao.class);
	/**
	 * 根据地区code和地区的行政级别查询其下一级的行政地区;有直辖的，越级查询。code和grade同一级。
	 * @param code  完整的code
	 * @param grade
	 * @return Object 是一个List，用来写成json
	 */
	public Object findSubRegionsByGrade(String code,String grade){
		if(code==null || "".equals(code) || grade==null || "".equals(grade)){
			return null;
		}
		Object result= new Object();
		if(!"".equals(code)){
			if(PROVINCE.equals(grade)){ //通过省级的code查询市级
				code = code.substring(0, 2);
				result=this.getCityList(code);
			}else if(CITY.equals(grade)){ //通过市级的code查询县级
				code = code.substring(0, 4);
				result=this.getCountyList(code);
			}else if(COUNTY.equals(grade)){ //通过县级的code查询乡级
				code = code.substring(0, 6);
				result=this.getTownList(code);
			}else if(TOWN.equals(grade)){ //通过乡级的code查询村级
				code = code.substring(0, 9);
				result=this.getVillageList(code);
			}else if(VILLAGE.equals(grade)){
				result="empty";
			}
		}else{ //code为空时取省级的所有对象。
			result=this.getProvinceList();
		}
		return result;
	}
	
	/**
	 * 该级地区中指定名称的行政区域有多少个。越级直辖的地区。用于新增判断是否有重名。
	 * @param code  是将要添加到的上一个级别  如：41（河南省code）
	 * @param grade 是将要添加到哪个级别  如：city（市级）
	 * @return  返回 0 ：表示下一级地区中尚无指定名称的行政区域，1 ：表示下一级地区中已存在指定名称的行政区域。
	 */
	public Integer getSubRegionCountByCodeAndGradeAndRegionName(final String code,final String grade,final String RegionName){
		if(code==null || "".equals(code) || grade==null || "".equals(grade)){
			return null;
		}
		final String subCode=getSubCodeByCodeAndGrade(code,getParentGrade(grade));
		String sql = "select count(id) from tb_"+grade+" where code like '"+subCode+"%'  and "+grade+" = '"+RegionName+"' ";
		Query query = entityManager.createNativeQuery(sql);
		Object result = query.getSingleResult();
		if(result!=null){
			return Integer.parseInt(result.toString());
		}
		return null;
	}
	
	public Integer getCurRegionCountByCodeAndGradeAndRegionName(final String subCode,final String grade,final String RegionName,final String orderNum){
		if(grade==null || "".equals(grade) || RegionName==null || "".equals(RegionName)){
			return null;
		}
		String sql = "select count(id) from tb_"+grade+" where "+grade+" = '"+RegionName+"' and code like '"+subCode+"%'";
		Query query = entityManager.createNativeQuery(sql);
		Object result = query.getSingleResult();
		if(result!=null){
			return Integer.parseInt(result.toString());
		}
		return null;
	}
	/**
	 * 下一级地区有多少个。计算越级直辖的地区。用于删除。
	 * @param code  是将要添加到的上一个级别  如：41（河南省code）
	 * @param grade 是将要添加到哪个级别  如：city（市级）
	 * @return
	 */
	public Integer getSubRegionCountByCodeAndGrade(final String code,String grade){
		if(code==null || "".equals(code) || grade==null || "".equals(grade)){
			return null;
		}
		if("village_d".equals(grade)){
			return 0;
		}
		boolean ts=(code.indexOf("11")==0||code.indexOf("12")==0||code.indexOf("31")==0||code.indexOf("50")==0)&&COUNTY.equals(grade);
		final String subCode=getSubCodeByCodeAndGrade(code,ts?getParentGrade(getParentGrade(grade)):getParentGrade(grade));
		String sql = "select count(id) from tb_"+grade+" where code like '"+subCode+"%' ";
		Query query = entityManager.createNativeQuery(sql);
		Object result = query.getSingleResult();
		if(result!=null){
			return Integer.parseInt(result.toString());
		}
		return null;
	}
	
	/**
	 * 新增一个行政地区
	 * @param code  已前根据夏规则生成好的完整的code
	 * @param regionName
	 * @param orderNum
	 * @param grade
	 */
	public void insertRegionByGrade(final String code,final String regionName,final String grade,final String orderNum,final String isRealName){
		if(CITY.equals(grade)){
			City city=new City();
			city.setCode(code);
			city.setCity(regionName);
			city.setGrade(grade);
			city.setOrderNum(orderNum);
			city.setIsRealName(isRealName);
			super.saveOrUpdate(city);
		}else if(COUNTY.equals(grade)){
			String sql = "insert into tb_"+grade+"(code,county,grade,orderNum,isRealName) values('"+code+COMMA+regionName+COMMA+grade+COMMA+orderNum+COMMA+isRealName+"') ";
			Query query = entityManager.createNativeQuery(sql);
			query.executeUpdate();
		}else if(TOWN.equals(grade)){
			String sql = "insert into tb_"+grade+"(code,town,grade,orderNum,isRealName) values('"+code+COMMA+regionName+COMMA+grade+COMMA+orderNum+COMMA+isRealName+"') ";
			Query query = entityManager.createNativeQuery(sql);
			query.executeUpdate();
		}else if(VILLAGE.equals(grade)){
			String sql = "insert into tb_"+grade+"(code,village,grade,orderNum,isRealName) values('"+code+COMMA+regionName+COMMA+grade+COMMA+orderNum+COMMA+isRealName+"') ";
			Query query = entityManager.createNativeQuery(sql);
			query.executeUpdate();
		}
	}
	
	/**
	 * 更新一个行政地区
	 * @param code  被修改地区的完整的code
	 * @param regionName  修改后的地区名称
	 * @param orderNum  修改后的地区排序号
	 * @param grade  被修改地区的级别
	 */
	public void updateRegionByGrade(final String code,final String regionName,final String orderNum, final String grade){
		String sql=" update tb_"+grade+" set "+grade+"='"+regionName+"', orderNum='"+orderNum+"'  where code = '"+code+"'";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	/**
	 * 删除一个行政地区，真删除！！！
	 * @param code  将要删除的地区的完整的code
	 * @param grade  将要删除的地区的级别
	 */
	public void deleteRegionByCodeAndGrade(final String code,String grade){
		String sql="delete from tb_"+grade+" where code = '"+code+"'";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	
	}
	
	/**
	 * 没用！！！
	 * 通过code和grade从 province city county town village 这五张表中查询出对象。
	 * @param code
	 * @param grade
	 * @return Object[] 长度固定值是2，第一个值是查询出的对象，第二个值是grade
	 */
	public Object[] accurateFindRegionByCodeAndGrade(String code,String grade){
		if(code==null || "".equals(code) || grade==null || "".equals(grade)){
			return null;
		}
		Object obj = getRegionByCodeAndGrade(code,grade);
		if(obj!=null){
			Object[] objs=new Object[2];
			objs[0]=obj;
			objs[1]=grade;
			return objs;
		}
		return null;
	}
	
	/**
	 * 根据行政地区的code和级别得到地区对象
	 * @param code   完整的code
	 * @param grade
	 * @return
	 */
	public Object getRegionByCodeAndGrade(final String code,String grade){
		String sql=" select * from tb_"+grade+" where code = '"+code+"' order by orderNum ";
		Query query = entityManager.createNativeQuery(sql);
		Object result  = query.getSingleResult();
		if(result!=null){
			return result;
		}
		return null;
	}
	
	/**
	 * 通过省级的code查询省级
	 * @return
	 */
	@SuppressWarnings(UNCHECKED)
	public List<Province> getProvinceList(){
		List<Province> result;
		String sql="select t.id,t.code,t.province,t.grade,t.orderNum,t.isRealName from tb_province as t order by t.orderNum ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> objs = query.getResultList();
		if(objs==null){
			return null;
		}else{
			result = new ArrayList<Province>();
			for(Object[] obj:objs){
				Province province = new Province();
				province.setId((Integer)obj[0]);
				province.setCode((obj[1]!=null)?obj[1].toString():null);
				province.setProvince((obj[2]!=null)?obj[2].toString():null);
				province.setGrade((obj[3]!=null)?obj[3].toString():null);
				province.setOrderNum((obj[4]!=null)?obj[4].toString():null);
				province.setIsRealName((obj[5]!=null)?obj[5].toString():null);
				result.add(province);
			}
		}
		if(result!=null && result.size()>0){
			return result;
		}
		return null;
	}
	
	/**
	 * 通过省级的code查询市级
	 * @param code   截取的code
	 * @return
	 */
	@SuppressWarnings(UNCHECKED)
	public List<Object> getCityList(final String code){
		List<Object> result=new ArrayList<Object>();
		if(code==null || "".equals(code)){
			return null;
		}

		List<City> cityList;
		String sql="select t.id,t.code,t.city,t.grade,t.orderNum,t.isRealName from tb_city as t where t.code like '"+code+"%'  order by orderNum";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> objs = query.getResultList(); 
		if(objs==null){
			return null;
		}else{
			cityList = new ArrayList<City>();
			for(Object[] obj:objs){
				City city = new City();
				city.setId((Integer)obj[0]);
				city.setCode((obj[1]!=null)?obj[1].toString():null);
				city.setCity((obj[2]!=null)?obj[2].toString():null);
				city.setGrade((obj[3]!=null)?obj[3].toString():null);
				city.setOrderNum((obj[4]!=null)?obj[4].toString():null);
				city.setIsRealName((obj[5]!=null)?obj[5].toString():null);
				cityList.add(city);
			}
		}
		
		if(cityList!=null && cityList.size()>0){
			//遍历，看有没有isRealName为n的。有则查下一级。
			for(City city:cityList){
				String irn=city.getIsRealName();
				if("y".equals(irn)){
					result.add(city);
				}else{ //往下级查   city--->county
					String countyCode=city.getCode().substring(0, 4); //市级的code是4位
					List<Object> countys=this.getCountyList(countyCode);
					if(countys!=null && countys.size()>0){
						for(Object obj:countys){
							result.add(obj);
						}
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 通过市级的code查询县级
	 * @param code  截取的code
	 * @return
	 */
	public List<Object> getCountyList(final String code){
		List<Object> result=new ArrayList<Object>();
		if(code==null || "".equals(code)){
			return null;
		}
		List<County> countyList;
		String sql="select t.id,t.code,t.county,t.grade,t.orderNum,t.isRealName from tb_county as t where t.code like '"+code+"%' order by orderNum";
		Query query = entityManager.createNativeQuery(sql);
		@SuppressWarnings(UNCHECKED)
		List<Object[]> objs = query.getResultList(); 
		if(objs==null){
			return null;
		}else{
			countyList = new ArrayList<County>();
			for(Object[] obj:objs){
				County county = new County();
				county.setId((Integer)obj[0]);
				county.setCode((obj[1]!=null)?obj[1].toString():null);
				county.setCounty((obj[2]!=null)?obj[2].toString():null);
				county.setGrade((obj[3]!=null)?obj[3].toString():null);
				county.setOrderNum((obj[4]!=null)?obj[4].toString():null);
				county.setIsRealName((obj[5]!=null)?obj[5].toString():null);
				countyList.add(county);
			}
		}
		if(countyList!=null && countyList.size()>0){
			//遍历，看有没有isRealName为n的。有则查下一级。
			for(County county:countyList){
				String irn=county.getIsRealName();
				if("y".equals(irn)){
					result.add(county);
				}else{ //往下级查   county--->town
					String townCode=county.getCode().substring(0, 6); //县级的code是6位
					List<Object> towns=this.getTownList(townCode);
					if(towns!=null && towns.size()>0){
						for(Object obj:towns){
							result.add(obj);
						}
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 通过县级的code查询乡级
	 * @param code  截取的code
	 * @return
	 */
	public List<Object> getTownList(final String code){
		List<Object> result=new ArrayList<Object>();
		if(code==null || "".equals(code)){
			return null;
		}
		List<Town> townList;
		String sql="select t.id,t.code,t.town,t.grade,t.orderNum,t.isRealName from tb_town as t where t.code like '"+code+"%' order by orderNum";
		Query query = entityManager.createNativeQuery(sql);
		@SuppressWarnings(UNCHECKED)
		List<Object[]> objs = query.getResultList(); 
		if(objs==null){
			return null;
		}else{
			townList = new ArrayList<Town>();
			for(Object[] obj:objs){
				Town town = new Town();
				town.setId((Integer)obj[0]);
				town.setCode((obj[1]!=null)?obj[1].toString():null);
				town.setTown((obj[2]!=null)?obj[2].toString():null);
				town.setGrade((obj[3]!=null)?obj[3].toString():null);
				town.setOrderNum((obj[4]!=null)?obj[4].toString():null);
				town.setIsRealName((obj[5]!=null)?obj[5].toString():null);
				townList.add(town);
			}
		}
		if(townList!=null && townList.size()>0){
			//遍历，看有没有isRealName为n的。有则查下一级。
			for(Town town:townList){
				String irn=town.getIsRealName();
				if("y".equals(irn)){
					result.add(town);
				}else{ //往下级查   town--->village
					String villageCode=town.getCode().substring(0, 9); //乡级的code是9位
					List<Object> villages=this.getVillageList(villageCode);
					if(villages!=null && villages.size()>0){
						for(Object obj:villages){
							result.add(obj);
						}
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 通过乡级的code查询村级
	 * @param code 截取的code
	 * @return
	 */
	public List<Object> getVillageList(final String code){
		List<Object> result;
		if(code==null || "".equals(code)){
			return null;
		}
		String sql=" select t.id,t.code,t.village,t.grade,t.orderNum,t.isRealName from tb_village as t where t.code like '"+code+"%' order by orderNum";
		Query query = entityManager.createNativeQuery(sql);
		@SuppressWarnings(UNCHECKED)
		List<Object[]> objs = query.getResultList();
		if(objs==null){
			return null;
		}else{
			result = new ArrayList<Object>();
			for(Object[] obj:objs){
				Village village = new Village();
				village.setId((Integer)obj[0]);
				village.setCode((obj[1]!=null)?obj[1].toString():null);
				village.setVillage((obj[2]!=null)?obj[2].toString():null);
				village.setGrade((obj[3]!=null)?obj[3].toString():null);
				village.setOrderNum((obj[4]!=null)?obj[4].toString():null);
				village.setIsRealName((obj[5]!=null)?obj[5].toString():null);
				result.add(village);
			}
		}
		if(result!=null && result.size()>0){
			return result;
		}
		return null;
	
	}
	
	/**
	 * 首字母大写
	 * @param string
	 * @return
	 */
	public String firstCharacterUpper(String string){
		if(string==null || string.length()<1){
			return null;
		}
		Character c=string.charAt(0);
		String result=c.toString().toUpperCase()+string.substring(1, string.length());
		return result;
	}
	
	/**
	 * 截取code
	 * @param code
	 * @param grade
	 * @return
	 */
	public String getSubCodeByCodeAndGrade(String code,String grade){
		if(code==null || "".equals(code) || grade==null || "".equals(grade)){
			return null;
		}
		try{
			if(PROVINCE.equals(grade)){ //省
				return code.substring(0, 2);
			}else if(CITY.equals(grade)){ //市
				return code.substring(0, 4);
			}else if(COUNTY.equals(grade)){ //县
				return code.substring(0, 6);
			}else if(TOWN.equals(grade)){ //乡
				return code.substring(0, 9);
			}else if(VILLAGE.equals(grade)){ //村
				return code.substring(0, 12);
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			return null;
		}
		return null;
	}
	
	/**
	 * 通过类名返回对象实例
	 * @param className
	 * @return
	 */
	public Object getInstance(String className){
		Object obj=null;
		try {
			obj=Class.forName(className).newInstance();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return obj;
	}
	
	/**
	 * 根据当前级别，返回上一级别。
	 * @param grade
	 * @return
	 */
	public String getParentGrade(String grade) {
		if(grade==null){
			return null;
		}
		if(CITY.equals(grade)){
			return PROVINCE;
		}else if(COUNTY.equals(grade)){
			return CITY;
		}else if(TOWN.equals(grade)){
			return COUNTY;
		}else if(VILLAGE.equals(grade)){
			return TOWN;
		}else{
			return "";
		}
	}
	/**
	 * 根据当前级别，返回下一级别。
	 * @param grade
	 * @return
	 */
	public String getSubGrade(String grade) {
		if(grade==null){
			return null;
		}
		if(PROVINCE.equals(grade)){
			return CITY;
		}else if(CITY.equals(grade)){
			return COUNTY;
		}else if(COUNTY.equals(grade)){
			return TOWN;
		}else if(TOWN.equals(grade)){
			return VILLAGE;
		}else if(VILLAGE.equals(grade)){
			return VILLAGE;
		}
		return null;
	}
	
}
