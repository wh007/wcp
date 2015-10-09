package com.tigerframework.wcp.channel.oauth.service;

public interface IOAuthService {

	String getUserOpenId(String code) throws Exception;

}
