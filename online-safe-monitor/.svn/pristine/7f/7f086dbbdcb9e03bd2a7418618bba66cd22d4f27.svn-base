package cn.com.qytx.workflow.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.service.impl.BaseServiceImpl;
import cn.com.qytx.platform.org.dao.UserDao;
import cn.com.qytx.platform.org.domain.UserInfo;
import cn.com.qytx.platform.utils.spring.SpringUtil;
import cn.com.qytx.workflow.constans.WorkflowConstans;
import cn.com.qytx.workflow.dao.HotNodeFormAttributeDao;
import cn.com.qytx.workflow.dao.HotProcessAttributeDao;
import cn.com.qytx.workflow.domain.HotNodeFormAttribute;
import cn.com.qytx.workflow.domain.HotProcessAttribute;
import cn.com.qytx.workflow.jpdl.parsejson.NodeObject;
import cn.com.qytx.workflow.jpdl.parsejson.PathObject;
import cn.com.qytx.workflow.service.JsonParserService;
import cn.com.qytx.workflow.service.ProcessDesignerService;

/**
 * Created by izerui.com on 14-4-30.
 */
@Service("hotprocessDesignerService")
@Transactional
public class ProcessDesignerServiceImpl extends BaseServiceImpl<HotNodeFormAttribute> implements ProcessDesignerService,WorkflowConstans {

    @Resource(name="hotnodeFormAttributeDao")
    private HotNodeFormAttributeDao nodeFormAttributeDao;

    @Resource(name="hotjsonParserService")
    private JsonParserService jsonParserService;

    @Resource(name="hotprocessAttributeDao")
    private HotProcessAttributeDao processAttributeDao;


    @Resource
    private UserDao<UserInfo> userDao;


    public void updateProcessAttributeByJsonData(int processAttributeId,String jsonData) {
//		ProcessAttribute pa = processAttributeDao.findById(ProcessAttribute.class, processAttributeId);
//		updateProcessAttributeByJson(pa, jsonData);
        nodeFormAttributeDao.deleteBatch(processAttributeId);
        List<NodeObject> pResult = jsonParserService.parser(jsonData);
        String xmlJpdl = jsonParserService.generateJpdl(pResult);
        processAttributeDao.saveOrUpdateBysql(processAttributeId, jsonData, xmlJpdl);
        List<HotNodeFormAttribute> list=  generateNodeFormAttributes(pResult, processAttributeId);
        nodeFormAttributeDao.saveBatch(list);
    }


    private List<HotNodeFormAttribute> generateNodeFormAttributes(List<NodeObject> pResult,int processAttributeId){
        HotProcessAttribute pa = new HotProcessAttribute();
        pa.setId(processAttributeId);
        List<HotNodeFormAttribute> sets = new ArrayList<HotNodeFormAttribute>();
        int order = 0 ;
        //保存各个节点
        for(Iterator<NodeObject> iterator = pResult.iterator();iterator.hasNext();){
            NodeObject nodeObject = iterator.next();
            if(nodeObject.getType().equals(NODE_TYPE_END)){
                continue;
            }
            HotNodeFormAttribute nfa = new HotNodeFormAttribute();
            nfa.setProcessAttribute(pa);
            nfa.setNodeName(nodeObject.getText().getText());
            nfa.setNodeType(nodeObject.getType());
            if(nodeObject.getProps().getDesc()!=null){
                nfa.setDescri(nodeObject.getProps().getDesc().getValue());
            }
            nfa.setNodeOrder(order++);
            if(nodeObject.getType().equals(NODE_TYPE_DECISON)){
                StringBuffer sbBuffer = new StringBuffer();
                sbBuffer.append("{");
                Set<PathObject> set = nodeObject.getPaths();
                for(Iterator<PathObject> it = set.iterator();it.hasNext();){
                    PathObject po = it.next();
                    sbBuffer.append("\"").append(po.getTo()).append("\":\"").append(po.getExpr()).append("\"");
                    if(it.hasNext()){
                        sbBuffer.append(",");
                    }
                }
                sbBuffer.append("}");
                nfa.setEl(sbBuffer.toString());
            }
            sets.add(nfa);
        }
        return sets;
    }


    /**
     * 功能：根据用户ID集合查找用户信息
     * @param
     * @return
     * @throws
     */
    public List<UserInfo> findByUserIds(String ids) {
        return  userDao.findUsersByIds(ids);
    }

    @Override
    public <D extends BaseDao<T, Serializable>, T> List<T> findByHql(Class<D> dClass, String hql) {
        return SpringUtil.getBean(dClass).findAll(hql);
    }

   
    public HotNodeFormAttribute saveOupdate(HotNodeFormAttribute node){
    	return nodeFormAttributeDao.saveOrUpdate(node);
    }


	@Override
	public HotNodeFormAttribute findByNodeName(int processId, String nodeName) {
		return nodeFormAttributeDao.findByProcessIdAndName(processId, nodeName);
	}
}
