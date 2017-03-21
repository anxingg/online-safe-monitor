package cn.com.wh.fee.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.com.qytx.platform.base.domain.DeleteState;


/**
 * 
 * <br/>功能:安全生产费用使用
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2015年10月8日
 * <br/>修改日期: 2015年10月8日
 * <br/>修改列表:
 */
@Entity
@Table(name = "tb_wuhai_fee_used")
public class FeeUsed implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8820303663344675864L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VID",unique=true,nullable=false)
	private Integer id;//主键
	
	/**
	 * 公司id（部门id）
	 */
	@Column(name="group_id")
	private Integer groupId;
	
	/**
	 * 公司名称
	 */
	@Column(name="group_name")
	private String groupName;
	
	/**
	 * 使用日期
	 */
	@Column(name="use_time")
	private String useTime;
	
	/**
	 * 资金用向
	 */
	@Column(name="use_direction")
	private Integer useDirection;
	
	/**
	 * 本次使用额
	 */
	@Column(name="use_money")
	private Double useMoney;
	
	/**
	 * 结存金额
	 */
	@Column(name="remaining_sum")
	private Double remainingSum;
	
	/**
	 * 备注
	 */
	@Column(name="memo")
	private String memo;
	
	/**
	 * 季度
	 */
	@Column(name="quarter")
	private Integer quarter;
	
	/**
	 * 年度
	 */
	@Column(name="year")
	private Integer year;
	
	/**
	 * 创建时间
	 */
	@Column(name="Create_Time")
	private Date createTime;
	
	/**
	 * 创建人id
	 */
	@Column(name="create_user_id")
	private Integer createUserId;
	
	/**
	 * 删除状态
	 */
	@DeleteState
	@Column(name="Is_Delete")
	private Integer isDelete = 0;
	
	/**
	 * 数据权限
	 */
	@Column(name="IS_FORK_GROUP")
	private Integer isForkGroup;
	
	/**
	 * 补正（0表示没有勾选补正， 1表示勾选了补正）
	 */
	@Transient
	private Integer plus;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	public Integer getUseDirection() {
		return useDirection;
	}

	public void setUseDirection(Integer useDirection) {
		this.useDirection = useDirection;
	}

	public Double getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(Double useMoney) {
		this.useMoney = useMoney;
	}

	public Double getRemainingSum() {
		return remainingSum;
	}

	public void setRemainingSum(Double remainingSum) {
		this.remainingSum = remainingSum;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getQuarter() {
		return quarter;
	}

	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsForkGroup() {
		return isForkGroup;
	}

	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}

	public Integer getPlus() {
		return plus;
	}

	public void setPlus(Integer plus) {
		this.plus = plus;
	}
	
}