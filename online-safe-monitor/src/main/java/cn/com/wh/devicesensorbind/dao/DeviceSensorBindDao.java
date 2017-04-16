package cn.com.wh.devicesensorbind.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.collectdevice.domain.CollectDevice;
import cn.com.wh.devicesensorbind.domain.DeviceSensorBind;
/**
 * 功能: 应急预案
 * 版本: 1.0
 * 开发人员: 吴胜光
 * 创建日期: 2015-8-27
 * 修改日期: 
 * 修改列表:
 */
@Repository
public class DeviceSensorBindDao extends BaseDao<DeviceSensorBind, Integer> implements Serializable{

	private static final long serialVersionUID = 1L;

	
	public Page<DeviceSensorBind> findDeviceSensorBindList(Pageable pageable,Integer collectDeviceId) {
		String hql=" 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if((collectDeviceId!=null)&&(collectDeviceId!=-1)){
			hql+=" and collectDevice.Id=?";
			params.add(collectDeviceId);
		}
		return this.findAll(hql,pageable,params.toArray());
	}
	public List<DeviceSensorBind> findDeviceSensorBindList(Integer collectDeviceId)
	{
		String hql=" 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if((collectDeviceId!=null)&&(collectDeviceId!=-1)){
			hql+=" and collectDevice.Id=?";
			params.add(collectDeviceId);
		}
		return this.findAll(hql,params.toArray());
	}
	public void deleteByCollectDeviceId(Integer collectDeviceId)
	{
		String hql="delete from DEVICESENSORBIND where DEVICEID="+collectDeviceId;
		this.entityManager.createNativeQuery(hql).executeUpdate();
	}
	public void update(Integer id,Integer DEVICEID,Integer SENSORID,Integer CHANNELSTATUS)
	{
		String hql="update DEVICESENSORBIND set DEVICEID="+DEVICEID
				+",SENSORID="+SENSORID+",CHANNELSTATUS="+CHANNELSTATUS+" where id="+id;
		this.entityManager.createNativeQuery(hql).executeUpdate();
	}
	
	public void initByByCollectDevice(CollectDevice collectDevice)
	{
		DeviceSensorBind deviceSensorBind=new DeviceSensorBind();
		for(int i=0;i<collectDevice.getChannelCount();i++){
			deviceSensorBind.setChannelNo(i+1);
			deviceSensorBind.setChannelStatus(0);
			deviceSensorBind.setCollectDevice(collectDevice);
			deviceSensorBind.setCollectSensor(null);
			deviceSensorBind.setId(null);
			this.saveOrUpdate(deviceSensorBind);
		}
	}
}
