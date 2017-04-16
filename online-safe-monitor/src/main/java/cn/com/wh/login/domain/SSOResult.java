package cn.com.wh.login.domain;


public class SSOResult implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer ret;
	private String msg;
	private SSOUserInfo userInfo;
	public Integer getRet() {
		return ret;
	}
	public void setRet(Integer ret) {
		this.ret = ret;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public SSOUserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(SSOUserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public SSOResult() {
		super();
	}
}
