package com.pl.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.pl.database.Database;

public class MatchInfo {

	public MatchInfo() {
		super();
		this.round = 0;
		this.match = 0;
		this.home = "null";
		this.away = "null";
		this.date = "null";
		this.time = "null";
		this.result = "null";
		this.referee = "null";
	}
	private int round;
	private int match;
	private String home;
	private String away;
	private String date;
	private String time;
	private String result;
	private String referee;
	private int refereeid;
	
	public static Vector<MatchInfo> getMatchInfoByTeam(String team)
	{
		String sql="select * from `match`,team_match,referee "
				+ "where match.match_round=team_match.match_round and "
				+ "match.match_id=team_match.match_id and "
				+ "match.match_referee_id=referee_id ";
		if(!team.equals("默认"))
			sql=sql+"and (team_match.home_team='"+team+"' OR team_match.away_team ='"+team+"')";
		//System.out.println(sql);
		Vector<MatchInfo> vt= new Vector<MatchInfo>();
		System.out.println(sql);
		ResultSet rs=Database.executeQuery(sql);
		try {
			while(rs.next())
			{
				MatchInfo mi= new MatchInfo();
				mi.setAway(rs.getString("away_team"));
				mi.setDate(rs.getString("match_date"));
				mi.setHome(rs.getString("home_team"));
				mi.setMatch(rs.getInt(2));
				mi.setReferee(rs.getString("referee_name"));
				mi.setResult(rs.getString("match_has_done").equals("是")?
						rs.getString("home_goal_number")+":"+
						rs.getString("away_goal_number"):"未结束");
				mi.setRound(rs.getInt(1));
				mi.setTime(rs.getString("match_time"));
				vt.add(mi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vt;
	}
	public static Vector<MatchInfo> getMatchInfoByRound(String round)
	{
		String sql="select * from `match`,team_match,referee "
				+ "where match.match_round=team_match.match_round and "
				+ "match.match_id=team_match.match_id and "
				+ "match.match_referee_id=referee_id ";
		if(!round.equals("全部"))
			sql=sql+"and match.match_round='"+round+"'";
		Vector<MatchInfo> vt= new Vector<MatchInfo>();
		System.out.println(sql);
		ResultSet rs=Database.executeQuery(sql);
		try {
			while(rs.next())
			{
				MatchInfo mi= new MatchInfo();
				mi.setAway(rs.getString("away_team"));
				mi.setDate(rs.getString("match_date"));
				mi.setHome(rs.getString("home_team"));
				mi.setMatch(rs.getInt(2));
				mi.setReferee(rs.getString("referee_name"));
				mi.setResult(rs.getString("match_has_done").equals("是")?
						rs.getString("home_goal_number")+":"+
						rs.getString("away_goal_number"):"未结束");
				mi.setRound(rs.getInt(1));
				mi.setTime(rs.getString("match_time"));
				vt.add(mi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vt;
	}
	public static MatchInfo			getMatchInfoByID(int round,int match)
	{
		String sql="select * from `match`,team_match,referee "
				+ "where match.match_round=team_match.match_round and "
				+ "match.match_id=team_match.match_id and "
				+ "match.match_referee_id=referee_id ";
		sql=sql+" and match.match_round="+round+" and match.match_id="+match;
		System.out.println(sql);
		ResultSet rs=Database.executeQuery(sql);
		try {
			if(rs.next())
			{
				MatchInfo mi= new MatchInfo();
				mi.setAway(rs.getString("away_team"));
				mi.setDate(rs.getString("match_date"));
				mi.setHome(rs.getString("home_team"));
				mi.setMatch(rs.getInt(2));
				mi.setReferee(rs.getString("referee_name"));
				mi.setResult(rs.getString("match_has_done").equals("是")?
						rs.getString("home_goal_number")+":"+
						rs.getString("away_goal_number"):"未结束");
				mi.setRound(rs.getInt(1));
				mi.setRefereeid(rs.getInt("match_referee_id"));
				mi.setTime(rs.getString("match_time"));
				
				return mi;
			}
			return new MatchInfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new MatchInfo();
		}
	}
	
	
	
	
	public int getRefereeid() {
		return refereeid;
	}
	public void setRefereeid(int refereeid) {
		this.refereeid = refereeid;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getMatch() {
		return match;
	}
	public void setMatch(int match) {
		this.match = match;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public String getAway() {
		return away;
	}
	public void setAway(String away) {
		this.away = away;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getReferee() {
		return referee;
	}
	public void setReferee(String referee) {
		this.referee = referee;
	}
	
}
