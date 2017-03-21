package cn.com.wh.dangerchemicals.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
import cn.com.wh.dangerchemicals.domain.CompanyDangerChemicals;
import cn.com.wh.dangerchemicals.domain.DangerChemicalsInfo;
import cn.com.wh.dangerchemicals.service.CompanyDangerChemicalsService;
import cn.com.wh.dangerchemicals.service.DangerChemicalsService;
import cn.com.wh.safeaccident.util.Tool;

/**
 * 公司危险化学品
 * @author lilipo
 *
 */
public class CompanyDangerChemicalsLoadAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1056354006356763743L;

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
	
	private Attachment technicalAttachment;
	private Attachment securityAttachment;
	
	private List<DangerChemicalsInfo> dangerChemicalsInfoList;
	
	public Map<Integer, String> companyMap;
	
	/**
	 * 查询一个对象
	 * @return
	 */
	public String findCompanyDangerChemicals(){
		if(companyDangerChemicals != null){
			if(companyDangerChemicals.getVid() != null){
				try{
					//查询危化品名称集合
					dangerChemicalsInfoList = dcService.findAll();
					Map<Integer, DangerChemicalsInfo> dcimap = changetomap(dangerChemicalsInfoList);
					
					//查询这个对象
					companyDangerChemicals = cdcService.findOne(companyDangerChemicals.getVid());
					DangerChemicalsInfo dci = null;
					if(companyDangerChemicals != null){
						dci = dcimap.get(companyDangerChemicals.getDangerId());
					}
					if(dci != null){
						companyDangerChemicals.setMaterialName(dci.getMaterialName());
					}
					
					//技术说明书附件
					String technicalId = companyDangerChemicals.getTechnicalId();
					technicalId = technicalId == null?null:technicalId.replaceAll(",", "");
					if(StringUtils.isNotBlank(technicalId)){
						List<Attachment> attalist = attachmentService.getAttachmentsByIds(technicalId);
						technicalAttachment = attalist==null?null:attalist.get(0);
					}
					
					//安全标签附件
					String securityId = companyDangerChemicals.getSecurityId();
					securityId = securityId ==null ? null: securityId.replaceAll(",", "");
					if(StringUtils.isNotBlank(securityId)){
						List<Attachment> attalist = attachmentService.getAttachmentsByIds(securityId);
						securityAttachment = attalist==null?null:attalist.get(0);
					}
				}catch(Exception e){
					LOGGER.error("通过主键查询公司危险化学品时出错", e);
				}
			}
		}
		return "success";
	}
	
	public static Map<Integer, DangerChemicalsInfo> changetomap(List<DangerChemicalsInfo> list){
		Map<Integer, DangerChemicalsInfo> map = new HashMap<Integer, DangerChemicalsInfo>();
		if(list != null && !list.isEmpty()){
			for(DangerChemicalsInfo dci: list){
				map.put(dci.getVid(), dci);
			}
		}
		return map;
	}
	
	
	/**
	 * 删除一个对象
	 * @return
	 */
	public String deleteOne() {
		int result = 0;//0表示失败
		if(companyDangerChemicals != null){
			if(companyDangerChemicals.getVid() != null){
				try{
					companyDangerChemicals = cdcService.findOne(companyDangerChemicals.getVid());
					companyDangerChemicals.setIsDelete(1);
					companyDangerChemicals = cdcService.saveOrUpdate(companyDangerChemicals);
					result = 1;//1表示成功
					
					//记录日志
					logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
							this.getRequest().getRemoteAddr(), 
							"删除公司危险化学品成功", 
							LogType.LOG_CDANGERH_DELETE, 
							companyDangerChemicals, 
							companyDangerChemicals.getVid()) );
				}catch(Exception e){
					LOGGER.error("通过主键删除危险化学品时出错", e);
				}
			}
		}
		try {
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(result);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error("删除危险化学品后，往页面返回数据时出错", e);
		}
		return null;
	}
	
	/**
	 * 跳转到统计列表页面
	 * @return
	 */
	public String toSPage(){
		companyMap = Tool.loadCompanyMap();
		return "success";
	}

	public CompanyDangerChemicals getCompanyDangerChemicals() {
		return companyDangerChemicals;
	}

	public void setCompanyDangerChemicals(
			CompanyDangerChemicals companyDangerChemicals) {
		this.companyDangerChemicals = companyDangerChemicals;
	}

	public Attachment getTechnicalAttachment() {
		return technicalAttachment;
	}

	public void setTechnicalAttachment(Attachment technicalAttachment) {
		this.technicalAttachment = technicalAttachment;
	}

	public Attachment getSecurityAttachment() {
		return securityAttachment;
	}

	public void setSecurityAttachment(Attachment securityAttachment) {
		this.securityAttachment = securityAttachment;
	}

	public List<DangerChemicalsInfo> getDangerChemicalsInfoList() {
		return dangerChemicalsInfoList;
	}


	public void setDangerChemicalsInfoList(
			List<DangerChemicalsInfo> dangerChemicalsInfoList) {
		this.dangerChemicalsInfoList = dangerChemicalsInfoList;
	}


	public Map<Integer, String> getCompanyMap() {
		return companyMap;
	}


	public void setCompanyMap(Map<Integer, String> companyMap) {
		this.companyMap = companyMap;
	}
	
}
