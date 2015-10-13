package com.tigerframework.wcp.channel.sms.service;

import com.mobset.bean.msmResultBean;

public interface ISmsService {
	
	public msmResultBean send(String mobiles , String content);

}
