package com.heisenberg.base.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.heisenberg.base.dao.SpringJDBCBaseDao;
import com.heisenberg.base.model.Position;
import com.heisenberg.base.model.Station;
import com.heisenberg.base.model.Team;
import com.heisenberg.base.model.User;
import com.heisenberg.base.model.UserBaseInfo;
import com.heisenberg.base.service.TeamService;
import com.heisenberg.common.email.model.E_MailProp;
import com.heisenberg.common.email.service.E_MailService;
import com.heisenberg.common.resultmodel.BaseResult;
import com.heisenberg.common.tree.model.Node;
import com.heisenberg.common.tree.util.TreeUtil;
import com.heisenberg.common.util.ObjectRelationalMapper;
import com.heisenberg.common.util.service.impl.CommonServiceImpl;


@Service
public class TeamServiceImpl extends CommonServiceImpl implements TeamService {
	@Autowired
	private SpringJDBCBaseDao baseDao;
	@Autowired
	private E_MailService emailService;
	@Value("${TYPE_SUCCESS}")
	private String SUCCESS_CODE;
	@Value("${SUCCESS}")
	private String SUCCESS;
	@Value("${TYPE_WARM}")
	private String WARM;
	@Value("${TYPE_ERROR}")
	private String ERROR;
	@Value("${TRY_AGAIN}")
	private String TRY_AGAIN;

	@Value("${e_mailAddress}")
	private String EMAIL_FROM;
	@Value("${e_mailPassword}")
	private String EMAIL_PASSWORD;
	@Value("${e_mailHost}")
	private String EMAIL_HOST;
	@Value("${INVITE_MESSAGE_NO_RIGIST}")
	private String INVITE_MESSAGE_NO_RIGIST;
	@Value("${INVITE_MESSAGE}")
	private String INVITE_MESSAGE;
	@Value("${INVITE_URL}")
	private String INVITE_URL;
	@Value("${IS_ALREADY_INVITED}")
	private String IS_ALREADY_INVITED;
	@Value("${INVITE_SUCCESS}")
	private String INVITE_SUCCESS;
	@Value("${TREE_URL}")
	private String TREE_URL;
	@Value("${STATION_EXIST}")
	private String STATION_EXIST;
	@Value("${POSITION_EXIST}")
	private String POSITION_EXIST;
	@Value("${APPLY_SUCCESS}")
	private String APPLY_SUCCESS;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 
	 * 方法功能说明：判断具有相同父节点的部门或者团队名和团队编码是否已经存在
	 * 创建：2017年3月12日 by 刘润枝 
	 * 修改：日期 by 修改者
	 * 修改内容：
	 * 
	 * @param team
	 * @return
	 * @throws
	 */
	@Override
	public boolean isTeamExist(Team team) {
		
		String[] params = new String[2];
		String sql = "";
		if (StringUtils.isBlank(team.getId())){
			sql = " select count(1) from tb_team where  1=1";
		}else{
			sql = " select count(1) from tb_team where  id != '" + team.getId() +"'";
		}
		
		/*
		 * 不是顶级团队，兄弟团队之间名称不重复
		 * 顶级团队，同一负责人，名称不能重复
		 */
		if (!StringUtils.isBlank(team.getParentId())){
			sql += " and parentId=? and name=?";
			params[0] = team.getParentId();
			params[1] = team.getName();
		}else{
			sql += " and leaderId=? and name=? ";
			params[0] = team.getLeaderId();
			params[1] = team.getName();
		}
		int count = (int) baseDao.queryUniqueObject(sql, params, Integer.class);
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * 方法功能说明：添加新团队 
	 * 创建：2017年3月12日 by 刘润枝 
	 * 修改：日期 by 修改者 修改内容：
	 * 
	 * @param team
	 * @return
	 * @throws
	 */

	@Override
	public BaseResult<Team> addTeam(Team team) {
		BaseResult<Team> result = new BaseResult<Team>();
		String sql = " insert into tb_team(id,name,teamLevel,parentId,teamCode,sort,remark,leaderId)values(?,?,?,?,?,?,?,?) ";
		// 生成id
		String id = UUID.randomUUID().toString();
		team.setId(id);
		int teamLevel = StringUtils.isBlank(String.valueOf(team.getTeamLevel())) ? 0 :team.getTeamLevel();
		Object[] params = { id, team.getName(), teamLevel,
				team.getParentId(), team.getTeamCode(), team.getSort() == 0 ? 999: team.getSort(),
				team.getRemark(), team.getLeaderId() };
		int count = baseDao.insert(sql, params);
		if (count > 0) {
			// 添加成功
			result.setResultType(SUCCESS_CODE);
			result.setObj(team);
			//如果用户配置表中的默认团队为空，配置默认团队
			sql = " select * from tb_userConfig where id = ? ";
			List<Map<String,Object>> userConfigs = baseDao.queryMapInList(sql, new Object[]{team.getLeaderId()});
			if (userConfigs.size() > 0){
				Map<String,Object> userConfig = userConfigs.get(0);
				if (userConfig.get("DEFAULTTEAM")==null){
					sql = " update tb_userConfig set showTeam = ? where id = ? ";
					baseDao.update(sql, new Object[]{id,team.getLeaderId()});
				}
			}
			
			return result;
		}
		result.setResultType(ERROR);
		result.setMessage(TRY_AGAIN);
		return result;
	}

	/**
	 * 
	 * @描述：TODO(获取团队列表)
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年3月21日下午3:09:43
	 * @修改人 ：lrz
	 * @修改时间：2017年3月21日下午3:09:43
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@Override
	public Node[] getTeamTree(String topTeamId) {
		/*
		 * 查询全部的子节点节点
		 */
		List<Node> teams = this.getTeamList(topTeamId);
		/*
		 * 解析成树结构
		 */
		Node[] result = TreeUtil.analyzeForest(teams);
		return result;
	}

	/**
	 * 
	 * @描述：TODO(根据顶级团队id获取下级团队)
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年3月22日下午3:19:07
	 * @修改人 ：lrz
	 * @修改时间：2017年3月22日下午3:19:07
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Node> getTeamList(String topTeamId) {
		String Sql = "select * from " +
						"       tb_team " + 
						"    start with id = ? " + 
						"    connect by prior id = parentid";
		List<Node> result = baseDao.queryToList(Sql, new Object[] {topTeamId},
				new ObjectRelationalMapper("com.heisenberg.base.model.Team"));
		return result;
	}

	/**
	 * 
	 * @描述：TODO(获取顶级团队列表)
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年3月23日下午5:00:01
	 * @修改人 ：lrz
	 * @修改时间：2017年3月23日下午5:00:01
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Team> getTopTeamList(String userId) {
		String Sql = "select t.* " +
						"  from TB_TEAM t\n" + 
						" where t.leaderid = ? " + 
						"union " + 
						"SELECT t.* " + 
						"  FROM TB_TEAM t " + 
						" where t.teamlevel = 0 " + 
						" START WITH id in " + 
						"            (select depid " + 
						"               from tb_teammember tm " + 
						"              where tm.memberid = ?) " + 
						"CONNECT BY ID = PRIOR PARENTID";

		return baseDao.queryToList(Sql, new Object[] {userId,userId},
				new ObjectRelationalMapper("com.heisenberg.base.model.Team"));
	}


	/**
	 * 
	 * 
	 * @描述：TODO(获取某个团队的顶级团队)
	 * 
	 * @param team
	 * @param teams
	 * @return Team
	 * @创建人 ：lrz
	 * @创建时间：2017年3月23日下午5:27:01
	 * @修改人 ：lrz
	 * @修改时间：2017年3月23日下午5:27:01
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	public Team getTopTeam(Team team, List<Team> teams) {
		if (StringUtils.isEmpty(team.getParentId())) {
			return team;
		}
		Team topTeam = null;
		for (Team topteam : teams) {
			if (topteam.getId().equals(team.getParentId())) {
				if (StringUtils.isEmpty(topteam.getParentId())) {
					topTeam = topteam;
					break;
				} else {
					topTeam = getTopTeam(topteam, teams);
				}
			}
		}
		return topTeam;
	}

	/**
	 * 
	 * 
	 * @描述：TODO(获取一个团队的所有下属团队)
	 * 
	 * @param topTeam
	 * @param teams
	 * @param innerTeams
	 * @return List<Team>
	 * @创建人 ：lrz
	 * @创建时间：2017年3月23日下午5:27:31
	 * @修改人 ：lrz
	 * @修改时间：2017年3月23日下午5:27:31
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	public List<Team> getInnerTeams(Team parentTeam, List<Team> teams,
			List<Team> innerTeams) {

		for (Team team : teams) {
			if (!StringUtils.isEmpty(team.getParentId())) {
				if (team.getParentId().equals(parentTeam.getId())) {
					innerTeams = this.getInnerTeams(team, teams, innerTeams);
					innerTeams.add(team);
				}

			}
		}
		return innerTeams;
	}

	/**
	 * 
	 * @描述：TODO(修改团队信息)
	 * @param team
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年3月22日下午5:13:45
	 * @修改人 ：lrz
	 * @修改时间：2017年3月22日下午5:13:45
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@Override
	public int ModifyTeam(Team team) {
		String Sql = " update tb_team set name=?,teamCode=?,sort=?,parentId=?,leaderId=?,remark=? where id=? ";
		int count = baseDao.update(Sql,new Object[] { team.getName(), team.getTeamCode(),team.getSort(), team.getParentId(),team.getLeaderId(), team.getRemark(),team.getId() });
		return count;
	}

	/**
	 * 
	 * @描述：TODO(删除团队)
	 * @param id
	 *            团队ID
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年3月23日上午10:39:50
	 * @修改人 ：lrz
	 * @修改时间：2017年3月23日上午10:39:50
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@Override
	public int deleteTeam(String id) {
		//删除团队及下属团队的成员信息
		String Sql = "delete from tb_teamMember where depid in (select id from tb_team where parentId=? or id=? )";
		baseDao.delete(Sql, new Object[]{id,id});
		// 删除团队及下属团队
		Sql = " delete from tb_team  where parentId=? or id=? ";
		int count = baseDao.delete(Sql, new Object[] { id, id });
		return count;
	}

	/**
	 * 
	 * @描述：TODO(邀请新成员加入团队)
	 * 
	 * @param inviterId
	 *            邀请人id
	 * @param inviteeName
	 *            被邀请人姓名
	 * @param inviteeEMail
	 *            被邀请人email
	 * @param teamId
	 *            团队ID
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年3月27日上午9:52:48
	 * @修改人 ：lrz
	 * @修改时间：2017年3月27日上午9:52:48
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult invitePeople(String inviterId, String inviteeName,
			String inviteeEMail, String teamId) {
		BaseResult baseResult = new BaseResult();
		String emailMessage = "";
		String inviterName = "";
		String teamName = "";
		String inviteeId = null;
		Date date = new Date();
		String inviteTime = sdf.format(date);

		// 根据用户ID和团队ID获取用户名和团队名
		String Sql = " select a.name as userName,b.name as teamName from tb_baseInfo a, tb_team b where a.id=? and b.id=?";
		List<Map<String, Object>> names = baseDao.queryMapInList(Sql,
				new Object[] { inviterId, teamId });
		if (names != null && names.size() != 0) {
			inviterName = names.get(0).get("USERNAME").toString();
			teamName = names.get(0).get("TEAMNAME").toString();
		} else {
			baseResult.setMessage(TRY_AGAIN);
			baseResult.setResultType(ERROR);
			return baseResult;
		}
		/**
		 * 设置发送邮件的参数
		 */
		E_MailProp emp = new E_MailProp();
		emp.setAuth("true");
		emp.setFrom(EMAIL_FROM);
		emp.setTo(new String[] { inviteeEMail });
		emp.setHost(EMAIL_HOST);
		emp.setPassWord(EMAIL_PASSWORD);
		emp.setTimeuot("5000");
		emp.setUserName(EMAIL_FROM);
		emp.setSubject("TeamWork:"+inviterName + "邀请你加入：" + teamName);
		// 生成邀请码
		String inviteId = UUID.randomUUID().toString();
		// 查询邀请表，判断是否存在邀请信息
		Sql = " select * from tb_invite where inviterId=? and inviteeEMail=? and teamId=? ";
		List<Map<String, Object>> inviteInfos = baseDao.queryMapInList(Sql,
				new Object[] { inviterId, inviteeEMail, teamId });

		// 获取被邀请人信息
		Sql = " select id from tb_baseInfo where email=?";
		List<Map<String, Object>> inviteeIds = baseDao.queryMapInList(Sql,
				new Object[] { inviteeEMail });

		if (inviteInfos != null && inviteInfos.size() != 0) {
			// 已经被邀请
			String inviteStatus = inviteInfos.get(0).get("INVITESTATUS")
					.toString();
			if ("1".equals(inviteStatus)) {
				// 已经接受
				baseResult.setMessage(IS_ALREADY_INVITED);
				baseResult.setResultType(WARM);
				return baseResult;
			}
			// 未接受
			inviteeId = inviteeIds.get(0).get("ID").toString();
			/*
			 * 设置邮件内容
			 */
			emailMessage = INVITE_MESSAGE;
			emailMessage = emailMessage.replace("{INVITEENAME}", inviteeName);
			emailMessage = emailMessage.replace("{INVITERNAME}", inviterName);
			emailMessage = emailMessage.replace("{TEAMNAME}", teamName);
			emailMessage = emailMessage.replace("{INVITEANWSERURL}", INVITE_URL
					+ inviteId);
			emp.setText(emailMessage);
			// 更新邀请时间
			Sql = " update tb_invite set inviteTime=? where inviterId=? and inviteeId=? and teamId=? ";
			int count = baseDao.update(Sql, new Object[] { inviteTime,
					inviterId, inviteeId, teamId });
			if (count == 0) {
				baseResult.setMessage(TRY_AGAIN);
				baseResult.setResultType(ERROR);
				return baseResult;
			}
		} else {
			// 未被邀请
			/*
			 * 判断被邀请成员是否注册
			 */
			if (inviteeIds == null || inviteeIds.size() == 0) {
				// 被邀请成员未注册
				// 设置邮件内容
				emailMessage = INVITE_MESSAGE_NO_RIGIST;
				emailMessage = emailMessage.replace("{INVITEENAME}",
						inviteeName);
				emailMessage = emailMessage.replace("{INVITERNAME}",
						inviterName);
				emailMessage = emailMessage.replace("{TEAMNAME}", teamName);
				emailMessage = emailMessage.replace("{INVITEEEMAIL}",
						inviteeEMail);
				emailMessage = emailMessage.replace("{INVITEANWSERURL}", INVITE_URL
						+ inviteId);

				/**
				 * 注册新用户
				 */
				// 生成一个id
				String id = UUID.randomUUID().toString();
				// 随机一个初始密码
				String passWord = this.getRandomCode(6);
				Sql = "insert into tb_baseInfo(id,email,name,password,isalive)values(?,?,?,?,?)";
				Object[] params = { id, inviteeEMail, inviteeName, passWord, 0 };
				// 插入数据库
				int insertCount = baseDao.insert(Sql, params);
				if (insertCount == 0) {
					baseResult.setMessage(TRY_AGAIN);
					baseResult.setResultType(ERROR);
					return baseResult;
				}
				emailMessage = emailMessage.replace("{PASSWORD}", passWord);
				emp.setText(emailMessage);
				inviteeId = id;

			} else {
				// 已经注册
				inviteeId = inviteeIds.get(0).get("ID").toString();
				emailMessage = INVITE_MESSAGE;
				emailMessage = emailMessage.replace("{INVITEENAME}",
						inviteeName);
				emailMessage = emailMessage.replace("{INVITERNAME}",
						inviterName);
				emailMessage = emailMessage.replace("{TEAMNAME}", teamName);
				emailMessage = emailMessage.replace("{INVITEANWSERURL}",
						INVITE_URL + inviteId);
				emp.setText(emailMessage);
			}
			// 插入邀请记录
			Sql = " insert into tb_invite (id,inviterId,inviteTime,inviteeId,inviteeName,inviteeEMail,teamId,inviteStatus) values(?,?,?,?,?,?,?,?)";
			int count = baseDao.insert(Sql,
					new Object[] { inviteId, inviterId, inviteTime, inviteeId,
							inviteeName, inviteeEMail, teamId, 0 });
			if (count == 0) {
				baseResult.setMessage(TRY_AGAIN);
				baseResult.setResultType(ERROR);
				return baseResult;
			}
		}
		/*
		 * 发送邮件
		 */
		try {
			boolean result = emailService.sendHtmlEMail(emp);
			if (!result) {
				baseResult.setMessage(TRY_AGAIN);
				baseResult.setResultType(ERROR);
				return baseResult;
			}

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		baseResult.setResultType(SUCCESS_CODE);
		baseResult.setMessage(INVITE_SUCCESS);
		return baseResult;
	}

	/**
	 * 
	 * @描述：TODO(获取用户邀请列表)
	 * @param teamId
	 *            团队ID
	 * @param inviterId
	 *            邀请人ID
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年3月27日下午3:55:09
	 * @修改人 ：lrz
	 * @修改时间：2017年3月27日下午3:55:09
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@Override
	public List<Map<String, Object>> getInviteList(String teamId,
			String inviterId) {
		String Sql = " select * from tb_invite where teamId=? and inviterId=? ";

		return baseDao.queryMapInList(Sql, new Object[] { teamId, inviterId });
	}

	/**
	 * 
	 * @描述：TODO(邀请应答)
	 * @param inviteId
	 *            邀请码
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年3月29日下午3:42:30
	 * @修改人 ：lrz
	 * @修改时间：2017年3月29日下午3:42:30
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public BaseResult<User> inviteAnwser(String inviteId) {
		BaseResult<User> result = new BaseResult<User>();
		// 根据邀请码获取邀请信息
		String Sql = " select * from tb_invite where id =? ";
		List<Map<String, Object>> inviteInfos = baseDao.queryMapInList(Sql,
				new Object[] { inviteId });
		if (inviteInfos.size() == 0) {
			result.setResultType(WARM);
			return result;
		}
		Map<String, Object> inviteInfo = inviteInfos.get(0);
		String inviteeId = inviteInfo.get("INVITEEID") + "";
		// 激活用户
		Sql = " update tb_baseInfo set isalive=1 where id = ? ";
		baseDao.update(Sql, new Object[] { inviteeId });
		/*
		 * 如果没有用户配置信息，生成用户默认设置
		 */
		Sql = " select count(1) from tb_userconfig where id = ? or (id=? and showteam is null)";
		int count = (int) baseDao.queryUniqueObject(Sql, new Object[]{inviteeId,inviteeId}, Integer.class);
		if (count == 0 ){
			Sql = " insert into tb_userconfig(id,showTeam) values(?,?) ";
			baseDao.insert(Sql, new Object[]{inviteeId,inviteInfo.get("TEAMID").toString()});
		}
		
		// 修改邀请状态
		Sql = " update tb_invite set inviteStatus=1 where id=? ";
		count = baseDao.update(Sql, new Object[] { inviteId });
		if (count == 0) {
			result.setResultType(ERROR);
			return result;
		}
		
		// 增加团队成员
		Sql = " insert into tb_teammember (memberId,teamId,depId)values(?,?,?) ";
		baseDao.insert(Sql, new Object[] {
				inviteInfo.get("INVITEEID").toString(),
				inviteInfo.get("TEAMID").toString(),
				inviteInfo.get("TEAMID").toString() });
		// 获取被邀请人信息
		Sql = " select * from tb_baseInfo where id = ?";
		List<User> users = baseDao.queryToList(Sql, new Object[] { inviteeId },
				new ObjectRelationalMapper("com.heisenberg.base.model.User"));
		result.setObj(users.get(0));
		result.setResultType(SUCCESS_CODE);
		return result;
	}

	/**
	 * 
	 * @描述：TODO(获取团队成员,包括下属团队的成员)
	 * @param id
	 *            团队id
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年4月3日下午5:18:46
	 * @修改人 ：lrz
	 * @修改时间：2017年4月3日下午5:18:46
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@Override
	public List<Map<String, Object>> getMemeber(String id) {
		String Sql = "select * " +
						"  from (select bi.id       as memberid, " + 
						"               bi.name     as membername, " + 
						"               bi.email    as memberemail, " + 
						"               tm.depid    as teamid, " + 
						"               t.name      as teamname, " + 
						"               t.teamlevel as teamlevel " + 
						"          from tb_baseinfo bi " + 
						"          left join tb_teammember tm " + 
						"            on bi.id = tm.memberid " + 
						"          left join tb_team t " + 
						"            on tm.depid = t.id " +
						"         where tm.depid in (select t.id " + 
						"                              from tb_team t " + 
						"                             start with t.id = ? " + 
						"                            CONNECT BY PRIOR t.ID = t.parentid)" + 
						" union " +
						"       select bi.id       as memberid,\n" + 
						"              bi.name     as membername,\n" + 
						"              bi.email    as memberemail,\n" + 
						"              t.id        as teamid,\n" + 
						"              t.name      as teamname,\n" + 
						"              t.teamlevel as teamlevel\n" + 
						"         from tb_baseInfo bi\n" + 
						"         left join tb_team t\n" + 
						"           on bi.id = t.leaderid\n" + 
						"        where t.id = ? )"+ 
						" order by teamlevel asc";

		List<Map<String, Object>> result = baseDao.queryMapInList(Sql,
				new Object[] {id,id});
		return result;
	}
	/**
	 * 
	 * @描述：TODO(获取团队成员)
	 * @param id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月24日下午2:06:44
	 * @修改人  ：lrz
	 * @修改时间：2017年4月24日下午2:06:44
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public List<Map<String, Object>> getTeamMember(String id) {
		String Sql = "select bi.id    as memberid, " +
						"       bi.name  as membername, " + 
						"       u.email  as memberemail, " + 
						"       tm.depid as teamid, " + 
						"       t.name   as teamname " + 
						"  from tb_baseinfo bi " + 
						"  left join tb_baseInfo u on bi.id = u.id " + 
						"  left join tb_teammember tm on u.id = tm.memberid " + 
						"  left join tb_team t on t.id = tm.depid " + 
						" where t.id = ? "+
						" union " +
						"       select bi.id       as memberid, " + 
						"              bi.name     as membername, " + 
						"              bi.email    as memberemail, " + 
						"              t.id        as teamid, " + 
						"              t.name      as teamname " + 
						"         from tb_baseInfo bi " + 
						"         left join tb_team t " + 
						"           on bi.id = t.leaderid " + 
						"        where t.id = ? ";

		List<Map<String, Object>> result = baseDao.queryMapInList(Sql,
				new Object[] { id,id });
		return result;
	}

	/**
	 * 
	 * @描述：TODO(添加岗位)
	 * @param station
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年4月3日下午11:26:29
	 * @修改人 ：lrz
	 * @修改时间：2017年4月3日下午11:26:29
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public BaseResult addStation(Station station) {
		BaseResult result = new BaseResult();
		String Sql = " select count(*) from tb_station where (name=? or stationcode=?) and teamId=? ";
		long count = (long) baseDao.queryUniqueObject(Sql, new Object[] {
				station.getName(), station.getStationCode(),station.getTeamId() }, long.class);
		if (count > 0) {
			// 岗位名已经存在
			result.setResultType(WARM);
			result.setMessage(STATION_EXIST);
		} else {
			// 生成id
			String id = UUID.randomUUID().toString();
			station.setId(id);
			Sql = " insert into tb_station(id,name,stationlevel,parentid,stationcode,sort,remark,teamId)values(?,?,?,?,?,?,?,?) ";
			baseDao.insert(Sql,new Object[] { id, station.getName(),station.getStationLevel(), station.getParentId(),station.getStationCode(), station.getSort() == 0 ? 999:station.getSort(),station.getRemark(), station.getTeamId() });
			result.setResultType(SUCCESS_CODE);
			result.setMessage(SUCCESS);
			result.setObj(station);
		}
		return result;
	}

	/**
	 * 
	 * @描述：TODO(获取岗位树)
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年4月4日下午1:42:57
	 * @修改人 ：lrz
	 * @修改时间：2017年4月4日下午1:42:57
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Node[] getStationTree(String topTeamId) {
		// 获取所有的岗位节点
		String Sql = " select * from tb_station where teamId=?";
		List<Node> stations = baseDao
				.queryToList(Sql, new Object[]{topTeamId}, new ObjectRelationalMapper(
						"com.heisenberg.base.model.Station"));
		// 解析成树
		Node[] stationsTree = TreeUtil.analyzeForest(stations);
		return stationsTree;
	}

	/**
	 * 
	 * @描述：TODO(获取跟某个节点相同顶级节点的子节点)
	 * @param id
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年4月4日下午3:18:22
	 * @修改人 ：lrz
	 * @修改时间：2017年4月4日下午3:18:22
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Node> getInnerStation(String id) {
		Node station = new Station();
		// 获取全部节点
		String Sql = " select * from tb_station ";
		List<Node> nodes = baseDao
				.queryToList(Sql, null, new ObjectRelationalMapper(
						"com.heisenberg.base.model.Station"));
		// 遍历节点找到源节点
		for (Node node : nodes) {
			if (node.getId().equals(id)) {
				station = node;
				break;
			}
		}
		// 获取顶级岗位
		Node rootStation = TreeUtil.getRootNode(station, nodes);
		List<Node> innerStations = TreeUtil
				.getChildrenNodes(rootStation, nodes);
		// 排除源节点
		for (int i = 0; i < innerStations.size(); i++) {
			if (innerStations.get(i).getId().equals(id)) {
				innerStations.remove(i);
				break;
			}
		}
		return innerStations;
	}

	/**
	 * 
	 * @描述：TODO(修改岗位信息)
	 * @param station
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年4月4日下午4:35:22
	 * @修改人 ：lrz
	 * @修改时间：2017年4月4日下午4:35:22
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public BaseResult modifyStation(Station station) {
		BaseResult result = new BaseResult();
		// 判断岗位名或者岗位编码是否重复
		String Sql = " select count(*) from tb_station where (name=? or stationcode=?) and id!=? and stationCode!=?";
		int count = (int) baseDao.queryUniqueObject(Sql,
				new Object[] { station.getName(), station.getStationCode(),
						station.getId(), station.getStationCode() },
				Integer.class);
		if (count > 0) {
			result.setMessage(STATION_EXIST);
			result.setResultType(WARM);
			return result;
		}
		// 更新岗位信息
		Sql = " update tb_station set name=?,stationlevel=?,parentId=?,stationcode=?,sort=?,remark=? where id=? ";
		baseDao.update(Sql,new Object[] { station.getName(), station.getStationLevel(),station.getParentId(), station.getStationCode(),station.getSort(), station.getRemark(), station.getId() });
		result.setMessage(SUCCESS);
		result.setResultType(SUCCESS_CODE);
		result.setObj(station);
		return result;
	}

	/**
	 * 
	 * @描述：TODO(删除岗位)
	 * @param id
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年4月4日下午5:28:47
	 * @修改人 ：lrz
	 * @修改时间：2017年4月4日下午5:28:47
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult deleteStation(String id) {
		BaseResult result = new BaseResult();
		// 删除id为id或者parentid为id的岗位
		String Sql = " delete from tb_station where parentid=? or id=? ";
		int count = baseDao.delete(Sql, new Object[] { id, id });
		if (count == 0) {
			result.setResultType(ERROR);
			result.setMessage(TRY_AGAIN);
		} else {
			result.setMessage(SUCCESS);
			result.setResultType(SUCCESS_CODE);
		}
		return result;
	}

	/**
	 * 
	 * @描述：TODO(获取某个岗位上的人员列表)
	 * @param id
	 *            岗位id
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年4月5日上午9:33:05
	 * @修改人 ：lrz
	 * @修改时间：2017年4月5日上午9:33:05
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@Override
	public List<Map<String, Object>> getMemberByStation(String id) {
		String Sql = " select  us.id memberId, bi.name memberName,u.email memberEmail,t.name as teamName "
				+ " from tb_userStation us "
				+ " left join tb_baseInfo bi"
				+ " on us.id=bi.id "
				+ "	left join tb_baseInfo u "
				+ " on bi.id = u.id "
				+ " left join tb_teamMember tm "
				+ " on u.id = tm.memberId "
				+ " left join tb_team t "
				+ " on tm.depId = t.id "
				+ " where us.stationId = ?";
		List<Map<String, Object>> members = baseDao.queryMapInList(Sql,
				new Object[] { id });
		return members;
	}

	/**
	 * 
	 * @描述：TODO(添加职级)
	 * @param positionName
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年4月5日上午11:35:27
	 * @修改人 ：lrz
	 * @修改时间：2017年4月5日上午11:35:27
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public BaseResult addPosition(String positionName,String teamId) {
		BaseResult result = new BaseResult();
		// 判断职级是否重复
		String Sql = " select count(*) from tb_position where name = ? and teamId=? ";
		int count = (int) baseDao.queryUniqueObject(Sql,
				new Object[] { positionName,teamId }, Integer.class);
		if (count > 0) {
			result.setResultType(WARM);
			result.setMessage(POSITION_EXIST);
			return result;
		}
		// 插入新的职级
		// 生成id
		String id = UUID.randomUUID().toString();
		// 生成创建时间
		Date createDate = new Date();
		String createTime = sdf.format(createDate);
		Sql = " insert into tb_position(id,name,createtime,teamId)values(?,?,?,?) ";
		baseDao.insert(Sql, new Object[] { id, positionName, createTime ,teamId});
		result.setResultType(SUCCESS_CODE);
		result.setMessage(SUCCESS);
		Position position = new Position();
		position.setId(id);
		position.setName(positionName);
		result.setObj(position);
		return result;
	}

	/**
	 * 
	 * @描述：TODO(获取直接列表)
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年4月9日上午1:43:57
	 * @修改人 ：lrz
	 * @修改时间：2017年4月9日上午1:43:57
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Position> getPositionList() {
		String Sql = " select * from tb_position order by createtime asc ";
		return baseDao.queryToList(Sql, null, new ObjectRelationalMapper(
				"com.heisenberg.base.model.Position"));
	}

	/**
	 * 
	 * @描述：TODO(修改职级)
	 * @param position
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年4月9日上午3:04:51
	 * @修改人 ：lrz
	 * @修改时间：2017年4月9日上午3:04:51
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public BaseResult modifyPosition(Position position) {
		BaseResult result = new BaseResult();
		// 判断职级名称是否重复
		String Sql = " select count(*) from tb_position where name=? ";
		int count = (int) baseDao.queryUniqueObject(Sql,
				new Object[] { position.getName() }, Integer.class);
		if (count > 0) {
			result.setMessage(POSITION_EXIST);
			result.setResultType(WARM);
		} else {
			// 更新职级
			Sql = " update tb_position set name=? where id=? ";
			baseDao.update(Sql,
					new Object[] { position.getName(), position.getId() });
			result.setMessage(SUCCESS);
			result.setResultType(SUCCESS_CODE);
			result.setObj(position);
		}
		return result;
	}

	/**
	 * 
	 * @描述：TODO(删除职级)
	 * @param id
	 * @return
	 * @创建人 ：lrz
	 * @创建时间：2017年4月9日上午3:35:05
	 * @修改人 ：lrz
	 * @修改时间：2017年4月9日上午3:35:05
	 * @修改备注：
	 * @version 1.0
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult deletePosition(String id) {
		BaseResult result = new BaseResult();
		String Sql = " delete from tb_position where id=? ";
		baseDao.delete(Sql, new Object[] { id });
		result.setMessage(SUCCESS);
		result.setResultType(SUCCESS_CODE);
		return result;
	}
	
	/**
	 * 
	 * @描述：TODO(根据职级id获取成员列表)
	 * @param id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月13日下午4:43:35
	 * @修改人  ：lrz
	 * @修改时间：2017年4月13日下午4:43:35
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public List<Map<String, Object>> getMemberByPosition(String id) {
		String Sql = " select u.id memberId, bi.name memberName, u.email memberEmail,t.name teamName "
				+ " from tb_userPosition up "
				+ " left join tb_baseInfo u "
				+ " on up.id = u.id "
				+ " left join tb_baseinfo bi "
				+ " on u.id = bi.id "
				+ " left join tb_teammember tm "
				+ " on tm.memberid = bi.id "
				+ " left join tb_team t "
				+ " on tm.depId = t.id "
				+ " where up.positionid=? ";
		
		return baseDao.queryMapInList(Sql, new Object[]{id});
	}

	/**
	 * 
	 * @描述：TODO(获取成员基本信息)
	 * @param id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月11日上午10:44:02
	 * @修改人  ：lrz
	 * @修改时间：2017年4月11日上午10:44:02
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public Map<String, Object> getMemBaseInfo(String id) {
		Map<String,Object> result = new HashMap<String,Object>();
		//获取基本信息
		String Sql = " select u.email,b.*,l.name as leaderName"
				+ " from tb_baseInfo u "
				+ " left join tb_baseInfo b "
				+ " on u.id = b.id "
				+ " left join tb_baseInfo l "
				+ " on b.leaderId=l.id "
				+ " where u.id=? ";
		List<Map<String,Object>> baseInfos = baseDao.queryMapInList(Sql, new Object[]{id});
		result.putAll(baseInfos.get(0));
		return result;
	}
	/**
	 * 
	 * @描述：TODO(获取成员的部门、岗位、职级和上级信息)
	 * @param id 成员id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月11日下午3:45:38
	 * @修改人  ：lrz
	 * @修改时间：2017年4月11日下午3:45:38
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public Map<String, List<Map<String, Object>>> getMemberPositionInfo(
			String id,String topTeamId) {
		Map<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String, Object>>>();
		/*
		 * 获取所在团队列表
		 */
		String Sql =  " with teams as " +
						" (select t.id, t.name, 0 as isthis " + 
						"    from tb_team t " + 
						"   start with t.id = ? " + 
						"  connect by prior id = parentid), " + 
						" thisteam as\n" + 
						" (select t.id, t.name, 1 as isthis " + 
						"    from tb_team t " + 
						"   where t.leaderid = ? " + 
						"  union " + 
						"  select t.id, t.name, 1 as isthis " + 
						"    from tb_teammember tm " + 
						"    left join tb_team t " + 
						"      on tm.depid = t.id " + 
						"   where tm.memberid = ? " + 
						"     and tm.depid in (select id from teams)) " + 
						" select * " +
						"  from thisteam " + 
						" union " + 
						" select * from teams where id not in (select id from thisteam)";
		List<Map<String,Object>> teams = baseDao.queryMapInList(Sql, new Object[]{topTeamId,id,id});
		result.put("teams", teams);
		/*
		 * 获取岗位信息
		 */
		Sql = "with thisstation as " +
						" (select s.id, s.name, 1 as isthis " + 
						"    from tb_station s " + 
						"    left join tb_userstation us " + 
						"      on s.id = us.stationid " + 
						"   where us.id =?), " + 
						"stations as " + 
						" (select s.id, s.name, 0 as isthis " + 
						"    from tb_station s " + 
						"   where teamId = ?) " + 
						"select * " + 
						"  from thisstation " + 
						"union " + 
						" select * from stations where id not in (select id from thisstation) ";

		List<Map<String,Object>> stations = baseDao.queryMapInList(Sql, new Object[]{id,topTeamId});
		result.put("stations",stations);
		/*
		 * 获取职级信息
		 */
		Sql = " with thisposition as " +
						" (select p.id, p.name, 1 as isthis " + 
						"    from tb_position p " + 
						"    left join tb_userposition up " + 
						"      on p.id = up.positionid " + 
						"   where up.id = ?), " + 
						" positions as " + 
						" (select p.id, p.name, 0 as isthis from tb_position p where teamId = ?) " + 
						" select * " + 
						"  from thisposition " + 
						" union " + 
						" select * from positions where id not in (select id from thisposition) ";

		List<Map<String,Object>> positions =baseDao.queryMapInList(Sql, new Object[]{id,topTeamId});
		result.put("positions", positions);
		
		Sql= "with team as " +
						" (select t.id " + 
						"    from tb_team t " + 
						"   start with t.id = ? " + 
						"  connect by prior id = parentid) " + 
						"SELECT u1.id, u1.name, 1 as isthis " + 
						"  FROM tb_leader tl1 " + 
						"  LEFT JOIN tb_baseInfo u1 " + 
						"    ON tl1.leaderId = u1.id " + 
						" WHERE tl1.id = ? " + 
						"UNION " + 
						"SELECT u2.id, u2.name, 0 as isthis " + 
						"  FROM tb_teammember t2 " + 
						"  LEFT JOIN tb_baseInfo u2 " + 
						"    ON t2.memberId = u2.id " + 
						" WHERE t2.teamId IN (SELECT team.id FROM team) and " + 
						" t2.memberid not in (SELECT u1.id " + 
						"                       FROM tb_leader tl1 " + 
						"                       LEFT JOIN tb_baseInfo u1 " + 
						"                         ON tl1.leaderId = u1.id " + 
						"                      WHERE tl1.leaderid = ?) and t2.memberid != ? " + 
						"union " + 
						"select bi.id, bi.name, 0 as isthis " + 
						"  from tb_baseInfo bi " + 
						"  left join tb_team t " + 
						"    on bi.id = t.leaderId " + 
						" where t.id in (select * from team) and bi.id != ?";


		List<Map<String,Object>> leaders = baseDao.queryMapInList(Sql, new Object[]{topTeamId,id,id,id,id});
		result.put("leaders", leaders);
		return result;
	}
	/**
	 * 
	 * @描述：TODO(修改成员基本信息)
	 * @param ubInfo
	 * @创建人  ：lrz
	 * @创建时间：2017年4月13日下午2:42:08
	 * @修改人  ：lrz
	 * @修改时间：2017年4月13日下午2:42:08
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public void modifyMemberInfo(UserBaseInfo ubInfo) {
		String Sql = " update tb_baseinfo "
				+ " set name=?, telNumber=?, gender=?,entryTime=?,"
				+ " employeeNum=? where id=? ";
		baseDao.update(Sql, new Object[]{ubInfo.getName(),ubInfo.getTelNumber(),ubInfo.getGender(),
				ubInfo.getEntryTime(),ubInfo.getEmployeeNum(),ubInfo.getId()});
	}
	/**
	 * 
	 * @描述：TODO(修改成员的团队，岗位，职级信息，领导信息)
	 * @param teamId
	 * @param stationId
	 * @param positionId
	 * @创建人  ：lrz
	 * @创建时间：2017年4月13日下午2:48:55
	 * @修改人  ：lrz
	 * @修改时间：2017年4月21日下午12:41:55
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public void modifyMemberPositionInfo(String memberId,String teamId, String stationId,
			String positionId,String leaderId) {
		//修改团队信息
		String Sql = " update tb_teammember set depId=? where memberId=? ";
		baseDao.update(Sql, new Object[]{teamId,memberId});
		if (!StringUtils.isEmpty(stationId)){
			//修改岗位信息
			Sql = " select count(*) from tb_userStation where Id=? ";
			int count = (int) baseDao.queryUniqueObject(Sql, new Object[]{memberId}, Integer.class);
			if (count > 0){
				Sql = " update tb_userStation set stationId=? where Id=? ";
				baseDao.update(Sql, new Object[]{stationId,memberId});
			}else{
				Sql = " insert into tb_userStation(Id,stationId)values(?,?) ";
				baseDao.insert(Sql, new Object[]{memberId,stationId});
			}
			
		}
		if (!StringUtils.isEmpty(positionId)){
			//修改职级信息
			Sql = " select count(*) from tb_userPosition where id=? ";
			int count = (int) baseDao.queryUniqueObject(Sql, new Object[]{memberId}, Integer.class);
			if (count > 0){
				Sql = " update tb_userPosition set positionId=? where id=? ";
				baseDao.update(Sql, new Object[]{positionId,memberId});
			}else{
				Sql = " insert into tb_userPosition(Id,positionId)values(?,?) ";
				baseDao.insert(Sql, new Object[]{memberId,positionId});
			}
		}
		
		if(!StringUtils.isEmpty(leaderId)){
			//修改领导信息
			Sql = " select count(*) from tb_leader where id=?";
			int count = (int) baseDao.queryUniqueObject(Sql, new Object[]{memberId}, Integer.class);
			if (count > 0){
				Sql = " update tb_leader set leaderId=? where id=? ";
				baseDao.update(Sql, new Object[]{leaderId,memberId});
			}else{
				Sql = " insert into tb_leader(id,leaderId)values(?,?) ";
				baseDao.insert(Sql, new Object[]{memberId,leaderId});
			}
		}
		
	}
	/**
	 * 
	 * @描述：TODO(根据用户id获取默认展示团队)
	 * @param userId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月10日下午12:07:51
	 * @修改人  ：lrz
	 * @修改时间：2017年5月10日下午12:07:51
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Team getDefaultTeamByUserId(String userId) {
		String Sql = " select t.* from tb_team t left join tb_userConfig uc on t.id = uc.showTeam where uc.id=?";
		List<Team> defaultTeams = baseDao.queryToList(Sql, new Object[]{userId}, new ObjectRelationalMapper("com.heisenberg.base.model.Team"));
		Team defaultTeam = defaultTeams.size() == 0 ? null : defaultTeams.get(0);
		return defaultTeam;
	}
	/**
	 * 
	 * @描述：TODO(根据团队名获取团队)
	 * @param name
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年6月1日上午12:58:16
	 * @修改人  ：lrz
	 * @修改时间：2017年6月1日上午12:58:16
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public List<Map<String,Object>> getTeamsByName(String name) {
		String Sql = " select t.id,t.name as teamName,bi.name as leaderName from tb_team t left join tb_baseinfo bi on t.leaderid = bi.id where t.name like ? ";
		return baseDao.queryMapInList(Sql, new Object[]{"%"+name+"%"});
	}
	/**
	 * 
	 * @描述：TODO(申请加入团队)
	 * @param teamId 团队id
	 * @param userId 用户id
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年6月4日上午1:20:24
	 * @修改人  ：lrz
	 * @修改时间：2017年6月4日上午1:20:24
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult applyJoinTeam(String teamId, String userId) {
		BaseResult result = new BaseResult();
		//获取团队名
		String getTeamSql = " select name from tb_team where id=? ";
		List<Map<String,Object>> teamNames = baseDao.queryMapInList(getTeamSql, new Object[]{teamId});
		String Sql = " insert into tb_log(OPERATIONTYPE,TARGET,OPERATIONTIME,TARGETID,TARGETNAME，USERID,TEAMID)values(?,?,?,?,?,?,?)";
		Object[] params = {"apply","team",sdf.format(new Date()),teamId,teamNames.get(0).get("NAME"),userId,teamId};
		int count = baseDao.insert(Sql, params);
		if (count > 0){
			result.setResultType(SUCCESS_CODE);
			result.setMessage(APPLY_SUCCESS);
			return result;
		}
		result.setResultType(ERROR);
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult agreeApplyJoin(String userId, String teamId,
			String operationBy, String topTeamId) {
		BaseResult result = new BaseResult();
		String Sql = " insert into tb_teammember(memberid,teamid,depid) " + 
				"select ? as memberid, " +
				"       (select id " + 
				"          from (SELECT id " + 
				"                  FROM tb_team " + 
				"                 START WITH id = ? " + 
				"                CONNECT BY PRIOR PARENTID = id " + 
				"                 order by rownum desc) " + 
				"         where rownum = 1) as teamid, " + 
				"       ? as depid " + 
				"  from dual";
		
		int count = baseDao.insert(Sql, new Object[]{userId,teamId,teamId});
		if (count > 0){
			//设置用户的首选团队
			Sql = " insert into tb_userconfig(id,showteam)values(?,?)";
			baseDao.insert(Sql, new Object[]{userId,teamId});
			Sql = " select bi.email,t.name from tb_baseInfo bi,tb_team t where bi.id=? and t.id=? ";
			List<Map<String,Object>> infos = baseDao.queryMapInList(Sql, new Object[]{userId,teamId});
			if (infos.size() > 0){
				//发送邮件通知
				String email = infos.get(0).get("EMAIL").toString();
				String teamName = infos.get(0).get("NAME").toString();
				E_MailProp emp = new E_MailProp();
				emp.setAuth("true");
				emp.setFrom(EMAIL_FROM);
				emp.setTo(new String[] { email });
				emp.setHost(EMAIL_HOST);
				emp.setPassWord(EMAIL_PASSWORD);
				emp.setTimeuot("5000");
				emp.setUserName(EMAIL_FROM);
				emp.setSubject("管理员同意了你的加入申请");
				emp.setText("管理员同意了你加入团队："+teamName + " 的申请，请登录系统开始团队工作吧！");
				try {
					emailService.sendSimpleEMail(emp);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			result.setResultType(SUCCESS_CODE);
			result.setMessage("操作成功！");
			return result;
		}else{
			result.setResultType(ERROR);
			result.setMessage(TRY_AGAIN);
			return result;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult disagreeApplyJoin(String userId, String teamId,
			String operationBy, String topTeamId) {
		BaseResult result = new BaseResult();
		String Sql = " select bi.email,t.name from tb_baseInfo bi,tb_team t where bi.id=? and t.id=? ";
		List<Map<String,Object>> infos = baseDao.queryMapInList(Sql, new Object[]{userId,teamId});
		if (infos.size() > 0){
			//发送邮件通知
			String email = infos.get(0).get("EMAIL").toString();
			String teamName = infos.get(0).get("NAME").toString();
			E_MailProp emp = new E_MailProp();
			emp.setAuth("true");
			emp.setFrom(EMAIL_FROM);
			emp.setTo(new String[] { email });
			emp.setHost(EMAIL_HOST);
			emp.setPassWord(EMAIL_PASSWORD);
			emp.setTimeuot("5000");
			emp.setUserName(EMAIL_FROM);
			emp.setSubject("管理员拒绝了你的加入申请");
			emp.setText("管理员拒绝了你加入团队："+teamName + " 的申请，请向管理员确认！");
			try {
				emailService.sendSimpleEMail(emp);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		result.setResultType(SUCCESS_CODE);
		result.setMessage("操作成功！");
		return result;
	}
	
	

}
