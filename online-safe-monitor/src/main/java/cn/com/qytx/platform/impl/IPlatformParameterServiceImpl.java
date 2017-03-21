package cn.com.qytx.platform.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.cbb.notify.domain.PlatformParameter;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.dao.PlatformParameterDao;
import cn.com.qytx.platform.service.IPlatformParameterService;
@Transactional
@Service("platformParameter")
public class IPlatformParameterServiceImpl extends BaseServiceImpl<PlatformParameter> implements IPlatformParameterService {
	@Resource(name="hotlinePlatformParameterDao")
	private PlatformParameterDao parmsDao;

	public void setParmsDao(PlatformParameterDao parmsDao) {
		this.parmsDao = parmsDao;
	}

	@Override
	public PlatformParameter findById(int vId) {
		return parmsDao.findById(vId);
	}

	@Override
	public void deletePlatformParameter(int vId) {
		parmsDao.deletePlatformParameter(vId);
	}

	@Override
	public void savePlatformParameter(PlatformParameter pp) {
		parmsDao.savePlatformParameter(pp);
	}

	@Override
	public void updatePlatformParameter(PlatformParameter pp) {
	parmsDao.updatePlatformParameter(pp);
		
	}

	@Override
	public Object findByParItems(String parItems) {
		
		return parmsDao.findByParItems(parItems);
	}

	@Override
	public Page<PlatformParameter> findPageByMd(Pageable page,
			String moduleName) {
		
	 return null;
	}

	@Override
	public List<Object> getAllPar() {
		
		return parmsDao.getAllPar();
	}

	@Override
	public void saveObj(Object obj) {
		parmsDao.savePlatformParameter(obj);
	}

	@Override
	public Map<String, Object> getParsMap() {
		return null;
	}
	
	

}
