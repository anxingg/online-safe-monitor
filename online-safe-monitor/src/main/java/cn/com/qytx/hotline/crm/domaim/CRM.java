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
@Table(name="tb_crm")
public class CRM extends BaseDomain implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6402667110996812195L;
	
	/**
	 * 主键vid
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer vid;

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
	
	/**
	 * 手机号或固定电话（仅查询条件中用）
	 */
	@Transient
	private String mobileOrTelephone;
	@Column(name="last_update_user_id")
	private int lastUpdateUserId;
	@Column(name="create_user_id")
	private int createUserId;
	@Column(name="last_update_time")
	private Timestamp lastUpdateTime;
	@Column(name="isDelete")
	@DeleteState
    private  Integer isDelete;
    @Column(name="createtime")
    private Timestamp createTime;
    /**
     * 关联id
     */
    @Column(name="linked_id")
    private Integer linkedId;
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
     * 权限控制
     */
    @Column(name="is_fork_group")
    private Integer isForkGroup;
    
   /**
    * 工单列表
    */
   @OneToMany(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY,mappedBy="crm")
	private List<CustomerCallLog> customerlog=new ArrayList<CustomerCallLog>();
   
   
	/**
     * 功能：
     * @return
     */
    @Override
    public String toString()
    {
        UserInfo  user=(UserInfo) ServletActionContext.getRequest().getSession().getAttribute("adminUser");
        return user.getUserName()+"CRM [vid=" + vid + ", name=" + name + ", gender=" + gender + ", mobile=" + mobile + ", telephone="
                + telephone + ", address=" + address + ", mobileOrTelephone=" + mobileOrTelephone
                + ", lastUpdateUserId=" + lastUpdateUserId + ", createUserId=" + createUserId + ", lastUpdateTime="
                + lastUpdateTime + ", isDelete=" + isDelete + ", createTime=" + createTime + ", backPhone=" + backPhone
                + ", cardId=" + cardId + ", hkAddress=" + hkAddress + ", company=" + company + ", job=" + job
                + ", receiveMoney=" + receiveMoney + ", note=" + note + ", personType=" + personType + ", age=" + age
                + ", isForkGroup=" + isForkGroup + ", customerlog=" + customerlog + "]";
    }

	/* getter setter */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

    /**
     * @return the linkedId
     */
    public Integer getLinkedId()
    {
        return linkedId;
    }

    /**
     * @param linkedId the linkedId to set
     */
    public void setLinkedId(Integer linkedId)
    {
        this.linkedId = linkedId;
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

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public String getMobileOrTelephone() {
		return mobileOrTelephone;
	}

	public void setMobileOrTelephone(String mobileOrTelephone) {
		this.mobileOrTelephone = mobileOrTelephone;
	}

	/**
	 * @return the lastUpdateUserId
	 */
	public int getLastUpdateUserId() {
		return lastUpdateUserId;
	}

	/**
	 * @param lastUpdateUserId the lastUpdateUserId to set
	 */
	public void setLastUpdateUserId(int lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}

	/**
	 * @return the createUserId
	 */
	public int getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the lastUpdateTime
	 */
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * @param lastUpdateTime the lastUpdateTime to set
	 */
	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
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
	 * @return the createTime
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
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
	 * @return the customerlog
	 */
	public List<CustomerCallLog> getCustomerlog() {
		return customerlog;
	}

	/**
	 * @param customerlog the customerlog to set
	 */
	public void setCustomerlog(List<CustomerCallLog> customerlog) {
		this.customerlog = customerlog;
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

	public Integer getIsForkGroup() {
		return isForkGroup;
	}

	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}
	/**
	 * 功能：       更新数据
	 * @param crm
	 * @return
	 */
	public CRM updateByCustomer(CRM crm) {
		// TODO Auto-generated method stub
		if(!StringUtil.isEmpty(crm.getName())){
			this.setName(crm.getName());
		}
        if(crm.getGender()!=null&&crm.getGender()>0){
        	this.setGender(crm.getGender());
        } 
        if(!StringUtil.isEmpty(crm.getMobile())){
        	this.setMobile(crm.getMobile());
        }
        if(crm.getAge()!=null&&crm.getAge()>0){
        	this.setAge(crm.getAge());
        }
        if(!StringUtil.isEmpty(crm.getBackPhone())){
        	this.setBackPhone(crm.getBackPhone());
        }
        if(!StringUtil.isEmpty(crm.getCardId())){
        	this.setCardId(crm.getCardId());
        }
        if(!StringUtil.isEmpty(crm.getHkAddress())){
        	this.setHkAddress(crm.getHkAddress());
        	
        }
        if(!StringUtil.isEmpty(crm.getAddress())){
        	this.setAddress(crm.getAddress());
        }
        if(!StringUtil.isEmpty(crm.getCompany())){
          this.setCompany(crm.getCompany())        	;
        }
        if(!StringUtil.isEmpty(crm.getJob())){
        	this.setJob(crm.getJob());
        }
        if(crm.getReceiveMoney()!=null){
        	this.setReceiveMoney(crm.getReceiveMoney());
        }
        if(!StringUtil.isEmpty(crm.getNote())){
        	this.setNote(crm.getNote());
        }
        if(crm.getPersonType()>0){
        	this.setPersonType(crm.getPersonType());
        }
		return this;
	}
	
	
	
}
