
package com.heisenberg.common.util.service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.heisenberg.common.util.service.CommonService;
@Service
public class CommonServiceImpl implements CommonService {
	/**
	 * 
	 * 方法功能说明：生成验证码  
	 * 创建：2017年3月5日 by 刘润枝   
	 * 修改：日期 by 修改者  
	 * 修改内容：  
	 *  @param length 验证码长度
	 *  @return          
	 * @throws
	 */
	@Override
	public String getRandomCode(int length) {
		Random random = new Random(System.currentTimeMillis());
		String nextInt = "8";
		for (int i = 0; i < length-1; i++){
			nextInt +="9";
		}
		int nextint = random.nextInt(Integer.parseInt(nextInt));
		int base = (int)Math.pow(10,length-1);
		int validateCode = nextint+base;
		return Integer.toString(validateCode);
	}
	
	
}
