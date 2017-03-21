package cn.com.qytx.hotline.balck.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import cn.com.qytx.cbb.org.util.ExportExcel;
import cn.com.qytx.hotline.balck.domain.Blacklist;
import cn.com.qytx.hotline.balck.service.IBlacklist;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.oa.domain.OaConst;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.base.query.Sort.Order;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.org.service.IUser;

/**
 * 功能:黑名单action
 * 版本: 1.0
 * 开发人员: 李华伟
 * 创建日期: 2013-8-19
 * 修改日期: 2013-8-19
 * 修改列表:
 */
public class BlacklistAction extends BaseActionSupport
{
    /**
     * 序列号
     */
    private static final long serialVersionUID = 3785306915566824638L;

    /**
     * log4j日志对象
     */
    private final static MonitorLogger logger =new Log4jImpl(BlacklistAction.class);

    /**
     * 黑名单service
     */
    @Resource
    IBlacklist blacklistService;

    /**
     * 用户信息
     */
    @Resource
    transient IUser userService;
 
    /**
     * 电话号码
     */
    private String phone;

    /**
     * 黑名单实体类
     */
    private Blacklist blacklist;

    /**
     * 黑名单Id集合
     */
    private Integer[] blacklistId;

    /**
     * 来源
     */
    private String from;

    /**
     * 获取黑名单列表
     */
    public void findBlacklistPage()
    {
        PrintWriter out = null;
        try
        {
            out = this.getResponse().getWriter();
            // 分页信息
            Order order = new Order(Direction.DESC, "createTime");
            Sort s = new Sort(order);
            Pageable pageable = getPageable(s);
            // 查询分页数据信息
            Page<Blacklist> pageInfo = blacklistService.findAll(pageable, phone);
            // 获取结果
            List<Blacklist> blacklists = pageInfo.getContent();
            // 把黑名单信息填充到map里面
            List<Map<String, Object>> mapList = analyzeResult(blacklists, pageable);
            ajaxPage(pageInfo, mapList);
        }
        catch (Exception e)
        { 
            logger.error("findBlacklistPage error. ", e);
        }
        finally
        {
            if (null != out)
            {
                out.close();
            }
        }
    }
    /**
     * 功能：将查询到的黑名单集合转为map集合
     * @param blacklists
     * @return
     */
    private List<Map<String, Object>>  analyzeResult(List<Blacklist> blacklists,Pageable pageable){
    	List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
    	if(blacklists!=null){
    		// 获取序号
    		int i = 1;
    		if(pageable!=null){
    			i = pageable.getPageNumber() * pageable.getPageSize() + 1;
    		}
    		for(Blacklist tempBlacklist : blacklists){
    			//调用实体类将对象转为map方法
    			Map<String, Object> map = tempBlacklist.blackListToMap();
    			 // 序号
                map.put("no", i);
    			// 创建人
                Integer createId = tempBlacklist.getCreateUserId();
                UserInfo createInfo = userService.findOne(createId);
                map.put("createName", null != createInfo ? createInfo.getUserName() : "-"); 
                mapList.add(map);
                i++;
    		}
    	}
    	return mapList;
    }
    /**
     * 功能：黑名单导出
     */
    public void exporting(){
    	HttpServletResponse response = this.getResponse();
		response.setContentType("application/vnd.ms-excel");
		OutputStream outp=null;
		try {
			UserInfo userInfo = getLoginUser();
			if(userInfo!=null){
				this.setIDisplayLength(Integer.MAX_VALUE);
				this.setIDisplayStart(0);
				// 分页信息
	            Order order = new Order(Direction.DESC, "createTime");
	            Sort s = new Sort(order);
	            Pageable pageable = getPageable(s);
				// 查询分页数据信息
	            Page<Blacklist> pageInfo = blacklistService.findAll(pageable, phone);
	            // 获取结果
	            List<Blacklist> blacklists = pageInfo.getContent();
	            // 把报表信息填充到map里面
				response.addHeader("Content-Disposition", "attachment;filename="
						+ new String("黑名单.xls".getBytes(), "iso8859-1"));// 解决中文
				outp = response.getOutputStream();
	            // 把黑名单信息填充到map里面
	            List<Map<String, Object>> mapList = analyzeResult(blacklists, null);
	            ExportExcel exportExcel = new ExportExcel(outp, getExportHeadList(), mapList,
						getExportKeyList());
				exportExcel.export();
			}
		} catch(Exception e){
			logger.error("exporting error. ", e);
		} finally{
			if(outp!=null){
				try {
					outp.close();
				} catch (IOException e) {
					logger.error("exporting error. ", e);
				}
			}
		}
    }
    
    /**
     * 
     * 功能：定义导出的各个字段属性列表
     * @return
     */
	private List<String> getExportKeyList(){
		List<String> exportKey = new ArrayList<String>();
		exportKey.add("no");
		exportKey.add("phone");
		exportKey.add("begintime");
		exportKey.add("durationStr");
		exportKey.add("createName");
		exportKey.add("remark");

		return exportKey;
	}
    /**
     * 
     * 功能：定义导出的黑名单头部信息
     * @return
     */
	private List<String> getExportHeadList(){
		List<String> headList = new ArrayList<String>();
		headList.add("序号");
		headList.add("电话号码");
		headList.add("锁定时间");
		headList.add("锁定时长(小时)");
		headList.add("添加人");
		headList.add("备注");
		return headList;
	}

    /**
     * 功能：保存黑名单
     */
    public void saveBlacklist()
    {
        try
        {
            // 参数校验
            if (!checkParam(blacklist))
            {
                logger.error("saveBlacklist error: param error");
                ajax("paramError");
                return;
            }

            // 根据时长计算结束时间
            Integer duration = blacklist.getDuration();
            if (null == duration)
            {
                // 未配置锁定时长时,默认时长为1
                duration = 1;
                blacklist.setDuration(1);
            }

            if ("seat".equals(from))
            {
                blacklist.setBegintime(new Timestamp(System.currentTimeMillis()));
                Timestamp begintime = blacklist.getBegintime();
                Timestamp endtime = new Timestamp(begintime.getTime() + (long)duration * 60 * 60 * 1000);
                blacklist.setEndtime(endtime);
            }
            else
            {
                Timestamp begintime = blacklist.getBegintime();
                Timestamp endtime = new Timestamp(begintime.getTime() + (long)duration * 60 * 60 * 1000);
                blacklist.setEndtime(endtime);
            }

            // 设置添加人和添加时间
            UserInfo user = (UserInfo) this.getSession().getAttribute("adminUser");
            blacklist.setCreateUserId(user.getUserId());
            blacklist.setCreateTime(new Timestamp(System.currentTimeMillis()));
            blacklist.setIsDelete(OaConst.NOT_DELETE);
            
            //保存company_id
            blacklist.setCompanyId(this.getLoginUser().getCompanyId());
            
            
            // 保存黑名单信息
            Blacklist oldBlacklist = blacklistService.findByPhone(blacklist.getPhone());
            if (oldBlacklist == null)
            {
                blacklistService.saveOrUpdate(blacklist);
            }
            else
            {
                oldBlacklist.setBegintime(blacklist.getBegintime());
                oldBlacklist.setEndtime(blacklist.getEndtime());
                oldBlacklist.setCreateUserId(user.getUserId());
                oldBlacklist.setCreateTime(new Timestamp(System.currentTimeMillis()));
                oldBlacklist.setIsDelete(OaConst.NOT_DELETE);
                oldBlacklist.setDuration(blacklist.getDuration());
                oldBlacklist.setRemark(blacklist.getRemark());
                oldBlacklist.setLockType(blacklist.getLockType());
                oldBlacklist.setCompanyId(blacklist.getCompanyId());
                blacklistService.saveOrUpdate(oldBlacklist);
            }

            ajax("success");
        }
        catch (Exception ex)
        {
            logger.error("saveBlacklist error. ", ex);
        }

    }

    /**
     * 功能：删除黑名单
     */
    public void deleteBlacklist()
    {
        try
        {
            // 基本参数校验
            if (null == blacklistId)
            {
                ajax("param error");
                return;
            }

            // 删除黑白名单
            blacklistService.deleteBatch(blacklistId);
            ajax("success");
        }
        catch (Exception e)
        {
            logger.error("deleteBlacklist error. ", e);
        }
    }

    private boolean checkParam(Blacklist checkBlacklist)
    {
        if (null == checkBlacklist)
        {
            return false;
        }

        // 电话号码不能为空
        String phone = checkBlacklist.getPhone();
        if (StringUtils.isEmpty(phone))
        {
            return false;
        }

        // 开始时间不能为空
        Timestamp begintime = checkBlacklist.getBegintime();
        if (null == begintime)
        {
            return false;
        }
        return true;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public Integer[] getBlacklistId()
    {
        return blacklistId;
    }

    public void setBlacklistId(Integer[] blacklistId)
    {
        this.blacklistId = blacklistId.clone();
    }

    public Blacklist getBlacklist()
    {
        return blacklist;
    }

    public void setBlacklist(Blacklist blacklist)
    {
        this.blacklist = blacklist;
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }
}
