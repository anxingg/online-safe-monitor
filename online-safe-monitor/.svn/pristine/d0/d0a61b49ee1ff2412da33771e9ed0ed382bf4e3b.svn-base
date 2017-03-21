package cn.com.wh.fee.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.com.qytx.platform.base.domain.DeleteState;


/**
 * 
 * <br/>功能:安全生产费用提取
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2015年10月8日
 * <br/>修改日期: 2015年10月8日
 * <br/>修改列表:
 */
@Entity
@Table(name = "tb_wuhai_fee_extract")
public class FeeExtract implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4063060100639528274L;

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
	 * 提取标准
	 */
	@Column(name="extract_stand")
	private String extractStand;
	
	/**
	 * 上年度营业额
	 */
	@Column(name="last_year_turnover")
	private Double turnover;
	
	/**
	 * 提取时间
	 */
	@Column(name="extract_time")
	private String extractTime;
	
	/**
	 * 本次提取额
	 */
	@Column(name="extract_money")
	private Double extractMoney;
	
	/**
	 * 结存金额
	 */
	@Column(name="remaining_sum")
	private Double remainingSum;
	
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

	public String getExtractStand() {
		return extractStand;
	}

	public void setExtractStand(String extractStand) {
		this.extractStand = extractStand;
	}

	public Double getTurnover() {
		return turnover;
	}

	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}

	public String getExtractTime() {
		return extractTime;
	}

	public void setExtractTime(String extractTime) {
		this.extractTime = extractTime;
	}

	public Double getExtractMoney() {
		return extractMoney;
	}

	public void setExtractMoney(Double extractMoney) {
		this.extractMoney = extractMoney;
	}

	public Double getRemainingSum() {
		return remainingSum;
	}

	public void setRemainingSum(Double remainingSum) {
		this.remainingSum = remainingSum;
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

	
}