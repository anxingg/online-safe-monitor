package cn.com.wh.exam.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.exam.domain.ExamQuestion;
import cn.com.wh.exam.domain.SearchVo;

@Component
public class ExamQuestionDao extends BaseDao<ExamQuestion, Integer>{
	
	/**
	 * 
	 * 功能：试题列表
	 * @param page
	 * @param search
	 * @return
	 */
	public Page<ExamQuestion> findByPage(Pageable page,SearchVo search) {
		StringBuffer condition=new StringBuffer(" isDelete=0");
	     if(search!=null){
	    		if(StringUtils.isNotEmpty(search.getTitle())){
	    			condition.append(" and title like '%"+search.getTitle()+"%'");
	    		}
	    	
	    		if (null!=search.getExamType()) {
	    			condition.append(" and titleType="+search.getExamType());
	    		}
	    		if (null!=search.getExamType()) {
	    			condition.append(" and state="+search.getState());
	    		}
	     }
		return super.findAll(condition.toString(), page);
	}
	
	/**
	 * 
	 * 功能：通过试题id获得试题列表（不加数据权限）
	 * @param questionIds
	 * @param search
	 * @return
	 */
	public List<ExamQuestion> findByQuestionIds(String questionIds) {
		StringBuffer condition=new StringBuffer("select ex from ExamQuestion ex where 1=1");
	
		if(StringUtils.isNotBlank(questionIds)){
			if (questionIds.startsWith(",")) {
				questionIds = questionIds.substring(1);
			}
			if (questionIds.endsWith(",")) {
				questionIds = questionIds.substring(0,questionIds.length()-1);
			}
			condition.append(" and id in ("+questionIds+")");
		}
	
		return super.find(condition.toString());
	}
	
	/**
	 * 
	 * 功能：是否有效 0 有效 1 失效
	 * @param questionId
	 * @param state
	 * @return
	 */
	public int updateQuestionState(Integer questionId,Integer state){
		return super.entityManager.createNativeQuery("update tb_wuhai_question set state="+state+" where vid="+questionId).executeUpdate();
	}
	
}
