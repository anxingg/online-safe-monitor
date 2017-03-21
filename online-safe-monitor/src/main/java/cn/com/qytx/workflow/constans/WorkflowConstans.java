package cn.com.qytx.workflow.constans;


/**
 * 功能：JPDL定义的接口
 * 版本: 1.0
 * 开发人员：贾永强
 * 创建日期: 2013-3-22 上午11:37:10 
 * 修改日期：2013-3-22 上午11:37:10 
 * 修改列表：
 */
public interface WorkflowConstans {

	//开始任务节点
	String NODE_TYPE_START 		= "start";
	//判断节点
	String NODE_TYPE_DECISON 	= "decision";
	//分支节点
	String NODE_TYPE_FORK		= "fork";
	//回合节点
	String NODE_TYPE_JOIN		= "join";
	//会签节点
	String NODE_TYPE_MULTISIGN	= "mutilSign";
	//任务节点
	String NODE_TYPE_TASK		= "task";
	//结束节点
	String NODE_TYPE_END		= "end";
	
	//回退信息
	String ROLL_BACK_INFO = "rollbackinfo";


    String CREATER = "creater";

    String CANDIDATE_USERS="candidate_persons";



    FormCategoryEnum WORKFLOW_TYPE = FormCategoryEnum.WORKFLOW;
    FormCategoryEnum FORM_TYPE = FormCategoryEnum.FORM;
    FormCategoryEnum GONG_WEN_TYPE = FormCategoryEnum.GONG_WEN;

    CandidateEnum[] candidate_list = {CandidateEnum.GROUP, CandidateEnum.ROLE, CandidateEnum.USER};

}
