package com.pl;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JInternalFrame;

import com.pl.iframe.insert.*;
import com.pl.iframe.search.*;
import com.pl.iframe.system.LoginIFrame;
import com.pl.iframe.update.CoachModifyAndDelIFrame;
import com.pl.iframe.update.MatchResultUpdateIFrame;
import com.pl.iframe.update.PlayerModifyAndDelIFrame;




public class MenuActions {
	private static Map<String, JInternalFrame> frames;
	
	//public static ReaderAddAction TEST;
	public static PasswordModiAction 	MODIFY_PASSWORD;
	public static PlayerSearchAction 	PLAYER_SEARCH;
	public static PlayerInsertAction 	ADD_PLAYER;
	public static TeamSearchAction   	TEAM_SEARCH;
	public static MatchSearchAction	 	MATCH_SEARCH;
	public static PlayerModifyAndDeleteAction PLAYER_UPDATE;
	public static LeagueSearchAction 	LEAGUE_SEARCH;
	public static CoachInsertAction  	ADD_COACH;
	public static RefereeInsertAction 	ADD_REFEREE;
	public static ReLoginAction			RE_LOGIN;
	public static CoachModifyAndDeleteAction  COACH_UPDATE;
	public static MatchUpdateAction		MATCH_UPDATE;
	
	//public static 
	
	static
	{
		frames = new HashMap<String, JInternalFrame>();
		//TEST= new ReaderAddAction();
		MODIFY_PASSWORD = new PasswordModiAction();
		PLAYER_SEARCH   = new PlayerSearchAction();
		ADD_PLAYER      = new PlayerInsertAction();
		TEAM_SEARCH		= new TeamSearchAction();
		MATCH_SEARCH	= new MatchSearchAction();
		PLAYER_UPDATE	= new PlayerModifyAndDeleteAction();
		LEAGUE_SEARCH	= new LeagueSearchAction();
		ADD_COACH		= new CoachInsertAction();
		ADD_REFEREE		= new RefereeInsertAction();
		RE_LOGIN		= new ReLoginAction();
		COACH_UPDATE	= new CoachModifyAndDeleteAction();
		MATCH_UPDATE	= new MatchUpdateAction();
	}
	
	private MenuActions() {
		super();
	}
	
	
	/****system actions****/
	
	private static class PasswordModiAction extends AbstractAction {
		PasswordModiAction() {
			putValue(Action.NAME,"更改口令");
			putValue(Action.LONG_DESCRIPTION, "修改当前用户密码");
			putValue(Action.SHORT_DESCRIPTION, "更换口令");//在“更改口令”提示中显示的文字
			//putValue(Action.SMALL_ICON,CreatecdIcon.add("bookAddtb.jpg"));
			//将图标存储到动作对象中
			//setEnabled(false);//使动作禁用
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	
	}

	private static class ReLoginAction extends AbstractAction{
		
		ReLoginAction(){
			super("重新登录",null);
			putValue(Action.LONG_DESCRIPTION,"重新登录");
			putValue(Action.SHORT_DESCRIPTION,"重登陆");
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			LoginIFrame.frame.setVisible(false);
			PLsystem.login=new LoginIFrame();
		}
		
	}
	
	/****search actions****/
	
	private static class PlayerSearchAction extends AbstractAction{

		PlayerSearchAction()
		{
			super("球员查询",null);
			putValue(Action.LONG_DESCRIPTION,"查询球员信息");
			putValue(Action.SHORT_DESCRIPTION,"球员查询");
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (!frames.containsKey("球员查询")||frames.get("球员查询").isClosed()) {
				PlayerSearchIFrame iframe=new PlayerSearchIFrame();
				frames.put("球员查询", iframe);
				PLsystem.addIFame(frames.get("球员查询"));
			}
			
		}
		
	}
	
	private static class TeamSearchAction extends AbstractAction{
		TeamSearchAction()
		{
			super("球队查询",null);
			putValue(Action.LONG_DESCRIPTION,"查询球队信息");
			putValue(Action.SHORT_DESCRIPTION,"球队查询");
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (!frames.containsKey("球队查询")||frames.get("球队查询").isClosed()) {
				TeamSearchIFrame iframe=new TeamSearchIFrame();
				frames.put("球队查询", iframe);
				PLsystem.addIFame(frames.get("球队查询"));
			}
		}
		
	}
	
	private static class MatchSearchAction extends AbstractAction{

		MatchSearchAction()
		{
			super("比赛查询",null);
			putValue(Action.LONG_DESCRIPTION,"查询比赛信息");
			putValue(Action.SHORT_DESCRIPTION,"比赛查询");
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(!frames.containsKey("比赛查询")||frames.get("比赛查询").isClosed())
			{
				MatchSearchIFrame iframe= new MatchSearchIFrame();
				frames.put("比赛查询", iframe);
				PLsystem.addIFame(frames.get("比赛查询"));
			}
		}
		
	}
	
	private static class LeagueSearchAction extends AbstractAction{

		LeagueSearchAction()
		{
			super("联赛查询",null);
			putValue(Action.LONG_DESCRIPTION,"查询联赛积分榜、射手榜");
			putValue(Action.SHORT_DESCRIPTION,"联赛查询");
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(!frames.containsKey("联赛查询")||frames.get("联赛查询").isClosed())
			{
				LeagueSearchIFrame iframe= new LeagueSearchIFrame();
				frames.put("联赛查询", iframe);
				PLsystem.addIFame(frames.get("联赛查询"));
			}
		}
		
	}
	
	/****insert actions****/
	
	private static class PlayerInsertAction extends AbstractAction{

		PlayerInsertAction()
		{
			super("增加球员",null);
			putValue(Action.LONG_DESCRIPTION,"增加球员信息");
			putValue(Action.SHORT_DESCRIPTION,"增加球员");
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (!frames.containsKey("增加球员")||frames.get("增加球员").isClosed()) {
				PlayerInsertIFrame iframe=new PlayerInsertIFrame();
				frames.put("增加球员", iframe);
				PLsystem.addIFame(frames.get("增加球员"));
			}
		}
		
	}
	
	private static class CoachInsertAction extends AbstractAction{

		CoachInsertAction()
		{
			super("增加教练",null);
			putValue(Action.LONG_DESCRIPTION,"增加教练信息");
			putValue(Action.SHORT_DESCRIPTION,"增加教练");
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (!frames.containsKey("增加教练")||frames.get("增加教练").isClosed()) {
				CoachInsertIFrame iframe=new CoachInsertIFrame();
				frames.put("增加教练", iframe);
				PLsystem.addIFame(frames.get("增加教练"));
			}
		}
		
	}
	
	private static class RefereeInsertAction extends AbstractAction{

		RefereeInsertAction()
		{
			super("增加裁判",null);
			putValue(Action.LONG_DESCRIPTION,"增加裁判信息");
			putValue(Action.SHORT_DESCRIPTION,"增加裁判");
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (!frames.containsKey("增加裁判")||frames.get("增加裁判").isClosed()) {
				RefereeInsertIFrame iframe=new RefereeInsertIFrame();
				frames.put("增加裁判", iframe);
				PLsystem.addIFame(frames.get("增加裁判"));
			}
		}
		
	}

	/****delete and update actions****/
	
	private static class PlayerModifyAndDeleteAction extends AbstractAction{
		PlayerModifyAndDeleteAction()
		{
			super("删改球员",null);
			putValue(Action.LONG_DESCRIPTION,"修改、删除球员信息");
			putValue(Action.SHORT_DESCRIPTION,"删改球员");
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (!frames.containsKey("删改球员")||frames.get("删改球员").isClosed()) {
				PlayerModifyAndDelIFrame iframe=new PlayerModifyAndDelIFrame();
				frames.put("删改球员", iframe);
				PLsystem.addIFame(frames.get("删改球员"));
			}
		}
		
	}
	private static class CoachModifyAndDeleteAction extends AbstractAction{

		CoachModifyAndDeleteAction()
		{
			super("删改教练",null);
			putValue(Action.LONG_DESCRIPTION,"修改、删除教练信息");
			putValue(Action.SHORT_DESCRIPTION,"删改教练");
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (!frames.containsKey("删改教练")||frames.get("删改教练").isClosed()) {
				CoachModifyAndDelIFrame iframe=new CoachModifyAndDelIFrame();
				frames.put("删改教练", iframe);
				PLsystem.addIFame(frames.get("删改教练"));
			}
		}
		
	}
	
	private static class MatchUpdateAction extends AbstractAction{

		
		
		public MatchUpdateAction() {
			super("比赛更新",null);
			putValue(Action.LONG_DESCRIPTION,"修改比赛基本信息，球员比赛数据");
			putValue(Action.SHORT_DESCRIPTION,"比赛更新");
			// TODO Auto-generated constructor stub
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			if (!frames.containsKey("比赛更新")||frames.get("比赛更新").isClosed()) {
				MatchResultUpdateIFrame iframe=new MatchResultUpdateIFrame();
				frames.put("比赛更新", iframe);
				PLsystem.addIFame(frames.get("比赛更新"));
			}
		}
		
	}
}
