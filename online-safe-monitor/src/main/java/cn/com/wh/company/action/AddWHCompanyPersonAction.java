package cn.com.wh.company.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.company.domain.WHCompanyPerson;
import cn.com.wh.company.service.IWHCompany;
import cn.com.wh.company.service.IWHCompanyPerson;
import cn.com.wh.safeaccident.util.Tool;

public class AddWHCompanyPersonAction extends BaseActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 日志
	 */
	private final static MonitorLogger LOGGER =new Log4jImpl(AddWHCompanyPersonAction.class);

	@Resource
	private IWHCompanyPerson wHCompanyPersonImpl;
	
	@Autowired
	public IWHCompany companyImpl;
	
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;
	
	private String name;
	
	private String phone;
	
	private Integer sex;
	
	private String idNumber;
	
	private String educationDegree;
	
	private String job;
	
	private String certificateNum;
	
	private String replaceDate;
	
	private String jobTitle;
	
	private String memo;
	
	private Integer cardType;
	

	
	/**
	 * 添加用户
	 * @return
	 */
	public String addPerson(){
		
		try {
			UserInfo userInfo = this.getSessionSupport().getCurrentLoginUser();
			Integer group_id = userInfo.getGroupId();
			WHCompanyPerson person = new WHCompanyPerson();
			WHCompany wuhaiCompany = companyImpl.findByGroupId(group_id);
			person.setCompanyName(wuhaiCompany.getCompanyName());
			person.setName(name);
			person.setPhone(phone);
			person.setPersonType(2);//2.安全管理员
			person.setGroupId(group_id);
			person.setSex(sex);
			person.setIdNumber(idNumber);
			person.setEducationDegree(educationDegree);
			person.setJob(job);
			person.setCardType(cardType);
			person.setCertificateNum(certificateNum);
			person.setReplaceDate(Timestamp.valueOf(replaceDate));
			person.setJobTitle(jobTitle);
			person.setMemo(memo);
			person.setCreateUserId(userInfo.getUserId());
			//根据group_id 查询出单位的 is_Fork_Group	
			//这里原则上 IsForkGroup和group_id一样，可以直接使用
			person.setIsForkGroup(group_id);//权限
			person.setCreateTime(new Timestamp(System.currentTimeMillis()));
			person.setIsDelete(0);//是否删除
			
			int index = wHCompanyPersonImpl.addWHCompanyPerson(person);
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"安全管理人员新增成功", 
					LogType.LOG_SMP_ADD, 
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



	public String getJobTitle() {
		return jobTitle;
	}



	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}



	public String getMemo() {
		return memo;
	}



	public void setMemo(String memo) {
		this.memo = memo;
	}



	public Integer getCardType() {
		return cardType;
	}



	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	
	
	
}
