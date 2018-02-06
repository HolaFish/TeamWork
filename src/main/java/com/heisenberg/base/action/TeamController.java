package com.heisenberg.base.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.heisenberg.base.model.MemberInfoVO;
import com.heisenberg.base.model.Position;
import com.heisenberg.base.model.Station;
import com.heisenberg.base.model.Team;
import com.heisenberg.common.resultmodel.BaseResult;
import com.heisenberg.common.tree.model.Node;

public interface TeamController {
	
	/**
	 * 
	 * 方法功能说明：跳转到团队管理  
	 * 创建：2017年3月8日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @return          
	 * @throws
	 */
	public String RedirectToTeam(Model model,String userId,String teamId);
	/**
	 * 
	 * 方法功能说明：跳转到团队结构  
	 * 创建：2017年3月12日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @return          
	 * @throws
	 */
	public String redirectToTeamArchitecture(Model model,String userId,String teamId);
	/**
	 * 
	 * @描述：TODO(跳转到岗位设置页面)
	 * @param model
	 * @param userId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月3日下午10:47:09
	 * @修改人  ：lrz
	 * @修改时间：2017年4月3日下午10:47:09
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToStation(Model model,String userId, String topTeamId);
	/**
	 * 
	 * 方法功能说明：  添加新团队
	 * 创建：2017年3月12日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @param team
	 *  @return          
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult addTeam(Team team,String operationBy,String topTeamId);
	/**
	 * 
	 *
	 * @描述：TODO(获取团队列表)
	 *
	 * @return
	 * Team[]
	 * @创建人  ：lrz
	 * @创建时间：2017年3月21日下午3:07:47
	 * @修改人  ：lrz
	 * @修改时间：2017年3月21日下午3:07:47
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public Node[] getTeamTree(String topTeamId);
	/**
	 * 
	 *
	 * @描述：TODO(获取全部团队列表)
	 *
	 * @return
	 * List<Team>
	 * @创建人  ：lrz
	 * @创建时间：2017年3月22日下午3:17:24
	 * @修改人  ：lrz
	 * @修改时间：2017年3月22日下午3:17:24
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Node> getTeamList(String topTeamId);
	/**
	 * 
	 *
	 * @描述：TODO(获取顶级团队列表)
	 *
	 * @return
	 * List<Team>
	 * @创建人  ：lrz
	 * @创建时间：2017年3月23日下午4:50:21
	 * @修改人  ：lrz
	 * @修改时间：2017年3月23日下午4:50:21
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Team> getTopTeamList(String userId);
//	/**
//	 * 
//	 *
//	 * @描述：TODO(获取一个团队中的团队)
//	 *
//	 * @param id
//	 * @return
//	 * List<Team>
//	 * @创建人  ：lrz
//	 * @创建时间：2017年3月23日下午4:51:15
//	 * @修改人  ：lrz
//	 * @修改时间：2017年3月23日下午4:51:15
//	 * @修改备注：
//	 * @version 1.0
//	 *
//	 */
//	public List<Team> getAllChildrenTeamList(String topTeamId);
	/**
	 * 
	 *
	 * @描述：TODO(修改团队信息)
	 *
	 * @param team
	 * @return
	 * BaseResult
	 * @创建人  ：lrz
	 * @创建时间：2017年3月22日下午5:09:51
	 * @修改人  ：lrz
	 * @修改时间：2017年3月22日下午5:09:51
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult modifyTeam(Team team,String operationBy, String topTeamId);
	/**
	 * 
	 *
	 * @描述：TODO(删除团队)
	 *
	 * @param id 团队ID
	 * @return
	 * BaseResult
	 * @创建人  ：lrz
	 * @创建时间：2017年3月23日上午10:36:52
	 * @修改人  ：lrz
	 * @修改时间：2017年3月23日上午10:36:52
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult deleteTeam(String id,String operationBy, String topTeamId);
	/**
	 * 
	 *
	 * @描述：TODO(跳转到邀请列表)
	 *
	 * @return
	 * String
	 * @创建人  ：lrz
	 * @创建时间：2017年3月23日下午2:17:06
	 * @修改人  ：lrz
	 * @修改时间：2017年3月23日下午2:17:06
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToInvitePeople(Model model,String userId,String topTeamId);
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
	 * @创建时间：2017年3月27日上午9:49:27
	 * @修改人  ：lrz
	 * @修改时间：2017年3月27日上午9:49:27
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
	 * List<Map>
	 * @创建人  ：lrz
	 * @创建时间：2017年3月27日下午3:51:47
	 * @修改人  ：lrz
	 * @修改时间：2017年3月27日下午3:51:47
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Map<String, Object>> getInviteList(String teamId,String inviterId);
	/**
	 * 
	 *
	 * @描述：TODO(邀请应答)
	 *
	 * @param model
	 * @param inviteId
	 * @return
	 * String
	 * @创建人  ：lrz
	 * @创建时间：2017年3月29日下午3:40:03
	 * @修改人  ：lrz
	 * @修改时间：2017年3月29日下午3:40:03
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String inviteAnwser(Model model, RedirectAttributes attr,String inviteId);
	/**
	 * 
	 * @描述：TODO(获取团队成员,包括下属团队成员)
	 * @param id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月3日下午5:16:48
	 * @修改人  ：lrz
	 * @修改时间：2017年4月3日下午5:16:48
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Map<String, Object>> getMember(String id);
	/**
	 * 
	 *
	 * @描述：TODO(获取该团队的成员)
	 *
	 * @param id 团队列表
	 * @return
	 * List<Map<String,Object>>
	 * @创建人  ：lrz
	 * @创建时间：2017年4月24日下午2:03:33
	 * @修改人  ：lrz
	 * @修改时间：2017年4月24日下午2:03:33
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
	 * @创建时间：2017年4月3日下午11:18:08
	 * @修改人  ：lrz
	 * @修改时间：2017年4月3日下午11:18:08
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult addStation (Station station,String operationBy, String topTeamId);
	/**
	 * 
	 * @描述：TODO(获取岗位树)
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月4日下午1:41:21
	 * @修改人  ：lrz
	 * @修改时间：2017年4月4日下午1:41:21
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public Node[] getStationTree(String topTeamId);
	/**
	 * 
	 * @描述：TODO(获取跟某个岗位具有相同顶级岗位的岗位)
	 * @param id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月4日下午3:27:13
	 * @修改人  ：lrz
	 * @修改时间：2017年4月4日下午3:27:13
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
	 * @创建时间：2017年4月4日下午4:33:41
	 * @修改人  ：lrz
	 * @修改时间：2017年4月4日下午4:33:41
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult modifyStation(Station station,String operationBy, String topTeamId);
	/**
	 * 
	 * @描述：TODO(删除岗位)
	 * @param id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月4日下午5:26:34
	 * @修改人  ：lrz
	 * @修改时间：2017年4月4日下午5:26:34
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult deleteStation(String id,String operationBy, String topTeamId);
	/**
	 * 
	 *
	 * @描述：TODO(获取某个岗位上的 人员列表)
	 *
	 * @param id
	 * @return
	 * List<Map<String,Object>>
	 * @创建人  ：lrz
	 * @创建时间：2017年4月5日上午9:30:38
	 * @修改人  ：lrz
	 * @修改时间：2017年4月5日上午9:30:38
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Map<String,Object>> getMemberByStation(String id); 
	/**
	 * 
	 *
	 * @描述：TODO(跳转到职级设置)
	 *
	 * @return
	 * String
	 * @创建人  ：lrz
	 * @创建时间：2017年4月5日上午10:14:39
	 * @修改人  ：lrz
	 * @修改时间：2017年4月5日上午10:14:39
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToPosition(Model model, String id,String topTeamId);
	/**
	 * 
	 *
	 * @描述：TODO(添加职级)
	 *
	 * @param positionName
	 * @return
	 * BaseResult
	 * @创建人  ：lrz
	 * @创建时间：2017年4月5日上午11:33:42
	 * @修改人  ：lrz
	 * @修改时间：2017年4月5日上午11:33:42
	 * @修改备注：
	 * @version 1.0
	 * @throws IOException 
	 * @throws ServletException 
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult addPosition(Position position,String operationBy, String topTeamId);
	/**
	 * 
	 * @描述：TODO(获取职级列表)
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月9日上午1:42:21
	 * @修改人  ：lrz
	 * @修改时间：2017年4月9日上午1:42:21
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
	 * @创建时间：2017年4月9日上午3:03:09
	 * @修改人  ：lrz
	 * @修改时间：2017年4月9日上午3:03:09
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult modifyPosition(Position position,String operationBy, String topTeamId);
	/**
	 * 
	 * @描述：TODO(删除职级)
	 * @param id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月9日上午3:33:12
	 * @修改人  ：lrz
	 * @修改时间：2017年4月9日上午3:33:12
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult deletePosition(String id,String operationBy, String topTeamId);
	/**
	 * 
	 *
	 * @描述：TODO(根据职级id获取成员)
	 *
	 * @param id
	 * @return
	 * List<Map<String,Object>>
	 * @创建人  ：lrz
	 * @创建时间：2017年4月13日下午4:40:55
	 * @修改人  ：lrz
	 * @修改时间：2017年4月13日下午4:40:55
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Map<String,Object>> getMemberByPosition(String id);
	/**
	 * 
	 *
	 * @描述：TODO(跳转到修改团队成员页面)
	 *
	 * @param id
	 * @return
	 * String
	 * @创建人  ：lrz
	 * @创建时间：2017年4月10日上午9:33:11
	 * @修改人  ：lrz
	 * @修改时间：2017年4月10日上午9:33:11
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToEditMember(Model model,String userId,String teamMateId,String topTeamId);
	/**
	 * 
	 *
	 * @描述：TODO(获取成员的部门、岗位、职级和上级信息)
	 *
	 * @param id
	 * @return
	 * Map<String,List<Map<String,Object>>>
	 * @创建人  ：lrz
	 * @创建时间：2017年4月11日下午3:43:17
	 * @修改人  ：lrz
	 * @修改时间：2017年4月11日下午3:43:17
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
	 * @param memberInfo
	 * @return
	 * BaseResult
	 * @创建人  ：lrz
	 * @创建时间：2017年4月13日下午2:19:29
	 * @修改人  ：lrz
	 * @修改时间：2017年4月13日下午2:19:29
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult modifyMemberInfo(MemberInfoVO memberInfo,String operationBy, String topTeamId);
	/**
	 * 
	 * @描述：TODO(根据团队名获取团队)
	 * @param teamName
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年6月1日上午12:56:02
	 * @修改人  ：lrz
	 * @修改时间：2017年6月1日上午12:56:02
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Map<String, Object>> getTeamsByName(String teamName);
	/**
	 * 
	 * @描述：TODO(申请加入团队)
	 * @param teamId 团队id
	 * @param userId 用户id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午1:12:12
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午1:12:12
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult applyJoinTeam(String teamId,String userId);
	/**
	 * 
	 * @描述：TODO(这里用一句话描述这个方法的作用)
	 * @param userId
	 * @param teamId
	 * @param operationBy
	 * @param topTeamId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月15日下午12:31:29
	 * @修改人  ：lrz
	 * @修改时间：2017年5月15日下午12:31:29
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult agreeApplyJoin(String userId,String teamId,String operationBy, String topTeamId);
	
	@SuppressWarnings("rawtypes")
	public BaseResult disagreeApplyJoin(String userId,String teamId,String operationBy, String topTeamId);
}
