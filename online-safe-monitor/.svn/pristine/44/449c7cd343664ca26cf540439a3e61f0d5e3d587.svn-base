package cn.com.wh.company.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.annotation.Resource;

import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.wh.company.domain.WHCompanyPerson;
import cn.com.wh.company.service.IWHCompanyPerson;
import cn.com.wh.safeaccident.util.Tool;

public class AddSpecialWorkPersonAction extends BaseActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 日志
	 */
	private final static MonitorLogger LOGGER =new Log4jImpl(AddSpecialWorkPersonAction.class);

	@Resource
	private IWHCompanyPerson wHCompanyPersonImpl;
	
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;
	
	private String name;
	
	private String groupName;
	
	private String idNumber;
	
	private String startTime;
	
	private String workType;

	private String issuingAuthority;
	
	private String certificateNum;
	
	private String replaceDate;
	
	private String certificateGetTime;
	
	private String memo;
	
	private Integer group_id;
	

	
	/**
	 * 添加用户
	 * @return
	 */
	public String addSpecialWorkPerson(){
		
		try {
			UserInfo userInfo = (UserInfo) getSession().getAttribute("adminUser");
			if(group_id==null || group_id == -1){				
				group_id = userInfo.getGroupId();
			}
			WHCompanyPerson person = new WHCompanyPerson();
			person.setName(name);
			person.setGroupName(groupName);
			person.setPersonType(3);//3.特种作业人员
			person.setGroupId(group_id);
			person.setStartTime(Timestamp.valueOf(startTime));
			person.setIdNumber(idNumber);
			person.setIssuingAuthority(issuingAuthority);
			person.setCertificateNum(certificateNum);
			if(replaceDate==null || replaceDate.equals("")){
				//person.setReplaceDate(null);
			}else{
				person.setReplaceDate(Timestamp.valueOf(replaceDate));
			}
			person.setWorkType(workType);
			if(certificateGetTime==null || certificateGetTime.equals("")){
				//person.setCertificateGetTime(null);
			}else{
				person.setCertificateGetTime(Timestamp.valueOf(certificateGetTime));				
			}
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
					"特种作业人员新增成功", 
					LogType.LOG_SWP_ADD, 
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



	public String getIdNumber() {
		return idNumber;
	}



	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
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



	public String getIssuingAuthority() {
		return issuingAuthority;
	}



	public void setIssuingAuthority(String issuingAuthority) {
		this.issuingAuthority = issuingAuthority;
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



	public String getCertificateGetTime() {
		return certificateGetTime;
	}



	public void setCertificateGetTime(String certificateGetTime) {
		this.certificateGetTime = certificateGetTime;
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



	public Integer getGroup_id() {
		return group_id;
	}



	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}
	
	
}
