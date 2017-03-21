package cn.com.wh.fee.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import cn.com.qytx.cbb.dict.domain.Dict;
import cn.com.qytx.cbb.dict.service.IDict;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.company.service.IWHCompany;
import cn.com.wh.fee.domain.SearchVo;
import cn.com.wh.fee.service.IFeeUsed;
/**
 * 
 * <br/>功能:费用提取统计
 * <br/>版本: 1.0
 * <br/>开发人员: 吴洲
 * <br/>创建日期: 2015年10月8日
 * <br/>修改日期: 2015年10月8日
 * <br/>修改列表:
 */
public class FeeUsedStatisticAction extends BaseActionSupport{

	private static final long serialVersionUID = -9036816638265397456L;
	
	@Autowired
	private IFeeUsed feeUsedService;
	
	@Autowired
	private IDict dictService;
	@Autowired
	public IWHCompany companyImpl;
	
	private SearchVo  vo;//查询vo
	
	private List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
	
	private WHCompany company;
	
	public WHCompany getCompany() {
		return company;
	}




	public void setCompany(WHCompany company) {
		this.company = company;
	}




	/**
	 * 
	 * 功能：公司年度费用统计
	 * @return
	 */
	public String findComapnyYearStatistic(){
		try {
			Map<Integer,Map<Integer, Double>> quarterMap = feeUsedService.findStatistic(vo);
			List<Dict> dictList = dictService.findAllList("feeType", 1);
			company = companyImpl.findByGroupId(vo.getGroupId());
			//<parentId,List>
			Map<Integer, List<Map<String, Object>>> childMap = new HashMap<Integer, List<Map<String,Object>>>();
			double oneQuarter = 0;//一季度
			double twoQuarter = 0;//二季度
			double threeQuarter = 0;//三季度
			double fourQuarter = 0;//四季度
			if (dictList!=null&&dictList.size()>0) {
				for (Dict info : dictList) {
					if (info.getGrade()==2) {//二级
						if (childMap!=null&&childMap.get(info.getParentId())!=null) {
							List<Map<String, Object>> childList =  childMap.get(info.getParentId());
							Map<String, Object> child = new HashMap<String, Object>();
							child.put("name", info.getName());
							//获得每季度的数据
							if (quarterMap!=null&&quarterMap.get(info.getId())!=null) {
								Map<Integer, Double> quarter = quarterMap.get(info.getId());
								if (quarter.get(1)!=null) {
									oneQuarter += quarter.get(1);
									child.put("oneQuarter", quarter.get(1));
								}
								if (quarter.get(2)!=null) {
									twoQuarter += quarter.get(2);
									child.put("twoQuarter", quarter.get(2));
								}
								if (quarter.get(3)!=null) {
									threeQuarter += quarter.get(3);
									child.put("threeQuarter", quarter.get(3));
								}
								if (quarter.get(4)!=null) {
									fourQuarter += quarter.get(4);
									child.put("fourQuarter", quarter.get(4));
								}
							}else {
								child.put("oneQuarter", 0);
								child.put("twoQuarter", 0);
								child.put("threeQuarter", 0);
								child.put("fourQuarter", 0);
							}
							childList.add(child);
							childMap.put(info.getParentId(), childList);
						}else {
							List<Map<String, Object>> childList =  new ArrayList<Map<String,Object>>();
							Map<String, Object> child = new HashMap<String, Object>();
							child.put("name", info.getName());
							//获得每季度的数据
							if (quarterMap!=null&&quarterMap.get(info.getId())!=null) {
								Map<Integer, Double> quarter = quarterMap.get(info.getId());
								if (quarter.get(1)!=null) {
									oneQuarter += quarter.get(1);
									child.put("oneQuarter", quarter.get(1));
								}
								if (quarter.get(2)!=null) {
									twoQuarter += quarter.get(2);
									child.put("twoQuarter", quarter.get(2));
								}
								if (quarter.get(3)!=null) {
									threeQuarter += quarter.get(3);
									child.put("threeQuarter", quarter.get(3));
								}
								if (quarter.get(4)!=null) {
									fourQuarter += quarter.get(4);
									child.put("fourQuarter", quarter.get(4));
								}
							}else {
								child.put("oneQuarter", 0);
								child.put("twoQuarter", 0);
								child.put("threeQuarter", 0);
								child.put("fourQuarter", 0);
							}
							childList.add(child);
							childMap.put(info.getParentId(), childList);
						}
					}
				}
			}
			//获得一级类别
			if (dictList!=null&&dictList.size()>0) {
				for (Dict info : dictList) {
					if (info.getGrade()==1) {
						Map<String, Object> oneMap = new HashMap<String, Object>();
						oneMap.put("name", info.getName());
						if (childMap!=null&&childMap.get(info.getId())!=null) {
							List<Map<String, Object>> childList =   childMap.get(info.getId());
							Map<String, Object> oMap = childList.get(0);
							oneMap.put("childName", oMap.get("name"));
							oneMap.put("oneQuarter", oMap.get("oneQuarter"));
							oneMap.put("twoQuarter", oMap.get("twoQuarter"));
							oneMap.put("threeQuarter", oMap.get("threeQuarter"));
							oneMap.put("fourQuarter", oMap.get("fourQuarter"));
							childList.remove(0);
							oneMap.put("child", childList);
						}else {
							oneMap.put("child", null);
						}
						resultList.add(oneMap);
					}
				}
			}
			//增加季度金额累计
			Map<String, Object> quarterTotalMap = new HashMap<String, Object>();
			quarterTotalMap.put("name", "季度金额累计");
			quarterTotalMap.put("oneQuarter", oneQuarter);
			quarterTotalMap.put("twoQuarter", twoQuarter);
			quarterTotalMap.put("threeQuarter", threeQuarter);
			quarterTotalMap.put("fourQuarter", fourQuarter);
			resultList.add(quarterTotalMap);
			//增加年度金额累计
			Map<String, Object> yearTotalMap = new HashMap<String, Object>();
			yearTotalMap.put("name", "年金额累计");
			yearTotalMap.put("money", oneQuarter+twoQuarter+threeQuarter+fourQuarter);
			resultList.add(yearTotalMap);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
		}
		return SUCCESS;
	}
	
    
	
	
	public SearchVo getVo() {
		return vo;
	}
	public void setVo(SearchVo vo) {
		this.vo = vo;
	}




	public List<Map<String, Object>> getResultList() {
		return resultList;
	}




	public void setResultList(List<Map<String, Object>> resultList) {
		this.resultList = resultList;
	}

	
}
