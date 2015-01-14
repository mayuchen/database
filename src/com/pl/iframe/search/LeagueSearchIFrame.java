package com.pl.iframe.search;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
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

import com.pl.iframe.search.TeamSearchIFrame.CloseActionListener;
import com.pl.model.PlayerPr;
import com.pl.model.TeamInfo;
import com.pl.util.CreatecdIcon;

public class LeagueSearchIFrame extends JInternalFrame{

	public LeagueSearchIFrame() {
		super();
		setSize(800, 600);
		//T1=new JComboBox();
		//T1.addItem("1");

		setTitle("比赛查询");
		setBounds(100, 100, 500, 400);
		setIconifiable(true);
		setClosable(true);
		setVisible(true);

		final JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(tabbedPane);
		
		tabbedPane.addTab("积分榜", leaguetable());
		tabbedPane.addTab("射手榜", shootertable());
		// TODO Auto-generated constructor stub
	}
	
	private JPanel leaguetable()
	{
		JPanel panel_1=new JPanel();
		panel_1.setLayout(new BorderLayout());
			///输入
		final JLabel picLabel = new JLabel();
		ImageIcon loginIcon=CreatecdIcon.add("积分榜.jpg");
		picLabel.setIcon(loginIcon);
		picLabel.setOpaque(true);
		picLabel.setBackground(Color.WHITE);
		picLabel.setPreferredSize(new Dimension(480, 60));
		panel_1.add(picLabel, BorderLayout.NORTH);
		
		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "查询结果显示", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1.add(panel);
		final JScrollPane scrollpane_1 = new JScrollPane();
		scrollpane_1.setPreferredSize(new Dimension(400, 200));
		panel.add(scrollpane_1);
		
		/////******
		final String [] teamsearch = { "球队", "已赛场数","胜",  "平", "负","积分","进球数","被进球数","净胜球" };
		Object [][] re={};
		JTable TJ=new JTable(re,teamsearch);
		scrollpane_1.setViewportView(TJ);
		Vector<TeamInfo> vt= TeamInfo.leaguetable(
				);
		if(vt.size()>0)
		{
			Object[][] rt=getleague(vt);
			JTable Tx=new JTable(rt,teamsearch);
			Tx.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			scrollpane_1.setViewportView(Tx);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "无相关数据！");
		}
	
		


			//输出框

		
		return panel_1;
	}

	private JPanel shootertable(){
		JPanel panel_1=new JPanel();
		panel_1.setLayout(new BorderLayout());
			///输入
		final JLabel picLabel = new JLabel();
		ImageIcon loginIcon=CreatecdIcon.add("射手榜.jpg");
		picLabel.setIcon(loginIcon);
		picLabel.setOpaque(true);
		picLabel.setBackground(Color.WHITE);
		picLabel.setPreferredSize(new Dimension(480, 60));
		panel_1.add(picLabel, BorderLayout.NORTH);
		
		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "查询结果显示", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1.add(panel);
		final JScrollPane scrollpane_1 = new JScrollPane();
		scrollpane_1.setPreferredSize(new Dimension(300, 200));
		panel.add(scrollpane_1);
		
		/////******
		final String [] teamsearch = { "姓名", "所属球队","进球数",  "助攻数" };
		Object [][] re={};
		JTable TJ=new JTable(re,teamsearch);
		scrollpane_1.setViewportView(TJ);
		Vector<PlayerPr> vt= PlayerPr.playerProfrom("默认", "", "");
		if(vt.size()>0)
		{
			Object[][] rt=getshooter(vt);
			JTable Tx=new JTable(rt,teamsearch);
			Tx.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			scrollpane_1.setViewportView(Tx);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "无相关数据！");
		}
		
		
		


			//输出框

		
		return panel_1;
	}
	
	private Object[][] getshooter(Vector<PlayerPr> vt) {
		// TODO Auto-generated method stub
		final String [] teamsearch = { "姓名", "所属球队","进球数",  "助攻数" };
		Object[][]s = new Object[(vt.size()>50?50:vt.size())][4];
		for(int i=0;i<vt.size()&&i<50;i++)
		{
			PlayerPr tp=vt.elementAt(i);
			s[i][0]=tp.getName();
			s[i][1]=tp.getTeam();
			s[i][2]=tp.getGoals();
			s[i][3]=tp.getAssist();
		}
		return s;
	}

	private Object[][] getleague(Vector<TeamInfo> vt) {
		// TODO Auto-generated method stub
		final String [] teamsearch = { "球队", "已赛场数","胜",  "平", "负","积分","进球数","被进球数","净胜球" };
		Object[][] s =new Object[vt.size()][9];
		for(int i=0;i<vt.size();i++)
		{
			TeamInfo ti=vt.elementAt(i);
			s[i][0]=ti.getName();
			s[i][1]=ti.getRound();
			s[i][2]=ti.getW();
			s[i][3]=ti.getD();
			s[i][4]=ti.getL();
			s[i][5]=ti.getPoint();
			s[i][6]=ti.getG();
			s[i][7]=ti.getBG();
			s[i][8]=ti.getG()-ti.getBG();
		}
		return s;
	
	}
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
	

}
