package cn.com.wh.exam.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.qytx.platform.base.domain.BaseDomain;
import cn.com.qytx.platform.base.domain.DeleteState;

/**
 * 
 * 功能:题目选项情况
 * 版本: 1.0
 * 开发人员: wangy
 * 创建日期: 2015年8月19日
 * 修改日期: 2015年8月19日
 * 修改列表:
 */
@Entity
@Table(name = "tb_wuhai_question_item")
public class ExamQuestionItem implements java.io.Serializable {


	// Fields

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VID",unique=true,nullable=false)
	private Integer id;
	
	/**
	 * 选项号
	 */
	@Column(name="Item_Code",length=100)
	private String itemCode;
	
	/**
	 * 选项
	 */
	@Column(name="Item_Name",length=100)
	private String itemName;
	
	/**
	 * 试题ID
	 */
	@Column(name="Question_ID")
	private Integer questionId;
	
	/**
	 * 删除状态
	 */
	@DeleteState
	@Column(name="is_Delete")
	private Integer isDelete = 0;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}



}
