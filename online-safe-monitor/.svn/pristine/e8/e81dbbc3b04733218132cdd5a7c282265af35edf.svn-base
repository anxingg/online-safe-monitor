package cn.com.qytx.hotline.holiday.domain;

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
@Table(name="tb_set_holiday")
public class Holiday extends BaseDomain implements Serializable{
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
     * 日期
     */
    @Column(name="holiday")
    private Timestamp holiday;
    /**
     * 是否为节假日 0：非节假日 1：是节假日
     */
    @Column(name="isHoliday")
    private Integer isHoliday;
    /**
     * 是否删除 0:为不删除 1:为删除
     */
    @Column(name="isDelete")
    private Integer isDelete;
    /**
     * 创建人id
     */
    @Column(name="create_user_id")
    private Integer createUserId;
    /**
     * 创建时间
     */
    @Column(name="create_Time")
    private Timestamp createTime;
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
   		return "Holiday [vid=" + vid + ", holiday=" + holiday + ", isHoliday=" + isHoliday
   				+ ", isDelete=" + isDelete + ", createUserId=" + createUserId + ", createTime="
   				+ createTime + ", lastUpdateUserId=" + lastUpdateUserId + ", isForkGroup=" + isForkGroup 
   				+ "]";
   	}
    
	public Integer getVid() {
		return vid;
	}
	public void setVid(Integer vid) {
		this.vid = vid;
	}
	public Timestamp getHoliday() {
		return holiday;
	}
	public void setHoliday(Timestamp holiday) {
		this.holiday = holiday;
	}
	public Integer getIsHoliday() {
		return isHoliday;
	}
	public void setIsHoliday(Integer isHoliday) {
		this.isHoliday = isHoliday;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
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
    
    
    
}
