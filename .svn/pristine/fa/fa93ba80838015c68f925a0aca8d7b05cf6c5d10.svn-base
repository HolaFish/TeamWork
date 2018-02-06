package com.heisenberg.base.action.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heisenberg.base.action.ProjectController;
import com.heisenberg.base.model.Project;
import com.heisenberg.base.model.ProjectDetail;
import com.heisenberg.base.model.ProjectInfoVO;
import com.heisenberg.base.model.Task;
import com.heisenberg.base.model.TaskGroup;
import com.heisenberg.base.service.ProjectService;
import com.heisenberg.base.service.TaskService;
import com.heisenberg.base.service.TeamService;
import com.heisenberg.common.resultmodel.BaseResult;
import com.heisenberg.common.tree.model.Node;

@Controller
public class ProjectControllerImpl implements ProjectController {
	@Autowired
	private TeamService teamService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private TaskService taskService;
	@Value("${TYPE_SUCCESS}")
	private String TYPE_SUCCESS;

	/**
	 * 
	 * @描述：TODO(跳转到项目管理)
	 * @param model
	 * @param userId
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年4月21日下午1:37:12
	 * @修改人 ：lrz
	 * @修改时间：2017年4月21日下午1:37:12
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@RequestMapping("redirectToProject/{userId}/{topTeamId}")
	@Override
	public String RedirectToProject(Model model, @PathVariable String userId,@PathVariable String topTeamId) {
		model.addAttribute("userId", userId);
		model.addAttribute("teamId", topTeamId);
		return "project/project_main";
	}

	/**
	 * 
	 * @描述：TODO(跳转到正在进行的项目)
	 * @param model
	 * @param userId
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年4月21日下午2:08:07
	 * @修改人 ：lrz
	 * @修改时间：2017年4月21日下午2:08:07
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@RequestMapping("redirectToOngoingProject/{userId}/{topTeamId}")
	@Override
	public String RedirectToOngoingProject(Model model,
			@PathVariable String userId,@PathVariable String topTeamId) {
		model.addAttribute("userId", userId);
		model.addAttribute("teamId", topTeamId);
		model.addAttribute("isOngoing", "1");
		return "project/project_list";
	}
	/**
	 * 
	 * @描述：TODO(跳转到已完成项目列表)
	 * @param model
	 * @param userId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午2:31:58
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午2:31:58
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToFinishedProject/{userId}/{topTeamId}")
	@Override
	public String redirectToFinishedProject(Model model,@PathVariable String userId,@PathVariable String topTeamId) {
		model.addAttribute("userId", userId);
		model.addAttribute("teamId", topTeamId);
		model.addAttribute("isOngoing", "0");
		return "project/project_list";
	}
	/**
	 * 
	 * @描述：TODO(跳转到全部项目列表)
	 * @param model
	 * @param userId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午2:34:01
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午2:34:01
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToAllProject/{userId}/{topTeamId}")
	@Override
	public String redirectToAllProject(Model model,@PathVariable String userId,@PathVariable String topTeamId) {
		model.addAttribute("userId", userId);
		model.addAttribute("teamId", topTeamId);
		return "project/project_list";
	}

	/**
	 * 
	 * @描述：TODO(跳转到添加新项目)
	 * @param model
	 * @param userId
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年4月21日下午3:40:13
	 * @修改人 ：lrz
	 * @修改时间：2017年4月21日下午3:40:13
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@RequestMapping("redirectToAddProject/{userId}/{topTeamId}")
	@Override
	public String RedirectToAddProject(Model model, @PathVariable String userId,@PathVariable String topTeamId) {
		model.addAttribute("userId", userId);
		model.addAttribute("teamId", topTeamId);
		Node[] teamTree = teamService.getTeamTree(topTeamId);
		model.addAttribute("teamTree", JSONArray.fromObject(teamTree));
		return "project/project_add";
	}

	/**
	 * 
	 * @描述：TODO(添加新项目)
	 * @param proInfo
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年4月24日下午11:58:07
	 * @修改人 ：lrz
	 * @修改时间：2017年4月24日下午11:58:07
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@RequestMapping("addProject")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult addProject(ProjectInfoVO proInfo,
			@RequestParam("teams[]") String[] teams,
			@RequestParam("members[]") String[] members,String operationBy,String topTeamId) {
		return projectService.addProject(proInfo, teams, members);
	}

	/**
	 * 
	 * @描述：TODO(按条件获取项目列表)
	 * @param id
	 *            用户id
	 * @param isOngoning
	 *            是否正在进行
	 * @param isCharge
	 *            是否是负责人
	 * @param orderBy
	 *            排列方式
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年4月30日上午2:57:10
	 * @修改人 ：lrz
	 * @修改时间：2017年4月30日上午2:57:10
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@RequestMapping("getProjectList")
	@ResponseBody
	@Override
	public List<Map<String, Object>> getProjectList(String id, String topTeamId,
			String isOngoing, String isCharge, String orderBy) {
		return projectService.getProjectList(id,topTeamId, isOngoing, isCharge, orderBy);
	}

	/**
	 * 
	 * @描述：TODO(跳转到项目详情页面)
	 * @param userId
	 * @param model
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年5月1日下午12:37:38
	 * @修改人 ：lrz
	 * @修改时间：2017年5月1日下午12:37:38
	 * @修改备注：
	 * @version 1.0
	 * @throws UnsupportedEncodingException
	 * 
	 */
	@RequestMapping("redirectToProTask")
	@Override
	public String redirectToProjectTask(String userId, String projectId,
			String projectName,String topTeamId, Model model)
			throws UnsupportedEncodingException {
		model.addAttribute("userId", userId);
		model.addAttribute("projectId", projectId);
		model.addAttribute("teamId", topTeamId);
		projectName = new String(projectName.getBytes("ISO-8859-1"), "UTF-8");
		model.addAttribute("projectName", projectName);
		// 获取任务详情
		ProjectDetail project = projectService.getProjectById(projectId);
		model.addAttribute("project", JSONObject.fromObject(project));
		return "project/project_tasklist";
	}

	/**
	 * 
	 * @描述：TODO(添加任务分组)
	 * @param groupName
	 *            分组名
	 * @param projectId
	 *            项目id
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年5月2日下午3:56:42
	 * @修改人 ：lrz
	 * @修改时间：2017年5月2日下午3:56:42
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@RequestMapping("addTaskGroup")
	@ResponseBody
	@Override
	public BaseResult<TaskGroup> addTaskgroup(String groupName, String projectId,String operationBy,String topTeamId) {
		return projectService.addTaskGroup(groupName, projectId);
	}

	/**
	 * 
	 * @描述：TODO(跳转到添加任务页面)
	 * @param userId
	 * @param projectId
	 * @param groupId
	 * @param model
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年5月2日上午12:20:07
	 * @修改人 ：lrz
	 * @修改时间：2017年5月2日上午12:20:07
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@RequestMapping("redirectToAddTask/{userId}/{projectId}/{groupId}/{topTeamId}")
	@Override
	public String redirectToAddTask(@PathVariable String userId,
			@PathVariable String projectId, @PathVariable String groupId,@PathVariable String topTeamId,
			Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("projectId", projectId);
		model.addAttribute("groupId", groupId);
		model.addAttribute("teamId", topTeamId);
		// 获取负责小组的成员
		List<Object> members = projectService.getMembersCanDistribut(projectId); // List<List<Map<String,Object>>>
		model.addAttribute("members", JSONArray.fromObject(members));
		return "project/project_addTask";
	}

	/**
	 * 
	 * @描述：TODO(添加任务)
	 * @param task
	 * @param members
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年5月2日上午11:25:37
	 * @修改人 ：lrz
	 * @修改时间：2017年5月2日上午11:25:37
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("addTask")
	@ResponseBody
	@Override
	public BaseResult addTask(Task task,
			@RequestParam("members[]") String[] members,String operationBy,String topTeamId) {
		// 记录任务
		BaseResult result = projectService.addTask(task);
		if (result.getResultType().equals(TYPE_SUCCESS)) {
			// 分配任务
			String taskId = "";
			if (result.getObj() instanceof Task){
				taskId = ((Task) result.getObj()).getId();
			}
			projectService.distributTask(taskId, members);
		}
		return result;
	}

	/**
	 * 
	 * @描述：TODO(跳转到修改任务列表)
	 * @param taskId
	 *            任务id
	 * @param model
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年5月6日下午3:27:47
	 * @修改人 ：lrz
	 * @修改时间：2017年5月6日下午3:27:47
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@RequestMapping("redirectToModifyTask/{taskId}/{userId}/{topTeamId}")
	@Override
	public String redirectToModifyTask(@PathVariable String taskId,@PathVariable String userId,@PathVariable String topTeamId, Model model) {
		// 获取任务详情
		Task task = projectService.getTaskById(taskId);
		// 获取任务分配情况
		List<Object> members = null;
		members = projectService
				.getTaskMemberAndCanDistribut(taskId); // List<List<Map<String,Object>>>
		model.addAttribute("task", task);
		model.addAttribute("members", JSONArray.fromObject(members));
		model.addAttribute("userId", userId);
		model.addAttribute("teamId", topTeamId);
		return "task/task_modify";
	}

	/**
	 * 
	 * @描述：TODO(删除任务分组)
	 * @param groupId
	 *            任务分组id
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年5月3日下午11:48:13
	 * @修改人 ：lrz
	 * @修改时间：2017年5月3日下午11:48:13
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("deleteTaskGroup")
	@ResponseBody
	@Override
	public BaseResult deleteTaskgroup(String groupId,String operationBy,String topTeamId) {
		return projectService.deleteTaskGroup(groupId);
	}

	/**
	 * 
	 * @描述：TODO(删除任务)
	 * @param taskId
	 *            任务id
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年5月4日上午12:07:27
	 * @修改人 ：lrz
	 * @修改时间：2017年5月4日上午12:07:27
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@RequestMapping("deleteTask")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult deleteTask(String taskId,String operationBy,String topTeamId) {
		return projectService.delteTask(taskId);
	}
	/**
	 * 
	 * @描述：TODO(修改任务)
	 * @param task 任务详情
	 * @param members 参与人员
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月6日下午10:31:51
	 * @修改人  ：lrz
	 * @修改时间：2017年5月6日下午10:31:51
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("modifyTask")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult modifyTask(Task task, @RequestParam("members[]") String[] members,String operationBy,String topTeamId) {
		//删除任务
		projectService.delteTask(task.getId());
		//添加任务
		BaseResult<Task> result = projectService.addTask(task);
		if (result.getResultType().equals(TYPE_SUCCESS)) {
			// 分配任务
			projectService.distributTask(result.getObj().getId(), members);
		}
		return result;
	}
	/**
	 * 
	 * @描述：TODO(将任务标记为完成)
	 * @param id 任务id
	 * @创建人  ：lrz
	 * @创建时间：2017年5月6日下午11:28:04
	 * @修改人  ：lrz
	 * @修改时间：2017年5月6日下午11:28:04
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("finishedTask")
	@ResponseBody
	@Override
	public void finishedTask(String id) {
		projectService.changeTaskStatus(id,"1");
	}
	/**
	 * 
	 * @描述：TODO(将任务标记为未完成)
	 * @param id 任务id
	 * @创建人  ：lrz
	 * @创建时间：2017年5月6日下午11:59:17
	 * @修改人  ：lrz
	 * @修改时间：2017年5月6日下午11:59:17
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("unfinishedTask")
	@ResponseBody
	@Override
	public void unfinishedTask(String id) {
		projectService.changeTaskStatus(id,"0");
	}
	/**
	 * 
	 * @描述：TODO(跳转到修改项目页面)
	 * @param id 项目id
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午3:09:57
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午3:09:57
	 * @修改备注：
	 * @version 1.0
	 * @throws UnsupportedEncodingException 
	 *
	 */
	@RequestMapping("redirectToModifyProject")
	@Override
	public String redirectToModifyProject(String userId,String projectId,String projectName,String topTeamId,Model model) throws UnsupportedEncodingException {
		Project project = projectService.getProjectInfo(projectId);
		model.addAttribute("project", project);
		//获取团队列表
		Node[] teamTree = teamService.getTeamTree(topTeamId);
		model.addAttribute("teamTree", JSONArray.fromObject(teamTree));
		//获取项目的负责团队
		List<Map<String,Object>> teams = projectService.getProjectTeams(projectId);
		model.addAttribute("teams", JSONArray.fromObject(teams));
		//获取团队成员
		List<Object> teamMembers = projectService.getMembersCanDistribut(projectId);
		model.addAttribute("teamMembers", JSONArray.fromObject(teamMembers));
		model.addAttribute("userId", userId);
		model.addAttribute("projectId", projectId);
		projectName = new String(projectName.getBytes("ISO-8859-1"), "UTF-8");
		model.addAttribute("projectName", projectName);
		model.addAttribute("teamId", topTeamId);
		return "project/project_modify";
	}
	/**
	 * 
	 * @描述：TODO(获取某个团队参加某个项目的人员列表)
	 * @param projectId 项目id
	 * @param teamId 团队id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午9:08:15
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午9:08:15
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("getProjectPartInMemberByTeamId")
	@ResponseBody
	@Override
	public List<Map<String, Object>> getProjectPartInMemberByTeamId(
			String projectId, String teamId) {
		
		return projectService.getProjectPartInMemberByTeamId(projectId, teamId);
	}
	/**
	 * 
	 * @描述：TODO(修改项目)
	 * @param project 项目详情
	 * @param teams 项目团队列表
	 * @param members 项目人员列表
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月7日下午11:00:14
	 * @修改人  ：lrz
	 * @修改时间：2017年5月7日下午11:00:14
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("modifyProject")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult modifyProject(String userId, Project project,@RequestParam("teams[]") String[] teams,
			@RequestParam("members[]") String[] members ,String operationBy,String topTeamId) {
		//修改项目信息
		BaseResult result = projectService.modifyProject(project, userId);
		if (result.getResultType().equals(TYPE_SUCCESS)){
			//修改团队信息和人员信息
			projectService.modifyProjectMember(project.getId(), members);
			projectService.modifyProjectTeam(project.getId(), teams);
		}
		return result;
	}
	@RequestMapping("finishedProject")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult finishedProject(String projectId) {
		
		return projectService.finishedProject(projectId);
	}

	
}
