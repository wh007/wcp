package com.tigerframework.wcp.channel.oauth.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tigerframework.wcp.channel.generic.service.IGenericService;
import com.tigerframework.wcp.channel.oauth.service.IOAuthService;

@Service
public class OAuthService implements IOAuthService {
	private Logger logger = LoggerFactory.getLogger(OAuthService.class);

	private String baseOAuthTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?";

	@Value("${wx.appId}")
	private String appId;

	@Value("${wx.secret}")
	private String secret;

	// @Resource
	// private AccountConf accountConf;
	@Resource
	private IGenericService genericService;

	@Override
	public String getUserOpenId(String code) throws Exception {
		String tokenUrl = getOAuthTokenUrl(code);
		logger.debug("tokenUrl is :" + tokenUrl);
		return genericService.getOpenIdByOauth(tokenUrl);
	}

	private String getOAuthTokenUrl(String code) {
		logger.debug("getOAuthTokenUrl,appId = " + appId + " ,secret = "
				+ secret);
		return new StringBuffer(baseOAuthTokenUrl).append("appId=")
				.append(appId).append("&secret=").append(secret)
				.append("&code=").append(code)
				.append("&grant_type=authorization_code").toString();
	}

}
