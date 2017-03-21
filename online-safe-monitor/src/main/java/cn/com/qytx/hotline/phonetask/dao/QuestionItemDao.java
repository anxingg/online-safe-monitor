package cn.com.qytx.hotline.phonetask.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.qytx.hotline.phonetask.domain.QuestionItem;
import cn.com.qytx.platform.base.dao.BaseDao;

/**
 * 
 * 功能:试题选项处理dao 
 * 版本: 1.0 
 * 开发人员: 彭晓东 
 * 创建日期: 2014-2-12 
 * 修改日期: 2014-2-12 
 * 修改列表:
 */
@Repository("questionItemDao")
public class QuestionItemDao extends BaseDao<QuestionItem, Integer> {
	/**
	 * 
	 * 功能:删除试题的选项
	 * 
	 * @param id
	 */
	public void deleteItemByQuestionId(Integer id) {
		String hql = "delete from QuestionItem where question.id =? " ;
		   super.executeQuery(hql, id);
	}

	/**
	 * 功能：根据试题id查询试题的所有选项
	 * 
	 * @param questionId
	 *            试题id
	 * @return
	 */
	public List<QuestionItem> findQuestionItemList(Integer questionId) {
		if (questionId == null) {
			return null;
		} else {
			String hql = " isDelete = 0 and question.id = ?1 ";
			return super.findAll(hql, questionId);
		}
	}

	/**
	 * 
	 * 功能：根据试卷id删除试卷的所有选项
	 * 
	 * @param questionnareId
	 */
	public void deleteByQuestionnaireId(Integer questionnareId) {
		String sql = "delete from tb_question_item where question_id in (select id from tb_question where info_id = ?1)";
		entityManager.createNativeQuery(sql).setParameter(1, questionnareId).executeUpdate();
	}
}
