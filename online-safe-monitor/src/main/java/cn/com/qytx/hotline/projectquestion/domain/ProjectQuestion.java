package cn.com.qytx.hotline.projectquestion.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 功能:问题上报实体
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-12-20
 * 修改日期: 2014-12-20
 * 修改列表:
 */
@Entity
@Table(name="tb_project_question")
public class ProjectQuestion implements Serializable {
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -5682668366286812248L;
	/**
	 * 主键vid
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer vid;
	/**
	 * 内容
	 */
	@Column(name="content_info")
	private String contentInfo;
	/**
	 *创建人id 
	 */
	@Column(name="create_userId")
	private Integer createUserId;
	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	private Timestamp createTime;
	/**
	 * 解决人
	 */
	@Column(name="solve_people")
	private Integer solvePeople;
	/**
	 * 解决时间
	 */
	@Column(name="solve_time")
	private Timestamp solveTime;
	/**
	 * 区域权限
	 */
	@Column(name="is_frok_group")
	private Integer isForkGroup;
	
	
	
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	public String getContentInfo() {
		return contentInfo;
	}
	public void setContentInfo(String contentInfo) {
		this.contentInfo = contentInfo;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Integer getSolvePeople() {
		return solvePeople;
	}
	public void setSolvePeople(Integer solvePeople) {
		this.solvePeople = solvePeople;
	}
	public Timestamp getSolveTime() {
		return solveTime;
	}
	public void setSolveTime(Timestamp solveTime) {
		this.solveTime = solveTime;
	}
	public Integer getIsForkGroup() {
		return isForkGroup;
	}
	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}
	
}
