package cn.com.qytx.hotline.phonetask.service;

import java.io.Serializable;

import cn.com.qytx.hotline.phonetask.domain.QuestionnaireUser;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * 试卷用户发布服务类
 * 
 * @author liyanlei
 * 
 * @date : Apr 15, 2014 4:34:38 PM
 * 
 * @version 1.0
 */
public interface IQuestionnaireUserService extends
		BaseService<QuestionnaireUser>, Serializable {


	/**
	 * 当前问卷的所以完成人的信息
	 * 
	 * @param pageable
	 * @param beginTime
	 * @param endTime
	 * @param searchkey
	 * @param questionnaireId
	 * @return
	 */
	Page<QuestionnaireUser> totalList(Pageable pageable,
			String beginTime, String endTime, String searchkey,
			Integer questionnaireId, UserInfo userinfo, int state);

	/**
	 * 通过被调查人和问卷ID 得到被调查的提交信息
	 * 
	 * @param userId
	 * @param questionnaireId
	 * @return
	 */
	QuestionnaireUser getQuestionnaireUserBySurveyUserId(Integer userId,
			Integer questionnareId);

	/**
	 * 
	 * 功能：统计某个执行坐席对 某个调查问卷的回访量
	 * 
	 * @param userId
	 *            执行坐席id
	 * @param questionnareId
	 *            试卷id
	 * @return
	 */
	Integer countquestionUser(Integer seatId, Integer questionnareId);

}
