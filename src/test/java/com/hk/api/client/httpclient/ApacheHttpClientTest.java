package com.hk.api.client.httpclient;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.junit.Test;

@SuppressWarnings("deprecation")
public class ApacheHttpClientTest {
	
	@Test
	public void getVersion() throws Exception{
		JSONObject obj = new JSONObject();
        final HttpClient httpClient = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 10000);

        HttpGet httpGet = new HttpGet();
        httpGet.setURI(new URI("http://127.0.0.1:10000/hello/version.hk"));
        UsernamePasswordCredentials creds = new UsernamePasswordCredentials("wen", "123456");
        httpGet.addHeader(new BasicScheme().authenticate(creds, httpGet));
        httpGet.addHeader("Accept", "application/json");
        httpGet.addHeader("Content-Type", "application/json");
        HttpResponse response = httpClient.execute(httpGet);

        HttpEntity resEntity = response.getEntity();
        String result = EntityUtils.toString(resEntity, "UTF-8");

        JSONParser parser = new JSONParser();

        obj = (JSONObject) parser.parse(result);
        
        System.out.println();
        System.out.println("==================");
        System.out.println("Apache Http Client");
        System.out.println("==================");
		System.out.println(obj.toJSONString());
		System.out.println("==================");
	}
}
