package cn.com.wh.fee.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.file.service.IAttachment;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.company.service.IWHCompany;
import cn.com.wh.fee.domain.FeeExtract;
import cn.com.wh.fee.domain.SearchVo;
import cn.com.wh.fee.service.IFeeExtract;
import cn.com.wh.safeaccident.util.Tool;
import cn.com.wh.util.DataInitUtil;

import com.google.gson.Gson;

/**
 * 
 * @author lilipo
 * 
 */
public class FeeExtractAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2691627764791504366L;

	/**
	 * 列表中的列没有数据
	 */
	private static final String LIST_COLS_NO_DATA = "-";

	/**
	 * 费用提取的接口
	 */
	@Autowired
	IFeeExtract feeExtractService;

	/**
	 * 附件接品
	 */
	@Autowired
	private IAttachment attachmentService;
	
	/**
	 * 公司接口
	 */
	@Autowired
	public IWHCompany companyImpl;
	
    /**
     * 系统日志接口
     */
    @Resource
    private ILog logService;

	/**
	 * 费用提取对象
	 */
	private FeeExtract feeExtract;

	private SearchVo searchvo;
	
	private WHCompany whcompany;
	
	public Map<Integer, String> companyMap;

	/**
	 * 分页查询列表
	 * 
	 * @return
	 */
	public String list() {
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()
					/ (double) this.getIDisplayLength())) + 1;

			// 创建排序规则
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "id"));

			// 查询
			Page<FeeExtract> pageInfo = feeExtractService.findByPage(
					this.getPageable(sort), searchvo);

			List<FeeExtract> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && !list.isEmpty()) {

				for (FeeExtract fe : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					// 序号
					map.put("no", i++);

					// 主键
					map.put("id", fe.getId());

					// 公司名称
					map.put("groupName",
							fe.getGroupId() == null ? LIST_COLS_NO_DATA
									: StringUtils.defaultString(
											DataInitUtil.companyMap.get(fe
													.getGroupId()),
											LIST_COLS_NO_DATA));

					// 提取标准
					map.put("extractStand", StringUtils.defaultString(
							fe.getExtractStand(), LIST_COLS_NO_DATA));

					// 提取时间
					map.put("extractTime", StringUtils.defaultString(
							fe.getExtractTime(), LIST_COLS_NO_DATA));

					// 上年度营业额（万元）
					map.put("turnover",
							fe.getTurnover() == null ? LIST_COLS_NO_DATA : fe
									.getTurnover());

					// 本次提取（万元）
					map.put("extractMoney",
							fe.getExtractMoney() == null ? LIST_COLS_NO_DATA
									: fe.getExtractMoney());

					// 结存金额（万元）
					map.put("remainingSum",
							fe.getRemainingSum() == null ? LIST_COLS_NO_DATA
									: fe.getRemainingSum());

					mapList.add(map);
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
			LOGGER.error("安全生产费用提取查询列表出错", e);
		}
		return null;
	}

	/**
	 * 新增或修改
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		try {
			int result = 0;//
			if (feeExtract != null) {
				if (feeExtract.getId() == -1) {
					feeExtract.setId(null);
					// 新增
					feeExtract.setIsDelete(0);
					feeExtract.setCreateTime(new Date());
					feeExtract.setCreateUserId(getLoginUser().getUserId());
					feeExtract.setIsForkGroup(getLoginUser().getIsForkGroup());
					try {
						feeExtract = feeExtractService.saveOrUpdate(feeExtract);
					} catch (Exception e) {
						result = 0;
						LOGGER.error("新增安全生产费用提取时出错", e);
					}
					if (feeExtract.getId() != null) {
						result = 1;
						//记录日志
						logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
								this.getRequest().getRemoteAddr(), 
								"新增安全生产费用提取成功", 
								LogType.LOG_FEEEXTRACT_ADD, 
								feeExtract, 
								feeExtract.getId()) );
					}
					
				} else {
					// 修改
				}
			}
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			writer.print(result);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error("新增或修改安全生产费用提取操作时出错", e);
		}
		return null;
	}
	
	/**
	 * 根据部门ID查询部门
	 * @return
	 */
	public String getExtractStand(){
		if(searchvo != null && searchvo.getGroupId() != null){
			whcompany = companyImpl.findByGroupId(searchvo.getGroupId());
		}
		return "success";
	}
	
	/**
	 * 跳转到统计列表页面
	 * @return
	 */
	public String toPage(){
		companyMap = Tool.loadCompanyMap();
		return "success";
	}

	public FeeExtract getFeeExtract() {
		return feeExtract;
	}

	public void setFeeExtract(FeeExtract feeExtract) {
		this.feeExtract = feeExtract;
	}

	public SearchVo getSearchvo() {
		return searchvo;
	}

	public void setSearchvo(SearchVo searchvo) {
		this.searchvo = searchvo;
	}

	public WHCompany getWhcompany() {
		return whcompany;
	}

	public void setWhcompany(WHCompany whcompany) {
		this.whcompany = whcompany;
	}

	public Map<Integer, String> getCompanyMap() {
		return companyMap;
	}

	public void setCompanyMap(Map<Integer, String> companyMap) {
		this.companyMap = companyMap;
	}

}
