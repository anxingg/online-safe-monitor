package cn.com.wh.thresholdtemplate.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import cn.com.qytx.platform.base.domain.DeleteState;

/**
 * 阈值模板实体类
 * @author 吴胜光
 * 创建时间： 2017年04月03日
 * 
 */
@Entity
@Table(name="WARNINGSETTINGTEMPLATE")
public class ThresholdTemplate implements java.io.Serializable {

	private static final long serialVersionUID = 4705025193958385486L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer vid;//id
	
	@Column(name="TEMPLATENAME")
	private Integer templateName;//模板名称
	
	@Column(name="WATCHTYPE")
	private String watchType;//监测类型
	
	@Column(name="RANGELOW")
	private Integer rangeLow;//适用量程低
	
	@Column(name="RangeHigh")
	private Integer rangeHigh;//适用量程高
	
	@Column(name="Unit")
	private String unit;//量程单位
	
	@Column(name="EnableWarning")
	private Integer enableWarning;//预警启用
	
	@Column(name="Leve1Low")
	private Integer leve1Low;//预警1级低
	
	@Column(name="Level1High")
	private Integer level1High;//预警1级高
	
	@Column(name="LEVEL1Type")
	private Integer level1Type;//预警1级类型 1（一般），2（严重），3（紧急）
	
	@Column(name="Leve2Low")
	private Integer leve2Low;//预警1级低
	
	@Column(name="Level2High")
	private Integer level2High;//预警1级高
	
	@Column(name="LEVEL2Type")
	private Integer level2Type;//预警1级类型 1（一般），2（严重），3（紧急）
	
	
	@Column(name="Leve3Low")
	private Integer leve3Low;//预警1级低
	
	@Column(name="Level3High")
	private Integer level3High;//预警1级高
	
	@Column(name="LEVEL3Type")
	private Integer level3Type;//预警1级类型 1（一般），2（严重），3（紧急）
	
	
	@Column(name="Leve4Low")
	private Integer leve4Low;//预警1级低
	
	@Column(name="Level4High")
	private Integer level4High;//预警1级高
	
	@Column(name="LEVEL4Type")
	private Integer level4Type;//预警1级类型 1（一般），2（严重），3（紧急）
	
	
	@Column(name="Leve5Low")
	private Integer leve5Low;//预警1级低
	
	@Column(name="Level5High")
	private Integer level5High;//预警1级高
	
	@Column(name="LEVEL5Type")
	private Integer level5Type;//预警1级类型 1（一般），2（严重），3（紧急）
	
	
	@Column(name="Leve6Low")
	private Integer leve6Low;//预警1级低
	
	@Column(name="Level6High")
	private Integer level6High;//预警1级高
	
	@Column(name="LEVEL6Type")
	private Integer level6Type;//预警1级类型 1（一般），2（严重），3（紧急）
	
	
	@Column(name="Leve7Low")
	private Integer leve7Low;//预警1级低
	
	@Column(name="Level7High")
	private Integer level7High;//预警1级高
	
	@Column(name="LEVEL7Type")
	private Integer level7Type;//预警1级类型 1（一般），2（严重），3（紧急）
	
	
	@Column(name="Leve8Low")
	private Integer leve8Low;//预警1级低
	
	@Column(name="Level8High")
	private Integer level8High;//预警1级高
	
	@Column(name="LEVEL8Type")
	private Integer level8Type;//预警1级类型 1（一般），2（严重），3（紧急）
	
	
	@Column(name="Leve9Low")
	private Integer leve9Low;//预警1级低
	
	@Column(name="Level9High")
	private Integer level9High;//预警1级高
	
	@Column(name="LEVEL9Type")
	private Integer level9Type;//预警1级类型 1（一般），2（严重），3（紧急）
	
	
	@Column(name="Leve10Low")
	private Integer leve10Low;//预警1级低
	
	@Column(name="Level10High")
	private Integer level10High;//预警1级高
	
	@Column(name="LEVEL10Type")
	private Integer level10Type;//预警1级类型 1（一般），2（严重），3（紧急）
	
	
	@Column(name="create_time")
	private Timestamp createTime;//创建时间
	
	@DeleteState
	@Column(name="is_delete")
	private Integer isDelete;//是否删除
	
	

	/** default constructor */
	public ThresholdTemplate() {
	}



	public Integer getVid() {
		return vid;
	}



	public void setVid(Integer vid) {
		this.vid = vid;
	}



	public Integer getTemplateName() {
		return templateName;
	}



	public void setTemplateName(Integer templateName) {
		this.templateName = templateName;
	}



	public String getWatchType() {
		return watchType;
	}



	public void setWatchType(String watchType) {
		this.watchType = watchType;
	}



	public Integer getRangeLow() {
		return rangeLow;
	}



	public void setRangeLow(Integer rangeLow) {
		this.rangeLow = rangeLow;
	}



	public Integer getRangeHigh() {
		return rangeHigh;
	}



	public void setRangeHigh(Integer rangeHigh) {
		this.rangeHigh = rangeHigh;
	}



	public String getUnit() {
		return unit;
	}



	public void setUnit(String unit) {
		this.unit = unit;
	}



	public Integer getEnableWarning() {
		return enableWarning;
	}



	public void setEnableWarning(Integer enableWarning) {
		this.enableWarning = enableWarning;
	}



	public Integer getLeve1Low() {
		return leve1Low;
	}



	public void setLeve1Low(Integer leve1Low) {
		this.leve1Low = leve1Low;
	}



	public Integer getLevel1High() {
		return level1High;
	}



	public void setLevel1High(Integer level1High) {
		this.level1High = level1High;
	}



	public Integer getLeve2Low() {
		return leve2Low;
	}



	public void setLeve2Low(Integer leve2Low) {
		this.leve2Low = leve2Low;
	}



	public Integer getLevel2High() {
		return level2High;
	}



	public void setLevel2High(Integer level2High) {
		this.level2High = level2High;
	}



	public Integer getLeve3Low() {
		return leve3Low;
	}



	public void setLeve3Low(Integer leve3Low) {
		this.leve3Low = leve3Low;
	}



	public Integer getLevel3High() {
		return level3High;
	}



	public void setLevel3High(Integer level3High) {
		this.level3High = level3High;
	}



	public Integer getLeve4Low() {
		return leve4Low;
	}



	public void setLeve4Low(Integer leve4Low) {
		this.leve4Low = leve4Low;
	}



	public Integer getLevel4High() {
		return level4High;
	}



	public void setLevel4High(Integer level4High) {
		this.level4High = level4High;
	}



	public Integer getLeve5Low() {
		return leve5Low;
	}



	public void setLeve5Low(Integer leve5Low) {
		this.leve5Low = leve5Low;
	}



	public Integer getLevel5High() {
		return level5High;
	}



	public void setLevel5High(Integer level5High) {
		this.level5High = level5High;
	}



	public Integer getLeve6Low() {
		return leve6Low;
	}



	public void setLeve6Low(Integer leve6Low) {
		this.leve6Low = leve6Low;
	}



	public Integer getLevel6High() {
		return level6High;
	}



	public void setLevel6High(Integer level6High) {
		this.level6High = level6High;
	}



	public Integer getLeve7Low() {
		return leve7Low;
	}



	public void setLeve7Low(Integer leve7Low) {
		this.leve7Low = leve7Low;
	}



	public Integer getLevel7High() {
		return level7High;
	}



	public void setLevel7High(Integer level7High) {
		this.level7High = level7High;
	}



	public Integer getLeve8Low() {
		return leve8Low;
	}



	public void setLeve8Low(Integer leve8Low) {
		this.leve8Low = leve8Low;
	}



	public Integer getLevel8High() {
		return level8High;
	}



	public void setLevel8High(Integer level8High) {
		this.level8High = level8High;
	}



	public Integer getLeve9Low() {
		return leve9Low;
	}



	public void setLeve9Low(Integer leve9Low) {
		this.leve9Low = leve9Low;
	}



	public Integer getLevel9High() {
		return level9High;
	}



	public void setLevel9High(Integer level9High) {
		this.level9High = level9High;
	}



	public Integer getLeve10Low() {
		return leve10Low;
	}



	public void setLeve10Low(Integer leve10Low) {
		this.leve10Low = leve10Low;
	}



	public Integer getLevel10High() {
		return level10High;
	}



	public void setLevel10High(Integer level10High) {
		this.level10High = level10High;
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



	public Integer getLevel1Type() {
		return level1Type;
	}



	public void setLevel1Type(Integer level1Type) {
		this.level1Type = level1Type;
	}



	public Integer getLevel2Type() {
		return level2Type;
	}



	public void setLevel2Type(Integer level2Type) {
		this.level2Type = level2Type;
	}



	public Integer getLevel3Type() {
		return level3Type;
	}



	public void setLevel3Type(Integer level3Type) {
		this.level3Type = level3Type;
	}



	public Integer getLevel4Type() {
		return level4Type;
	}



	public void setLevel4Type(Integer level4Type) {
		this.level4Type = level4Type;
	}



	public Integer getLevel5Type() {
		return level5Type;
	}



	public void setLevel5Type(Integer level5Type) {
		this.level5Type = level5Type;
	}



	public Integer getLevel6Type() {
		return level6Type;
	}



	public void setLevel6Type(Integer level6Type) {
		this.level6Type = level6Type;
	}



	public Integer getLevel7Type() {
		return level7Type;
	}



	public void setLevel7Type(Integer level7Type) {
		this.level7Type = level7Type;
	}



	public Integer getLevel8Type() {
		return level8Type;
	}



	public void setLevel8Type(Integer level8Type) {
		this.level8Type = level8Type;
	}



	public Integer getLevel9Type() {
		return level9Type;
	}



	public void setLevel9Type(Integer level9Type) {
		this.level9Type = level9Type;
	}



	public Integer getLevel10Type() {
		return level10Type;
	}



	public void setLevel10Type(Integer level10Type) {
		this.level10Type = level10Type;
	}



	public ThresholdTemplate(Integer vid, Integer templateName,
			String watchType, Integer rangeLow, Integer rangeHigh, String unit,
			Integer enableWarning, Integer leve1Low, Integer level1High,
			Integer level1Type, Integer leve2Low, Integer level2High,
			Integer level2Type, Integer leve3Low, Integer level3High,
			Integer level3Type, Integer leve4Low, Integer level4High,
			Integer level4Type, Integer leve5Low, Integer level5High,
			Integer level5Type, Integer leve6Low, Integer level6High,
			Integer level6Type, Integer leve7Low, Integer level7High,
			Integer level7Type, Integer leve8Low, Integer level8High,
			Integer level8Type, Integer leve9Low, Integer level9High,
			Integer level9Type, Integer leve10Low, Integer level10High,
			Integer level10Type, Timestamp createTime, Integer isDelete) {
		super();
		this.vid = vid;
		this.templateName = templateName;
		this.watchType = watchType;
		this.rangeLow = rangeLow;
		this.rangeHigh = rangeHigh;
		this.unit = unit;
		this.enableWarning = enableWarning;
		this.leve1Low = leve1Low;
		this.level1High = level1High;
		this.level1Type = level1Type;
		this.leve2Low = leve2Low;
		this.level2High = level2High;
		this.level2Type = level2Type;
		this.leve3Low = leve3Low;
		this.level3High = level3High;
		this.level3Type = level3Type;
		this.leve4Low = leve4Low;
		this.level4High = level4High;
		this.level4Type = level4Type;
		this.leve5Low = leve5Low;
		this.level5High = level5High;
		this.level5Type = level5Type;
		this.leve6Low = leve6Low;
		this.level6High = level6High;
		this.level6Type = level6Type;
		this.leve7Low = leve7Low;
		this.level7High = level7High;
		this.level7Type = level7Type;
		this.leve8Low = leve8Low;
		this.level8High = level8High;
		this.level8Type = level8Type;
		this.leve9Low = leve9Low;
		this.level9High = level9High;
		this.level9Type = level9Type;
		this.leve10Low = leve10Low;
		this.level10High = level10High;
		this.level10Type = level10Type;
		this.createTime = createTime;
		this.isDelete = isDelete;
	}


	
	

}