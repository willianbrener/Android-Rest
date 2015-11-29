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

	@SuppressWarnings("unused")
	public String PostData(String[] valuse, String operation,String entidade) {
		String s = "";
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://" + Constantes.IP_SERVIDOR
					+ ":8080/HttpPostServlet1/servlet/httpPostServlet");
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			for (int i = 0; i < valuse.length; i++) {
				list.add(new BasicNameValuePair("value" + i + "", valuse[i]));
			}
			list.add(new BasicNameValuePair("quantidade", valuse[101]));
			list.add(new BasicNameValuePair("operacao", operation));
			list.add(new BasicNameValuePair("entidade", entidade));
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
