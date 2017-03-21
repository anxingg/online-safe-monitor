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

import cn.com.qytx.cbb.dict.service.IDict;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.wh.exam.domain.ExamQuestion;
import cn.com.wh.exam.domain.ExamQuestionItem;
import cn.com.wh.exam.domain.SearchVo;
import cn.com.wh.exam.service.ExamQuestionItemService;
import cn.com.wh.exam.service.ExamQuestionService;
import cn.com.wh.safeaccident.util.Tool;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ExamQuestionAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5933626872987577768L;

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

	private Integer questionId;

	private String operType;// 操作类型

	private String itemStr;

	private ExamQuestion question;// 试题类

	private String title;// 试题名称

	private Integer titleType;
	
    private Integer state;//0生效1失效
	
    private SearchVo search ;
	/**
	 * 
	 * 功能：试题列表
	 * 
	 * @return
	 */
	public String listExamQuestion() {

		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()
					/ (double) this.getIDisplayLength())) + 1;
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "createTime"));
			Page<ExamQuestion> pageInfo = examQuestionService.findByPage(
					this.getPageable(sort), search);
			List<ExamQuestion> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			Map<String, String> dictmap = dictService.findMap("examType", 1);
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {
				for (ExamQuestion examQuestion : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", examQuestion.getId());
					map.put("no", i);
					map.put("title", examQuestion.getTitle());
					if (examQuestion.getTitleType() != null) {
						if(dictmap.containsKey(examQuestion.getTitleType().toString())){
							map.put("titleType",
									dictmap.get(examQuestion.getTitleType().toString()));
						}else{
							map.put("titleType", "");
						}
					} else {
						map.put("titleType", "");
					}
					if (examQuestion.getQuestionType() != null) {
						if (examQuestion.getQuestionType() == 0) {
							map.put("questionType", "单选题");
						} else {
							map.put("questionType", "多选题");
						}
					} else {
						map.put("questionType", "");
					}
					if (examQuestion.getState() != null) {
						if (examQuestion.getState() == 0) {
							map.put("state", "生效");
						} else {
							map.put("state", "失效");
						}
					} else {
						map.put("state", "");
					}
					//分值
					if (examQuestion.getScore()==null) {
						map.put("score", 0);
					}else {
						map.put("score", examQuestion.getScore());
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

	/**
	 * 
	 * 功能：（添加/修改）问题
	 * 
	 * @return
	 */
	public String addQuestion() {
		try {
		   Integer groupId =  getLoginUser().getGroupId();
			Gson itemGson = new Gson();
			//Map<String, Object> questionMap = new HashMap<String, Object>();
			List<ExamQuestionItem> itemList = itemGson.fromJson(itemStr,
					new TypeToken<List<ExamQuestionItem>>() {
					}.getType());
			if ("add".equals(operType)) {// 添加
				question.setGroupId(groupId);
				question.setIsForkGroup(groupId);
				question.setCreateTime(new Timestamp(System.currentTimeMillis()));
				examQuestionService.saveOrUpdate(question);
				Integer questionId = question.getId();// 得到试题Id
				if (itemList != null && itemList.size() > 0) {
					for (ExamQuestionItem questionItem : itemList) {
						questionItem.setQuestionId(questionId);
					}
					examQuestionItemService.saveOrUpdate(itemList);
				}
				//记录日志
				question.setQuestionItems(null);
				logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
						this.getRequest().getRemoteAddr(), 
						"新增试题成功", 
						LogType.LOG_EQ_ADD, 
						question, 
						question.getId()) );
				ajax("1");
			} else {// 修改
				ExamQuestion oldQuestion = this.examQuestionService
						.findOne(questionId);
				oldQuestion.setQuestionType(question.getQuestionType());
				oldQuestion.setRightAnswer(question.getRightAnswer());
				oldQuestion.setScore(question.getScore());
				oldQuestion.setTitle(question.getTitle());
				oldQuestion.setTitleType(question.getTitleType());
				oldQuestion.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
				oldQuestion = this.examQuestionService.saveOrUpdate(oldQuestion);
				int deleteFlag = this.examQuestionItemService
						.deleteManyItemByQuestionId(questionId);
				if (deleteFlag > 0) {
					if (itemList != null && itemList.size() > 0) {
						for (ExamQuestionItem questionItem : itemList) {
							questionItem.setQuestionId(questionId);
						}
						examQuestionItemService.saveOrUpdate(itemList);
					}
				}
				//记录日志
				logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
						this.getRequest().getRemoteAddr(), 
						"修改试题成功", 
						LogType.LOG_EQ_UPDATE, 
						oldQuestion, 
						oldQuestion.getId()) );
				ajax("2");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajax("0");
		}
		return null;
	}

	/**
	 * 功能：查询试题
	 * 
	 * @return
	 */
	public String findQuestion() {
		ExamQuestion tempQuestion = this.examQuestionService
				.findOne(questionId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("questionTitle", tempQuestion.getTitle());
		map.put("questionId", tempQuestion.getId());
		map.put("orderLevel", tempQuestion.getOrderLevel());
		map.put("titleType", tempQuestion.getTitleType());
		map.put("questionType", tempQuestion.getQuestionType());
		map.put("rightAnswer", tempQuestion.getRightAnswer());
		map.put("score", tempQuestion.getScore());
		List<ExamQuestionItem> tempItemList = this.examQuestionItemService
				.findByQuestionId(tempQuestion.getId());
		map.put("itemList", tempItemList);
		Gson gson = new Gson();
		String gsonStr = gson.toJson(map);
		ajax(gsonStr);
		return null;
	}

	/**
	 * 功能：删除试题
	 * 
	 * @return
	 */
	public String deleteQuestion() {
		try {
			this.examQuestionService.delete(questionId, false);
			this.examQuestionItemService.updateManyItemByQuestionId(questionId);
			
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					"删除试题成功", 
					LogType.LOG_EQ_DELETE, 
					null, 
					questionId) );
			ajax("1");
		} catch (Exception e) {
			e.printStackTrace();
			ajax("0");
		}
		return null;
	}
	
	/**
	 * 
	 * 功能：是否生效
	 * 
	 * @return
	 */
	public String updateQuestionState(){
		try {
			this.examQuestionService.updateQuestionState(questionId, state);
			//记录日志
			logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
					this.getRequest().getRemoteAddr(), 
					state == 0?"设置试题生效成功":"设置试题失效成功", 
					state == 0?LogType.LOG_EQ_OK:LogType.LOG_EQ_FAIL, 
					null, 
					questionId) );
			ajax("1");
		} catch (Exception e) {
			e.printStackTrace();
			ajax("0");
		}
		return null;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getItemStr() {
		return itemStr;
	}

	public void setItemStr(String itemStr) {
		this.itemStr = itemStr;
	}

	public ExamQuestion getQuestion() {
		return question;
	}

	public void setQuestion(ExamQuestion question) {
		this.question = question;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTitleType() {
		return titleType;
	}

	public void setTitleType(Integer titleType) {
		this.titleType = titleType;
	}

	public SearchVo getSearch() {
		return search;
	}
	public void setSearch(SearchVo search) {
		this.search = search;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	
}
