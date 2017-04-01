/** 
 * @author 作者姓名  E-mail: email地址 
 * @version 创建时间：2015-8-25 上午11:20:27 
 * 类说明 
 */
package cn.com.wh.zhdwxy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.qytx.cbb.dict.domain.Dict;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.base.query.Sort.Order;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.wh.safeaccident.util.Tool;
import cn.com.wh.support.SessionSupport;
import cn.com.wh.util.DataInitUtil;
import cn.com.wh.util.DateUtil;
import cn.com.wh.zhdwxy.domain.WhDangerSources;
import cn.com.wh.zhdwxy.service.WhDangerSourcesServiceImpl;

import com.google.gson.Gson;


/**
 * @ClassName:     ZhdwxyAction.java
 * @Description:   (用一句话描述该文件做什么) 
 * 
 * @author         wangxj
 * @version        V1.0  
 * @Date           2015-8-25 上午11:20:27 
 */
public class ZhdwxyAction extends BaseActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String vid;
	public String groupId;
	public String dangerSourcesName;
	public String safetyMeasures;
	public String ac_flag;
	public Integer grade;
	
	WhDangerSources whDangerSources = new WhDangerSources();
	
	/** 查询service */
	@Resource(name = "whDangerSourcesServiceImpl")
	WhDangerSourcesServiceImpl whDangerSourcesServiceImpl;
	
    /**
     * 系统日志接口
     */
    @Resource
    private ILog logService;
    
    /**
     * 危险性类别数据字典的Map
     */
    private Map<Integer, String> dangerObjTypeMap;
	
	/**
	 * 
	 */
	public void query(){
		UserInfo userInfo = this.getSessionSupport().getCurrentLoginUser();
		int whroletype = ((SessionSupport)this.getSessionSupport()).getCurrentWHRoleType();
		if(whroletype==2){
			groupId = String.valueOf(userInfo.getGroupId());
		}else{
			groupId = null==groupId?"":groupId;
		}
		dangerSourcesName = null==dangerSourcesName?"":dangerSourcesName;
		safetyMeasures = null==safetyMeasures?"":safetyMeasures;
		
		int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()/(double) this.getIDisplayLength())) + 1;
		Order order1 = new Order(Direction.DESC, "group_id");
		Order order = new Order(Direction.DESC, "vid");
		Sort s = new Sort(order1,order);
		
		Page<WhDangerSources> pageInfo = whDangerSourcesServiceImpl.queryByConPage(getPageable(s),groupId,dangerSourcesName, safetyMeasures,grade);
		List<WhDangerSources> queryList = null==pageInfo.getContent()?new ArrayList<WhDangerSources>():pageInfo.getContent();
		int i = (pageNum - 1) * this.getIDisplayLength() + 1;
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		if (null != queryList && queryList.size() > 0) {
			for (WhDangerSources whDangerSources : queryList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("vid", whDangerSources.getVid());
				map.put("id", i);
				//公司名称
				if (DataInitUtil.companyMap!=null&&DataInitUtil.companyMap.get(whDangerSources.getGroup_id())!=null) {
					map.put("companyName", DataInitUtil.companyMap.get(whDangerSources.getGroup_id()));
				}else {
					map.put("companyName", "");
				}
				//danger_sources_name 危险源名称
				map.put("danger_sources_name",whDangerSources.getDanger_sources_name());
				//danger_grade 危险源级别
				
				map.put("danger_grade", whDangerSources.getDanger_grade());
				map.put("grade_name", whDangerSources.getGrade()==0?"一般":"重大");
				//danger_grade 危险源级别名称
				
				map.put("danger_grade_name", whDangerSources.getDanger_grade_name());
				//review_time 评审时间
				//map.put("review_time", DateUtil.date2Str(whDangerSources.getReview_time()));
				//review_end_time 评审过期时间
				//map.put("review_end_time", DateUtil.date2Str(whDangerSources.getReview_end_time()));
				//accident_measures 发生事故时紧急措施
				//map.put("accident_measures", whDangerSources.getAccident_measures());
				//安全管理措施 safety_measures
				//map.put("safety_measures", whDangerSources.getSafety_measures());
				//评价机构
				map.put("mechanism", whDangerSources.getMechanism()==null?"":whDangerSources.getMechanism());
				mapList.add(map);
				i++;
			}
		}
		try{
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
		}catch(Exception e){
			LOGGER.error(e.toString());
		}
		
	}
	
	
	public String queryDetail(){
		vid = (null==vid?"":vid);
		ac_flag = (null==ac_flag?"detail":ac_flag);
		whDangerSources = new WhDangerSources();
		whDangerSources = whDangerSourcesServiceImpl.queryDetail(vid);
		
		//获取危险性类别数据字典
		dangerObjTypeMap = Tool.loadaccidentCharacterTypemap("dangerObjType");
		
		if(ac_flag.equals("detail")){
			return "detail";
		}else{
			return "update";
		}
	}
	
	
	public void del(){
		vid = null==vid?"":vid;
		try{
			//aqyhServiceImpl.delete(vid, true);
			whDangerSourcesServiceImpl.delete(Integer.parseInt(vid), true);
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"删除重大危险源成功", 
					LogType.LOG_ZDDANGER_DELETE, 
					null, 
					Integer.parseInt(vid)) );
			ajax(0);
		}catch(Exception e){
			e.printStackTrace();
			ajax(1);
		}
	}
	public void queryGrade(){
		try {
			List<Dict> list = DataInitUtil.getDictFromKey("dangerGrade");
			Gson json = new Gson();
			String jsons = json.toJson(list);
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(jsons);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public void saveorup(){
		UserInfo userInfo = this.getSessionSupport().getCurrentLoginUser();
		groupId = null==groupId?String.valueOf(userInfo.getGroupId()):groupId;//从用户信息中获取,政府端查看的时候，输入的是此信息
		dangerSourcesName = null==dangerSourcesName?"":dangerSourcesName;
		//safetyMeasures = null==safetyMeasures?"":safetyMeasures;
		String address = null==this.getRequest().getParameter("address")?"":this.getRequest().getParameter("address");
		String danger_grade = null==this.getRequest().getParameter("danger_grade")?"1":this.getRequest().getParameter("danger_grade");
		String danger_grade_name =DataInitUtil.getNamefromDict("dangerGrade", Integer.parseInt(danger_grade));//从静态数据字典表中获取
		//String review_time = null==this.getRequest().getParameter("review_time")?"":this.getRequest().getParameter("review_time");
		//String review_end_time = null==this.getRequest().getParameter("review_end_time")?"":this.getRequest().getParameter("review_end_time");
		String product_scale = null==this.getRequest().getParameter("product_scale")?"":this.getRequest().getParameter("product_scale");
		String distance = null==this.getRequest().getParameter("distance")?"0":this.getRequest().getParameter("distance");
		String three_year_accident = null==this.getRequest().getParameter("three_year_accident")?"":this.getRequest().getParameter("three_year_accident");
		//String accident_measures = null==this.getRequest().getParameter("accident_measures")?"":this.getRequest().getParameter("accident_measures");
		String mechanism = null==this.getRequest().getParameter("mechanism")?"":this.getRequest().getParameter("mechanism");
		String is_park = null==this.getRequest().getParameter("is_park")?"":this.getRequest().getParameter("is_park");
		String men = null==this.getRequest().getParameter("men")?"":this.getRequest().getParameter("men");
		String use_time = null==this.getRequest().getParameter("use_time")?"":this.getRequest().getParameter("use_time");
		String r_value = null==this.getRequest().getParameter("r_value")?"":this.getRequest().getParameter("r_value");
		String grade = null==this.getRequest().getParameter("grade")?"1":this.getRequest().getParameter("grade");
		
		WhDangerSources saveWhDangerSourcessave = new WhDangerSources();
		try{
			if(!ac_flag.equals("add")){
				vid = null==vid?"":vid;
				saveWhDangerSourcessave = whDangerSourcesServiceImpl.findOne(Integer.parseInt(vid));
				//saveWhDangerSourcessave.setAccident_measures(accident_measures);
				saveWhDangerSourcessave.setAddress(address);
				saveWhDangerSourcessave.setDanger_grade(Integer.parseInt(danger_grade));
				saveWhDangerSourcessave.setDanger_grade_name(danger_grade_name);
				saveWhDangerSourcessave.setDanger_sources_name(dangerSourcesName);
				saveWhDangerSourcessave.setDistance(Integer.parseInt(distance));
				saveWhDangerSourcessave.setProduct_scale(product_scale);
				//saveWhDangerSourcessave.setReview_end_time(DateUtil.str2Dateofday(review_end_time));
				//saveWhDangerSourcessave.setReview_time(DateUtil.str2Dateofday(review_time));
				
				//saveWhDangerSourcessave.setSafety_measures(safetyMeasures);
				saveWhDangerSourcessave.setThree_year_accident(three_year_accident);
				saveWhDangerSourcessave.setMechanism(mechanism);
				saveWhDangerSourcessave.setIs_park(is_park);
				saveWhDangerSourcessave.setMen(men);
				saveWhDangerSourcessave.setUse_time(DateUtil.str2Dateofday(use_time));
				saveWhDangerSourcessave.setR_value(r_value);
				saveWhDangerSourcessave.setGrade(Integer.parseInt(grade));
			}else{
				//saveWhDangerSourcessave.setAccident_measures(accident_measures);
				saveWhDangerSourcessave.setGrade(Integer.parseInt(grade));
				saveWhDangerSourcessave.setAddress(address);
				saveWhDangerSourcessave.setCreate_time(new Date());
				saveWhDangerSourcessave.setDanger_grade(Integer.parseInt(danger_grade));
				saveWhDangerSourcessave.setDanger_grade_name(danger_grade_name);
				saveWhDangerSourcessave.setDanger_sources_name(dangerSourcesName);
				saveWhDangerSourcessave.setDistance(Integer.parseInt(distance));
				saveWhDangerSourcessave.setGroup_id(Integer.parseInt(groupId));
				saveWhDangerSourcessave.setIs_delete(0);
				saveWhDangerSourcessave.setIs_fork_group(Integer.parseInt(groupId));
				saveWhDangerSourcessave.setLast_update_time(new Date());
				saveWhDangerSourcessave.setProduct_scale(product_scale);
				//saveWhDangerSourcessave.setReview_end_time(DateUtil.str2Dateofday(review_end_time));
				//saveWhDangerSourcessave.setReview_time(DateUtil.str2Dateofday(review_time));
				//saveWhDangerSourcessave.setSafety_measures(safetyMeasures);
				saveWhDangerSourcessave.setThree_year_accident(three_year_accident);
				saveWhDangerSourcessave.setGroup_name(DataInitUtil.companyMap.get(Integer.parseInt(groupId)));
				saveWhDangerSourcessave.setMechanism(mechanism);
				saveWhDangerSourcessave.setIs_park(is_park);
				saveWhDangerSourcessave.setMen(men);
				saveWhDangerSourcessave.setUse_time(DateUtil.str2Dateofday(use_time));
				saveWhDangerSourcessave.setR_value(r_value);
			}
			saveWhDangerSourcessave = whDangerSourcesServiceImpl.saveOrUpdate(saveWhDangerSourcessave);
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					ac_flag.equals("add") ? "新增重大危险源成功":"修改重大危险源成功", 
					ac_flag.equals("add") ? LogType.LOG_ZDDANGER_ADD : LogType.LOG_ZDDANGER_UPDATE, 
					saveWhDangerSourcessave, 
					saveWhDangerSourcessave.getVid()) );
			ajax(0);
		}catch(Exception e){
			LOGGER.error("新增或修改重大危险源时出错。", e);
			ajax(1);
		}
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getDangerSourcesName() {
		return dangerSourcesName;
	}

	public void setDangerSourcesName(String dangerSourcesName) {
		this.dangerSourcesName = dangerSourcesName;
	}

	public String getSafetyMeasures() {
		return safetyMeasures;
	}

	public void setSafetyMeasures(String safetyMeasures) {
		this.safetyMeasures = safetyMeasures;
	}

	public String getAc_flag() {
		return ac_flag;
	}

	public void setAc_flag(String acFlag) {
		ac_flag = acFlag;
	}

	public WhDangerSourcesServiceImpl getWhDangerSourcesServiceImpl() {
		return whDangerSourcesServiceImpl;
	}

	public void setWhDangerSourcesServiceImpl(
			WhDangerSourcesServiceImpl whDangerSourcesServiceImpl) {
		this.whDangerSourcesServiceImpl = whDangerSourcesServiceImpl;
	}

	public WhDangerSources getWhDangerSources() {
		return whDangerSources;
	}

	public void setWhDangerSources(WhDangerSources whDangerSources) {
		this.whDangerSources = whDangerSources;
	}

	public Map<Integer, String> getDangerObjTypeMap() {
		return dangerObjTypeMap;
	}

	public void setDangerObjTypeMap(Map<Integer, String> dangerObjTypeMap) {
		this.dangerObjTypeMap = dangerObjTypeMap;
	}


	public Integer getGrade() {
		return grade;
	}


	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
}
