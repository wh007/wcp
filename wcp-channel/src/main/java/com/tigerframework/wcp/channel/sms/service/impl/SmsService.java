package com.tigerframework.wcp.channel.sms.service.impl;

import org.springframework.stereotype.Service;

import com.mobset.bean.msmResultBean;
import com.mobset.sms.sms_Send;
import com.tigerframework.wcp.channel.sms.service.ISmsService;

@Service
public class SmsService implements ISmsService {

	@Override
	public boolean send(String mobiles, String content) {
		msmResultBean msmResultBean = nativeSend(mobiles, content);
		if (msmResultBean.getErrMsg().value.contains("成功提交")) {
			return true;
		}
		return false;
	}

	private msmResultBean nativeSend(String mobiles, String content) {
		return sms_Send.SendMsg(mobiles, content);
	}

	public static void main(String[] args) {
		SmsService service = new SmsService();
		if (service.send("13810011368", "测试中文502")) {
			System.out.println("success");
		} else {
			System.out.println("fail");
		}
	}
}
