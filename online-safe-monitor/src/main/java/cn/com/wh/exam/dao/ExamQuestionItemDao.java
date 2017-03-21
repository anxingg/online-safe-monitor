package cn.com.wh.exam.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.wh.exam.domain.ExamQuestionItem;

@Component
public class ExamQuestionItemDao extends BaseDao<ExamQuestionItem, Integer>{

	/**
	 * 功能：根据试题Id删除该试题下选项
	 * @param questionId
	 */
	public int deleteManyItemByQuestionId(Integer questionId) {
		
		return super.entityManager.createNativeQuery("delete from tb_wuhai_question_item where question_Id="+questionId).executeUpdate();
	}
	
	
	/**
	 * 功能：根据试题Id删除该试题下选项
	 * @param questionId
	 */
	public int updateManyItemByQuestionId(Integer questionId) {
		
		return super.entityManager.createNativeQuery("update tb_wuhai_question_item set is_Delete=1 where question_Id="+questionId).executeUpdate();
	}
	/**
	 * 
	 * 功能：查询选项
	 * @param questionId
	 * @return
	 */
	public List<ExamQuestionItem> findByQuestionId(Integer questionId) {
		return unDeleted().findAll("questionId=?", questionId);
	}
	
	
	/**
	 * 
	 * 功能：查询选项(全部)
	 * @param questionId
	 * @return
	 */
	public List<ExamQuestionItem> findByQuestionIdunDeleted(Integer questionId) {
		return findAll("questionId=?", questionId);
	}
	/**
	 * 
	 * 功能：通过试题id字符串查询选项
	 * @param questionIds
	 * @return
	 */
	public List<ExamQuestionItem> findItemsByQuestionIds(String questionIds) {
		if (StringUtils.isNotBlank(questionIds)) {
			if (questionIds.startsWith(",")) {
				questionIds = questionIds.substring(1);
			}
			if (questionIds.endsWith(",")) {
				questionIds = questionIds.substring(0, questionIds.length()-1);
			}
			String jpql = "questionId in ("+questionIds+")";
			//不加数据权限
			return super.dataFilter("154341").findAll(jpql);
		}else {
			return null;
		}
		
	}
	
}
