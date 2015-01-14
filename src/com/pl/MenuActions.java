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
			putValue(Action.NAME,"���Ŀ���");
			putValue(Action.LONG_DESCRIPTION, "�޸ĵ�ǰ�û�����");
			putValue(Action.SHORT_DESCRIPTION, "��������");//�ڡ����Ŀ����ʾ����ʾ������
			//putValue(Action.SMALL_ICON,CreatecdIcon.add("bookAddtb.jpg"));
			//��ͼ��洢������������
			//setEnabled(false);//ʹ��������
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	
	}

	private static class ReLoginAction extends AbstractAction{
		
		ReLoginAction(){
			super("���µ�¼",null);
			putValue(Action.LONG_DESCRIPTION,"���µ�¼");
			putValue(Action.SHORT_DESCRIPTION,"�ص�½");
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
			super("��Ա��ѯ",null);
			putValue(Action.LONG_DESCRIPTION,"��ѯ��Ա��Ϣ");
			putValue(Action.SHORT_DESCRIPTION,"��Ա��ѯ");
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (!frames.containsKey("��Ա��ѯ")||frames.get("��Ա��ѯ").isClosed()) {
				PlayerSearchIFrame iframe=new PlayerSearchIFrame();
				frames.put("��Ա��ѯ", iframe);
				PLsystem.addIFame(frames.get("��Ա��ѯ"));
			}
			
		}
		
	}
	
	private static class TeamSearchAction extends AbstractAction{
		TeamSearchAction()
		{
			super("��Ӳ�ѯ",null);
			putValue(Action.LONG_DESCRIPTION,"��ѯ�����Ϣ");
			putValue(Action.SHORT_DESCRIPTION,"��Ӳ�ѯ");
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (!frames.containsKey("��Ӳ�ѯ")||frames.get("��Ӳ�ѯ").isClosed()) {
				TeamSearchIFrame iframe=new TeamSearchIFrame();
				frames.put("��Ӳ�ѯ", iframe);
				PLsystem.addIFame(frames.get("��Ӳ�ѯ"));
			}
		}
		
	}
	
	private static class MatchSearchAction extends AbstractAction{

		MatchSearchAction()
		{
			super("������ѯ",null);
			putValue(Action.LONG_DESCRIPTION,"��ѯ������Ϣ");
			putValue(Action.SHORT_DESCRIPTION,"������ѯ");
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(!frames.containsKey("������ѯ")||frames.get("������ѯ").isClosed())
			{
				MatchSearchIFrame iframe= new MatchSearchIFrame();
				frames.put("������ѯ", iframe);
				PLsystem.addIFame(frames.get("������ѯ"));
			}
		}
		
	}
	
	private static class LeagueSearchAction extends AbstractAction{

		LeagueSearchAction()
		{
			super("������ѯ",null);
			putValue(Action.LONG_DESCRIPTION,"��ѯ�������ְ����ְ�");
			putValue(Action.SHORT_DESCRIPTION,"������ѯ");
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(!frames.containsKey("������ѯ")||frames.get("������ѯ").isClosed())
			{
				LeagueSearchIFrame iframe= new LeagueSearchIFrame();
				frames.put("������ѯ", iframe);
				PLsystem.addIFame(frames.get("������ѯ"));
			}
		}
		
	}
	
	/****insert actions****/
	
	private static class PlayerInsertAction extends AbstractAction{

		PlayerInsertAction()
		{
			super("������Ա",null);
			putValue(Action.LONG_DESCRIPTION,"������Ա��Ϣ");
			putValue(Action.SHORT_DESCRIPTION,"������Ա");
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (!frames.containsKey("������Ա")||frames.get("������Ա").isClosed()) {
				PlayerInsertIFrame iframe=new PlayerInsertIFrame();
				frames.put("������Ա", iframe);
				PLsystem.addIFame(frames.get("������Ա"));
			}
		}
		
	}
	
	private static class CoachInsertAction extends AbstractAction{

		CoachInsertAction()
		{
			super("���ӽ���",null);
			putValue(Action.LONG_DESCRIPTION,"���ӽ�����Ϣ");
			putValue(Action.SHORT_DESCRIPTION,"���ӽ���");
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (!frames.containsKey("���ӽ���")||frames.get("���ӽ���").isClosed()) {
				CoachInsertIFrame iframe=new CoachInsertIFrame();
				frames.put("���ӽ���", iframe);
				PLsystem.addIFame(frames.get("���ӽ���"));
			}
		}
		
	}
	
	private static class RefereeInsertAction extends AbstractAction{

		RefereeInsertAction()
		{
			super("���Ӳ���",null);
			putValue(Action.LONG_DESCRIPTION,"���Ӳ�����Ϣ");
			putValue(Action.SHORT_DESCRIPTION,"���Ӳ���");
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (!frames.containsKey("���Ӳ���")||frames.get("���Ӳ���").isClosed()) {
				RefereeInsertIFrame iframe=new RefereeInsertIFrame();
				frames.put("���Ӳ���", iframe);
				PLsystem.addIFame(frames.get("���Ӳ���"));
			}
		}
		
	}

	/****delete and update actions****/
	
	private static class PlayerModifyAndDeleteAction extends AbstractAction{
		PlayerModifyAndDeleteAction()
		{
			super("ɾ����Ա",null);
			putValue(Action.LONG_DESCRIPTION,"�޸ġ�ɾ����Ա��Ϣ");
			putValue(Action.SHORT_DESCRIPTION,"ɾ����Ա");
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (!frames.containsKey("ɾ����Ա")||frames.get("ɾ����Ա").isClosed()) {
				PlayerModifyAndDelIFrame iframe=new PlayerModifyAndDelIFrame();
				frames.put("ɾ����Ա", iframe);
				PLsystem.addIFame(frames.get("ɾ����Ա"));
			}
		}
		
	}
	private static class CoachModifyAndDeleteAction extends AbstractAction{

		CoachModifyAndDeleteAction()
		{
			super("ɾ�Ľ���",null);
			putValue(Action.LONG_DESCRIPTION,"�޸ġ�ɾ��������Ϣ");
			putValue(Action.SHORT_DESCRIPTION,"ɾ�Ľ���");
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (!frames.containsKey("ɾ�Ľ���")||frames.get("ɾ�Ľ���").isClosed()) {
				CoachModifyAndDelIFrame iframe=new CoachModifyAndDelIFrame();
				frames.put("ɾ�Ľ���", iframe);
				PLsystem.addIFame(frames.get("ɾ�Ľ���"));
			}
		}
		
	}
	
	private static class MatchUpdateAction extends AbstractAction{

		
		
		public MatchUpdateAction() {
			super("��������",null);
			putValue(Action.LONG_DESCRIPTION,"�޸ı���������Ϣ����Ա��������");
			putValue(Action.SHORT_DESCRIPTION,"��������");
			// TODO Auto-generated constructor stub
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			if (!frames.containsKey("��������")||frames.get("��������").isClosed()) {
				MatchResultUpdateIFrame iframe=new MatchResultUpdateIFrame();
				frames.put("��������", iframe);
				PLsystem.addIFame(frames.get("��������"));
			}
		}
		
	}
}
