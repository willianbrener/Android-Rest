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
				setOperacao("CADASTRAR");
				new ExecuteTask().execute(s1, s2, s3);
			}
		});
		

	}

	class ExecuteTask extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			String retorno =  PostData(params);
			return retorno;
		}

		@Override
		protected void onPostExecute(String result) {
			progressBar.setVisibility(View.GONE);
			Toast.makeText(getApplicationContext(), result, 3000).show();
		}

	}
	
	public String PostData(String[] valuse) {
		String s = "";
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://" + Constantes.IP_SERVIDOR
					+ ":8080/HttpPostServlet1/servlet/cadastrarAluno");
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("cpf", valuse[0]));
			list.add(new BasicNameValuePair("nome", valuse[1]));
			list.add(new BasicNameValuePair("data_nascimento", valuse[2]));
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