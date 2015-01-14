package com.pl.iframe.update;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.pl.database.Database;
import com.pl.model.PlayerAb;

public class PlayerMatchUpdateIFrame extends JInternalFrame{

	private JButton buttonadd;

	public PlayerMatchUpdateIFrame(final int round,final int match,String home,
			String away,int hg,int ag) {
		super("",true);
		
		final BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		//setIconifiable(true);							// 设置窗体可最小化－－－必须
		//setClosable(true);								// 设置窗体可关闭－－－必须

		setTitle("第"+round+"轮 "+home+" VS "+away+" 球员表现");						// 设置窗体标题－－－必须
		setBounds(100, 100, 796, 380);					// 设置窗体位置和大小－－－必须
		setIconifiable(true);
		setClosable(true);
		this.setAutoscrolls(true);
		final Team hp=new Team(round, match, home, hg);
		
		final Team ap=new Team(round,match,away,ag);
		ap.setBorder(new TitledBorder(null, away, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(0,2));
		panel.add(hp);
		panel.add(ap);
		
		getContentPane().add(panel,BorderLayout.NORTH);
		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(2);
		flowLayout.setHgap(30);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.setLayout(flowLayout);
		JButton modify=new JButton("更改");
		modify.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(hp.gensql().size()==0||ap.gensql().size()==0)
				{
					JOptionPane.showMessageDialog(null, "与比赛信息矛盾，错误的球员数据！");
					return;
				}
				String sql="DELETE  from player_match where match_round="+round+
		 				" and match_id="+match+";";
				System.out.println(match+" "+round);
				if(Database.executeUpdate(sql)==-1){
				}
				Vector<String>sqls=hp.gensql();
				for(int i=0;i<sqls.size();i++)
				{
					System.out.println(sqls.elementAt(i));
					if(Database.executeUpdate(sqls.elementAt(i))==-1){
						JOptionPane.showMessageDialog(null, "错误的信息！");
						return;}
				}
				sqls=ap.gensql();
				for(int i=0;i<sqls.size();i++)
				{
					if(Database.executeUpdate(sqls.elementAt(i))==-1){
						JOptionPane.showMessageDialog(null, "错误的信息！");
						return;}
				}
				JOptionPane.showMessageDialog(null, "成功！");
				
				
			}
			
		});
		panel_1.add(modify);
		
		/**
		 * =
		 * ***/
		setVisible(true);
		// TODO Auto-generated constructor stub
	}
		
}
class Team extends JPanel{
	
	private JLabel[] squad;
	private JLabel[] name;
	private JComboBox[] att;
	private JTextField[] ax;
	private JTextField[] gx;
	private int[] id;
	private int g;
	private int r;
	private int m;
	private int plnum;
	
 	Team(int round,int match,String team,int goal)
	{
		
		String title=team+"球员表现";
		System.out.println(title);
 		this.setBorder(new TitledBorder(null, title, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		this.setAutoscrolls(true);
		Vector<PlayerAb> vt= PlayerAb.playerAbility(team, "", "球衣号");
		plnum=vt.size();
		this.setPreferredSize(new Dimension(380,plnum*40+40));
		
		squad =new JLabel[vt.size()];
		name  =new JLabel[vt.size()];
		att =new JComboBox[vt.size()];
		gx =new JTextField[vt.size()];
		ax= new JTextField[vt.size()];
		id= new int[vt.size()];
		GridLayout gi=new GridLayout(plnum+1,5);
		gi.setHgap(5);
		gi.setVgap(10);
		this.setLayout(gi);
		JLabel _1=new JLabel("号码");
		_1.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(_1);
		JLabel _2=new JLabel("姓名");
		_2.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(_2);
		JLabel _3 = new JLabel("出场情况");
		_3.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(_3);
		JLabel _4 = new JLabel("进球");
		_4.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(_4);
		JLabel _5=new JLabel("助攻");
		_5.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(_5);
		for(int i=0;i<vt.size();i++)
		{
			squad[i]=new JLabel(vt.elementAt(i).getSquad()+"");
			squad[i].setHorizontalAlignment(SwingConstants.CENTER);
			this.add(squad[i]);
			name[i]=new JLabel(vt.elementAt(i).getName());
			name[i].setHorizontalAlignment(SwingConstants.CENTER);
			this.add(name[i]);
			att[i]=new JComboBox();
			att[i].addItem("首发");
			att[i].addItem("替补");
			att[i].addItem("名单");
			att[i].addItem("缺席");
			this.add(att[i]);
			gx[i]=new JTextField();
			this.add(gx[i]);
			ax[i]=new JTextField();
			this.add(ax[i]);
			id[i]=Integer.parseInt(vt.elementAt(i).getID());
		}
		g=goal;
		r=round;
		m=match;
	}

 	
 	public Vector<String> gensql(){
 		int gsum=0;
 		int asum=0;
 		int first=0;
 		int rep=0;
 		int lis=0;
 		System.out.println(g+"!!!");
 		Vector<String> vt=new Vector<String>();
 		for(int i=0;i<plnum;i++){
 			gsum=gsum+(gx[i].getText().equals("")?0:Integer.parseInt(gx[i].getText()));
 			asum=asum+(ax[i].getText().equals("")?0:Integer.parseInt(ax[i].getText()));
 			if(att[i].getSelectedItem().equals("首发"))
 				first++;
 			if(att[i].getSelectedItem().equals("替补")){
 				rep++;
 				lis++;
 			}
 			if(att[i].getSelectedItem().equals("名单"))
 				lis++;
 			if((att[i].getSelectedItem().equals("名单")||att[i].getSelectedItem().equals("缺席"))
 					&&(!(gx[i].getText().equals("0")||gx[i].getText().equals(""))))
 				return vt;
 			if((att[i].getSelectedItem().equals("名单")||att[i].getSelectedItem().equals("缺席"))
 					&&(!(ax[i].getText().equals("0")||ax[i].getText().equals(""))))
 				return vt;
 		}

 		if(gsum!=g)return vt;
 		if(asum>g)return vt;
 		if(first>11)return vt;
 		if(rep>3)return vt;
 		if(lis>6)return vt;
 		
 		//String sql="";
 		for(int i=0;i<plnum;i++)
 			vt.add("INSERT INTO `plsystem`.`player_match` (`match_id`, `match_round`, `player_id`, `goals`, `assist`, `attend`) VALUES ("
 					+m+","+r+","+id[i]+","+(gx[i].getText().equals("")?0:gx[i].getText())+","
 					+ (ax[i].getText().equals("")?0:ax[i].getText())+",'"+att[i].getSelectedItem()+"')");
 		
 		//System.out.println(sql);
 		return vt;
 		
 	}
}
