package cn.com.wh.support;


import cn.com.qytx.platform.session.BaseSessionSupport;
import cn.com.wh.WHConstant;

public class SessionSupport extends BaseSessionSupport{
	public void setCurrentWHRoleType(Integer whroletype)
	{
		this.session.setAttribute(WHConstant.WHROLETYPE.WHROLETYPE,whroletype);
	}
	public Integer getCurrentWHRoleType()
	{
		return (Integer)this.session.getAttribute(WHConstant.WHROLETYPE.WHROLETYPE);
	}
	public void setCurrentCompanyName(String companyName)
	{
		this.session.setAttribute(WHConstant.SESSIONCONSTANTS.COMPANY_NAME,companyName);
	}
	public String getCurrentCompanyName()
	{
		return (String)this.session.getAttribute(WHConstant.SESSIONCONSTANTS.COMPANY_NAME);
	}
	
}
