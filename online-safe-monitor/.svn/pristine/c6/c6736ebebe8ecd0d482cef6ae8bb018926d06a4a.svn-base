package cn.com.wh.dangerchemicals.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.dangerchemicals.dao.CompanyDangerChemicalsDao;
import cn.com.wh.dangerchemicals.domain.CompanyDangerChemicals;
import cn.com.wh.dangerchemicals.domain.CompanyDangerChemicalsStatisticsResult;

/**
 * @author lilipo
 * 
 */
@Service
@Transactional
public class CompanyDangerChemicalsImpl implements
		CompanyDangerChemicalsService {

	@Resource
	CompanyDangerChemicalsDao cdcDao;

	@Override
	public CompanyDangerChemicals saveOrUpdate(CompanyDangerChemicals cdc) {
		return cdcDao.saveOrUpdate(cdc);
	}

	@Override
	public CompanyDangerChemicals findOne(Integer vid) {
		return cdcDao.findOne(vid);
	}

	@Override
	public Page<CompanyDangerChemicals> findByPage(Pageable pageable,
			String materialName, Integer dangerId, Integer groupId,
			Integer whroletype) {
		return cdcDao.findByPage(pageable, materialName, dangerId, groupId);
	}

	@Override
	public Page<CompanyDangerChemicalsStatisticsResult> findStatisticsResultByPage(
			Pageable pageable, String materialName, Integer statisticsType,
			Integer groupId) {
		return cdcDao.findStatisticsResultByPage(pageable, materialName,
				statisticsType, groupId);
	}

}
