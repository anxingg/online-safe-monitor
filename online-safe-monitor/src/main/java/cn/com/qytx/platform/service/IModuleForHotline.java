package cn.com.qytx.platform.service;

import cn.com.qytx.platform.org.service.IModule;

public interface IModuleForHotline extends IModule {
	/**
	 * 更新模块的状态，满足定制需求
	 * 蔡明雪 2014-04-08
	 * 
	 */
	void updateModuleByURL(String url,Integer flag);
}
