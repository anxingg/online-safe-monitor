package cn.com.wh.exam.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import cn.com.qytx.platform.base.domain.DeleteState;


/**
 * 
 * <br/>功能:试卷实体类
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2015年8月21日
 * <br/>修改日期: 2015年8月21日
 * <br/>修改列表:
 */
@Entity
@Table(name = "tb_wuhai_papers")
public class ExamPaper implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VID",unique=true,nullable=false)
	private Integer id;//主键
	
	/**
	 * 公司id（部门id）
	 */
	@Column(name="group_id")
	private Integer groupId;
	
	/**
	 * 试卷名称
	 */
	@Column(name="paper_name")
	private String paperName;
	
	/**
	 * 试卷类型 
	 */
	@Column(name="PAPER_TYPE")
	private Integer paperType;
	
	/**
	 * 考试时长，单位为分钟
	 */
	@Column(name="PAPER_TIME")
	private Integer paperTime;
	
	/**
	 * 创建时间
	 */
	@Column(name="Create_Time")
	private Timestamp createTime;
	
	/**
	 * 删除状态
	 */
	@DeleteState
	@Column(name="Is_Delete")
	private Integer isDelete = 0;
	
	/**
	 * 数据权限
	 */
	@Column(name="IS_FORK_GROUP")
	private Integer isForkGroup;
	
	/**
	 * 状态 0生效 1失效
	 */
	@Column(name="PAPER_STATE")
	private Integer state = 0;
	
	/**
	 * 题数
	 */
	@Column(name="QUESTION_NUM")
	private Integer questionNum;
	
	/**
	 * 总分
	 */
	@Column(name="SCORE")
	private Integer score;

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

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public Integer getPaperType() {
		return paperType;
	}

	public void setPaperType(Integer paperType) {
		this.paperType = paperType;
	}

	public Integer getPaperTime() {
		return paperTime;
	}

	public void setPaperTime(Integer paperTime) {
		this.paperTime = paperTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsForkGroup() {
		return isForkGroup;
	}

	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	
	
	
	
}