package cn.com.qytx.platform.action;

import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.hotline.ivr.domain.CallCenterConst;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.domain.SystemBasisSet;
import cn.com.qytx.platform.org.domain.CompanyInfo;
import cn.com.qytx.platform.org.service.ICompany;
import cn.com.qytx.platform.service.IPlatformParameterService;

public class SystemBasisSetAction extends BaseActionSupport{
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 6477911543402486311L;
	@Autowired
	private transient IPlatformParameterService parmsService;
	@Autowired
	private    ICompany  companyService;
	
	private SystemBasisSet sysSet;
	
	public SystemBasisSet getSysSet() {
		return sysSet;
	}

	public void setSysSet(SystemBasisSet sysSet) {
		this.sysSet = sysSet;
	}
	
	
 /**
 * @return
 * 系统基础设置平台参数的保存
 * 
 */
public String systemBasisSetSave(){
	PrintWriter out = null;
	try {
		sysSet.setParDescribe("系统基础设置参数");
		sysSet.setParItems("cn.com.qytx.platform.domain.SystemBasisSet");
		parmsService.saveObj(sysSet);
		//更新application中的设置
		ServletActionContext.getServletContext().setAttribute("systemBasisSet", sysSet);
		//根据组织机构根目录修改companyName;
	//	List<CompanyInfo> ciList= companyService.listAll();
		//2014-04-28 严正修改  
		/*
		 * 修改内容：将取CompanyInfo的方法改变根据ID来取
		 * 
		 * 
		 */
		//取出鹤壁人民医院对应的Id
	   int hbCpId=	CallCenterConst.COMPANY_MARK_SYSTEM;
	   CompanyInfo  ci=	companyService.findOne(hbCpId);
	   if(ci!=null){
		   ci.setCompanyName(sysSet.getOrganizationsRootName());
		   companyService.saveOrUpdate(ci);
	   }
//		if(ciList!=null&&ciList.size()>0){
//			
//			CompanyInfo  ci=	ciList.get(0);
//			ci.setCompanyName(sysSet.getOrganizationsRootName());
//			companyService.saveOrUpdate(ci);
//		}
		out = this.getResponse().getWriter();
		out.print(1);
	} catch (Exception e) {
		LOGGER.error(e.getMessage());
		
	}finally{
		if(out!=null){
			out.close();
		}
		
		
	}
	 return null;
 }
	
	
	
 /**
 * @return
 * 系统基础设置平台参数的展示
 */
public String systemBasisSetView(){
	//SystemBasisSet sysBasisSet=	(SystemBasisSet) parmsService.findByParItems("cn.com.qytx.platform.domain.SystemBasisSet");
	SystemBasisSet sysBasisSet= (SystemBasisSet) ServletActionContext.getServletContext().getAttribute("systemBasisSet");
	this.getRequest().setAttribute("sysSet", sysBasisSet);
	 return SUCCESS;
	 
 }
	
	
	
}
