package cn.com.wh.fee.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.dict.domain.Dict;
import cn.com.qytx.cbb.file.service.IAttachment;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.log.service.LogType;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.company.service.IWHCompany;
import cn.com.wh.fee.domain.FeeUsed;
import cn.com.wh.fee.domain.SearchVo;
import cn.com.wh.fee.service.IFeeUsed;
import cn.com.wh.safeaccident.util.Tool;
import cn.com.wh.util.DataInitUtil;

import com.google.gson.Gson;

/**
 * 
 * @author lilipo
 * 
 */
public class FeeUsedAction extends BaseActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1692061788173709610L;

	/**
	 * 列表中的列没有数据
	 */
	private static final String LIST_COLS_NO_DATA = "-";

	/**
	 * 费用使用的接口
	 */
	@Autowired
	private IFeeUsed feeUsedService;

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
	 * 费用使用对象
	 */
	private FeeUsed feeUsed;

	private SearchVo searchvo;

	private WHCompany whcompany;

	private Map<Integer, String> feeTypeMap;

	private Dict dict;

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
			Page<FeeUsed> pageInfo = feeUsedService.findByPage(
					this.getPageable(sort), searchvo);

			List<FeeUsed> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && !list.isEmpty()) {
				Map<Integer, String> dictMap = Tool
						.loadTreeDictMap("feeType", -1);
				for (FeeUsed fe : list) {
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

					// 使用日期
					map.put("useTime", StringUtils.defaultString(
							fe.getUseTime(), LIST_COLS_NO_DATA));

					// 资金用向
					map.put("useDirectionName",
							fe.getUseDirection() == null ? LIST_COLS_NO_DATA
									: StringUtils.isBlank(dictMap.get(fe
											.getUseDirection())) ? LIST_COLS_NO_DATA
											: dictMap.get(fe.getUseDirection()));

					// 费用提取金额（万元）
					map.put("useMoney",
							fe.getUseMoney() == null ? LIST_COLS_NO_DATA : fe
									.getUseMoney());

					// 结存金额（万元）
					map.put("remainingSum",
							fe.getRemainingSum() == null ? LIST_COLS_NO_DATA
									: fe.getRemainingSum());

					// 备注
					map.put("memo", fe.getMemo() == null ? LIST_COLS_NO_DATA
							: fe.getMemo());

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
			if (feeUsed != null) {
				if (feeUsed.getId() == -1) {
					feeUsed.setId(null);
					// 新增
					feeUsed.setIsDelete(0);
					feeUsed.setCreateTime(new Date());
					feeUsed.setCreateUserId(getLoginUser().getUserId());
					feeUsed.setIsForkGroup(getLoginUser().getIsForkGroup());
					feeUsed.setGroupName(getLoginUser().getGroupName());

					Calendar cal = Calendar.getInstance();
					cal.setTime(Tool.getDateByString(feeUsed.getUseTime(),
							null, "yyyy-MM-dd"));
					feeUsed.setYear(cal.get(Calendar.YEAR));
					feeUsed.setQuarter(Tool.getQuarter(cal));

					try {
						feeUsed = feeUsedService.saveOrUpdate(feeUsed);
					} catch (Exception e) {
						result = 0;
						LOGGER.error("新增安全生产费用使用时出错", e);
					}
					if (feeUsed.getId() != null) {
						result = 1;
						//记录日志
						logService.saveOrUpdate( Tool.generateLog(getLoginUser(), 
								this.getRequest().getRemoteAddr(), 
								"新增安全生产费用使用成功", 
								LogType.LOG_FEEUSED_ADD, 
								feeUsed, 
								feeUsed.getId()) );
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
			LOGGER.error("新增或修改安全生产费用使用操作时出错", e);
		}
		return null;
	}

	/**
	 * 获取初始化数据
	 * 
	 * @return
	 */
	public String getInitData() {
		if (searchvo != null && searchvo.getGroupId() != null) {
			whcompany = companyImpl.findByGroupId(searchvo.getGroupId());
		}
		feeTypeMap = Tool.loadTreeDictMap("feeType", null);
		return "success";
	}

	/**
	 * @return
	 */
	public String getSubDictMap() {
		try {
			List<Map<String, Object>> result = new LinkedList<Map<String, Object>>();
			if (dict != null) {
				Map<Integer, String> dictMap = Tool.loadTreeDictMap(
						dict.getInfoType(), dict.getParentId());

				Set<Entry<Integer, String>> entrySet = dictMap.entrySet();
				Iterator<Entry<Integer, String>> iterator = entrySet.iterator();
				while (iterator.hasNext()) {
					Map<String, Object> map = new HashMap<String, Object>();
					Entry<Integer, String> entry = iterator.next();
					map.put("id", entry.getKey());
					map.put("name", entry.getValue());

					result.add(map);
				}
			}
			PrintWriter writer = new PrintWriter(this.getResponse().getWriter());
			Gson gson = new Gson();
			writer.print(gson.toJson(result));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			LOGGER.error("获取数据字典数据时出错", e);
		}
		return null;
	}

	public FeeUsed getFeeUsed() {
		return feeUsed;
	}

	public void setFeeUsed(FeeUsed feeUsed) {
		this.feeUsed = feeUsed;
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

	public Map<Integer, String> getFeeTypeMap() {
		return feeTypeMap;
	}

	public void setFeeTypeMap(Map<Integer, String> feeTypeMap) {
		this.feeTypeMap = feeTypeMap;
	}

	public Dict getDict() {
		return dict;
	}

	public void setDict(Dict dict) {
		this.dict = dict;
	}

}
