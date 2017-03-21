package cn.com.wh.exam.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.wh.exam.dao.ExamQuestionItemDao;
import cn.com.wh.exam.domain.ExamQuestionItem;
import cn.com.wh.exam.service.ExamQuestionItemService;

@Service
@Transactional
public class ExamQuestionItemImpl extends BaseServiceImpl<ExamQuestionItem> implements ExamQuestionItemService{

	@Autowired
	private ExamQuestionItemDao examQuestionItemDao;
	
	@Override
	public int deleteManyItemByQuestionId(Integer questionId) {
		// TODO Auto-generated method stub
		return examQuestionItemDao.deleteManyItemByQuestionId(questionId);
	}
	
	

	@Override
	public List<ExamQuestionItem> findByQuestionId(Integer questionId) {
		// TODO Auto-generated method stub
		return examQuestionItemDao.findByQuestionId(questionId);
	}
	/**
	 * 通过试题ids获得试题选项map
	 * @param questionIds
	 * @return
	 */
	public Map<Integer, List<ExamQuestionItem>> findItemsByQuestionIds(String questionIds){
		Map<Integer, List<ExamQuestionItem>> map = new HashMap<Integer, List<ExamQuestionItem>>();
		List<ExamQuestionItem> list = examQuestionItemDao.findItemsByQuestionIds(questionIds);
		if (list!=null&&list.size()>0) {
			for (ExamQuestionItem item : list) {
				if (map.get(item.getQuestionId())==null) {
					List<ExamQuestionItem> itemList = new ArrayList<ExamQuestionItem>();
					itemList.add(item);
					map.put(item.getQuestionId(), itemList);
				}else {
					List<ExamQuestionItem> itemList = map.get(item.getQuestionId());
					itemList.add(item);
					map.put(item.getQuestionId(), itemList);
				}
			}
		}
		return map;
	}



	@Override
	public int updateManyItemByQuestionId(Integer questionId) {
		// TODO Auto-generated method stub
		return examQuestionItemDao.updateManyItemByQuestionId(questionId);
	}

}
