package com.tigerframework.wcp.channel.generic.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tigerframework.wcp.channel.generic.service.IGenericService;
import com.tigerframework.wcp.channel.http.service.IHttpClientService;

@Service
public class GenericService implements IGenericService {

	private Logger logger = LoggerFactory.getLogger(GenericService.class);

	@Value("${wx.proxyFlag}")
	private String proxyFlag;

	@Resource
	private IHttpClientService httpClientService;

	@Override
	public String getOpenIdByOauth(String url) throws Exception {
		logger.debug("****新修改Oauth服务****");
		String openId = null;
		Boolean flag = Boolean.parseBoolean(proxyFlag);
		String json = httpClientService.sendRequset(url, flag);
		if (json != null && !json.equals("")) {
			logger.debug("json is : " + json);
			// ObjectMapper mapper = new ObjectMapper();
			// mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
			// Map<String, Object> jsonMap = mapper.readValue(json,
			// HashMap.class);
			JSONObject jsonObj = JSON.parseObject(json);
			String errmsg = (String) jsonObj.get("errmsg");
			if (errmsg != null) {
				logger.debug("OAuth获取OpenId失败");
			} else {
				openId = (String) jsonObj.get("openid");
				logger.debug("OAuth获取OpenId：" + openId + "成功");
			}
			return openId;
		}
		logger.error("OAuth获取OpenId失败！");
		throw new Exception("OAuth获取OpenId失败！");
	}
}
