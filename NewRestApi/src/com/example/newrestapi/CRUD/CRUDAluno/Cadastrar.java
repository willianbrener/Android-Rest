package com.example.newrestapi.CRUD.CRUDAluno;

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

import com.example.newrestapi.R;
import com.example.newrestapi.Activity.GenericActivity;
import com.example.newrestapi.Rest.RestClass;

import contants.Constantes;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Cadastrar extends GenericActivity {
	EditText editCPF, editNome, editDataNasci;
	Button btnCadastrar, btnLimpar;
	ProgressBar progressBar;
	RestClass rest = new RestClass();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastrar_aluno);
		
		editCPF = (EditText) findViewById(R.id.editText1);
		editNome = (EditText) findViewById(R.id.editText2);
		editDataNasci = (EditText) findViewById(R.id.editText3);
		
		btnCadastrar = (Button) findViewById(R.id.button1);
		btnLimpar = (Button) findViewById(R.id.button2);

		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		progressBar.setVisibility(View.GONE);

		btnCadastrar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				progressBar.setVisibility(View.VISIBLE);

				String s1 = editCPF.getText().toString();
				String s2 = editNome.getText().toString();
				String s3 = editDataNasci.getText().toString();
				setOperacao("CADASTRO");
				new ExecuteTask().execute(s1, s2, s3);
			}
		});
		

	}

	class ExecuteTask extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			String retorno =  getRest().PostData(params,operacao,"aluno");
			return retorno;
		}

		@Override
		protected void onPostExecute(String result) {
			progressBar.setVisibility(View.GONE);
			Toast.makeText(getApplicationContext(), result, 3000).show();
		}

	}

	public RestClass getRest() {
		return rest;
	}

	public void setRest(RestClass rest) {
		this.rest = rest;
	}

}