package cn.com.wh.training.domain;

/**
 * 
 * <br/>功能:查询对象
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2015年8月27日
 * <br/>修改日期: 2015年8月27日
 * <br/>修改列表:
 */
public class SearchVo {
	
	private String userName;//用户姓名
	
	private String checkerName;//批准人
	
	private Integer sex;//性别
	
	private Integer trainingType;//培训类型
	
	private String trainingContext;//培训内容
	
	private String charge;//培训人
	
	private String trainingYear;//培训年度
	
	private String trainingTime;//培训时间
	
	private Integer groupId;//公司id

	private String speaker;//主讲人

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(Integer trainingType) {
		this.trainingType = trainingType;
	}

	public String getTrainingContext() {
		return trainingContext;
	}

	public void setTrainingContext(String trainingContext) {
		this.trainingContext = trainingContext;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	public String getTrainingYear() {
		return trainingYear;
	}

	public void setTrainingYear(String trainingYear) {
		this.trainingYear = trainingYear;
	}

	public String getTrainingTime() {
		return trainingTime;
	}

	public void setTrainingTime(String trainingTime) {
		this.trainingTime = trainingTime;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}
	

	
}

