package cn.com.wh.exam.service;

import java.util.List;
import java.util.Map;

import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.exam.domain.ExamQuestionItem;

public interface ExamQuestionItemService extends BaseService<ExamQuestionItem>{

	/**
	 * 功能：根据试题Id删除该试题下选项
	 * @param questionId
	 */
	int deleteManyItemByQuestionId(Integer questionId);
	
	
	int updateManyItemByQuestionId(Integer questionId);
	
	/**
	 * 功能：根据试题id查询选项
	 * @param unitId
	 * @return
	 */
	public List<ExamQuestionItem> findByQuestionId(Integer questionId);
	
	/**
	 * 通过试题ids获得试题选项map
	 * @param questionIds
	 * @return
	 */
	public Map<Integer, List<ExamQuestionItem>> findItemsByQuestionIds(String questionIds);
}
