package cn.com.wh.training.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import cn.com.wh.safeaccident.util.Tool;
import cn.com.wh.training.domain.SearchVo;
import cn.com.wh.training.domain.Training;
import cn.com.wh.training.service.ITraining;
import cn.com.wh.util.DataInitUtil;

import com.google.gson.Gson;

public class TrainingAction extends BaseActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7253663036650159840L;

	@Autowired
	private ITraining trainingService;
	
    /**
     * 系统日志接口
     */
    @Resource
    private ILog logService;
	
	private SearchVo search;
	
	private Training training;
	
	private String operType;//操作类型
	
	private Integer trainingType;//培训类型
	
	private Integer trainingId;//培训id
	


	/**
	 * 
	 * 功能：培训列表
	 * @return
	 */
	public String listTraining(){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()
					/ (double) this.getIDisplayLength())) + 1;
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "groupId"),new Sort.Order(Direction.DESC, "createTime"));
			Page<Training> pageInfo = trainingService.findByPage(this.getPageable(sort), search);
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			List<Training> list = pageInfo.getContent();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {
				for (Training training : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					if (DataInitUtil.companyMap!=null&&DataInitUtil.companyMap.get(training.getGroupId())!=null) {
						map.put("companyName", DataInitUtil.companyMap.get(training.getGroupId()));
					}else {
						map.put("companyName","");
					}
					map.put("id", training.getId());
					map.put("no", i);
					//StringUtils.defaultString(str, "-");
					map.put("details", training.getDetails()==null?"-":training.getDetails());
					map.put("charge", training.getCharge());
					map.put("trainRate", training.getTrainRate());
					map.put("trainObject", training.getTrainObject());
					map.put("trainForm", training.getTrainForm());
					map.put("memo", training.getMemo()==null?"-":training.getMemo());
					map.put("trainYear", training.getTrainYear());
					map.put("subject", training.getSubject());
					String trainTime = "";
					if(training.getTrainTime()!=null){
						 trainTime = sdf.format(training.getTrainTime());
					}
					map.put("trainTime", trainTime);
					map.put("speaker", training.getSpeaker());
					map.put("address", training.getAddress());
					map.put("school", training.getSchool()==null?"-":training.getSchool());
					map.put("effect", training.getEffect()==null?"-":training.getEffect());
					mapList.add(map);
					i++;
				}
			}Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("iTotalRecords", pageInfo.getTotalElements());
			jsonMap.put("iTotalDisplayRecords", pageInfo.getTotalElements());
			jsonMap.put("aaData", mapList);
			Gson json = new Gson();
			String jsons = json.toJson(jsonMap);
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(jsons);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
		
	}
	
	/**
	 * 
	 * 功能：增加或者修改培训
	 * @return
	 */
	public String addOrUpdateTraining(){
		try {
			Integer groupId  = this.getLoginUser().getGroupId();
			if("add".equals(operType)){
					training.setCreateTime(new Date());
					training.setGroupId(groupId);
					training.setIsForkGroup(groupId);
					training.setTrainType(trainingType);
					training = this.trainingService.saveOrUpdate(training);
					//记录日志
					logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
							this.getRequest().getRemoteAddr(), 
							trainingType==0?"新增年度培训成功":"新增安全培训记录成功", 
							trainingType==0?LogType.LOG_YDPX_ADD : LogType.LOG_AQPX_ADD, 
							training, 
							training.getId()) );
					ajax("1");
			}else{
				Training trainingOld = trainingService.findOne(trainingId);
				trainingOld.setUpdateTime(new Date());
				trainingOld.setTrainObject(training.getTrainObject());
				trainingOld.setDetails(training.getDetails());
				if(trainingType==0){
					trainingOld.setTrainYear(training.getTrainYear());
					trainingOld.setCharge(training.getCharge());
					trainingOld.setTrainForm(training.getTrainForm());
					trainingOld.setMemo(training.getMemo());
					trainingOld.setTrainRate(training.getTrainRate());
					trainingOld.setAttachmentIds(training.getAttachmentIds());
					trainingOld.setAttachName(training.getAttachName());
				}else if(trainingType==1){
					trainingOld.setSubject(training.getSubject());
					trainingOld.setTrainTime(training.getTrainTime());
					trainingOld.setSpeaker(training.getSpeaker());
					trainingOld.setEffect(training.getEffect());
					trainingOld.setSchool(training.getSchool());
					trainingOld.setAddress(training.getAddress());
					trainingOld.setNum(training.getNum());
				}
				trainingOld = this.trainingService.saveOrUpdate(trainingOld);
				ajax("2");
				//记录日志
				logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
						this.getRequest().getRemoteAddr(), 
						trainingType==0?"修改年度培训成功":"修改安全培训记录成功", 
						trainingType==0?LogType.LOG_YDPX_UPDATE : LogType.LOG_AQPX_UPDATE, 
						trainingOld, 
						trainingOld.getId()) );
			}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			ajax("0");
		}
		return null;
		
	}
	
	/**
	 * 
	 * 功能：培训详情
	 * @return
	 */
	public String findTraining(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Training training  =  trainingService.findOne(trainingId);
		Map<String,Object> map  = new HashMap<String, Object>();
		map.put("trainYear", training.getTrainYear());
		map.put("details", training.getDetails());
		map.put("charge", training.getCharge());
		map.put("trainRate", training.getTrainRate());
		map.put("trainObject", training.getTrainObject());
		map.put("trainForm", training.getTrainForm());
		map.put("num", training.getNum());
		if(training.getMemo()==null||training.getMemo()==""){
			map.put("memo", "");
		}else{
			map.put("memo", training.getMemo());
		}
		map.put("subject", training.getSubject());
		map.put("attachmentIds", training.getAttachmentIds());
		map.put("attachName", training.getAttachName());
		String trainTime = "";
		if(training.getTrainTime()!=null){
			 trainTime = sdf.format(training.getTrainTime());
		}
		map.put("trainTime", trainTime);
		map.put("speaker", training.getSpeaker());
		map.put("address", training.getAddress());
		map.put("school", training.getSchool()==null?"":training.getSchool());
		map.put("effect", training.getEffect()==null?"":training.getEffect());
		Gson gson = new Gson();
		String gsonStr = gson.toJson(map);
		ajax(gsonStr);
		return null;
	}
	
	/**
	 * 
	 * 功能：删除培训
	 * @return
	 */
	public String deleteTraining(){
		try {
			this.trainingService.delete(trainingId, false);
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					trainingType==0?"删除年度培训成功":"删除安全培训记录成功", 
					trainingType==0?LogType.LOG_YDPX_DELETE:LogType.LOG_AQPX_DELETE, 
					null, 
					trainingId) );
			ajax("1");
		} catch (Exception e) {
			ajax("0");
		}
		return null;
	}


	public SearchVo getSearch() {
		return search;
	}


	public void setSearch(SearchVo search) {
		this.search = search;
	}


	public Training getTraining() {
		return training;
	}


	public void setTraining(Training training) {
		this.training = training;
	}


	public String getOperType() {
		return operType;
	}


	public void setOperType(String operType) {
		this.operType = operType;
	}


	public Integer getTrainingType() {
		return trainingType;
	}


	public void setTrainingType(Integer trainingType) {
		this.trainingType = trainingType;
	}


	public Integer getTrainingId() {
		return trainingId;
	}


	public void setTrainingId(Integer trainingId) {
		this.trainingId = trainingId;
	}
}
