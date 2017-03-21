package cn.com.wh.exam.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.dict.service.IDict;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.wh.company.service.IWHCompany;
import cn.com.wh.exam.domain.ExamPaper;
import cn.com.wh.exam.domain.ExamTest;
import cn.com.wh.exam.domain.ExamUserTest;
import cn.com.wh.exam.domain.SearchVo;
import cn.com.wh.exam.service.ExamTestService;
import cn.com.wh.exam.service.IExamPaper;
import cn.com.wh.exam.service.IExamUserTest;
import cn.com.wh.util.DataInitUtil;
import cn.com.wh.util.ExportExcelUtils;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
/**
 * 
 * <br/>功能:人员考试action
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2015年8月26日
 * <br/>修改日期: 2015年8月26日
 * <br/>修改列表:
 */
public class UserExamAction extends BaseActionSupport{

	@Autowired
	private IDict dictService;
	
	@Autowired
    private ExamTestService examTestService;
	
	private ExamPaper examPaper;
	
	private ExamTest examTest;//考试
	
	private Integer examTestId;//考试id
	
	private String userName;//考试人姓名
	
	private String idcard;//身份证号
	
	private SearchVo vo;
	
	@Autowired
	private IWHCompany iwhCompany;
	
	@Autowired
	private IExamUserTest examUserTestService;
	
	@Autowired
	private IExamUserTest examUserTestDetailService;
	@Autowired
	private IExamPaper examPaperImpl;
	
	
	/**
	 * 
	 * 功能：进入考试
	 * @return
	 */
	public String joinExam(){
		//examTest = examTestService.findOne(examTestId);
		examPaper = examPaperImpl.findOne(examTestId);
		return "detail";
	}
	
	/**
	 * 
	 * 功能：选择试卷进入考试  wangxj add  20150902
	 * @return
	 */
	public String joinPaperExam(){
		//examTest = examTestService.findOne(examTestId);
		examPaper = examPaperImpl.findOne(examTestId);
		
		return "detail";
	}
	
	/**
	 * 
	 * 功能：判断是否参加过考试
	 * @return
	 */
	public String checkUser(){
		try {
			List<ExamUserTest> list = examUserTestService.getExamTestList(examTestId, userName, idcard);
			if (list==null||list.size()==0) {
				ajax("0");
			}else {
				ajax("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajax("3");
		}
		return null;
	}
	
	/**
	 * 考试成绩查询
	 * @return
	 */
	public String findUserExamScore(){
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart() / (double) this.getIDisplayLength())) + 1;
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "startTime"));
			Page<ExamUserTest> pageInfo = examUserTestService.findByPage(this.getPageable(sort), vo);
			List<ExamUserTest> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			Map<Integer, Integer> testMap = examTestService.getAllTestType();
			Map<String, String> dictmap = dictService.findMap("examType", 1);
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			//查询所有考试的考试类型
			if (null != list && list.size() > 0) {
				for (ExamUserTest user : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					//主键
					map.put("id", user.getId());
					//序号
					map.put("no", i);
					//公司名称
					if (DataInitUtil.companyMap!=null&&DataInitUtil.companyMap.get(user.getGroupId())!=null) {
						map.put("companyName", DataInitUtil.companyMap.get(user.getGroupId()));
					}else {
						map.put("companyName", "");
					}
					//考试名称
					map.put("testTitle", user.getTestName());
					//试卷名称
					map.put("paperTitle", user.getPaperName());
					//考试类型
					String testTypeStr = "";
					if(user.getTestId()!=null){
						if (testMap!=null&&testMap.get(user.getTestId())!=null) {
							Integer testType = testMap.get(user.getPaperId());
							if (dictmap!=null&&dictmap.get(testType.toString())!=null) {
								testTypeStr = dictmap.get(testType.toString());
							}
						}
						
					}
					map.put("testType", testTypeStr);
					//考试月份
					if (user.getStartTime()!=null) {
						map.put("examTime", new SimpleDateFormat("yyyy-MM").format(user.getStartTime()));
					}else {
						map.put("examTime", "");
					}
					//考试人员
					map.put("userName", user.getUserName());
					//考试成绩
					map.put("score", user.getScore());
					
					mapList.add(map);
					i++;
				}
			}
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("iTotalRecords", pageInfo.getTotalElements());
			jsonMap.put("iTotalDisplayRecords", pageInfo.getTotalElements());
			jsonMap.put("aaData", mapList);
			Gson json = new Gson();
			String jsons = json.toJson(jsonMap);
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(jsons);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
     * 导出考试结果
     */
    public String exportUserScore()
    {
		try {	
			HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(StrutsStatics.HTTP_RESPONSE);
			//得到登录用户
			Object userObject=getLoginUser();
			if(userObject!=null){
				int pageNum = (int) (Math.ceil((double) this.getIDisplayStart() / (double) this.getIDisplayLength())) + 1;
				this.setIDisplayLength(Integer.MAX_VALUE);
				Sort sort = new Sort(new Sort.Order(Direction.DESC, "startTime"));
				Page<ExamUserTest> pageInfo = examUserTestService.findByPage(this.getPageable(sort), vo);
				List<ExamUserTest> list = pageInfo.getContent();
				List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
				Map<Integer, Integer> testMap = examTestService.getAllTestType();
				Map<String, String> dictmap = dictService.findMap("examType", 1);
				int i = (pageNum - 1) * this.getIDisplayLength() + 1;
				//查询所有考试的考试类型
				if (null != list && list.size() > 0) {
					for (ExamUserTest user : list) {
						Map<String, Object> map = new HashMap<String, Object>();
						//主键
						map.put("id", user.getId());
						//序号
						map.put("no", i);
						//公司名称
						if (DataInitUtil.companyMap!=null&&DataInitUtil.companyMap.get(user.getGroupId())!=null) {
							map.put("companyName", DataInitUtil.companyMap.get(user.getGroupId()));
						}else {
							map.put("companyName", "");
						}
						//考试名称
						//map.put("testTitle", user.getTestName());
						//试卷名称
						map.put("paperTitle", user.getPaperName());
						//试卷类型
						String testTypeStr = "";
						if(user.getTestId()!=null){
							if (testMap!=null&&testMap.get(user.getTestId())!=null) {
								Integer testType = testMap.get(user.getPaperId());
								if (dictmap!=null&&dictmap.get(testType.toString())!=null) {
									testTypeStr = dictmap.get(testType.toString());
								}
							}
							
						}
						map.put("testType", testTypeStr);
						//考试月份
						if (user.getStartTime()!=null) {
							map.put("examTime", new SimpleDateFormat("yyyy-MM").format(user.getStartTime()));
						}else {
							map.put("examTime", "");
						}
						//考试人员
						map.put("userName", user.getUserName());
						//考试成绩
						map.put("score", user.getScore());
						
						mapList.add(map);
						i++;
					}
				}
	            
				response.setContentType("application/vnd.ms-excel");
		        //response.addHeader("Content-Disposition","attachment;filename=" + new String("考试成绩.xls".getBytes(), "iso8859-1"));
				response.setCharacterEncoding("utf-8");
				String fileName = "考试成绩.xls";
				String userAgent = this.getRequest().getHeader("User-agent");//返回客户端浏览器的版本号、类型
				if(userAgent.toUpperCase().contains("FIREFOX") 
						|| userAgent.toUpperCase().contains("CHROME") 
						|| userAgent.toUpperCase().contains("OPR") 
						|| userAgent.toUpperCase().contains("SAFARI") ) {
					
					fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
				}else {
					fileName = URLEncoder.encode(fileName, "UTF-8");
				}
				response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
		        OutputStream out = response.getOutputStream();		
		        Integer userType = (Integer)getSession().getAttribute("whroletype");
		        if (userType==1) {//政府
		        	String[] headerArr = { "序号", "试卷名称", "试卷类型","公司名称","考试月份","考试人员","成绩"};
					String[] columnArr = { "no", "paperTitle","testType","companyName","examTime","userName","score"};
					ExportExcelUtils.exportExcel("考试成绩", headerArr, columnArr,mapList, out);
			        out.close();
		        }else {//企业
					String[] headerArr = { "序号", "试卷名称", "考试类型","公司名称","考试人员","成绩"};
					String[] columnArr = { "no", "paperTitle","testType","companyName","userName","score"};
					ExportExcelUtils.exportExcel("考试成绩", headerArr, columnArr,mapList, out);
			        out.close();
		        }
				
			}
		} catch (Exception e) {  
			e.printStackTrace();
			return null;
		}
		return null;
	}
	

	public ExamTest getExamTest() {
		return examTest;
	}

	public void setExamTest(ExamTest examTest) {
		this.examTest = examTest;
	}

	public Integer getExamTestId() {
		return examTestId;
	}

	public void setExamTestId(Integer examTestId) {
		this.examTestId = examTestId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getIdcard() {
		return idcard;
	}


	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}


	public SearchVo getVo() {
		return vo;
	}


	public void setVo(SearchVo vo) {
		this.vo = vo;
	}

	public ExamPaper getExamPaper() {
		return examPaper;
	}

	public void setExamPaper(ExamPaper examPaper) {
		this.examPaper = examPaper;
	}
	
	
}
