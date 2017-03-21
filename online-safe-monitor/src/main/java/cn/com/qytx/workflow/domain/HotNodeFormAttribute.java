package cn.com.qytx.workflow.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.com.qytx.platform.base.domain.BaseDomain;

/**
 * 功能：流程节点模型
 * 版本: 1.0
 * 开发人员：贾永强
 * 创建日期: 2013-3-21 下午5:23:17 
 * 修改日期：2013-3-21 下午5:23:17 
 * 修改列表：
 */
@Entity
@Table(name="tb_cbb_node_form_attribute")
public class HotNodeFormAttribute extends BaseDomain{

	//流程定义对象
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	@JoinColumn(name="process_attribute_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private HotProcessAttribute processAttribute;
	//节点名称
	@Column(name="node_name")
	private String nodeName;
	//可写字段
	@Column(name="writeable_properties")
	private String writeableProperties;
	//加密字段
	@Column(name="secret_properties")
	private String secretProperties;
	//候选人
	@Column(name="candidate")
	private String candidate;
	//节点类型
	@Column(name="node_type")
	private String nodeType;
	//候选角色
    @Column(name="roles")
	private String roles;
	//候选部门
    @Column(name="depts")
	private String depts;
	//转入条件
    @Column(name="decison_into")
	private String decisonInto;
	//转出条件
    @Column(name="decison_out")
	private String decisonOut;
    @Column(name="create_time")
	private Date createDate;
    @Column(name="update_time")
	private Date updateDate;
    @Column(name="descri")
	private String descri;
    @Column(name="node_order")
	private Integer nodeOrder;
    @Column(name="el")
	private String el;
	//是否只有本部门可接受任务:0全公司；1：本部门，和任务发起人同一个部门
    @Column(name="is_mydep_can_accept")
	private Integer isMydepCanAccept = 0;

    //操作 类型  查看、回访、。。。
    @Column(name="operate_name")
    private String operateName;
    //操作url   流程定义节点定义好的 form表单url
    @Column(name="operate_url")
    private String operateUrl;
    @Column(name="node_state")
    private String state;

	public String getState() {
		return state;
	}

	public void setState(final String state) {
		this.state = state;
	}

	public Integer getIsMydepCanAccept() {
		return isMydepCanAccept;
	}

	public void setIsMydepCanAccept(final Integer isMydepCanAccept) {
		this.isMydepCanAccept = isMydepCanAccept;
	}

	public String getEl() {
		return el;
	}

	public void setEl(final String el) {
		this.el = el;
	}

	public Integer getNodeOrder() {
		return nodeOrder;
	}

	public void setNodeOrder(final Integer nodeOrder) {
		this.nodeOrder = nodeOrder;
	}

	public HotProcessAttribute getProcessAttribute() {
		return processAttribute;
	}

	public void setProcessAttribute(final HotProcessAttribute processAttribute) {
		this.processAttribute = processAttribute;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(final String nodeName) {
		this.nodeName = nodeName;
	}

	public String getWriteableProperties() {
		return writeableProperties;
	}

	public void setWriteableProperties(final String writeableProperties) {
		this.writeableProperties = writeableProperties;
	}

	public String getSecretProperties() {
		return secretProperties;
	}

	public void setSecretProperties(final String secretProperties) {
		this.secretProperties = secretProperties;
	}

	public String getCandidate() {
		return candidate;
	}

	public void setCandidate(final String candidate) {
		this.candidate = candidate;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(final String nodeType) {
		this.nodeType = nodeType;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(final String roles) {
		this.roles = roles;
	}

	public String getDepts() {
		return depts;
	}

	public void setDepts(final String depts) {
		this.depts = depts;
	}

	public String getDecisonInto() {
		return decisonInto;
	}

	public void setDecisonInto(final String decisonInto) {
		this.decisonInto = decisonInto;
	}

	public String getDecisonOut() {
		return decisonOut;
	}

	public void setDecisonOut(final String decisonOut) {
		this.decisonOut = decisonOut;
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

	public String getDescri() {
		return descri;
	}

	public void setDescri(final String descri) {
		this.descri = descri;
	}
	
	/**
	 * 功能：获取候选人ID列表
	 * @param
	 * @return
	 * @throws   
	 */
	public List<Integer> getUserList(){
		return parseStrsToList(this.getCandidate());
	}
	
	/**
	 * 功能：获取候选部门ID列表
	 * @param
	 * @return
	 * @throws   
	 */
	public List<Integer> getGroupList(){
		return parseStrsToList(this.getDepts());
	}
	
	/**
	 * 功能：获取候选角色列表
	 * @param
	 * @return
	 * @throws   
	 */
	public List<Integer> getRoleList(){
		return parseStrsToList(this.getRoles());
	}
	
	/**
	 * 功能：把x,x,格式的字符串格式化为Integer数组
	 * @param
	 * @return
	 * @throws   
	 */
	private List<Integer> parseStrsToList(final String str){
		List<Integer> result = new ArrayList<Integer>();
		if(str!=null && !str.equals("")){
			final String[] strs = str.split(",");
			for(int i=0;i<strs.length ;i++){
				if(strs[i]!=null){
					result.add(Integer.parseInt(strs[i]));
				}
			}
		}
		return result;
	}

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(final String operateName) {
        this.operateName = operateName;
    }

    public String getOperateUrl() {
        return operateUrl;
    }

    public void setOperateUrl(final String operateUrl) {
        this.operateUrl = operateUrl;
    }

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}
    
}
