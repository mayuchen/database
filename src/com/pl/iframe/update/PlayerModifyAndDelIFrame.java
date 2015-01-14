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
import java.util.List;
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
import com.pl.model.ContractInfo;
import com.pl.model.PlayerAb;
import com.pl.model.TeamInfo;
import com.pl.util.MyDocument;



public class PlayerModifyAndDelIFrame extends JInternalFrame{
	JTable table;
	private JLabel PLID;
	DefaultComboBoxModel bookTypeModel;
	private JTextField playerName;
	private JTextField value;
	private JComboBox position;
	private JTextField teen;
	private JFormattedTextField Brithday;
	private JTextField nation;
	private JTextField height;
	private JTextField weight;
	private JComboBox foot;
	private JComboBox team;
	private JTextField squad;
	private JFormattedTextField contract_begin;
	private JFormattedTextField contract_end;
	private JTextField salary;
	public PlayerModifyAndDelIFrame()
	{
		super();
		final BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		setIconifiable(true);
		setClosable(true);
		setTitle("球员信息修改");
		setBounds(100, 100, 593, 406);
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
				new String[]{"球员ID", "姓名","身高",  "体重", "位置","主脚","生日","国籍","身价","青训","所属球队","球衣号","合同开始","合同结束","周薪"};
		final JScrollPane scrollPane = new JScrollPane();
		
		final JButton button = new JButton();
		//button.addActionListener(new addBookActionListener());
		button.setText("修改");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(playerName.getText().equals("")){
					JOptionPane.showMessageDialog(null, "姓名不能为空");
				return;	
				}
				String sql="UPDATE `players` SET `player_id`="+PLID.getText()
						+ ", `player_name`='"+playerName.getText()+"'";
						if(!weight.getText().equals(""))
							sql=sql+ ", `player_weight`="+weight.getText();
						if(!height.getText().equals(""))
							sql=sql+ ", `player_height`="+height.getText();
						if(!Brithday.getText().equals(""))
							sql=sql+", `player_birthday`='"+Brithday.getText()+"'";
						if(!value.getText().equals(""))
							sql=sql	+ ", `player_value`='"+value.getText()+"'";
						if(!nation.getText().equals(""))
							sql=sql	+ ", `player_nation`='"+nation.getText()+"'";
						sql=sql	+ ", `player_foot`='"+foot.getSelectedItem().toString()+"'";
						sql=sql + ", `player_position`='"+position.getSelectedItem().toString()+"'";
						if(!teen.getText().equals(""))
							sql=sql	+ ", `player_youth_team`='"+teen.getText()+"'";
						sql=sql	+ "  WHERE `player_id`="+PLID.getText();
						System.out.println(sql);
				
						
				String sql2="";
				if(!team.getSelectedItem().equals("默认"))
				try {
					if(Database.executeQuery("select * from player_team where player_id="+PLID.getText()).next()){
						sql2="UPDATE player_team SET player_id="+PLID.getText()+",team_name='"
						+team.getSelectedItem()+"',contract_begin='"+contract_begin.getText()+"'"
						+ ",contract_end='"+contract_end.getText()+"'";
						if(!salary.getText().equals(""))
							sql2=sql2+",salary='"+salary.getText()+"'";
						if(!squad.getText().equals(""))
							sql2=sql2+",`squad`="+squad.getText();
						sql2=sql2+" where player_id="+PLID.getText();
					}
					else
						{sql2="INSERT INTO player_team (`player_id`, `team_name`,"
								+ "contract_begin,contract_end";
						if(salary.getText()!="")
							sql2=sql2+ ", `salary`";
						if(squad.getText()!="")
							sql2=sql2+",`squad`";
						sql2=sql2+ ") VALUES ("+PLID.getText()+", '"
								+ team.getSelectedItem()+"','"+contract_begin.getText()
								+ "','"+contract_end.getText()+"'";
						if(salary.getText()!="")
							sql2=sql2+",'"+salary.getText()+"'";
						if(squad.getText()!="")
							sql2=sql2+","+squad.getText();
						sql2=sql2	+ ")";}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				else
				{
					sql2="DELETE from player_team where player_id="+PLID.getText();
				}
				System.out.println(sql2);
				if(!sql2.equals(""))
					Database.executeUpdate(sql2);
				if(Database.executeUpdate(sql)==0)
					JOptionPane.showMessageDialog(null, "修改信息成功！");
				Vector<PlayerAb> vts=PlayerAb.list();
				Object[][] rts;
						rts=getselect(vts);
				table = new JTable(rts,columnNames);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.addMouseListener(new TableListener());
				
				scrollPane.setViewportView(table);

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
				String sql="DELETE from players WHERE `player_id`="+PLID.getText();
						System.out.println(sql);
				if(Database.executeUpdate(sql)==0)
					JOptionPane.showMessageDialog(null, "修改信息成功！");
				Vector<PlayerAb> vts=PlayerAb.list();
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
		Vector<PlayerAb> vt=PlayerAb.list();
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
		final GridLayout gridLayout = new GridLayout(0, 6);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		panel.setLayout(gridLayout);

		final JLabel label_2 = new JLabel();
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setText("球员ID：");
		panel.add(label_2);

		PLID = new JLabel();
		//PLID.setDocument(new MyDocument(13)); 
		panel.add(PLID);
		final JLabel label_1 = new JLabel();
		label_1.setText("姓名：");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1);

		playerName = new JTextField();
		panel.add(playerName);

		final JLabel label_h = new JLabel();
		label_h.setHorizontalAlignment(SwingConstants.CENTER);
		label_h.setText("身高：");
		panel.add(label_h);

		height = new JTextField();
		//writer.setDocument(new MyDocument(10));
		panel.add(height);
		
		final JLabel label_w = new JLabel();
		label_w.setHorizontalAlignment(SwingConstants.CENTER);
		label_w.setText("体重：");
		panel.add(label_w);

		weight = new JTextField();
		//writer.setDocument(new MyDocument(10));
		panel.add(weight);
		final JLabel label_3 = new JLabel();
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setText("身价：");
		panel.add(label_3);

		value = new JTextField();
		//writer.setDocument(new MyDocument(10));
		panel.add(value);

		final JLabel label_2_1 = new JLabel();
		label_2_1.setText("位置：");
		label_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2_1);

		position = new JComboBox();
		String[]array=new String[]{"门将","中后卫","边后卫","后腰","前腰","中场","边锋","中锋"};
		position.setModel(new DefaultComboBoxModel(array));
		panel.add(position);

		final JLabel label_f = new JLabel();
		label_f.setText("惯用脚：");
		label_f.setHorizontalAlignment(SwingConstants.CENTER);;
		panel.add(label_f);
		foot= new JComboBox();
		String[]f=new String[]{"左","右","双"};
		foot.setModel(new DefaultComboBoxModel(f));
		panel.add(foot);
		
		final JLabel label_4 = new JLabel();
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setText("青训：");
		panel.add(label_4);

		teen = new JTextField();
		//translator.setDocument(new MyDocument(10));
		panel.add(teen);

		final JLabel label_1_1 = new JLabel();
		label_1_1.setText("出生日期：");
		label_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1_1);

		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
		Brithday= new JFormattedTextField(myfmt.getDateInstance());
		Brithday.setValue(new java.util.Date());
		panel.add(Brithday);
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
		
		JLabel squadtag=new JLabel("球衣号：");
		squadtag.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(squadtag);
		squad=new JTextField();
		panel.add(squad);
		
		JLabel cb=new JLabel("合同开始：");
		cb.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(cb);
		contract_begin= new JFormattedTextField(myfmt.getDateInstance());
		contract_begin.setValue(new java.util.Date());
		panel.add(contract_begin);
		
		JLabel ce=new JLabel("合同结束：");
		ce.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(ce);
		contract_end= new JFormattedTextField(myfmt.getDateInstance());
		contract_end.setValue(new java.util.Date());
		panel.add(contract_end);
		
		JLabel salarytag=new JLabel("周薪：");
		salarytag.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(salarytag);
		salary=new JTextField();
		panel.add(salary);
		
	}
	private Object[][] getselect(Vector vt) {
		Object[][] s = new Object[vt.size()][15];
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
			ContractInfo ci=ContractInfo.getContractInfoByID(Integer.parseInt((String)s[i][0]));
			s[i][10]=ci.getTeam();
			s[i][11]=ci.getSquad();
			s[i][12]=ci.getBegin();
			s[i][13]=ci.getEnd();
			s[i][14]=ci.getSalary();
		}
		return s;

	}
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			String PLIDs;
			int selRow = table.getSelectedRow();
			PLIDs = table.getValueAt(selRow, 0).toString().trim();
			String name = table.getValueAt(selRow, 1).toString().trim();
			String heights = (table.getValueAt(selRow, 2)!=null?table.getValueAt(selRow, 2).toString().trim():"");
			String weights = (table.getValueAt(selRow, 3)!=null?table.getValueAt(selRow, 3).toString().trim():"");
			String values = (table.getValueAt(selRow, 8)!=null?table.getValueAt(selRow, 8).toString().trim():"");
			String positions =(table.getValueAt(selRow, 4)!=null?table.getValueAt(selRow, 4).toString().trim():"门将");
			String foots =(table.getValueAt(selRow, 5)!=null?table.getValueAt(selRow, 5).toString().trim():"右");
			String teens =(table.getValueAt(selRow, 9)!=null?table.getValueAt(selRow, 9).toString().trim():"");
			String birthday=(table.getValueAt(selRow, 6)!=null?table.getValueAt(selRow, 6).toString().trim():"");
			String nations=(table.getValueAt(selRow, 7)!=null?table.getValueAt(selRow, 7).toString().trim():"");
			String teams=table.getValueAt(selRow, 10).toString().trim();
			String squads=table.getValueAt(selRow, 11).toString().trim();
			String cbe=table.getValueAt(selRow, 12).toString().trim();
			String cen=table.getValueAt(selRow, 13).toString().trim();
			String sal=table.getValueAt(selRow, 14).toString().trim();
			PLID.setText(PLIDs);
			playerName.setText(name);
		
			height.setText(heights);
	
			weight.setText(weights);
	
			value.setText(values);
			position.setSelectedItem(positions);
			foot.setSelectedItem(foots);
	
			teen.setText(teens);
			
			Brithday.setText(birthday);
			
			nation.setText(nations);
			team.setSelectedItem(teams);
			squad.setText(squads);
			contract_begin.setText(cbe);
			contract_end.setText(cen);
			salary.setText(sal);
			//bookTypeModel.setSelectedItem(item);

		}
	}

}
