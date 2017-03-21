package cn.com.qytx.hotline.projectquestion.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.hotline.projectquestion.domain.ProjectQuestion;
import cn.com.qytx.hotline.projectquestion.service.IProjectQuestion;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;

/**
 * 功能:问题上报实现类
 * 版本: 1.0
 * 开发人员: 张东领
 * 创建日期: 2014-12-20
 * 修改日期: 2014-12-20
 * 修改列表:
 */
@Service
@Transactional
public class ProjectQuestionImpl extends BaseServiceImpl<ProjectQuestion> implements IProjectQuestion,Serializable {
	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = -5245525418344448069L;
	

}
