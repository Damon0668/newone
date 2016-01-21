package com.liefeng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class TestHttp {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();

	    HttpHost targetHost = new HttpHost("localhost", 8081, "http");
	    CredentialsProvider credsProvider = new BasicCredentialsProvider();
	    credsProvider.setCredentials(
	            new AuthScope(targetHost.getHostName(), targetHost.getPort()),
	            new UsernamePasswordCredentials("clientapp", "123456"));

	    // 创建 AuthCache 对象
	    AuthCache authCache = new BasicAuthCache();
	    //创建 BasicScheme，并把它添加到 auth cache中
	    BasicScheme basicAuth = new BasicScheme();
	    authCache.put(targetHost, basicAuth);

	    // 把AutoCache添加到上下文中
	    HttpClientContext context = HttpClientContext.create();
	    context.setCredentialsProvider(credsProvider);
	    context.setAuthCache(authCache);

	    HttpPost httpget = new HttpPost("/oauth/token");
	    List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        nvps.add(new BasicNameValuePair("password", "spring"));  
        nvps.add(new BasicNameValuePair("username", "roy"));  
        nvps.add(new BasicNameValuePair("grant_type", "password"));  
        httpget.setEntity(new UrlEncodedFormEntity(nvps));  
	    for (int i = 0; i < 0; i++) {
	        CloseableHttpResponse response = httpclient.execute(
	                targetHost, httpget, context);
	        try {
	            HttpEntity entity = response.getEntity();
	            System.out.println(entity);
	        } finally {
	            response.close();
	        }
	    }
	    
	    httpget = new HttpPost("/greeting");
	    httpget.addHeader("Authorization", "Bearer 0a97af15-7a37-4f4d-8fa6-7677bfcd2771");
	    for (int i = 0; i < 1; i++) {
	        CloseableHttpResponse response = httpclient.execute(
	                targetHost, httpget, context);
	        try {
	            HttpEntity entity = response.getEntity();
	            System.out.println(entity);
	        } finally {
	            response.close();
	        }
	    }
	}
}
