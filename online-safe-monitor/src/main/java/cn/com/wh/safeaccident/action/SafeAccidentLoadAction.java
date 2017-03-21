package cn.com.wh.safeaccident.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

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

public class SafeAccidentLoadAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3466587967482048158L;

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
     * 系统日志接口
     */
    @Resource
    private ILog logService;

//	/**
//	 * 数据字典接口
//	 */
//	@Autowired
//	IDict dictService;

	private Integer operation;

	/**
	 * 附件接品
	 */
	@Autowired
	private IAttachment attachmentService;

	String accidentCharacterName;

	Attachment attachment;

	public Map<Integer, String> companyMap;

	public Map<Integer, String> accidentCharacterTypemap;

	/**
	 * 查询一个对象
	 * 
	 * @return
	 */
	public String findAafeAccident() {
		if (safeAccident != null) {
			if (safeAccident.getVid() != null) {
				try {
					safeAccident = safeAccidentService.findOne(safeAccident
							.getVid());
					// 获取数据字典Map<value, name>
					accidentCharacterTypemap = Tool.loadaccidentCharacterTypemap();

					accidentCharacterName = safeAccident.getAccidentCharacter() == null ? ""
							: accidentCharacterTypemap.get(safeAccident
									.getAccidentCharacter());
					String reportId = safeAccident.getReportId();
					if(StringUtils.isNotBlank(reportId)){
						reportId = reportId.replaceAll(",", "");
					}
					if (StringUtils.isNotBlank(reportId)) {
						List<Attachment> attalist = attachmentService
								.getAttachmentsByIds(reportId);
						attachment = attalist == null ? null : attalist.get(0);
						// System.out.println(attachment==null?"查询结果是空。":attachment.getAttachName());
						// System.out.println(attachment.getId());
					}

					loadMap();
				} catch (Exception e) {
					LOGGER.error("通过主键查询安全生产事故时出错", e);
				}
			}
		}
		return "success";
	}

	/**
	 * 删除一个对象
	 * 
	 * @return
	 */
	public String deleteAafeAccident() {
		int result = 0;// 0表示失败
		if (safeAccident != null) {
			if (safeAccident.getVid() != null) {
				try {
					safeAccident = safeAccidentService.findOne(safeAccident
							.getVid());
					safeAccident.setIsDelete(1);
					safeAccident = safeAccidentService.saveOrUpdate(safeAccident);
					result = 1;// 1表示成功
					
					//记录日志
					logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
							this.getRequest().getRemoteAddr(), 
							"删除事故成功", 
							LogType.LOG_SAFEACCIDENT_DELETE, 
							safeAccident, 
							safeAccident.getVid()) );
				} catch (Exception e) {
					LOGGER.error("通过主键删除安全生产事故时出错", e);
				}
			}
		}
		try {
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(result);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error("删除安全生产事故后，往页面返回数据时出错", e);
		}
		return null;
	}

	public String loadCompanyAndAccidentCharacter() {
		loadMap();
		return "success";
	}

	private void loadMap() {
		// 事故性质
		accidentCharacterTypemap = Tool.loadaccidentCharacterTypemap();

		// 公司名
		companyMap = Tool.loadCompanyMap();
	}

	public SafeAccident getSafeAccident() {
		return safeAccident;
	}

	public void setSafeAccident(SafeAccident safeAccident) {
		this.safeAccident = safeAccident;
	}

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}

	public String getAccidentCharacterName() {
		return accidentCharacterName;
	}

	public void setAccidentCharacterName(String accidentCharacterName) {
		this.accidentCharacterName = accidentCharacterName;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

}
