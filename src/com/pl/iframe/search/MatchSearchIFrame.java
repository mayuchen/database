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

import com.pl.iframe.search.TeamSearchIFrame.CloseActionListener;
import com.pl.model.MatchInfo;

public class MatchSearchIFrame extends JInternalFrame{

	public MatchSearchIFrame()
	{
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
		
		tabbedPane.addTab("比赛查询", null,this.matchinfo(),"比赛信息查询");
	}
	private JPanel matchinfo()
	{
		JPanel panel_1=new JPanel();
		panel_1.setLayout(new BorderLayout());
			///输入
		final JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(null, "球队赛程", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1_1.setPreferredSize(new Dimension(0, 50));
		JLabel rtag=new JLabel("轮次");
		panel_1_1.add(rtag);
		final JComboBox round=new JComboBox();
		round.addItem("全部");
		for(int i=1;i<=38;i++){
			round.addItem(i);
		}
		panel_1_1.add(round);		
	/*	JLabel rtag2=new JLabel("场次");
		panel_1_1.add(rtag2);
		JComboBox match=new JComboBox();
		match.addItem("全部");
		for(int i=1;i<=10;i++){
			match.addItem(i);
		}
		panel_1_1.add(match);*/
		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "查询结果显示", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1.add(panel);

			//输出框
		final JScrollPane scrollpane_1 = new JScrollPane();
		scrollpane_1.setPreferredSize(new Dimension(400, 200));
		panel.add(scrollpane_1);
		final String [] matchsearch = { "轮次","主队",  "客队", "日期","时间","比分","裁判" };
		/////******
		Object [][] re={};
		JTable TJ=new JTable(re,matchsearch);
		scrollpane_1.setViewportView(TJ);
		final JButton button_s1 = new JButton();
		button_s1.setText("查询");
		button_s1.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Vector<MatchInfo> vt= MatchInfo.getMatchInfoByRound(
						round.getSelectedItem().toString());
				if(vt.size()>0)
				{
					Object[][] rt=getmatchdetail(vt);
					JTable Tx=new JTable(rt,matchsearch);
					Tx.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					scrollpane_1.setViewportView(Tx);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "无相关数据！");
				}
			}
			
			
		}
		);
		panel_1_1.add(button_s1);
		final JButton button_q1 = new JButton();
		button_q1.setText("退出");
		button_q1.addActionListener(new CloseActionListener());
		panel_1_1.add(button_q1);
		
		panel_1.add(panel_1_1, BorderLayout.NORTH);
		

		
		return panel_1;
	}
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
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
}
