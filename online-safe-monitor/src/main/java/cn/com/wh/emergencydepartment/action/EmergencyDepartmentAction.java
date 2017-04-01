package cn.com.wh.emergencydepartment.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.wh.emergencydepartment.domain.EmergencyDepartment;
import cn.com.wh.emergencydepartment.domain.SearchVo;
import cn.com.wh.emergencydepartment.service.IEmergencyDepartment;
import cn.com.wh.safeaccident.util.Tool;
import cn.com.wh.support.SessionSupport;

import com.google.gson.Gson;
/**
 * 
 * <br/>功能:应急机构action
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2015年9月28日
 * <br/>修改日期: 2015年9月28日
 * <br/>修改列表:
 */
public class EmergencyDepartmentAction extends BaseActionSupport{

	private static final long serialVersionUID = -9036816638265397456L;
	
	@Autowired
	private IEmergencyDepartment emergencyDepartmentService;
	
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;
	
	private SearchVo  vo;//查询vo
	
	private EmergencyDepartment info;
	
	private Integer emergencyId;
	
	/**
	 * 
	 * 功能：应急机构分页
	 * @return
	 */
	public String findEmergencyDepList(){
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart() / (double) this.getIDisplayLength())) + 1;
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "departType"),new Sort.Order(Direction.ASC, "createTime"));
			Page<EmergencyDepartment> pageInfo = emergencyDepartmentService.findByPage(this.getPageable(sort), vo);
			List<EmergencyDepartment> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {
				for (EmergencyDepartment info : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					//主键
					map.put("id", info.getId());
					//序号
					map.put("no", i);
					//机构名称
					map.put("title", info.getDepartName()==null?"":info.getDepartName());
					//联系电话
					map.put("phone", info.getPhone()==null?"":info.getPhone());
					//集团号码
					map.put("groupNum", info.getGroupNumber()==null?"":info.getGroupNumber());
					//职务
					map.put("job", info.getJob()==null?"":info.getJob());
					//公司名称
					map.put("companyName", info.getGroupName()==null?"":info.getGroupName());
					//机构类型
					map.put("type", info.getDepartType());
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
     * 功能：添加应急机构
     * @return
     */
	public String saveOrUpdateEmergencyDepartment() {
		try {
			if (info.getId()!=null) {
				EmergencyDepartment oldInfo = emergencyDepartmentService.findOne(info.getId());
				oldInfo.setDepartName(info.getDepartName());
				oldInfo.setDepartType(info.getDepartType());
				oldInfo.setGroupNumber(info.getGroupNumber());
				oldInfo.setJob(info.getJob());
				oldInfo.setPhone(info.getPhone());
				emergencyDepartmentService.saveEmergency(oldInfo);
				ajax("1");
				//记录日志
				logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
						this.getRequest().getRemoteAddr(), 
						"修改应急机构成功", 
						LogType.LOG_YJ_UPDATE, 
						oldInfo, 
						oldInfo.getId()) );
			}else {
				info.setCreateTime(new Date());
				info.setIsDelete(0);
				info.setIsForkGroup(getLoginUser().getIsForkGroup());
				info.setGroupId(getLoginUser().getIsForkGroup());
				info.setGroupName(((SessionSupport)(this.getSessionSupport())).getCurrentCompanyName());
				info.setCreateUserId(getLoginUser().getUserId());
				emergencyDepartmentService.saveEmergency(info);
				ajax("1");
				
				//记录日志
				logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
						this.getRequest().getRemoteAddr(), 
						"新增应急机构成功", 
						LogType.LOG_YJ_ADD, 
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
	 * 功能：删除应急机构
	 * 
	 * @return
	 */
	public String deleteEmergencyDepartment() {
		try {
			emergencyDepartmentService.deleteEmergency(emergencyId);
			ajax("1");
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"删除应急机构成功", 
					LogType.LOG_YJ_DELETE, 
					null, 
					emergencyId) );
		} catch (Exception e) {
			e.printStackTrace();
			ajax("0");
		}
		return null;
	}
	
	/**
	 * 获得试卷详情
	 * @return
	 */
	public String loadEmergencyDepartmentDetail(){
        info = emergencyDepartmentService.findOne(emergencyId);
		return "update";
	}
	
	
	public SearchVo getVo() {
		return vo;
	}
	public void setVo(SearchVo vo) {
		this.vo = vo;
	}

	public EmergencyDepartment getInfo() {
		return info;
	}

	public void setInfo(EmergencyDepartment info) {
		this.info = info;
	}

	public Integer getEmergencyId() {
		return emergencyId;
	}

	public void setEmergencyId(Integer emergencyId) {
		this.emergencyId = emergencyId;
	}

	
}
