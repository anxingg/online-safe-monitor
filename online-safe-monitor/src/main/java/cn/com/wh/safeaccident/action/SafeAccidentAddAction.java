package cn.com.wh.safeaccident.action;

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
import cn.com.wh.safeaccident.domain.SafeAccident;
import cn.com.wh.safeaccident.service.SafeAccidentService;
import cn.com.wh.safeaccident.util.Tool;

public class SafeAccidentAddAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4442711644770802812L;

	/**
	 * 事故生产安全对象
	 */
	private SafeAccident safeAccident;

	/**
	 * 安全生产事故的接口
	 */
	@Autowired
	SafeAccidentService safeAccidentService;
	
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
			if(safeAccident != null){
				Attachment atta = getAttachmentById(safeAccident.getReportId());
				if(safeAccident.getVid() == -1){
					safeAccident.setVid(null);
					//新增
					safeAccident.setIsDelete(0);
					safeAccident.setCreateTime(new Date());
					safeAccident.setCreateUser(getLoginUser().getUserId());
					safeAccident.setIsForkGroup(getLoginUser().getIsForkGroup());
					safeAccident.setReportPath(atta==null?null:atta.getAttachFile());
					safeAccident.setReportName(atta==null?null:atta.getAttachName());
					safeAccident.setOccurredTime(StringUtils.isBlank(safeAccident.getOccurredTimeStr())? null :Tool.getDateByString(safeAccident.getOccurredTimeStr(), null, "yyyy-MM-dd HH:mm:ss.mis"));
					try{
						safeAccident = safeAccidentService.saveOrUpdate(safeAccident);
					}catch(Exception e){
						result = 0;
						LOGGER.error("新增安全生产事故时出错", e);
					}
					if(safeAccident.getVid() != null){
						result = 1;
					}
					
					//记录日志
					logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
							this.getRequest().getRemoteAddr(), 
							"新增事故成功", 
							LogType.LOG_SAFEACCIDENT_ADD, 
							safeAccident, 
							safeAccident.getVid()) );
				}else {
					//修改
					SafeAccident oldSafeAccident = safeAccidentService.findOne(safeAccident.getVid());
					safeAccident.setLastUpdateUser(getLoginUser().getUserId());
					safeAccident.setLastUpdateTime(new Date());
					
					safeAccident.setIsDelete(oldSafeAccident.getIsDelete());
					safeAccident.setCreateTime(oldSafeAccident.getCreateTime());
					safeAccident.setCreateUser(oldSafeAccident.getCreateUser());
					safeAccident.setIsForkGroup(oldSafeAccident.getIsForkGroup());
					
					safeAccident.setReportPath(atta==null?null:atta.getAttachFile());
					safeAccident.setReportName(atta==null?null:atta.getAttachName());
					safeAccident.setOccurredTime(StringUtils.isBlank(safeAccident.getOccurredTimeStr())? null :Tool.getDateByString(safeAccident.getOccurredTimeStr(), null, "yyyy-MM-dd HH:mm:ss.mis"));
					
					safeAccidentService.saveOrUpdate(safeAccident);
					result = 1;
					
					//记录日志
					logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
							this.getRequest().getRemoteAddr(), 
							"修改事故成功", 
							LogType.LOG_SAFEACCIDENT_UPDATE, 
							safeAccident, 
							safeAccident.getVid()) );
				}
			}
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(result);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error("新增或修改操作时出错", e);
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
			return StringUtils.isBlank(temp)?null:attachmentService.findOne(Integer.parseInt(temp));
		}
	}

	public SafeAccident getSafeAccident() {
		return safeAccident;
	}

	public void setSafeAccident(SafeAccident safeAccident) {
		this.safeAccident = safeAccident;
	}
	
}
