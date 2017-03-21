package cn.com.qytx.platform.service;

import java.util.List;
import java.util.Map;

import cn.com.qytx.cbb.notify.domain.PlatformParameter;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;


public interface IPlatformParameterService extends BaseService<PlatformParameter>{
	
	
	//按照ID查询
	PlatformParameter findById(int vId);
	void deletePlatformParameter(int vId);
	void savePlatformParameter(PlatformParameter pp);
	void updatePlatformParameter(PlatformParameter pp);
	//按照参数项列表出值
	Object findByParItems(String parItems);
	Page<PlatformParameter> findPageByMd(Pageable page,String moduleName);
	List<Object> getAllPar();
	void  saveObj(Object obj);
	Map<String ,Object> getParsMap();
}
