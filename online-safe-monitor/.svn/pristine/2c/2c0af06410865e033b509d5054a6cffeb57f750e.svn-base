package cn.com.qytx.hotline.datafilterrule.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lilipo
 *
 */
@Entity
@Table(name="tb_data_filter_rule")
public class DataFilterRule {

	/**
	 * 主键id
	 */
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer vid;

	/**
	 * 公司ID
	 */
	@Column(name="company_id")
	private Integer companyId;
	
	/**
	 * 条件
	 */
	@Column(name="condition_jpql")
	private String conditionJpql;
	
	/**
	 * 类名（全名）
	 */
	@Column(name="model_class_name")
	private String modelClassName;
	
	/**
	 * 描述
	 */
	@Column(name="name")
	private String name;
	
	/**
	 * 类型
	 */
	@Column(name="operation_type")
	private String operationType;
	
	/**
	 * 
	 */
	@Column(name="relation_id")
	private String relationId;
	
	
	/* getter setter */
	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getConditionJpql() {
		return conditionJpql;
	}

	public void setConditionJpql(String conditionJpql) {
		this.conditionJpql = conditionJpql;
	}

	public String getModelClassName() {
		return modelClassName;
	}

	public void setModelClassName(String modelClassName) {
		this.modelClassName = modelClassName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	
}
