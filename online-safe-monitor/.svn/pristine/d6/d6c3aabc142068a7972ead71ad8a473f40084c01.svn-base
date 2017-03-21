package cn.com.qytx.hotline.phonetask.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.qytx.hotline.phonetask.domain.PhoneTask;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.base.query.Page;
import cn.com.qytx.platform.base.query.Pageable;

/**
 * 功能:电话任务的数据处理类
 * 版本: 1.0
 * 开发人员: 彭小东
 * 创建日期: 2014-9-16
 * 修改日期: 2014-9-16
 * 修改列表:
 */
@Repository
public class PhoneTaskDao extends BaseDao<PhoneTask, Integer> implements Serializable
{

    /**
     * 描述含义
     */
    private static final long serialVersionUID = 1L;

    /**
     * 功能：根据条件分页查询任务
     * @param page
     * @param vo
     * @return
     */
    public Page<PhoneTask> findPageByVo(Pageable page, int seatId, PhoneTask vo)
    {
        StringBuffer sb = new StringBuffer(" isDelete=0 ");
        List<Object> params = new ArrayList<Object>();
        if (seatId > 0)
        {
            sb.append(" and seatUserIds like ?");
            params.add("%," + seatId + ",%");
        }
        if (vo != null)
        {
            if (vo.getTaskStateVo() != null && !"".equals(vo.getTaskStateVo()) && !"-1".equals(vo.getTaskStateVo()))
            {
                sb.append(" and taskState in (").append(vo.getTaskStateVo()).append(")");
            }
            if (vo.getTaskName() != null && !"".equals(vo.getTaskName()))
            {
                sb.append(" and taskName like ?");
                params.add("%" + vo.getTaskName() + "%");
            }
        }

        return super.dataFilter().findAll(sb.toString(), page, params.size() > 0 ? params.toArray() : null);
    }

    /**
     * 功能：根据hql语句更新数据外呼任务
     * @param string
     * @param array
     */
    public void updatePhoneTaskByHql(String hql, Object[] array)
    {
        String jpdl = "update PhoneTask set " + hql + "where isDelete=0 and vid=?";
        super.executeQuery(jpdl,array);
    }

    /**
     * 功能：修改外呼任务的状态
     * @param questionnareId 试卷id
     * @param statue 任务状态
     */
    public void updateState(Integer questionnareId, Integer statue)
    {
        String hql = "";
        if (statue == 2)
        {
            hql = "update PhoneTask set taskState =1 where questionnaire.id = ?1";
        }
        else
        {
            hql = "update PhoneTask set taskState =2 where questionnaire.id = ?1";
        }
        super.executeQuery(hql, questionnareId);
    }

}
