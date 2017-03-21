/**
 * 
 */
package cn.com.wh.aqyh.action;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.base.query.Sort.Order;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.wh.aqyh.domain.WuhaiSafeDanger;
import cn.com.wh.aqyh.service.AqyhService;
import cn.com.wh.safeaccident.util.Tool;
import cn.com.wh.util.DataInitUtil;
import cn.com.wh.util.DateUtil;

import com.google.gson.Gson;

/**
 * @ClassName:     AqyhAction.java
 * @Description:   (用一句话描述该文件做什么) 
 * 
 * @author         wangxj
 * @version        V1.0  
 * @Date           2015-8-21 下午03:05:41 
 */
public class AqyhAction extends BaseActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String group_id;
	public String danger_name;
	public String responsible_department;
	public String responsible;
	public String checkdate;
	public String check_condition;
	public String rectification_end_date;
	public String rectification;
	public String review_user;
	public String review_time;
	public String memo ;
	public int is_fork_group;
	public int data_source;
	public String create_time;

	public String last_update_time;
	
	public String vid;
	public String can_see;
	WuhaiSafeDanger wuhaiSafeDanger;
	
	public String ac_flag;
	
	
	/** 查询service */
	@Resource(name = "aqyhServiceImpl")
	AqyhService aqyhServiceImpl;
	
    /**
     * 系统日志接口
     */
    @Resource
    private ILog logService;
	
	List<WuhaiSafeDanger> queryList = new ArrayList<WuhaiSafeDanger>();
	
	
	public String query(){

		UserInfo userInfo = (UserInfo) getSession().getAttribute("adminUser");
		int whroletype = null==this.getSession().getAttribute("whroletype")?-1:(Integer)this.getSession().getAttribute("whroletype");
		if(whroletype==2){
			group_id = String.valueOf(userInfo.getGroupId());
		}else{
			group_id = null==group_id?"":group_id;
		}
		
		responsible_department = null==responsible_department?"":responsible_department;//检查部门
		responsible = null==responsible?"":responsible;//检查人
		review_time = null==review_time?"":review_time.replaceAll("-", "");//复查时间
		checkdate = null==checkdate?"":checkdate.replaceAll("-", "");//检查时间
		int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()/(double) this.getIDisplayLength())) + 1;
		Order order = new Order(Direction.DESC, "vid");
		Order order1 = new Order(Direction.DESC, "group_id");
		Sort s = new Sort(order1,order);
		data_source = null==this.getSession().getAttribute("whroletype")?1:(Integer)this.getSession().getAttribute("whroletype");
		Page<WuhaiSafeDanger> pageInfo = aqyhServiceImpl.queryByConPage(getPageable(s), group_id, review_time, checkdate, responsible_department, responsible,data_source);
		List<WuhaiSafeDanger> queryList = null==pageInfo.getContent()?new ArrayList<WuhaiSafeDanger>():pageInfo.getContent();
		int i = (pageNum - 1) * this.getIDisplayLength() + 1;
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		if (null != queryList && queryList.size() > 0) {
			for (WuhaiSafeDanger wuhaiSafeDanger : queryList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("vid", wuhaiSafeDanger.getVid());
				map.put("id", i);
				//check_condition 检查情况
				map.put("check_condition", wuhaiSafeDanger.getCheck_condition());
				//rectification_end_date 整改结束日期
				
				// 公司名称
				String companyName = DataInitUtil.companyMap.get(wuhaiSafeDanger.getGroup_id());

				map.put("companyName", companyName == null ? "-" : companyName);
				map.put("rectification_end_date", null==wuhaiSafeDanger.getRectification_end_date()?"-":DateUtil.timestamp2yyyymmddStr(wuhaiSafeDanger.getRectification_end_date()));
				//responsible_department 责任部门
				map.put("responsible_department", wuhaiSafeDanger.getResponsible_department());
				//responsible 责任人
				map.put("responsible", wuhaiSafeDanger.getResponsible());
				//rectification 整改情况
				map.put("rectification", null==wuhaiSafeDanger.getRectification()?"":wuhaiSafeDanger.getRectification());
				//review_user 复查责任人
				map.put("review_user", null==wuhaiSafeDanger.getReview_user()?"":wuhaiSafeDanger.getReview_user());
				//review_time 复查时间
				map.put("review_time", null==wuhaiSafeDanger.getReview_time()?"":DateUtil.timestamp2yyyymmddStr(wuhaiSafeDanger.getReview_time()));
				//data_source 数据来源   1.企业2政府
				int  data_source = wuhaiSafeDanger.getData_source();
				if(data_source==2){
					map.put("data_source", "否");
				}else{
					map.put("data_source", "是");
				}
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
		return null;
	}
	
	
	public String queryDetail(){
		vid = null==vid?"":vid;
		ac_flag = null==ac_flag?"detail":ac_flag;
		wuhaiSafeDanger = aqyhServiceImpl.queryDetail(vid);
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
			aqyhServiceImpl.delete(Integer.parseInt(vid), true);
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"删除安全隐患排查成功", 
					LogType.LOG_AQYH_DELETE, 
					null, 
					Integer.parseInt(vid)) );
			ajax(0);
		}catch(Exception e){
			e.printStackTrace();
			ajax(1);
		}
	}
	
	public void saveorup(){
		group_id = null==group_id?"":group_id;
		danger_name  = null==danger_name?" ":danger_name;
		responsible_department = null==responsible_department?" ":responsible_department;
		responsible = null==responsible?" ":responsible;
		checkdate = null==checkdate?(new Timestamp(System.currentTimeMillis())).toString():checkdate.concat(" 00:00:00.000");
		check_condition = null==check_condition?"":check_condition;
		rectification_end_date = (null==rectification_end_date||"".equals(rectification_end_date))?"":rectification_end_date.concat(" 00:00:00.000");
		rectification = null==rectification?" ":rectification;
		review_user = null==review_user?" ":review_user;
		review_time = (null==review_time||"".equals(review_time))? "":review_time.concat(" 00:00:00.000");
		memo = null==memo ?" ":memo;
		ac_flag = null==ac_flag?"add":ac_flag;
		can_see = null==can_see?"1":can_see;
		WuhaiSafeDanger whSafeDanger = new WuhaiSafeDanger();
		if(!ac_flag.equals("add")){
			vid = null==vid?"":vid;
			whSafeDanger = aqyhServiceImpl.findOne(Integer.parseInt(vid));
			
			whSafeDanger.setCheck_condition(check_condition);
			whSafeDanger.setCheckdate(DateUtil.str2Timestamp(checkdate));//应该为字符串
//			whSafeDanger.setCreate_time(new Timestamp(System.currentTimeMillis()));
//			whSafeDanger.setCreate_user(getLoginUser().getUserId());
			whSafeDanger.setDanger_name(danger_name);
			data_source = null==this.getSession().getAttribute("whroletype")?1:(Integer)this.getSession().getAttribute("whroletype");
			//whSafeDanger.setData_source(data_source);//从session中获取
			whSafeDanger.setGroup_id(Integer.parseInt(group_id));
			whSafeDanger.setGroup_name(DataInitUtil.companyMap.get(Integer.parseInt(group_id)));//从静态缓存中获取
			whSafeDanger.setIs_fork_group(Integer.parseInt(group_id));
			whSafeDanger.setLast_update_time(new Timestamp(System.currentTimeMillis()));
			whSafeDanger.setLast_update_user(getLoginUser().getUserId());//从session中获取
			whSafeDanger.setMemo(memo);
			whSafeDanger.setRectification(rectification);
			if(!"".equals(rectification_end_date)){
				whSafeDanger.setRectification_end_date(DateUtil.str2Timestamp(rectification_end_date));
			}
			whSafeDanger.setResponsible(responsible);
			whSafeDanger.setResponsible_department(responsible_department);
			if(!"".equals(rectification_end_date)){
				whSafeDanger.setReview_time(DateUtil.str2Timestamp(review_time));
			}			whSafeDanger.setReview_user(review_user);
			whSafeDanger.setCan_see(Integer.parseInt(can_see));
		}else{
			whSafeDanger.setCheck_condition(check_condition);
			whSafeDanger.setCheckdate(DateUtil.str2Timestamp(checkdate));//应该为字符串
			whSafeDanger.setCreate_time(new Timestamp(System.currentTimeMillis()));
			whSafeDanger.setCreate_user(getLoginUser().getUserId());
			whSafeDanger.setDanger_name(danger_name);
			data_source = null==this.getSession().getAttribute("whroletype")?1:(Integer)this.getSession().getAttribute("whroletype");
			whSafeDanger.setData_source(data_source);//从session中获取
			whSafeDanger.setGroup_id(Integer.parseInt(group_id));
			whSafeDanger.setGroup_name(DataInitUtil.companyMap.get(Integer.parseInt(group_id)));//从静态缓存中获取
			whSafeDanger.setIs_delete(0);
			whSafeDanger.setIs_fork_group(Integer.parseInt(group_id));
			whSafeDanger.setLast_update_time(new Timestamp(System.currentTimeMillis()));
			whSafeDanger.setLast_update_user(getLoginUser().getUserId());//从session中获取
			whSafeDanger.setMemo(memo);
			whSafeDanger.setRectification(rectification);
			if(!"".equals(rectification_end_date)){
				whSafeDanger.setRectification_end_date(DateUtil.str2Timestamp(rectification_end_date));
			}
			whSafeDanger.setResponsible(responsible);
			whSafeDanger.setResponsible_department(responsible_department);
			if(!"".equals(rectification_end_date)){
				whSafeDanger.setReview_time(DateUtil.str2Timestamp(review_time));
			}
			whSafeDanger.setReview_user(review_user);
			whSafeDanger.setCan_see(Integer.parseInt(can_see));
		}
		whSafeDanger = aqyhServiceImpl.saveOrUpdate(whSafeDanger);
		
		//记录日志
		logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
				this.getRequest().getRemoteAddr(), 
				ac_flag.equals("add") ? "新增安全隐患排查成功":"修改安全隐患排查成功", 
				ac_flag.equals("add") ? LogType.LOG_AQYH_ADD : LogType.LOG_AQYH_UPDATE, 
						whSafeDanger, 
						whSafeDanger.getVid()) );
		//ajax传输出数据
		ajax("0");
	}
	
	
	
	

	public String getGroup_id() {
		return group_id;
	}


	public void setGroup_id(String groupId) {
		group_id = groupId;
	}


	public String getDanger_name() {
		return danger_name;
	}


	public void setDanger_name(String dangerName) {
		danger_name = dangerName;
	}


	public String getResponsible_department() {
		return responsible_department;
	}


	public void setResponsible_department(String responsibleDepartment) {
		responsible_department = responsibleDepartment;
	}


	public String getResponsible() {
		return responsible;
	}


	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}


	public String getCheckdate() {
		return checkdate;
	}


	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}


	public String getCheck_condition() {
		return check_condition;
	}


	public void setCheck_condition(String checkCondition) {
		check_condition = checkCondition;
	}


	public String getRectification_end_date() {
		return rectification_end_date;
	}


	public void setRectification_end_date(String rectificationEndDate) {
		rectification_end_date = rectificationEndDate;
	}


	public String getRectification() {
		return rectification;
	}


	public void setRectification(String rectification) {
		this.rectification = rectification;
	}


	public String getReview_user() {
		return review_user;
	}


	public void setReview_user(String reviewUser) {
		review_user = reviewUser;
	}


	public String getReview_time() {
		return review_time;
	}


	public void setReview_time(String reviewTime) {
		review_time = reviewTime;
	}


	public String getMemo() {
		return memo;
	}


	public void setMemo(String memo) {
		this.memo = memo;
	}


	public int getIs_fork_group() {
		return is_fork_group;
	}

	public void setIs_fork_group(int isForkGroup) {
		is_fork_group = isForkGroup;
	}

	public int getData_source() {
		return data_source;
	}

	public void setData_source(int dataSource) {
		data_source = dataSource;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String createTime) {
		create_time = createTime;
	}	

	public String getLast_update_time() {
		return last_update_time;
	}


	public void setLast_update_time(String lastUpdateTime) {
		last_update_time = lastUpdateTime;
	}

	public String getVid() {
		return vid;
	}


	public void setVid(String vid) {
		this.vid = vid;
	}


	public WuhaiSafeDanger getWuhaiSafeDanger() {
		return wuhaiSafeDanger;
	}


	public void setWuhaiSafeDanger(WuhaiSafeDanger wuhaiSafeDanger) {
		this.wuhaiSafeDanger = wuhaiSafeDanger;
	}


	public AqyhService getAqyhServiceImpl() {
		return aqyhServiceImpl;
	}


	public void setAqyhServiceImpl(AqyhService aqyhServiceImpl) {
		this.aqyhServiceImpl = aqyhServiceImpl;
	}


	public List<WuhaiSafeDanger> getQueryList() {
		return queryList;
	}


	public void setQueryList(List<WuhaiSafeDanger> queryList) {
		this.queryList = queryList;
	}


	public String getAc_flag() {
		return ac_flag;
	}


	public void setAc_flag(String acFlag) {
		ac_flag = acFlag;
	}
	
}
