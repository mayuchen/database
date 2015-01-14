package com.pl.database;

import com.pl.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class Database {

	protected static String dbClassName = "com.mysql.jdbc.Driver";;
	protected static String dbUrl = "jdbc:mysql://localhost:3306/"
			+ "plsystem";
	protected static String dbUser = "visitor";
	protected static String dbPwd = "123456";
	protected static String second = null;
	private static Connection conn = null;
	
	
	private Database() {
		//dbUser=""
		try {
			if (conn == null) {
				Class.forName(dbClassName).newInstance();
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
				//System.out.println(dbUser);
			}
			else
				return;
		} catch (Exception ee) {
			ee.printStackTrace();
		}

	}

	public static boolean check(String name,String password)
	{
		try {
			conn=DriverManager.getConnection(dbUrl, name, password);
			dbUser=name;
			dbPwd=password;
			if(!name.equals("visitor"))
				return true;
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
	}
	
	public static ResultSet executeQuery(String sql) {
		try {
			if(conn==null)
			new Database();
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
		}
	}

	public static int executeUpdate(String sql) {
		
		try {
			if(conn==null)
				new Database();
			return conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			//if(e.getMessage().equals("[Microsoft][SQLServer 2000 Driver for JDBC][SQLServer]DELETE 语句与 COLUMN REFERENCE 约束 'FK_TB_BORRO_REFERENCE_TB_BOOKI' 冲突。该冲突发生于数据库 'db_library'，表 'tb_borrow', column 'bookISBN'。"))
				
			return -1;
		} finally {
		}
	}

	
	public static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn = null;
		}
	}
	
	
	
	

}
