package cn.com.qytx.hotline.area.service;

import java.io.Serializable;
import java.util.List;

import cn.com.qytx.hotline.area.domain.city.City;
import cn.com.qytx.hotline.area.domain.province.Province;
import cn.com.qytx.platform.base.service.BaseService;



public interface IFiveGradeArea extends BaseService<City>,Serializable  {

	List<Province> getProvinceList();
	List<Object> getCityList(String code);
	List<Object> getCountyList(String code);
	List<Object> getTownList(String code);
	List<Object> getVillageList(String code);
	/**
	 * 根据地区code和地区的行政级别查询其下一级的行政地区;有直辖的，越级查询。
	 * @param code  完整的code
	 * @param grade
	 * @return Object 是一个List，用来写成json
	 */
	Object findSubRegionsByGrade(String code,String grade);
	/**
	 * 下一级地区有多少个。不计算越级直辖的地区。用于新增时计算code。
	 * @param code
	 * @param grade
	 * @return
	 */
	Integer getSubRegionCountByCodeAndGrade(String code,String grade);
	/**
	 * 该级地区中指定名称的行政区域有多少个。越级直辖的地区。用于新增判断是否有重名
	 * @param code  是将要添加到的上一个级别  如：41（河南省code）
	 * @param grade 是将要添加到哪个级别  如：city（市级）
	 * @return  返回 0 ：表示下一级地区中尚无指定名称的行政区域，1 ：表示下一级地区中已存在指定名称的行政区域。
	 */
	Integer getSubRegionCountByCodeAndGradeAndRegionName(final String code,final String grade,final String RegionName);
	/**
	 * 该级地区中指定名称的行政区域有多少个。越级直辖的地区。用于修改判断是否有重名
	 * @param code
	 * @param grade
	 * @param RegionName
	 * @return
	 */
	Integer getCurRegionCountByCodeAndGradeAndRegionName(String subCode,String grade,String RegionName,String orderNum);
	/**
	 * 根据行政地区的code和级别得到地区对象
	 * @param code   完整的code
	 * @param grade
	 * @return
	 */
	Object getRegionByCodeAndGrade(String code,String grade);
	/**
	 * 更新一个行政地区
	 * @param code  被修改地区的完整的code
	 * @param regionName  修改后的地区名称
	 * @param orderNum  修改后的地区排序号
	 * @param grade  被修改地区的级别
	 */
	void updateRegionByGrade(String code,String regionName,String orderNum, String grade);
	/**
	 * 删除一个行政地区，真删除！！！
	 * @param code  将要删除的地区的完整的code
	 * @param grade  将要删除的地区的级别
	 */
	void deleteRegionByCodeAndGrade(String code,String grade);
	/**
	 * 新增一个行政地区
	 * @param code  已前根据夏规则生成好的完整的code
	 * @param regionName
	 * @param orderNum
	 * @param grade
	 */
	void insertRegionByGrade(final String code,final String regionName,final String grade,final String orderNum,final String isRealName);
}
