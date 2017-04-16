package cn.com.wh.devicesensorbind.service;

import java.util.List;
import java.util.Map;

import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.utils.tree.TreeNode;
import cn.com.wh.collectsensor.domain.CollectSensor;
import cn.com.wh.devicesensorbind.domain.DeviceSensorBind;
import cn.com.wh.thresholdtemplate.domain.ThresholdTemplate;




public interface IDeviceSensorBind extends BaseService<DeviceSensorBind> {

	/**
	 * 根据采集设备ID获得通道绑定列表
	 * @param 采集设备ID
	 * @return
	 */
	public Page<DeviceSensorBind> findDeviceSensorBindList(Pageable pageable,Integer collectDeviceId);
	
	public List<DeviceSensorBind> findDeviceSensorBindList(Integer collectDeviceId);
	/**
	 * 通道绑定
	 * @param id
	 * @param DEVICEID
	 * @param SENSORID
	 */
	public void bind(Integer id,Integer DEVICEID,Integer SENSORID);
	/**
	 * 获得已经绑定的传感器列表
	 * @param collectDeviceId
	 * @return
	 */
	public String findBindedCollectSensorIds(Integer collectDeviceId);
	/**
	 * 创建查询树
	 * @return
	 */
	public List<TreeNode> createSearchTree(UserInfo userInfo);
	
}
