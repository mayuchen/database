package com.pl.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.Vector;

import com.pl.database.Database;

public class PlayerAb {

	private String ID;
	private String name;
	private String weight;
	private String height;
	private Date birthday;
	private String value;
	private String nation;
	private String foot;
	private String position;
	private String youth_team;
	private int squad;

	

	public PlayerAb() {
		ID = "null";
		this.name = "null";
		this.weight = "null";
		this.height = "null";
		this.birthday = new Date(0, 0, 0);
		this.value = "null";
		this.nation = "null";
		this.foot = "null";
		this.position = "null";
		this.youth_team = "null";
		this.squad = 0;
	}

	public int getSquad() {
		return squad;
	}

	public void setSquad(int squad) {
		this.squad = squad;
	}

	public static Vector<PlayerAb> playerAbility(String team,String value,String type)
	{
		String sql="select * from players where players.player_id =players.player_id ";
		if(!team.equals("ƒ¨»œ"))
			sql="select * from players,player_team where players.player_id ="
					+ "player_team.player_id and player_team.team_name = '"+team+"' ";
		//if(type.equals("«Ú“¬∫≈")&&(!value.equals("")))
			//sql=sql+" and squad ="+value;
		if(type.equals("«Ú‘±ID")&&(!value.equals("")))
			sql=sql+"and players.player_id = "+value;
		if(type.equals("«Ú‘±–’√˚")&&(!value.equals("")))
			sql=sql+"and players.player_name = '"+value+"'";
		if(team.equals("ƒ¨»œ")&&value.equals(""))
			sql="select * from players,player_team where player_team.player_id =players.player_id";
		/****µ˜ ‘”√*****/
		System.err.println(sql);//
		ResultSet rs= Database.executeQuery(sql);
		Vector<PlayerAb> vt=new Vector<PlayerAb>();
		PlayerAb pa;
		try{
			while(rs.next())
			{
				pa=new PlayerAb();
				//System.err.println(rs.toString());

				pa.setBirthday(rs.getDate("player_birthday"));
				pa.setFoot(rs.getString("player_foot"));
				pa.setHeight(rs.getString("player_height"));
				pa.setID(rs.getString("player_id"));
				pa.setName(rs.getString("player_name"));
				//System.out.println(pa.getName());
				pa.setNation(rs.getString("player_nation"));
				pa.setPosition(rs.getString("player_position"));
				pa.setValue(rs.getString("player_value"));
				pa.setWeight(rs.getString("player_weight"));
				pa.setYouth_team(rs.getString("player_youth_team"));
				if(type.equals("«Ú“¬∫≈")&&!value.equals(""))
				{
					String s="select * from player_team "
							+ "where player_id="+pa.getID()+" and squad="+value;
					System.err.println(s);
					ResultSet r=Database.executeQuery(s);
					if(r.next())
						{
							pa.setSquad(r.getInt("squad"));
						vt.add(pa);}
				}
				else if(type.equals("«Ú“¬∫≈")&&value.equals(""))
				{

					{
						pa.setSquad(rs.getInt("squad"));
						vt.add(pa);
					}
				}
				else
					vt.add(pa);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return vt;
	}

	public static Vector<PlayerAb> list(){
		String sql = "select * from players";
		/****µ˜ ‘”√*****/
		System.out.println(sql);
		ResultSet rs= Database.executeQuery(sql);
		Vector<PlayerAb> vt=new Vector<PlayerAb>();
		PlayerAb pa;
		try{
			while(rs.next())
			{
				pa=new PlayerAb();
				//System.err.println(rs.toString());

				pa.setBirthday(rs.getDate("player_birthday"));
				pa.setFoot(rs.getString("player_foot"));
				pa.setHeight(rs.getString("player_height"));
				pa.setID(rs.getString("player_id"));
				pa.setName(rs.getString("player_name"));
				//System.out.println(pa.getName());
				pa.setNation(rs.getString("player_nation"));
				pa.setPosition(rs.getString("player_position"));
				pa.setValue(rs.getString("player_value"));
				pa.setWeight(rs.getString("player_weight"));
				pa.setYouth_team(rs.getString("player_youth_team"));
				//pa.setSquad(rs.getInt("squad"));
				vt.add(pa);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return vt;
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getFoot() {
		return foot;
	}
	public void setFoot(String foot) {
		this.foot = foot;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getYouth_team() {
		return youth_team;
	}
	public void setYouth_team(String youth_team) {
		this.youth_team = youth_team;
	}
	
}
