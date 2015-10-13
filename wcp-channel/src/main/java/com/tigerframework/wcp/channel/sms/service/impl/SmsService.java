package com.tigerframework.wcp.channel.sms.service.impl;

import org.springframework.stereotype.Service;

import com.mobset.bean.msmResultBean;
import com.mobset.sms.sms_Send;
import com.tigerframework.wcp.channel.sms.service.ISmsService;

@Service
public class SmsService implements ISmsService {

	@Override
	public msmResultBean send(String mobiles, String content) {
		return sms_Send.SendMsg(mobiles, content);
	}
	// 判断是否发送成功可使用以下逻辑
	// msmResultBean.getErrMsg().value.indexOf("成功提交") != -1
	// public static void main(String[] args) {
	// SmsService service = new SmsService();
	// msmResultBean msmResultBean = service.send("13426135918", "测试中文501");
	// String result = msmResultBean.getErrMsg().value;
	// if (result.indexOf("成功提交") != -1) {
	// System.out.println("success");
	// } else {
	// System.out.println("fail");
	// }
	// }
}
