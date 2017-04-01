package cn.com.wh.company.action;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.file.domain.Attachment;
import cn.com.qytx.cbb.file.service.IAttachment;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.wh.company.domain.SafetyInstitutions;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.company.service.ISafetyInstitutions;
import cn.com.wh.company.service.IWHCompany;
import cn.com.wh.safeaccident.util.Tool;

import com.google.gson.Gson;

/**
 * @author lilipo
 *
 */
public class SafetyInstitutionsAction extends BaseActionSupport{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3629990949946782660L;

	/**
	 * 安全管理机构接口
	 */
	@Autowired
	private ISafetyInstitutions sisService;
	
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;
	
	/**
	 * 附件接口
	 */
	@Autowired
	private IAttachment attachmentService;
	
	/**
	 * 公司接口
	 */
	@Autowired
	public IWHCompany companyImpl;
	
	
	private WHCompany whCompany;
	
	private SafetyInstitutions sis;
	
	private List<Attachment> cflist;
	private List<Attachment> dflist;
	private List<Attachment> gflist;
	
	private Integer operation;
	
	/**
	 * 更新
	 * @return
	 */
	public String update(){
		UserInfo userInfo = this.getSessionSupport().getCurrentLoginUser();
		if(userInfo != null){
			String createFileIds = sis.getCreateFileIds();
			String departmentFileIds = sis.getDepartmentFileIds();
			String groupFileIds = sis.getGroupFileIds();
			
			SafetyInstitutions oldsis = sisService.findByGroupId(whCompany.getGroupId());
			
			oldsis.setCreateFileIds(createFileIds);
			oldsis.setDepartmentFileIds(departmentFileIds);
			oldsis.setGroupFileIds(groupFileIds);
			sis = sisService.saveOrUpdate(oldsis);
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"修改安全管理机构成功", 
					LogType.LOG_AQGLJG_UPDATE, 
					sis, 
					sis.getVid()) );
			Map<String, Object> map = new HashMap<String, Object>();
			if(sis != null){
				List<Attachment> attlist = new LinkedList<Attachment>();
				if(StringUtils.isNotBlank(Tool.trimComma(sis.getCreateFileIds()))){
					attlist.addAll(attachmentService.getAttachmentsByIds(Tool.trimComma(sis.getCreateFileIds())));
				}
				if(StringUtils.isNotBlank(Tool.trimComma(sis.getDepartmentFileIds()))){
					attlist.addAll(attachmentService.getAttachmentsByIds(Tool.trimComma(sis.getDepartmentFileIds())));
				}
				if(StringUtils.isNotBlank(Tool.trimComma(sis.getGroupFileIds()))){
					attlist.addAll(attachmentService.getAttachmentsByIds(Tool.trimComma(sis.getGroupFileIds())));
				}
				
				Map<Integer, Attachment> attmap = getAttachmentMap(attlist);
				List<Map<String, Object>> cflist = new LinkedList<Map<String, Object>>();
				if(StringUtils.isNotBlank(sis.getCreateFileIds())){
					for(String idstr: Tool.trimComma(sis.getCreateFileIds()).split(",")){
						if(StringUtils.isNotBlank(idstr)){
							Map<String, Object> innermap = new HashMap<String, Object>();
							innermap.put("id", idstr);
							innermap.put("name", attmap.get(Integer.parseInt(idstr)).getAttachName());
							cflist.add(innermap);
						}
					}
				}
				List<Map<String, Object>> dflist = new LinkedList<Map<String, Object>>();
				if(StringUtils.isNotBlank(sis.getDepartmentFileIds())){
					for(String idstr: Tool.trimComma(sis.getDepartmentFileIds()).split(",")){
						if(StringUtils.isNotBlank(idstr)){
							Map<String, Object> innermap = new HashMap<String, Object>();
							innermap.put("id", idstr);
							innermap.put("name", attmap.get(Integer.parseInt(idstr)).getAttachName());
							dflist.add(innermap);
						}
					}
				}
				List<Map<String, Object>> gflist = new LinkedList<Map<String, Object>>();
				if(StringUtils.isNotBlank(sis.getGroupFileIds())){
					for(String idstr: Tool.trimComma(sis.getGroupFileIds()).split(",")){
						if(StringUtils.isNotBlank(idstr)){
							Map<String, Object> innermap = new HashMap<String, Object>();
							innermap.put("id", idstr);
							innermap.put("name", attmap.get(Integer.parseInt(idstr)).getAttachName());
							gflist.add(innermap);
						}
					}
				}
				
				map.put("cf", cflist);
				map.put("df", dflist);
				map.put("gf", gflist);
			}
			
			Gson gson = new Gson();
			ajax(gson.toJson(map));
		}
		return null;
	}
	
	private Map<Integer, Attachment> getAttachmentMap(List<Attachment> attlist){
		Map<Integer, Attachment> map = new HashMap<Integer, Attachment>();
		for(Attachment att: attlist){
			map.put(att.getId(), att);
		}
		return map;
	}

	/**
	 * 跳转页面
	 * @return
	 */
	public String jmpPage(){
		UserInfo userInfo = this.getSessionSupport().getCurrentLoginUser();
		if(userInfo != null){
			whCompany = companyImpl.findByGroupId(whCompany.getGroupId());
			sis = sisService.findByGroupId(whCompany.getGroupId());
			
			String createFileIds = sis == null ? null : Tool.trimComma( sis.getCreateFileIds() );
			String departmentFileIds = sis == null ? null : Tool.trimComma( sis.getDepartmentFileIds() );
			String groupFileIds =  sis == null ? null : Tool.trimComma( sis.getGroupFileIds() );
			
			if(StringUtils.isNotBlank(createFileIds)){
				cflist = attachmentService.getAttachmentsByIds(createFileIds);
			}
			if(StringUtils.isNotBlank(departmentFileIds)){
				dflist = attachmentService.getAttachmentsByIds(departmentFileIds);
			}
			if(StringUtils.isNotBlank(groupFileIds)){
				gflist = attachmentService.getAttachmentsByIds(groupFileIds);
			}
		}
		
		return "success";
	}
	
	public WHCompany getWhCompany() {
		return whCompany;
	}

	public void setWhCompany(WHCompany whCompany) {
		this.whCompany = whCompany;
	}

	public SafetyInstitutions getSis() {
		return sis;
	}

	public void setSis(SafetyInstitutions sis) {
		this.sis = sis;
	}

	public List<Attachment> getCflist() {
		return cflist;
	}

	public void setCflist(List<Attachment> cflist) {
		this.cflist = cflist;
	}

	public List<Attachment> getDflist() {
		return dflist;
	}

	public void setDflist(List<Attachment> dflist) {
		this.dflist = dflist;
	}

	public List<Attachment> getGflist() {
		return gflist;
	}

	public void setGflist(List<Attachment> gflist) {
		this.gflist = gflist;
	}

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}
	
	
}
