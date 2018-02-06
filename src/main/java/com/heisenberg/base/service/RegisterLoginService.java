package com.heisenberg.base.service;


import javax.mail.MessagingException;

import com.heisenberg.base.model.User;
import com.heisenberg.common.resultmodel.BaseResult;



public interface RegisterLoginService {
	/**
	 * 
	 *
	 * @描述：TODO(注册用户)
	 *
	 * @param E_MailAddr 邮箱地址
	 * @param password 密码
	 * @return
	 * String
	 * @创建人  ：lrz
	 * @创建时间：2017年2月10日下午1:29:22
	 * @修改人  ：lrz
	 * @修改时间：2017年2月10日下午1:29:22
	 * @修改备注：
	 * @version 1.0
	 * @throws MessagingException 
	 *
	 */
	public String register(String E_MailAddr,String password) throws MessagingException;
	/**
	 * 
	 *
	 * @描述：TODO(激活账户)
	 *
	 * @param id 账户id
	 * @return
	 * String
	 * @创建人  ：lrz
	 * @创建时间：2017年2月10日下午1:29:26
	 * @修改人  ：lrz
	 * @修改时间：2017年2月10日下午1:29:26
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String activate(String id);
	/**
	 * 
	 *
	 * @描述：TODO(登录)
	 *
	 * @param userName 账户名或者邮箱地址
	 * @param passWord 密码
	 * @return
	 * User
	 * @创建人  ：lrz
	 * @创建时间：2017年2月10日下午1:29:29
	 * @修改人  ：lrz
	 * @修改时间：2017年2月10日下午1:29:29
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public User login(String userName,String passWord);
	/**
	 * 
	 * 方法功能说明：  验证用户名是否存在
	 * 创建：2017年2月21日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 * @参数： @param userName
	 * @参数： @return      
	 * @return BaseResult     
	 * @throws
	 */
	public BaseResult<User> validateUserName(String userName);
	/**
	 * 
	 * 方法功能说明：验证密码是否正确  
	 * 创建：2017年2月21日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @param passWord
	 *  @return          
	 * @throws
	 */
	public BaseResult<User> validatePassWord(String userNmae, String passWord,User user);
	/**
	 * 
	 * 方法功能说明：发送验证码  
	 * 创建：2017年3月5日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @param email
	 *  @return          
	 * @throws
	 */
	public String sendValidateCode(String email);
	/**
	 * 
	 * 方法功能说明：  
	 * 创建：2017年3月6日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @param email 邮箱地址
	 *  @param validateCode 验证码
	 *  @param type	验证事件
	 *  @param timeOut	验证码过期时间
	 *  @return          
	 * @throws
	 */
	public boolean saveValidateCoed(String email,String validateCode,String type,String timeOut);
	/**
	 * 
	 * 方法功能说明：  判断验证码是否正确
	 * 创建：2017年3月6日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @param email	邮箱
	 *  @param valideateCode	验证码
	 *  @param type	验证类型
	 *  @return          
	 * @throws
	 */
	public boolean ifValidateRight(String email, String validateCode, String type);
	/**
	 * 
	 * 方法功能说明：  修改密码
	 * 创建：2017年3月6日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @param email	邮箱
	 *  @param newPassWord 	新密码         
	 * @throws
	 */
	public void modifyPassWord(String email,String newPassWord);
	/**
	 * 
	 * @描述：TODO(通过userId获取用户名)
	 * @param userId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月8日下午10:30:20
	 * @修改人  ：lrz
	 * @修改时间：2017年5月8日下午10:30:20
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public String getUserNameById(String userId);
}
