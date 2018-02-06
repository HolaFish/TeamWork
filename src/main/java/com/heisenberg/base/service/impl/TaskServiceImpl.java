package com.heisenberg.base.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.heisenberg.base.dao.SpringJDBCBaseDao;
import com.heisenberg.base.model.Task;
import com.heisenberg.base.service.TaskService;
import com.heisenberg.common.resultmodel.BaseResult;
import com.heisenberg.common.util.ObjectRelationalMapper;
@Service 
public class TaskServiceImpl implements TaskService {
	@Autowired
	private SpringJDBCBaseDao baseDao;
	@Value("${QUICK_TASK}")
	private String QUICK_TASK;
	@Value("${TYPE_SUCCESS}")
	private String TYPE_SUCCESS;
	@Value("${SUCCESS}")
	private String SUCCESS;
	/**
	 * 
	 * @描述：TODO(根据查询条件获取任务列表)
	 * @param userId 用户id
	 * @param topTeamId 顶级团队id
	 * @param isOngoing 是否是正在进行的任务
	 * @param orderBy 排序规则
	 * @param isCharge 是否是userid负责的项目
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午5:30:24
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午5:30:24
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Task> getTasks(String userId,
			String topTeamId, String isOngoing, String orderBy, String isCharge, String isCreate) {
		String Sql = " select * from tb_task t ";
		if (StringUtils.isBlank(isCreate) && StringUtils.isBlank(isCharge)){//全部
			Sql += " left join tb_task_member tm "
					+ " on t.id = tm.id "
					+ " where (t.leader ='"+userId+"' "
					+ " or t.createPeople = '"+userId+"' "
					+ " or tm.member = '"+userId +"') ";
		}else{
			if ("1".equals(isCreate)){
				Sql += " where t.createPeople = '"+userId+"'"; //我创建的
			}else{
				if ("1".equals(isCharge)){
					Sql += " where t.leader  = '"+userId + "'"; //我负责的
				}else if ("0".equals(isCharge)){
					Sql += " left join tb_task_member tm on t.id = tm.id where tm.member='"+userId+"'"; //我参与的
				}
			}
		}
		if ("1".equals(isOngoing)){
			Sql +=" and t.status = 0 "; //正在进行的
		}else if ("0".equals(isOngoing)){
			Sql += " and t.status = 1 "; //已经完成的
		}
		Sql += " and t.teamId ='"+ topTeamId +"' order by t." + orderBy;
		List<Task> tasks = baseDao.queryToList(Sql, null, new ObjectRelationalMapper("com.heisenberg.base.model.Task"));
		
		return tasks;
	}
	/**
	 * 
	 * @描述：TODO(添加快速任务)
	 * @param task
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午5:30:21
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午5:30:21
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public BaseResult<Task> addQuickTask(Task task) {
		//生成id
		String id = UUID.randomUUID().toString();
		task.setId(id);
		String Sql = " insert into tb_task(id,name,leader,description,startTime,endtime,sort,teamId,type,createPeople)"
				+ " values( '"+ id +"',"
				+"'"+task.getName()+"',"
				+"'"+task.getLeader() +"',"
				+"'"+task.getDescription() +"',"
				+"'"+task.getStartTime() + "',"
				+"'"+task.getEndTime()+ "',"
				+"9999,"
				+"'" + task.getTeamId() +"',"
				+"'" + QUICK_TASK + "',"
				+"'"+task.getCreatePeople()+"')";
		baseDao.insert(Sql, null);
		BaseResult<Task> result = new BaseResult<Task>(TYPE_SUCCESS,SUCCESS);
		result.setObj(task);
		return result ;
	}
	/**
	 * 
	 * @描述：TODO(将任务分配给组内成员)
	 * @param taskId 任务id
	 * @param members 成员列表
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午5:42:05
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午5:42:05
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public void distributQuickTask(String taskId, String[] members) {
		if (members != null && members.length > 0){
			String Sql = " insert into tb_task_member(id,member)";
			for (int i = 0; i < members.length; i++){
				Sql += "select '"+taskId + "' as id,'"+members[i] + "' as member from dual ";
				if (i < members.length-1){
					Sql += " union all ";
				}
			}
			baseDao.insert(Sql, null);
		}
	
		
	}
	/**
	 * 
	 * @描述：TODO(获取快速人的人员分工情况)
	 * @param taskId 任务id
	 * @param topTeamId 顶级团队id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午10:20:09
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午10:20:09
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public List<Object> getQuickTaskMemberAndCanDistribut(String taskId,
			String topTeamId) {
		String Sql = "with isthismember as " +
						" (select bi.id       as memberid, " + 
						"         bi.name     as membername, " + 
						"         bi.email    as memberemail, " + 
						"         tm.depid    as teamid, " + 
						"         t.name      as teamname, " + 
						"         t.teamlevel as teamlevel, " + 
						"         1           as isthis " + 
						"    from tb_baseInfo bi " + 
						"    left join tb_teammember tm " + 
						"      on bi.id = tm.memberid " + 
						"    left join tb_team t " + 
						"      on tm.depid = t.id " + 
						"    left join tb_task_member tam " + 
						"      on bi.id = tam.member " + 
						"   where tam.id = ?), " + 
						"allmembers as " + 
						" (select bi.id       as memberid, " + 
						"         bi.name     as membername, " + 
						"         bi.email    as memberemail, " + 
						"         tm.depid    as teamid, " + 
						"         t.name      as teamname, " + 
						"         t.teamlevel as teamlevel, " + 
						"         0           as isthis " + 
						"    from tb_baseinfo bi " + 
						"    left join tb_teammember tm " + 
						"      on bi.id = tm.memberid " + 
						"    left join tb_team t " + 
						"      on tm.depid = t.id " + 
						"   where tm.depid in " + 
						"         (select t.id " + 
						"            from tb_team t " + 
						"           start with t.id = ? " + 
						"          CONNECT BY PRIOR t.ID = t.parentid) " + 
						"  union " + 
						"  select bi.id       as memberid, " + 
						"         bi.name     as membername, " + 
						"         bi.email    as memberemail, " + 
						"         t.id        as teamid, " + 
						"         t.name      as teamname, " + 
						"         t.teamlevel as teamlevel, " + 
						"         0           as isthis " + 
						"    from tb_baseinfo bi " + 
						"    left join tb_team t " + 
						"      on bi.id = t.leaderid " + 
						"   where t.id in (select t.id " + 
						"                    from tb_team t " + 
						"                   start with t.id = ? " + 
						"                  CONNECT BY PRIOR t.ID = t.parentid)) " + 
						"select * " + 
						"  from isthismember " + 
						"union " + 
						"select * " + 
						"  from allmembers " + 
						" where memberid not in (select memberid from isthismember)";
		List<Map<String,Object>> members = baseDao.queryMapInList(Sql, new Object[]{taskId,topTeamId,topTeamId});
		//将成员安组分开
		Map<String,List<Map<String,Object>>> teamMembers = new HashMap<String,List<Map<String,Object>>>();
		for (Map<String,Object> teamMember : members){
			String teamId = teamMember.get("TEAMID").toString();
			if (teamMembers.containsKey(teamId)){
				teamMembers.get(teamId).add(teamMember);
			}else{
				List<Map<String,Object>> teams = new ArrayList<Map<String,Object>>();
				teams.add(teamMember);
				teamMembers.put(teamId, teams);
			}
		}
		List<Object> TeamMembers = Arrays.asList(teamMembers.values().toArray());
		return TeamMembers;
	}
	/**
	 * 
	 * @描述：TODO(根据id获取任务详情)
	 * @param id 任务id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月6日下午3:39:14
	 * @修改人  ：lrz
	 * @修改时间：2017年5月6日下午3:39:14
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Task getTaskById(String id) {
		String Sql = " select * from tb_task where id=? ";
		List<Task> tasks = baseDao.queryToList(Sql, new Object[]{id}, new ObjectRelationalMapper("com.heisenberg.base.model.Task"));
		if (tasks != null && tasks.size() > 0){
			return tasks.get(0);
		}
		return null;
	}
}
