package com.example.newrestapi.MenuAndroid;

import com.example.newrestapi.R;
import com.example.newrestapi.CRUDAluno.Cadastrar;
import com.example.newrestapi.CRUDAluno.Deletar;
import com.example.newrestapi.CRUDAluno.Listar;
import com.example.newrestapi.Rest.RestClass;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuAluno extends Activity {
	Button btnCadastrar, btnListar, btnDeletar;
	RestClass rest;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_aluno);

		btnCadastrar = (Button) findViewById(R.id.button1);
		btnListar = (Button) findViewById(R.id.button2);
		btnDeletar = (Button) findViewById(R.id.button3);

		btnCadastrar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuAluno.this, Cadastrar.class);
				startActivity(intent);
			}
		});
		btnListar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuAluno.this, Listar.class);
				startActivity(intent);
			}
		});
		btnDeletar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuAluno.this, Deletar.class);
				startActivity(intent);
			}
		});

	}

	class ExecuteTask extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {

			String retorno = rest.PostData(params);
			return retorno;
		}

		@Override
		protected void onPostExecute(String result) {
		}

	}

}