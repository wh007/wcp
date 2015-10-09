package com.tigerframework.wcp.channel.http.service;

public interface IHttpClientService {

	public String sendRequset(String uri, boolean proxyFlag) throws Exception;

	public String sendPostRequset(String uri, String data, boolean proxyFlag)
			throws Exception;

}
