package com.heisenberg.base.action;

import org.springframework.ui.Model;

import com.heisenberg.common.resultmodel.BaseResult;

public interface BaseController {
	/**
	 * 
	 * @描述：TODO(跳转到任务主页)
	 * @param userId
	 * @param topTeamId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月11日下午3:57:45
	 * @修改人  ：lrz
	 * @修改时间：2017年5月11日下午3:57:45
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToTask(String userId,String topTeamId,Model model);
	/**
	 * 
	 * @描述：TODO(跳转到团队工作动态页面)
	 * @param userId
	 * @param topTeamId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月12日下午10:32:41
	 * @修改人  ：lrz
	 * @修改时间：2017年5月12日下午10:32:41
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToDynamic(String userId,String topTeamId,Model model);
	/**
	 * 
	 * @描述：TODO(跳转到文档管理页面)
	 * @param userId
	 * @param topTeamId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午1:08:15
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午1:08:15
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToDocument(String userId,String topTeamId, Model model);
	/**
	 * 
	 * @描述：TODO(跳转到日程安排)
	 * @param userId
	 * @param topTeamId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午5:40:20
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午5:40:20
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToCanlendar(String userId,String topTeamId, Model model);
	
	@SuppressWarnings("rawtypes")
	public BaseResult noAuthority();
}
