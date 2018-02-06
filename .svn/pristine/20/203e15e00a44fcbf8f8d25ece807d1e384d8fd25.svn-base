package com.heisenberg.aop.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.heisenberg.base.dao.SpringJDBCBaseDao;
import com.heisenberg.base.model.Position;
import com.heisenberg.base.model.Project;
import com.heisenberg.base.model.Station;
import com.heisenberg.base.model.Task;
import com.heisenberg.base.model.TaskGroup;
import com.heisenberg.base.model.Team;
import com.heisenberg.base.model.UserBaseInfo;
import com.heisenberg.common.resultmodel.BaseResult;
@Component
@Aspect
public class Log {
	@Autowired
	private SpringJDBCBaseDao baseDao;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Value("${TYPE_SUCCESS}")
	private String TYPE_SUCCESS;
	/**
	 * 
	 * @描述：TODO(为添加团队记录日志)
	 * @param team
	 * @param createBy
	 * @param topTeamId
	 * @param rtv
	 * @创建人  ：lrz
	 * @创建时间：2017年5月12日上午2:49:23
	 * @修改人  ：lrz
	 * @修改时间：2017年5月12日上午2:49:23
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	@AfterReturning(value="execution(* com.heisenberg.base.action.impl.*ControllerImpl.add*( .. )) "
			+ " or execution(* com.heisenberg.base.action.impl.*ControllerImpl.modify*( .. )) ", returning="rtv")
	public void recordAddAndModify(JoinPoint joinPoint ,BaseResult rtv){
		if (TYPE_SUCCESS.equals(rtv.getResultType())){
			String operationTime = sdf.format(new Date());
			String Sql = " insert into tb_log(operationType,target,targetId,targetName,userId,operationTime,teamId)values(?,?,?,?,?,?,?)";
			String methodName = joinPoint.getSignature().getName();
			String[] method = methodName.split("(?<!^)(?=[A-Z])");
			Object[] args = joinPoint.getArgs();
			String targetId = "";
			String targetName = "";
			switch (method[1]){
			case "Team" : //团队
				if (rtv.getObj() instanceof Team){
					Team team = (Team) rtv.getObj();
					targetId = team.getId();
					targetName = team.getName();
				}
				break;
			case "Position" : //职级
				if (rtv.getObj() instanceof Position){
					Position position = (Position) rtv.getObj();
					targetId = position.getId();
					targetName = position.getName();
				}
				break;
			case "Station" : //岗位
				if (rtv.getObj() instanceof Station){
					Station station = (Station) rtv.getObj();
					targetId = station.getId();
					targetName = station.getName();
				}
				break;
			case "Member" : //团队成员
				if (rtv.getObj() instanceof UserBaseInfo){
					UserBaseInfo memberInfo = (UserBaseInfo) rtv.getObj();
					targetId = memberInfo.getId();
					targetName = memberInfo.getName();
				}
				break;
			case "Project"://项目
				if(rtv.getObj() instanceof Project){
					Project project = (Project) rtv.getObj();
					targetId = project.getId();
					targetName = project.getName();
				}
				break;
			case "Taskgroup" : //任务分组
				if(rtv.getObj() instanceof TaskGroup){
					TaskGroup taskGroup = (TaskGroup) rtv.getObj();
					targetId = taskGroup.getId();
					targetName = taskGroup.getName();
				}
				break;
			case "Task" : //项目任务
				if(rtv.getObj() instanceof Task){
					Task task = (Task) rtv.getObj();
					targetId = task.getId();
					targetName = task.getName();
					if (StringUtils.isBlank(task.getProject())){
						method[1] = "Quicktask";
					}
				}
				break;
			case "Quicktask" ://快速任务
				if(rtv.getObj() instanceof Task){
					Task task = (Task) rtv.getObj();
					targetId = task.getId();
					targetName = task.getName();
				}
				break;
				default:break;
			}
			int length = args.length;
			baseDao.insert(Sql, new Object[]{method[0],method[1],targetId,targetName,args[length-2],operationTime,args[length-1]});
		}else{
			String methodName = joinPoint.getSignature().getName();
			System.out.println(methodName + " 失败");
		}
	}
	/**
	 * 
	 * @描述：TODO(在项目，任务，团队删除之前)
	 * @param joinPoint
	 * @创建人  ：lrz
	 * @创建时间：2017年5月12日下午9:18:39
	 * @修改人  ：lrz
	 * @修改时间：2017年5月12日下午9:18:39
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Before(value="execution(* com.heisenberg.base.action.impl.*ControllerImpl.delete*( .. ))")
	public void beforeDelete(JoinPoint joinPoint){
		String insertSql = " insert into tb_log(operationType,target,targetId,targetName,userId,operationTime,teamId)values(?,?,?,?,?,?,?)";
		String methodName = joinPoint.getSignature().getName();
		String[] method = methodName.split("(?<!^)(?=[A-Z])");
		Object[] args = joinPoint.getArgs();
		String targetId = args[0].toString();
		String tableName = "tb_" + method[1];
		if ("Taskgroup".equals(method[1])){
			tableName = "tb_project_group";
		}
		//获取删除对象的名称
		String sql = " select name from " + tableName + " where id=? ";
		String targetName = (String) baseDao.queryUniqueObject(sql, new Object[]{targetId}, String.class);
		int length = args.length;
		String operationBy = args[length-2].toString();
		String topTeamId = args[length -1 ].toString();
		String operationTime = sdf.format(new Date());
		//记录日志
		baseDao.insert(insertSql, new Object[]{method[0],method[1],targetId,targetName,operationBy,operationTime,topTeamId});
	}
}
