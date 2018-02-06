package com.heisenberg.base.service.impl;


import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.heisenberg.base.dao.SpringJDBCBaseDao;
import com.heisenberg.base.model.User;
import com.heisenberg.base.service.RegisterLoginService;
import com.heisenberg.common.email.model.E_MailProp;
import com.heisenberg.common.email.service.impl.E_MailServiceImpl;
import com.heisenberg.common.resultmodel.BaseResult;
import com.heisenberg.common.util.ObjectRelationalMapper;
import com.heisenberg.common.util.service.impl.CommonServiceImpl;

@Service
public class RegisterLoginServiceImpl extends CommonServiceImpl implements RegisterLoginService{
	@Autowired
	private SpringJDBCBaseDao baseDao;

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
	
	@Value("${e_mailAddress}")
	private String EMAIL_FROM;
	@Value("${e_mailPassword}")
	private String EMAIL_PASSWORD;
	@Value("${e_mailHost}")
	private String EMAIL_HOST;
	@Value("${ACTIVATE_URL}")
	private String ACTIVATE_URL;
	@Value("${EMAIL_ACTIVATE}")
	private String EMAIL_ACTIVATE;
	@Value("${ACTIVATE_USER_NOT_EXIST}")
	private String ACTIVATE_USER_NOT_EXIST;
	@Value("${ACTIVATE_SUCCESS}")
	private String ACTIVATE_SUCCESS;
	@Value("${ACTIVATE_DEFEAT}")
	private String ACTIVATE_DEFEAT;
	@Value("${USERNAME_NOT_EXIST}")
	private String USERNAME_NOT_EXIST;
	@Value("${USERNAME_NOT_ACTIVATED}")
	private String USERNAME_NOT_ACTIVATED;
	@Value("${PASSWORD_ERROR}")
	private String PASSWORD_ERROR;
	@Value("${VALIDATE_CODE}")
	private String VALIDATE_CODE;
	
	/**
	 * 
	 * @Description: TODO 注册新账户
	 * @param @param E_MailAddr 邮箱地址
	 * @param @param password 密码
	 * @param @return   
	 * @return
	 * @throws MessagingException 
	 * @throws
	 * @author lrz
	 * @date 2017年2月10日
	 */
	@Override
	public String register(String E_MailAddr, String password) throws MessagingException {
		/*
		 * 判断该邮箱是否已经被注册
		 */
		String sql = "select count(1) from tb_baseInfo where email=?";
		long count = (Long) baseDao.queryUniqueObject(sql, new Object[]{E_MailAddr}, Long.class);
		if (count > 0){
			return EMAIL_EXIST;
		}
		/*
		 * 生成新账户
		 */
		//生成一个id
		String id = UUID.randomUUID().toString();
		sql = "insert into tb_baseInfo(id,email,name,password,isalive)values(?,?,?,?,?)";
		Object[] params = {id,E_MailAddr,E_MailAddr,password,0};
		//插入数据库
		int insertcount = baseDao.insert(sql, params);
		if (insertcount == 0) {
			//注册失败
			return REGISTER_DEFEAT;
		}
		/*
		 * 生成用户默认设置
		 */
		sql = " insert into tb_userconfig(id) values(?) ";
		baseDao.insert(sql, new Object[]{id});
		/*
		 * 发送激活邮件
		 */
		E_MailProp emp = new E_MailProp();
		emp.setAuth("true");
		emp.setFrom(EMAIL_FROM);
		emp.setTo(new String[]{E_MailAddr});
		emp.setHost(EMAIL_HOST);
		emp.setPassWord(EMAIL_PASSWORD);
		emp.setSubject("新用户激活");
		String activateUrl = ACTIVATE_URL + id;//拼接激活用户请求地址
		String messeage = EMAIL_ACTIVATE.replace("URL", activateUrl);//拼接邮件内容
		emp.setText(messeage);
		emp.setTimeuot("5000");
		emp.setUserName(EMAIL_FROM);
		E_MailServiceImpl ems = new E_MailServiceImpl();
		boolean result = ems.sendHtmlEMail(emp);
		if (result){
			return REGISTER_SUCCESS;
		}
		return REGISTER_DEFEAT;
	}
	/**
	 * 
	 * @Description: TODO 激活账户
	 * @param @param id 账户id
	 * @param @return   
	 * @return 
	 * @throws
	 * @author lrz
	 * @date 2017年2月10日
	 */
	@Override
	public String activate(String id) {
		/**
		 * 判断用户是否存在
		 */
		String sql = " select count(1) from tb_baseInfo where id=? ";
		long count = (Long) baseDao.queryUniqueObject(sql, new Object[]{id}, Long.class);
		if (count == 0){
			return ACTIVATE_USER_NOT_EXIST;
		}
		//将用户状态改为激活状态
		sql = " update tb_baseInfo set isalive=1 where id=? ";
		count = baseDao.update(sql, new Object[]{id});
		
		if(count > 0){
			return ACTIVATE_SUCCESS;
		}else{
			return ACTIVATE_DEFEAT;
		}
		
	}
	/**
	 * 
	 * @Description: TODO 登录
	 * @param userName 账户名或邮箱地址
	 * @param passWord 密码
	 * @param @return   
	 * @throws
	 * @author lrz
	 * @date 2017年2月10日
	 */
	@Override
	public User login(String userName, String passWord) {
		
		return null;
	}
	/**
	 * 
	 * 方法功能说明：  验证用户是否可用(注册并激活)
	 * 创建：2017年2月21日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @param userName
	 *  @return          
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult<User> validateUserName(String userName) {
		BaseResult<User> result = new BaseResult<User>();
		String sql = " select * from tb_baseInfo where email=? or name=? ";
		List resultList = baseDao.queryToList(sql, new Object[]{userName,userName}, new ObjectRelationalMapper("com.heisenberg.base.model.User"));
		if (resultList.size() == 0 ){
			//用户名不存在
			result.setResultType(WARM);
			result.setMessage(USERNAME_NOT_EXIST);
			return result;
		}
		User u = (User) resultList.get(0);
		if (u.getISALIVE()==0){
			//用户未激活
			result.setResultType(WARM);
			result.setMessage(USERNAME_NOT_ACTIVATED);
			return result;
		}
		result.setResultType(SUCCESS);
		result.setObj(u);
		return result;
	}
	/**
	 * 
	 * 方法功能说明：  验证密码是否正确
	 * 创建：2017年2月21日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @param userNmae
	 *  @param passWord
	 *  @return          
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult<User> validatePassWord(String userName, String passWord,User user) {
		BaseResult<User> result = new BaseResult<User>();
		if (user != null){
			String psd = user.getPASSWORD();
			if (psd.equals(passWord)){
				//密码正确
				result.setObj(user);
				result.setResultType(SUCCESS);
				return result;
			}else{
				//密码错误
				result.setResultType(WARM);
				result.setMessage(PASSWORD_ERROR);
				return result;
			}
		}else{
			String Sql = " select * from tb_baseInfo where name=? or email=? ";
			List resultList = baseDao.queryToList(Sql, new Object[]{userName,userName}, new ObjectRelationalMapper("com.heisenberg.base.model.User"));
			if (resultList.size() == 0){
				//用户不存在
				result.setResultType(ERROR);
				result.setMessage(USERNAME_NOT_EXIST);
				return result;
			}else{
				User userTemp = (User) resultList.get(0);
				if (userTemp.getPASSWORD().equals(passWord)){
					//密码正确
					result.setResultType(SUCCESS);
					return result;
				}else{
					//密码错误
					result.setResultType(ERROR);
					result.setMessage(PASSWORD_ERROR);
					return result;
				}
				
			}
		}
	}
	/**
	 * 
	 * 方法功能说明： 发送找回密码的验证码 
	 * 创建：2017年3月5日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @param email
	 *  @return          
	 * @throws
	 */
	@Override
	public String sendValidateCode(String email) {
		String validateCode = this.getRandomCode(6);
		/*
		 * 发送验证码邮件
		 */
		E_MailProp emp = new E_MailProp();
		emp.setAuth("true");
		emp.setFrom(EMAIL_FROM);
		emp.setTo(new String[]{email});
		emp.setHost(EMAIL_HOST);
		emp.setPassWord(EMAIL_PASSWORD);
		emp.setSubject("找回密码验证码");
		
		String contain = VALIDATE_CODE.replace("VALIDATE_CODE", validateCode);
		emp.setText(contain);
		emp.setTimeuot("5000");
		emp.setUserName(EMAIL_FROM);
		E_MailServiceImpl ems = new E_MailServiceImpl();
		try {
			boolean sendResult = ems.sendHtmlEMail(emp);
			if (sendResult){
				return validateCode;
			}
			
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
			
		}
		return null;
	}
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
	@Override
	public boolean saveValidateCoed(String email,String validateCode,String type,String timeOut) {
		//判断验证码是否已经存在
		String sql = "select count(1) from tb_validate_code where email=? and type=? ";
		int count = (int) baseDao.queryUniqueObject(sql, new Object[]{email,type}, Integer.class);
		if (count > 0 ){
			sql = " update tb_validate_code set validate_code=?,time_out=? where email = ? and type=? ";
			count = baseDao.update(sql, new Object[]{validateCode,timeOut,email,type});
		}else{
			sql = "insert into tb_validate_code(email,validate_code,type,time_out) values(?,?,?,?)";
			count = baseDao.insert(sql, new Object[]{email,validateCode,type,timeOut});
		}
		
		if (count > 0 ){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * 方法功能说明：	判断验证码是否正确  
	 * 创建：2017年3月6日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @param email	邮箱	
	 *  @param valideateCode	验证码
	 *  @param type	验证类型
	 *  @return          
	 * @throws
	 */
	@Override
	public boolean ifValidateRight(String email, String validateCode,
			String type) {
		String sql = " select count(1) from tb_validate_code where email=? and validate_code=? and type=? ";
		int count = (int) baseDao.queryUniqueObject(sql, new Object[]{email,validateCode,type}, Integer.class);
		if (count > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * 方法功能说明：修改密码  
	 * 创建：2017年3月6日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @param email	邮箱
	 *  @param newPassWord	新密码          
	 * @throws
	 */
	@Override
	public void modifyPassWord(String email, String newPassWord) {
		String sql = " update tb_baseInfo set password=? where email=? ";
		baseDao.update(sql, new Object[]{newPassWord,email});
		
	}
	/**
	 * 
	 * @描述：TODO(获取用户名)
	 * @param userId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月8日下午10:30:56
	 * @修改人  ：lrz
	 * @修改时间：2017年5月8日下午10:30:56
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public String getUserNameById(String userId) {
		String Sql = " select name from tb_baseInfo where id=? ";
		return (String) baseDao.queryUniqueObject(Sql, new Object[]{userId}, String.class);
	}
	
	
	
	
}
