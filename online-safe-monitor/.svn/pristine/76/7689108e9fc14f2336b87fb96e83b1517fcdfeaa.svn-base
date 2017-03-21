package cn.com.wh.dangerchemicals.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.file.service.IAttachment;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.wh.dangerchemicals.domain.CompanyDangerChemicals;
import cn.com.wh.dangerchemicals.domain.CompanyDangerChemicalsStatisticsResult;
import cn.com.wh.dangerchemicals.domain.DangerChemicalsInfo;
import cn.com.wh.dangerchemicals.service.CompanyDangerChemicalsService;
import cn.com.wh.dangerchemicals.service.DangerChemicalsService;
import cn.com.wh.util.DataInitUtil;

import com.google.gson.Gson;

/**
 * @author lilipo
 * 
 */
public class CompanyDangerChemicalsListAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5114090361164736989L;

	/**
	 * 列表中的列没有数据
	 */
	private static final String LIST_COLS_NO_DATA = "-";

	/**
	 * 公司危化品接口
	 */
	@Autowired
	private CompanyDangerChemicalsService cdcService;

	/**
	 * 公司危化品接口
	 */
	@Autowired
	private DangerChemicalsService dcService;

	/**
	 * 附件接口
	 */
	@Autowired
	private IAttachment attachmentService;

	/**
	 * 公司危化品对象
	 */
	private CompanyDangerChemicals companyDangerChemicals;

	private String materialName;
	private Integer whroletype;
	private Integer statisticsType;

	/**
	 * 列表
	 * 
	 * @return
	 */
	public String list() {
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()
					/ (double) this.getIDisplayLength())) + 1;

			// 创建排序规则
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "groupId"),new Sort.Order(Direction.DESC, "createTime"));

			Integer groupId = companyDangerChemicals.getGroupId();
			Integer dangerId = companyDangerChemicals.getDangerId();

			// 查询
			Page<CompanyDangerChemicals> pageInfo = cdcService.findByPage(
					this.getPageable(sort), materialName, dangerId, groupId,
					whroletype);

			List<CompanyDangerChemicals> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {
				Map<Integer, DangerChemicalsInfo> dcimap = CompanyDangerChemicalsLoadAction.changetomap(dcService.findAll());
				DangerChemicalsInfo dci = null;
				for (CompanyDangerChemicals cdc : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					// 序号
					map.put("no", i);
					
					//公司名称
					if (DataInitUtil.companyMap!=null&&DataInitUtil.companyMap.get(cdc.getGroupId())!=null) {
						map.put("companyName", DataInitUtil.companyMap.get(cdc.getGroupId()));
					}else {
						map.put("companyName", "");
					}

					// 主键
					map.put("vid", cdc.getVid());

					if(cdc != null){
						dci = dcimap.get(cdc.getDangerId());
					}
					if(dci != null){
						cdc.setMaterialName(dci.getMaterialName());
					}
					// 化学品名称
					map.put("materialName", StringUtils.defaultString(
							cdc.getMaterialName(), LIST_COLS_NO_DATA));

					// 存放地点
					map.put("storagePlace", StringUtils.defaultString(
							cdc.getStoragePlace(), LIST_COLS_NO_DATA));

					// 数量
					map.put("num", cdc.getNum() == null ? LIST_COLS_NO_DATA
							: cdc.getNum());

					// 使用地点
					map.put("userPlace", StringUtils.defaultString(
							cdc.getUserPlace(), LIST_COLS_NO_DATA));

					// 危险性分类
					map.put("riskType", StringUtils.defaultString(
							cdc.getRiskType(), LIST_COLS_NO_DATA));

					// 危规号
					map.put("riskNum", StringUtils.defaultString(
							cdc.getRiskNum(), LIST_COLS_NO_DATA));

					// 包装类别
					map.put("packagingCategory", StringUtils.defaultString(
							cdc.getPackagingCategory(), LIST_COLS_NO_DATA));

					// 登记号
					map.put("registrationNO", StringUtils.defaultString(
							cdc.getRegistrationNO(), LIST_COLS_NO_DATA));

					// 技术说明书ID
					map.put("technicalId", cdc.getTechnicalId());

					// 技术说明书名称
					map.put("technicalName", StringUtils.defaultString(
							cdc.getTechnicalName(), LIST_COLS_NO_DATA));

					// 安全标签ID
					map.put("securityId", cdc.getSecurityId());

					// 安全标签名称
					map.put("securityName", StringUtils.defaultString(
							cdc.getSecurityName(), LIST_COLS_NO_DATA));

					// 备注
					map.put("memo", StringUtils.defaultString(cdc.getMemo(),
							LIST_COLS_NO_DATA));

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
	 * 列表
	 * 
	 * @return
	 */
	public String statistics() {
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()
					/ (double) this.getIDisplayLength())) + 1;

			// 创建排序规则（写啥都行，真正的排序写在DAO中）
			Sort sort = new Sort(new Sort.Order(Direction.DESC, "createTime"));

			Integer groupId = companyDangerChemicals.getGroupId();
			String materialName = companyDangerChemicals.getMaterialName();

			// 查询
			Page<CompanyDangerChemicalsStatisticsResult> pageInfo = cdcService
					.findStatisticsResultByPage(this.getPageable(sort),
							materialName, statisticsType, groupId);

			List<CompanyDangerChemicalsStatisticsResult> list = pageInfo
					.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && list.size() > 0) {
				Map<Integer, String> companyMap = DataInitUtil.companyMap;
				List<DangerChemicalsInfo> dclist = dcService.findAll();
				Map<Integer, String> dcMap = new HashMap<Integer, String>();
				if (dclist != null && !dclist.isEmpty()) {
					for (DangerChemicalsInfo dc : dclist) {
						dcMap.put(dc.getVid(), dc.getMaterialName());
					}
				}
				for (CompanyDangerChemicalsStatisticsResult cdcsr : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					// 序号
					map.put("no", i);

					// 企业ID
					map.put("groupId", cdcsr.getGroupId());

					// 企业名称
					map.put("groupName", StringUtils.defaultString(
							companyMap.get(cdcsr.getGroupId()),
							LIST_COLS_NO_DATA));

					// 化学品ID
					map.put("dangerId", cdcsr.getDangerId());

					// 物质名称（危险化学品名）
					map.put("materialName", StringUtils.defaultString(
							dcMap.get(cdcsr.getDangerId()), LIST_COLS_NO_DATA));

					// 数量
					map.put("num", cdcsr.getCount() == null ? LIST_COLS_NO_DATA
							: cdcsr.getCount());

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

	// private Attachment getAttachmentById(final String reportId) {
	// String temp = null;
	// if (StringUtils.isBlank(reportId)) {
	// return null;
	// } else {
	// temp = reportId.replaceAll(",", "");
	// return attachmentService.findOne(Integer.parseInt(temp));
	// }
	// }

	public CompanyDangerChemicals getCompanyDangerChemicals() {
		return companyDangerChemicals;
	}

	public void setCompanyDangerChemicals(
			CompanyDangerChemicals companyDangerChemicals) {
		this.companyDangerChemicals = companyDangerChemicals;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public Integer getWhroletype() {
		return whroletype;
	}

	public void setWhroletype(Integer whroletype) {
		this.whroletype = whroletype;
	}

	public Integer getStatisticsType() {
		return statisticsType;
	}

	public void setStatisticsType(Integer statisticsType) {
		this.statisticsType = statisticsType;
	}

}
