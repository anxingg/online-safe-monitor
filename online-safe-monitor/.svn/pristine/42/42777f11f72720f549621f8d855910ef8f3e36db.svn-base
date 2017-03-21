package cn.com.wh.dangerchemicals.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.qytx.platform.base.domain.DeleteState;

/**
 * 危险化学品目录类
 * @author lilipo
 *
 */
@Entity
@Table(name="tb_wuhai_danger_chemicals")
public class ChemicalsDirectory implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3608105953432828278L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer vid;//
	
	/**
	 * 编号
	 */
	@Column(name="code")
	private String code;// 
	
	/**
	 * CAS号
	 */
	@Column(name="cas")
	private String cas;//
	
	/**
	 * 品名
	 */
	@Column(name="MATERIAL_NAME")
	private String materialName;//
	
	/**
	 * 别名
	 */
	@Column(name="MOLECULAR_FORMULA")
	private String molecularFormula;//
	
	/**
	 * 备注
	 */
	@Column(name="OTHER")
	private String other;//
	
	/**
	 * 是否删除
	 */
	@DeleteState
	@Column(name="IS_DELETE")
	private Integer isDelete;//
	
	/**
	 * 创建人ID
	 */
	@Column(name="CREATE_USER")
	private Integer createUser;//
	
	/**
	 * 创建时间
	 */
	@Column(name="CREATE_DATE")
	private Date createDate;//

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCas() {
		return cas;
	}

	public void setCas(String cas) {
		this.cas = cas;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMolecularFormula() {
		return molecularFormula;
	}

	public void setMolecularFormula(String molecularFormula) {
		this.molecularFormula = molecularFormula;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
