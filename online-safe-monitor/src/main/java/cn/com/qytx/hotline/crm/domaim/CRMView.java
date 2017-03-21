package cn.com.qytx.hotline.crm.domaim;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.struts2.ServletActionContext;

import cn.com.qytx.hotline.customercall.domain.CustomerCallLog;
import cn.com.qytx.oa.util.StringUtil;
import cn.com.qytx.platform.base.domain.BaseDomain;
import cn.com.qytx.platform.base.domain.DeleteState;
import cn.com.qytx.platform.org.domain.UserInfo;

@Entity
@Table(name="view_crm")
public class CRMView  implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6402667110996812195L;
	
	/**
	 * 主键vid
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	/**
	 * 姓名
	 */
	@Column(name="name")
	private String name;
	
	/**
	 * 性别（0、女  ；1、男）
	 */
	@Column(name="gender")
	private Integer gender;
	
	/**
	 * 手机号码
	 */
	@Column(name="mobile")
	private String mobile;
	
	/**
	 * 固定电话号码
	 */
	@Column(name="telephone")
	private String telephone;
	
	/**
	 * 居住地址
	 */
	@Column(name="address")
	private String address;
	
	
	@Column(name="isDelete")
    private  Integer isDelete;
 
    /**
     * 备用号码
     */
    @Column(name="backPhone")
   private String backPhone;
   /**
    * 省份证号
    */
    @Column(name="cardId")
   private  String cardId;
   /**
    *户口所在地 
    */
    @Column(name="hkAddress")
   private String hkAddress;
   /**
    * 工作单位
    */
    @Column(name="company")
   private String company;
   /**
    * 职务
    */
    @Column(name="job")
   private String job;
   /**
    * 月收入
    */
    @Column(name="receiveMoney")
   private BigDecimal  receiveMoney;
   /**
    * 备注
    */
    @Column(name="note",length=500)
   private String note;
    /**
     * 客户类别   1 普通   2 vip
     */
    @Column(name="personType")
    private int personType;
    /**
     * 年龄
     */
    @Column(name="age")
    private Integer age;
    
    

   


	/**
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /* getter setter */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}





	/**
	 * @return the isDelete
	 */
	public Integer getIsDelete() {
		return isDelete;
	}

	/**
	 * @param isDelete the isDelete to set
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * @return the backPhone
	 */
	public String getBackPhone() {
		return backPhone;
	}

	/**
	 * @param backPhone the backPhone to set
	 */
	public void setBackPhone(String backPhone) {
		this.backPhone = backPhone;
	}

	/**
	 * @return the cardId
	 */
	public String getCardId() {
		return cardId;
	}

	/**
	 * @param cardId the cardId to set
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	/**
	 * @return the hkAddress
	 */
	public String getHkAddress() {
		return hkAddress;
	}

	/**
	 * @param hkAddress the hkAddress to set
	 */
	public void setHkAddress(String hkAddress) {
		this.hkAddress = hkAddress;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * @param job the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}




	/**
	 * @return the receiveMoney
	 */
	public BigDecimal getReceiveMoney() {
		return receiveMoney;
	}

	/**
	 * @param receiveMoney the receiveMoney to set
	 */
	public void setReceiveMoney(BigDecimal receiveMoney) {
		this.receiveMoney = receiveMoney;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}



	/**
	 * @return the personType
	 */
	public int getPersonType() {
		return personType;
	}

	/**
	 * @param personType the personType to set
	 */
	public void setPersonType(int personType) {
		this.personType = personType;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}


	
}
