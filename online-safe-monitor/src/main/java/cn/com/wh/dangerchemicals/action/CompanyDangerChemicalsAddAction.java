package cn.com.wh.dangerchemicals.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.file.domain.Attachment;
import cn.com.qytx.cbb.file.service.IAttachment;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.wh.dangerchemicals.domain.CompanyDangerChemicals;
import cn.com.wh.dangerchemicals.domain.DangerChemicalsInfo;
import cn.com.wh.dangerchemicals.service.CompanyDangerChemicalsService;
import cn.com.wh.dangerchemicals.service.DangerChemicalsService;
import cn.com.wh.safeaccident.util.Tool;

public class CompanyDangerChemicalsAddAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4951419744941315409L;

	/**
	 * 公司危化品对象
	 */
	private CompanyDangerChemicals companyDangerChemicals;
	
	/**
	 * 公司危化品接品
	 */
	@Autowired
	private CompanyDangerChemicalsService cdcService;
	
	/**
	 * 公司危化品接品
	 */
	@Autowired
	private DangerChemicalsService dcService;
	
	/**
	 * 附件接品
	 */
	@Autowired
	private IAttachment attachmentService;
	
    /**
     * 系统日志接口
     */
    @Resource
    private ILog logService;
    

	/**
	 * 新增或修改
	 * @return
	 */
	public String saveOrUpdate() {
		try {
			int result = 0;//
			if(companyDangerChemicals != null){
				DangerChemicalsInfo dc = null;
				if(companyDangerChemicals.getDangerId() != null){
					dc = dcService.findOne(companyDangerChemicals.getDangerId());
				}
				Attachment technicalAtta = getAttachmentById(companyDangerChemicals.getTechnicalId());
				Attachment securityAtta = getAttachmentById(companyDangerChemicals.getSecurityId());
				if(companyDangerChemicals.getVid() == -1){
					companyDangerChemicals.setVid(null);
					//新增
					companyDangerChemicals.setIsDelete(0);
					companyDangerChemicals.setCreateTime(new Date());
					//companyDangerChemicals.setCreateUser(getLoginUser().getUserId());
					companyDangerChemicals.setIsForkGroup(getLoginUser().getIsForkGroup());
					companyDangerChemicals.setTechnicalPath(technicalAtta==null?null:technicalAtta.getAttachFile());
					companyDangerChemicals.setTechnicalName(technicalAtta==null?null:technicalAtta.getAttachName());
					companyDangerChemicals.setSecurityPath(securityAtta==null?null:securityAtta.getAttachFile());
					companyDangerChemicals.setSecurityName(securityAtta==null?null:securityAtta.getAttachName());
					
					companyDangerChemicals.setMaterialName(dc==null?null:dc.getMaterialName());
					try{
						companyDangerChemicals = cdcService.saveOrUpdate(companyDangerChemicals);
					}catch(Exception e){
						result = 0;
						LOGGER.error("新增公司危险化学品时出错", e);
						try {
							throw e;
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					if(companyDangerChemicals.getVid() != null){
						result = 1;
					}
					
					//记录日志
					logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
							this.getRequest().getRemoteAddr(), 
							"新增公司危险化学品成功", 
							LogType.LOG_CDANGERH_ADD, 
							companyDangerChemicals, 
							companyDangerChemicals.getVid()) );
				}else {
					//修改
					CompanyDangerChemicals oldcompanyDangerChemicals = cdcService.findOne(companyDangerChemicals.getVid());
					
					companyDangerChemicals.setIsDelete(oldcompanyDangerChemicals.getIsDelete());
					companyDangerChemicals.setCreateTime(oldcompanyDangerChemicals.getCreateTime());
					companyDangerChemicals.setIsForkGroup(oldcompanyDangerChemicals.getIsForkGroup());
					
					companyDangerChemicals.setTechnicalPath(technicalAtta==null?null:technicalAtta.getAttachFile());
					companyDangerChemicals.setTechnicalName(technicalAtta==null?null:technicalAtta.getAttachName());
					companyDangerChemicals.setSecurityPath(securityAtta==null?null:securityAtta.getAttachFile());
					companyDangerChemicals.setSecurityName(securityAtta==null?null:securityAtta.getAttachName());
					companyDangerChemicals.setMaterialName(dc==null?null:dc.getMaterialName());
					
					companyDangerChemicals = cdcService.saveOrUpdate(companyDangerChemicals);
					result = 1;
					
					//记录日志
					logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
							this.getRequest().getRemoteAddr(), 
							"修改公司危险化学品成功", 
							LogType.LOG_CDANGERH_UPDATE, 
							companyDangerChemicals, 
							companyDangerChemicals.getVid()) );
				}
			}
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(result);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	private Attachment getAttachmentById(final String reportId){
		String temp = null;
		if(StringUtils.isBlank(reportId)){
			return null;
		}else {
			if(StringUtils.isNotBlank(reportId)){
				temp = reportId.replaceAll(",", "");
			}
			return StringUtils.isBlank(temp) ? null :attachmentService.findOne(Integer.parseInt(temp));
		}
	}

	public CompanyDangerChemicals getCompanyDangerChemicals() {
		return companyDangerChemicals;
	}

	public void setCompanyDangerChemicals(
			CompanyDangerChemicals companyDangerChemicals) {
		this.companyDangerChemicals = companyDangerChemicals;
	}
	
}
