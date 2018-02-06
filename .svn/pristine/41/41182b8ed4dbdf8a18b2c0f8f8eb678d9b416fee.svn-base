package com.heisenberg.base.service;

import java.util.List;
import java.util.Map;

import com.heisenberg.base.model.Position;
import com.heisenberg.base.model.Station;
import com.heisenberg.base.model.Team;
import com.heisenberg.base.model.User;
import com.heisenberg.base.model.UserBaseInfo;
import com.heisenberg.common.resultmodel.BaseResult;
import com.heisenberg.common.tree.model.Node;

public interface TeamService {
	/**
	 * 
	 * 方法功能说明：  判断具有相同父节点的部门名是否已经存在 
	 * 创建：2017年3月12日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @param team 部门名称
	 *  @return          
	 * @throws
	 */
	public boolean isTeamExist(Team team);
	/**
	 * 
	 * 方法功能说明：添加新团队  
	 * 创建：2017年3月12日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @param team
	 *  @return          
	 * @throws
	 */
	public BaseResult<Team> addTeam(Team team);
	/**
	 * 
	 *
	 * @描述：TODO(获取团队列表)
	 *
	 * @return
	 * Team[]
	 * @创建人  ：lrz
	 * @创建时间：2017年3月21日下午3:09:12
	 * @修改人  ：lrz
	 * @修改时间：2017年3月21日下午3:09:12
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public Node[] getTeamTree(String topTeamId);
	/**
	 * 
	 *
	 * @描述：TODO(获取团队列表)
	 *
	 * @return
	 * List<Team>
	 * @创建人  ：lrz
	 * @创建时间：2017年3月22日下午3:18:34
	 * @修改人  ：lrz
	 * @修改时间：2017年3月22日下午3:18:34
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Node> getTeamList(String topTeamId);
	/**
	 * 
	 * @描述：TODO(获取顶级团队列表)
	 * @param userId 用户id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月10日下午4:28:19
	 * @修改人  ：lrz
	 * @修改时间：2017年5月10日下午4:28:19
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Team> getTopTeamList(String userId);
//	
//	/**
//	 * 
//	 *
//	 * @描述：TODO(获取某个团队的内部团队)
//	 *
//	 * @param id
//	 * @return
//	 * List<Team>
//	 * @创建人  ：lrz
//	 * @创建时间：2017年3月23日下午4:54:24
//	 * @修改人  ：lrz
//	 * @修改时间：2017年3月23日下午4:54:24
//	 * @修改备注：
//	 * @version 1.0
//	 *
//	 */
//	public List<Team> getInnerTeamList(String id);
	/**
	 * 
	 *
	 * @描述：TODO(修改团队信息)
	 *
	 * @param team
	 * @return
	 * BaseResult
	 * @创建人  ：lrz
	 * @创建时间：2017年3月22日下午5:13:18
	 * @修改人  ：lrz
	 * @修改时间：2017年3月22日下午5:13:18
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public int ModifyTeam(Team team);
	/**
	 * 
	 *
	 * @描述：TODO(删除团队)
	 *
	 * @param id
	 * @return
	 * int
	 * @创建人  ：lrz
	 * @创建时间：2017年3月23日上午10:39:28
	 * @修改人  ：lrz
	 * @修改时间：2017年3月23日上午10:39:28
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public int deleteTeam(String id);
	
	/**
	 * 
	 *
	 * @描述：TODO(邀请新成员加入团队)
	 *
	 * @param inviterId	邀请人id
	 * @param inviteeName	被邀请人姓名
	 * @param inviteeEMail	被邀请人email	
	 * @param teamId	团队ID
	 * @return
	 * BaseResult
	 * @创建人  ：lrz
	 * @创建时间：2017年3月27日上午9:53:10
	 * @修改人  ：lrz
	 * @修改时间：2017年3月27日上午9:53:10
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult invitePeople(String inviterId,String inviteeName,String inviteeEMail,String teamId);
	/**
	 * 
	 *
	 * @描述：TODO(获取用户的邀请列表)
	 *
	 * @param teamId	团队ID
	 * @param inviterId	邀请人ID
	 * @return
	 * List<Map<String,String>>
	 * @创建人  ：lrz
	 * @创建时间：2017年3月27日下午3:54:33
	 * @修改人  ：lrz
	 * @修改时间：2017年3月27日下午3:54:33
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Map<String,Object>>  getInviteList(String teamId, String inviterId);
	/**
	 * 
	 *
	 * @描述：TODO(邀请应答)
	 *
	 * @param inviteId 邀请码
	 * @return
	 * String
	 * @创建人  ：lrz
	 * @创建时间：2017年3月29日下午3:41:55
	 * @修改人  ：lrz
	 * @修改时间：2017年3月29日下午3:41:55
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public BaseResult<User> inviteAnwser(String inviteId);
	/**
	 * 
	 * @描述：TODO(获取团队成员,包括下属团队)
	 * @param id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月3日下午5:18:27
	 * @修改人  ：lrz
	 * @修改时间：2017年4月3日下午5:18:27
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Map<String, Object>> getMemeber(String id);
	/**
	 * 
	 *
	 * @描述：TODO(获取团队成员)
	 *
	 * @param id
	 * @return
	 * List<Map<String,Object>>
	 * @创建人  ：lrz
	 * @创建时间：2017年4月24日下午2:06:00
	 * @修改人  ：lrz
	 * @修改时间：2017年4月24日下午2:06:00
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Map<String,Object>> getTeamMember(String id);
	/**
	 * 
	 * @描述：TODO(添加岗位)
	 * @param station
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月3日下午11:25:26
	 * @修改人  ：lrz
	 * @修改时间：2017年4月3日下午11:25:26
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult addStation(Station station);
	/**
	 * 
	 * @描述：TODO(获取岗位树)
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月4日下午1:42:32
	 * @修改人  ：lrz
	 * @修改时间：2017年4月4日下午1:42:32
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public Node[] getStationTree(String topTeamId);
	/**
	 * 
	 * @描述：TODO(获取跟某个节点相同顶级节点的子节点)
	 * @param id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月4日下午3:14:41
	 * @修改人  ：lrz
	 * @修改时间：2017年4月4日下午3:14:41
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Node> getInnerStation(String id);
	/**
	 * 
	 * @描述：TODO(修改岗位信息)
	 * @param station
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月4日下午4:35:00
	 * @修改人  ：lrz
	 * @修改时间：2017年4月4日下午4:35:00
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult modifyStation(Station station);
	/**
	 * 
	 * @描述：TODO(删除岗位)
	 * @param id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月4日下午5:27:44
	 * @修改人  ：lrz
	 * @修改时间：2017年4月4日下午5:27:44
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult deleteStation(String id);
	/**
	 * 
	 *
	 * @描述：TODO(获取某个岗位上的人员列表)
	 *
	 * @param id 岗位id
	 * @return
	 * List<Map<String,Object>>
	 * @创建人  ：lrz
	 * @创建时间：2017年4月5日上午9:32:22
	 * @修改人  ：lrz
	 * @修改时间：2017年4月5日上午9:32:22
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Map<String,Object>> getMemberByStation(String id);
	/**
	 * 
	 *
	 * @描述：TODO(添加职级)
	 *
	 * @param positionName
	 * @return
	 * BaseResult
	 * @创建人  ：lrz
	 * @创建时间：2017年4月5日上午11:35:01
	 * @修改人  ：lrz
	 * @修改时间：2017年4月5日上午11:35:01
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult addPosition(String positionName,String teamId);
	/**
	 * 
	 * @描述：TODO(获取职级列表)
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月9日上午1:43:36
	 * @修改人  ：lrz
	 * @修改时间：2017年4月9日上午1:43:36
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Position> getPositionList();
	/**
	 * 
	 * @描述：TODO(修改职级)
	 * @param position
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月9日上午3:04:28
	 * @修改人  ：lrz
	 * @修改时间：2017年4月9日上午3:04:28
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult modifyPosition(Position position);
	/**
	 * 
	 * @描述：TODO(删除职级)
	 * @param id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月9日上午3:34:41
	 * @修改人  ：lrz
	 * @修改时间：2017年4月9日上午3:34:41
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult deletePosition(String id);
	/**
	 * 
	 *
	 * @描述：TODO(通过职级id获取成员列表)
	 *
	 * @param id
	 * @return
	 * List<Map<String,Object>>
	 * @创建人  ：lrz
	 * @创建时间：2017年4月13日下午4:42:43
	 * @修改人  ：lrz
	 * @修改时间：2017年4月13日下午4:42:43
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Map<String, Object>> getMemberByPosition(String id);
	/**
	 * 
	 *
	 * @描述：TODO(获取成员的基本信息)
	 *
	 * @param id
	 * @return
	 * Map<String,Object>
	 * @创建人  ：lrz
	 * @创建时间：2017年4月11日上午10:42:19
	 * @修改人  ：lrz
	 * @修改时间：2017年4月11日上午10:42:19
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public Map<String,Object> getMemBaseInfo(String id);
	/**
	 * 
	 *
	 * @描述：TODO(获取成员的部门、岗位、职级和上级信息)
	 *
	 * @param id
	 * @return
	 * Map<String,List<Map<String,Object>>>
	 * @创建人  ：lrz
	 * @创建时间：2017年4月11日下午3:45:04
	 * @修改人  ：lrz
	 * @修改时间：2017年4月11日下午3:45:04
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public Map<String,List<Map<String,Object>>> getMemberPositionInfo(String id,String topTeamId);
	/**
	 * 
	 *
	 * @描述：TODO(修改成员基本信息)
	 *
	 * @param ubInfo
	 * void
	 * @创建人  ：lrz
	 * @创建时间：2017年4月13日下午2:41:37
	 * @修改人  ：lrz
	 * @修改时间：2017年4月13日下午2:41:37
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public void modifyMemberInfo(UserBaseInfo ubInfo);
	/**
	 * 
	 *
	 * @描述：TODO(修改成员的团队、岗位、职级信息)
	 *
	 * @param teamId
	 * @param stationId
	 * @param positionId
	 * void
	 * @创建人  ：lrz
	 * @创建时间：2017年4月13日下午2:48:26
	 * @修改人  ：lrz
	 * @修改时间：2017年4月13日下午2:48:26
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public void modifyMemberPositionInfo(String memberId, String teamId,
			String stationId, String positionId, String leaderId);
	/**
	 * 
	 * @描述：TODO(根据用户id获取默认展示团队)
	 * @param userId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月10日下午12:07:12
	 * @修改人  ：lrz
	 * @修改时间：2017年5月10日下午12:07:12
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public Team getDefaultTeamByUserId(String userId);
	/**
	 * 
	 * @描述：TODO(根据团队名获取团队)
	 * @param name
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年6月1日上午12:57:43
	 * @修改人  ：lrz
	 * @修改时间：2017年6月1日上午12:57:43
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Map<String, Object>> getTeamsByName(String name);
	/**
	 * 
	 * @描述：TODO(申请加入团队)
	 * @param teamId 团队id
	 * @param userId 用户id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午1:15:01
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午1:15:01
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult applyJoinTeam(String teamId,String userId);
	
	@SuppressWarnings("rawtypes")
	public BaseResult agreeApplyJoin(String userId,String teamId,String operationBy, String topTeamId);
	
	@SuppressWarnings("rawtypes")
	public BaseResult disagreeApplyJoin(String userId,String teamId,String operationBy, String topTeamId);
}
