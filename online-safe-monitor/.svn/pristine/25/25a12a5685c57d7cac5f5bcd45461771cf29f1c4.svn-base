package cn.com.qytx.workflow.domain;
// default package

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.com.qytx.platform.base.domain.BaseDomain;

import com.google.gson.annotations.Expose;

/**
 * TdFormCategory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tb_form_category")
public class HotFormCategory extends BaseDomain{
	// Fields

	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="category_id")
	private Integer categoryId;//分类id
	@Expose
	@Column(name="category_name")
	private String categoryName;//分类名称

	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	private Timestamp createTime;

	/**
	 * 最后修改时间
	 */
	@Column(name="last_update_time")
	private Timestamp lastUpdateTime;
	/**
	 * 得到创建时间
	 * @return 创建时间
	 */
	@Column(name="type")
	private Integer type;//分类的类型  1，表单分类 2，流程分类
	@Column(name="orderindex")
	private Integer orderIndex;//排序号
    @Transient
    private List<HotProcessAttribute> processAttributeList;


	public Timestamp getCreateTime() {
		return createTime;
	}
	/**
	 * 设置创建时间
	 * @param createTime
	 */
	public void setCreateTime(final Timestamp createTime) {
		this.createTime = createTime;
	}
	/**
	 * 得到最后修改时间
	 * @return 最后修改时间
	 */
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	/**
	 * 设置最后修改时间
	 * @param lastUpdateTime
	 */
	public void setLastUpdateTime(final Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	// Property accessors
	/**
	 * 得到分类的id
	 * @return
	 */
	public Integer getCategoryId() {
		return this.categoryId;
	}
	/**
	 * 设置分类的id
	 * @param categoryId
	 */
	public void setCategoryId(final Integer categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * 得到分类名称
	 * @return
	 */
	public String getCategoryName() {
		return this.categoryName;
	}
	/**
	 * 设置分类名称
	 * @param categoryName
	 */
	public void setCategoryName(final String categoryName) {
		this.categoryName = categoryName;
	}


	
	/**
	 * 得到分类的类别      1，表单分类 2，流程分类
	 * @return
	 */
	public Integer getType() {
		return this.type;
	}
	/**
	 * 设置分类的类别    1，表单分类 2，流程分类
	 * @param type
	 */
	public void setType(final Integer type) {
		this.type = type;
	}
	/**
	 * 得到排序号
	 * @return orderIndex
	 */
	public Integer getOrderIndex() {
		return orderIndex;
	}
	/**
	 * 设置排序号
	 * @param orderIndex
	 */
	public void setOrderIndex(final Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

    public List<HotProcessAttribute> getProcessAttributeList() {
        return processAttributeList;
    }

    public void setProcessAttributeList(final List<HotProcessAttribute> processAttributeList) {
        this.processAttributeList = processAttributeList;
    }

    /**
     * 给分类下添加流程定义列表
     * @param attributes
     */
    public void addProcessAttributeList(final List<HotProcessAttribute> attributes){
        if(processAttributeList==null){
            processAttributeList = new ArrayList<HotProcessAttribute>();
        }

        for(int i=0;i<attributes.size();i++){
            final HotProcessAttribute hpab = attributes.get(i);
            if(hpab.getProcessName()==null || hpab.getProcessName().equals("")){
                continue;
            }
            if(hpab.getCategoryId()!=null && (hpab.getCategoryId().intValue() == getCategoryId().intValue())){
                this.processAttributeList.add(hpab);
            }
        }

    }
}