package com.example.newrestapi.Rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import contants.Constantes;

public class RestClass {
	private static RestClass instance;

	public static RestClass getInstance() {
		if (instance == null)
			instance = new RestClass();
		return instance;
	}

	@SuppressWarnings("unused")
	public String PostData(String[] valuse) {
		String s = "";
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://" + Constantes.IP_SERVIDOR
					+ ":8080/HttpPostServlet1/servlet/httpPostServlet");
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("usuario", valuse[0]));
			list.add(new BasicNameValuePair("senha", valuse[1]));
			httpPost.setEntity(new UrlEncodedFormEntity(list));
			HttpResponse httpResponse = httpClient.execute(httpPost);

			HttpEntity httpEntity = httpResponse.getEntity();
			s = readResponse(httpResponse);
		} catch (Exception e) {
			System.out.println(e);
		}
		return s;

	}

	public String readResponse(HttpResponse res) {
		InputStream is = null;
		String return_text = "";
		try {
			is = res.getEntity().getContent();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(is));
			String line = "";
			StringBuffer sb = new StringBuffer();
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			return_text = sb.toString();
		} catch (Exception e) {

		}
		return return_text;

	}

}
