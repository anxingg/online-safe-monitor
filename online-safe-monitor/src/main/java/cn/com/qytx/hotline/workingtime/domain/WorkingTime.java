package cn.com.qytx.hotline.workingtime.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.qytx.platform.base.domain.BaseDomain;
/**
 * 作者：李贺
 * 创建时间：2014年12月7日
 * 修改时间：2014年12月7日
 *
 */
@Entity
@Table(name="tb_working_time")
public class WorkingTime extends BaseDomain implements Serializable{
	/**
     * 描述含义
     */
	private static final long serialVersionUID = 7010378565187747868L;
    
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer vid ;
    
    /**
     * 上午上班
     */
    @Column(name="am_go_work_time")
    private String amGoWorkTime;
    /**
     * 上午下班
     */
    @Column(name="am_off_work_time")
    private String amOffWorkTime;
    /**
     * 下午上班
     */
    @Column(name="pm_go_work_time")
    private String pmGoWorkTime;
    /**
     * 下午午下班
     */
    @Column(name="pm_off_work_time")
    private String pmOffWorkTime;
    /**
     * 是否删除 0:为不删除 1:为删除
     */
    @Column(name="isDelete")
    private Integer isDelete;
    /**
     * 最后修改人id
     */
    @Column(name="last_update_user_id")
    private Integer lastUpdateUserId;
    /**
     * 最后修改时间
     */
    @Column(name="last_update_time")
    private Timestamp lastUpdateTime;
    /**
     * 权限判断
     */
    @Column(name="is_fork_group")
    private Integer isForkGroup;
    
    @Override
	public String toString() {
		return "WorkingTime [vid=" + vid + ", amGoWorkTime=" + amGoWorkTime + ", amOffWorkTime=" + amOffWorkTime
				+ ", pmGoWorkTime=" + pmGoWorkTime + ", pmOffWorkTime=" + pmOffWorkTime + ", lastUpdateUserId="
				+ lastUpdateUserId + ", lastUpdateTime=" + lastUpdateTime + ", isDelete=" + isDelete 
				+ ", isForkGroup=" + isForkGroup + "]";
	}
    
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getLastUpdateUserId() {
		return lastUpdateUserId;
	}
	public void setLastUpdateUserId(Integer lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Integer getIsForkGroup() {
		return isForkGroup;
	}
	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}
	public String getAmGoWorkTime() {
		return amGoWorkTime;
	}
	public void setAmGoWorkTime(String amGoWorkTime) {
		this.amGoWorkTime = amGoWorkTime;
	}
	public String getAmOffWorkTime() {
		return amOffWorkTime;
	}
	public void setAmOffWorkTime(String amOffWorkTime) {
		this.amOffWorkTime = amOffWorkTime;
	}
	public String getPmGoWorkTime() {
		return pmGoWorkTime;
	}
	public void setPmGoWorkTime(String pmGoWorkTime) {
		this.pmGoWorkTime = pmGoWorkTime;
	}
	public String getPmOffWorkTime() {
		return pmOffWorkTime;
	}
	public void setPmOffWorkTime(String pmOffWorkTime) {
		this.pmOffWorkTime = pmOffWorkTime;
	}
    
}
