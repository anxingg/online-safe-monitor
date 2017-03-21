package cn.com.wh.company.action;

import java.io.IOException;
import java.io.PrintWriter;
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
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.wh.company.domain.WHCompanyPerson;
import cn.com.wh.company.service.IWHCompany;
import cn.com.wh.company.service.IWHCompanyPerson;
import cn.com.wh.safeaccident.util.Tool;

public class WHCompanyPersonAction extends BaseActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 日志
	 */
	private final static MonitorLogger LOGGER =new Log4jImpl(WHCompanyPersonAction.class);

	@Resource
	private IWHCompanyPerson wHCompanyPersonImpl;
	
	@Autowired
	public IWHCompany companyImpl;
	
	@Resource 
	private IDict dictService;
	
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;
	
	private String name;
	
	private String phone;
	
	private Integer personType;
	
	private Integer personId;
	
	private Integer groupId;
	
	private String minTime;
	
	private String maxTime;
	
	public String listWHCompanyPerson(){
		
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()
					/ (double) this.getIDisplayLength())) + 1;
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "createTime"));
			Page<WHCompanyPerson> pageInfo = wHCompanyPersonImpl.findByPage(this.getPageable(sort), personType, phone, name, null, null,groupId,personId,minTime,maxTime);
			List<WHCompanyPerson> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {
				for (WHCompanyPerson wHCompanyPerson : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("no", i);
					
					map.put("personId", wHCompanyPerson.getPersonId());
					
					//公司名
					String companyName = wHCompanyPerson.getCompanyName();
					map.put("companyName", companyName == null ? "-" : companyName);
					// 姓名
					String name = wHCompanyPerson.getName();

					map.put("name", name == null ? "-" : name);
					
					// 联系方式
					String phone = wHCompanyPerson.getPhone();

					map.put("phone", phone == null ? "-" : phone);
					
					// 换证时间
					Date replaceDate = wHCompanyPerson.getReplaceDate();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					map.put("replaceDate", replaceDate == null ? "-" : formatter.format(replaceDate));
					
					// 职务
					String job = wHCompanyPerson.getJob();

					map.put("job", job == null ? "-" : job);
					
					// 职称
					
					String workType = wHCompanyPerson.getWorkType();
					map.put("workType", workType == null? "-" : workType);
					
					//职务
					String jobTitle = wHCompanyPerson.getJobTitle();
					map.put("jobTitle", jobTitle == null? "-" : jobTitle);
					
				
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
	 * 删除人员
	 * @return
	 */
	public String ajaxDeletePersonById(){
		try{
			int index = wHCompanyPersonImpl.deleteWHCompanyPerson(personId);
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					personType == 3 ? "特种作业人员删除成功":"安全管理人员删除成功", 
					personType == 3 ? LogType.LOG_SWP_DELETE : LogType.LOG_SMP_DELETE, 
					null, 
					personId) );
			
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(index);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Integer getPersonType() {
		return personType;
	}

	public void setPersonType(Integer personType) {
		this.personType = personType;
	}


	public Integer getPersonId() {
		return personId;
	}


	public void setPersonId(Integer personId) {
		this.personId = personId;
	}


	public Integer getGroupId() {
		return groupId;
	}


	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}


	public String getMinTime() {
		return minTime;
	}


	public void setMinTime(String minTime) {
		this.minTime = minTime;
	}


	public String getMaxTime() {
		return maxTime;
	}


	public void setMaxTime(String maxTime) {
		this.maxTime = maxTime;
	}
	
	
}
