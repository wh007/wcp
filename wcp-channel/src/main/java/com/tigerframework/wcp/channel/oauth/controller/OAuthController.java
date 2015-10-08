package com.tigerframework.wcp.channel.oauth.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tigerframework.wcp.channel.oauth.service.IOAuthService;

@Controller
public class OAuthController {
	private Logger logger = LoggerFactory.getLogger(OAuthController.class);

	@Resource
	private IOAuthService service;

	public static final String DEFAULT_REDIRECT_PAGE_URL = "main";

	@RequestMapping(value = "/oauth", method = RequestMethod.GET)
	public Object oauthRedirect(@RequestParam("code") String code,
			@RequestParam("state") String state, Map<String, Object> model) {
		logger.debug("state is : " + state);
		logger.debug("code is : " + code);
		String openId = null;
		String redirectUrl = DEFAULT_REDIRECT_PAGE_URL;
		// Object view = null;
		if (code != null && !code.equals("")) {
			try {
				openId = service.getUserOpenId(code);
				if (openId != null) {
					model.put("openId", openId);
				} else {
					logger.error("invoke weixin getUserOpenId faillure");
					return "error/500";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.info("最终转发URL为: " + redirectUrl);
		// view = new ModelAndView("redirect:" + redirectUrl);
		return redirectUrl;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public Object testRedirect(@RequestParam("code") String code,
			@RequestParam("state") String state, Map<String, Object> model) {
		logger.debug("state is : " + state);
		logger.debug("code is : " + code);
		String openId = null;
		String redirectUrl = DEFAULT_REDIRECT_PAGE_URL;
		// Object view = null;
		if (code != null && !code.equals("")) {
			try {
				// 临时注销取openId的方法，方便测试
				// openId = service.getUserOpenId(code);
				openId = "aaaaa";
				if (openId != null) {
					model.put("openId", openId);
				} else {
					logger.error("invoke weixin getUserOpenId faillure");
					return "error/500";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.info("最终转发URL为: " + redirectUrl);
		// view = new ModelAndView("redirect:" + redirectUrl);
		return redirectUrl;
	}
}
