package cn.com.qytx.platform.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.dao.ModuleForHotlineDao;
import cn.com.qytx.platform.org.domain.ModuleInfo;
import cn.com.qytx.platform.service.IModuleForHotline;

@Service
@Transactional
public class IModuleForHotlineImpl extends BaseServiceImpl<ModuleInfo> implements IModuleForHotline {
	
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -8797783033336491871L;
	@Autowired
	private ModuleForHotlineDao mfhDao;
	
	@Override
	public void updateModuleByURL(String url,Integer flag) {

		this.mfhDao.updateModuleByURL(url,flag);
	}

	@Override
	public List<ModuleInfo> getAllModule() {
		return null;
	}

	@Override
	public List<ModuleInfo> getModuleByRole(String roleArr) {
		return null;
	}

	@Override
	public List<ModuleInfo> getNotModuleByRole(String roleArr) {
		return null;
	}

	@Override
	public Map<Integer, ModuleInfo> findModuleLevelMap(
			Map<Integer, ModuleInfo> moduleMap, Integer level) {
		return null;
	}

	@Override
	public Map<Integer, ModuleInfo> findModuleMap(String sysName, Integer roleId) {
		return null;
	}

	@Override
	public List<ModuleInfo> getModuleListByUser(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
}
