package cn.com.wh.plans.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import cn.com.qytx.cbb.dict.service.IDict;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.wh.plans.domain.Plans;
import cn.com.wh.plans.service.IPlans;
import cn.com.wh.safeaccident.util.Tool;
import cn.com.wh.util.DataInitUtil;

public class PlansAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	public IPlans plansImpl; 
	
	@Resource 
	private IDict dictService;
	
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;
	
	private Plans plans;
	
	private Integer vid;
	
	private String prepareTime;
	
	private String prepareEndTime;
	
	private Integer groupId;
	
	private Integer planType;
	//private 
	
	public String getList(){
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()
					/ (double) this.getIDisplayLength())) + 1;
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "createTime"));
			Page<Plans> pageInfo = plansImpl.findPlansByPage(this.getPageable(sort),groupId,prepareTime,prepareEndTime,planType);
			List<Plans> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {
				for (Plans plans : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("no", i);
					
					map.put("vid", plans.getVid());
					
					map.put("groupId", plans.getGroupId());
					
					map.put("planNo", plans.getPlanNo());
					// 公司名称
					String companyName = DataInitUtil.companyMap.get(plans.getGroupId());
					map.put("companyName", companyName == null ? "-" : companyName);
					// 预案类型
					planType = plans.getPlanType();
					Map<String, String> dictmap = dictService.findMap("planType", 1);
					if(planType!=null){
						map.put("planTypeName", dictmap.get(planType.toString())==null?"-":dictmap.get(planType.toString()));
					}else{
						map.put("planTypeName", "-");
					}
					
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					
					Date prepareDate = plans.getPrepareTime();
					if(prepareDate!=null){
						map.put("prepareTime", formatter.format(prepareDate));
					}else{
						map.put("prepareTime", "-");
					}
					
					Date prepareEndDate = plans.getPrepareEndTime();
					if(prepareEndDate!=null){
						map.put("prepareEndTime", formatter.format(prepareEndDate));
					}else{
						map.put("prepareEndTime", "-");
					}
					String attachmentIds = plans.getAttachmentIds();
					map.put("attachmentIds", attachmentIds == null ? "-" : attachmentIds);
					map.put("path", plans.getPath());
					
					String attachName = plans.getAttachName();
					map.put("attachName", attachName == null ? "无" : attachName);
					
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
	 * 新增
	 * @return
	 */
	public String add(){
		try {
			LOGGER.info("应急预案新增开始。。。");
			UserInfo userInfo = this.getSessionSupport().getCurrentLoginUser();
			groupId = userInfo.getGroupId();
			plans.setPrepareTime(Timestamp.valueOf(prepareTime));
			plans.setPrepareEndTime(Timestamp.valueOf(prepareEndTime));
			plans.setGroupId(groupId);
			plans.setIsForkGroup((groupId));
			plans.setCreateTime(new Timestamp(System.currentTimeMillis()));
			plans.setIsDelete(0);//是否删除
			
			plansImpl.saveOrUpdate(plans);
			LOGGER.info("应急预案新增结束，vid:"+plans.getVid()+",预案编号:"+plans.getPlanNo());
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"新增应急预案成功", 
					LogType.LOG_YJYA_ADD, 
					plans, 
					plans.getVid()) );
			ajax(1);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("应急预案新增异常，e:"+e.getMessage());
			ajax(0);
		}
		return null;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		try {
			Plans plans = plansImpl.findOne(vid);
			plans.setIsDelete(1);
			plansImpl.saveOrUpdate(plans);
			LOGGER.info("删除应急预案，vid："+vid+",预案编号："+plans.getPlanNo());
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"删除应急预案成功", 
					LogType.LOG_YJYA_DELETE, 
					plans, 
					vid) );
			ajax("1");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("删除应急预案异常，vid="+vid+",异常信息："+e.getMessage());
			ajax("0");
		}
		return null;
	}
	
	/**
	 * 修改
	 * @return
	 */
	public String update(){
		try {
			LOGGER.info("应急预案修改开始。。。");
			Plans oldPlans = plansImpl.findOne(plans.getVid());
			plans.setPrepareTime(Timestamp.valueOf(prepareTime));
			plans.setPrepareEndTime(Timestamp.valueOf(prepareEndTime));
			
			plans.setGroupId(oldPlans.getGroupId());
			plans.setIsForkGroup(oldPlans.getIsForkGroup());
			plans.setCreateTime(oldPlans.getCreateTime());
			plans.setIsDelete(oldPlans.getIsDelete());//是否删除
			
			plansImpl.saveOrUpdate(plans);
			LOGGER.info("应急预案修改结束，vid:"+plans.getVid()+",预案编号:"+plans.getPlanNo());
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"修改应急预案成功", 
					LogType.LOG_YJYA_UPDATE, 
					plans, 
					plans.getVid()) );
			ajax(1);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("应急预案修改异常，e:"+e.getMessage());
			ajax(0);
		}
		return null;
	}
	
	/**
	 * 获取信息
	 * @return
	 */
	public String getInfo(){
		try {
			
			Plans plans = plansImpl.findOne(vid);
			//格式化前台时间输出格式
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
			Date prepareDate = plans.getPrepareTime();
			plans.setPrepareDate(formatter.format(prepareDate));
			
			Date prepareEndDate = plans.getPrepareEndTime();
			plans.setPrepareEndDate(formatter.format(prepareEndDate));
			
			Integer planType = plans.getPlanType();
			Map<String, String> dictmap = dictService.findMap("planType", 1);
			if(planType!=null){
				plans.setPlanTypeName(dictmap.get(planType.toString()));
			}
			
			Gson json = new Gson();
			String jsons = json.toJson(plans);
			
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(jsons);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error("获取应急预案异常,vid="+vid+",e:"+e.getMessage());
		}
		return null;
	}

	/**
	 * 获取应急预案集合
	 * @return
	 */
	public String getPlansList(){
		
		try {
			List<Plans> list = plansImpl.unDeleted().findAll();
			Gson json = new Gson();
			String jsons = json.toJson(list);
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(jsons);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("getPlansList发生异常： "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	/** get   set */
	
	public Plans getPlans() {
		return plans;
	}

	public void setPlans(Plans plans) {
		this.plans = plans;
	}

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public String getPrepareTime() {
		return prepareTime;
	}

	public void setPrepareTime(String prepareTime) {
		this.prepareTime = prepareTime;
	}

	public String getPrepareEndTime() {
		return prepareEndTime;
	}

	public void setPrepareEndTime(String prepareEndTime) {
		this.prepareEndTime = prepareEndTime;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}


	public Integer getPlanType() {
		return planType;
	}


	public void setPlanType(Integer planType) {
		this.planType = planType;
	}
	
	
}
