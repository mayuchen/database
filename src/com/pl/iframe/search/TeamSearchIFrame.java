package com.pl.iframe.search;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import com.pl.iframe.search.PlayerSearchIFrame.CloseActionListener;
import com.pl.model.MatchInfo;
import com.pl.model.PlayerAb;
import com.pl.model.PlayerPr;
import com.pl.model.TeamInfo;

public class TeamSearchIFrame extends JInternalFrame{

	public TeamSearchIFrame()
	{
		super();
		
		setSize(800, 600);
		//T1=new JComboBox();
		//T1.addItem("1");

		setTitle("��Ӳ�ѯ");
		setBounds(100, 100, 500, 400);
		setIconifiable(true);
		setClosable(true);
		setVisible(true);

		final JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(tabbedPane);
		
		tabbedPane.addTab("�����Ϣ",null, this.teaminfo(),"��ѯ��ӵĻ�����Ϣ");
		tabbedPane.addTab("�������", null, this.teammatch(), "��ѯ��ӵ����̣������Ѿ������ı������");
		tabbedPane.addTab("��ӳ�Ա", null, this.teamplayer(), "��ѯ����е���Ա���ºš���ͬ�������Ϣ");
	}
	
	private JPanel teaminfo()
	{
		JPanel panel_1=new JPanel();
		panel_1.setLayout(new BorderLayout());
			///����
		final JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(null, "�����Ϣ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1_1.setPreferredSize(new Dimension(0, 50));
		JLabel rtag=new JLabel("���");
		panel_1_1.add(rtag);
		final JComboBox team=new JComboBox();
		Vector<TeamInfo>Ti=TeamInfo.teamlist();
		team.addItem("Ĭ��");
		for(int i=0;i<Ti.size();i++){
			team.addItem(Ti.elementAt(i).getName());
		}
		panel_1_1.add(team);
		
		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "��ѯ�����ʾ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1.add(panel);
		final JScrollPane scrollpane_1 = new JScrollPane();
		scrollpane_1.setPreferredSize(new Dimension(400, 200));
		panel.add(scrollpane_1);
		
		/////******
		final String [] teamsearch = { "���", "��д","��",  "������", "��������","�ͳ�����","����" };
		Object [][] re={};
		JTable TJ=new JTable(re,teamsearch);
		scrollpane_1.setViewportView(TJ);
		final JButton button_s1 = new JButton();
		
		button_s1.setText("��ѯ");
		button_s1.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Vector<TeamInfo> vt= TeamInfo.teamDetail(
						team.getSelectedItem().toString());
				if(vt.size()>0)
				{
					Object[][] rt=getteamdetail(vt);
					JTable Tx=new JTable(rt,teamsearch);
					Tx.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					scrollpane_1.setViewportView(Tx);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "��������ݣ�");
				}
			}}
		);
		panel_1_1.add(button_s1);
		final JButton button_q1 = new JButton();
		button_q1.setText("�˳�");
		button_q1.addActionListener(new CloseActionListener());
		panel_1_1.add(button_q1);
		
		panel_1.add(panel_1_1, BorderLayout.NORTH);
		


			//�����

		
		return panel_1;
	}

	private JPanel teammatch()
	{
		JPanel panel_1=new JPanel();
		panel_1.setLayout(new BorderLayout());
			///����
		final JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(null, "�������", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1_1.setPreferredSize(new Dimension(0, 50));
		JLabel rtag=new JLabel("���");
		panel_1_1.add(rtag);
		final JComboBox team=new JComboBox();
		Vector<TeamInfo>Ti=TeamInfo.teamlist();
		team.addItem("Ĭ��");
		for(int i=0;i<Ti.size();i++){
			team.addItem(Ti.elementAt(i).getName());
		}
		panel_1_1.add(team);		

		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "��ѯ�����ʾ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1.add(panel);

			//�����
		final JScrollPane scrollpane_1 = new JScrollPane();
		scrollpane_1.setPreferredSize(new Dimension(400, 200));
		panel.add(scrollpane_1);
		final String [] matchsearch = { "�ִ�","����",  "�Ͷ�", "����","ʱ��","�ȷ�","����" };
		/////******
		Object [][] re={};
		JTable TJ=new JTable(re,matchsearch);
		scrollpane_1.setViewportView(TJ);
		final JButton button_s1 = new JButton();
		button_s1.setText("��ѯ");
		button_s1.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Vector<MatchInfo> vt= MatchInfo.getMatchInfoByTeam(
						team.getSelectedItem().toString());
				if(vt.size()>0)
				{
					Object[][] rt=getmatchdetail(vt);
					JTable Tx=new JTable(rt,matchsearch);
					Tx.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					scrollpane_1.setViewportView(Tx);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "��������ݣ�");
				}
			}

			}
		);
		panel_1_1.add(button_s1);
		final JButton button_q1 = new JButton();
		button_q1.setText("�˳�");
		button_q1.addActionListener(new CloseActionListener());
		panel_1_1.add(button_q1);
		
		panel_1.add(panel_1_1, BorderLayout.NORTH);
		

		
		return panel_1;
	}

	private JPanel teamplayer()
	{

		JPanel panel_1=new JPanel();
		panel_1.setLayout(new BorderLayout());
			///����
		final JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(null, "�����Ϣ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1_1.setPreferredSize(new Dimension(0, 50));
		JLabel rtag=new JLabel("���");
		panel_1_1.add(rtag);
		final JComboBox team=new JComboBox();
		Vector<TeamInfo>Ti=TeamInfo.teamlist();
		team.addItem("Ĭ��");
		for(int i=0;i<Ti.size();i++){
			team.addItem(Ti.elementAt(i).getName());
		}
		panel_1_1.add(team);
		
		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "��ѯ�����ʾ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1.add(panel);

		
		
		/////******
		final String [] playersearch = { "��ԱID", "����","���",  "����", "λ��","����","����","����","���","��ѵ" };
		Object[][]re={};
		final JTable TJ=new JTable(re,playersearch);
		final JScrollPane scrollpane_1 = new JScrollPane();
		scrollpane_1.setBounds(50,30, 300, 600);
		scrollpane_1.setViewportView(TJ);
		scrollpane_1.setPreferredSize(new Dimension(400, 200));
		panel.add(scrollpane_1);
		final JButton button_s1 = new JButton();
		
		button_s1.setText("��ѯ");
		button_s1.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Vector<PlayerAb> vt=PlayerAb.playerAbility(
						team.getSelectedItem().toString(), 
						"",
						"���º�");
				if(vt.size()>0)
				{
					Object[][] rt=getselect(vt);
					JTable Tx=new JTable(rt,playersearch);
					Tx.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					scrollpane_1.setViewportView(Tx);
				}
				else
					JOptionPane.showMessageDialog(null, "�޽����");
				///*********
			}}
		);
		panel_1_1.add(button_s1);
		final JButton button_q1 = new JButton();
		button_q1.setText("�˳�");
		button_q1.addActionListener(new CloseActionListener());
		panel_1_1.add(button_q1);
		
		panel_1.add(panel_1_1, BorderLayout.NORTH);
		


			//�����

		
		return panel_1;
	}
	class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
	private Object[][]getteamdetail(Vector<TeamInfo> vt)
	{
		Object[][] s =new Object[vt.size()][7];
		for(int i=0;i<vt.size();i++)
		{
			TeamInfo ti=vt.elementAt(i);
			s[i][0]=ti.getName();
			s[i][1]=ti.getAbb();
			s[i][2]=ti.getStadium();
			s[i][3]=ti.getCoach();
			s[i][4]=ti.getHcolor();
			s[i][5]=ti.getAcolor();
			s[i][6]=ti.getPoint();
		}
		return s;
	}
	private Object[][] getmatchdetail(Vector<MatchInfo> vt) {
		// TODO Auto-generated method stub
		Object s[][]=new Object[vt.size()][7];
		for(int i=0;i<vt.size();i++)
		{
			MatchInfo mi=vt.elementAt(i);
			s[i][0]=mi.getRound();
			s[i][1]=mi.getHome();
			s[i][2]=mi.getAway();
			s[i][3]=mi.getDate();
			s[i][4]=mi.getTime();
			s[i][5]=mi.getResult();
			s[i][6]=mi.getReferee();
			
		}
		return s;
	}
	private Object[][] getselect(Vector vt) {
		Object[][] s = new Object[vt.size()][10];
		for (int i = 0; i < vt.size(); i++) {
			PlayerAb pla = (PlayerAb)vt.elementAt(i);
			s[i][0] = pla.getID();
			
			s[i][1] = pla.getName();
			s[i][2] = pla.getHeight();
			s[i][3] = pla.getWeight();
			s[i][4] = pla.getPosition();
			s[i][5] = pla.getFoot();
			s[i][6] = pla.getBirthday().toString();
			s[i][7] = pla.getNation();
			s[i][8] = pla.getValue();
			s[i][9] = pla.getYouth_team();
		}
		return s;

	}
}
