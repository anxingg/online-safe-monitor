package cn.com.qytx.hotline.area.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.area.dao.FiveGrageAreaDao;
import cn.com.qytx.hotline.area.domain.city.City;
import cn.com.qytx.hotline.area.domain.province.Province;
import cn.com.qytx.hotline.area.service.IFiveGradeArea;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;


@Service
@Transactional
public class FiveGradeAreaImpl extends BaseServiceImpl<City> implements IFiveGradeArea, Serializable {

	private static final long serialVersionUID = 1L;
	@Resource
	FiveGrageAreaDao fiveGrageAreaDao;
	
	@Override
	public List<Province> getProvinceList() {
		return fiveGrageAreaDao.getProvinceList();
	}

	@Override
	public List<Object> getCityList(String code) {
		return fiveGrageAreaDao.getCityList(code);
	}

	@Override
	public List<Object> getCountyList(String code) {
		return fiveGrageAreaDao.getCountyList(code);
	}

	@Override
	public List<Object> getTownList(String code) {
		return fiveGrageAreaDao.getTownList(code);
	}

	@Override
	public List<Object> getVillageList(String code) {
		return fiveGrageAreaDao.getVillageList(code);
	}

	@Override
	public Object findSubRegionsByGrade(String code, String grade) {
		return fiveGrageAreaDao.findSubRegionsByGrade(code, grade);
	}

	@Override
	public Integer getSubRegionCountByCodeAndGrade(String code, String grade) {
		return fiveGrageAreaDao.getSubRegionCountByCodeAndGrade(code, grade);
	}

	@Override
	public Object getRegionByCodeAndGrade(String code, String grade) {
		return fiveGrageAreaDao.getRegionByCodeAndGrade(code, grade);
	}

	@Override
	public void updateRegionByGrade(String code, String regionName,
			String orderNum, String grade) {
		fiveGrageAreaDao.updateRegionByGrade(code, regionName, orderNum, grade);
	}

	@Override
	public void deleteRegionByCodeAndGrade(String code, String grade) {
		fiveGrageAreaDao.deleteRegionByCodeAndGrade(code, grade);
	}

	@Override
	public void insertRegionByGrade(String code, String regionName,
			String grade, String orderNum, String isRealName) {
		fiveGrageAreaDao.insertRegionByGrade(code, regionName, grade, orderNum, isRealName);
	}

	@Override
	public Integer getSubRegionCountByCodeAndGradeAndRegionName(String code,
			String grade, String RegionName) {
		return fiveGrageAreaDao.getSubRegionCountByCodeAndGradeAndRegionName(code, grade, RegionName);
	}

	@Override
	public Integer getCurRegionCountByCodeAndGradeAndRegionName(String subCode,String grade, String RegionName,String orderNum) {
		return fiveGrageAreaDao.getCurRegionCountByCodeAndGradeAndRegionName(subCode,grade, RegionName, orderNum);
	}

}
