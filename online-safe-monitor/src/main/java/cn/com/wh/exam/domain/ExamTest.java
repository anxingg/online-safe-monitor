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
 * 功能:考试
 * 版本: 1.0
 * 开发人员: wangy
 * 创建日期: 2015年8月21日
 * 修改日期: 2015年8月21日
 * 修改列表:
 */
@Entity
@Table(name = "tb_wuhai_test")
public class ExamTest implements java.io.Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VID",unique=true,nullable=false)
	private Integer id;//实体
	
	/**
	 * 群组id
	 */
	@Column(name="Group_Id")
	private Integer groupId;
	
	/**
	 * 试卷名称
	 */
	@Column(name="Test_Name",length=100)
	private String testName;
	
	/**
	 * 考试类型
	 */
	@Column(name="Test_Type")
	private Integer testType;

	/**
	 * 试卷id
	 */
	@Column(name="Paper_Ids",length=500)
	private String PaperIds;
	
	/**
	 * 创建时间
	 */
	@Column(name="Create_Time")
	private Timestamp createTime;
	
	/**
	 * 数据权限
	 */
	@Column(name="IS_FORK_GROUP")
	private Integer isForkGroup;
	
	
	/**
	 * 删除状态
	 */
	@DeleteState
	@Column(name="Is_Delete")
	private Integer isDelete = 0;
	
	/**
	 * 是否生效 0 生效 1 失效
	 */
	@Column(name="state")
	private Integer state = 0;


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


	public String getTestName() {
		return testName;
	}


	public void setTestName(String testName) {
		this.testName = testName;
	}


	public Integer getTestType() {
		return testType;
	}


	public void setTestType(Integer testType) {
		this.testType = testType;
	}


	public String getPaperIds() {
		return PaperIds;
	}


	public void setPaperIds(String paperIds) {
		PaperIds = paperIds;
	}


	public Timestamp getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Timestamp createTime) {
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


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}
	
	
}
