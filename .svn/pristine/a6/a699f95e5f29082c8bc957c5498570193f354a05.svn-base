package com.heisenberg.base.action;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

public interface TeamDynamicController {
	
	/**
	 * 
	 * @描述：TODO(跳转到查看任务操作日志)
	 * @param userId
	 * @param topTeamId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月12日下午10:31:29
	 * @修改人  ：lrz
	 * @修改时间：2017年5月12日下午10:31:29
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToTaskLog(String userId, String topTeamId,Model model);
	/**
	 * 
	 * @描述：TODO(跳转到查看项目操作日志)
	 * @param userId
	 * @param topTeamId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月12日下午10:31:29
	 * @修改人  ：lrz
	 * @修改时间：2017年5月12日下午10:31:29
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToProjectLog(String userId, String topTeamId,Model model);
	/**
	 * 
	 * @描述：TODO(跳转到查看团队操作日志)
	 * @param userId
	 * @param topTeamId
	 * @param model
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月12日下午10:31:29
	 * @修改人  ：lrz
	 * @修改时间：2017年5月12日下午10:31:29
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToTeamLog(String userId, String topTeamId,Model model);
	
	public String redirectToApplyJoinLog(String userId,String tipTeamId,Model model);
	/**
	 * 
	 * @描述：TODO(按条件获取日志列表)
	 * @param userId 用户id
	 * @param topTeamId 顶级团队id
	 * @param logType 日志类型
	 * @param queryParam 日志分类
	 * @param date 时间
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月12日下午11:51:38
	 * @修改人  ：lrz
	 * @修改时间：2017年5月12日下午11:51:38
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Map<String,Object>> getLogs(String userId,String topTeamId,String logType,String queryParam,String date);
	
	public List<Map<String, Object>> getApplyJoinLogs(String userId, String topTeamId);
}
