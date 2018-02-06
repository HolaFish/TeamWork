package com.heisenberg.base.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heisenberg.base.dao.SpringJDBCBaseDao;
import com.heisenberg.base.service.TeamDynamicService;
@Service
public class TeamDynamicServiceImpl implements TeamDynamicService{
	@Autowired
	private SpringJDBCBaseDao baseDao;
	@Override
	public List<Map<String, Object>> getLogs(String userId,String topTeamId, String logType,
			String queryParam, String date) {
		String Sql = "select bi.name as userName, l.* " +
						"  from TB_LOG l " + 
						" inner join tb_baseinfo bi " + 
						"    on userId = bi.id " + 
						" where l.teamid = '" + topTeamId + "'";
		if (!StringUtils.isBlank(logType)){
			//存在日志类型
			switch(logType){
			case "Task" :
				Sql += " and (target='Task' or target='Quicktask') ";
				break;
			case "Project":
				Sql +=" and target='Project' ";
				break;
			case "Team" :
				Sql += "and (target='Team' or target='Position' or target = 'Station' or target='Invite' or target = 'InviteAnwser' or target='Member')";
				break;
			case "File":
				Sql += " and target='File' ";
				break;
				default:break;
			}
		}
		if (!StringUtils.isBlank(date)){
			Sql += " and operationTime='"+date + "' ";
		}
		if ("myself".equals(queryParam)){
			Sql += " and userId = '" + userId +"'"; //我操作的日志
		}else if ("subordinates".equals(queryParam)){
			Sql +=" and userId in ( select id from tb_leader where leaderId='"+userId+"')"; //下属操作的日志
		} 
		return baseDao.queryMapInList(Sql, null);
	}
	
	@Override
	public List<Map<String, Object>> getApplyJoinLogs(String userId, String topTeamId) {
		String Sql = "select t.*, bi.name as userName" +
						"  from TB_LOG t " + 
						"  left join tb_baseInfo bi " + 
						"    on t.userid = bi.id " + 
						" where t.teamid in (SELECT id " + 
						"                      FROM tb_team " + 
						"                     START WITH id = ? "+ 
						"                    CONNECT BY PRIOR ID = PARENTID) " + 
						"   and t.operationtype = 'apply'";
		
		return baseDao.queryMapInList(Sql, new Object[]{topTeamId});
	}
	
}
