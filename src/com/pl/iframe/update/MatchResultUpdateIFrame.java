package com.pl.iframe.update;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.pl.PLsystem;
import com.pl.database.Database;
import com.pl.model.MatchInfo;
import com.pl.model.RefereeInfo;
import com.pl.model.TeamInfo;

public class MatchResultUpdateIFrame extends JInternalFrame{

	public MatchResultUpdateIFrame() {
		super();
		// TODO Auto-generated constructor stub
		setSize(800, 600);
		//T1=new JComboBox();
		//T1.addItem("1");

		setTitle("比赛信息更新");
		setBounds(100, 100, 600, 400);
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		setVisible(true);

		final JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(tabbedPane);
		
		tabbedPane.addTab("比赛信息更新", matchUpdate());
		
	}
/***************************************************/
	private JPanel matchUpdate(){

		JPanel panel=new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new TitledBorder(null, "基本信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel.setPreferredSize(new Dimension(0, 50));
		
		final JPanel panel_1 = new JPanel();
		GridLayout g=new GridLayout(0,5);
		g.setHgap(5);
		g.setVgap(10);
		panel_1.setLayout(g);
		JLabel roundtag=new JLabel("轮次");
		roundtag.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(roundtag);
		final JComboBox round= new JComboBox();
		for(int i=1;i<39;i++)
			round.addItem(i);
		panel_1.add(round);
		
		JLabel matchtag=new JLabel("轮次");
		matchtag.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(matchtag);
		final JComboBox match= new JComboBox();
		for(int i=1;i<11;i++)
			match.addItem(i);
		panel_1.add(match);
		
		JButton search=new JButton("更改该场次");
	
		
		
		panel.add(panel_1,BorderLayout.NORTH);
		final matchinfo panel_2=new matchinfo(1,1);
		search.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				panel_2.changematch(round.getSelectedIndex()+1, match.getSelectedIndex()+1);
			}
			
		});
		panel_1.add(search);
		panel.add(panel_2);
		return panel;
	}
	
	
	/******************
	 * 
	 * 下一个工作
	 * 
	 */
	
	/********************************************/
static class matchinfo extends JPanel {
	
	
	private JLabel r;
	private JLabel m;
	private JComboBox home;
	private JComboBox away;
	private JFormattedTextField date;
	private JFormattedTextField time;
	private JComboBox referee;
	private JTextField result;

	matchinfo(int round,int match){	
	super();
	setLayout(new BorderLayout());
	setBorder(new TitledBorder(null, "基本信息修改", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
	setPreferredSize(new Dimension(0, 50));
	
	MatchInfo mi=MatchInfo.getMatchInfoByID(round, match);
	boolean insert=(mi.getRound()==0?true:false);
	//System.out.println(mi.getHome());
	
	JPanel panel_1=new JPanel();
	GridLayout g=new GridLayout(0,6);
	g.setHgap(5);
	g.setVgap(10);
	panel_1.setLayout(g);
	
		JLabel rtag=new JLabel("轮次：");
		rtag.setHorizontalAlignment(SwingConstants.CENTER);
		r=new JLabel(""+round);
		r.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(rtag);
		panel_1.add(r);
	
		JLabel mtag=new JLabel("场次：");
		mtag.setHorizontalAlignment(SwingConstants.CENTER);
		m=new JLabel(""+match);
		m.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(mtag);
		panel_1.add(m);
		
		JLabel hom=new JLabel("主队：");
		hom.setHorizontalAlignment(SwingConstants.CENTER);
		home=new JComboBox();
		Vector<TeamInfo>Ti=TeamInfo.teamlist();
		home.addItem("默认");
		for(int i=0;i<Ti.size();i++){
			home.addItem(Ti.elementAt(i).getName());
		}
		if(!insert)
			home.setSelectedItem(mi.getHome());
		else
			home.setSelectedIndex(0);
		panel_1.add(hom);
		panel_1.add(home);
		
		JLabel awa=new JLabel("主队：");
		awa.setHorizontalAlignment(SwingConstants.CENTER);
		away=new JComboBox();
		away.addItem("默认");
		for(int i=0;i<Ti.size();i++){
			away.addItem(Ti.elementAt(i).getName());
		}
		if(!insert)
			away.setSelectedItem(mi.getAway());
		else
			away.setSelectedIndex(0);
		panel_1.add(awa);
		panel_1.add(away);
		
		JLabel label_1_1 = new JLabel();
		label_1_1.setText("比赛日期：");
		label_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_1_1);
		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
		date= new JFormattedTextField(myfmt.getDateInstance());
		System.out.println(insert);
		if(insert)
			date.setValue(new java.util.Date());
		else
			date.setText(mi.getDate());
		panel_1.add(date);
		
		
		JLabel ttag =new JLabel("开球时间：");
		ttag.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(ttag);
		SimpleDateFormat tifmt=new SimpleDateFormat("HH:mm:ss");
		time= new JFormattedTextField(tifmt.getTimeInstance());
		if(insert)
			time.setValue(new java.util.Date());
		else
			time.setText(mi.getTime());
		panel_1.add(time);
		
		JLabel retag=new JLabel("裁判ID：");
		retag.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(retag);
		referee=new JComboBox();
		referee.addItem("未指派");
		Vector<RefereeInfo> Ri=RefereeInfo.RefereeList();  
		for(int i=0;i<Ri.size();i++){
			referee.addItem(Ri.elementAt(i).getId());
		}
		if(!insert)
			referee.setSelectedItem(mi.getRefereeid());
		else
			referee.setSelectedIndex(0);
		panel_1.add(referee);
		System.out.println(mi.getRefereeid());
		
		JLabel rttag=new JLabel("比赛结果：");
		rttag.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(rttag);
		result = new JTextField();
		if(!insert)
			result.setText(mi.getResult());
		else
			result.setText("");
		panel_1.add(result);

	add(panel_1,BorderLayout.NORTH);
	
			/**
		 * 
		 * ****/
	JPanel buttonpanel=new JPanel();
		GridLayout g1=new GridLayout(0,5);
		g1.setVgap(20);
		g1.setHgap(20);
		buttonpanel.setLayout(g1);
		JLabel tab1=new JLabel(" ");
		JLabel tab2=new JLabel(" ");
		buttonpanel.add(tab1);
		buttonpanel.add(tab2);
		JButton modify= new JButton("修改");
		modify.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String sql="select * from team_match where"
						+ " match_round="+r.getText()
						+ " and not match_id="+m.getText()
						+ " and(home_team='"+home.getSelectedItem()
						+ "' or home_team='"+away.getSelectedItem()
						+ "' or away_team='"+home.getSelectedItem()
						+ "' or away_team='"+away.getSelectedItem()
						+ "')"
						;
				
				String sql2="select * from team_match where "
						+" home_team='"+home.getSelectedItem()+"'"
						+" and away_team='"+away.getSelectedItem()+"'"
						+" and not match_round="+r.getText();
				try {
					if(Database.executeQuery(sql).next())
					{
						JOptionPane.showMessageDialog(null, "一支球队一轮不能有多场比赛！");
						return;
					}
					if(Database.executeQuery(sql2).next())
					{
						JOptionPane.showMessageDialog(null, "重复的对阵情况");
						return;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				boolean done=(!result.getText().equals("未结束"))&&
						(!home.getSelectedItem().equals("默认"))&&
						(!away.getSelectedItem().equals("默认"));
				String hg="0",ag="0";
				if(done)
				{
					String[] re=result.getText().split(":");
					hg=re[0];
					ag=re[1];
				}
				try {
					
					if(Database.executeQuery("select * from team_match where match_round="+r.getText()+
							" and match_id="+m.getText()).next())
						
					sql= "UPDATE team_match set home_team="+
					(home.getSelectedItem().equals("默认")?"NULL":"'"+home.getSelectedItem()+"'")
					+",away_team="+
					(away.getSelectedItem().equals("默认")?"NULL":"'"+away.getSelectedItem()+"'")
					+",match_has_done="+(done?"'是'":"'否'")
					+",home_goal_number="+(done?hg:'0')
					+",away_goal_number="+(done?ag:'0')
					+" where match_round="+r.getText()+" and match_id="+m.getText()
					;
					else
						sql="INSERT INTO team_match (match_round,match_id,"
								+ "home_team,away_team,match_has_done,"
								+ "home_goal_number,away_goal_number)VALUES("
								+ r.getText()+","+m.getText()+","
								+(home.getSelectedItem().equals("默认")?"NULL":"'"+home.getSelectedItem()+"'")+","
								+(away.getSelectedItem().equals("默认")?"NULL":"'"+away.getSelectedItem()+"'")+","
								+""+(done?"'是'":"'否'")
								+","+(done?hg:'0')
								+","+(done?ag:'0')+")"
								;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(sql);
				
				
				String sql21;
				try {
					if(Database.executeQuery("select * from `match` where match_round="+r.getText()
							+" and match_id="+m.getText()).next())
					sql21="UPDATE `match` set match_date="+
					(date.getText().equals("")?"NULL":"'"+date.getText()+"'")+
					",match_time="+
					(time.getText().equals("")?"NULL":"'"+time.getText()+"'")
					+",match_referee_id="
					+(referee.getSelectedItem().equals("未指派")?"NULL":""+referee.getSelectedItem()+"")
					+" where match_round="+r.getText()+" and match_id="+m.getText()
					;
					else
						sql21="INSERT INTO `match`(match_round,match_id,match_date,match_time,"
								+ "match_referee_id)VALUES("+r.getText()+","+m.getText()+","
								+(date.getText().equals("")?"NULL":"'"+date.getText()+"'")+","
								+(time.getText().equals("")?"NULL":"'"+time.getText()+"'")+","
								+(referee.getSelectedItem().equals("未指派")?"NULL":""+referee.getSelectedItem()+"")
								+")";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					sql21="";
				}
				System.out.println(sql21);
				if(Database.executeUpdate(sql)!=-1&&Database.executeUpdate(sql21)!=-1)
					{
						JOptionPane.showMessageDialog(null, "修改比赛信息成功");;
						if(done)
						PLsystem.addIFame(new PlayerMatchUpdateIFrame(
								Integer.parseInt(r.getText()),Integer.parseInt(m.getText()),
								(String)home.getSelectedItem(),(String)away.getSelectedItem(),
								Integer.parseInt(hg),Integer.parseInt(ag))
								);
					}
				}
			
			
		});
		JButton delete= new JButton("删除");
		JButton quit  = new JButton("退出");
		buttonpanel.add(modify);
	add(buttonpanel,BorderLayout.SOUTH);
	}

	public void changematch(int round,int match)
	{
		r.setText(round+"");
		m.setText(match+"");
		MatchInfo mi=MatchInfo.getMatchInfoByID(round, match);
		boolean insert=(mi.getRound()==0?true:false);
		if(!insert)
			home.setSelectedItem(mi.getHome());
		else
			home.setSelectedIndex(0);
		if(!insert)
			away.setSelectedItem(mi.getAway());
		else
			away.setSelectedIndex(0);
		if(insert)
			date.setValue(new java.util.Date());
		else
			date.setText(mi.getDate());
		if(insert)
			time.setValue(new java.util.Date());
		else
			time.setText(mi.getTime());
		if(!insert)
			referee.setSelectedItem(mi.getRefereeid());
		else
			referee.setSelectedIndex(0);
		if(!insert)
			result.setText(mi.getResult());
		else
			result.setText("");
	}
}
}