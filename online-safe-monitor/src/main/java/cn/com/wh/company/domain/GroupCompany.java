package cn.com.wh.company.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.qytx.platform.base.domain.DeleteState;

@Entity
@Table(name="tb_group_company")
public class GroupCompany implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2054199221039215419L;
	
	/**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="vid")
	private Integer vid;
    
    /**
	 * 部门id
	 */
    @Column(name="group_id")
	private Integer groupId;
    
    /**
	 * 单位id
	 */
    @Column(name="company_id")
	private Integer companyId;

    @Column(name="company_group_id")
	private Integer companyGroupId;
    
    /**
	 * 是否删除
	 */
	@DeleteState
	@Column(name="is_delete")
	private Integer isDelete;
	
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	

	public Integer getCompanyGroupId() {
		return companyGroupId;
	}

	public void setCompanyGroupId(Integer companyGroupId) {
		this.companyGroupId = companyGroupId;
	}

	public GroupCompany() {
		super();
	}


}
