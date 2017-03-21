package cn.com.qytx.hotline.crm.action;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.qytx.cbb.org.util.ExportExcel;
import cn.com.qytx.hotline.crm.domaim.CRM;
import cn.com.qytx.hotline.crm.domaim.CRMView;
import cn.com.qytx.hotline.crm.service.ICRM;
import cn.com.qytx.hotline.crm.service.ICRMView;
import cn.com.qytx.monitor.client.log.MonitorLogger;
import cn.com.qytx.monitor.client.logImpl.Log4jImpl;
import cn.com.qytx.platform.base.action.BaseActionSupport;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Sort;
import cn.com.qytx.platform.base.query.Sort.Direction;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.utils.tree.TreeNode;

import com.google.gson.Gson;

/**
 * CRM的列表Action
 * 创建人：李立泼
 * 创建时间：2014年04月14日
 * 修改人：
 * 修改时间：
 */
public class CRMAction extends BaseActionSupport
{
    /**
     * 序列号
     */
    private static final long serialVersionUID = 4328523477972114009L;

    /**
     * log4j日志对象
     */
    private final static MonitorLogger logger =new Log4jImpl(CRMAction.class);

    /**
     * 客户名称字段常量
     */
    private static final String NAME = "name";
    
    /**
     * 客户性别字段常量
     */
    private static final String GENDER = "gender";
    
    /**
     * 客户手机号字段常量
     */
    private static final String MOBILE = "mobile";
    
    /**
     * 备用号码字段常量
     */
    private static final String BACKPHONE = "backPhone";
    
    /**
     * 客户地址字段常量
     */
    private static final String ADDRESS = "address";

    /**
     * crm业务处理类接口
     */
    @Autowired
    private ICRM crmservice;
    @Autowired
    private ICRMView crmViewser;
    /**
     * 客户档案对象
     */
    private CRM crm;
    /**
     * 客户档案视图
     */
    private CRMView crmView;
    /**
     * crm主键 客户id
     */
    private Integer vid;
    /**
     * 跳转页面
     */
    private String toPage;
    /**
     * 查询关键字
     */
    private String searchKey;
    /**
     * (0,男 1,女)
     * 客户性别
     */
    private Integer gender;
    /**
     * 页面来源
     */
    private String fromPage;

    /**
     * 客户档案导出
     */
    public void exporting()
    {
        HttpServletResponse response = this.getResponse();
        response.setContentType("application/vnd.ms-excel");
        OutputStream outp = null;
        UserInfo userInfo = getLoginUser();
        try
        {
            if (userInfo != null)
            {
                searchKey = URLDecoder.decode(searchKey, "utf-8");
                CRM crm = new CRM();
                crm.setGender(gender);
                crm.setName(searchKey);
                crm.setMobile(searchKey);
                this.setIDisplayLength(Integer.MAX_VALUE);
                this.setIDisplayStart(0);
                Page<CRM> pageInfo = crmservice.findPageByVo(getPageable(new Sort(Direction.DESC,
                        "vid")), crm);
                List<CRM> crmList = pageInfo.getContent();
                // 把报表信息填充到map里面
                response.addHeader("Content-Disposition", "attachment;filename="
                        + new String("客户档案.xls".getBytes(), "iso8859-1"));// 解决中文
                outp = response.getOutputStream();
                List<Map<String, Object>> mapList = analyzeResult(crmList);
                ExportExcel exportExcel = new ExportExcel(outp, getExportHeadList(), mapList,
                        getExportKeyList());
                exportExcel.export();
            }
        }
        catch (Exception e)
        {
            logger.error("exporting error. 当前操作人：" + userInfo.getUserId(), e);
        }
        finally
        {
            if (outp != null)
            {
                try
                {
                    outp.close();
                }
                catch (IOException e)
                {
                    logger.error("exporting error. 当前操作人：" + userInfo.getUserId(), e);
                }
            }
        }
    }

    /**
     * 功能：定义导出的各个字段属性列表
     * @return
     */
    private List<String> getExportKeyList()
    {
        List<String> exportKey = new ArrayList<String>();
        exportKey.add("orderNumber");
        exportKey.add(NAME);
        exportKey.add(GENDER);
        exportKey.add(MOBILE);
        exportKey.add(BACKPHONE);
        exportKey.add(ADDRESS);

        return exportKey;
    }

    /**
     * 功能：定义导出的用户档案头部信息
     * @return
     */
    private List<String> getExportHeadList()
    {
        List<String> headList = new ArrayList<String>();
        headList.add("序号");
        headList.add("姓名");
        headList.add("性别");
        headList.add("联系电话");
        headList.add("备用号码");
        headList.add("联系地址");

        return headList;
    }

    /**
     * 功能：把查询出来的客户档案列表转换为map形式保存
     * @param crmList
     * @return
     */
    private List<Map<String, Object>> analyzeResult(List<CRM> crmList)
    {

        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        if (crmList != null)
        {
            int i = getPageable().getPageNumber() * getPageable().getPageSize() + 1;
            for (CRM c : crmList)
            {
                Map<String, Object> map = new HashMap<String, Object>();
                // 标识符
                map.put("vid", c.getVid());
                // 序号
                map.put("orderNumber", i);
                // 姓名
                map.put(NAME, StringUtils.isNotEmpty(c.getName()) == true ? c.getName() : "");
                // 性别
                map.put(GENDER, c.getGender() != null ? (c.getGender() == 0 ? "女" : "男") : "");
                // 联系电话
                map.put(MOBILE, StringUtils.isNotEmpty(c.getMobile()) == true ? c.getMobile() : "");
                map.put(BACKPHONE,
                        StringUtils.isNotEmpty(c.getBackPhone()) == true ? c.getBackPhone() : "");
                // 居住地
                map.put(ADDRESS, StringUtils.isNotEmpty(c.getAddress()) == true ? c.getAddress()
                        : "");
                map.put("linkedId", c.getLinkedId() != null ? c.getLinkedId() : "");
                mapList.add(map);
                i++;
                // logger.info("客户档案，列表展示的信息或将要导出的信息。 " + c);
            }
        }
        return mapList;
    }

    /**
     * 功能：根据电话号码查询用户档案
     * @return
     */
    public String verify()
    {
        UserInfo userInfo = getLoginUser();
        try
        {
            if (userInfo != null)
            {
                crm = crmservice.findCRMByMobile(crm.getMobile());
                if (crm == null)
                {
                    ajax("0");// 没有查找到
                }
                else
                {
                    ajax("1");// 查找到
                }
            }
        }
        catch (Exception e)
        {
            logger.error("verify error. 当前操作人：" + userInfo.getUserId(), e);
        }
        return null;
    }

    /**
     * 功能：根据电话号码查询是否电话号码重复
     * @return
     */
    public String verifyMobileAndTelephone()
    {
        UserInfo userInfo = getLoginUser();
        try
        {
            Integer result = 0;
            if (userInfo != null)
            {
                CRM crmMobile = null;
                if (this.crm != null && this.crm.getMobile() != null
                        && !"".equals(this.crm.getMobile()) && this.crm.getName() != null
                        && !"".equals(this.crm.getName()))
                {
                    crmMobile = crmservice.findByMobileAndName(this.crm.getMobile(),
                            this.crm.getName());
                }
                // 1表示手机号码重复；
                if (crmMobile != null)
                {
                    result = 1;
                    logger.info("手机号码重复。 当前操作人：" + userInfo.getUserId() + crm);
                }
                ajax(result.toString());
            }
        }
        catch (Exception e)
        {
            logger.error("verifyMobileAndTelephone error. 当前操作人：" + userInfo.getUserId(), e);
        }
        return null;
    }

    /**
     * 编辑、新增用户档案
     * @return
     */
    public String edit()
    {
        UserInfo userInfo = getLoginUser();
        try
        {
            if (userInfo != null)
            {
                CRM c = new CRM();
                c = crmservice.findByMobileAndName(crm.getMobile(), crm.getName());
                boolean isAdd = false;// 是否新增
                if (c == null)
                {// 新增
                    isAdd = true;// 表示新增
                    c = new CRM();
                    c.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    c.setCreateUserId(userInfo.getUserId());
                    c.setIsDelete(0);
                    c.setIsForkGroup(getLoginUser().getIsForkGroup());// 控制权限
                    c.setCompanyId(userInfo.getCompanyId()!=null?userInfo.getCompanyId():-1);
                }
                else
                {
                    isAdd = false;// 表示修改
                    c.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
                    c.setLastUpdateUserId(userInfo.getUserId());
                }
                c.setName(crm.getName());
                c.setGender(crm.getGender());
                c.setMobile(crm.getMobile());
                c.setAge(crm.getAge());
                c.setPersonType(crm.getPersonType());
                c.setBackPhone(crm.getBackPhone());
                c.setCardId(crm.getCardId());
                c.setHkAddress(crm.getHkAddress());
                c.setAddress(crm.getAddress());
                c.setCompany(crm.getCompany());
                c.setJob(crm.getJob());
                c.setReceiveMoney(crm.getReceiveMoney());
                c.setNote(crm.getNote());
                crmservice.saveOrUpdate(c);
                if (isAdd)
                {
                    logger.info("客户档案，新增。 当前操作人：" + userInfo.getUserId() + c);
                }
                else
                {
                    logger.info("客户档案，修改。 当前操作人：" + userInfo.getUserId() + c);
                }
                ajax("0");
            }
        }
        catch (Exception e)
        {
            logger.error("edit error. 当前操作人：" + userInfo.getUserId(), e);
        }
        return null;
    }

    /**
     * 编辑和查看用户档案详情
     * @return
     */
    public String detail()
    {
        UserInfo userInfo = getLoginUser();
        try
        {
            if (userInfo != null)
            {
                crm = crmservice.findCRMById(vid);
                if (toPage != null && "edit".equals(toPage))
                {
                    return "edit";
                }
            }
        }
        catch (Exception e)
        {
            logger.error("detail error. 当前操作人：" + userInfo.getUserId(), e);
        }
        return "detail";
    }

    
    public String crmDetail()
    {
        crmView = crmViewser.findCRMById(vid);
        return "detail";
    }
    /**
     * 功能：张东领添加：根据联系电话查询客户信息
     * @return
     */
    private String mobile;

    public String getCrmInfoByPhone()
    {
        CRM c = new CRM();
        CRMView cv = new CRMView();
        UserInfo userInfo = getLoginUser();
        try
        {
            if (userInfo != null && StringUtils.isNotBlank(mobile))
            {
                c = crmservice.findCRMByMobile(mobile);
            }
            if(c==null){
                cv=crmViewser.findByMobileAndName(mobile, null);
            }
            if (c != null)
            {
                Map<String, Object> jsonMap = new HashMap<String, Object>();
                jsonMap.put(NAME, c.getName());
                jsonMap.put("age", c.getAge());
                jsonMap.put(GENDER, c.getGender());
                jsonMap.put(MOBILE, c.getMobile());
                jsonMap.put("personType", c.getPersonType());
                jsonMap.put(BACKPHONE, c.getBackPhone());
                jsonMap.put("cardId", c.getCardId());
                jsonMap.put("hkAddress", c.getHkAddress());
                jsonMap.put(ADDRESS, c.getAddress());
                jsonMap.put("company", c.getCompany());
                jsonMap.put("job", c.getJob());
                jsonMap.put("receiveMoney", c.getReceiveMoney());
                jsonMap.put("note", c.getNote());
                if(c.getLinkedId()==null){
                    jsonMap.put("linkedId", "");
                }else{
                    jsonMap.put("linkedId", c.getLinkedId());
                }

                List<CRM> crmList = crmservice.findByPhone(mobile);
                if (crmList != null)
                {
                    jsonMap.put("isCRMs", crmList.size());
                }
                else
                {
                    jsonMap.put("isCRMs", 0);
                }
                Gson json = new Gson();
                String jsons = json.toJson(jsonMap);
                ajax(jsons);
            }else if(cv != null){
                Map<String, Object> jsonMap = new HashMap<String, Object>();
                jsonMap.put(NAME, cv.getName());
                jsonMap.put("age", cv.getAge());
                jsonMap.put(GENDER, cv.getGender());
                jsonMap.put(MOBILE, cv.getMobile());
                jsonMap.put("personType", cv.getPersonType());
                jsonMap.put(BACKPHONE, cv.getBackPhone());
                jsonMap.put("cardId", cv.getCardId());
                jsonMap.put("hkAddress", cv.getHkAddress());
                jsonMap.put(ADDRESS, cv.getAddress());
                jsonMap.put("company", cv.getCompany());
                jsonMap.put("job", cv.getJob());
                jsonMap.put("receiveMoney", cv.getReceiveMoney());
                jsonMap.put("note", cv.getNote());
                jsonMap.put("linkedId", cv.getId());
                jsonMap.put("isCRMs", 0);
                Gson json = new Gson();
                String jsons = json.toJson(jsonMap);
                ajax(jsons);
            }
        }
        catch (Exception e)
        {
            logger.error("getCrmInfoByPhone error. 当前操作人：" + userInfo.getUserId(), e);
        }
        return null;
    }

    /**
     * 删除用户档案信息的方法
     */
    public String delete()
    {
        UserInfo userInfo = (UserInfo) this.getSession().getAttribute("adminUser");
        try
        {
            if (userInfo != null)
            {
                String crmInfo;// 记录的将要删除的客户信息
                crm.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
                crm.setLastUpdateUserId(userInfo.getUserId());
                crmInfo = crm.toString();
                crmservice.delete(crm.getVid(), false);
                logger.info("客户档案，删除的信息。 当前操作人：" + userInfo.getUserId() + crmInfo);
                ajax("0");
            }
        }
        catch (Exception e)
        {
            logger.error("delete error. 当前操作人：" + userInfo.getUserId(), e);
        }
        return null;
    }

    /**
     * 客户档案列表显示方法
     * @return
     */
    public String list()
    {
        UserInfo userInfo = getLoginUser();
        try
        {
            if (userInfo != null)
            {
                Page<CRM> pageInfo = crmservice.findPageByVo(getPageable(new Sort(Direction.DESC,
                        "vid")), crm);
                List<CRM> crmList = pageInfo.getContent();
                List<Map<String, Object>> mapList = analyzeResult(crmList);
                this.ajaxPage(pageInfo, mapList);
            }
        }
        catch (Exception e)
        {
            logger.error("list error. 当前操作人：" + userInfo.getUserId(), e);
        }
        return null;
    }

    /**
     * 得到CRM的树形结构
     * @return
     */
    public String getCRMtree()
    {
        String contextPath = getRequest().getContextPath() + "/";
        String searchName = this.getRequest().getParameter("searchName");
        List<TreeNode> treeNodes = crmservice.getCRMtree(contextPath, searchName);
        ajax(treeNodes, true);
        return null;
    }

    /* getter setter */
    public CRM getCrm()
    {
        return crm;
    }

    public void setCrm(CRM crm)
    {
        this.crm = crm;
    }

    public Integer getVid()
    {
        return vid;
    }

    public void setVid(Integer vid)
    {
        this.vid = vid;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getToPage()
    {
        return toPage;
    }

    public void setToPage(String toPage)
    {
        this.toPage = toPage;
    }

    public String getSearchKey()
    {
        return searchKey;
    }

    public void setSearchKey(String searchKey)
    {
        this.searchKey = searchKey;
    }

    public Integer getGender()
    {
        return gender;
    }

    public void setGender(Integer gender)
    {
        this.gender = gender;
    }

    public String getFromPage()
    {
        return fromPage;
    }

    public void setFromPage(String fromPage)
    {
        this.fromPage = fromPage;
    }

    /**
     * @return the crmView
     */
    public CRMView getCrmView()
    {
        return crmView;
    }

    /**
     * @param crmView the crmView to set
     */
    public void setCrmView(CRMView crmView)
    {
        this.crmView = crmView;
    }

}
