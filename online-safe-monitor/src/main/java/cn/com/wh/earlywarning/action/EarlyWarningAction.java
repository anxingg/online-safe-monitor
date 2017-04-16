/**
 * 
 */
package cn.com.wh.earlywarning.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.org.domain.GroupInfo;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IGroup;
import cn.com.wh.collectsensor.domain.CollectSensor;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.dangersource.domain.DangerSource;
import cn.com.wh.dangersourceobject.domain.DangerSourceObject;
import cn.com.wh.earlywarning.domain.EarlyWarning;
import cn.com.wh.earlywarning.domain.EarlyWarningVo;
import cn.com.wh.earlywarning.service.IEarlyWarning;
import cn.com.wh.util.DateUtil;

/**
 * 功能: 
 * 版本: 1.0
 * 开发人员: 王刚
 * 创建日期: 2017年4月13日
 * 修改日期: 2017年4月13日
 * 修改列表: 
 */
public class EarlyWarningAction extends BaseActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 331062058992620739L;
	
	private EarlyWarningVo earlyWarningVo;
	
	@Autowired
	private IEarlyWarning earlyWarningService;
	
	@Autowired
	private IGroup groupService;
	//1.查询区域 2.查询公司
	private Integer type;
	
	private Integer parentId;
	/**
	 * 获取预警列表
	 */
	public void getPageList(){
		UserInfo user =getLoginUser();
		if(user!=null){
			Page<EarlyWarning> pageInfo = earlyWarningService.pageInfo(getPageable(), earlyWarningVo);
			List<EarlyWarning> list = pageInfo.getContent();
			List<Map<String,Object>> MapList= new ArrayList<Map<String,Object>>();
			if(list!=null){
				for(EarlyWarning e : list){
					Map<String,Object> map = new HashMap<String, Object>();
					CollectSensor c=e.getCollectSensor();
					String dangerSource="--";
					String sensorName="--";
					String companyName="--";
					String cityName="--";
					String dangerSourceName="--";
					String watchType="--";
					if(c!=null){
						DangerSource d= c.getDangerSource();
						sensorName =c.getSensorName();
						watchType=c.getWatchType();
						if(d!=null){
							Set<DangerSourceObject> monitoredObjects=d.getMonitoredObjects();
							for(DangerSourceObject dso:monitoredObjects){
								dangerSource +=dso.getObjectName()+",";
							}
							dangerSource =dangerSource.substring(0, dangerSource.length()-1);
							dangerSourceName = d.getDangerSourceName();
							WHCompany company = d.getCompany();
							if(company!=null){
								companyName=company.getCompanyName();
								cityName=company.getCityName();
							}
						}
					}
					
					map.put("dangerSource",dangerSource);//检测对象
					map.put("sensorName",sensorName);//传感器名称
					map.put("companyName",companyName);//公司名称
					map.put("cityName",cityName);//地区名称
					String waringLevelStr="";
					if(e.getWaringLevel()==1){
						waringLevelStr="一级";
					}else if(e.getWaringLevel()==2){
						waringLevelStr="二级";
					}else{
						waringLevelStr="三级";
					}
					map.put("waringLevelStr",waringLevelStr);//预警级别
					String stateStr="未处理";
					if(e.getStatus()==0){
						stateStr="未处理";
					}else if(e.getStatus()==1){
						stateStr="已接受";
					}else{
						stateStr="已处理";
					}
					//状态
					map.put("state",stateStr);
					if(e.getBeginTime()!=null){
						
					}
					//预警时间
					map.put("beginTime",e.getBeginTime()!=null?DateUtil.date2Str(e.getBeginTime()):"--" );
					//危险源
					map.put("dangerSourceName",dangerSourceName);
					//检测类型
					map.put("watchType",watchType);
					map.put("vid",e.getId());
					MapList.add(map);
				}
			}
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("iTotalRecords", pageInfo.getTotalElements());
			jsonMap.put("iTotalDisplayRecords", pageInfo.getTotalElements());
			jsonMap.put("aaData", MapList);
			Gson json = new Gson();
			String jsons = json.toJson(jsonMap);
            ajax(jsons);
		}
	}
	/**
	 * 初始化查找县区或公司
	 */
	public void findCity0rCompany(){
		UserInfo user =getLoginUser();
		if(user!=null){
			List<GroupInfo> getGroupList =new ArrayList<GroupInfo>();
			List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
			if(type==1){
				getGroupList = groupService.getGroupList(1, 0, 1, parentId);
			}else{
				getGroupList = groupService.getGroupList(1, 2, -1, parentId);
			}
			if(getGroupList!=null){
				for(GroupInfo g:getGroupList){
					Map<String,Object> map =new HashMap<String, Object>();
					map.put("value", g.getGroupId());
					map.put("name", g.getGroupName());
					mapList.add(map);
				}
			}
			Gson json = new Gson();
			ajax(json.toJson(mapList));
		}
	}
	
	
	public EarlyWarningVo getEarlyWarningVo() {
		return earlyWarningVo;
	}
	public void setEarlyWarningVo(EarlyWarningVo earlyWarningVo) {
		this.earlyWarningVo = earlyWarningVo;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
   
	
}
