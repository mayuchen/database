package com.pl.iframe.update;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.pl.database.Database;
import com.pl.model.CoachInfo;
import com.pl.model.ContractInfo;
import com.pl.model.PlayerAb;
import com.pl.model.TeamInfo;

public class CoachModifyAndDelIFrame extends JInternalFrame{

	private JTable table;
	private JLabel CAID;
	private JTextField coachName;
	private JFormattedTextField birthday;
	private JTextField nation;
	private JComboBox team;

	public CoachModifyAndDelIFrame() {
		super();
		// TODO Auto-generated constructor stub
		final BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		setIconifiable(true);
		setClosable(true);
		setTitle("教练信息修改");
		setBounds(100, 100, 400, 300);
		setVisible(true);
		
		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(2);
		flowLayout.setHgap(30);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.setLayout(flowLayout);
		
		final String[] columnNames = 
				new String[]{"教练ID", "姓名","生日","国籍","所属球队"};
		final JScrollPane scrollPane = new JScrollPane();
		
		final JButton button = new JButton();
		//button.addActionListener(new addBookActionListener());
		button.setText("修改");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(coachName.getText().equals("")){
					JOptionPane.showMessageDialog(null, "姓名不能为空");
				return;	
				}
				if(team.getSelectedItem().equals("默认"))
				{
					JOptionPane.showMessageDialog(null,"球队不能没有主教练！请指派另一名教练上任！");
					return;
				}
				String sql="UPDATE `coach` SET `coach_id`="+CAID.getText()
						+ ", `coach_name`='"+coachName.getText()+"'";
			
						if(!birthday.getText().equals(""))
							sql=sql+", `coach_birthday`='"+birthday.getText()+"'";

						if(!nation.getText().equals(""))
							sql=sql	+ ", `coach_nation`='"+nation.getText()+"'";
							
						sql=sql	+ "  WHERE `coach_id`="+CAID.getText();
						System.out.println(sql);
				String sql2="UPDATE `team` SET team_coach_id="+CAID.getText()
						+" where team_name='"+team.getSelectedItem()+"'";
				System.out.println(sql2);
				Database.executeUpdate(sql);
				Database.executeUpdate(sql2);
					JOptionPane.showMessageDialog(null, "修改信息成功！");
				
				Vector<CoachInfo> vts=CoachInfo.getCoachInfo();
				Object[][] rts;
				rts=getselect(vts);
				table = new JTable(rts,columnNames);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.addMouseListener(new TableListener());
		
				scrollPane.setViewportView(table);
				/*				
			
				if(!sql2.equals(""))
					Database.executeUpdate(sql2);
				if(Database.executeUpdate(sql)==0)
					
				Vector<PlayerAb> vts=PlayerAb.playerAbility(
						"默认", 
						"",
						"球员ID");
				Object[][] rts;
						rts=getselect(vts);
				table = new JTable(rts,columnNames);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.addMouseListener(new TableListener());
				
				scrollPane.setViewportView(table);*/

			}}
		);
		panel_1.add(button);
		
		
		final JButton button_d = new JButton();
		//button.addActionListener(new addBookActionListener());
		button_d.setText("删除");
		button_d.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sql="DELETE from players WHERE `coach_id`="+CAID.getText();
						System.out.println(sql);
				if(Database.executeUpdate(sql)==0)
					JOptionPane.showMessageDialog(null, "修改信息成功！");
				Vector<CoachInfo> vts=CoachInfo.getCoachInfo();
				Object[][] rts;
						rts=getselect(vts);
				table = new JTable(rts,columnNames);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.addMouseListener(new TableListener());
				
				scrollPane.setViewportView(table);

			}}
		);
		panel_1.add(button_d);
		
		
		
		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		button_1.setText("关闭");
		panel_1.add(button_1);
		
		final JPanel panel_2 = new JPanel();
		final BorderLayout borderLayout_1 = new BorderLayout();
		borderLayout_1.setVgap(5);
		panel_2.setLayout(borderLayout_1);
		panel_2.setBorder(new EmptyBorder(5, 10, 5, 10));
		getContentPane().add(panel_2);

		
		panel_2.add(scrollPane);
		/*****/
		Object[][] rt;
		Vector<CoachInfo> vt=CoachInfo.getCoachInfo();
		if(vt.size()>0)
				rt=getselect(vt);

		else
			{JOptionPane.showMessageDialog(null, "无结果！");
			rt=null;
			}
			/*****/
		
		table = new JTable(rt,columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.addMouseListener(new TableListener());
		
		scrollPane.setViewportView(table);

		final JPanel panel = new JPanel();
		panel_2.add(panel, BorderLayout.SOUTH);
		final GridLayout gridLayout = new GridLayout(0, 4);////
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		panel.setLayout(gridLayout);

		final JLabel label_2 = new JLabel();
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setText("教练ID：");
		panel.add(label_2);

		CAID = new JLabel();
		//PLID.setDocument(new MyDocument(13)); 
		panel.add(CAID);
		final JLabel label_1 = new JLabel();
		label_1.setText("姓名：");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1);

		coachName = new JTextField();
		panel.add(coachName);



		


		


		
		final JLabel label_1_1 = new JLabel();
		label_1_1.setText("出生日期：");
		label_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1_1);

		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
		birthday= new JFormattedTextField(myfmt.getDateInstance());
		birthday.setValue(new java.util.Date());
		panel.add(birthday);
		final JLabel label_3_1 = new JLabel();
		label_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_3_1.setText("国籍：");
		panel.add(label_3_1);
		  nation=   new   JTextField();
		//  price.setDocument(new MyDocument(5));
		 // price.addKeyListener(new NumberListener());
		panel.add(nation);
		
		JLabel teamtag=new JLabel("球队：");
		teamtag.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(teamtag);
		team=new JComboBox();
		Vector<TeamInfo>Ti=TeamInfo.teamlist();
		team.addItem("默认");
		for(int i=0;i<Ti.size();i++){
			team.addItem(Ti.elementAt(i).getName());
		}
		panel.add(team);
		
		
	
	}

	private Object[][] getselect(Vector<CoachInfo> vt) {
		// TODO Auto-generated method stub
		
		Object[][] s = new Object[vt.size()][5];
		for (int i = 0; i < vt.size(); i++) {
			CoachInfo ci = (CoachInfo)vt.elementAt(i);
			s[i][0] = ""+ci.getId();
			
			s[i][1] = ci.getName();
			s[i][2] = ci.getBirthday();
			s[i][3] = ci.getNation();
			TeamInfo ti=TeamInfo.byCoachID(ci.getId());
			s[i][4] = ti.getName();
			
		
			
		}
		return s;
		
	
	}

	
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
					int selRow = table.getSelectedRow();
			String CAIDs = table.getValueAt(selRow, 0).toString().trim();
			String name = table.getValueAt(selRow, 1).toString().trim();
			String birthdays=(table.getValueAt(selRow, 2)!=null?table.getValueAt(selRow, 2).toString().trim():"");
			String nations=(table.getValueAt(selRow, 3)!=null?table.getValueAt(selRow, 3).toString().trim():"");
			String teams=table.getValueAt(selRow, 4).toString().trim();
			
			CAID.setText(CAIDs);
			coachName.setText(name);
		
			birthday.setText(birthdays);
			
			nation.setText(nations);
			team.setSelectedItem(teams);
			
			//bookTypeModel.setSelectedItem(item);

		}
	}
	
	
	
}
