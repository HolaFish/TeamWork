package com.heisenberg.base.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.heisenberg.base.dao.SpringJDBCBaseDao;
import com.heisenberg.base.model.Project;
import com.heisenberg.base.model.ProjectDetail;
import com.heisenberg.base.model.ProjectInfoVO;
import com.heisenberg.base.model.Task;
import com.heisenberg.base.model.TaskGroup;
import com.heisenberg.base.service.ProjectService;
import com.heisenberg.common.resultmodel.BaseResult;
import com.heisenberg.common.util.CommonComparator;
import com.heisenberg.common.util.ObjectRelationalMapper;
@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private SpringJDBCBaseDao baseDao;
	@Value("${SUCCESS}")
	private String SUCCESS;
	@Value("${TYPE_SUCCESS}")
	private String TYPE_SUCCESS;
	@Value("${TYPE_WARM}")
	private String TYPE_WARM;
	@Value("${TYPE_ERROR}")
	private String TYPE_ERROR;
	@Value("${PROJECT_EXIST}")
	private String PROJECT_EXIST;
	@Value("${GROUPNAME_EXIST}")
	private String GROUPNAME_EXIST;
	@Value("${TASK_EXIST}")
	private String TASK_EXIST;
	@Value("${NORMAL_TASK}")
	private String NORMAL_TASK;
	/**
	 * 
	 * @描述：TODO(添加项目)
	 * @param project
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月29日下午2:22:04
	 * @修改人  ：lrz
	 * @修改时间：2017年4月29日下午2:22:04
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public BaseResult addProject(ProjectInfoVO projectVo,String[] teams, String[] members) {
		BaseResult result = new BaseResult();
		//判断项目名称是否重复
		String Sql = " select count(*) from tb_project where name=? ";
		int count = (int) baseDao.queryUniqueObject(Sql, new Object[]{projectVo.getTitle()}, Integer.class);
		if (count > 0 ){//项目名称重复
			result.setResultType(TYPE_WARM);
			result.setMessage(PROJECT_EXIST);
			return result;
		}
		/*
		 * 记录项目信息
		 */
		String id = UUID.randomUUID().toString();//生成id
		
		Sql = " insert into tb_project(id,name,leaderid,description,startTime,endTime,teamId)values(?,?,?,?,?,?,?) ";
		baseDao.insert(Sql, new Object[]{id,projectVo.getTitle(),projectVo.getLeader(),projectVo.getDescription(),projectVo.getStartTime(),projectVo.getEndTime(),projectVo.getTeamId()});
		/*
		 * 记录负责团队信息
		 */
		this.distributProjectTeam(id, teams);
		/*
		 * 记录负责成员信息
		 */
		this.distributProjectMember(id, members);
		Project project = new Project();
		project.setId(id);
		project.setName(projectVo.getTitle());
		result.setObj(project);
		result.setResultType(TYPE_SUCCESS);
		result.setMessage(SUCCESS);
		return result;
	}
	/**
	 * 
	 * @描述：TODO(为项目分配负责成员)
	 * @param projectID 项目id
	 * @param teams 负责团队
	 * @创建人  ：lrz
	 * @创建时间：2017年4月29日下午2:51:30
	 * @修改人  ：lrz
	 * @修改时间：2017年4月29日下午2:51:30
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public void distributProjectTeam(String projectID,String[] teams) {
		String Sql = " insert into tb_project_team(projectid,team) ";
		String params = " select * from ("; 
		for (int i = 0 ; i < teams.length; i++){
			
			if ( i == teams.length -1 ){
				params += " select '" + projectID +"','" + teams[i] + "' from dual)";
			}else{
				params += " select '" + projectID +"','" + teams[i] + "' from dual union all ";
			}
		}
		Sql += params;
		baseDao.insert(Sql, null);
	}
	/**
	 * 
	 * @描述：TODO(为项目分配负责成员)
	 * @param projectID 项目id
	 * @param members 负责成员列表
	 * @创建人  ：lrz
	 * @创建时间：2017年4月29日下午2:51:56
	 * @修改人  ：lrz
	 * @修改时间：2017年4月29日下午2:51:56
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public void distributProjectMember(String projectID,String[] members) {
		String Sql = " insert into tb_project_member(projectid,member) ";
		String params = " select * from ("; 
		for (int i = 0 ; i < members.length; i++){
			
			if ( i == members.length -1 ){
				params += " select '" + projectID +"','" + members[i] + "' from dual)";
			}else{
				params += " select '" + projectID +"','" + members[i] + "' from dual union all ";
			}
		}
		Sql += params;
		baseDao.insert(Sql, null);
	}
	/**
	 * 
	 * @描述：TODO(按条件获取项目列表)
	 * @param id 用户id
	 * @param isOngoing 是否正在进行
	 * @param isCharge 是否是负责人
	 * @param orderBy 排列方式
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月30日上午2:57:10
	 * @修改人  ：lrz
	 * @修改时间：2017年4月30日上午2:57:10
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public List<Map<String, Object>> getProjectList(String id,String topTeamId,
			String isOngoing, String isCharge, String orderBy) {
		String Sql =  " select distinct p.id as projectID,p.name as projectName,bi.name as leader,p.createtime,p.endtime,p.updatetime "
				+ " from tb_project p "
				+ " left join tb_baseinfo bi on p.leaderid=bi.id ";
		if (!StringUtils.isBlank(isCharge)){
			if ("0".equals(isCharge)){
				Sql += " left join tb_project_member m on p.id=m.projectid "
						+ " where m.member = '" +id + "' and p.teamId='"+topTeamId + "' ";
			}else{
				Sql += " where p.leaderid = '" + id + "' and p.teamId='"+topTeamId + "' "; 
			}
		}else {
			Sql += " left join tb_project_member m on p.id=m.projectid where (p.leaderid = '" + id + "'or m.member = '" + id + "')";
		}
		if (!StringUtils.isBlank(isOngoing)){
			 if ("0".equals(isOngoing)){
				 Sql += " and p.isongoing=0 ";
			 }else{
				 Sql +=" and p.isongoing=1 ";
			 }
		}
		
		switch(orderBy){
			case "createTime":
				Sql += " order by p.createTime desc";
				break;
			case "endTime":
				Sql += " order by p.endtime desc ";
				break;
			case "updateTime":
				Sql += " order by p.updatetime desc ";
		}
		List<Map<String, Object>> projects = baseDao.queryMapInList(Sql, null);
		return projects;
	}
	/**
	 * 
	 * @描述：TODO(添加任务分组)
	 * @param groupName 分组名
	 * @param projectId 项目id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月1日下午5:27:50
	 * @修改人  ：lrz
	 * @修改时间：2017年5月1日下午5:27:50
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public BaseResult<TaskGroup> addTaskGroup(String groupName, String projectId) {
		BaseResult<TaskGroup> result = new BaseResult<TaskGroup>();
		//判断分组名是否重复
		String Sql = " select count(*) from tb_project_group where project=? and name=? ";
		int count = (int) baseDao.queryUniqueObject(Sql, new Object[]{projectId,groupName}, Integer.class);
		if (count > 0 ){
			result.setMessage(GROUPNAME_EXIST);
			result.setResultType(TYPE_WARM);
			return result;
		}
		/*
		 * 添加任务分组
		 */
		String id = UUID.randomUUID().toString();//id
		Sql = "insert into tb_project_group " +
						"  (id, project, name, sort) " + 
						"  select '" + id + "', '"+ projectId +"', '"+ groupName +"', nvl(max(sort) + 1, 1) " + 
						"    from tb_project_group t " + 
						"   where t.project = '"+projectId+"' ";
		baseDao.insert(Sql, null);
		TaskGroup taskGroup = new TaskGroup();
		taskGroup.setId(id);
		taskGroup.setName(groupName);
		result.setObj(taskGroup);
		result.setMessage(SUCCESS);
		result.setResultType(TYPE_SUCCESS);
		return result;
	}
	/**
	 * 
	 * @描述：TODO(根据项目id获取项目详情)
	 * @param id 项目id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月1日下午7:17:33
	 * @修改人  ：lrz
	 * @修改时间：2017年5月1日下午7:17:33
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ProjectDetail getProjectById(String id) {
		ProjectDetail project = new ProjectDetail();
		//获取项目的任务列表
		String Sql = "select 	t.id    as taskId, " +
						"       t.name  as taskName, " + 
						"       t.sort  as taskSort, " + 
						"       t.status  as status, " + 
						"       pg.id   as groupId, " + 
						"       pg.name as groupName, " + 
						"		pg.sort as groupSort,"+		
						"       p.id    as projectId, " + 
						"       p.name  as projectName " + 
						"  from tb_task t " + 
						"  left join tb_project_group pg on t.groupid = pg.id " + 
						"  left join tb_project p on t.project = p.id " + 
						" where t.project = ? "
						+ " order by t.sort asc ";

		List<Map<String,Object>> tasks = baseDao.queryMapInList(Sql, new Object[]{id});
		//将任务按任务分组分类
		Map<String,TaskGroup> groupsMap = new HashMap<String,TaskGroup>();
		String existGroupSql = "";
		for (Map<String, Object> task : tasks){
			String groupId = task.get("GROUPID").toString();
			Task taskObj = new Task();
			taskObj.setId(task.get("TASKID").toString());
			taskObj.setName(task.get("TASKNAME").toString());
			taskObj.setSort(Integer.parseInt(task.get("TASKSORT").toString()));
			taskObj.setStatus(task.get("STATUS").toString());
			if (groupsMap.containsKey(groupId)){
				groupsMap.get(groupId).getTasks().add(taskObj);
			}else{
				List<Task> taskObjs = new ArrayList<Task>();
				taskObjs.add(taskObj);
				TaskGroup taskGroup = new TaskGroup();
				taskGroup.setTasks(taskObjs);
				taskGroup.setId(task.get("GROUPID").toString());
				taskGroup.setName(task.get("GROUPNAME").toString());
				taskGroup.setSort(Integer.parseInt(task.get("GROUPSORT").toString()));
				groupsMap.put(groupId, taskGroup);
				existGroupSql += " and pg.Id !='"+task.get("GROUPID").toString() + "' "; //在后续查找没有任务的任务分组中排除该分组
			}
		}
		//将map的values转为list
		List<TaskGroup> groupArrayList = Arrays.asList(groupsMap.values().toArray(new TaskGroup[groupsMap.size()]));
		/*
		 * Arrays.asLisvt() 返回java.util.Arrays$ArrayList， 而不是ArrayList
		 * 这个类没有实现addAll()方法，所以要实例化一个ArrayList，以便后续合并ArrayList
		 */
		List<TaskGroup> groups = new ArrayList(groupArrayList);
		/*
		 * 获取没有任务的任务分组
		 */
		Sql = "select p.id    as projectid, " +
				"       p.name  as projectName, " + 
				"       pg.id   as groupId, " + 
				"       pg.name as groupName, " +
				"       pg.sort as groupSort " + 
				"  from tb_project_group pg " + 
				"  left join tb_project p on pg.project = p.id " + 
				" where p.id = ?"
				+existGroupSql;
		List<Map<String,Object>> groupsNoTaskInMap = baseDao.queryMapInList(Sql, new  Object[]{id});
		List<TaskGroup> groupNoTask= new ArrayList<TaskGroup>();
		//将map转化为TaskGroup对象
		for (Map<String,Object> group : groupsNoTaskInMap){
			TaskGroup tGroup = new TaskGroup();
			tGroup.setId(group.get("GROUPID").toString());
			tGroup.setName(group.get("GROUPNAME").toString());
			tGroup.setSort(Integer.parseInt(group.get("GROUPSORT").toString()));
			groupNoTask.add(tGroup);
		}
		//合并两个查询结果
		groups.addAll(groupNoTask);
		//赋值项目id和项目名
		if (tasks != null && tasks.size() > 0){
			String projectId = tasks.get(0).get("PROJECTID").toString();
			String projectName = tasks.get(0).get("PROJECTNAME").toString();
			project.setId(projectId);
			project.setName(projectName);
			
		}else if (groupsNoTaskInMap != null && groupsNoTaskInMap.size() > 0){
			String projectId = groupsNoTaskInMap.get(0).get("PROJECTID").toString();
			String projectName = groupsNoTaskInMap.get(0).get("PROJECTNAME").toString();
			project.setId(projectId);
			project.setName(projectName);
		}
		
		/*
		 * 为任务和任务分组排序
		 */
		CommonComparator comparator = new CommonComparator();
		//排序任务
		for (TaskGroup tg : groups){
			if (tg.getTasks() != null){
				Collections.sort(tg.getTasks(), comparator);
			}
			
		}
		//排序任务分组
		Collections.sort(groups, comparator);
		project.setTaskGroups(groups);
		return project;
	}
	/**
	 * 
	 * @描述：TODO(根据项目id获取可参与项目的人员列表)
	 * @param projectId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月2日上午12:49:51
	 * @修改人  ：lrz
	 * @修改时间：2017年5月2日上午12:49:51
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public List<Object> getMembersCanDistribut(String projectId) {
		String Sql ="select bi.id as memberId, bi.name as memberName, t.id as teamId, t.name as teamName " +
						"  from tb_baseInfo bi "
						+ "left join tb_project_member pm"
						+ " on bi.id = pm.member " +
						"  left join tb_teammember tm " + 
						"    on bi.id = tm.memberid " + 
						"  left join tb_team t " + 
						"    on tm.depid = t.id " + 
						"  left join tb_project_team pt " + 
						"    on t.id = pt.team " + 
						" where pt.projectid = ? "+
						" group by bi.id,bi.name,t.id,t.name "+
						"union " +
						"select bi.id   as memberId, " + 
						"      bi.name as memberName, " + 
						"      t.id    as teamId, " + 
						"      t.name  as teamName " + 
						" from tb_baseInfo bi " + 
						" left join tb_project_member pm " + 
						"   on bi.id = pm.member " + 
						" inner join tb_team t\n" + 
						"      on pm.member = t.leaderid " + 
						"      where pm.projectid =  ?";

		List<Map<String,Object>> members = baseDao.queryMapInList(Sql, new Object[]{projectId,projectId});
		List<Object> result = new ArrayList<Object>();
		//按组分开
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
		result = Arrays.asList(teamMembers.values().toArray());
		return result;
	}
	/**
	 * 
	 * @描述：TODO(添加任务)
	 * @param task
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月2日下午1:58:42
	 * @修改人  ：lrz
	 * @修改时间：2017年5月2日下午1:58:42
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public BaseResult<Task> addTask(Task task) {
		BaseResult<Task> result = new BaseResult<Task>();
		//判断任务分组中任务名是否重复
		String Sql = " select count(*) from tb_task where project=? and groupid=? and name=? ";
		int count = (int) baseDao.queryUniqueObject(Sql, new Object[]{task.getProject(),task.getGroupId(),task.getName()}, Integer.class);
		if (count > 0 ){
			result.setResultType(TYPE_WARM);
			result.setMessage(TASK_EXIST);
			return result;
		}
		/*
		 * 插入新任务
		 */
		String id = UUID.randomUUID().toString();//生成id
		task.setId(id);
		Sql = " insert into tb_task(id,name,project,groupid,leader,description,startTime,endtime,sort,teamId,type,createPeople)"
				+ " values( '"+ id +"' ,"
				+"'"+task.getName()+"' ,"
				+"'"+task.getProject()+"' ,"
				+"'"+task.getGroupId()+"' ,"
				+"'"+task.getLeader() +"' ,"
				+"'"+task.getDescription() +"' ,"
				+"'"+task.getStartTime() + "' ,"
				+"'"+task.getEndTime()+ "' ,"
				+"9999,"
				+ "'"+task.getTeamId() + "',"
				+"'" + NORMAL_TASK+"',"
				+"'"+task.getCreatePeople() + "')";
		//插入数据
		baseDao.insert(Sql, null);
		result.setMessage(SUCCESS);
		result.setResultType(TYPE_SUCCESS);
		result.setObj(task);
		return result;
	}
	/**
	 * 
	 * @描述：TODO(分配任务)
	 * @param taskId 任务id
	 * @param members  人员列表
	 * @创建人  ：lrz
	 * @创建时间：2017年5月2日下午2:20:17
	 * @修改人  ：lrz
	 * @修改时间：2017年5月2日下午2:20:17
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public void distributTask(String taskId, String[] members) {
		String Sql = " insert into tb_task_member(id,member)";
		for (int i = 0; i < members.length; i++){
			Sql += "select '"+taskId + "' as id,'"+members[i] + "' as member from dual ";
			if (i < members.length-1){
				Sql += " union all ";
			}
		}
		baseDao.insert(Sql, null);
	}
	/**
	 * 
	 * @描述：TODO(删除任务分组)
	 * @param groupId 任务分组id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月3日下午11:50:21
	 * @修改人  ：lrz
	 * @修改时间：2017年5月3日下午11:50:21
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult deleteTaskGroup(String groupId) {
		//删除任务分组下的任务的分工信息
		String Sql = " delete from tb_task_member where id in (select id from tb_task where groupid=?)";
		baseDao.delete(Sql, new Object[]{groupId});
		//删除任务
		Sql = " delete from tb_task where groupid=? ";
		baseDao.delete(Sql, new Object[]{groupId});
		//删除任务分组
		Sql = " delete from tb_project_group where id = ? ";
		baseDao.delete(Sql, new Object[]{groupId});
		return new BaseResult(TYPE_SUCCESS,SUCCESS);
	}
	/**
	 * 
	 * @描述：TODO(删除任务)
	 * @param taskId 任务id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月4日上午12:05:59
	 * @修改人  ：lrz
	 * @修改时间：2017年5月4日上午12:05:59
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult delteTask(String taskId) {
		//删除任务分组下的任务的分工信息
		String Sql = " delete from tb_task_member where id =?";
		baseDao.delete(Sql, new Object[]{taskId});
		//删除任务
		Sql = " delete from tb_task where id=? ";
		baseDao.delete(Sql, new Object[]{taskId});
		return new BaseResult(TYPE_SUCCESS,SUCCESS);
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
	/**
	 * 
	 * @描述：TODO(获取任务的参与人员和可参与人员)
	 * @param id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月6日下午3:39:34
	 * @修改人  ：lrz
	 * @修改时间：2017年5月6日下午3:39:34
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public List<Object> getTaskMemberAndCanDistribut(String id) {
		String Sql = "with tm as " +
						" (select bi.id as memberId, " + 
						"         bi.name as memberName, " + 
						"         t.id as teamId, " + 
						"         t.name as teamName, " + 
						"         '1' as isthis " + 
						"    from Tb_Baseinfo bi " + 
						"   inner join tb_task_member tam " + 
						"      on bi.id = tam.member " + 
						"   inner join tb_task ta " + 
						"      on tam.id = ta.id " + 
						"   inner join tb_project_team pt " + 
						"      on ta.project = pt.projectid " + 
						"   inner join tb_teammember tm " + 
						"      on pt.team = tm.depid " + 
						"     and tam.member = tm.memberid " + 
						"   inner join tb_team t " + 
						"      on tm.depid = t.id " + 
						"   where tam.id = ?), " + 
						"pm as " + 
						" (select distinct bi.id as memberId, " + 
						"                  bi.name as memberName, " + 
						"                  t.id as teamId, " + 
						"                  t.name as teamName, " + 
						"                  '0' as isthis " + 
						"    from tb_baseInfo bi " + 
						"    left join tb_project_member pm " + 
						"      on bi.id = pm.member " + 
						"    left join tb_teammember tm " + 
						"      on bi.id = tm.memberid " + 
						"    left join tb_team t " + 
						"      on tm.depid = t.id " + 
						"    left join tb_project_team pt " + 
						"      on t.id = pt.team " + 
						"   where pt.projectid in " + 
						"         (select task.project " + 
						"            from tb_task task " + 
						"           where id = ?) " + 
						"  union " + 
						"  select bi.id as memberId, " + 
						"         bi.name as memberName, " + 
						"         t.id as teamId, " + 
						"         t.name as teamName, " + 
						"         '0' as isthis " + 
						"    from tb_baseInfo bi " + 
						"    left join tb_project_member pm " + 
						"      on bi.id = pm.member " + 
						"   inner join tb_team t " + 
						"      on pm.member = t.leaderid " + 
						"   where pm.projectid in " + 
						"         (select task.project " + 
						"            from tb_task task " + 
						"           where id = ?)) " + 
						"select * " + 
						"  from tm " + 
						"union " + 
						"select * from pm where pm.memberid not in (select memberid from tm)";

		List<Map<String,Object>> members = baseDao.queryMapInList(Sql, new Object[]{id,id,id});
		List<Object> result = new ArrayList<Object>();
		Map<String,List<Map<String,Object>>> teamMembers = new HashMap<String,List<Map<String,Object>>>();
		//成员按组分开
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
		result = Arrays.asList(teamMembers.values().toArray());
		return result;
	}
	/**
	 * 
	 * @描述：TODO(修改任务状态)
	 * @param id 任务id
	 * @param status 任务状态 0：未完成 1：完成
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日上午12:02:39
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日上午12:02:39
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public void changeTaskStatus(String id,String status) {
		String Sql = " update tb_task set status=? where id=? ";
		baseDao.update(Sql, new Object[]{status,id});
	}
	/**
	 * 
	 * @描述：TODO(获取项目信息)
	 * @param id 项目id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午3:28:46
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午3:28:46
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Project getProjectInfo(String id) {
		String Sql = " select * from tb_project where id=? ";
		List<Project> projects = baseDao.queryToList(Sql, new Object[]{id}, new ObjectRelationalMapper("com.heisenberg.base.model.Project"));
		return projects.size()==0 ?null :projects.get(0);
	}
	/**
	 * 
	 * @描述：TODO(获取项目的负责团队)
	 * @param projectId 项目id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午4:22:19
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午4:22:19
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public List<Map<String,Object>> getProjectTeams(String projectId) {
		String Sql = "select t.id, t.name " +
						"  from tb_project_team pt " + 
						"  left join tb_team t " + 
						"    on pt.team = t.id " + 
						" where pt.projectid = ? ";
		return baseDao.queryMapInList(Sql, new Object[]{projectId});
	}
	/**
	 * 
	 * @描述：TODO(获取某个团队参见某个项目的人员列表)
	 * @param projectId 项目id
	 * @param teamId 团队id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午9:10:25
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午9:10:25
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public List<Map<String, Object>> getProjectPartInMemberByTeamId(
			String projectId, String teamId) {
		String Sql = 	" with partMember as " +
						" (select t.name as teamname, " + 
						"         t.id as teamid, " + 
						"         bi.id as memberid, " + 
						"         bi.name as membername, " + 
						"         '1' as isthis " + 
						"    from tb_project_member pm " + 
						"    left join tb_baseinfo bi " + 
						"      on pm.member = bi.id " + 
						"    left join tb_teammember tm " + 
						"      on bi.id = tm.memberid " + 
						"    left join tb_team t " + 
						"      on tm.depid = t.id " + 
						"   where pm.projectid = ? " + 
						"     and t.id = ? " + 
						"     union " + 
						"     select t.name as teamname, " + 
						"         t.id as teamid, " + 
						"         bi.id as memberid, " + 
						"         bi.name as membername, " + 
						"         '1' as isthis " + 
						"        from tb_project_member pm " + 
						"    left join tb_baseinfo bi " + 
						"      on pm.member = bi.id " + 
						"      inner join tb_team t " + 
						"      on pm.member = t.leaderid " + 
						"      where pm.projectid=?), " + 
						"members as " + 
						" (select t.name as teamname, " + 
						"         t.id as teamid, " + 
						"         bi.id as memberid, " + 
						"         bi.name as membername, " + 
						"         '0' as isthis " + 
						"    from tb_baseinfo bi " + 
						"    left join tb_teammember tm " + 
						"      on bi.id = tm.memberid " + 
						"    left join tb_team t " + 
						"      on tm.depid = t.id " + 
						"   where t.id = ? " + 
						"   union " + 
						"   select  t.name as teamname, " + 
						"         t.id as teamid, " + 
						"         bi.id as memberid, " + 
						"         bi.name as membername, " + 
						"         '0' as isthis " + 
						"         from tb_baseinfo bi " + 
						"         left join tb_team t " + 
						"         on t.leaderid = bi.id " + 
						"         where t.id =  ? " + 
						"   ) " + 
						"select * " + 
						"  from partMember " + 
						"union " + 
						"select * from members where teamid not in (select teamid from partmember)";


		
		return baseDao.queryMapInList(Sql, new Object[]{projectId,teamId,projectId,teamId,teamId});
	}
	/**
	 * 
	 * @描述：TODO(修改项目信息)
	 * @param project
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午10:38:39
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午10:38:39
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public BaseResult modifyProject(Project project,String userId) {
		BaseResult result = new BaseResult();
		//判断项目名是否重复
		String Sql = "select count(*) " +
						"  from tb_project p " + 
						"  left join tb_project_member pm " + 
						"    on p.id = pm.projectid " + 
						" where p.name = 'TeamWor' " + 
						"   and p.id != ? " + 
						"   and (p.leaderid = ? or " + 
						"       pm.member = ?)";
		int count = (int) baseDao.queryUniqueObject(Sql, new Object[]{project.getId(),userId,userId}, Integer.class);
		if (count > 0) {
			result.setResultType(TYPE_WARM);
			result.setMessage(PROJECT_EXIST);
			return result;
		}
		//修改项目信息
		Sql = " update tb_project set name=?,leaderid=?,description=?,startTime=?,endTime=? where id=? ";
		baseDao.update(Sql, new Object[]{project.getName(),project.getLeaderid(),project.getDescription(),project.getStartTime(),project.getEndTime(),project.getId()});
		result.setMessage(SUCCESS);
		result.setResultType(TYPE_SUCCESS);
		result.setObj(project);
		return result;
	}
	/**
	 * 
	 * @描述：TODO(修改项目的团队信息)
	 * @param projectId 项目id
	 * @param teams 团队列表
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午10:54:12
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午10:54:12
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public void modifyProjectTeam(String projectId, String[] teams) {
		//删除原始记录
		String Sql = " delete from tb_project_team where projectId=? ";
		baseDao.delete(Sql, new Object[]{projectId});
		/*
		 * 记录负责团队信息
		 */
		this.distributProjectTeam(projectId, teams);
		
	}
	/**
	 * 
	 * @描述：TODO(修改项目的成员列表)
	 * @param projectId 项目id
	 * @param members 成员列表
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午10:54:39
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午10:54:39
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public void modifyProjectMember(String projectId, String[] members) {
		//删除原始记录
		String Sql = " delete from tb_project_member where projectId=?";
		baseDao.delete(Sql, new Object[]{projectId});
		/*
		 * 记录负责成员信息
		 */
		this.distributProjectMember(projectId, members);
		
	}
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult finishedProject(String projectId) {
		BaseResult result = new BaseResult();
		String Sql = " update tb_project set isongoing = 0 where id = ?";
		int count = baseDao.update(Sql, new Object[]{projectId});
		if (count > 0){
			result.setMessage(SUCCESS);
			result.setResultType(TYPE_SUCCESS);
			return result;
		}
		result.setMessage(TYPE_ERROR);
		result.setResultType("发生未知错误，请重试！");
		return null;
	}
	
	
}