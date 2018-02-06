package com.heisenberg.base.action;

import java.util.List;

import org.springframework.ui.Model;

import com.heisenberg.base.model.Task;
import com.heisenberg.common.resultmodel.BaseResult;

public interface TaskController {
	/**
	 * 
	 * @描述：TODO(跳转到正在进行的任务)
	 * @param userId
	 * @param topTeamId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午4:21:35
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午4:21:35
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToOngoingTask(String userId, String topTeamId,Model model);
	/**
	 * 
	 * @描述：TODO(获取完成了的任务)
	 * @param userId
	 * @param topTeamId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午11:08:21
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午11:08:21
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToFinishedTask(String userId, String topTeamId,Model model);
	/**
	 * 
	 * @描述：TODO(获取全部任务)
	 * @param userId
	 * @param topTeamId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午11:08:34
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午11:08:34
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToAllTask(String userId,String topTeamId, Model model);
	/**
	 * 
	 * @描述：TODO(按要求获取任务列表)
	 * @param userId 用户id
	 * @param topTeamId 顶级团队id
	 * @param isOngoing 是否正在进行
	 * @param orderBy 排序规则
	 * @param isCharge 是否为userID用户负责
	 * @param isCreate 是否为userID用户创建的
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午6:17:35
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午6:17:35
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Task> getTasks(String userId,String topTeamId,String isOngoing,String orderBy,String isCharge,String isCreate);
	/**
	 * 
	 * @描述：TODO(跳转到添加快速任务页面)
	 * @param userId 用户id
	 * @param topTeamId 顶级团队id
	 * @param model 
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午4:47:24
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午4:47:24
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToAddQuickTask(String userId,String topTeamId, Model model);
	/**
	 * 
	 * @描述：TODO(添加快速任务)
	 * @param task 
	 * @param members
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午5:21:14
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午5:21:14
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult addQuicktask(Task task,String[] members,String operationBy, String topTeamId);
	/**
	 * 
	 * @描述：TODO(跳转到修改快速任务页面)
	 * @param taskId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午10:50:33
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午10:50:33
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToModifyQuickTask(String taskId, String userId,String topTeamId, Model model);
}
