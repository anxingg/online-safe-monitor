package cn.com.wh.collectdevice.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.gson.Gson;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.qytx.platform.org.dao.CompanyDao;
import cn.com.qytx.platform.org.dao.GroupDao;
import cn.com.qytx.platform.org.domain.CompanyInfo;
import cn.com.qytx.platform.org.domain.GroupInfo;
import cn.com.qytx.platform.org.service.impl.GroupImpl;
import cn.com.qytx.platform.utils.datetime.DateTimeUtil;
import cn.com.wh.collectdevice.domain.CollectDevice;
import cn.com.wh.collectdevice.service.ICollectDevice;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.company.impl.WHCompanyImpl;
import cn.com.wh.util.DataInitUtil;
import cn.com.wh.util.Tool;

public class CollectDeviceAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	public ICollectDevice collectDeviceImpl; 
		
	@Resource
    private WHCompanyImpl whCompanyImpl;
	
	@Resource
    private GroupImpl groupImpl;
	
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;
	
	//采集设备ID
	private Integer collectDeviceId;
	//设备状态
	private Integer deviceStatus;
	
	//查询关键字
	private String searchKey;
	
	//采集设备对象
	private CollectDevice collectDevice;
	//单位Group ID
	private Integer groupId;

	//安装时间
	private String installDate;
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getCollectDeviceId() {
		return collectDeviceId;
	}
	public void setCollectDeviceId(Integer collectDeviceId) {
		this.collectDeviceId = collectDeviceId;
	}
	public Integer getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(Integer deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	
	public String getInstallDate() {
		return installDate;
	}
	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}
	public String getList(){
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()
					/ (double) this.getIDisplayLength())) + 1;
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "createTime"));
			Page<CollectDevice> pageInfo = collectDeviceImpl.findCollectDeviceList(this.getPageable(sort),
					searchKey);
			List<CollectDevice> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {
				for (CollectDevice collectDevice : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("no", i);
					
					map.put("vid", collectDevice.getVid());
					
					map.put("companyName", collectDevice.getCompany().getCompanyName());
					
					map.put("installPosition", collectDevice.getInstallPosition());
					
					String deviceModel=DataInitUtil.getNamefromDict("deviceModel",collectDevice.getDeviceModel());
					map.put("deviceModel",deviceModel);
					
					map.put("channelCount", collectDevice.getChannelCount());
					
					map.put("deviceAddress", collectDevice.getDeviceAddress());
					
					String deviceStatus=DataInitUtil.getNamefromDict("deviceStatus",collectDevice.getDeviceStatus());
					map.put("deviceStatus",deviceStatus);
					
					mapList.add(map);
					i++;
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
	/**
	 * 删除
	 * @return
	 */
	public String deleteCollectDevice(){
		try {
			CollectDevice collectDevice=collectDeviceImpl.findOne(collectDeviceId);
			collectDeviceImpl.delete(collectDeviceId, true);
			LOGGER.info("删除采集设备，vid："+collectDeviceId);
			
			//记录日志
			logService.saveOrUpdate(Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"删除采集设备成功", 
					LogType.LOG_CJSB_DELETE, 
					collectDevice, 
					collectDeviceId) );
			ajax("1");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("删除采集设备异常，vid="+collectDeviceId+",异常信息："+e.getMessage());
			ajax("0");
		}
		return null;
	}
	/**
	 * 设置采集设备状态
	 * @return
	 */
	public String setCollectDeviceStatus(){
		try {
			CollectDevice collectDevice=collectDeviceImpl.findOne(collectDeviceId);
			collectDevice.setDeviceStatus(deviceStatus);
			collectDeviceImpl.saveOrUpdate(collectDevice);
			LOGGER.info("设置采集设备状态，vid："+collectDeviceId+",deviceStatus:"+deviceStatus);
			String log="";
			if(deviceStatus.equals(0)){
				log="停用采集设备";
			}
			else if(deviceStatus.equals(1)){
				log="启用采集设备";
			}
			else if(deviceStatus.equals(2)){
				log="维修采集设备";
			}
			else if(deviceStatus.equals(3)){
				log="网络中断采集设备";
			}
			//记录日志
			logService.saveOrUpdate(Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					log, 
					LogType.LOG_CJSB_STATUS, 
					collectDevice, 
					collectDeviceId) );
			ajax("1");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("设置采集 设备状态，vid="+collectDeviceId+",异常信息："+e.getMessage());
			ajax("0");
		}
		return null;
	}
	/**
	 * 获取信息
	 * @return
	 */
	public String getInfo(){
		try {
			collectDevice = collectDeviceImpl.findOne(collectDeviceId);
			Integer areaId=groupImpl.findOne(collectDevice.getCompany().getGroupId()).getParentId();
			collectDevice.setAreaId(areaId);
			Gson json = new Gson();
			String jsons = json.toJson(collectDevice);
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(jsons);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("getInfo发生异常： "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 保存
	 * @return
	 */
	public String save(){
		String log="采集设备新增";
		try{
			if(collectDeviceId!=null){//是修改
				collectDevice.setVid(collectDeviceId);
				log="采集设备修改";
			}
			LOGGER.info(log+"开始。。。");
			WHCompany whCompany=whCompanyImpl.findByGroupId(groupId);
			collectDevice.setCompany(whCompany);
			collectDevice.setInstallDate(DateTimeUtil.stringToDate(installDate, "yyyy-MM-dd"));
			collectDevice.setCreateTime(new Timestamp(System.currentTimeMillis()));
			collectDeviceImpl.saveOrUpdate(collectDevice);
			LOGGER.info(log+"结束，vid:"+collectDevice.getVid());
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					log+"成功", 
					LogType.LOG_CJSB_ADD, 
					collectDevice, 
					collectDevice.getVid()) );
			ajax(1);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(log+"异常，e:"+e.getMessage());
			ajax(0);
		}
		return null;
	}
}
