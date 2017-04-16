package cn.com.wh.devicesensorbind.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.Gson;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.qytx.platform.utils.tree.TreeNode;
import cn.com.wh.collectsensor.domain.CollectSensor;
import cn.com.wh.collectsensor.service.ICollectSensor;
import cn.com.wh.dangersource.domain.DangerSource;
import cn.com.wh.devicesensorbind.domain.DeviceSensorBind;
import cn.com.wh.devicesensorbind.service.IDeviceSensorBind;
import cn.com.wh.util.Tool;

public class DeviceSensorBindAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	public IDeviceSensorBind deviceSensorBindImpl; 
	@Autowired
	public ICollectSensor collectSensorImpl; 
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;
	
	//通道名称 位置+通道号
	private String channelName;
	//采集设备ID
	private Integer collectDeviceId;
	//传感器 ID
	private Integer collectSensorId;
	
	//通道ID
	private Integer deviceSensorBindId;

	//通道状态
	private Integer channelStatus;
	
	
	//选择的危险源ID
	private Integer dangerSourceId;
	
	public Integer getCollectDeviceId() {
		return collectDeviceId;
	}
	public void setCollectDeviceId(Integer collectDeviceId) {
		this.collectDeviceId = collectDeviceId;
	}
	public Integer getDeviceSensorBindId() {
		return deviceSensorBindId;
	}
	public void setDeviceSensorBindId(Integer deviceSensorBindId) {
		this.deviceSensorBindId = deviceSensorBindId;
	}
	
	
	public Integer getChannelStatus() {
		return channelStatus;
	}
	public void setChannelStatus(Integer channelStatus) {
		this.channelStatus = channelStatus;
	}
	
	public Integer getDangerSourceId() {
		return dangerSourceId;
	}
	public void setDangerSourceId(Integer dangerSourceId) {
		this.dangerSourceId = dangerSourceId;
	}
	
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
	public Integer getCollectSensorId() {
		return collectSensorId;
	}
	public void setCollectSensorId(Integer collectSensorId) {
		this.collectSensorId = collectSensorId;
	}
	/**
	 * 创建树
	 * @return
	 */
	public String createSearchTree()
	{
		List<TreeNode> treeNodes =deviceSensorBindImpl.createSearchTree(getLoginUser());
		Gson gson = new Gson();
        String jsons = gson.toJson(treeNodes);
        ajax(jsons);
        return null;
	}
	/**
	 * 页面进入，显示当前通道信息
	 * @return
	 */
	public String getInfo()
	{
		DeviceSensorBind deviceSensorBind=deviceSensorBindImpl.findOne(deviceSensorBindId);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String channelName = deviceSensorBind.getCollectDevice().getInstallPosition()
				+" - "+deviceSensorBind.getChannelNo();
		jsonMap.put("channelName", channelName);
		Gson gson = new Gson();
        String jsons = gson.toJson(jsonMap);
        ajax(jsons);
		return null;
	}
	/**
	 * 获得可绑定的传感器列表
	 * @return
	 */
	public String getCollectSensorSelect()
	{
		String ids=deviceSensorBindImpl.findBindedCollectSensorIds(collectDeviceId);
		List<CollectSensor> list=collectSensorImpl.findCollectSensorList(dangerSourceId, ids);
		if(list!=null){
			List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
			for(CollectSensor d:list){
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("value",d.getVid());
				map.put("name", d.getSensorName());
				mapList.add(map);
			}
			Gson json =new Gson();
			ajax(json.toJson(mapList));
		}
		return null;
	}
	public String getList(){
		try {
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "channelNo"));
			Page<DeviceSensorBind> pageInfo = deviceSensorBindImpl.findDeviceSensorBindList(this.getPageable(sort), collectDeviceId);
			List<DeviceSensorBind> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			if (null != list && list.size() > 0) {
				for (DeviceSensorBind deviceSensorBind : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("channelNo", deviceSensorBind.getChannelNo());
					
					map.put("vid", deviceSensorBind.getId());
					
					map.put("dangerSourceName", deviceSensorBind.getCollectSensor().getDangerSource().getDangerSourceName());
					
					map.put("sensorName", deviceSensorBind.getCollectSensor().getSensorName());
					String range= ((deviceSensorBind.getCollectSensor().getRangeLow()==null)?
							"":deviceSensorBind.getCollectSensor().getRangeLow().toString())+"~"+
							((deviceSensorBind.getCollectSensor().getRangeHigh()==null)?
									"":deviceSensorBind.getCollectSensor().getRangeHigh());
					map.put("range", range);
					String alogRange= ((deviceSensorBind.getCollectSensor().getAlogRangeLow()==null)?
							"":deviceSensorBind.getCollectSensor().getAlogRangeLow().toString())+"~"+
							((deviceSensorBind.getCollectSensor().getAlogRangeHith()==null)?
									"":deviceSensorBind.getCollectSensor().getAlogRangeHith());
					map.put("alogRange", alogRange);
					String channelStatus="未启用";
					if(deviceSensorBind.getChannelStatus().equals(0))
						channelStatus="未启用";
					else if(deviceSensorBind.getChannelStatus().equals(1))
						channelStatus="未预警";
					else if(deviceSensorBind.getChannelStatus().equals(2))
						channelStatus="预警中";
					map.put("channelStatus", channelStatus);
					
					mapList.add(map);
				}
			}
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("iTotalRecords", pageInfo.getTotalElements());
			jsonMap.put("iTotalDisplayRecords", pageInfo.getTotalElements());
			jsonMap.put("aaData", mapList);
			Gson json = new Gson();
			String jsons = json.toJson(jsonMap);
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(jsons);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	public String update()
	{
		String log="绑定";
		deviceSensorBindImpl.bind(deviceSensorBindId, collectDeviceId, collectSensorId);
		//记录日志
		logService.saveOrUpdate(Tool.generateLog(getLoginUser(), 
				this.getRequest().getRemoteAddr(), 
				log, 
				LogType.LOG_TD_BIND, 
				null, 
				deviceSensorBindId) );
		ajax("1");
		return null;
	}
	/**
	 * 设置通道状态
	 * @return
	 */
	public String setChannelStatus(){
		try {
			String log="";
			DeviceSensorBind deviceSensorBind=deviceSensorBindImpl.findOne(deviceSensorBindId);
			if(channelStatus.equals(0)){
				deviceSensorBind.setCollectDevice(null);
				deviceSensorBind.setCollectSensor(null);
				log="取消绑定通道";
			}
			else if(channelStatus.equals(1)){
				log="禁用通道";
			}
			deviceSensorBind.setChannelStatus(channelStatus);
			deviceSensorBindImpl.saveOrUpdate(deviceSensorBind);
			LOGGER.info("设置通道状态，vid："+deviceSensorBindId+",channelStatus:"+channelStatus);
			
			
			//记录日志
			logService.saveOrUpdate(Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					log, 
					LogType.LOG_TD_STATUS, 
					deviceSensorBind, 
					deviceSensorBindId) );
			ajax("1");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("设置通道状态，vid="+deviceSensorBindId+",异常信息："+e.getMessage());
			ajax("0");
		}
		return null;
	}
}
