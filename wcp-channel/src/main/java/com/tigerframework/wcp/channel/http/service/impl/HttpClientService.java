package com.tigerframework.wcp.channel.http.service.impl;

import java.net.URLDecoder;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpProtocolParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tigerframework.wcp.channel.http.service.IHttpClientService;

@Service
public class HttpClientService implements IHttpClientService {

	private final static Logger logger = LoggerFactory
			.getLogger(HttpClientService.class);

	private final static String HTTP_PROXY_ADDRESS = "proxy6.taikanglife.com";

	private final static int HTTP_PROXY_PORT = 8080;

	public String sendRequset(String uri, boolean proxyFlag) throws Exception {

		if (uri == null) {
			throw new Exception("生成URL出错！");
		}

		String result = "";
		DefaultHttpClient httpClient = new DefaultHttpClient();
		if (proxyFlag) {
			HttpHost proxy = new HttpHost(HTTP_PROXY_ADDRESS, HTTP_PROXY_PORT);
			httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
					proxy);
		}
		try {
			HttpProtocolParams
					.setUserAgent(
							httpClient.getParams(),
							"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.9) Gecko/20100315 Firefox/3.5.9");
			HttpGet httpGet = new HttpGet(uri);
			logger.debug("本次请求的uri：" + httpGet.getURI());
			httpGet.getParams().setParameter(ConnManagerPNames.TIMEOUT, 2500L);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			result = httpClient.execute(httpGet, responseHandler);
			result = new String(result.getBytes("ISO8859-1"));
			logger.debug("result is : "
					+ new String(result.getBytes("ISO8859-1")));
		} catch (Exception e) {
			logger.error("httpService请求出错！");
			e.printStackTrace();
			throw new Exception("httpSerive请求出错");
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return result;
	}

	public String sendPostRequset(String uri, String data, boolean proxyFlag)
			throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(uri);
		String result = "";

		if (proxyFlag) {
			HttpHost proxy = new HttpHost(HTTP_PROXY_ADDRESS, HTTP_PROXY_PORT);
			httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
					proxy);
		}
		try {
			StringEntity entity = new StringEntity(new String(
					data.getBytes("UTF-8"), "ISO-8859-1"));
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);

			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			result = URLDecoder.decode(
					httpClient.execute(httpPost, responseHandler), "UTF-8");
		} catch (Exception e) {
			logger.error("http[post]请求出错！");
			e.printStackTrace();
			throw new Exception("http[post]请求出错");
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return result;
	}

}
