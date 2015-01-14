package com.pl.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.pl.database.Database;

public class TeamInfo {
	private String name;
	private String hcolor;
	private String acolor;
	private String abb;
	private String stadium;
	private String coach;

	private int w;
	private int d;
	private int l;
	private int round;
	
	
	
	public TeamInfo() {
		this.name = "null";
		this.hcolor = "null";
		this.acolor = "null";
		this.abb = "null";
		this.stadium = "null";
		this.coach = "null";
		this.w = 0;
		this.d = 0;
		this.l = 0;
		this.round = 0;
		this.point = 0;
		G = 0;
		BG = 0;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}





	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}
	private int point;
	private int G;
	private int BG;

	public static Vector<TeamInfo>leaguetable()
	{
		String sql="select * from leaguetable";
		ResultSet rs= Database.executeQuery(sql);
		Vector<TeamInfo> vt=new Vector<TeamInfo>();

		TeamInfo ti;
		try{
			while(rs.next())
			{
				ti=new TeamInfo();
				//System.err.println(rs.toString());
				ti.setName(rs.getString("team"));
				ti.setBG(rs.getInt("BG"));
				ti.setG(rs.getInt("G"));
				ti.setW(rs.getInt("win"));
				ti.setL(rs.getInt("lose"));
				ti.setPoint(rs.getInt("P"));
				ti.setD(rs.getInt("Draw"));
				ti.setRound(rs.getInt("match_done"));
				vt.add(ti);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return vt;
	}
	
	public static Vector<TeamInfo> teamlist()
	{
		String sql="select team_name from team";
		/****调试用*****/
		/**System.out.println(sql);**/
		ResultSet rs= Database.executeQuery(sql);
		Vector<TeamInfo> vt=new Vector<TeamInfo>();

		TeamInfo ti;
		try{
			while(rs.next())
			{
				ti=new TeamInfo();
				//System.err.println(rs.toString());
				ti.setName(rs.getString("team_name"));
				vt.add(ti);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return vt;
	}
	
	public static TeamInfo byCoachID(int id){
		String sql="select * from team where "
				+ "team.team_coach_id="+id;
		ResultSet rs= Database.executeQuery(sql);
		try {
			if(rs.next())
			{
				TeamInfo ti=new TeamInfo();
				ti.setName(rs.getString("team_name"));
				return ti;
			}
			else
				return new TeamInfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new TeamInfo();
		}
	}
	
	public static Vector<TeamInfo> teamDetail(String team)
	{
		String sql="select * from team,leaguetable,coach where "
				+ "team.team_coach_id=coach.coach_id and team=team_name";
		if(!team.equals("默认"))
			sql=sql+" and team ='"+team+"'";
		/****调试用*****/
		///System.out.println(sql);
		ResultSet rs= Database.executeQuery(sql);
		Vector<TeamInfo> vt=new Vector<TeamInfo>();

		TeamInfo ti;
		try{
			while(rs.next())
			{
				ti=new TeamInfo();
				//System.err.println(rs.toString());
				ti.setAbb(rs.getString("team_abb"));
				ti.setAcolor(rs.getString("team_away_color"));
				ti.setBG(rs.getInt("BG"));
				ti.setCoach(rs.getString("coach_name"));
				ti.setG(rs.getInt("G"));
				ti.setHcolor(rs.getString("team_home_color"));
				ti.setName(rs.getString("team"));
				ti.setPoint(rs.getInt("P"));
				ti.setStadium(rs.getString("team_stadium"));
				vt.add(ti);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return vt;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHcolor() {
		return hcolor;
	}
	public void setHcolor(String hcolor) {
		this.hcolor = hcolor;
	}
	public String getAcolor() {
		return acolor;
	}
	public void setAcolor(String acolor) {
		this.acolor = acolor;
	}
	public String getAbb() {
		return abb;
	}
	public void setAbb(String abb) {
		this.abb = abb;
	}
	public String getStadium() {
		return stadium;
	}
	public void setStadium(String stadium) {
		this.stadium = stadium;
	}
	public String getCoach() {
		return coach;
	}
	public void setCoach(String coach) {
		this.coach = coach;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getG() {
		return G;
	}
	public void setG(int g) {
		G = g;
	}
	public int getBG() {
		return BG;
	}
	public void setBG(int bG) {
		BG = bG;
	}
	
	
	
}
