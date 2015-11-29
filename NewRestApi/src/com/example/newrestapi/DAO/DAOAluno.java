package com.example.newrestapi.DAO;

public class DAOAluno {
	private static DAOAluno instance;

	public static DAOAluno getInstance() {
		if (instance == null)
			instance = new DAOAluno();
		return instance;
	}

}
