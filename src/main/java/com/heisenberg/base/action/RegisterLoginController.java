package com.heisenberg.base.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.heisenberg.base.model.User;
import com.heisenberg.common.resultmodel.BaseResult;



public interface RegisterLoginController {
	/**
	 * 
	 *
	 * @描述：TODO(进入系统的第一个请求)
	 *
	 * @param request
	 * @return
	 * String
	 * @创建人  ：lrz
	 * @创建时间：2017年2月15日下午2:07:45
	 * @修改人  ：lrz
	 * @修改时间：2017年2月15日下午2:07:45
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String index(HttpServletRequest request,Model model);
	/**
	 * 
	 *
	 * @描述：TODO(用户注册)
	 *
	 * @param E_MailAddr 邮箱地址
	 * @param passwordOne 密码
	 * @return
	 * User
	 * @创建人  ：lrz
	 * @创建时间：2017年2月10日下午12:34:45
	 * @修改人  ：lrz
	 * @修改时间：2017年2月10日下午12:34:45
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult register(String E_MailAddr, String passwordone);
	/**
	 * 
	 *
	 * @描述：TODO(新用户激活)
	 *
	 * @param id  用户id
	 * @return
	 * String
	 * @创建人  ：lrz
	 * @创建时间：2017年2月14日上午11:05:45
	 * @修改人  ：lrz
	 * @修改时间：2017年2月14日上午11:05:45
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String activate(String id,Model model);
	/**
	 * 
	 *
	 * @描述：TODO(用户登录)
	 *
	 * @param userName 	用户名或邮箱地址
	 * @param password	密码
	 * @param userName 	是否记住用户名
	 * @param password	是否记住密码
	 * @return
	 * BaseResult
	 * @创建人  ：lrz
	 * @创建时间：2017年2月10日下午12:35:47
	 * @修改人  ：lrz
	 * @修改时间：2017年2月10日下午12:35:47
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public BaseResult<User> login(String username, String password, String rememberUser, String remenberPsd,HttpServletRequest request,HttpServletResponse response);
	/**
	 * 
	 *
	 * @描述：TODO(重定向到登录界面)
	 *
	 * @return
	 * String
	 * @创建人  ：lrz
	 * @创建时间：2017年2月14日下午1:31:04
	 * @修改人  ：lrz
	 * @修改时间：2017年2月14日下午1:31:04
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToLogin();
	/**
	 * 
	 * 方法功能说明：重定向到找回密码页面 
	 * 创建：2017年3月1日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @return          
	 * @throws
	 */
	public String redirectToGetBackPsd();
	/**
	 * 
	 * 方法功能说明：  获取验证码
	 * 创建：2017年3月5日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @param email
	 *  @return          
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult getValidateCode(String email);
	/**
	 * 
	 * 方法功能说明：  重置密码
	 * 创建：2017年3月6日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @param email	邮箱
	 *  @param validateCode	验证码
	 *  @param newpassword	新密码
	 *  @return          
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public BaseResult resetPassWord(String email,String validateCode,String newpassword);
	/**
	 * 
	 * 方法功能说明：跳转到主页面  
	 * 创建：2017年3月7日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @return          
	 * @throws
	 */
	public String redirectToMain(Model model, String userId);
	/**
	 * 
	 * @描述：TODO(退出系统)
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月8日下午10:54:40
	 * @修改人  ：lrz
	 * @修改时间：2017年5月8日下午10:54:40
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String quit(HttpServletRequest request);
	/**
	 * 
	 * @描述：TODO(修改密码)
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月8日下午11:06:54
	 * @修改人  ：lrz
	 * @修改时间：2017年5月8日下午11:06:54
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String redirectToModifyPsd();
}
