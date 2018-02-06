package com.heisenberg.base.action.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heisenberg.base.action.RegisterLoginController;
import com.heisenberg.base.model.Team;
import com.heisenberg.base.model.User;
import com.heisenberg.base.service.RegisterLoginService;
import com.heisenberg.base.service.TeamService;
import com.heisenberg.common.resultmodel.BaseResult;


@Controller
public class RegisterLoginContorllerImpl implements RegisterLoginController{

	@Autowired
	private RegisterLoginService service;
	@Autowired
	private TeamService teamService;
	@Value("${TYPE_SUCCESS}")
	private String SUCCESS;
	@Value("${TYPE_WARM}")
	private String WARM;
	@Value("${TYPE_ERROR}")
	private String ERROR;
	@Value("${COUNT_EXIST}")
	private String EMAIL_EXIST; //邮箱已被注册
	@Value("${REGISTER_SUCCESS}")
	private String REGISTER_SUCCESS; //注册成功
	@Value("${REGISTER_DEFEAT}")
	private String REGISTER_DEFEAT; //注册失败

	@Value("${ACTIVATE_USER_NOT_EXIST}")
	private String ACTIVATE_USER_NOT_EXIST; //激活用户不存在
	@Value("${ACTIVATE_SUCCESS}") 
	private String ACTIVATE_SUCCESS;//激活用户成功
	@Value("${ACTIVATE_DEFEAT}")
	private String ACTIVATE_DEFEAT; //激活用户失败
	@Value("${COOKIE_LIFE_TIME}")
	private int COOKIE_LIFE_TIME;
	@Value("${EMAIL_NOT_REGIST}")
	private String EMAIL_NOT_REGIST; //邮箱未注册
	@Value("${TRY_AGAIN}")
	private String TRY_AGAIN;
	@Value("${VSLIDATE_CODE_ERROR}")
	private String VSLIDATE_CODE_ERROR;//验证码错误
	@Value("${MODIFI_PASSWORD_SUCCESS}")
	private String MODIFI_PASSWORD_SUCCESS;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 *
	 * @Description: TODO(系统的第一个请求，跳转到登录或者主页面)
	 * @param @param request
	 * @param @return   
	 * @return 
	 * @throws
	 * @author lrz
	 * @date 2017年2月15日
	 */
	@RequestMapping("index")
	@Override
	public String index(HttpServletRequest request,Model model){
		HttpSession session = request.getSession();
		//获取session是否存在登录信息
		String logedUser = (String)session.getAttribute("loginedUser");
		if (logedUser != null){
			//存在登录信息，直接返回主页面
			model.addAttribute("userId", logedUser);
			//获取可切换团队列表
			List<Team> teamsAboutMe = teamService.getTopTeamList(logedUser);
			model.addAttribute("teams", JSONArray.fromObject(teamsAboutMe));
			//获取主页默认团队
			Team defaultTeam = teamService.getDefaultTeamByUserId(logedUser);
			model.addAttribute("defaultTeam", defaultTeam);
			String userName = service.getUserNameById(logedUser);
			model.addAttribute("userName", userName);
			return "main";
		}
		return "login";
	}
	/**
	 * 
	 * @Description: 跳转到登录界面
	 * @param @return   
	 * @return 
	 * @throws
	 * @author lrz
	 * @date 2017年2月15日
	 */
	@RequestMapping("redirectToLogin")
	@Override
	public String redirectToLogin() {

		return "login";
	}

	/**
	 * 
	 * @Description: TODO 注册
	 * @param @param E_MailAddr 邮箱地址
	 * @param @param password 密码
	 * @param @return   
	 * @return 
	 * @throws
	 * @author lrz
	 * @date 2017年2月10日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("register")
	@ResponseBody
	@Override
	public BaseResult register(String userName, String passWord) {
		
		BaseResult result = new BaseResult();
		//向数据库写入注册信息
		String resultStr=" ";

		try {
			resultStr = service.register(userName, passWord);
		} catch (MessagingException e) {
			result.setMessage(resultStr);
			result.setResultType(ERROR);
			return result;
		}

		if (resultStr.equals(REGISTER_SUCCESS)){
			//注册成功
			result.setMessage(resultStr);
			result.setResultType(SUCCESS);
		}else if (resultStr.equals(EMAIL_EXIST)){
			//邮箱已被注册
			result.setMessage(resultStr);
			result.setResultType(WARM);
		}else{
			//注册失败
			result.setMessage(resultStr);
			result.setResultType(ERROR);
		}
		return result;
	}
	/**
	 * 
	 * @Description: 激活新用户
	 * @param @param id
	 * @param @param model
	 * @param @return   
	 * @return 
	 * @throws
	 * @author lrz
	 * @date 2017年2月15日
	 */
	@RequestMapping("activate/{id}")
	@Override
	public String activate(@PathVariable("id") String id, Model model) {
		String result = service.activate(id);
		model.addAttribute("message", result);
		if (result.equals(ACTIVATE_SUCCESS)){
			model.addAttribute("type", 0);
			model.addAttribute("userId", id);
		}else if (result.equals(ACTIVATE_USER_NOT_EXIST)){
			model.addAttribute("type", 1);
		}else{
			model.addAttribute("type", 2);
		}
		return "activate";
	}
	/**
	 * 
	 * @描述：TODO(用户登录)
	 * @param userName	用户名
	 * @param password	密码
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年2月16日上午10:31:17
	 * @修改人  ：lrz
	 * @修改时间：2017年2月16日上午10:31:17
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("login")
	@ResponseBody
	@Override
	public BaseResult<User> login(String username, String password, String rememberUser, String rememberPsd,HttpServletRequest request, HttpServletResponse response) {
		
		BaseResult<User> result = new BaseResult<User>("1","登录成功");
		/*
		 * 验证用户名
		 */
		result = service.validateUserName(username);
		if (!result.getResultType().equals(SUCCESS)){
			//验证用户不合法
 			return result;
		}
		/*
		 * 验证密码
		 */
		result = service.validatePassWord(username, password, result.getObj());
		if (!result.getResultType().equals(SUCCESS)){
			//密码错误
			return result;
		}
		
		User user = result.getObj();
		/*
		 * 将登陆信息存入session
		 */
		HttpSession session = request.getSession();
		session.setAttribute("loginedUser", user.getID());
		
		
		Cookie[] cookies = request.getCookies();//获取cookies
		/*
		 * 存入cookie
		 */
		if ("on".equals(rememberUser)){
			Cookie cookie = new Cookie("loginedUser",user.getEMAIL());
			//设置cookie的声明周期
			cookie.setMaxAge(COOKIE_LIFE_TIME);
			response.addCookie(cookie);
		}else{
			//如果cookie中保存有用户信息，将信息删除
			boolean delUser = false;
			boolean delPsd = false;
			for(Cookie cookie : cookies){
				String name = cookie.getName();// 获取cookie的name
			    if ("loginedUser".equals(name)){
			    	cookie.setMaxAge(0);
			    	response.addCookie(cookie);
			    	delUser = true;
			    }
			    /*
			     * 将密码也一同删除
			     */
			    if("loginedUserPsd".equals(name)){
			    	cookie.setMaxAge(0);
			    	response.addCookie(cookie);
			    	delPsd = true;
			    }
			    
			    if (delUser && delPsd){
			    	break;
			    }
			}
		}
		if ("on".equals(rememberPsd)){
			Cookie cookie = new Cookie("loginedUserPsd",user.getPASSWORD());
			//设置cookie的声明周期
			cookie.setMaxAge(COOKIE_LIFE_TIME);
			response.addCookie(cookie);
		}else{
			//删除存在的密码
			for(Cookie cookie : cookies){
			    String name = cookie.getName();// 获取cookie的name
			    if ("loginedUserPsd".equals(name)){
			    	cookie.setMaxAge(0);
			    	response.addCookie(cookie);
			    	break;
			    }
			}
		}
		
		
		return result;
	}
	/**
	 * 
	 * 方法功能说明：	重定向到找回密码页面  
	 * 创建：2017年3月1日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @return          
	 * @throws
	 */
	@RequestMapping("redirectToGetBackPsd")
	@Override
	public String redirectToGetBackPsd() {
		return "takeBackPsd";
	}
	/**
	 * 
	 * 方法功能说明：找回密码时获取验证码  
	 * 创建：2017年3月5日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @param email
	 *  @return          
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("getValidateCode")
	@ResponseBody
	@Override
	public BaseResult getValidateCode(String email) {
		BaseResult result = new BaseResult();
		//判断邮箱是已注册并激活
		BaseResult<User> user = service.validateUserName(email);
		if (!user.getResultType().equals(SUCCESS)){
			result.setResultType(user.getResultType());
			result.setMessage(user.getMessage());
			return result;
		}
		//邮箱注册并激活
		String validateCode = service.sendValidateCode(email);
		if (validateCode == null){
			//邮箱发送失败
			result.setResultType(ERROR);
			result.setMessage(TRY_AGAIN);
			return result;
		}
	
		//将验证码存入数据库
		Calendar nowTime = Calendar.getInstance();
		//当前时间加半个小时
		nowTime.add(Calendar.MINUTE, 30);
		String timeOut = sdf.format(nowTime.getTime());
		boolean saveResult = service.saveValidateCoed(email, validateCode, "resetPassWord", timeOut);
		if (!saveResult){
			//保存验证码失败
			result.setResultType(ERROR);
			result.setMessage(TRY_AGAIN);
			return result;
		}
		
		result.setResultType(SUCCESS);
		return result;
	}
	
	/**
	 * 
	 * 方法功能说明：	重置密码  
	 * 创建：2017年3月6日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @param email	邮箱	
	 *  @param validateCode	验证码
	 *  @param newPassword	新密码
	 *  @return          
	 * @throws
	 */
	@RequestMapping("resetPsd")
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult resetPassWord(String email, String validateCode, String newPassWord) {
		BaseResult baseResult = new BaseResult();
		//判断验证码是否正确
		boolean ifValidateCodeRight = service.ifValidateRight(email, validateCode, "resetPassWord");
		if (!ifValidateCodeRight){
			//验证码错误
			baseResult.setResultType(ERROR);
			baseResult.setMessage(VSLIDATE_CODE_ERROR);
			return baseResult;
		}
		//重置密码
		service.modifyPassWord(email, newPassWord);
		baseResult.setResultType(SUCCESS);
		baseResult.setMessage(MODIFI_PASSWORD_SUCCESS);
		return baseResult;
	}
	/**
	 * 
	 * 方法功能说明：跳转到主页面  
	 * 创建：2017年3月7日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @return          
	 * @throws
	 */
	@RequestMapping("main/{userId}")
	@Override
	public String redirectToMain(Model model, @PathVariable("userId") String userId){
		model.addAttribute("userId",userId);
		String userName = service.getUserNameById(userId);
		model.addAttribute("userName", userName);
		//获取可切换团队列表
		List<Team> teamsAboutMe = teamService.getTopTeamList(userId);
		model.addAttribute("teams", JSONArray.fromObject(teamsAboutMe));
		//获取主页默认团队
		Team defaultTeam = teamService.getDefaultTeamByUserId(userId);
		model.addAttribute("defaultTeam", defaultTeam);
		return "main";
	}
	/**
	 * 
	 * @描述：TODO(退出)
	 * @param request
	 * @param response
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月8日下午10:58:48
	 * @修改人  ：lrz
	 * @修改时间：2017年5月8日下午10:58:48
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("quit")
	@Override
	public String quit(HttpServletRequest request) {
		HttpSession session = request.getSession();
		//删除session中的用户信息
		session.removeAttribute("loginedUser");
		return "login";
	}
	/**
	 * 
	 * @描述：TODO(跳转到修改密码页面)
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月8日下午11:07:46
	 * @修改人  ：lrz
	 * @修改时间：2017年5月8日下午11:07:46
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@RequestMapping("redirectToModifyPsd")
	@Override
	public String redirectToModifyPsd() {
		return "modifyPsd";
	}
	
	
	
}
