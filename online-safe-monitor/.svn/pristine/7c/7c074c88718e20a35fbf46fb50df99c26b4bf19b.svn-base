package cn.com.wh.exam.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.wh.exam.domain.ExamTest;
import cn.com.wh.exam.domain.SearchVo;

@Component
public class ExamTestDao extends BaseDao<ExamTest, Integer>{

	/**
	 * 
	 * 功能：考试列表
	 * @param page
	 * @param search
	 * @return
	 */
	public Page<ExamTest> getExamTestList(Pageable page,SearchVo search){
		StringBuffer condition=new StringBuffer(" 1=1");
		if(StringUtils.isNotEmpty(search.getTitle())){
			condition.append(" and testName like '%"+search.getTitle()+"%'");
		}
	
		if (null!=search.getExamType()) {
			condition.append(" and testType="+search.getExamType());
		}
		if (search.getState()!=null) {
			condition.append(" and state="+search.getState());
		}
		return unDeleted().findAll(condition.toString(), page);
	}
	
	/**
	 * 
	 * 功能：是否有效 0 有效 1 失效
	 * @param questionId
	 * @param state
	 * @return
	 */
	public int updateTestState(Integer examTestId,Integer state){
		return super.entityManager.createNativeQuery("update tb_wuhai_test set state="+state+" where vid="+examTestId).executeUpdate();
	}
	
	
}
