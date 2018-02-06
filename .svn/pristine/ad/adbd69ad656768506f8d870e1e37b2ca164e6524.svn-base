package com.heisenberg.base.action.impl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heisenberg.base.action.BaseController;
import com.heisenberg.common.resultmodel.BaseResult;
@Controller
public class BaseControllerImpl implements BaseController{
	/**
	 * 
	 * @描述：TODO(跳转到任务主页)
	 * @param userId
	 * @param topTeamId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午3:59:04
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午3:59:04
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToTask/{userId}/{topTeamId}")
	@Override
	public String redirectToTask(@PathVariable String userId,@PathVariable String topTeamId, Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("teamId", topTeamId);
		return "task/task_main";
	}
	/**
	 * 
	 * @描述：TODO(跳转到团队工作动态页面)
	 * @param userId
	 * @param topTeamId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月12日下午10:33:19
	 * @修改人  ：lrz
	 * @修改时间：2017年5月12日下午10:33:19
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToDynamic/{userId}/{topTeamId}")
	@Override
	public String redirectToDynamic(@PathVariable String userId,@PathVariable String topTeamId, Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("teamId",topTeamId);
		return "dynamic/dynamic_main";
	}
	/**
	 * 
	 * @描述：TODO(跳转到文档管理页面)
	 * @param userId
	 * @param topTeamId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午1:08:46
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午1:08:46
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToDocument/{userId}/{topTeamId}")
	@Override
	public String redirectToDocument(@PathVariable String userId,@PathVariable String topTeamId, Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("teamId",topTeamId);
		return "document/document_main";
	}
	/**
	 * 
	 * @描述：TODO(跳转到日程安排界面)
	 * @param userId
	 * @param topTeamId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午5:40:48
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午5:40:48
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToCanlendar/{userId}/{topTeamId}")
	@Override
	public String redirectToCanlendar(@PathVariable String userId,@PathVariable String topTeamId,
			Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("teamId",topTeamId);
		return "schedule/schedule";
	}
	@RequestMapping("noAuthority")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult noAuthority() {
		BaseResult result = new BaseResult();
		result.setMessage("你无权进行此操作，请联系管理员！");
		return result;
	}
	@RequestMapping("redirectToResetPsd")
	public String redirectToResetPsd(){
		return "resetPsd";
	}
}
