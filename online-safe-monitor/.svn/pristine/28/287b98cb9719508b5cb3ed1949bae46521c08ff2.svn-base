package cn.com.wh.exam.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.dict.service.IDict;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.wh.company.service.IWHCompany;
import cn.com.wh.exam.domain.ExamPaper;
import cn.com.wh.exam.domain.ExamTest;
import cn.com.wh.exam.domain.SearchVo;
import cn.com.wh.exam.service.ExamTestService;
import cn.com.wh.exam.service.IExamPaper;
import cn.com.wh.util.DataInitUtil;

import com.google.gson.Gson;

public class ExamTestAction extends BaseActionSupport{

	@Autowired
	private IDict dictService;
	
	@Autowired
    private ExamTestService examTestService;
	
	
	private String operType;//操作类型
	
	private ExamTest examTest;//考试
	
	private Integer examTestId;//考试id
	
	@Autowired
	private IWHCompany iwhCompany;
	
	@Autowired
	private IExamPaper examPaperService;
	
	private SearchVo search;//查询条件
	
	private Integer state;
	
	/**
	 * 
	 * 功能：考试列表
	 * 
	 * @return
	 */
	public String listExamTest() {

		try {
			Integer groupId = this.getLoginUser().getGroupId();
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()
					/ (double) this.getIDisplayLength())) + 1;
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "createTime"));
			Page<ExamTest> pageInfo = examTestService.findByPage(this.getPageable(sort), search);
			List<ExamTest> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			Map<String, String> dictmap = dictService.findMap("examType", 1);
			//查询所有状态的试卷
			search = new SearchVo();
			Map<Integer,String> mapPaper = examPaperService.findByPaperType(search);
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {
				for (ExamTest examTest : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					if (DataInitUtil.companyMap!=null&&DataInitUtil.companyMap.get(groupId)!=null) {
						map.put("companyName", DataInitUtil.companyMap.get(groupId));
					}else {
						map.put("companyName","");
					}
					map.put("id", examTest.getId());
					map.put("no", i);
					map.put("testName", examTest.getTestName());
					if (examTest.getTestType() != null) {
						if(dictmap.containsKey(examTest.getTestType().toString())){
							map.put("testType",
									dictmap.get(examTest.getTestType().toString()));
						}else{
							map.put("testType", "");
						}
					} else {
						map.put("testType", "");
					}
					if (examTest.getState() != null) {
						if (examTest.getState() == 0) {
							map.put("state", "生效");
						} else {
							map.put("state", "失效");
						}
					} else {
						map.put("state", "");
					}
					String pagerIds = examTest.getPaperIds();
					String paperSrt = "";
                    if(StringUtils.isNotEmpty(pagerIds)){
                    	String Paper[] = pagerIds.split(",");
                    	int size = Paper.length;
                    	for (int j = 1; j < size ; j++) {
                    		String PaperString = Paper[j];
                    		if(mapPaper.containsKey(Integer.valueOf(PaperString))){
                    			paperSrt += mapPaper.get(Integer.valueOf(Paper[j]))+" ";
                    		}
						}
                    }
                    map.put("paperSrt", paperSrt);
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
	 * 
	 * 功能：添加考试（修改）
	 * @return
	 */
	public String addExamTest(){
		try {
			Integer groupId  = this.getLoginUser().getGroupId();
			if("add".equals(operType)){
				examTest.setGroupId(groupId);
				examTest.setIsForkGroup(groupId);
				examTest.setCreateTime(new Timestamp(System.currentTimeMillis()));
				examTestService.saveOrUpdate(examTest);
				ajax("1");
			}else{
				ExamTest examTestOld = examTestService.findOne(examTestId);
				examTestOld.setTestName(examTest.getTestName());
				examTestOld.setTestType(examTest.getTestType());
				examTestOld.setPaperIds(examTest.getPaperIds());
				examTestService.saveOrUpdate(examTestOld);
				ajax("2");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajax("0");
		}
		return null;
		
	}
	
	/**
	 * 
	 * 功能：查询考试信息
	 * @return
	 */
	public String findExamTest(){
		ExamTest examTest = examTestService.findOne(examTestId);
		Integer groupId = this.getLoginUser().getGroupId();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("companyName", DataInitUtil.companyMap.get(groupId));
		map.put("testName", examTest.getTestName());
		map.put("testType", examTest.getTestType());
		map.put("parperIds", examTest.getPaperIds()==null?"":examTest.getPaperIds());
		Gson gson = new Gson();
		String gsonStr = gson.toJson(map);
		ajax(gsonStr);
		return null;
	}
	
	
	/**
	 * 
	 * 功能：删除考试
	 * @return
	 */
	public String deleteExamTest(){
		try {
			this.examTestService.delete(examTestId, false);
			ajax("1");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ajax("0");
		}
		return null;
	}
	
	/**
	 * 
	 * 功能：获取选择试卷信息
	 * @return
	 */
	public String getPaper(){
		this.setIDisplayLength(10000);
		Sort sort = new Sort(new Sort.Order(Direction.ASC, "createTime"));
		Page<ExamPaper> pageInfo = examPaperService.findByPage(this.getPageable(sort), search);
		List<ExamPaper> list = pageInfo.getContent();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		if (null != list && list.size() > 0) {
			for (ExamPaper paper : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", paper.getId());
				map.put("paperName", paper.getPaperName());
				mapList.add(map);
			}
		}
		Gson gson = new Gson();
		String gsonStr = gson.toJson(mapList);
		ajax(gsonStr);
		return null;
		
	}
	
	/**
	 * 
	 * 功能：是否生效
	 * @return
	 */
	public String updateTestState(){
		try {
			this.examTestService.updateTestState(examTestId, state);
			ajax("1");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ajax("0");
		}
		return null;
	
		
	}
	
	public String getPaperByIds(){
		return null;
		
	}


	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
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

	public SearchVo getSearch() {
		return search;
	}

	public void setSearch(SearchVo search) {
		this.search = search;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	
}
