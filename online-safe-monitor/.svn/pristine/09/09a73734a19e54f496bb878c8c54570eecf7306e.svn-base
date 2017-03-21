package cn.com.qytx.hotline.customercall.dao;

import java.sql.Timestamp;

import org.springframework.stereotype.Repository;

import cn.com.qytx.hotline.customercall.domain.CclSn;
import cn.com.qytx.hotline.ivr.domain.CallCenterConst;
import cn.com.qytx.platform.base.dao.BaseDao;
import cn.com.qytx.platform.utils.datetime.DateTimeUtil;
/**
 * 功能: 工单编号维护Dao
 * 版本: 1.0
 * 开发人员: 李华伟
 * 创建日期: 2013-10-23
 * 修改日期: 2013-10-23
 * 修改列表:
 */
@Repository
public class CclSnDao extends BaseDao<CclSn, Integer>
{
    /**
     * 功能：获取本月未使用的工单编号
     * @param source 来源
     * @return CclSn 工单编号
     */
	public CclSn findUnUseCclSn(String source)
    {
        // 获取当前月份
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String yearMonth = DateTimeUtil.timestampToString(now, CallCenterConst.TIME_FORMAT_STR_Y_M);
        return (CclSn) super.findOne( " yearMonth = ? and source = ? order by sn asc", yearMonth, source);
    }
}
