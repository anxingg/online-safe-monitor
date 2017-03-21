package cn.com.wh.dangerchemicals.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.star.uno.RuntimeException;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.dangerchemicals.dao.DangerChemicalsDao;
import cn.com.wh.dangerchemicals.domain.DangerChemicalsInfo;

/**
 * @author lilipo
 * 
 */
@Service
@Transactional
public class DangerChemicalsImpl implements DangerChemicalsService {

	@Resource
	DangerChemicalsDao dcDao;

	@Override
	public DangerChemicalsInfo saveOrUpdate(DangerChemicalsInfo dangerChemicals) {
		if(dcDao.usedThisName(dangerChemicals.getVid(), dangerChemicals.getMaterialName(), 1)){
			throw new RuntimeException("materialName used");
		}
		if(dcDao.usedThisName(dangerChemicals.getVid(), dangerChemicals.getMolecularFormula(), 2)){
			throw new RuntimeException("molecularFormula used");
		}
		
		return dcDao.saveOrUpdate(dangerChemicals);
	}

	@Override
	public DangerChemicalsInfo findOne(Integer id) {
		return dcDao.findOne(id);
	}

	@Override
	public Page<DangerChemicalsInfo> findByPage(Pageable pageable,
			DangerChemicalsInfo dangerChemicals) {
		return dcDao.findByPage(pageable, dangerChemicals);
	}

	@Override
	public boolean usedThisName(Integer id, String name, int which) {
		return dcDao.usedThisName(id, name, which);
	}

	@Override
	public List<DangerChemicalsInfo> findAll() {
		return dcDao.unDeleted().findAll();
	}

}
