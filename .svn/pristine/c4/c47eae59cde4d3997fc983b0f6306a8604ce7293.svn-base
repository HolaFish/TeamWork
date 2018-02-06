package com.heisenberg.base.action.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heisenberg.base.action.ScheduleController;
import com.heisenberg.base.model.Schedule;
import com.heisenberg.base.service.ScheduleService;
import com.heisenberg.common.resultmodel.BaseResult;
@Controller
public class ScheduleControllerImpl implements ScheduleController {
	@Autowired
	private  ScheduleService service;
	/**
	 * 
	 * @描述：TODO(跳转到添加日程)
	 * @param userId
	 * @param startDate
	 * @param allDay
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午6:48:29
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午6:48:29
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToAddSchedule/{userId}/{startDate}/{allDay}")
	@Override
	public String redirectToAddSchedule(@PathVariable String userId,@PathVariable String startDate,
			@PathVariable boolean allDay,Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("startDate", startDate);
		model.addAttribute("allDay", allDay);
		return "schedule/schedule_add";
	}
	/**
	 * 
	 * @描述：TODO(添加新日程)
	 * @param scheduel
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午7:23:23
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午7:23:23
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("addSchedule")
	@ResponseBody
	@Override
	public BaseResult AddSchedule(Schedule scheduel) {
		
		return service.addSchedule(scheduel);
	}
	/**
	 * 
	 * @描述：TODO(获取日程)
	 * @param userId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午7:38:47
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午7:38:47
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("getSchedule/{userId}")
	@ResponseBody
	@Override
	public List<Schedule> getSchedule(@PathVariable String userId) {
		return service.getSchedule(userId);
	}
	/**
	 * 
	 * @描述：TODO(查看日程)
	 * @param title
	 * @param start
	 * @param end
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午8:01:57
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午8:01:57
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectTocheckSchedule")
	@Override
	public String checkSchedule(String id,Model model) {
		Map<String,Object > result = service.getSchedleById(id);
		model.addAttribute("id", result.get("ID"));
		model.addAttribute("title", result.get("TITLE"));
		model.addAttribute("start", result.get("START"));
		model.addAttribute("end", result.get("END"));
		return "schedule/schedule_check";
	}
	@RequestMapping("modifySchedule")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult SmodifySchedule(Schedule schedule) {
		return service.modifySchedule(schedule);
	}
	@RequestMapping("deleteSchedule")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult SdeleteSchedule(Schedule schedule) {
		return service.deleteSchedule(schedule);
	}

	
}
