package com.heisenberg.common.email.service.impl;




import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;



import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.heisenberg.common.email.model.E_MailProp;
import com.heisenberg.common.email.service.E_MailService;


/**
 * 
 * @author Abstract
 * 邮件服务
 */
@Service
public class E_MailServiceImpl implements E_MailService{
	/**
	 * 
	 *
	 * @描述：TODO(发送简单的文本邮件)
	 *
	 * @param prop 邮件 
	 * @return
	 * boolean
	 * @创建人  ：lrz
	 * @创建时间：2017年2月10日上午11:18:37
	 * @修改人  ：lrz
	 * @修改时间：2017年2月10日上午11:18:37
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public boolean sendSimpleEMail(E_MailProp props) {
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		senderImpl.setHost(props.getHost());//设置发送邮箱的host
		senderImpl.setUsername(props.getUserName());//设置发送邮箱登录名
		senderImpl.setPassword(props.getPassWord());//设置发送邮箱登录密码
		Properties prop = new Properties();  
        prop.put(" mail.smtp.auth ", props.getAuth()); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确  
        prop.put(" mail.smtp.timeout ", props.getTimeuot());  
        senderImpl.setJavaMailProperties(prop);  
        // 建立邮件消息  
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(props.getTo());  
        mailMessage.setFrom(props.getFrom());  
        mailMessage.setSubject(props.getSubject());  
        mailMessage.setText(props.getText());  
        senderImpl.send(mailMessage);  
		return true;
		
	}
	/**
	 * 
	 * @Description: 发送简单的html邮件
	 * @param  props
	 * @param   
	 * @return 
	 * @throws MessagingException
	 * @throws
	 * @author lrz
	 * @date 2017年2月10日
	 */
	@Override
	public Boolean sendHtmlEMail(E_MailProp props) throws MessagingException  {
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		senderImpl.setHost(props.getHost());//设置发送邮箱的host
		senderImpl.setUsername(props.getUserName());//设置发送邮箱登录名
		senderImpl.setPassword(props.getPassWord());//设置发送邮箱登录密码
		Properties prop = new Properties();  
        prop.put(" mail.smtp.auth ", props.getAuth()); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确  
        prop.put(" mail.smtp.timeout ", props.getTimeuot());  
        senderImpl.setJavaMailProperties(prop);  
        // 建立邮件消息  
        MimeMessage mailMessage = senderImpl.createMimeMessage();  
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true,"GBK"); 
        try {
			messageHelper.setTo(props.getTo());
			messageHelper.setFrom(props.getFrom());  
	        messageHelper.setSubject(props.getSubject());  
	        messageHelper.setText(props.getText(),true);  
	        senderImpl.send(mailMessage);  
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}  
        return false;
	}
		
}
