package com.example.newrestapi;

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

import com.example.newrestapi.MenuAndroid.MenuAluno;
import com.example.newrestapi.MenuAndroid.MenuProfessor;

import contants.Constantes;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText editSenha,editUsuario;
	Button btnLogin,btnRegistrar, btnMenuAluno, btnMenuProfessor;
	ProgressBar progressBar;
	
	
	   
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editSenha=(EditText) findViewById(R.id.editText2);
		editUsuario=(EditText) findViewById(R.id.editText1);
		btnLogin=(Button) findViewById(R.id.button1);
		btnRegistrar=(Button) findViewById(R.id.button2);
		btnMenuAluno=(Button) findViewById(R.id.button9);
		btnMenuProfessor=(Button) findViewById(R.id.button8);
		
		
		//progess_msz.setVisibility(View.GONE);
		progressBar=(ProgressBar) findViewById(R.id.progressBar1);
		progressBar.setVisibility(View.GONE);
		
		
		btnLogin.setOnClickListener(new OnClickListener() {
		
			public void onClick(View v) {
				progressBar.setVisibility(View.VISIBLE);
				
				String s1=editUsuario.getText().toString();
				String s2=editSenha.getText().toString();
				new ExecuteTask().execute(s1,s2);
				
			}
		});
		
		btnMenuAluno.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent  intent=new Intent(MainActivity.this,MenuAluno.class);
				startActivity(intent);
				
			}
		});
		btnMenuProfessor.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent  intent=new Intent(MainActivity.this,MenuProfessor.class);
				startActivity(intent);
				
			}
		});
		

	}
	
	 class ExecuteTask extends AsyncTask<String, Integer, String>
	    {

			@Override
			protected String doInBackground(String... params) {
				
				String res=PostData(params);
				
				return res;
			}
			
			@Override
			protected void onPostExecute(String result) {
			progressBar.setVisibility(View.GONE);
			//progess_msz.setVisibility(View.GONE);
			Toast.makeText(getApplicationContext(), result, 3000).show();
			}
	    	
	    }
	
	public String PostData(String[] valuse) {
		String s="";
    	try
    	{
    	HttpClient httpClient=new DefaultHttpClient();
    	HttpPost httpPost=new HttpPost("http://"+Constantes.IP_SERVIDOR+":8080/HttpPostServlet1/servlet/Login");
    	
    	List<NameValuePair> list=new ArrayList<NameValuePair>();
    	list.add(new BasicNameValuePair("usuario", valuse[0]));
    	list.add(new BasicNameValuePair("senha",valuse[1]));
    	httpPost.setEntity(new UrlEncodedFormEntity(list));
        HttpResponse httpResponse=	httpClient.execute(httpPost);
    
        HttpEntity httpEntity=httpResponse.getEntity();
        s= readResponse(httpResponse);
  
    	}
    	catch(Exception exception) 	{}
		return s;
	
		
	}
	public String readResponse(HttpResponse res) {
		InputStream is=null; 
		String return_text="";
		try {
			is=res.getEntity().getContent();
			BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));
			String line="";
			StringBuffer sb=new StringBuffer();
			while ((line=bufferedReader.readLine())!=null)
			{
			sb.append(line);
			}
			return_text=sb.toString();
		} catch (Exception e)
		{
			
		}
		return return_text;
		
	}
	
}