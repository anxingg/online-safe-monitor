package cn.com.qytx.workflow.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;

import cn.com.qytx.platform.base.domain.BaseDomain;
import cn.com.qytx.platform.utils.spring.SpringUtil;
import cn.com.qytx.workflow.constans.WorkflowConstans;

import com.google.gson.annotations.Expose;

/**
 * 功能：流程定义模型
 * 版本: 1.0
 * 开发人员：贾永强
 * 创建日期: 2013-3-21 下午5:23:41 
 * 修改日期：2013-3-21 下午5:23:41 
 * 修改列表：
 */
@Entity
@Table(name="tb_cbb_process_attribute")
public class HotProcessAttribute extends BaseDomain implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1870432543014119438L;
	//未发布
	public static final Integer NOT_DEPLOY = 1;
	//已发布
	public static final Integer DEPLOY_STATE = 2;
	//停用
	public static final Integer STOP_STATE = 3;

	/**
	 * 
	 */
	public HotProcessAttribute() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param processName
	 */
	public HotProcessAttribute(final String processName, final Integer id,
			final Integer categoryId,final String processDefineId) {
		super();
		this.processName = processName;
		this.id = id;
		this.categoryId = categoryId;
		this.processDefineId = processDefineId;
	}
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="process_attribute_id")
	private Integer id;
	//类别
	@Column(name="category_id")
	private Integer categoryId;
	
	
	
	@Column(name="create_time")
	private Date createDate;
	
	@Column(name="update_time")
	private Date updateDate;
	
	//表单Id
	@Column(name="form_id")
	private Integer formId;
	
	//部门ID
	@Column(name="dept")
	private Integer dept;
	
	//工作流名称
	@Expose
	@Column(name="process_name",length=50)
	private String processName;
	
	//工作流定义ID
	@Column(name="process_define_id")
	private String processDefineId;
	
	//工作流顺序
	@Column(name="process_order")
	private Integer processOrder;
	
	//是否允许附件;1允许；0拒绝
	@Column(name="is_attach")
	private Integer isAttach;
	
	//工作流描述
	@Column(name="directions")
	private String directions;
	
	//工作流定义的状态
//	1，已定义 ，未发布
//	2，已发布，并启用
//	3，停用
	@Column(name="process_state")
	private Integer processState = 1;
	
	public String getProcessStateCN(){
		
		if(this.processState == null){
			this.processState = 1;
		}
		
		String result;
		if(this.processState == 1){
			result = "未发布";
		}else if(this.processState == 2){
			result = "已发布";
		}else if(this.processState == 3){
			result = "停用";
		}else{
			result = "未知状态";
		}
		return result;
	}
	
	//工作流定义的JSON格式
	@Column(name="process_define_byjson",length=8000)
	private String procsssDefinByJSON;
	
	//工作流定义的XML格式
	@Column(name="process_define_byxml",length=8000)
	private String processDefineByXML;
	
	@OneToMany(mappedBy="processAttribute",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderBy("nodeOrder asc")
	private Set<HotNodeFormAttribute> nodeSet;
	
	//文号表达式
	@Column(name="process_name_expr")
	private String processNameExpr ;
	
	//文号开始序号
	@Column(name="process_name_beginnum")
	private Integer processNameBeginNum = 1;
	
	//文号序号长度
	@Column(name="process_name_num_length")
	private Integer processNamLength = 4;
	
	//文号是否能修改:0否；1能
	@Column(name="process_name_can_update")
	private Integer processNameCanupdate;
	
	//选择审批人的方式，1代表直接选人；2代表通过部门选人,默认是1
	@Column(name="selectusermode")
	private Integer selectUserMode = 1;
	
	/**
	 * 功能：获取当前在用实例数量
	 * @param
	 * @return
	 * @throws   
	 */
	public int getInstanceNum(){
		int result = 0;
		if(this.processDefineId!=null && !this.processDefineId.equals("")){
			final ProcessEngine engine = (ProcessEngine) SpringUtil.getBean("processEngine");
			result = (int) engine.getExecutionService().createProcessInstanceQuery().processDefinitionId(processDefineId).count();
		}
		return result;
	}
	
	/**
	 * 功能：获取流程开始节点
	 * @param
	 * @return
	 * @throws   
	 */
	public HotNodeFormAttribute getStartNode(){
		HotNodeFormAttribute temp = null;
		if(this.nodeSet!=null && this.nodeSet.size()>0){
			for(final Iterator<HotNodeFormAttribute> iterator = this.nodeSet.iterator();iterator.hasNext();){
				final HotNodeFormAttribute nfa = iterator.next();
				if(nfa.getNodeType().equals(WorkflowConstans.NODE_TYPE_START)){
					temp = nfa;
					break;
				}
			}
		}
		return temp;
	}
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(final Integer categoryId) {
		this.categoryId = categoryId;
		
	}
	public Integer getFormId() {
		return formId;
	}
	public void setFormId(final Integer formId) {
		this.formId = formId;
	}
	public Integer getDept() {
		return dept;
	}
	public void setDept(final Integer dept) {
		this.dept = dept;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(final String processName) {
		this.processName = processName;
	}
	public String getProcessDefineId() {
		return processDefineId;
	}
	public void setProcessDefineId(final String processDefineId) {
		this.processDefineId = processDefineId;
	}
	public Integer getProcessOrder() {
		return processOrder;
	}
	public void setProcessOrder(final Integer processOrder) {
		this.processOrder = processOrder;
	}
	public String getDirections() {
		return directions;
	}
	public void setDirections(final String directions) {
		this.directions = directions;
	}
	public Integer getProcessState() {
		return processState;
	}
	public void setProcessState(final Integer processState) {
		this.processState = processState;
	}
	public String getProcsssDefinByJSON() {
		return procsssDefinByJSON;
	}
	public void setProcsssDefinByJSON(final String procsssDefinByJSON) {
		this.procsssDefinByJSON = procsssDefinByJSON;
	}
	public String getProcessDefineByXML() {
		return processDefineByXML;
	}
	public void setProcessDefineByXML(final String processDefineByXML) {
		this.processDefineByXML = processDefineByXML;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(final Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(final Date updateDate) {
		this.updateDate = updateDate;
	}
	public Set<HotNodeFormAttribute> getNodeSet() {
		return nodeSet;
	}
	public void setNodeSet(final Set<HotNodeFormAttribute> nodeSet) {
		this.nodeSet = nodeSet;
	}
	public Integer getIsAttach() {
		return isAttach;
	}
	public void setIsAttach(final Integer isAttach) {
		this.isAttach = isAttach;
	}
	

	//流程是否使用:false未使用。true使用
	public boolean isUse(){
		final ProcessEngine engine = (ProcessEngine) SpringUtil.getBean("processEngine");
		final ExecutionService executionService = engine.getExecutionService();
		boolean result = false;
		if(processDefineId==null){
			result = false;
		}else{
			final List<ProcessInstance> instanceslist = executionService.createProcessInstanceQuery().processDefinitionId(processDefineId).list();
			if(instanceslist!=null && !instanceslist.isEmpty() ){
				result = true;
			}
		}
		return result;
	}
	
	
	public String getProcessNameExpr() {
		return processNameExpr;
	}

	public void setProcessNameExpr(final String processNameExpr) {
		this.processNameExpr = processNameExpr;
	}

	public Integer getProcessNameBeginNum() {
		return processNameBeginNum;
	}

	public void setProcessNameBeginNum(final Integer processNameBeginNum) {
		this.processNameBeginNum = processNameBeginNum;
	}

	public Integer getProcessNamLength() {
		return processNamLength;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setProcessNamLength(final Integer processNamLength) {
		this.processNamLength = processNamLength;
	}

	public Integer getProcessNameCanupdate() {
		return processNameCanupdate;
	}

	public void setProcessNameCanupdate(final Integer processNameCanupdate) {
		this.processNameCanupdate = processNameCanupdate;
	}
	
	public Integer getSelectUserMode() {
		return selectUserMode;
	}

	public void setSelectUserMode(final Integer selectUserMode) {
		this.selectUserMode = selectUserMode;
	}


	@Override
	public String toString() {
		return "{\"id\":\""+this.getId()+"\",\"name\":\""+this.getProcessName()+"\"}";
	}
	
}
