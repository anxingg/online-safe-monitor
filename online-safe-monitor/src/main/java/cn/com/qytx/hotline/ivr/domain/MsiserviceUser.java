package cn.com.qytx.hotline.ivr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.qytx.oa.domain.OaBaseEntity;

/**
 * 项目名称：wzerp 类名称： 类描述：呼入队列员工实体类 创建人：WangBin 创建时间：2012-11-21
 * 
 * @version
 */
@Entity
@Table(name = "MSIServiceUser")
public class MsiserviceUser extends OaBaseEntity {

	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vid;

	/**
	 * 坐席id
	 */
	@Column(name = "MSIId")
	private Integer msiid;

	/**
	 * 队列id
	 */
	@Column(name = "ServiceId")
	private Integer serviceId;

	/**
	 * 公司ID
	 */
	@Column(name = "CompanyId")
	private Integer companyId;

	/**
	 * @return the vid
	 */
	public Integer getVid() {
		return vid;
	}

	/**
	 * @param vid
	 *            the vid to set
	 */
	public void setVid(Integer vid) {
		this.vid = vid;
	}

	/**
	 * @return the msiid
	 */
	public Integer getMsiid() {
		return msiid;
	}

	/**
	 * @param msiid
	 *            the msiid to set
	 */
	public void setMsiid(Integer msiid) {
		this.msiid = msiid;
	}

	/**
	 * @return the serviceId
	 */
	public Integer getServiceId() {
		return serviceId;
	}

	/**
	 * @param serviceId
	 *            the serviceId to set
	 */
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((msiid == null) ? 0 : msiid.hashCode());
		result = prime * result
				+ ((serviceId == null) ? 0 : serviceId.hashCode());
		result = prime * result + ((vid == null) ? 0 : vid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (!super.equals(obj)){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		MsiserviceUser other = (MsiserviceUser) obj;
		if (companyId == null) {
			if (other.companyId != null){
				return false;
			}
		} else if (!companyId.equals(other.companyId)){
			return false;
		}
		if (msiid == null) {
			if (other.msiid != null){
				return false;
			}
		} else if (!msiid.equals(other.msiid)){
			return false;
		}
		if (serviceId == null) {
			if (other.serviceId != null){
				return false;
			}
		} else if (!serviceId.equals(other.serviceId)){
			return false;
		}
		if (vid == null) {
			if (other.vid != null){
				return false;
			}
		} else if (!vid.equals(other.vid)){
			return false;
		}
		return true;
	}

}