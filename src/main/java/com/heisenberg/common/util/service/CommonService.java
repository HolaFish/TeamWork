package com.heisenberg.common.util.service;

public interface CommonService {
	/**
	 * 
	 * 方法功能说明： 生成一个验证码  
	 * 创建：2017年3月5日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @param length 验证码长度
	 *  @return          
	 * @throws
	 */
	public String getRandomCode(int length);
}
