package cn.com.wh.training.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.qytx.platform.base.domain.DeleteState;
/**
 * 
 * <br/>功能:岗前三级培训实体类
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2015年8月27日
 * <br/>修改日期: 2015年8月27日
 * <br/>修改列表:
 */
@Entity
@Table(name = "tb_wuhai_preservice_training")
public class PreserviceTraining implements java.io.Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VID",unique=true,nullable=false)
	private Integer id;//主键
	
	@Column(name="GROUP_ID")
	private Integer groupId;//组名称
	
	@Column(name="COMPANY_NAME")
	private String companyName;//公司名称
	
	@Column(name="USER_NAME")
	private String userName;//培训人姓名
	
	@Column(name="SEX")
	private Integer  sex;//性别
	
	@Column(name="AGE")
	private Integer age;//年龄
	
	@Column(name="RECIIVE_TYPE")
	private String receiveType;//录用形式
	
	@Column(name="TEST_RESULT")
	private String testResult;//体检结果
	
	@Column(name="NATIVE_PLACE")
	private String nativePlace;//来源地
	
	@Column(name="ONE_TEACH_CONTENT")
	private String oneTeachContent;//一级教育内容
	
	@Column(name="ONE_SCORE")
	private Integer oneScore;//一级教育分数
	
	@Column(name="ONE_EXAMINER")
	private String oneExaminer;//一级阅卷人
	
	@Column(name="ONE_HEADER")
	private String oneHeader;//一级安全负责人
	
	@Column(name="TWO_TEACH_CONTENT")
	private String twoTeachContent;//二级教育内容
	
	@Column(name="TWO_SCORE")
	private Integer twoScore;//二级教育分数
	
	@Column(name="TWO_EXAMINER")
	private String twoExaminer;//二级阅卷人
	
	@Column(name="TWO_HEADER")
	private String twoHeader;//二级教育负责人
	
	@Column(name="THREE_TEACH_CONTENT")
	private String threeTeachContent;//三级教育内容
	
	@Column(name="THREE_TEACH_TIME")
	private Date threeTeachTime;//三级教育时间
	
	@Column(name="THREE_STUDY_SITUATION")
	private String threeStudySituation;//三级教育掌握情况
	
	@Column(name="THREE_HEADER")
	private String threeHeader;//三级教育负责人
	
	@Column(name="OWNER_ATTITUDE")
	private String ownerAttitude;//个人态度
	
	@Column(name="CHECK_ATTITUDE")
	private String checkAttitude;//准上岗人意见
	
	@Column(name="CHECKER")
	private String checker;//批准人
	
	@Column(name="MEMO")
	private String memo;//备注
	
	@Column(name="PHOTO_PATH")
	private String photoPath;//照片路径
	
	@Column(name="ATTACHMENT_ID")
	private String attachmentId;//照片id
	
	@Column(name="CREATE_TIME")
	private Date createTime;//创建时间
	
	@Column(name="IS_FORK_GROUP")
	private Integer isForkGroup;//数据权限
	
	@DeleteState
	@Column(name="IS_DELETE")
	private Integer isDelete;//是否删除
	
	@Column(name="WORK_TYPE")
	private String workType;//工种
	
	@Column(name="POST")
	private String post;//岗位

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getReceiveType() {
		return receiveType;
	}

	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}

	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getOneTeachContent() {
		return oneTeachContent;
	}

	public void setOneTeachContent(String oneTeachContent) {
		this.oneTeachContent = oneTeachContent;
	}

	public Integer getOneScore() {
		return oneScore;
	}

	public void setOneScore(Integer oneScore) {
		this.oneScore = oneScore;
	}

	public String getOneExaminer() {
		return oneExaminer;
	}

	public void setOneExaminer(String oneExaminer) {
		this.oneExaminer = oneExaminer;
	}

	public String getOneHeader() {
		return oneHeader;
	}

	public void setOneHeader(String oneHeader) {
		this.oneHeader = oneHeader;
	}

	public String getTwoTeachContent() {
		return twoTeachContent;
	}

	public void setTwoTeachContent(String twoTeachContent) {
		this.twoTeachContent = twoTeachContent;
	}

	public Integer getTwoScore() {
		return twoScore;
	}

	public void setTwoScore(Integer twoScore) {
		this.twoScore = twoScore;
	}

	public String getTwoExaminer() {
		return twoExaminer;
	}

	public void setTwoExaminer(String twoExaminer) {
		this.twoExaminer = twoExaminer;
	}

	public String getTwoHeader() {
		return twoHeader;
	}

	public void setTwoHeader(String twoHeader) {
		this.twoHeader = twoHeader;
	}

	public String getThreeTeachContent() {
		return threeTeachContent;
	}

	public void setThreeTeachContent(String threeTeachContent) {
		this.threeTeachContent = threeTeachContent;
	}

	public Date getThreeTeachTime() {
		return threeTeachTime;
	}

	public void setThreeTeachTime(Date threeTeachTime) {
		this.threeTeachTime = threeTeachTime;
	}

	public String getThreeStudySituation() {
		return threeStudySituation;
	}

	public void setThreeStudySituation(String threeStudySituation) {
		this.threeStudySituation = threeStudySituation;
	}

	public String getThreeHeader() {
		return threeHeader;
	}

	public void setThreeHeader(String threeHeader) {
		this.threeHeader = threeHeader;
	}

	public String getOwnerAttitude() {
		return ownerAttitude;
	}

	public void setOwnerAttitude(String ownerAttitude) {
		this.ownerAttitude = ownerAttitude;
	}

	public String getCheckAttitude() {
		return checkAttitude;
	}

	public void setCheckAttitude(String checkAttitude) {
		this.checkAttitude = checkAttitude;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsForkGroup() {
		return isForkGroup;
	}

	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}
	
	
}
