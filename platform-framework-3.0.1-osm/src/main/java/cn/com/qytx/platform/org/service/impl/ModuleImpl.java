package cn.com.qytx.platform.org.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.org.dao.ModuleDao;
import cn.com.qytx.platform.org.domain.ModuleInfo;
import cn.com.qytx.platform.org.service.IModule;

/**
 * 模块操作实现类
 * User: 黄普友
 * Date: 13-2-19
 * Time: 下午4:25
 */
@Transactional
@Service("moduleService")
public class ModuleImpl  extends BaseServiceImpl<ModuleInfo> implements IModule {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="moduleDao")
	private ModuleDao<ModuleInfo> moduleDao;

    @Override
    public List<ModuleInfo> getModuleByRole(String roleArr,String sysName) {
        return moduleDao.getModuleByRole(roleArr,sysName);
    }
   
	/**
	 * @Description: TODO(得到级别Module) 
	 * @param  level
	 * @param  moduleMap
	 * @return Map<Integer,Module>    返回类型
	 */
	@Override
	public Map<Integer,ModuleInfo> findModuleLevelMap(Map<Integer,ModuleInfo> moduleMap,
			Integer level,String sysName){
		return moduleDao.findModuleLevelMap(moduleMap, level);
	}
	
	/**
	 * 
	 * @Title: findModuleMap 
	 * @Description: TODO(得到Module) 
	 * @param sysName 系统名称
	 * @return Map<Integer,Module>    返回类型
	 */
	@Override
	public Map<Integer,ModuleInfo> findModuleMap(String roleArr,String sysName){
		return moduleDao.findModuleMap(roleArr,sysName);
	}
}
