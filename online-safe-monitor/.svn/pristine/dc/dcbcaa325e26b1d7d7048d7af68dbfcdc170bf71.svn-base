package cn.com.wh.exam.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.dict.domain.Dict;
import cn.com.qytx.cbb.dict.service.IDict;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.wh.exam.domain.ExamPaper;
import cn.com.wh.exam.domain.ExamPaperQuestion;
import cn.com.wh.exam.domain.ExamQuestion;
import cn.com.wh.exam.domain.ExamQuestionItem;
import cn.com.wh.exam.domain.SearchVo;
import cn.com.wh.exam.service.ExamQuestionItemService;
import cn.com.wh.exam.service.ExamQuestionService;
import cn.com.wh.exam.service.IExamPaper;
import cn.com.wh.safeaccident.util.Tool;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/**
 * 
 * <br/>功能:试卷action
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2015年8月21日
 * <br/>修改日期: 2015年8月21日
 * <br/>修改列表:
 */
public class ExamPaperAction extends BaseActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9036816638265397456L;
	
	@Autowired
	private IExamPaper examPaperService;
	
	@Autowired
	private ExamQuestionService examQuestionService;
	
	@Autowired
	private ExamQuestionItemService examQuestionItemService;
	
	@Autowired
	private IDict dictService;
	
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;
	
	private SearchVo  vo;//查询vo
	
	private ExamPaper paper;
	
	private String paperQuestion;
	
	private Integer paperId;
	
	private Integer state;//0生效1失效
	
	private String examType;
	
	
	
	
	/**
	 * 
	 * 功能：试卷分页
	 * @return
	 */
	public String findPaperList(){
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart() / (double) this.getIDisplayLength())) + 1;
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "createTime"));
			state = null==state?-1:state;
			if(state==0){
				vo.setState(0);
			}
			Page<ExamPaper> pageInfo = examPaperService.findByPage(this.getPageable(sort), vo);
			List<ExamPaper> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			
			
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			Map<String, String> dictmap = dictService.findMap("examType", 1);
			if (null != list && list.size() > 0) {
				for (ExamPaper paper : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					if(state==0){
						if(paper.getState()==0){
							//主键
							map.put("id", paper.getId());
							//序号
							map.put("no", i);
							//试卷名称
							map.put("title", paper.getPaperName());
							//试卷类型
							if(paper.getPaperType()!=null){
								if (dictmap!=null&&dictmap.get(paper.getPaperType().toString())!=null) {
									map.put("titleType", dictmap.get(paper.getPaperType().toString()));
								}else {
									map.put("titleType", "");
								}
							}else{
								map.put("titleType", "");
							}
							//题干数量
							if(paper.getQuestionNum()!=null){
								map.put("questionNum", paper.getQuestionNum());
							}else{
								map.put("questionType", 0);
							}
							//是否生效
							map.put("state", paper.getState());
							mapList.add(map);
							i++;
						}
					}else{
					//主键
					map.put("id", paper.getId());
					//序号
					map.put("no", i);
					//试卷名称
					map.put("title", paper.getPaperName());
					//试卷类型
					if(paper.getPaperType()!=null){
						if (dictmap!=null&&dictmap.get(paper.getPaperType().toString())!=null) {
							map.put("titleType", dictmap.get(paper.getPaperType().toString()));
						}else {
							map.put("titleType", "");
						}
					}else{
						map.put("titleType", "");
					}
					//题干数量
					if(paper.getQuestionNum()!=null){
						map.put("questionNum", paper.getQuestionNum());
					}else{
						map.put("questionType", 0);
					}
					//是否生效
					map.put("state", paper.getState());
					
					mapList.add(map);
					i++;
					}
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
     * 功能：添加试卷
     * @return
     */
	public String addPaper() {
		try {
			Gson itemGson = new Gson();
			List<ExamPaperQuestion> questionlist = itemGson.fromJson(paperQuestion,
					new TypeToken<List<ExamPaperQuestion>>() {
					}.getType());
			paper.setCreateTime(new Timestamp(System.currentTimeMillis()));
			paper.setIsDelete(0);
			paper.setIsForkGroup(getLoginUser().getIsForkGroup());
			paper.setGroupId(getLoginUser().getIsForkGroup());
			paper.setState(0);
			examPaperService.savePaper(paper, questionlist);
			ajax("1");
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"新增试卷成功", 
					LogType.LOG_EP_ADD, 
					paper, 
					paper.getId()) );
		} catch (Exception e) {
			e.printStackTrace();
			ajax("0");
		}
		return null;
	}
	
	/**
	 * 功能：删除试卷
	 * 
	 * @return
	 */
	public String deletePaper() {
		try {
			examPaperService.deletePaper(paperId);
			ajax("1");
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"删除试卷成功", 
					LogType.LOG_EP_DELETE, 
					null, 
					paperId) );
		} catch (Exception e) {
			e.printStackTrace();
			ajax("0");
		}
		return null;
	}
	
	/**
	 * 功能：失效/生效
	 * 
	 * @return
	 */
	public String updateState() {
		try {
			examPaperService.updateState(paperId,state);
			ajax("1");
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					state == 0 ? "试卷生效成功" : "试卷失效成功", 
					state == 0 ? LogType.LOG_EP_OK : LogType.LOG_EP_FAIL, 
					null, 
					paperId) );
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
	public String loadPaperDetail(){
        paper = examPaperService.findOne(paperId);
        if (paper!=null) {
        	Dict dict = dictService.loadByTypeTagValue("examType", 1, paper.getPaperType());
        	if (dict!=null) {
        		examType = dict.getName();
			}
		}
		return "detail";
	}
	/**
	 * 获得试卷的试题分页
	 * @return
	 */
	public String findPaperQuestion(){

		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart() / (double) this.getIDisplayLength())) + 1;
			Sort sort = new Sort(new Sort.Order(Direction.ASC, "id"));
			Page<ExamPaperQuestion> pageInfo = examPaperService.findPaperQuestionByPage(this.getPageable(sort), paperId);
			List<ExamPaperQuestion> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {
				//查询试卷类型map
				//Map<String, String> dictmap = dictService.findMap("examType", 1);
				//封装id字符串
				String questionIds = getQuestionIds(list);
				//获得试题map
				Map<Integer, ExamQuestion> questionMap = examQuestionService.findByQuestionIds(questionIds);
				//获得选项map
				Map<Integer, List<ExamQuestionItem>> itemMap = examQuestionItemService.findItemsByQuestionIds(questionIds);
				for (ExamPaperQuestion question : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					//序号
					map.put("no", i);
					//分值
					map.put("score", question.getScore());
					
					if (questionMap!=null&&questionMap.get(question.getQuestionId())!=null) {
						ExamQuestion eq = questionMap.get(question.getQuestionId());
						//试题名称
						map.put("title", eq.getTitle());
						//题目类型
						if (eq.getQuestionType()!=null) {
							if (eq.getQuestionType()==1) {
								map.put("questionType", "多选");
							}else {
								map.put("questionType", "单选");
							}
						}
					}else {
						map.put("title", "");
						map.put("titleType", "");
					}
					//题目选项
					if (itemMap!=null&&itemMap.get(question.getQuestionId())!=null) {
						List<ExamQuestionItem> itemList = itemMap.get(question.getQuestionId());
						String itemStr = "";
						for (ExamQuestionItem examQuestionItem : itemList) {
							itemStr += examQuestionItem.getItemCode()+"."+examQuestionItem.getItemName();
						}
						map.put("items", itemStr);
					}else {
						map.put("items", 0);
					}
					
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
	private String getQuestionIds(List<ExamPaperQuestion> list){
		String questionIds = "";
		if (list!=null&&list.size()>0) {
			for (ExamPaperQuestion examPaperQuestion : list) {
				questionIds += "," + examPaperQuestion.getQuestionId();
			}
		}
		return questionIds;
	}
	
	public SearchVo getVo() {
		return vo;
	}
	public void setVo(SearchVo vo) {
		this.vo = vo;
	}
	public ExamPaper getPaper() {
		return paper;
	}
	public void setPaper(ExamPaper paper) {
		this.paper = paper;
	}
	public String getPaperQuestion() {
		return paperQuestion;
	}
	public void setPaperQuestion(String paperQuestion) {
		this.paperQuestion = paperQuestion;
	}
	public Integer getPaperId() {
		return paperId;
	}
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	
}
