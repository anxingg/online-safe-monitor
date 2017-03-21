package cn.com.wh.exam.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * 功能:考试记录表用户
 * 版本: 1.0
 * 开发人员: wangy
 * 创建日期: 2015年8月25日
 * 修改日期: 2015年8月25日
 * 修改列表:
 */
@Entity
@Table(name = "tb_wuhai_test_user")
public class ExamUserTest implements java.io.Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VID",unique=true,nullable=false)
	private Integer id;//实体
	
	/**
	 * 群组id
	 */
	@Column(name="Group_Id",length=100)
	private Integer groupId;
	
	/**
	 * 考试id
	 */
	@Column(name="TEST_ID")
	private Integer testId;
	
	/**
	 * 试卷id
	 */
	@Column(name="PAPER_ID")
	private Integer paperId;
	
	/**
	 * 用户名
	 */
	@Column(name="USER_NAME")
	private String userName;
	
	/**
	 * 身份证
	 */
	@Column(name="ID_CARD_NUM")
	private String idCardNum;
	/**
	 * 成绩
	 */
	@Column(name="SCORE")
	private Double score;
	/**
	 * 考试名称
	 */
	@Column(name="TEST_NAME")
	private String testName;
	
	/**
	 * 试卷名称
	 */
	@Column(name="PAPER_NAME")
	private String paperName;
	
	/**
	 * 答题开始时间
	 */
	@Column(name="START_TIME")
	private Date startTime;
	
	/**
	 * 答题结束时间
	 */
	@Column(name="END_TIME")
	private Date endTime;
	
	
	/**
	 * 数据权限
	 */
	@Column(name="IS_FORK_GROUP")
	private Integer isForkGroup;

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

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdCardNum() {
		return idCardNum;
	}

	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}


	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getIsForkGroup() {
		return isForkGroup;
	}

	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}
	
	
}
