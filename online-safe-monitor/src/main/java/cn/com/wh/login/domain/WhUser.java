package cn.com.wh.login.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * <br/>功能:客户用户表
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2016年5月1日
 * <br/>修改日期: 2016年5月1日
 * <br/>修改列表:
 */
@Entity
@Table(name="SYSUSER")
public class WhUser  implements java.io.Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -2020291820511867809L;

	
	//用户编号
	@Id
	@Column(name="CODE")
	private String code;
	
	//用户类型
	@Column(name="USERTYPE")
	private Integer userType;
	
	//登录密码
	@Column(name="LOGINPWD")
	private String loginPwd;
	
	//用户名称
	@Column(name="NickName")
	private String nickName;
	
	//用户主角色
	@Column(name="UserMainRole")
	private String userMainRole;
	
	//机构代码
	@Column(name="OrgCode")
	private String orgCode;
  	
	//手机号
	@Column(name="Mobile")
	private String mobile;
	
	//微信OpenID
	@Column(name="OpenID")
	private String openID;
	
	//预留信息
	@Column(name="Reserve")
	private String reserve;
	
	//是否禁用
	@Column(name="Disabled")
	private Integer disabled;
	
	//id
	@Column(name="id")
	private Integer id;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserMainRole() {
		return userMainRole;
	}

	public void setUserMainRole(String userMainRole) {
		this.userMainRole = userMainRole;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

}