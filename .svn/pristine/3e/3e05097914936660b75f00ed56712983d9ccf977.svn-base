package com.heisenberg.base.service;

import java.util.List;
import java.util.Map;

import com.heisenberg.base.model.Schedule;
import com.heisenberg.common.resultmodel.BaseResult;

public interface ScheduleService {
	/**
	 * 
	 * @描述：TODO(添加日程)
	 * @param schedule
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午7:17:57
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午7:17:57
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult addSchedule(Schedule schedule);
	/**
	 * 
	 * @描述：TODO(获取日程)
	 * @param userId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午7:39:54
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午7:39:54
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Schedule> getSchedule(String userId);
	/**
	 * 
	 * @描述：TODO(根据日程id获取详情)
	 * @param id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午8:11:27
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午8:11:27
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public Map<String,Object> getSchedleById(String id);
	
	@SuppressWarnings("rawtypes")
	public BaseResult modifySchedule(Schedule schedule);
	
	@SuppressWarnings("rawtypes")
	public BaseResult deleteSchedule(Schedule schedule);
}
