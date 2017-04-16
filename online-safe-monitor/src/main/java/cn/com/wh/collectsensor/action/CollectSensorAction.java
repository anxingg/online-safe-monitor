package cn.com.wh.collectsensor.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
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
import cn.com.wh.dangersourceobject.domain.DangerSourceObject;
import cn.com.wh.thresholdtemplate.domain.ThresholdTemplate;
import cn.com.wh.thresholdtemplate.service.IThresholdTemplate;
import cn.com.wh.util.DataInitUtil;
import cn.com.wh.util.Tool;

public class CollectSensorAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	public ICollectSensor collectSensorImpl; 
		
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;
	
	//报表查询的类型
	private String report_type;
	//报表查询的数据ID
	private Integer report_vid;
	//危险源ID,根据危险源查询传感器
	private Integer dangerSourceId;
	//传感器ID
	private Integer collectSensorId;
	//传感器状态
	private Integer enableWarning;
	/**
	 * 创建树
	 * @return
	 */
	public String createSearchTree()
	{
		List<TreeNode> treeNodes =collectSensorImpl.createSearchTree(getLoginUser());
		Gson gson = new Gson();
        String jsons = gson.toJson(treeNodes);
        ajax(jsons);
        return null;
	}
	public String getInfo()
	{
		CollectSensor collectSensor =collectSensorImpl.findOne(collectSensorId);
		Gson gson = new Gson();
        String jsons = gson.toJson(collectSensor);
        ajax(jsons);
        return null;
	}
	/**
	 * 统计
	 * @return
	 */
	public String getReport()
	{
		Map<String, Integer> jsonMap=null;
		if(report_type.equals("all")){  //查询全部
			jsonMap=collectSensorImpl.stReportByAll();
		}
		else if(report_type.equals("gid")){  //查询某区域/企业的
			jsonMap=collectSensorImpl.stReportByArea(report_vid);
		}
		if(jsonMap!=null){
			Gson gson = new Gson();
	        String jsons = gson.toJson(jsonMap);
	        ajax(jsons);
		}
        return null;
	}
	public String getReport_type() {
		return report_type;
	}
	public void setReport_type(String report_type) {
		this.report_type = report_type;
	}
	public Integer getReport_vid() {
		return report_vid;
	}
	public void setReport_vid(Integer report_vid) {
		this.report_vid = report_vid;
	}
	
	public Integer getDangerSourceId() {
		return dangerSourceId;
	}
	public void setDangerSourceId(Integer dangerSourceId) {
		this.dangerSourceId = dangerSourceId;
	}
	
	public Integer getCollectSensorId() {
		return collectSensorId;
	}
	public void setCollectSensorId(Integer collectSensorId) {
		this.collectSensorId = collectSensorId;
	}
	public String getList(){
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()
					/ (double) this.getIDisplayLength())) + 1;
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "createTime"));
			Page<CollectSensor> pageInfo = collectSensorImpl.findCollectSensorList(this.getPageable(sort),
					dangerSourceId);
			List<CollectSensor> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {
				for (CollectSensor collectSensor : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("no", i);
					
					map.put("vid", collectSensor.getVid());
					
					map.put("templateNasensorNameme", collectSensor.getSensorName());
					
					String watchType=DataInitUtil.getNamefromDict("watchType",Integer.parseInt(collectSensor.getWatchType()));
					map.put("watchType",watchType);
					
					StringBuilder setting=new StringBuilder();
					for(DangerSourceObject dso : collectSensor.getDangerSource().getMonitoredObjects()){
						setting.append(",");
						setting.append(dso.getObjectName());
					}
					if(setting.length()>0)
						setting.delete(0, 1);
					map.put("setting", setting.toString());
					
					String enableWarning="停用";
					if(collectSensor.getEnableWarning().equals(0))
						enableWarning="停用";
					else
						enableWarning="启用";
					map.put("enableWarning", enableWarning);
					
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
	public String deleteCollectSensor(){
		try {
			CollectSensor collectSensor=collectSensorImpl.findOne(collectSensorId);
			collectSensor.setIsDelete(1);
			collectSensorImpl.saveOrUpdate(collectSensor);
			LOGGER.info("删除传感器，vid："+collectSensorId);
			
			//记录日志
			logService.saveOrUpdate(Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"删除传感器成功", 
					LogType.LOG_CGQ_DELETE, 
					collectSensor, 
					collectSensorId) );
			ajax("1");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("删除传感器异常，vid="+collectSensorId+",异常信息："+e.getMessage());
			ajax("0");
		}
		return null;
	}
	/**
	 * 设置传感器状态
	 * @return
	 */
	public String setCollectSensorEnable(){
		try {
			CollectSensor collectSensor=collectSensorImpl.findOne(collectSensorId);
			collectSensor.setEnableWarning(enableWarning);
			collectSensorImpl.saveOrUpdate(collectSensor);
			LOGGER.info("设置传感器状态，vid："+collectSensorId+",enableWarning:"+enableWarning);
			String log="";
			if(enableWarning.equals(0)){
				log="停用传感器";
			}
			else{
				log="启用传感器";
			}
			//记录日志
			logService.saveOrUpdate(Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					log, 
					LogType.LOG_CGQ_ENABLEWARNING, 
					collectSensor, 
					collectSensorId) );
			ajax("1");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("设置传感器状态，vid="+collectSensorId+",异常信息："+e.getMessage());
			ajax("0");
		}
		return null;
	}
}
