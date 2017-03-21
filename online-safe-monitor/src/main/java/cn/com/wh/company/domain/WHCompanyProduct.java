package cn.com.wh.company.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 吴胜光
 */

@Entity
@Table(name="tb_wuhai_company_product")
public class WHCompanyProduct implements java.io.Serializable {

	private static final long serialVersionUID = 4705025293958385486L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer vid;//id
	
	@Column(name="group_id")
	private Integer groupId;//部门id
	
	@Column(name="material_type")
	private Integer materialType;//材料种类id
	
	@Column(name="material_type_name")
	private String materialTypeName;//材料种类名称
	
	@Column(name="product_id")
	private Integer productId;//物质标示
	
	@Column(name="material_name")
	private String materialName;//物质名称
	
	@Column(name="output_year")
	private Integer outputYear;//年设计产量
	
	@Column(name="output_mouth")
	private Integer outputMouth;//月设计产量
	
	@Column(name="use_year")
	private Integer useYear;//年消耗量
	
	@Column(name="use_mouth")
	private Integer useMouth;//月消耗量
	
	
	/**
	 * 备注
	 */
	@Column(name="memo")
	private String memo;
	
	/**
	 * 数据权限
	 */
	@Column(name="is_fork_group")
	private Integer isForkGroup;
	
	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	private Timestamp createTime;
	
	@Column(name="is_delete")
	private Integer isDelete;//是否删除

	// Constructors

	/** default constructor */
	public WHCompanyProduct() {
	}

	/** full constructor */
	

	
	public WHCompanyProduct(Integer vid, Integer groupId, Integer materialType,
			String materialTypeName, Integer productId, String materialName,
			Integer outputYear, Integer outputMouth, Integer useYear,
			Integer useMouth, String memo, Integer isForkGroup,
			Timestamp createTime, Integer isDelete) {
		super();
		this.vid = vid;
		this.groupId = groupId;
		this.materialType = materialType;
		this.materialTypeName = materialTypeName;
		this.productId = productId;
		this.materialName = materialName;
		this.outputYear = outputYear;
		this.outputMouth = outputMouth;
		this.useYear = useYear;
		this.useMouth = useMouth;
		this.memo = memo;
		this.isForkGroup = isForkGroup;
		this.createTime = createTime;
		this.isDelete = isDelete;
	}


	// Property accessors


	public Integer getVid() {
		return vid;
	}

	public void setVid(Integer vid) {
		this.vid = vid;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getMaterialType() {
		return materialType;
	}

	public void setMaterialType(Integer materialType) {
		this.materialType = materialType;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getOutputYear() {
		return outputYear;
	}

	public void setOutputYear(Integer outputYear) {
		this.outputYear = outputYear;
	}

	public Integer getOutputMouth() {
		return outputMouth;
	}

	public void setOutputMouth(Integer outputMouth) {
		this.outputMouth = outputMouth;
	}

	public Integer getUseYear() {
		return useYear;
	}

	public void setUseYear(Integer useYear) {
		this.useYear = useYear;
	}

	public Integer getUseMouth() {
		return useMouth;
	}

	public void setUseMouth(Integer useMouth) {
		this.useMouth = useMouth;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getIsForkGroup() {
		return isForkGroup;
	}

	public void setIsForkGroup(Integer isForkGroup) {
		this.isForkGroup = isForkGroup;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialTypeName() {
		return materialTypeName;
	}

	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}




	
}