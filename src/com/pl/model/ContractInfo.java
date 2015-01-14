package com.pl.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.pl.database.Database;

public class ContractInfo {

	private int PLID=0;
	private String team="";
	private String begin="";
	private String end="";
	private String salary="";
	private int squad;
	
	public static ContractInfo getContractInfoByID(int id)
	{
		String sql="select * from player_team where player_id="+id;
		System.out.println(sql);
		ResultSet rs=Database.executeQuery(sql);
		try {
			if(rs.next())
			{
				ContractInfo ci=new ContractInfo();
				ci.setBegin(rs.getString("contract_begin"));
				ci.setEnd(rs.getString("contract_end"));
				ci.setPLID(rs.getInt("player_id"));
				ci.setSalary(rs.getString("salary"));
				ci.setTeam(rs.getString("team_name"));
				ci.setSquad(rs.getInt("squad"));
				return ci;
			}
			return new ContractInfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ContractInfo();
		}
	}
	
	
	
	public int getSquad() {
		return squad;
	}



	public void setSquad(int squad) {
		this.squad = squad;
	}



	public int getPLID() {
		return PLID;
	}
	public void setPLID(int pLID) {
		PLID = pLID;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	
	
}
