package cn.com.wh.process.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.file.service.IAttachment;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.wh.process.domain.TechnologicalProcess;
import cn.com.wh.process.service.ITechnologicalProcess;
import cn.com.wh.reliefgoods.domain.SearchVo;
import cn.com.wh.safeaccident.util.Tool;

import com.google.gson.Gson;
/**
 * 
 * <br/>功能:工艺流程action
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2015年9月30日
 * <br/>修改日期: 2015年9月30日
 * <br/>修改列表:
 */
public class TechnologicalProcessAction extends BaseActionSupport{

	private static final long serialVersionUID = -9036816638265397456L;
	
	@Autowired
	private ITechnologicalProcess technologicalProcessService;
	
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;
	
	@Resource(name="attachmentService")
	private IAttachment attachmentService;
	
	private SearchVo  vo;//查询vo
	
	private TechnologicalProcess info;
	
	private Integer infoId;
	
	private String optStr;//返回页面  “detail”  “update”
	
	/**
	 * 
	 * 功能：工艺流程分页
	 * @return
	 */
	public String findProcessList(){
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart() / (double) this.getIDisplayLength())) + 1;
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "groupId"),new Sort.Order(Direction.ASC, "createTime"));
			Page<TechnologicalProcess> pageInfo = technologicalProcessService.findByPage(this.getPageable(sort), vo);
			List<TechnologicalProcess> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {
				for (TechnologicalProcess info : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					//主键
					map.put("id", info.getId());
					//序号
					map.put("no", i);
					//工艺名称
					map.put("title", info.getTitle()==null?"":info.getTitle());
					//创建时间
					map.put("createTime", info.getCreateTime()==null?"":new SimpleDateFormat("yyyy-MM-dd").format(info.getCreateTime()));
					//公司名称
					map.put("companyName", info.getGroupName()==null?"":info.getGroupName());
					//部门id
					map.put("groupId", info.getGroupId());
					mapList.add(map);
					i++;
				}
			}
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("iTotalRecords", pageInfo.getTotalElements());
			jsonMap.put("iTotalDisplayRecords", pageInfo.getTotalElements());
			jsonMap.put("aaData", mapList);
			Gson json = new Gson();
			String jsons = json.toJson(jsonMap);
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(jsons);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
    /**
     * 
     * 功能：添加工艺流程
     * @return
     */
	public String saveOrUpdateProcess() {
		try {
			if (info.getId()!=null) {
				TechnologicalProcess oldInfo = technologicalProcessService.findOne(info.getId());
				oldInfo.setTitle(info.getTitle());
				oldInfo.setIntroduction(info.getIntroduction());
				oldInfo.setIntroductionHtml(info.getIntroductionHtml());
				oldInfo.setFileIds(info.getFileIds());
				technologicalProcessService.saveOrUpdate(oldInfo);
				ajax("1");
				//记录日志
				logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
						this.getRequest().getRemoteAddr(), 
						"修改工艺流程成功", 
						LogType.LOG_GYLC_UPDATE, 
						oldInfo, 
						oldInfo.getId()) );
			}else {
				info.setCreateTime(new Date());
				info.setIsDelete(0);
				info.setIsForkGroup(getLoginUser().getIsForkGroup());
				info.setGroupId(getLoginUser().getIsForkGroup());
				info.setGroupName(getSession().getAttribute("companyName").toString());
				info.setCreateUserId(getLoginUser().getUserId());
				technologicalProcessService.saveOrUpdate(info);
				info.setAttachmentList(null);
				ajax("1");
				Gson gosn = new Gson();
				gosn.toJson(info);
				
				//记录日志
				logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
						this.getRequest().getRemoteAddr(), 
						"新增工艺流程成功", 
						LogType.LOG_GYLC_ADD, 
						info, 
						info.getId()) );
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			ajax("0");
		}
		return null;
	}
	
	
	/**
	 * 功能：删除工艺流程
	 * 
	 * @return
	 */
	public String deleteProcess() {
		try {
			technologicalProcessService.delete(infoId, false);
			ajax("1");
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"删除工艺流程成功", 
					LogType.LOG_GYLC_DELETE, 
					null, 
					infoId) );
		} catch (Exception e) {
			e.printStackTrace();
			ajax("0");
		}
		return null;
	}
	
	/**
	 * 获得工艺流程详情
	 * @return
	 */
	public String loadProcess(){
        info = technologicalProcessService.findOne(infoId);
        if (StringUtils.isNotBlank(info.getFileIds())) {
        	info.setAttachmentList(attachmentService.getAttachmentsByIds(info.getFileIds()));
		}
        ajax(new Gson().toJson(info));
		return null;
	}
	
	
	public SearchVo getVo() {
		return vo;
	}
	public void setVo(SearchVo vo) {
		this.vo = vo;
	}

	public TechnologicalProcess getInfo() {
		return info;
	}

	public void setInfo(TechnologicalProcess info) {
		this.info = info;
	}

	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	public String getOptStr() {
		return optStr;
	}

	public void setOptStr(String optStr) {
		this.optStr = optStr;
	}
	
}
