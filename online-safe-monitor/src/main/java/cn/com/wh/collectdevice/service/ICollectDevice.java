package cn.com.wh.collectdevice.service;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.collectdevice.domain.CollectDevice;





public interface ICollectDevice extends BaseService<CollectDevice> {

	/**
	 * 获得采集设备列表
	 * @param pageable
	 * @param deviceAddress 设备地址
	 * @param companyName 企业名称
	 * @return
	 */
	public Page<CollectDevice> findCollectDeviceList(Pageable pageable,
			String keyWord);
	
	public CollectDevice saveOrUpdate(CollectDevice entity);
	
	public void delete(Integer collectDeviceId);
	
}
