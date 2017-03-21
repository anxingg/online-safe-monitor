package cn.com.wh.exam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.wh.exam.dao.ExamUserTestLogDao;
import cn.com.wh.exam.domain.ExamUserTestLog;
import cn.com.wh.exam.service.IExamUserTestLog;


@Service
@Transactional
public class ExamUserTestLogService extends BaseServiceImpl<ExamUserTestLog> implements IExamUserTestLog{

	@Autowired
	private ExamUserTestLogDao examUserTestLogDao;
	@Override
	public List<ExamUserTestLog> getExamUserTestLogList(Integer userTestId) {
		// TODO Auto-generated method stub
		return examUserTestLogDao.getExamUserTestLogList(userTestId);
	}

}
