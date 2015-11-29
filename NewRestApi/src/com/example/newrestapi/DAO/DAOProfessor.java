package com.example.newrestapi.DAO;

public class DAOProfessor {
	private static DAOProfessor instance;

	public static DAOProfessor getInstance() {
		if (instance == null)
			instance = new DAOProfessor();
		return instance;
	}

}
