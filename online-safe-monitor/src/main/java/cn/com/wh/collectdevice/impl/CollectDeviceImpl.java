package cn.com.wh.collectdevice.impl;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.wh.collectdevice.dao.CollectDeviceDao;
import cn.com.wh.collectdevice.domain.CollectDevice;
import cn.com.wh.collectdevice.service.ICollectDevice;
import cn.com.wh.devicesensorbind.dao.DeviceSensorBindDao;

/**
 * 功能: 传感器
 * 版本: 1.0
 * 开发人员: 吴胜光
 * 创建日期: 2015-8-25
 * 修改日期: 
 * 修改列表:
 */
@Service
public class CollectDeviceImpl extends BaseServiceImpl<CollectDevice> implements ICollectDevice,Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日志
	 */
	private final static MonitorLogger LOGGER =new Log4jImpl(CollectDeviceImpl.class);
	
	@Autowired
	private CollectDeviceDao collectDeviceDao;
	
	@Autowired
	private DeviceSensorBindDao deviceSensorBindDao;
	@Override
	public Page<CollectDevice> findCollectDeviceList(Pageable pageable,
			String keyWord)
	{
		return collectDeviceDao.findCollectDeviceList(pageable, keyWord);
	}
	@Override
	public CollectDevice saveOrUpdate(CollectDevice entity)
	{
		int type=1;  //新增
		if(entity.getVid()==null){//修改
			type=2;//修改
		}
		CollectDevice collectDevice=super.saveOrUpdate(entity);
		if(type==1){//新增
			deviceSensorBindDao.initByByCollectDevice(collectDevice);
		}
		return collectDevice;
	}
	@Override
	public void delete(Integer collectDeviceId)
	{
		this.delete(collectDeviceId, true);
		deviceSensorBindDao.deleteByCollectDeviceId(collectDeviceId);
	}
}
