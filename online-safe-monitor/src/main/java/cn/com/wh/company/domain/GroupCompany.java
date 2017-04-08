package cn.com.wh.company.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public GroupCompany(Integer vid, Integer groupId, Integer companyId) {
		super();
		this.vid = vid;
		this.groupId = groupId;
		this.companyId = companyId;
	}

	public GroupCompany() {
		super();
	}
    
    

}
