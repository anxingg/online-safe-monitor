/** 
 * @author 作者姓名  E-mail: email地址 
 * @version 创建时间：2015-8-27 下午03:10:43 
 * 类说明 
 */
package cn.com.wh.yjyl.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.file.domain.Attachment;
import cn.com.qytx.cbb.file.service.IAttachment;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.base.query.Sort.Order;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.wh.plans.domain.Plans;
import cn.com.wh.plans.service.IPlans;
import cn.com.wh.safeaccident.util.Tool;
import cn.com.wh.support.SessionSupport;
import cn.com.wh.util.DataInitUtil;
import cn.com.wh.util.DateUtil;
import cn.com.wh.yjyl.domain.WHContingencyPlansExe;
import cn.com.wh.yjyl.service.YjylService;

import com.google.gson.Gson;

/**
 * @ClassName:     YjylAction.java
 * @Description:   (用一句话描述该文件做什么) 
 * 
 * @author         wangxj
 * @version        V1.0  
 * @Date           2015-8-27 下午03:10:43 
 */
public class YjylAction extends BaseActionSupport{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 查询service */
	@Resource(name = "yjylServiceImpl")
	YjylService yjylServiceImpl;
	
	/** 查询service */
	@Autowired
	public IPlans plansImpl;
	
	@Resource(name="attachmentService")
	private IAttachment attachmentService;
	
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;
	
	WHContingencyPlansExe whContingencyPlansExe;
	
	public String ac_flag;
	
	private List<Attachment> fileList = new ArrayList<Attachment>();
	
	public void query(){
		UserInfo userInfo = this.getSessionSupport().getCurrentLoginUser();
		String groupId = null==this.getRequest().getParameter("group_id")?String.valueOf(userInfo.getGroupId()):this.getRequest().getParameter("group_id");//从用户信息中获取,政府端查看的时候，输入的是此信息
		String exercise_name = null==this.getRequest().getParameter("exercise_name")?"":this.getRequest().getParameter("exercise_name");
		int plan_type = null==this.getRequest().getParameter("plan_type")?-1:Integer.parseInt(this.getRequest().getParameter("plan_type"));
		//int plan_id = null==this.getRequest().getParameter("plan_no")?-1:Integer.parseInt(this.getRequest().getParameter("plan_no"));
		int whroletype = ((SessionSupport)this.getSessionSupport()).getCurrentWHRoleType();
		if(whroletype==2){
			groupId = String.valueOf(userInfo.getGroupId());
		}else{
			groupId = null==groupId?"":groupId;
		}
		String plan_id = "".equals(fetchFromRequest("plan_id"))?"":fetchFromRequest("plan_id");
		int _plan_id = -1;
		int _plan_type=-1;
		if(plan_id.length()>0){
			_plan_id = Integer.parseInt((plan_id.split(","))[0]);
			_plan_type = Integer.parseInt((plan_id.split(","))[1]);
		}
		
		if(-1==plan_type){
			plan_type = _plan_type;
		}
		
		int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()/(double) this.getIDisplayLength())) + 1;
		Order order = new Order(Direction.DESC, "vid");
		Sort s = new Sort(order);
		
		Page<WHContingencyPlansExe> pageInfo = yjylServiceImpl.queryByConPage(getPageable(s),groupId,exercise_name, plan_type,_plan_id);
		List<WHContingencyPlansExe> queryList = null==pageInfo.getContent()?new ArrayList<WHContingencyPlansExe>():pageInfo.getContent();
		int i = (pageNum - 1) * this.getIDisplayLength() + 1;
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		if (null != queryList && queryList.size() > 0) {
			for (WHContingencyPlansExe wHContingencyPlansExe : queryList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("vid", wHContingencyPlansExe.getVid());
				map.put("id", i);
				//exercise_program 应急演练项目
				map.put("exercise_program",wHContingencyPlansExe.getExercise_program());
				//exercise_time 演练时间
				
				map.put("exercise_time", DateUtil.date2Str(wHContingencyPlansExe.getExercise_time(),"yyyy-MM-dd"));
				//exercise_address 演练地点
				
				map.put("exercise_address", wHContingencyPlansExe.getExercise_address());
				//组织部门   organize_group
				map.put("organize_group", wHContingencyPlansExe.getOrganize_group());
				//reviews 负责人
				map.put("reviews", wHContingencyPlansExe.getReviews());
				
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
		int vid =-1;
		if(!"".equals(fetchFromRequest("vid"))){
			vid = Integer.parseInt(fetchFromRequest("vid"));
		}
		ac_flag=null==ac_flag?"detail":ac_flag;
		whContingencyPlansExe = new WHContingencyPlansExe();
		whContingencyPlansExe = yjylServiceImpl.queryDetail(vid);
		
		if (whContingencyPlansExe!=null && StringUtils.isNotBlank(whContingencyPlansExe.getAttachment_ids())) {
        	//查询附件
        	fileList = attachmentService.getAttachmentsByIds(whContingencyPlansExe.getAttachment_ids());
		}
		if(ac_flag.equals("detail")){
			return "detail";
		}else{
			return "update";
		}
	}
	
	
	public void del(){
		int vid =-1;
		if(!"".equals(fetchFromRequest("vid"))){
			vid = Integer.parseInt(fetchFromRequest("vid"));
		}
		try{
			yjylServiceImpl.delete(vid, true);
			ajax(0);
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"删除应急演练成功", 
					LogType.LOG_YJYL_DELETE, 
					null, 
					vid) );
		}catch(Exception e){
			e.printStackTrace();
			ajax(1);
		}
	}
	
	
	public void saveorup(){
		UserInfo userInfo = this.getSessionSupport().getCurrentLoginUser();
		String groupId = null==this.getRequest().getParameter("group_id")?String.valueOf(userInfo.getGroupId()):this.getRequest().getParameter("group_id");
		String exercise_name =  fetchFromRequest("exercise_name");
		int plan_type = "".equals(fetchFromRequest("plan_type"))?-1:Integer.parseInt(fetchFromRequest("plan_type"));
		String plan_no = fetchFromRequest("plan_no");
		String plan_id = "".equals(fetchFromRequest("plan_id"))?"":fetchFromRequest("plan_id");
		int _plan_id = -1;
		if(plan_id.length()>0){
			_plan_id = Integer.parseInt((plan_id.split(","))[0]);
			plan_type = Integer.parseInt((plan_id.split(","))[1]);
			
			Plans plans = plansImpl.findOne(_plan_id);
			plan_no = plans.getPlanNo();
		}
		
		//依据_plan_id 获取plan_no
		String company_name = DataInitUtil.companyMap.get(Integer.parseInt(groupId));
		String organize_group =  fetchFromRequest("organize_group");
		String exercise_program = fetchFromRequest("exercise_program");
		String exercise_purpose = fetchFromRequest("exercise_purpose");
		String exercise_time = fetchFromRequest("exercise_time");
		String exercise_address = fetchFromRequest("exercise_address");
		String exercise_people = fetchFromRequest("exercise_people");
		
		String exercise_records = fetchFromRequest("exercise_records");
		String rescue_reviews = fetchFromRequest("rescue_reviews");
		String reviews = fetchFromRequest("reviews");
		String photo_path = fetchFromRequest("photo_path");
		String attachment_ids = fetchFromRequest("attachmentId");
		
		
		
		WHContingencyPlansExe wHContingencyPlansExesave = new WHContingencyPlansExe();
		try{
			if(!ac_flag.equals("add")){
				int vid =-1;
				if(!"".equals(fetchFromRequest("vid"))){
					vid = Integer.parseInt(fetchFromRequest("vid"));
				}
				
				wHContingencyPlansExesave = yjylServiceImpl.findOne(vid);
				
				wHContingencyPlansExesave.setAttachment_ids(attachment_ids);//演练照片id
				wHContingencyPlansExesave.setCompany_name(company_name);
				wHContingencyPlansExesave.setCreate_time(new Date());
				wHContingencyPlansExesave.setExercise_address(exercise_address);
				wHContingencyPlansExesave.setExercise_name(exercise_name);
				wHContingencyPlansExesave.setExercise_people(exercise_people);
				wHContingencyPlansExesave.setExercise_program(exercise_program);
				wHContingencyPlansExesave.setExercise_purpose(exercise_purpose);
				wHContingencyPlansExesave.setExercise_records(exercise_records);
				wHContingencyPlansExesave.setExercise_time(DateUtil.str2Dateofday(exercise_time));
				wHContingencyPlansExesave.setGroup_id(Integer.parseInt(groupId));
				wHContingencyPlansExesave.setIs_delete(0);
				wHContingencyPlansExesave.setIs_fork_group(Integer.parseInt(groupId));
				wHContingencyPlansExesave.setOrganize_group(organize_group);
				wHContingencyPlansExesave.setPhoto_path(photo_path);
				wHContingencyPlansExesave.setPlan_id(_plan_id);
				wHContingencyPlansExesave.setPlan_no(plan_no);
				wHContingencyPlansExesave.setPlan_type(plan_type);
				wHContingencyPlansExesave.setRescue_reviews(rescue_reviews);
				wHContingencyPlansExesave.setReviews(reviews);
				
			}else{
				wHContingencyPlansExesave.setAttachment_ids(attachment_ids);//演练照片id
				wHContingencyPlansExesave.setCompany_name(company_name);
				wHContingencyPlansExesave.setCreate_time(new Date());
				wHContingencyPlansExesave.setExercise_address(exercise_address);
				wHContingencyPlansExesave.setExercise_name(exercise_name);
				wHContingencyPlansExesave.setExercise_people(exercise_people);
				wHContingencyPlansExesave.setExercise_program(exercise_program);
				wHContingencyPlansExesave.setExercise_purpose(exercise_purpose);
				wHContingencyPlansExesave.setExercise_records(exercise_records);
				wHContingencyPlansExesave.setExercise_time(DateUtil.str2Dateofday(exercise_time));
				wHContingencyPlansExesave.setGroup_id(Integer.parseInt(groupId));
				wHContingencyPlansExesave.setIs_delete(0);
				wHContingencyPlansExesave.setIs_fork_group(Integer.parseInt(groupId));
				wHContingencyPlansExesave.setOrganize_group(organize_group);
				wHContingencyPlansExesave.setPhoto_path(photo_path);
				wHContingencyPlansExesave.setPlan_id(_plan_id);
				wHContingencyPlansExesave.setPlan_no(plan_no);
				wHContingencyPlansExesave.setPlan_type(plan_type);
				wHContingencyPlansExesave.setRescue_reviews(rescue_reviews);
				wHContingencyPlansExesave.setReviews(reviews);
			}
			yjylServiceImpl.saveOrUpdate(wHContingencyPlansExesave);
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					ac_flag.equals("add") ? "新增应急演练成功" : "修改应急演练成功", 
					ac_flag.equals("add") ? LogType.LOG_YJYL_ADD : LogType.LOG_YJYL_UPDATE, 
					wHContingencyPlansExesave, 
					wHContingencyPlansExesave.getVid()) );
			ajax(0);
		}catch(Exception e){
			e.printStackTrace();
			ajax(1);
		}
	}
	
	
	
	
	
	public String fetchFromRequest(String key){
		return null==this.getRequest().getParameter(key)?"":this.getRequest().getParameter(key);
	}



	public String getAc_flag() {
		return ac_flag;
	}


	public void setAc_flag(String acFlag) {
		ac_flag = acFlag;
	}


	public void setYjylServiceImpl(YjylService yjylServiceImpl) {
		this.yjylServiceImpl = yjylServiceImpl;
	}


	public WHContingencyPlansExe getWhContingencyPlansExe() {
		return whContingencyPlansExe;
	}


	public void setWhContingencyPlansExe(WHContingencyPlansExe whContingencyPlansExe) {
		this.whContingencyPlansExe = whContingencyPlansExe;
	}

	public List<Attachment> getFileList() {
		return fileList;
	}

	public void setFileList(List<Attachment> fileList) {
		this.fileList = fileList;
	}
	
}
