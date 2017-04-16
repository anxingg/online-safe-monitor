package cn.com.wh.dangersourceobject.domain;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.dangersource.domain.DangerSource;

/**
 * 阈值模板实体类
 * @author 吴胜光
 * 创建时间： 2017年04月03日
 * 
 */
@Entity
@Table(name="DANGERSOURCEOBJECT")
public class DangerSourceObject implements java.io.Serializable {

	private static final long serialVersionUID = 4705025193958385486L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;//id
	
	@Column(name="OBJECTNAME")
	private String objectName;//对象名称
	
//	@Column(name="DANGERSOURCEID")
//	private String dangerSourceID;//
	
    /**
     * 危险源对象
     */
    @JoinColumn(name="DANGERSOURCEID")
    @ManyToOne(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	private DangerSource dangerSource;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public DangerSource getDangerSource() {
		return dangerSource;
	}

	public void setDangerSource(DangerSource dangerSource) {
		this.dangerSource = dangerSource;
	}

	public DangerSourceObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}