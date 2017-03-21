package cn.com.qytx.workflow.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.qytx.platform.base.domain.BaseDomain;
import cn.com.qytx.platform.utils.spring.SpringUtil;
import cn.com.qytx.workflow.dao.HotNodeFormAttributeDao;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

/**工作流变量表
 * @author jiayongqiang
 *
 */
@Entity
@Table(name="tb_workflow_var")
public class HotWorkflowVar extends BaseDomain{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	//流程实例ID
	@Column(name="instance_id")
	private String instanceId;
	
	/**
	 * 保存流程处理人信息
	 */
	@Column(name="processers")
	private String processers;
	
	@Column(name="state")
	private String state;
	
	/**
	 * 最后一次操作的时间
	 */
	@Column(name="last_operation_time")
	private Timestamp lastOperationTime;
	
	@Column(name="process_Id")
	private int processId;
	
	@Column(name="process_list")
	private String processList;
	
	public String getStateShow(){
		final HotNodeFormAttributeDao dao = SpringUtil.getBean(HotNodeFormAttributeDao.class);
		final HotNodeFormAttribute hafa = dao.findByProcessIdAndName(processId, state);
		String result;
		if(hafa == null){
			if(state!=null && state.equals("end")){
				result = "已办结";
			}else{
				result = state;
			}
		}else{
			result = hafa.getState();
		}
		return result;
	}
	
	/**获取处理历史
	 * approveTime,nodeName,approverName,approverId,role,comment
	 * @return
	 */
	@SuppressWarnings("serial")
	public List<Map<String,String>> getProcessHistoryList(){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		if(this.processList!=null){
			final Gson gson = new Gson();
			list = gson.fromJson(this.processList, new TypeToken<List<Map<String,String>>>() {}.getType());
		}
		return list;
	}
	
	/**添加审批意见
	 * @param map
	 */
	public void addProcessHistory(final Map<String,String> map){
		List<Map<String,String>> list = getProcessHistoryList();
		list.add(map);
		final Gson gson = new Gson();
		this.setProcessList(gson.toJson(list));
	}

	public String getProcessList() {
		return processList;
	}

	public void setProcessList(final String processList) {
		this.processList = processList;
	}

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(final int processId) {
		this.processId = processId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(final String instanceId) {
		this.instanceId = instanceId;
	}

	public String getProcessers() {
		return processers;
	}

	public void setProcessers(final String processers) {
		this.processers = processers;
	}

	public String getState() {
		return state;
	}

	public void setState(final String state) {
		this.state = state;
	}

	public Timestamp getLastOperationTime() {
		return lastOperationTime;
	}

	public void setLastOperationTime(final Timestamp lastOperationTime) {
		this.lastOperationTime = lastOperationTime;
	}

	
}
