package com.heisenberg.base.service;

import java.util.List;
import java.util.Map;

import com.heisenberg.base.model.Project;
import com.heisenberg.base.model.ProjectDetail;
import com.heisenberg.base.model.ProjectInfoVO;
import com.heisenberg.base.model.Task;
import com.heisenberg.base.model.TaskGroup;
import com.heisenberg.common.resultmodel.BaseResult;

public interface ProjectService {
	/**
	 * 
	 * @描述：TODO(添加新项目)
	 * @param project 项目信息
	 * @param teams 负责团队
	 * @param members 负责成员
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月29日下午2:48:33
	 * @修改人  ：lrz
	 * @修改时间：2017年4月29日下午2:48:33
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult addProject(ProjectInfoVO project,String[] teams, String[] members);
	
	/**
	 * 
	 * @描述：TODO(为项目分配团队)
	 * @param projectID 项目id
	 * @param teams 负责团队列表
	 * @创建人  ：lrz
	 * @创建时间：2017年4月29日下午2:48:55
	 * @修改人  ：lrz
	 * @修改时间：2017年4月29日下午2:48:55
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public void distributProjectTeam(String projectID, String[] teams);
	/**
	 * 
	 * @描述：TODO(为项目分配负责人员)
	 * @param projectID 项目id
	 * @param members 负责成员列表
	 * @创建人  ：lrz
	 * @创建时间：2017年4月29日下午2:49:26
	 * @修改人  ：lrz
	 * @修改时间：2017年4月29日下午2:49:26
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public void distributProjectMember(String projectID,String[] members);
	/**
	 * 
	 * @描述：TODO(按条件获取项目列表)
	 * @param id 用户id
	 * @param isOngoning 是否正在进行
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
	public List<Map<String, Object>> getProjectList(String id, String topTeamId,
			String isOngoning, String isCharge, String orderBy);
	/**
	 * 
	 * @描述：TODO(添加项目分组)
	 * @param groupName	分组名
	 * @param projectId 项目id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月1日下午5:27:11
	 * @修改人  ：lrz
	 * @修改时间：2017年5月1日下午5:27:11
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public BaseResult<TaskGroup> addTaskGroup(String groupName, String projectId);
	/**
	 * 
	 * @描述：TODO(通过项目id获取项目详情)
	 * @param id 项目id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月1日下午7:16:56
	 * @修改人  ：lrz
	 * @修改时间：2017年5月1日下午7:16:56
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public ProjectDetail getProjectById(String id);
	/**
	 * 
	 * @描述：TODO(根据项目id获取可参与项目的人员列表)
	 * @param projectId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月2日上午12:49:10
	 * @修改人  ：lrz
	 * @修改时间：2017年5月2日上午12:49:10
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Object> getMembersCanDistribut(String projectId);
	/**
	 * 
	 *
	 * @描述：TODO(添加任务)
	 *
	 * @param task
	 * @return
	 * BaseResult<Task>
	 * @创建人  ：lrz
	 * @创建时间：2017年5月2日下午1:58:01
	 * @修改人  ：lrz
	 * @修改时间：2017年5月2日下午1:58:01
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public BaseResult<Task> addTask(Task task);
	/**
	 * 
	 *
	 * @描述：TODO(分配任务)
	 *
	 * @param taskId 任务id 
	 * @param members 参与人员列表
	 * void
	 * @创建人  ：lrz
	 * @创建时间：2017年5月2日下午2:19:26
	 * @修改人  ：lrz
	 * @修改时间：2017年5月2日下午2:19:26
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public void distributTask(String taskId,String[] members);
	/**
	 * 
	 * @描述：TODO(删除任务分组)
	 * @param groupId 任务分组id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月3日下午11:49:48
	 * @修改人  ：lrz
	 * @修改时间：2017年5月3日下午11:49:48
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult deleteTaskGroup(String groupId);
	/**
	 * 
	 * @描述：TODO(删除任务)
	 * @param taskId 任务id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月3日下午11:59:21
	 * @修改人  ：lrz
	 * @修改时间：2017年5月3日下午11:59:21
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult delteTask(String taskId);
	/**
	 * 
	 * @描述：TODO(根据id获取任务详情)
	 * @param id 任务id 
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月6日下午3:36:58
	 * @修改人  ：lrz
	 * @修改时间：2017年5月6日下午3:36:58
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public Task getTaskById(String id);
	/**
	 * 
	 * @描述：TODO(获取任务的参与人员和可参与人员)
	 * @param id 任务id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月6日下午3:38:28
	 * @修改人  ：lrz
	 * @修改时间：2017年5月6日下午3:38:28
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Object> getTaskMemberAndCanDistribut(String id);
	/**
	 * 
	 * @描述：TODO(修改任务状态)
	 * @param id 任务id
	 * @param status 任务状态  0：未完成 1：完成
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日上午12:01:32
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日上午12:01:32
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public void changeTaskStatus(String id,String status);
	/**
	 * 
	 * @描述：TODO(获取项目信息)
	 * @param id 项目id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午3:28:14
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午3:28:14
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public Project getProjectInfo(String id);
	/**
	 * 
	 * @描述：TODO(获取项目的负责团队)
	 * @param projectId 项目id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午4:21:08
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午4:21:08
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Map<String, Object>> getProjectTeams(String projectId);
	/**
	 * 
	 * @描述：TODO(获取某个团队参加某个项目的人员列表)
	 * @param projectId 项目id
	 * @param teamId 人员列表
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午9:09:42
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午9:09:42
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Map<String,Object>> getProjectPartInMemberByTeamId(String projectId,String teamId);
	/**
	 * 
	 * @描述：TODO(修改项目信息)
	 * @param project
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午10:37:00
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午10:37:00
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult modifyProject(Project project,String userId);
	/**
	 * 
	 * @描述：TODO(修改参与项目的团队信息)
	 * @param projectId 项目id
	 * @param teams 团队列表
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午10:53:34
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午10:53:34
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public void modifyProjectTeam(String projectId, String[] teams);
	/**
	 * 
	 * @描述：TODO(修改参与项目的人员列表)
	 * @param projectId 项目id
	 * @param members 人员id
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午10:53:05
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午10:53:05
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public void modifyProjectMember(String projectId, String[] members);
	
	@SuppressWarnings("rawtypes")
	public BaseResult finishedProject(String projectId);
}