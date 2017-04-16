package cn.com.wh.dangersource.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.dict.service.IDict;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.log.service.ILog;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IGroup;
import cn.com.qytx.platform.org.service.IUser;
import cn.com.qytx.platform.utils.JavaBeanUtils;
import cn.com.wh.company.domain.WHCompany;
import cn.com.wh.company.service.IWHCompany;
import cn.com.wh.dangersource.domain.DangerSource;
import cn.com.wh.dangersource.domain.DangerSourceQuery;
import cn.com.wh.dangersource.service.IDangerSource;

import com.google.gson.Gson;

/**
 * 功能：危险源Action
 * 作者：李立泼
 * 时间：2017年04月10日
 * 修改人：
 * 修改时间：
 */
public class DangerSourceAction extends BaseActionSupport{

	private static final long serialVersionUID = -3209484199082218431L;
	
	/**
	 * 常量(表格上的空数据)
	 */
	private static final String DATA_TABLE_EMPTY = "-";
	
	/**
	 * 
	 */
	private static final String SUCCESS = "success";
	
	@Resource(name="dangerSourceImpl")
	public IDangerSource dangerSourceService;
	
	@Autowired
	public IWHCompany companyImpl;
	
	@Resource
    private IUser userService;
	
	@Resource
	private IGroup groupService;
	
	@Resource 
	private IDict dictService;
	
	/**
	 * 系统日志接口
	 */
	@Resource
	private ILog logService;
	
	
	/**
	 * 危险源类
	 */
	private DangerSource ds;
	
	/**
	 * 危险源查询类
	 */
	private DangerSourceQuery dsq;
	
	private Integer groupId;
	
	
	/**
	 * 企业列表
	 * @return
	 */
	public String findByPage(){
		try {
			int pageNum = (int) (Math.ceil((double) this.getIDisplayStart()
					/ (double) this.getIDisplayLength())) + 1;
			List<Sort.Order> orders = new ArrayList<Sort.Order>();
			orders.add(new Sort.Order(Sort.Direction.ASC, "dangerLevel"));
	        orders.add(new Sort.Order(Sort.Direction.DESC, "createTime"));
	        orders.add(new Sort.Order(Sort.Direction.DESC, "id"));
	        Sort sort = new Sort(orders);
	        
	        Page<DangerSource> pageInfo = dangerSourceService.findByPage(getPageable(sort), dsq);
			List<DangerSource> list = pageInfo.getContent();
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int i = (pageNum - 1) * this.getIDisplayLength() + 1;
			if (null != list && !list.isEmpty()) {
				SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-mm-dd");
				
				/* 获取三年前的时间 */
				Calendar threeYearsAgo = Calendar.getInstance();
				threeYearsAgo.add(Calendar.YEAR, -3);
				
				for (DangerSource ds : list) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("no", i);
					
					/* 危险源主键 */
					map.put("id", ds.getId());
					
					/* 危险源名称 */
					map.put("dangerSourceName", StringUtils.defaultString(ds.getDangerSourceName(), DATA_TABLE_EMPTY));
					
					/* 县区 */
					map.put("regionName", "?乌达区?");
					
					/* 所属企业 */
					WHCompany company = ds.getCompany();
					map.put("companyName", company == null ? DATA_TABLE_EMPTY 
							: StringUtils.defaultString(company.getCompanyName(), DATA_TABLE_EMPTY));
					
					/* 危险源级别 */
					map.put("dangerLevelStr", StringUtils.defaultString(ds.getDangerLevelStrStr(), DATA_TABLE_EMPTY));
					
					/* 评价机构 */
					map.put("evaluteOrg", StringUtils.defaultString(ds.getEvaluteOrg(), DATA_TABLE_EMPTY));
					
					/* 最新评价时间 */
					Date evaluteTime = ds.getEvaluteTime();
					map.put("evaluteTime", evaluteTime == null ? DATA_TABLE_EMPTY : dayFormat.format(evaluteTime));
					
					/* 最新评价时间是否超期(3年) */
					if(evaluteTime.before(threeYearsAgo.getTime())){
						/* 超了 */
						map.put("Overdue", 1);
					}else {
						/* 没超 */
						map.put("Overdue", 0);
					}
					
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
	 * 新增操作的方法
	 * @return
	 */
	public String saveDangerSource(){
		//System.out.println(ds);
		UserInfo userInfo = this.getSessionSupport().getCurrentLoginUser();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ds.setCreateTime(new Date());
		ds.setCreateUser(userInfo);
		try {
			ds.setBeginUseTime(sdf.parse(ds.getBeginUseTimeStr()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			ds.setEvaluteTime(sdf.parse(ds.getEvaluteTimeStr()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ds.setIsDelete(0);
		ds.setIsForkGroup(userInfo.getIsForkGroup());
		dangerSourceService.saveOrUpdate(ds);
		return SUCCESS;
	}
	
	/**
	 * 修改操作的方法
	 * @return
	 */
	public String updateDangerSource(){
		UserInfo userInfo = this.getSessionSupport().getCurrentLoginUser();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ds.setLastUpdateTime(new Date());
		ds.setLastUpdateUser(userInfo);
		try {
			ds.setBeginUseTime(sdf.parse(ds.getBeginUseTimeStr()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			ds.setEvaluteTime(sdf.parse(ds.getEvaluteTimeStr()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		/* 需要先查询一次原来的旧对象 */
		DangerSource oldDs = dangerSourceService.findOne(ds.getId());
		
		try {
			/* 把页面表单中传过来的有新值的对象中的新值， 复制给旧对象。 */
			JavaBeanUtils.copyValue(DangerSource.class, ds, oldDs);
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		
		dangerSourceService.saveOrUpdate(oldDs);
		return SUCCESS;
	}
	
	/**
	 * 跳转到修改页面
	 * @return
	 */
	public String toUpdatePage(){
		ds = dangerSourceService.findOne(dsq.getId());
		return SUCCESS;
	}
	
	/**
	 * 删除操作的方法
	 * @return
	 */
	public String deleteDangerSource(){
		ds = dangerSourceService.findOne(dsq.getId());
		dangerSourceService.delete(ds, false);
		return SUCCESS;
	}
    /**
     * 危险源下拉查询
     * @return
     */
	public String getSelectList(){
		List<DangerSource> list=dangerSourceService.findByCompanyId(groupId);
		if(list!=null){
			List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
			for(DangerSource d:list){
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("value",d.getId());
				map.put("name", d.getDangerSourceName());
				mapList.add(map);
			}
			Gson json =new Gson();
			ajax(json.toJson(mapList));
		}
		return null;
		
	}

	public DangerSource getDs() {
		return ds;
	}
	
	public void setDs(DangerSource ds) {
		this.ds = ds;
	}
	
	public DangerSourceQuery getDsq() {
		return dsq;
	}

	public void setDsq(DangerSourceQuery dsq) {
		this.dsq = dsq;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
    
	
	
}
