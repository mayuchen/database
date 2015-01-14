package com.pl.model;

import java.sql.ResultSet;
import java.util.Vector;

import com.pl.database.Database;

public class PlayerPr {

	private int id;
	private String name;
	private int goals;
	private int assist;
	private String team;



	public PlayerPr() {
		this.id = 0;
		this.name = "null";
		this.goals = 0	;
		this.assist = 0;
		this.team = "null";
	}

	public static Vector<PlayerPr> playerProfrom(String team,String value,String type)
	{
		String sql="select distinct shooter.* from shooter,player_team "
				+ "where id=player_id ";
	
		if(!team.equals("默认"))
			sql=sql+"and  player_team.team_name = '"+team+"' ";

		if(type.equals("球衣号")&&(!value.equals("")))
			sql=sql+" and squad ="+value;
		
		else if(type.equals("球员ID")&&(!value.equals("")))
			sql=sql+" and id = "+value;
		else if(type.equals("球员姓名")&&(!value.equals("")))
			sql=sql+" and name = '"+value+"'";
		if(team.equals("默认")&&value.equals(""))
			sql="select * from shooter";
		/****调试用*****/
		///////System.out.println(sql);
		ResultSet rs= Database.executeQuery(sql);
		Vector<PlayerPr> vt=new Vector<PlayerPr>();
		PlayerPr pp;
		try{
			while(rs.next())
			{
				pp=new PlayerPr();
				//System.err.println(rs.toString());
				pp.setAssist(rs.getInt("A"));
				pp.setGoals(rs.getInt("G"));
				pp.setId(rs.getInt("id"));
				pp.setName(rs.getString("name"));
				pp.setTeam(rs.getString("team"));
				vt.add(pp);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return vt;
	}
	
	public String getTeam() {
		return team;
	}


	public void setTeam(String team) {
		this.team = team;
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
	public int getGoals() {
		return goals;
	}
	public void setGoals(int goals) {
		this.goals = goals;
	}
	public int getAssist() {
		return assist;
	}
	public void setAssist(int assist) {
		this.assist = assist;
	}
	
}
