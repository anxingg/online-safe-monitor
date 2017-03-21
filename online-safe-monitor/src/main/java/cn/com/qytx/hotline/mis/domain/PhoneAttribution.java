package cn.com.qytx.hotline.mis.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 号码段实体类
 */
@Entity
@Table(name="Dm_Mobile")
public class PhoneAttribution implements Serializable
{
    
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -8812304258622690769L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    /**
     * 手机号码段 前7位
     */
	@Column(name="mobileNumber")
    private String mobileNumber;
 
    /**
     * 归属区域
     */
	@Column(name="MobileArea")
    private String mobileArea;
    
    /**
     * 手机卡类型
     */
	@Column(name="mobileType")
    private String mobileType;
    
    /**
     * 区号
     */
	@Column(name="areaCode")
    private String areaCode; 
 
    /**
     * 邮编
     */
	@Column(name="postCode")
    private String postCode;
	
	/**
	 * 查询关键字
	 */
	@Transient
	private String searchKey;
	
	
    @Override
	public String toString() {
		return "PhoneAttribution [id=" + id + ", mobileNumber=" + mobileNumber
				+ ", mobileArea=" + mobileArea + ", mobileType=" + mobileType
				+ ", areaCode=" + areaCode + ", postCode=" + postCode + "]";
	}

	public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getMobileNumber()
    {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber)
    {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileArea()
    {
        return mobileArea;
    }

    public void setMobileArea(String mobileArea)
    {
        this.mobileArea = mobileArea;
    }

    public String getMobileType()
    {
        return mobileType;
    }

    public void setMobileType(String mobileType)
    {
        this.mobileType = mobileType;
    }

    public String getAreaCode()
    {
        return areaCode;
    }

    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }

    public String getPostCode()
    {
        return postCode;
    }

    public void setPostCode(String postCode)
    {
        this.postCode = postCode;
    }

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
}
