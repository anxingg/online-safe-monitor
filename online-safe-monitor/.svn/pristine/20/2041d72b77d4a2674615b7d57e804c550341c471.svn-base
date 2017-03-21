package cn.com.wh.company.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.google.gson.Gson;

import cn.com.qytx.cbb.dict.service.IDict;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.wh.company.domain.WHCompanyPerson;
import cn.com.wh.company.service.IWHCompanyPerson;
import cn.com.wh.safeaccident.util.Tool;
import cn.com.wh.util.DataInitUtil;

public class UpdateWHCompanyPersonAction extends BaseActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 日志
	 */
	private final static MonitorLogger LOGGER =new Log4jImpl(UpdateWHCompanyPersonAction.class);

	@Resource
	private IWHCompanyPerson wHCompanyPersonImpl;
	
	@Resource 
	private IDict dictService;
	
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;
	
	private Integer personId;
	
	private WHCompanyPerson person;
	
	private String name;
	
	private String phone;
	
	private Integer sex;
	
	private String idNumber;
	
	private String educationDegree;
	
	private String job;
	
	private String certificateNum;
	
	private String replaceDate;
	
	private String workType;
	
	private String jobTitle;
	
	private String memo;
	
	private String groupName;
	
	private String startTime;
	
	private String certificateGetTime;
	
	private String issuingAuthority;
	
	private Integer group_id;
	
	private String photo;
	
	private Integer cardType;
	
	
	/**
	 * 获取人员信息
	 * @return
	 */
	public String getPersonInfo(){
		
		try {
			
			person = wHCompanyPersonImpl.findWHCompanyPerson(personId);
			
			Map<String, Object> map = new HashMap<String, Object>();
			if(person!=null){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String companyName = DataInitUtil.companyMap.get(person.getGroupId());
				map.put("companyName", companyName);
				map.put("name", person.getName());
				map.put("sex", person.getSex());
				map.put("groupName", person.getGroupName());
				map.put("idNumber", person.getIdNumber());
				map.put("educationDegree", person.getEducationDegree());
				Date startTime = person.getStartTime();
				if(startTime==null){
					map.put("startTime", "");
				}else{
					map.put("startTime", formatter.format(startTime));
				}
				map.put("job", person.getJob());
				map.put("issuingAuthority", person.getIssuingAuthority());
				map.put("certificateNum", person.getCertificateNum());
				Date replaceDate = person.getReplaceDate();
				if(replaceDate==null){
					map.put("replaceDate", "");
				}else{
					map.put("replaceDate", formatter.format(replaceDate));
				}
				Date certificateGetTime = person.getCertificateGetTime();
				if(certificateGetTime==null){
					map.put("certificateGetTime", "");
				}else{
					map.put("certificateGetTime", formatter.format(certificateGetTime));
				}
				map.put("phone", person.getPhone());
				// 职称
				map.put("jobTitle", person.getJobTitle());

				map.put("workType", person.getWorkType());
				map.put("memo", person.getMemo());
				Integer cardType = person.getCardType();
				map.put("cardType", cardType);
				//map<value,name>
				Map<String, String> dictmap = dictService.findMap("cardType", 1);
				if(cardType!=null){
					map.put("cardTypeName", dictmap.get(cardType.toString()));
				}else{
					map.put("cardTypeName", "");
				}
			}
			
			Gson json = new Gson();
			String jsons = json.toJson(map);
			
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
	 * 获取法人信息
	 * @return
	 */
	public String getLegalPersonInfo(){
		
		try {
			UserInfo userInfo = (UserInfo) getSession().getAttribute("adminUser");
			if(group_id==null){
				group_id = userInfo.getGroupId();
			}
			
			person = wHCompanyPersonImpl.findByGroupId(group_id);
			
			Map<String, Object> map = new HashMap<String, Object>();
			if(person!=null){
				map.put("name", person.getName());
				map.put("phone", person.getPhone());
				map.put("job", person.getJob());
				Integer cardType = person.getCardType();
				map.put("cardType", cardType);
				//map<value,name>
				Map<String, String> dictmap = dictService.findMap("cardType", 1);
				if(cardType!=null){
					map.put("cardTypeName", dictmap.get(cardType.toString()));
				}else{
					map.put("cardTypeName", "");
				}
				map.put("certificateNum", person.getCertificateNum());
				map.put("photo", person.getPhoto());
			}
			
			Gson json = new Gson();
			String jsons = json.toJson(map);
			
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
	 * 保存、修改法人 信息
	 * @return
	 */
	public String updateLegalPerson(){
		
		try {
			UserInfo userInfo = (UserInfo) getSession().getAttribute("adminUser");
			Integer groupId = userInfo.getGroupId();
			
			person = wHCompanyPersonImpl.findByGroupId(groupId);
			
			if(person==null){
				person = new WHCompanyPerson();
				person.setGroupId(groupId);
				person.setPersonType(1);//2.安全管理员
				person.setCreateUserId(userInfo.getUserId());
				person.setIsForkGroup(groupId);//权限
				person.setCreateTime(new Timestamp(System.currentTimeMillis()));
				person.setIsDelete(0);//是否删除
			}
			
			person.setName(name);
			person.setPhone(phone);
			person.setCardType(cardType);
			person.setCertificateNum(certificateNum);
			person.setJob(job);
			person.setPhoto(photo);
			person.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
			person.setLastUpdateUserId(userInfo.getUserId());
			
			wHCompanyPersonImpl.saveOrUpdate(person);
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"修改法人信息成功", 
					LogType.LOG_QYLP_UPDATE, 
					person, 
					person.getPersonId()) );
			
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(1);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	
	/**
	 * 保存修改安全管理人员 信息
	 * @return
	 */
	public String updatePerson(){
		
		try {
			UserInfo userInfo = (UserInfo) getSession().getAttribute("adminUser");
			person = wHCompanyPersonImpl.findWHCompanyPerson(personId);
			person.setName(name);
			person.setPhone(phone);
			person.setSex(sex);
			person.setIdNumber(idNumber);
			person.setEducationDegree(educationDegree);
			person.setJob(job);
			person.setCertificateNum(certificateNum);
			person.setReplaceDate(Timestamp.valueOf(replaceDate));
			person.setJobTitle(jobTitle);//职务
			person.setMemo(memo);
			person.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
			person.setLastUpdateUserId(userInfo.getUserId());
			
			int index = wHCompanyPersonImpl.updateWHCompanyPerson(person);
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"安全管理人员修改成功", 
					LogType.LOG_SMP_UPDATE, 
					person, 
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
	
	
	
	/**
	 * 保存修改 特殊作业人员 信息
	 * @return
	 */
	public String updateSpecialWorkPerson(){
		
		try {
			UserInfo userInfo = (UserInfo) getSession().getAttribute("adminUser");
			person = wHCompanyPersonImpl.findWHCompanyPerson(personId);
			//政府端修改人员公司
//			if(group_id!=null && group_id != -1){				
//				person.setGroupId(group_id);
//				person.setIsForkGroup(group_id);
//			}
			person.setName(name);
			person.setGroupName(groupName);
			person.setStartTime(Timestamp.valueOf(startTime));
			person.setIdNumber(idNumber);
			person.setWorkType(workType);	//工种
			person.setIssuingAuthority(issuingAuthority);
			person.setCertificateNum(certificateNum);
			if(replaceDate==null || replaceDate.equals("")){
				person.setReplaceDate(null);
			}else{				
				person.setReplaceDate(Timestamp.valueOf(replaceDate));
			}
			if(certificateGetTime==null || certificateGetTime.equals("")){
				person.setCertificateGetTime(null);
			}else{
				person.setCertificateGetTime(Timestamp.valueOf(certificateGetTime));
			}
			person.setMemo(memo);
			
			person.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
			person.setLastUpdateUserId(userInfo.getUserId());
			
			int index = wHCompanyPersonImpl.updateWHCompanyPerson(person);
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"特种作业人员修改成功", 
					LogType.LOG_SWP_UPDATE, 
					person, 
					person.getPersonId()) );
			
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(index);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	public Integer getPersonId() {
		return personId;
	}


	public void setPersonId(Integer personId) {
		this.personId = personId;
	}


	public WHCompanyPerson getPerson() {
		return person;
	}


	public void setPerson(WHCompanyPerson person) {
		this.person = person;
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


	public Integer getSex() {
		return sex;
	}


	public void setSex(Integer sex) {
		this.sex = sex;
	}


	public String getIdNumber() {
		return idNumber;
	}


	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}


	public String getEducationDegree() {
		return educationDegree;
	}


	public void setEducationDegree(String educationDegree) {
		this.educationDegree = educationDegree;
	}


	public String getJob() {
		return job;
	}


	public void setJob(String job) {
		this.job = job;
	}


	public String getCertificateNum() {
		return certificateNum;
	}


	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}


	public String getReplaceDate() {
		return replaceDate;
	}

	public void setReplaceDate(String replaceDate) {
		this.replaceDate = replaceDate;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getMemo() {
		return memo;
	}


	public void setMemo(String memo) {
		this.memo = memo;
	}


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getCertificateGetTime() {
		return certificateGetTime;
	}

	public void setCertificateGetTime(String certificateGetTime) {
		this.certificateGetTime = certificateGetTime;
	}

	public String getIssuingAuthority() {
		return issuingAuthority;
	}


	public void setIssuingAuthority(String issuingAuthority) {
		this.issuingAuthority = issuingAuthority;
	}

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	
}
