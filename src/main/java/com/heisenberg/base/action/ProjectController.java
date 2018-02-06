package com.heisenberg.base.action;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.heisenberg.base.model.Project;
import com.heisenberg.base.model.ProjectInfoVO;
import com.heisenberg.base.model.Task;
import com.heisenberg.common.resultmodel.BaseResult;

public interface ProjectController {
	/**
	 * 
	 * @描述：TODO(跳转到项目管理页面)
	 * @param model
	 * @param userId
	 * @param userId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月21日下午1:36:52
	 * @修改人  ：lrz
	 * @修改时间：2017年4月21日下午1:36:52
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String RedirectToProject(Model model,String userId,String teamId);
	
	/**
	 * 
	 * @描述：TODO(跳转到正在进行的项目)
	 * @param model
	 * @param userId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月21日下午2:07:38
	 * @修改人  ：lrz
	 * @修改时间：2017年4月21日下午2:07:38
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String RedirectToOngoingProject(Model model,String userId,String teamId);
	/**
	 * 
	 * @描述：TODO(跳转到已完成项目列表)
	 * @param model
	 * @param userId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午2:31:34
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午2:31:34
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToFinishedProject(Model model,String userId,String teamId);
	/**
	 * 
	 * @描述：TODO(跳转到全部项目列表)
	 * @param model
	 * @param userId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午2:33:38
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午2:33:38
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToAllProject(Model model, String userId,String teamId);
	/**
	 * 
	 * @描述：TODO(跳转到添加新项目)
	 * @param model
	 * @param userId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月21日下午3:39:51
	 * @修改人  ：lrz
	 * @修改时间：2017年4月21日下午3:39:51
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String RedirectToAddProject(Model model, String userId,String teamId);
	/**
	 * 
	 * @描述：TODO(添加新项目)
	 * @param proInfo
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月24日下午11:56:56
	 * @修改人  ：lrz
	 * @修改时间：2017年4月24日下午11:56:56
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult addProject(ProjectInfoVO proInfo,String[] teams, String[] members,String operationBy,String topTeamId);
	
	/**
	 * 
	 * @描述：TODO(按条件获取项目列表)
	 * @param id 用户id
	 * @param isOngoning 是否是正在进行的项目
	 * @param isCharge 是否是负责人
	 * @param orderBy 排序方式
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月30日上午2:55:26
	 * @修改人  ：lrz
	 * @修改时间：2017年4月30日上午2:55:26
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Map<String,Object>> getProjectList(String id, String topTeamId, String isOngoning,String isCharge,  String orderBy);
	/**
	 * 
	 * @描述：TODO(跳转到项目详情列表)
	 * @param userId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月1日下午12:34:57
	 * @修改人  ：lrz
	 * @修改时间：2017年5月1日下午12:34:57
	 * @修改备注：
	 * @version 1.0
	 * @throws UnsupportedEncodingException 
	 *
	 */
	public String redirectToProjectTask(String userId,String projectId,String projectName, String topTeamId,Model model) throws UnsupportedEncodingException;
	/**
	 * 
	 * @描述：TODO(添加项目分组)
	 * @param groupName 分组名
	 * @param projectId 项目id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月1日下午5:25:18
	 * @修改人  ：lrz
	 * @修改时间：2017年5月1日下午5:25:18
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult addTaskgroup(String groupName, String projectId,String operationBy,String topTeamId);
	/**
	 * 
	 * @描述：TODO(跳转到添加任务)
	 * @param userId 用户id
	 * @param projectId 项目id
	 * @param groupId 任务分组id
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月2日上午12:19:18
	 * @修改人  ：lrz
	 * @修改时间：2017年5月2日上午12:19:18
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToAddTask(String userId,String projectId,String groupId,String topTeamId,Model model);
	/**
	 * 
	 *
	 * @描述：TODO(添加任务)
	 *
	 * @param task 任务详情
	 * @param members	参加人员列表
	 * @return
	 * BaseResult<Task>
	 * @创建人  ：lrz
	 * @创建时间：2017年5月2日上午11:24:31
	 * @修改人  ：lrz
	 * @修改时间：2017年5月2日上午11:24:31
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult addTask(Task task, String[] members,String operationBy,String topTeamId);
	/**
	 * 
	 * @描述：TODO(跳转到修改任务页面)
	 * @param taskId 任务id
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月6日下午3:27:13
	 * @修改人  ：lrz
	 * @修改时间：2017年5月6日下午3:27:13
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToModifyTask(String taskId,String userId,String topTeamId,Model model);
	/**
	 * 
	 * @描述：TODO(删除任务分组)
	 * @param groupId 任务分组id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月3日下午11:46:52
	 * @修改人  ：lrz
	 * @修改时间：2017年5月3日下午11:46:52
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult deleteTaskgroup(String groupId,String operationBy,String topTeamId);
	/**
	 * 
	 * @描述：TODO(删除任务)
	 * @param taskId 任务id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月3日下午11:58:43
	 * @修改人  ：lrz
	 * @修改时间：2017年5月3日下午11:58:43
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult deleteTask(String taskId,String operationBy,String topTeamId);
	/**
	 * 
	 * @描述：TODO(修改任务)
	 * @param task 任务详情
	 * @param members 参与人员
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月6日下午10:31:18
	 * @修改人  ：lrz
	 * @修改时间：2017年5月6日下午10:31:18
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult modifyTask(Task task,String[] members,String operationBy,String topTeamId);
	/**
	 * 
	 * @描述：TODO(将任务标记为完成)
	 * @param id 任务id
	 * @创建人  ：lrz
	 * @创建时间：2017年5月6日下午11:27:33
	 * @修改人  ：lrz
	 * @修改时间：2017年5月6日下午11:27:33
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public void finishedTask(String id);
	/**
	 * 
	 * @描述：TODO(将任务标记为未完成)
	 * @param id 任务id
	 * @创建人  ：lrz
	 * @创建时间：2017年5月6日下午11:58:47
	 * @修改人  ：lrz
	 * @修改时间：2017年5月6日下午11:58:47
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public void unfinishedTask(String id);
	/**
	 * 
	 * @描述：TODO(跳转到修改项目页面)
	 * @param userId 用户id
	 * @param projectId 项目id
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午3:09:26
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午3:09:26
	 * @修改备注：
	 * @version 1.0
	 * @throws UnsupportedEncodingException 
	 *
	 */
	public String redirectToModifyProject(String userId,String projectId,String projectName,String teamId,Model model) throws UnsupportedEncodingException;
	/**
	 * 
	 * @描述：TODO(获取某个团队参加某个项目的人员列表)
	 * @param projectId 项目id
	 * @param teamId 团队id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午9:07:15
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午9:07:15
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Map<String,Object>> getProjectPartInMemberByTeamId(String projectId,String teamId);
	/**
	 * 
	 * @描述：TODO(修改项目)
	 * @param project
	 * @param teams
	 * @param members
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午10:33:08
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午10:33:08
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult modifyProject(String userId,Project project,String[] teams, String[] members,String operationBy,String topTeamId);
	
	public BaseResult finishedProject(String projectId);
}
