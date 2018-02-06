package com.heisenberg.common.email.model;
/**
 * 
 * @author Abstract
 *	邮件发送参数
 */
public class E_MailProp {
	private String[] to; //接收邮箱地址
	private String from;//发送邮箱地址
	private String subject; //邮件主题
	private String text; //邮件内容
	private String userName; //发送邮箱登录名
	private String passWord; //发送邮箱密码
	private String auth; //是否认证用户名和密码
	private String timeuot; //发送超时
	private String host; //发送邮箱host
	
	public E_MailProp() {
		super();
	}

	public E_MailProp(String[] to, String from, String subject, String text,
			String userName, String passWord, String auth, String timeuot,
			String host) {
		super();
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.text = text;
		this.userName = userName;
		this.passWord = passWord;
		this.auth = auth;
		this.timeuot = timeuot;
		this.host = host;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getTimeuot() {
		return timeuot;
	}

	public void setTimeuot(String timeuot) {
		this.timeuot = timeuot;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
}