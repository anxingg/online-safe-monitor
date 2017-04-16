package cn.com.wh.dangersource.domain;


/**
 * 功能：危险源查询类
 * 作者：李立泼
 * 时间：2017年04月10日
 */
public class DangerSourceQuery implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4513214879438291848L;
	
	/**
	 * 主键
	 */
	private Integer id;
    
    /**
     * 关键字
     */
	private String keyword;
    
    /**
     * 危险源级别
     */
    private Integer dangerLevel;
    
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDangerLevelStrStr() {
		return DangerSource.dangerLevelStr(dangerLevel);
	}
	
	public Integer getDangerLevel() {
		return dangerLevel;
	}

	public void setDangerLevel(Integer dangerLevel) {
		this.dangerLevel = dangerLevel;
	}
    
}