package cn.com.qytx.workflow.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.workflow.constans.WorkflowConstans;
import cn.com.qytx.workflow.domain.HotNodeFormAttribute;

/**
 * 功能：流程节点属性DAO 版本: 1.0 开发人员：贾永强 创建日期: 2013-3-21 下午5:20:52 修改日期：2013-3-21
 * 下午5:20:52 修改列表：
 */
@Repository("hotnodeFormAttributeDao")
public class HotNodeFormAttributeDao extends BaseDao<HotNodeFormAttribute, Integer>
	  implements WorkflowConstans,Serializable {

	/**
	 * 描述含义
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 功能：批量删除节点属性
	 * 
	 * @param：sets,节点对象集合
	 * @return
	 * @throws
	 */
	public void delete(final Set<HotNodeFormAttribute> sets) {
		for (final Iterator<HotNodeFormAttribute> iterator = sets.iterator(); iterator
				.hasNext();) {
			final HotNodeFormAttribute nfAttribute = iterator.next();
			super.delete(nfAttribute, true);
		}
	}

	/**
	 * 功能：根据流程ID和任务名称获取节点属性
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public HotNodeFormAttribute findByProcessIdAndName(final int processId, final String toTaskName) {
	    String taskName = toTaskName;
		if (taskName != null && taskName.startsWith("TO ")) {
//		    String[] strs = taskName.split(" ");
//			taskName = strs[1];
			taskName = taskName.split(" ")[1];
		}
		return findOne("processAttribute.id = ?1 and nodeName = ?2", processId,taskName);
	}

	public List<HotNodeFormAttribute> findByProcessAttributeId(final int paId) {
		return super.findAll("processAttribute.id = ?", paId);
	}

	/**
	 * 功能：获取流程的第一个节点 也就是 状态为start
	 * 
	 * @param processId2
	 * @return
	 */
	public HotNodeFormAttribute findFirstTask(final int paId) {
		return findOne("nodeType?1 and processAttribute.id = ?2", NODE_TYPE_START,paId);
	}

	/**
	 * 功能：批量保存
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public void saveBatch(final List<HotNodeFormAttribute> list) {
		for (int i = 0; i < list.size(); i++) {
			final HotNodeFormAttribute nfa = list.get(i);
			/*String sql = "insert into tb_cbb_node_form_attribute"
					+ "(process_attribute_id,node_name,writeable_properties,secret_properties,candidate,node_type,roles,depts,descri,el,is_mydep_can_accept)"
					+ "values("
					+ nfa.getProcessAttribute().getId()
					+ ","
					+ nfa.getNodeName()
					+ ","
					+ nfa.getWriteableProperties()
					+ ","
					+ nfa.getSecretProperties()
					+ ","
					+ nfa.getCandidate()
					+ ","
					+ nfa.getNodeType()
					+ ","
					+ nfa.getRoles()
					+ ","
					+ nfa.getDepts()
					+ ","
					+ nfa.getDescri()
					+ ","
					+ nfa.getEl()
					+ ","
					+ nfa.getIsMydepCanAccept() + ")";
			entityManager.createNativeQuery(sql).executeUpdate();*/
			saveOrUpdate(nfa);
		}
	}

	public void deleteBatch(final int paId) {
		final String sql = "delete from tb_cbb_node_form_attribute where process_attribute_id = "+paId;
		entityManager.createNativeQuery(sql).executeUpdate();
	}
}
