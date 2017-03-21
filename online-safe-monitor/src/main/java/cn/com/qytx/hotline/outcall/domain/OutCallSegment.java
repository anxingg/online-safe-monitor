package cn.com.qytx.hotline.outcall.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.com.qytx.platform.base.domain.BaseDomain;
import cn.com.qytx.platform.base.domain.DeleteState;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 
 * 功能:外呼号码段实体类 版本: 1.0 开发人员: 徐长江 创建日期: 2014-3-6 修改日期: 2014-3-6 修改列表:
 */
@Entity
@Table(name = "tb_outCall_segment")
public class OutCallSegment extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 5915683522952655293L;
	/**
	 * 标识
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vid;
	
	/**
	 * 外呼号码端号码
	 */
	@Column(name = "phone")
	private String phone;

	/**
	 * 
	 */
	@Column(name = "checkState")
	private Integer checkState;

	/**
	 * 是否删除 0未删除 1删除
	 */
	@DeleteState
	@Column(name = "is_delete")
	private Integer isDelete = 0;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Timestamp createTime;

	/**
	 * 创建人
	 */
	@Transient
	private UserInfo createUserInfo;

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getCheckState() {
		return checkState;
	}

	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public UserInfo getCreateUserInfo() {
		return createUserInfo;
	}

	public void setCreateUserInfo(UserInfo createUserInfo) {
		this.createUserInfo = createUserInfo;
	}

}

