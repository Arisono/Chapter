package com.didispace.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import okhttp3.Response;

public class ApiUasUtils {

	//http://218.18.115.198:8888/ERP/  http://218.18.115.198:8888/ERP/
	private static final String baseurl = "http://218.18.115.198:8888/ERP/";
	//YITOA_DATACENTER  USOFTSYS
	private static final String master = "YITOA_DATACENTER";

	public static String login(String phone, String password) {
		Map<String, Object> params = new HashMap<>();
		params.put("username", phone);
		params.put("password", password);
		params.put("master", master);
		Response response = OkhttpUtils.sendHttp(baseurl + "mobile/login.action", params, "", "login", "post");
		String content = null;
		try {
			content = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	public static String getSignInfo(String emcode, String sessionId, String date) {
		Map<String, Object> params = new HashMap<>();
		params.put("currentMaster", master);
		params.put("master", master);
		params.put("emcode", emcode);
		params.put("caller", "CardLog");
		params.put("condition", "cl_emcode='"+emcode+"' and to_char(cl_time,'yyyy-MM-dd')='" + date + "'");
		params.put("page", "1");
		params.put("sessionId", sessionId);
		Response response =
				OkhttpUtils.sendHttp(baseurl + "/mobile/oa/workdata.action", params, sessionId, "login", "post");
		String content = null;
		try {
			content = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	public static String getCode(String sessionId) {
		Map<String, Object> params = new HashMap<>();
		params.put("master", master);
		params.put("type", "2");
		params.put("caller", "CardLog");
		params.put("sessionId", sessionId);
		Response response =
				OkhttpUtils.sendHttp(baseurl + "common/getCodeString.action", params, sessionId, "login", "post");
		String content = null;
		try {
			content = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	public static String saveSign(String sessionId, String phone, String emcode, String emname, String dis, String date) {
		System.out.println("执行特殊方法！！！");
		String code = getCode(sessionId);
		code = JSON.parseObject(code).getString("code");
		String formStore = "" + "{\"cl_emname\":\"" + emname + "\"," + "\"cl_distance\":" + dis + "," + "\"cl_time\":\""
				+ date + "\"," + "\"cl_emcode\":\"" + emcode + "\"," + "\"cl_phone\":\"" + phone + "\","
				+ "\"cl_code\":\"" + code + "\"," + "\"cl_location\":\"在英唐大厦附近\","
				+ "\"cl_address\":\"中国广东省深圳市南山区科技南五路5\"}";

		Map<String, Object> params = new HashMap<>();
		params.put("master", master);
		params.put("formStore", formStore);
		params.put("caller", "CardLog");
		params.put("sessionId", sessionId);
		System.out.println(formStore);
		Response response =
				OkhttpUtils.sendHttp(baseurl + "mobile/saveCardLog.action", params, sessionId, "login", "post");
		String content = null;
		try {
			content = response.body().string();
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return content;
	}

}
