package cn.com.qytx.platform.session;


import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.platform.org.domain.CompanyInfo;
import cn.com.qytx.platform.org.domain.GroupInfo;
import cn.com.qytx.platform.org.domain.UserInfo;

public class BaseSessionSupport {
	@Autowired
	protected HttpSession session;
	
	public UserInfo getCurrentLoginUser()
	{
		return (UserInfo)this.session.getAttribute(Constants.CURRENT_LOGIN_USER);
	}
	public void setCurrentLoginUser(UserInfo userInfo)
	{
		this.session.setAttribute(Constants.CURRENT_LOGIN_USER, userInfo);
	}
	public CompanyInfo getCurrentLoginCompany()
	{
		return (CompanyInfo)this.session.getAttribute(Constants.CURRENT_LOGIN_COMPANY);
	}
	public void setCurrentLoginCompany(GroupInfo groupInfo)
	{
		this.session.setAttribute(Constants.CURRENT_USER_GROUP,groupInfo);//把部门信息存放到session
	}
	public void setCurrentLoginCompany(CompanyInfo companyInfo)
	{
		this.session.setAttribute(Constants.CURRENT_LOGIN_COMPANY,companyInfo);//把单位信息存放到session
	}
	public GroupInfo getCurrentLoginGroup()
	{
		return (GroupInfo)this.session.getAttribute(Constants.CURRENT_USER_GROUP);
	}
	public void setDownPath(String downPath)
	{
		this.session.setAttribute(Constants.DOWN_PATH,downPath);
	}
	public String getDownPath()
	{
		return (String)this.session.getAttribute(Constants.DOWN_PATH);
	}
	public void setSSOToken(String ssoToken)
	{
		this.session.setAttribute(Constants.SSO_TOKEN,ssoToken);
	}
	public String getSSOToken()
	{
		return (String)this.session.getAttribute(Constants.SSO_TOKEN);
	}
	public HttpSession getSession()
	{
		return this.session;
	}
}
