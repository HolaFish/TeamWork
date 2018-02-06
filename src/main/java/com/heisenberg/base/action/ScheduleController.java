package com.heisenberg.base.action;

import java.util.List;

import org.springframework.ui.Model;

import com.heisenberg.base.model.Schedule;
import com.heisenberg.common.resultmodel.BaseResult;

public interface ScheduleController {
	/**
	 * 
	 * @描述：TODO(跳转到添加日程)
	 * @param userId
	 * @param startDate 开始时间
	 * @param allDay 是否为全天
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午6:46:11
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午6:46:11
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToAddSchedule(String userId,String startDate,boolean allDay,Model model);
	/**
	 * 
	 * @描述：TODO(新建日程)
	 * @param scheduel
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午7:22:36
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午7:22:36
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult AddSchedule(Schedule scheduel);
	/**
	 * 
	 * @描述：TODO(获取日程)
	 * @param userId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午7:38:24
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午7:38:24
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Schedule> getSchedule(String userId);
	/**
	 * 
	 * @描述：TODO(查看日程)
	 * @param title
	 * @param start
	 * @param end
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午8:01:28
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午8:01:28
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String checkSchedule(String id,Model model);
	
	@SuppressWarnings("rawtypes")
	public BaseResult SmodifySchedule(Schedule schedule);
	
	@SuppressWarnings("rawtypes")
	public BaseResult SdeleteSchedule(Schedule schedule);
}
