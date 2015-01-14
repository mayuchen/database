package com.pl.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.pl.database.Database;

public class RefereeInfo {

	private int id=0;
	private String name="";
	private String standard="";
	private String birthday="";
	
	
	public static Vector<RefereeInfo> RefereeList()
	{
		String sql="select * from referee";
		ResultSet rs=Database.executeQuery(sql);
		Vector<RefereeInfo>vt=new Vector<RefereeInfo>();
		try {
			while(rs.next())
			{
				RefereeInfo ri=new RefereeInfo();
				ri.setBirthday(rs.getString("referee_birthday"));
				ri.setId(rs.getInt("referee_id"));
				ri.setName(rs.getString("referee_name"));
				ri.setStandard(rs.getString("referee_standard"));
				vt.add(ri);
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
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	
	
}
