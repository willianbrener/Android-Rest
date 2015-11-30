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
	Button btnMenuAluno, btnMenuProfessor;
	
	
	   
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnMenuAluno=(Button) findViewById(R.id.button9);
		btnMenuProfessor=(Button) findViewById(R.id.button8);
		
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
				return null;
			}
			
			@Override
			protected void onPostExecute(String result) {
			}
	    	
	    }
	
	
}