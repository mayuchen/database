package com.pl.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.pl.database.Database;

public class CoachInfo {

	private int id=0;
	private String name="";
	private String birthday="";
	private String nation="";
	
	public static Vector<CoachInfo> getCoachInfo(){
		String sql="select * from coach";
		ResultSet rs=Database.executeQuery(sql);
		Vector<CoachInfo> vt=new Vector<CoachInfo>();
		try {
			while(rs.next())
			{
				CoachInfo ci=new CoachInfo();
				ci.setBirthday(rs.getString("coach_birthday"));
				ci.setId(rs.getInt("coach_id"));
				ci.setName(rs.getString("coach_name"));
				ci.setNation(rs.getString("coach_nation"));
				vt.add(ci);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return vt;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	
	
	
}
