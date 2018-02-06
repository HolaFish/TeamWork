package com.heisenberg.base.action.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heisenberg.base.action.TaskController;
import com.heisenberg.base.model.Task;
import com.heisenberg.base.service.TaskService;
import com.heisenberg.base.service.TeamService;
import com.heisenberg.common.resultmodel.BaseResult;
@Controller
public class TaskControllerImpl implements TaskController {
	@Autowired
	private TeamService teamService;
	@Autowired
	private TaskService taskService;
	@Value("${TYPE_SUCCESS}")
	private String TYPE_SUCCESS;
	/**
	 * 
	 * @描述：TODO(跳转到正在进行的任务页面)
	 * @param userId
	 * @param topTeamId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午4:22:43
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午4:22:43
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToOngoingTask/{userId}/{topTeamId}")
	@Override
	public String redirectToOngoingTask(@PathVariable String userId,@PathVariable String topTeamId,Model model) {
		model.addAttribute("userId",userId);
		model.addAttribute("teamId", topTeamId);
		model.addAttribute("isOngoing", "1");
		return "task/task_list";
	}
	/**
	 * 
	 * @描述：TODO(跳转到已完成任务)
	 * @param userId
	 * @param topTeamId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午11:09:00
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午11:09:00
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToFinishedTask/{userId}/{topTeamId}")
	@Override
	public String redirectToFinishedTask(@PathVariable String userId,@PathVariable String topTeamId,
			Model model) {
		model.addAttribute("userId",userId);
		model.addAttribute("teamId", topTeamId);
		model.addAttribute("isOngoing", "0");
		return "task/task_list";
	}
	/**
	 * 
	 * @描述：TODO(跳转到全部任务)
	 * @param userId
	 * @param topTeamId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午11:10:22
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午11:10:22
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToAllTask/{userId}/{topTeamId}")
	@Override
	public String redirectToAllTask(@PathVariable String userId,@PathVariable String topTeamId, Model model) {
		model.addAttribute("userId",userId);
		model.addAttribute("teamId", topTeamId);
		return "task/task_list";
	}

	/**
	 * 
	 * @描述：TODO(按条件获取任务列表)
	 * @param userId 用户id
	 * @param topTeamId 顶级团队id
	 * @param isOngoing 是否为正在进行
	 * @param orderBy 排列规则
	 * @param isCharge 是否为userid用户负责
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午6:18:26
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午6:18:26
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("getTasks")
	@ResponseBody
	@Override
	public List<Task> getTasks(String userId, String topTeamId,
			String isOngoing, String orderBy, String isCharge, String isCreate) {
		return taskService.getTasks(userId, topTeamId, isOngoing, orderBy, isCharge,isCreate);
	}


	/**
	 * 
	 * @描述：TODO(跳转到添加快速任务页面)
	 * @param userId
	 * @param topTeamId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午4:47:58
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午4:47:58
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToAddQuickTask/{userId}/{topTeamId}")
	@Override
	public String redirectToAddQuickTask(@PathVariable String userId,@PathVariable String topTeamId,
			Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("teamId", topTeamId);
		//获取团队及其下属团队的成员
		List<Map<String,Object>> members = teamService.getMemeber(topTeamId);
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
		model.addAttribute("members", JSONArray.fromObject(TeamMembers));
		return "task/task_addQuickTask";
	}
	/**
	 * 
	 * @描述：TODO(添加快速任务)
	 * @param task
	 * @param members
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午5:23:44
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午5:23:44
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("addQuickTask")
	@ResponseBody
	@Override
	public BaseResult addQuicktask(Task task,@RequestParam(required = false,name="members[]") String[] members,String operationBy,String topTeamId) {
		BaseResult result = taskService.addQuickTask(task);
		if (TYPE_SUCCESS.equals(result.getResultType())){
			Object obj = result.getObj();
			String id = "";
			if (obj instanceof Task){
				Task quicktask = (Task) obj;
				id = quicktask.getId();
			}
			taskService.distributQuickTask(id, members);
		}
		return result;
	}
	/**
	 * 
	 * @描述：TODO(跳转到修改快速任务页面)
	 * @param taskId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午10:51:09
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午10:51:09
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToModifyQuickTask/{taskId}/{userId}/{topTeamId}")
	@Override
	public String redirectToModifyQuickTask(@PathVariable String taskId,@PathVariable String userId,@PathVariable String topTeamId,Model model) {
		//获取任务详情
		Task task = taskService.getTaskById(taskId);
		model.addAttribute("task", task);
		//获取团队的分工情况
		List<Object> members = taskService.getQuickTaskMemberAndCanDistribut(taskId, topTeamId);
		model.addAttribute("members", JSONArray.fromObject(members));
		model.addAttribute("userId", userId);
		model.addAttribute("teamId", topTeamId);
		return "task/task_modify";
	}
	
	
	
}
