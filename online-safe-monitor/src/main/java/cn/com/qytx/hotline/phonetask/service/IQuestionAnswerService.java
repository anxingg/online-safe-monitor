package cn.com.qytx.hotline.phonetask.service;

import java.util.List;
import java.util.Map;

import cn.com.qytx.hotline.phonetask.domain.PhoneTaskUser;
import cn.com.qytx.hotline.phonetask.domain.QuestionAnswer;
import cn.com.qytx.hotline.phonetask.domain.QuestionnaireUser;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 试题答案服务类
 * 
 * @author liyanlei
 * 
 * @date : Apr 15, 2014 3:56:38 PM
 * 
 * @version 1.0
 */
public interface IQuestionAnswerService extends BaseService<QuestionAnswer> {

	/**
	 * 查询某个人某个试卷的答案
	 * 
	 * @param userId
	 * @param questionnaireId
	 * @return
	 */
	List<QuestionAnswer> getUserAnswers(Integer userId,
			Integer questionnaireId);


	/**
	 * 返回用户答案
	 * 
	 * @param userInfo
	 * @param questionnaireId
	 * @return
	 */
	Map<String, String> findAnswerMap(PhoneTaskUser userInfo,
			Integer questionnaireId);

	/**
	 * 保存所以答案，更新试卷
	 * 
	 * @param list
	 * @param questionnaire
	 */
	void saveAllAnswer(List<QuestionAnswer> list,
			Integer questionnaireId, UserInfo userInfo);

	/**
	 * 
	 * 功能：保存所以答案，更新试卷
	 * 
	 * @param phoneUserid
	 *            外呼任务用户
	 * @param questionAnswerArrayJSONStr
	 *            答案json
	 * @param userInfo
	 *            外呼坐席
	 * @param questionnaireId
	 *            试卷id
	 * @param phontTaskId
	 *            任务id
	 * @param phoneState
	 *            外呼状态
	 * @param qnu
	 *            答卷人
	 */
	void saveAllAnswer(Integer phoneUserid,
			String questionAnswerArrayJSONStr, UserInfo userInfo,
			Integer questionnaireId, Integer phontTaskId, int phoneState,
			QuestionnaireUser qnu);

}
