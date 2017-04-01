package cn.com.wh.professor.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.wh.professor.domain.Professor;
import cn.com.wh.professor.service.IProfessor;
import cn.com.wh.safeaccident.util.Tool;

/**
 * @版本: 1.0 
 * @开发人员:吴胜光
 * @功能 安全生产专家
 * @创建时间 2015-09-25
 * @修改时间
 * @修改列表
 */
public class ProfessorAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	public IProfessor professorImpl; 
	
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;
	
	private Professor professor;
	
	private Integer vid;
	
	private String name;
	
	private String specialties;
	
	private Integer professorType;
	
	
	/**
	 * 列表
	 * @return
	 */
	public String getList(){
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()
					/ (double) this.getIDisplayLength())) + 1;
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "createTime"));
			Page<Professor> pageInfo = professorImpl.findProfessorByPage(this.getPageable(sort),name,specialties,professorType);
			List<Professor> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {
				for (Professor professor : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("no", i);
					
					map.put("vid", professor.getVid());
					
					map.put("name", professor.getName());
					
					map.put("specialties", professor.getSpecialties());
					
					map.put("title", professor.getTitle());
					
					map.put("workCompany", professor.getWorkCompany());
					
					map.put("phone", professor.getPhone());
					
					String memo = professor.getMemo();
					map.put("memo", memo == null ? "" : memo);
					
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
	 * 新增/修改
	 * @return
	 */
	public String addOrUp(){
		String saveOrUp = "";
		//professorType 专业类别(1.职业卫生专家2.非煤矿山专家3.危险化学品专家)
		//int logType = LogType.LOG_ZJ_ADD;
		int logType = getLogType( 1, professor.getProfessorType());
		try {
			UserInfo userInfo = this.getSessionSupport().getCurrentLoginUser();
			if(professor.getVid()==null){//新增
				professor.setIsForkGroup(userInfo.getGroupId());
				professor.setCreateTime(new Timestamp(System.currentTimeMillis()));
				professor.setCreateUserId(userInfo.getUserId());
				professor.setIsDelete(0);//是否删除
				saveOrUp = "新增";
			}else{//修改
				Professor professorOld = professorImpl.findOne(professor.getVid());
				professor.setIsForkGroup(professorOld.getIsForkGroup());
				professor.setCreateTime(professorOld.getCreateTime());
				professor.setCreateUserId(professorOld.getCreateUserId());
				professor.setIsDelete(0);//是否删除
				saveOrUp = "修改";
				//logType = LogType.LOG_ZJ_UPDATE;
				logType = getLogType( 2, professor.getProfessorType());
			}
			professorImpl.saveOrUpdate(professor);
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					getTypeName(professor.getProfessorType())+saveOrUp, 
					logType, 
					professor, 
					professor.getVid()) );
			LOGGER.info(getTypeName(professor.getProfessorType())+saveOrUp+"成功，vid="+professor.getVid());
			ajax(1);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(getTypeName(professor.getProfessorType())+saveOrUp+"异常，e:"+e.getMessage());
			ajax(0);
		}
		return null;
	}
	
	/**
	 * @param operation 操作类型（1表示新增、2表示修改、3表示删除）
	 * @param professorType 专业类别(1.职业卫生专家2.非煤矿山专家3.危险化学品专家)
	 * @return
	 */
	private int getLogType( int operation, int professorType){
		if(operation == 1){
			if(professorType == 1){
				return LogType.LOG_ZJ1_ADD;
			}else if(professorType == 2){
				return LogType.LOG_ZJ2_ADD;
			}else {
				return LogType.LOG_ZJ3_ADD;
			}
		}else if(operation == 2){
			if(professorType == 1){
				return LogType.LOG_ZJ1_UPDATE;
			}else if(professorType == 2){
				return LogType.LOG_ZJ2_UPDATE;
			}else {
				return LogType.LOG_ZJ3_UPDATE;
			}
		}else {
			if(professorType == 1){
				return LogType.LOG_ZJ1_DELETE;
			}else if(professorType == 2){
				return LogType.LOG_ZJ2_DELETE;
			}else {
				return LogType.LOG_ZJ3_DELETE;
			}
		}
	}
	
	/**
	 * 获取信息
	 * @return
	 */
	public String getProfessorInfo(){
		try {
			professor = professorImpl.findOne(vid);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Professor查询异常，vid=:"+vid, e);
		}
		return null;
	}

	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		try {
			professor = professorImpl.findOne(professor.getVid());
			professor.setIsDelete(1);
			professorImpl.saveOrUpdate(professor);
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					getTypeName(professor.getProfessorType())+"删除", 
					//LogType.LOG_ZJ_DELETE, 
					getLogType( 3, professor.getProfessorType()), 
					professor, 
					professor.getVid()) );
			LOGGER.info(getTypeName(professor.getProfessorType())+"删除成功，vid="+professor.getVid());
			ajax(1);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(getTypeName(professor.getProfessorType())+"删除异常，vid=:"+vid, e);
			ajax(0);
		}
		return null;
	}
	
	/**
	 * getTypeName
	 * @param professorType1.职业卫生专家2.非煤矿山专家3.危险化学品专家
	 * @return
	 */
	public String getTypeName(Integer professorType){
		if(professorType==null){
			return "未知类型";
		}
		switch (professorType) {
		case 1:
			return "职业卫生专家";
		case 2:
			return "非煤矿山专家";
		case 3:
			return "危险化学品专家";
		default:
			return "未知类型";
		}
	}

	public Professor getProfessor() {
		return professor;
	}


	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSpecialties() {
		return specialties;
	}


	public void setSpecialties(String specialties) {
		this.specialties = specialties;
	}


	public Integer getProfessorType() {
		return professorType;
	}


	public void setProfessorType(Integer professorType) {
		this.professorType = professorType;
	}
	
	
	
}
