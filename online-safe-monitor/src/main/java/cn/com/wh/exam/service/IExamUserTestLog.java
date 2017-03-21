package cn.com.wh.exam.service;

import java.util.List;

import cn.com.qytx.platform.base.service.BaseService;
import cn.com.wh.exam.domain.ExamUserTestLog;

public interface IExamUserTestLog extends BaseService<ExamUserTestLog>{

	public List<ExamUserTestLog> getExamUserTestLogList(Integer userTestId);
}
