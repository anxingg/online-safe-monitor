package cn.com.wh.company.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.Gson;

import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.wh.company.domain.WHCompanyPerson;
import cn.com.wh.company.service.IWHCompanyPerson;

public class SpecialWorkPersonAction extends BaseActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 日志
	 */
	private final static MonitorLogger LOGGER =new Log4jImpl(SpecialWorkPersonAction.class);

	@Resource
	private IWHCompanyPerson wHCompanyPersonImpl;
	
	private String name;
	
	private String workType;
	
	private String groupName;
	
	private Integer personType;

	
	private Integer personId;
	
	private Integer groupId;
	
	private String minTime;
	
	private String maxTime;
	
	
	
	/**
	 * 特种作业列表
	 * @return
	 */
	public String listSpecialWorkPerson(){
		
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()
					/ (double) this.getIDisplayLength())) + 1;
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "createTime"));
			Page<WHCompanyPerson> pageInfo = wHCompanyPersonImpl.findByPage(this.getPageable(sort), personType, null, name, workType, groupName, groupId, personId,minTime,maxTime);
			List<WHCompanyPerson> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {
				for (WHCompanyPerson wHCompanyPerson : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("no", i);
					
					map.put("personId", wHCompanyPerson.getPersonId());
					
					// 姓名
					String name = wHCompanyPerson.getName();

					map.put("name", name == null ? "-" : name);
					
					// 部门
					String groupName = wHCompanyPerson.getGroupName();

					map.put("groupName", groupName == null ? "-" : groupName);
					
					// 工种
					String workType = wHCompanyPerson.getWorkType();

					map.put("workType", workType == null ? "-" : workType);
					
					// 证书编号
					String certificateNum = wHCompanyPerson.getCertificateNum();

					map.put("certificateNum", certificateNum == null ? "-" : certificateNum);
					
					// 发证机关
					String issuingAuthority = wHCompanyPerson.getIssuingAuthority();

					map.put("issuingAuthority", issuingAuthority == null ? "-" : issuingAuthority);
				
				
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
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(index);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 根据编号获得职称
	 * @param workType
	 * @return
	 */
	public String getTitle(Integer workType){
		if(workType==null){
			return "-";
		}else{
			if(workType==1){
				return "A证";
			}else if(workType==2){
				return "B证";
			}else if(workType==3){
				return "C证";
			}else{
				return "-";
			}
		}
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
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
