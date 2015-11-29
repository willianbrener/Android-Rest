package com.example.newrestapi.DAO;

import com.example.newrestapi.Rest.RestClass;

import android.os.AsyncTask;

public class DAOAluno {
	RestClass rest;
	private static DAOAluno instance;

	public static DAOAluno getInstance() {
		if (instance == null)
			instance = new DAOAluno();
		return instance;
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
