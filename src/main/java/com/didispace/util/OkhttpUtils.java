package com.didispace.util;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.didispace.dto.ApiModel;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.FormBody.Builder;

public class OkhttpUtils {

	public static int maxLoadTimes = 5;
	private static int serverLoadTimes = 0;

	public static OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
			.readTimeout(10, TimeUnit.SECONDS).sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts())// 信任所有证书
			.hostnameVerifier(new TrustAllHostnameVerifier()).build();

	public static Response sendHttp(String url, Map<String, Object> params, String cookies, String tag, String method) {
		serverLoadTimes = 0;
		if ("get".equals(method)) {
			return sendGetHttp(url, params, cookies, tag, true);
		}
		if ("post".equals(method)) {
			return sendPostHttp(url, params, cookies, tag, true);
		}
		return null;
	}

	public static Response sendPostHttp(String url, Map<String, Object> params, String cookies, String tag, boolean isSyn) {
		Builder paramBuilder = new FormBody.Builder();
		if (!params.isEmpty()) {
			Iterator<Map.Entry<String, Object>> entries = params.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry<String, Object> entry = entries.next();
				paramBuilder.add(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
			}
			RequestBody formBody = paramBuilder.build();
			Request request = new Request.Builder().url(url).addHeader("content-type", "text/html;charset:utf-8")
					.addHeader("Cookie", "JSESSIONID=" + cookies).post(formBody).build();
			if (isSyn) {
				try {
					Response response = OkhttpUtils.client.newCall(request).execute();
					System.out.println("请求执行成功！");
					return response;
				} catch (IOException e1) {
					e1.printStackTrace();
					return null;
				}
			} else {
				OkhttpUtils.client.newCall(request).enqueue(new Callback() {

					@Override
					public void onResponse(Call call, Response response) throws IOException {
						String requestJson = OkhttpUtils.getResponseString(response);
						ApiModel model = new ApiModel();
						model.setTag(tag);
						model.setContent(requestJson);
						RxBus.getInstance().send(model);
					}

					@Override
					public void onFailure(Call call, IOException e) {
						OkhttpUtils.onFailurePrintln(call, e, this);
					}
				});
				return null;
			}
		}
		return null;
	}

	public static Response sendGetHttp(String url, Map<String, Object> params, String cookies, String tag, boolean isSyn) {
		StringBuilder buf = new StringBuilder(url);
		if (params != null) {
			if (!params.isEmpty()) {
				if (url.indexOf("?") == -1)
					buf.append("?");
				else if (!url.endsWith("&"))
					buf.append("&");
				Iterator<Map.Entry<String, Object>> entries = params.entrySet().iterator();
				while (entries.hasNext()) {
					Map.Entry<String, Object> entry = entries.next();
					buf.append(String.valueOf(entry.getKey())).append("=").append(String.valueOf(entry.getValue()))
							.append("&");
				}
				buf.deleteCharAt(buf.length() - 1);
			}
		}
		Request request = new Request.Builder().url(buf.toString()).addHeader("content-type", "text/html;charset:utf-8")
				.addHeader("Cookie", "JSESSIONID=" + cookies).build();

		if (isSyn) {
			try {
				Response response = OkhttpUtils.client.newCall(request).execute();
				System.out.println("请求执行成功！");
				return response;
			} catch (IOException e1) {
				e1.printStackTrace();
				return null;
			}
		} else {
			OkhttpUtils.client.newCall(request).enqueue(new Callback() {

				@Override
				public void onResponse(Call call, Response response) throws IOException {
					String requestJson = OkhttpUtils.getResponseString(response);
					ApiModel model = new ApiModel();
					model.setTag(tag);
					model.setContent(requestJson);
					RxBus.getInstance().send(model);
				}

				@Override
				public void onFailure(Call call, IOException e) {
					OkhttpUtils.onFailurePrintln(call, e, this);
				}
			});
			return null;
		}

	}

	public static String getResponseString(Response response) throws IOException {
		if (response.isSuccessful()) {
			String json = response.body().string();
			return json;
		} else {
			return "code:" + response.code() + "\n  message:" + response.message() + "\n errorBody:"
					+ response.body().string() + "\n 异常栈：" + response;
		}
	}

	protected static void onFailurePrintln(Call call, IOException e, Callback callback) {
		if (e instanceof ConnectException) {
			System.out.println("服务器拒绝访问！");
		} else if (e instanceof SocketTimeoutException) {
			System.out.println("超时响应！");
		} else {
			System.out.println("运行异常！");
			e.printStackTrace();
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		if (serverLoadTimes < maxLoadTimes) {
			serverLoadTimes++;
			System.out.println("重复请求：" + serverLoadTimes);
			OkhttpUtils.client.newCall(call.request()).enqueue(callback);
			;
		}
	}

	public static class TrustAllCerts implements X509TrustManager {
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}

	}

	public static class TrustAllHostnameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	public static SSLSocketFactory createSSLSocketFactory() {
		SSLSocketFactory ssfFactory = null;
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, new TrustManager[] { new TrustAllCerts() }, new SecureRandom());

			ssfFactory = sc.getSocketFactory();
		} catch (Exception e) {
		}
		return ssfFactory;
	}

}
