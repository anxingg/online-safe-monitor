package cn.com.qytx.hotline.phonetask.service;

import java.io.Serializable;

import cn.com.qytx.hotline.phonetask.domain.Questionnaire;
import cn.com.qytx.platform.base.service.BaseService;

/**
 * 试卷服务类
 * 
 * @author liyanlei
 * @date : Apr 15, 2014 3:11:38 PM
 * @version 1.0
 */
public interface IQuestionnaireService extends BaseService<Questionnaire>,
		Serializable {

	

	/**
	 * 加载试卷对象
	 * 
	 * @param 试卷主键id
	 * @return
	 */
	Questionnaire load(Integer id);


	/**
	 * 序列化加载对象，加载试卷包含试题、试题项
	 * 
	 * @param id
	 * @return
	 */
	Questionnaire serializeLoadQuestionnaire(Integer id);



	/**
	 * 更新问卷
	 * 
	 * @param qaire
	 * @param id
	 */
	void update(Questionnaire qaire, Integer id);

}
