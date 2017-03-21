package cn.com.qytx.hotline.phonetask.service;

import java.io.Serializable;
import java.util.List;

import cn.com.qytx.hotline.phonetask.domain.Question;
import cn.com.qytx.platform.base.service.BaseService;

/**
 * 试题服务类
 * 
 * @author liyanlei
 * 
 * @date : Apr 15, 2014 3:54:22 PM
 * 
 * @version 1.0
 */
public interface IQuestionService extends BaseService<Question>, Serializable {


	/**
	 * 删除试题并且删除试题项
	 * 
	 * @param 试题主键id
	 */
	void deleteAndItemById(Integer id);




	/**
	 * 保存所有的问题
	 * 
	 * @param list
	 */
	void save(List<Question> list, Integer questionnareId, Integer statue);
}
