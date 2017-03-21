package cn.com.qytx.hotline.area.domain.province;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 功能:省级单位
 * 版本: 1.0
 * 开发人员: 徐长江
 * 创建日期: 2014-1-2
 * 修改日期: 2014-1-2
 * 修改列表:
 */
@Entity
@Table(name="tb_province")
public class Province{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	//编码
	@Column(name="code")
	private String code;
	
	//省
	@Column(name="province")
	private String province;
	
	//行政区域级别
	@Column(name="grade")
	private String grade;
	//排序号
	@Column(name="orderNum")
	private String orderNum;
	//是否是真名
	@Column(name="isRealName")
	private String isRealName;
  

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getIsRealName() {
		return isRealName;
	}

	public void setIsRealName(String isRealName) {
		this.isRealName = isRealName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
