package cn.com.qytx.workflow.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.com.qytx.platform.org.domain.UserInfo;

/**
 * Created by izerui.com on 14-5-8.
 */
public enum TaskQueryType implements IQuerySQLGenerator{

    CANDIDATE{
        @Override
        public String toHQL() {
            String sql = "select distinct task.executionId from org.jbpm.pvm.internal.task.ParticipationImpl as participant" +
            " join participant.task as task where participant.type = 'candidate'   and participant.userId = '"+getSessionUser().getUserId()+"'   and task.assignee is null ";
            return sql;
        }
    },COMPLETE{
        @Override
        public String toHQL() {
            return "select distinct ht.executionId from org.jbpm.pvm.internal.history.model.HistoryTaskImpl as ht where  ht.assignee = '"+getSessionUser().getUserId()+"'";
        }
    };

    /**
     * 获取session中的用户信息
     * @return
     */
    protected UserInfo getSessionUser(){
        //for local test
        if(Authentication.getAuthor()!=null){
            return Authentication.getAuthor();
        }
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        return (UserInfo)request.getSession().getAttribute("adminUser");
    }

}

interface IQuerySQLGenerator{
    String toHQL();
}