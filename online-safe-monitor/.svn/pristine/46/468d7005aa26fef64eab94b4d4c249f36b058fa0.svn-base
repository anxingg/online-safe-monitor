package cn.com.qytx.workflow.service;

import java.io.Serializable;
import java.util.List;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.service.BaseService;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.workflow.domain.HotNodeFormAttribute;

/**
 * Created by izerui.com on 14-4-30.
 */
public interface ProcessDesignerService extends BaseService<HotNodeFormAttribute> {

    /**
     * 功能：根绝JSON格式的数据更新流程定义
     * 更新内容包括:1，流程定义（json/xml 格式的JPDL）
     * 			   2，各个流程节点的定义。
     * @param
     * @return
     * @throws
     */
    void updateProcessAttributeByJsonData(int processAttributeId,String jsonData);

    /**
     * 功能：根据用户的ID集合查询用户列表
     * @param
     * @return
     * @throws
     */
    List<UserInfo> findByUserIds(String ids);

    /**
     * 根据Dao 执行HQL查询
     */
    <D extends BaseDao<T,Serializable>,T> List<T> findByHql(Class<D> dClas,String hql);

    HotNodeFormAttribute saveOupdate(HotNodeFormAttribute node);
    
    /**根据流程ID，节点名称查询节点
     * @param processId
     * @param nodeName
     * @return
     */
    HotNodeFormAttribute findByNodeName(int processId,String nodeName);
}
