package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.test.model.UserRegistrationDataModel;

public class UserRegistrationDAO {
	private Context context = null;
	private DataSource dataSource = null;
	
	public UserRegistrationDAO() {
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/connection1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Inserts a new record into user registration table
	 * @param modelIn
	 */
	public void insertRecord(UserRegistrationDataModel modelIn) {
		PreparedStatement pStatement = null;
		reInitializeIfNeeded();
		try(Connection connection = dataSource.getConnection()) {
				
			String sql = "insert into user_registration (first_name, last_name, address_1, address_2, "
					+ " city, state, zip_code, country, registration_date ) " 
					+ " values (?,?,?,?,"
					+          " ?,?,?,?, sysdate())";

			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, modelIn.getFirstName());
			pStatement.setString(2, modelIn.getLastName());
			pStatement.setString(3,  modelIn.getAddress1());
			pStatement.setString(4, modelIn.getAddress2());
			pStatement.setString(5, modelIn.getCity());
			pStatement.setString(6, modelIn.getState());
			pStatement.setString(7, modelIn.getZipCode());
			pStatement.setString(8, modelIn.getCountry());
			
			pStatement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Unexpected Exception! ");
			e.printStackTrace();
		} finally {
			try {
				if(pStatement != null)
					pStatement.close();
			} catch (Exception e) { ; }
		}
	}
	/**
	 * gets s list of registered users
	 * @return
	 */
	public ArrayList<UserRegistrationDataModel> getRegistrationList() {
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		ArrayList<UserRegistrationDataModel> models = new ArrayList<UserRegistrationDataModel>();
		reInitializeIfNeeded();
		
		try(Connection connection = dataSource.getConnection();) {
			String sql = " select * from user_registration order by registration_date desc";

			pStatement = connection.prepareStatement(sql);
			rs = pStatement.executeQuery();
			
			while(rs.next()) {
				UserRegistrationDataModel model = new UserRegistrationDataModel();
					model.setFirstName(rs.getString("first_name"));
					model.setLastName(rs.getString("last_name"));
					model.setAddress1(rs.getString("address_1"));
					model.setAddress2(rs.getString("address_2"));
					model.setCity(rs.getString("city"));
					model.setState(rs.getString("state"));
					model.setZipCode(rs.getString("zip_code"));
					model.setCountry(rs.getString("country"));
					model.setRegistrationDate(rs.getTimestamp("registration_date"));
				models.add(model);
			}
			
			return models;
		} catch (Exception e) {
			System.out.println("Unexpected Exception! " + e.toString());
			e.printStackTrace();
			return new ArrayList<UserRegistrationDataModel>();
		} finally {
			try {
				if(pStatement != null)
					pStatement.close();
				if(rs != null)
					rs.close();
			} catch (Exception e) { ; }
		}
		
		
	}
	/**
	 * Checks if a user registration record already exists
	 * @param modelIn
	 * @return
	 */
	public boolean doesUserExist(UserRegistrationDataModel modelIn) {
		boolean exists = false;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		reInitializeIfNeeded();
		
		try(Connection connection = dataSource.getConnection()) {
			
			String sql = "select count(*) as count from user_registration " +
					     " where first_name = ? " +
					     " and last_name = ? " +
					     " and address_1 = ? " +
					     " and address_2 = ? " +
					     " and city = ? " +
					     " and state = ? " +
					     " and zip_code = ? " + 
					     " and country = ? ";
					    
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, modelIn.getFirstName());
			pStatement.setString(2, modelIn.getLastName());
			pStatement.setString(3,  modelIn.getAddress1());
			pStatement.setString(4, modelIn.getAddress2());
			pStatement.setString(5, modelIn.getCity());
			pStatement.setString(6, modelIn.getState());
			pStatement.setString(7, modelIn.getZipCode());
			pStatement.setString(8, modelIn.getCountry());
			
			
			rs = pStatement.executeQuery();
			
			if(rs.next()) {
				if(rs.getInt("count") > 0) {
					exists = true;
				}
			}
			
		} catch (Exception e) {
			System.out.println("Unexpected Exception! ");
			e.printStackTrace();
		} finally {
			try {
				if(pStatement != null)
					pStatement.close();
				if(rs != null)
					rs.close();
			} catch (Exception e) { ; }
		}
		return exists;
	}
	/**
	 * Re-initalize the context and data source
	 */
	public void reInitializeIfNeeded() {
		try {
			if(context == null) {
				context = new InitialContext();
				dataSource = (DataSource)context.lookup("java:/comp/env/jdbc/connection1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
