package cn.com.wh.exam.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IUser;
import cn.com.wh.exam.domain.ExamPaper;
import cn.com.wh.exam.domain.ExamQuestion;
import cn.com.wh.exam.domain.ExamQuestionItem;
import cn.com.wh.exam.domain.ExamTest;
import cn.com.wh.exam.domain.ExamUserTest;
import cn.com.wh.exam.domain.ExamUserTestLog;
import cn.com.wh.exam.service.ExamQuestionItemService;
import cn.com.wh.exam.service.ExamTestService;
import cn.com.wh.exam.service.IExamPaper;
import cn.com.wh.exam.service.IExamPaperQuestion;
import cn.com.wh.exam.service.IExamUserTest;
import cn.com.wh.exam.service.IExamUserTestLog;
import cn.com.wh.util.DataInitUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ExamTestDetailAction extends BaseActionSupport{
	private static SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private IExamPaper examPaperService;
	@Autowired
	private ExamTestService examTestService;
	@Autowired
	private IUser userService;
	@Autowired
	private IExamPaperQuestion examPaperQuestionService;
	
	private String questionResult;
	
	private ExamUserTest examUserTest;
	
	private long startTime;// 考试开始时间
	
	private Integer testId;//考试id
	
	@Autowired
	private IExamUserTest examUserTestDetailService;
	
	@Autowired
	private ExamQuestionItemService examQuestionItemService;
	
	@Autowired
	private IExamUserTestLog examUserTestLogService;
	
	private Integer examPaperId;
	
	private String userName;
	
	private String idcard;
	/**
	 * 
	 * 功能：获取考试时间
	 * @return
	 */
	public String getTestTime(){
		try {
			UserInfo userInfo = this.getLoginUser();
			ExamPaper examPaper = examPaperService.findOne(examPaperId);
			if (examPaper != null) {
				Integer testTime = examPaper.getPaperTime();
				long newTestTime = testTime * 60 * 1000;
				long currentTime = System.currentTimeMillis();
				ajax(newTestTime + ":" + currentTime );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 功能：获取试卷
	 * @return
	 */
	public String getExam(){
		try {
			UserInfo userInfo=this.getLoginUser();
			Map<String, Object> map=new HashMap<String, Object>();
			if(userInfo!=null){
				int userId = userInfo.getUserId();
				Integer groupId = this.getLoginUser().getGroupId();
//				ExamTest test = examTestService.findOne(testId);
//				if(test!=null){
//					String paperIds = test.getPaperIds();
//					if(paperIds!=null && !paperIds.equals("")){
//						paperIds = paperIds.substring(1);
//					}
//					String[] paperArr = paperIds.split(",");
//					Random r=new Random();
//					int index = r.nextInt(paperArr.length);
//					int paperId = Integer.parseInt(paperArr[index]);
					ExamPaper examPaper = examPaperService.findOne(testId);
					List<ExamQuestion> list  = examPaperQuestionService.findListByPaperId(testId);
					map.put("testEndTime", YYYYMMDD.format(examPaper.getPaperTime()));
					map.put("examTestConfig", examPaper);
					map.put("list", list);
					map.put("companyName", DataInitUtil.companyMap.get(groupId));
					map.put("testId", examPaper.getId());
					map.put("testName", examPaper.getPaperName());
					ajax(map);
//				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			ajax("");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 功能：交卷
	 * @return
	 */
	public String submitExam(){
		try {
			UserInfo userInfo = this.getLoginUser();
			StringBuffer sb = new StringBuffer();
			Gson gson = new Gson();
			Map<Integer, String> hadQuestionMap = null;
			Map<String,List<Map<String,String>>> jc1 = gson.fromJson(questionResult, new TypeToken<Map<String,List<Map<String,String>>>>(){}.getType());
			if(jc1 != null){
				List<Map<String,String>> jsonMapList  = jc1.get("arr");
				if(jsonMapList != null && jsonMapList.size() > 0){
					hadQuestionMap = new HashMap<Integer, String>();
					for(Map<String,String> jsonMap:jsonMapList){
						String userAnswer = jsonMap.get("value");
						String tempQuestionId = jsonMap.get("key");
						System.out.println(tempQuestionId);
						hadQuestionMap.put(Integer.parseInt(tempQuestionId), userAnswer);
					}
				}
			}
			// }
			List<ExamQuestion> questionList = null;
			questionList = examPaperQuestionService.findListByPaperId(examPaperId);
			// 封装试卷日志实体
			ExamUserTest examUserTestnew = new ExamUserTest();
			examUserTestnew.setGroupId(userInfo.getGroupId());
			examUserTestnew.setUserName(examUserTest.getUserName());
			examUserTestnew.setIdCardNum(examUserTest.getIdCardNum());
			examUserTestnew.setTestId(examUserTest.getTestId());
			examUserTestnew.setTestName(examUserTest.getTestName());
			examUserTestnew.setPaperId(examPaperId);
			examUserTestnew.setIsForkGroup(userInfo.getGroupId());
			examUserTestnew.setPaperName(examUserTest.getPaperName());
			if (startTime != 0) {
				examUserTestnew.setStartTime(new Date(startTime));
			}
			examUserTestnew.setEndTime(new Date());
			List<ExamUserTestLog> questionLogList = null;
			double score = 0.0;
			if (questionList != null) {
				questionLogList = new ArrayList<ExamUserTestLog>();
				int orderLevel = 1;
				int corretNum = 0;// 正确个数
				int courseId = 0;// 课程id
				for (ExamQuestion question : questionList) {
					ExamUserTestLog questionLog = new ExamUserTestLog();
					Integer tempQuestionId = question.getId();
					questionLog.setQuestionId(tempQuestionId);
					questionLog.setQuestName(question.getTitle());
					questionLog.setUserTestId(examUserTestnew.getId());
					List<ExamQuestionItem> questionItemList = examQuestionItemService.findByQuestionId(tempQuestionId);
					StringBuffer sbItem = null;
					if (questionItemList != null && questionItemList.size() > 0) {
						sbItem = new StringBuffer();
						for (int i = 0; i < questionItemList.size(); i++) {
							ExamQuestionItem tempItem = questionItemList
									.get(i);
							if (i == (questionItemList.size() - 1)) {
								sbItem.append(tempItem.getItemCode() + ":"
										+ tempItem.getItemName());
							} else {
								sbItem.append(tempItem.getItemCode() + ":"
										+ tempItem.getItemName() + "&");
							}
						}
					}
					if (sbItem != null) {
						questionLog.setAllItem(sbItem.toString());
					}
					questionLog.setRightItem(question.getRightAnswer());
					if (hadQuestionMap != null
							&& hadQuestionMap.containsKey(tempQuestionId)) {
						String userAnswer = hadQuestionMap.get(tempQuestionId);// 4-3
						String rightAnswer = question.getRightAnswer();// 3-4
						questionLog.setUserCheckItem(userAnswer);
				
							// 判断用户作答的题是否是正确答案
							if (question.getQuestionType() == 0) {// 单选
								if (rightAnswer.equals(userAnswer)) {
									score += question.getPaperQuestionScore();
									corretNum = corretNum + 1;
								} else {
								}
							} else {// 多选
								String[] rightAnswerArr = rightAnswer
										.split("-");// 4,3
								String[] userAnswerArr = userAnswer.split("-");// 3,4
								/*
								 * boolean isExist = true; for(String
								 * tempRightAnswer:rightAnswerArr){ int index =
								 * Arrays.binarySearch(userAnswerArr,
								 * tempRightAnswer); if(index < 0){ isExist =
								 * false; break; } }
								 */
								Set<String> set = new HashSet<String>();
								boolean isExist = true;
								for (String tempUserAnswer : userAnswerArr) {
									set.add(tempUserAnswer);
								}
								int oldLength = set.size();
								for (String tempRightAnswer : rightAnswerArr) {
									set.add(tempRightAnswer);
								}
								int newLength = set.size();
								if (oldLength != newLength) {
									isExist = false;
								}
								if (isExist
										&& rightAnswerArr.length == userAnswerArr.length) {
									score += question.getPaperQuestionScore();
									corretNum = corretNum + 1;
								} else {
								}
							
						}

					}
					questionLogList.add(questionLog);
					orderLevel++;
				}
				double oldScore = examUserTestnew.getScore()==null?0:examUserTestnew.getScore();
				examUserTestnew.setScore(score + oldScore);
				examUserTestDetailService.finishPaper(examUserTestnew,questionLogList);
				ajax("0");// 提交考试状态
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajax("1");
		}

		return null;
	}

	public String getQuestionResult() {
		return questionResult;
	}

	public void setQuestionResult(String questionResult) {
		this.questionResult = questionResult;
	}

	public ExamUserTest getExamUserTest() {
		return examUserTest;
	}

	public void setExamUserTest(ExamUserTest examUserTest) {
		this.examUserTest = examUserTest;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public Integer getExamPaperId() {
		return examPaperId;
	}

	public void setExamPaperId(Integer examPaperId) {
		this.examPaperId = examPaperId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
	
}
