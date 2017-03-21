package cn.com.wh.dangerchemicals.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.dangerchemicals.dao.ChemicalsDirectoryDao;
import cn.com.wh.dangerchemicals.domain.ChemicalsDirectory;

import com.sun.star.uno.RuntimeException;

/**
 * @author lilipo
 * 
 */
@Service
@Transactional
public class ChemicalsDirectoryImpl implements ChemicalsDirectoryService {

	@Resource
	ChemicalsDirectoryDao dcDao;

	@Override
	public ChemicalsDirectory saveOrUpdate(ChemicalsDirectory dangerChemicals) {
		if(dcDao.usedThisName(dangerChemicals.getVid(), dangerChemicals.getMaterialName(), 1)){
			throw new RuntimeException("materialName used");
		}
		if(dcDao.usedThisName(dangerChemicals.getVid(), dangerChemicals.getMolecularFormula(), 2)){
			throw new RuntimeException("molecularFormula used");
		}
		
		return dcDao.saveOrUpdate(dangerChemicals);
	}

	@Override
	public ChemicalsDirectory findOne(Integer id) {
		return dcDao.findOne(id);
	}

	@Override
	public Page<ChemicalsDirectory> findByPage(Pageable pageable,
			ChemicalsDirectory dangerChemicals) {
		return dcDao.findByPage(pageable, dangerChemicals);
	}

	@Override
	public boolean usedThisName(Integer id, String name, int which) {
		return dcDao.usedThisName(id, name, which);
	}

	@Override
	public List<ChemicalsDirectory> findAll() {
		return dcDao.unDeleted().findAll();
	}

}
