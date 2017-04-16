package cn.com.wh.login.domain;


public class SSOUserInfo implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4781480444553253298L;
	private String loginName;
	private String userName;
	private String role;
	private String organize;
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getOrganize() {
		return organize;
	}
	public void setOrganize(String organize) {
		this.organize = organize;
	}
	public SSOUserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
