package com.heisenberg.base.service;

import java.util.List;

import com.heisenberg.base.model.Task;
import com.heisenberg.common.resultmodel.BaseResult;

public interface TaskService {
	/**
	 * 
	 * @描述：TODO(按条件搜索任务)
	 * @param userId 用户id
	 * @param topTeamId 顶级团队id
	 * @param isOngoing 是否正在进行
	 * @param orderBy 排列方式
	 * @param isCharge 是否为用户负责
	 * @param iscreate 是否为用户创建的
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午4:28:51
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午4:28:51
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Task> getTasks(String userId,String topTeamId,String isOngoing,String orderBy,String isCharge,String iscreate);
	/**
	 * 
	 * @描述：TODO(添加快速任务)
	 * @param task
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午5:29:49
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午5:29:49
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public BaseResult<Task> addQuickTask(Task task);
	/**
	 * 
	 * @描述：TODO(为人员分配任务)
	 * @param taskId 任务id
	 * @param members 参与人员
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午5:41:20
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午5:41:20
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public void distributQuickTask(String taskId,String[] members);
	/**
	 * 
	 * @描述：TODO(获取快速任务的人员参与情况)
	 * @param taskId 任务id
	 * @param topTeamId 顶级团队id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午10:19:14
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午10:19:14
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Object> getQuickTaskMemberAndCanDistribut(String taskId,String topTeamId);
	
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
}
