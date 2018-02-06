package com.heisenberg.base.action.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heisenberg.base.action.TeamDynamicController;
import com.heisenberg.base.service.TeamDynamicService;
@Controller
public class TeamDynacControllerImpl implements TeamDynamicController {
	@Autowired
	private TeamDynamicService service;
	/**
	 * 
	 * @描述：TODO(跳转到查看任务操作日志页面)
	 * @param userId
	 * @param topTeamId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月12日下午10:36:49
	 * @修改人  ：lrz
	 * @修改时间：2017年5月12日下午10:36:49
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToTaskLog/{userId}/{topTeamId}")
	@Override
	public String redirectToTaskLog(@PathVariable String userId,@PathVariable String topTeamId, Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("teamId", topTeamId);
		model.addAttribute("logType", "Task");
		return "dynamic/dynamic_logList";
	}
	/**
	 * 
	 * @描述：TODO(跳转到查看项目操作日志)
	 * @param userId
	 * @param topTeamId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月13日上午2:10:52
	 * @修改人  ：lrz
	 * @修改时间：2017年5月13日上午2:10:52
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToProjectLog/{userId}/{topTeamId}")
	@Override
	public String redirectToProjectLog(@PathVariable String userId,@PathVariable String topTeamId,
			Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("teamId", topTeamId);
		model.addAttribute("logType", "Project");
		return "dynamic/dynamic_logList";
	}
	/**
	 * 
	 * @描述：TODO(跳转到查看团队操作日志)
	 * @param userId
	 * @param topTeamId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月13日上午2:11:18
	 * @修改人  ：lrz
	 * @修改时间：2017年5月13日上午2:11:18
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToTeamLog/{userId}/{topTeamId}")
	@Override
	public String redirectToTeamLog(@PathVariable String userId,@PathVariable String topTeamId, Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("teamId", topTeamId);
		model.addAttribute("logType", "Team");
		return "dynamic/dynamic_logList";
	}

	/**
	 * 
	 * @描述：TODO(跳转到查看加入申请)
	 * @param userId
	 * @param tipTeamId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月13日上午11:39:57
	 * @修改人  ：lrz
	 * @修改时间：2017年5月13日上午11:39:57
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToApplyJoin/{userId}/{topTeamId}")
	@Override
	public String redirectToApplyJoinLog(@PathVariable String userId,@PathVariable String topTeamId,
			Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("teamId", topTeamId);
		model.addAttribute("logType", "apply");
		return "dynamic/dynamic_applyJoin";
	}
	/**
	 * 
	 * @描述：TODO(按条件获取日志)
	 * @param userId 用户id
	 * @param topTeamId 顶级团队id
	 * @param logType 日志类型
	 * @param queryParam 日志分类
	 * @param date 日期
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月12日下午11:52:29
	 * @修改人  ：lrz
	 * @修改时间：2017年5月12日下午11:52:29
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("getLogs")
	@ResponseBody
	@Override
	public List<Map<String, Object>> getLogs(String userId, String topTeamId,
			String logType, String queryParam, String date) {
		return service.getLogs(userId, topTeamId, logType, queryParam, date);
	}
	@RequestMapping("getApplyJoinLogs")
	@ResponseBody
	@Override
	public List<Map<String, Object>> getApplyJoinLogs(String userId,String topTeamId) {
		
		return service.getApplyJoinLogs(userId, topTeamId);
	}

	
}
