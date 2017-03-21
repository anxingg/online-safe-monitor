package cn.com.qytx.hotline.ivr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.qytx.platform.base.domain.BaseDomain;

/**
 * @Description: [公司信息表]
 * @Author: [lhw]
 * @CreateDate: [Dec 7, 2012]
 * @UpdateUser: [lhw]
 * @UpdateDate: [Dec 7, 2012]
 * @UpdateRemark: [说明本次修改内容]
 * @Version: [v1.0]
 */
@Entity
@Table(name = "tb_company_info")
public class MsiCompanyInfo {

	/**
	 * 公司id
	 */
	@Id
	@Column(name="company_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer companyId;

	/**
	 * 公司名称
	 */
	@Column(name = "company_name")
	private String companyName;

	/**
	 * 公司类型
	 */
	@Column(name = "CompanyType")
	private Integer companyType;

	/**
	 * 公司热线
	 */
	@Column(name = "company_code")
	private String companyHotCode;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	public String getCompanyHotCode() {
		return companyHotCode;
	}

	public void setCompanyHotCode(String companyHotCode) {
		this.companyHotCode = companyHotCode;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
}
