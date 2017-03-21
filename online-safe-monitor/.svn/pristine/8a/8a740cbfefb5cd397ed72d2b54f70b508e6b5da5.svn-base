package cn.com.wh.exam.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * <br/>功能:试卷试题对应关系
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2015年8月21日
 * <br/>修改日期: 2015年8月21日
 * <br/>修改列表:
 */
@Entity
@Table(name = "tb_wuhai_paper_question")
public class ExamPaperQuestion implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VID",unique=true,nullable=false)
	private Integer id;//主键
	
	/**
	 * 试卷id
	 */
	@Column(name="PAPER_ID")
	private Integer paperId;
	
	/**
	 * 试题id
	 */
	@Column(name="QUESTION_ID")
	private Integer questionId;
	
	/**
	 * 分数
	 */
	@Column(name="SCORE")
	private Integer score;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	
	
}