package com.heisenberg.common.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.heisenberg.base.dao.SpringJDBCBaseDao;

public class AuthorityInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	private SpringJDBCBaseDao baseDao;
	@Override    
    public boolean preHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler) throws Exception {    
		String operationBy = request.getParameter("operationBy");
		String topTeamId = request.getParameter("topTeamId");
		String Sql = " select count(1) from (select * from tb_team start with id=? connect by prior id = parentId ) t where leaderId=? ";
		if (operationBy == null && topTeamId == null){
			return true;
		}
		int count = (int) baseDao.queryUniqueObject(Sql, new Object[]{topTeamId,operationBy},Integer.class);
		if (count > 0){
			return true;
		}else{
			request.getRequestDispatcher("noAuthority.action").forward(request, response);  
			return false;
		}
        
    }    
}
