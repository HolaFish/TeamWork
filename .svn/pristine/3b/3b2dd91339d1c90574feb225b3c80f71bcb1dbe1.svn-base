package com.heisenberg.base.action.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.heisenberg.base.action.TeamController;
import com.heisenberg.base.model.MemberInfoVO;
import com.heisenberg.base.model.Position;
import com.heisenberg.base.model.Station;
import com.heisenberg.base.model.Team;
import com.heisenberg.base.model.User;
import com.heisenberg.base.model.UserBaseInfo;
import com.heisenberg.base.service.TeamService;
import com.heisenberg.common.resultmodel.BaseResult;
import com.heisenberg.common.tree.model.Node;
@Controller
public class TeamControllerImpl implements TeamController{
	@Autowired
	private TeamService service;
	
	@Value("${TYPE_SUCCESS}")
	private String SUCCESS_CODE;
	@Value("${SUCCESS}")
	private String SUCCESS;
	@Value("${TYPE_WARM}")
	private String WARM;
	@Value("${TYPE_ERROR}")
	private String ERROR;
	@Value("${TEAM_EXIST}")
	private String TEAM_EXIST;
	@Value("${TRY_AGAIN}")
	private String TRY_AGAIN;
	@Value("${INVITE_SUCCESS}")
	private String INVITE_SUCCESS;
	/**
	 * 
	 * 方法功能说明：跳转到团队管理  
	 * 创建：2017年3月8日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @return          
	 * @throws
	 */
	@RequestMapping("redirectToTeam/{userId}/{teamId}")
	@Override
	public String RedirectToTeam(Model model,@PathVariable String userId,@PathVariable String teamId) {
		model.addAttribute("userId", userId);
		model.addAttribute("teamId", teamId);
		return "team/team_main";
	}
	
	/**
	 * 
	 * @描述：TODO(跳转到邀请列表)
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年3月23日下午2:17:35
	 * @修改人  ：lrz
	 * @修改时间：2017年3月23日下午2:17:35
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToInvitePeople/{userId}/{topTeamId}")
	@Override
	public String redirectToInvitePeople(Model model,@PathVariable String userId,@PathVariable String topTeamId) {
		model.addAttribute("userId", userId);
		model.addAttribute("teamId", topTeamId);
		return "team/team_invite";
	}
	
	/**
	 * 
	 * @描述：TODO(跳转到岗位设置页面)
	 * @param model
	 * @param userId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月3日下午10:48:41
	 * @修改人  ：lrz
	 * @修改时间：2017年4月3日下午10:48:41
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToStation/{userId}/{topTeamId}")
	@Override
	public String redirectToStation(Model model, @PathVariable String userId,@PathVariable String topTeamId) {
		model.addAttribute("userId", userId);
		model.addAttribute("teamId", topTeamId);
		return "team/team_station";
	}

	/**
	 * 
	 * 方法功能说明：跳转到团队结构  
	 * 创建：2017年3月12日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @return          
	 * @throws
	 */
	@RequestMapping("redirectToTeamArchitecture/{userId}/{teamId}")
	@Override
	public String redirectToTeamArchitecture(Model model,@PathVariable String userId,@PathVariable String teamId) {
		model.addAttribute("teamId", teamId);
		model.addAttribute("userId", userId);
		//获取团队成员
		List<Map<String,Object>> teamMember = this.getMember(teamId);
		model.addAttribute("teamMember", teamMember);
		return "team/team_arc";
	}
	
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("addTeam")
	@ResponseBody
	@Override
	public BaseResult addTeam(Team team,String operationBy,String topTeamId) {
		//判断团队名称,团队编码是否重复是否已经存在
		boolean isExist = service.isTeamExist(team);
		if (isExist){
			//已经存在
			BaseResult result = new BaseResult();
			result.setObj(team);
			result.setResultType(WARM);
			result.setMessage(TEAM_EXIST);
			return result;
		}
		return service.addTeam(team);
	}
	/**
	 * 
	 * @描述：TODO(获取团队列表)
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年3月21日下午4:20:57
	 * @修改人  ：lrz
	 * @修改时间：2017年3月21日下午4:20:57
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("getTeamTree")
	@ResponseBody
	@Override
	public Node[] getTeamTree(String topTeamId) {
		return service.getTeamTree(topTeamId);
	}
	/**
	 * 
	 * @描述：TODO(获取团队列表)
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年3月22日下午3:17:57
	 * @修改人  ：lrz
	 * @修改时间：2017年3月22日下午3:17:57
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("getTeamList")
	@ResponseBody
	@Override
	public List<Node> getTeamList(String topTeamId) {
		return service.getTeamList(topTeamId);
	}
	/**
	 * 
	 * @描述：TODO(获取顶级团队列表)
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年3月23日下午4:51:48
	 * @修改人  ：lrz
	 * @修改时间：2017年3月23日下午4:51:48
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("getTopTeams")
	@ResponseBody
	@Override
	public List<Team> getTopTeamList(String userId) {
		return service.getTopTeamList(userId);
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	/**
	 * 
	 * @描述：TODO(修改团队)
	 * @param team
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年3月22日下午5:21:22
	 * @修改人  ：lrz
	 * @修改时间：2017年3月22日下午5:21:22
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("modifyTeam")
	@ResponseBody
	@Override
	public BaseResult modifyTeam(Team team,String operationBy, String topTeamId) {
		BaseResult result = new BaseResult();
		//传过来的team中parentid 由parentid}|{level-1组成
		String[] idLevel = team.getParentId().split("\\}\\|\\{");
		team.setParentId(idLevel[0]);
		team.setTeamLevel(Integer.parseInt(idLevel[1])-1);
		//判断团队名称是否已经存在
		boolean isExist = service.isTeamExist(team);
		if (isExist){
			//已经存在
			result.setResultType(WARM);
			result.setMessage(TEAM_EXIST);
			return result;
		}
		//修改团队
		int count = service.ModifyTeam(team);
		if (count > 0){
			result.setResultType(SUCCESS_CODE);
			result.setObj(team);
			
		}else{
			result.setResultType(ERROR);
			result.setMessage(TRY_AGAIN);
		}
		
		return result;
	}
	/**
	 * 
	 * @描述：TODO(删除团队)
	 * @param id 团队id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年3月23日上午10:37:29
	 * @修改人  ：lrz
	 * @修改时间：2017年3月23日上午10:37:29
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("deleteTeam")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult deleteTeam(String id,String operationBy, String topTeamId) {
		BaseResult result = new BaseResult();
		int count = service.deleteTeam(id);
		if (count > 0){
			result.setResultType(SUCCESS_CODE);
		}else{
			result.setResultType(ERROR);
			result.setMessage(TRY_AGAIN);
		}
		return result;
	}
	/**
	 * 
	 * @描述：TODO(邀请新成员加入团队)
	 * @param inviterId	邀请人ID	
	 * @param inviteeName	被邀请人姓名	
	 * @param inviteeEMail	被邀请人email
	 * @param teamId	团队ID
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年3月27日上午9:50:25
	 * @修改人  ：lrz
	 * @修改时间：2017年3月27日上午9:50:25
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("invitePeople")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult invitePeople(String inviterId, String inviteeName,
			String inviteeEMail, String teamId) {
		BaseResult result = service.invitePeople(inviterId, inviteeName, inviteeEMail, teamId);
		return result;
	}
	/**
	 * 
	 * @描述：TODO(获取用户的邀请列表)
	 * @param teamId	团队ID	
	 * @param inviterId	邀请人ID
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年3月27日下午3:53:00
	 * @修改人  ：lrz
	 * @修改时间：2017年3月27日下午3:53:00
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("getInviteList")
	@ResponseBody
	@Override
	public List<Map<String, Object>> getInviteList(String teamId,
			String inviterId) {
		
		return service.getInviteList(teamId, inviterId);
	}
	/**
	 *     
	 * @描述：TODO(邀请应答)
	 * @param model
	 * @param inviteId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年3月29日下午3:40:48
	 * @修改人  ：lrz
	 * @修改时间：2017年3月29日下午3:40:48
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("inviteAnwser/{inviteId}")
	@Override
	public String inviteAnwser(Model model,RedirectAttributes attr, @PathVariable String inviteId) {
		BaseResult<User> anwserResult = service.inviteAnwser(inviteId);
		if (anwserResult.getResultType().equals(ERROR)){
			model.addAttribute("type", 2);
			model.addAttribute("message", "出现未知错误，请重试！");
			return "team/error";
		}else if (anwserResult.getResultType().equals(WARM)){
			model.addAttribute("type", 1);
			model.addAttribute("message", "此路径无效，请确认！");
			return "team/error";
		}else{
			return "redirect:/main/"+anwserResult.getObj().getID();
		}
		
	}
	/**
	 * 
	 * @描述：TODO(获取团队成员，包括下属团队的成员)
	 * @param id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月3日下午5:17:50
	 * @修改人  ：lrz
	 * @修改时间：2017年4月3日下午5:17:50
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("getMember")
	@ResponseBody
	@Override
	public List<Map<String, Object>> getMember(String id) {
		return service.getMemeber(id);
	}
	/**
	 * 
	 * @描述：TODO(获取团队成员列表)
	 * @param id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月24日下午2:04:12
	 * @修改人  ：lrz
	 * @修改时间：2017年4月24日下午2:04:12
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("getTeamMember")
	@ResponseBody
	@Override
	public List<Map<String, Object>> getTeamMember(String id) {
		return service.getTeamMember(id);
	}

	/**
	 * 
	 * @描述：TODO(添加岗位)
	 * @param station
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月3日下午11:18:30
	 * @修改人  ：lrz
	 * @修改时间：2017年4月3日下午11:18:30
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("addStation")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult addStation(Station station,String operationBy, String topTeamId) {
		
		return service.addStation(station);
	}
	/**
	 * 
	 * @描述：TODO(获取岗位树)
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月4日下午1:41:49
	 * @修改人  ：lrz
	 * @修改时间：2017年4月4日下午1:41:49
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("getStationTree")
	@ResponseBody
	@Override
	public Node[] getStationTree(String topTeamId) {
		return service.getStationTree(topTeamId);
	}
	/**
	 * 
	 * @描述：TODO(获取跟某个岗位具有相同顶级岗位的岗位)
	 * @param id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月4日下午3:28:16
	 * @修改人  ：lrz
	 * @修改时间：2017年4月4日下午3:28:16
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("getInnerStation")
	@ResponseBody
	@Override
	public List<Node> getInnerStation(String id) {
		return service.getInnerStation(id);
	}
	/**
	 * 
	 * @描述：TODO(修改岗位信息)
	 * @param station
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月4日下午4:34:03
	 * @修改人  ：lrz
	 * @修改时间：2017年4月4日下午4:34:03
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("modifyStation")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult modifyStation(Station station,String operationBy, String topTeamId) {
		String[] idLevel = station.getParentId().split("\\}\\|\\{");
		station.setParentId(idLevel[0]);
		station.setStationLevel(Integer.parseInt(idLevel[1])-1);
		return service.modifyStation(station);
	}
	/**
	 * 
	 * @描述：TODO(删除岗位)
	 * @param id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月4日下午5:27:01
	 * @修改人  ：lrz
	 * @修改时间：2017年4月4日下午5:27:01
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("deleteStation")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult deleteStation(String id,String operationBy, String topTeamId) {
		return service.deleteStation(id);
	}
	/**
	 * 
	 * @描述：TODO(获取某个岗位上的人员列表)
	 * @param id 岗位id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月5日上午9:31:05
	 * @修改人  ：lrz
	 * @修改时间：2017年4月5日上午9:31:05
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("getMemberByStation")
	@ResponseBody
	@Override
	public List<Map<String, Object>> getMemberByStation(String id) {
		return service.getMemberByStation(id);
	}
	/**
	 * 
	 * @描述：TODO(跳转到职级设置)
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月5日上午10:15:09
	 * @修改人  ：lrz
	 * @修改时间：2017年4月5日上午10:15:09
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToPosition/{id}/{topTeamId}")
	@Override
	public String redirectToPosition(Model model, @PathVariable String id,@PathVariable String topTeamId) {
		model.addAttribute("userId", id);
		model.addAttribute("teamId",topTeamId);
		return "team/team_position";
	}
	/**
	 * 
	 * @描述：TODO(添加职级)
	 * @param positionName
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月5日上午11:34:00
	 * @修改人  ：lrz
	 * @修改时间：2017年4月5日上午11:34:00
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("addPostion")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult addPosition(Position position,String operationBy, String topTeamId){
		return service.addPosition(position.getName(),topTeamId);
	}
	/**
	 * 
	 * @描述：TODO(获取职级列表)
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月9日上午1:42:45
	 * @修改人  ：lrz
	 * @修改时间：2017年4月9日上午1:42:45
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("getPosition")
	@ResponseBody
	@Override
	public List<Position> getPositionList() {
		return service.getPositionList();
	}
	/**
	 * 
	 * @描述：TODO(修改职级)
	 * @param position
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月9日上午3:09:21
	 * @修改人  ：lrz
	 * @修改时间：2017年4月9日上午3:09:21
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("modifyPosition")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult modifyPosition(Position position,String operationBy, String topTeamId) {
		return service.modifyPosition(position);
	}
	/**
	 * 
	 * @描述：TODO(删除职级)
	 * @param id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月9日上午3:33:37
	 * @修改人  ：lrz
	 * @修改时间：2017年4月9日上午3:33:37
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("deletePosition")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult deletePosition(String id,String operationBy, String topTeamId) {
		return service.deletePosition(id);
	}
	
	/**
	 * 
	 * @描述：TODO(通过职级id获取成员列表)
	 * @param id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月13日下午4:41:52
	 * @修改人  ：lrz
	 * @修改时间：2017年4月13日下午4:41:52
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("getMemberByPosition")
	@ResponseBody
	@Override
	public List<Map<String, Object>> getMemberByPosition(String id) {
		return service.getMemberByPosition(id);
	}

	/**
	 * 
	 * @描述：TODO(跳转到修改团队成员列表)
	 * @param id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月10日上午9:33:41
	 * @修改人  ：lrz
	 * @修改时间：2017年4月10日上午9:33:41
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToEditMember")
	@Override
	public String redirectToEditMember(Model model,String userId,String teamMateId,String topTeamId) {
		Map<String,Object> baseInfo = service.getMemBaseInfo(teamMateId);
		model.addAttribute("baseInfo", baseInfo);
		model.addAttribute("userId", userId);
		model.addAttribute("memId", teamMateId);
		model.addAttribute("teamId", topTeamId);
		return "team/team_memberEdit";
	}
	/**
	 * 
	 * @描述：TODO(获取成员的部门、岗位、职级和上级信息)
	 * @param id 成员 id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月11日下午3:43:56
	 * @修改人  ：lrz
	 * @修改时间：2017年4月11日下午3:43:56
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("getMemberPositionInfo")
	@ResponseBody
	@Override
	public Map<String,List<Map<String,Object>>> getMemberPositionInfo(String id,String topTeamId){
		return service.getMemberPositionInfo(id,topTeamId);
	}
	/**
	 * 
	 * @描述：TODO(修改成员基本信息)
	 * @param memberInfo
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月13日下午2:25:27
	 * @修改人  ：lrz
	 * @修改时间：2017年4月13日下午2:25:27
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("modifyMemberInfo")
	@ResponseBody
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public BaseResult modifyMemberInfo(MemberInfoVO memberInfo,String operationBy, String topTeamId) {
		BaseResult result = new BaseResult(SUCCESS_CODE,SUCCESS);
		UserBaseInfo ubInfo = new UserBaseInfo();
		ubInfo.setId(memberInfo.getId());
		ubInfo.setName(memberInfo.getName());
		ubInfo.setGender(memberInfo.getGender());
		ubInfo.setTelNumber(memberInfo.getTelNumber());
		ubInfo.setEntryTime(memberInfo.getEntryTime());
		ubInfo.setEmployeeNum(memberInfo.getEmployeNum());
		
		service.modifyMemberInfo(ubInfo);
		service.modifyMemberPositionInfo(memberInfo.getId(), memberInfo.getTeam(), memberInfo.getStation(), memberInfo.getPosition(),memberInfo.getLeader());
		result.setObj(ubInfo);
		return result;
	}
	/**
	 * 
	 * @描述：TODO(根据团队名获取团队)
	 * @param teamName
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午12:56:45
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午12:56:45
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("getTeamsByName")
	@ResponseBody
	@Override
	public List<Map<String,Object>> getTeamsByName(String teamName) {
		return service.getTeamsByName(teamName);
	}
	/**
	 * 
	 * @描述：TODO(申请加入团队)
	 * @param teamId 团队id
	 * @param userId 用户id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午1:12:44
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午1:12:44
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("applyJoinTeam")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult applyJoinTeam(String teamId,String userId) {
		return service.applyJoinTeam(teamId, userId);
	}
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
	@RequestMapping("agreeApplyJoin")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult agreeApplyJoin(String userId,String teamId,String operationBy, String topTeamId){
		return service.agreeApplyJoin(userId, teamId, operationBy, topTeamId);
	}
	@RequestMapping("disagreeApplyJoin")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult disagreeApplyJoin(String userId,String teamId,String operationBy, String topTeamId){
		return service.disagreeApplyJoin(userId, teamId, operationBy, topTeamId);
	}
}
