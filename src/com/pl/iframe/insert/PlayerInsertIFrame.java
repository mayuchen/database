package com.pl.iframe.insert;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
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

import com.pl.database.Database;
import com.pl.model.TeamInfo;


public class PlayerInsertIFrame extends JInternalFrame{

	private JComboBox position;
	private JTextField   nation;
	private JFormattedTextField Brithday;
	private JTextField teen;
	private JTextField value;
	private JTextField PLID;
	private JTextField playerName;
	private JComboBox team;
	private JButton buttonadd;
	private JButton buttonclose;
	DefaultComboBoxModel TeamModel;
	
	Map map=new HashMap();
	public PlayerInsertIFrame() {
		super();
		
		final BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		//setIconifiable(true);							// 设置窗体可最小化－－－必须
		//setClosable(true);								// 设置窗体可关闭－－－必须

		setTitle("球员信息添加");						// 设置窗体标题－－－必须
		setBounds(100, 100, 396, 280);					// 设置窗体位置和大小－－－必须
		setIconifiable(true);
		setClosable(true);
		
		final JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 10, 5, 10));
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		panel.setLayout(gridLayout);
		getContentPane().add(panel);

		final JLabel label_2 = new JLabel();
		label_2.setText("球员编号：");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2);

		PLID = new JTextField("请输入5位ID",5);
		//ISBN.setDocument(new Document()); //设置书号文本框最大输入值为13
		
		PLID.setColumns(13);
		PLID.addKeyListener(new ISBNkeyListener());
		//ISBN.addFocusListener(new ISBNFocusListener());
		panel.add(PLID);
		//panel.add(new JTextField());

		/*final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("球队：");
		panel.add(label);

		final JComboBox team=new JComboBox();
		Vector<TeamInfo>Ti=TeamInfo.teamlist();
		team.addItem("默认");
		for(int i=0;i<Ti.size();i++){
			team.addItem(Ti.elementAt(i).getName());
		}*/
		

		//panel.add(team);

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

		final JTextField height = new JTextField();
		//writer.setDocument(new MyDocument(10));
		panel.add(height);
		
		final JLabel label_w = new JLabel();
		label_w.setHorizontalAlignment(SwingConstants.CENTER);
		label_w.setText("体重：");
		panel.add(label_w);

		final JTextField weight = new JTextField();
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
		final JComboBox foot= new JComboBox();
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

		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(2);
		flowLayout.setHgap(30);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.setLayout(flowLayout);

		buttonadd= new JButton();
		buttonadd.setText("添加");
		buttonadd.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(PLID.getText().equals("")||playerName.getText().equals(""))
					{JOptionPane.showMessageDialog(null, "请输入球员姓名，ID");
						return;
					}
				ResultSet test=Database.executeQuery("select * from players where player_id ="+PLID.getText());
				try {
					if(test.next())
					{
						JOptionPane.showMessageDialog(null, "重复的球员ID!");
					}
					else
					{
						String sql="INSERT INTO players (player_id,player_name,"
								+ "player_value,player_position,player_birthday,player_foot";
						if(!teen.getText().equals(""))
							sql=sql+",player_youth_team";
						if(!nation.getText().equals(""))
							sql=sql+",player_nation";
						if(!height.getText().equals(""))
							sql=sql+",player_height";
						if(!weight.getText().equals(""))
							sql=sql+",player_weight";						
						sql=sql+")VALUES("+PLID.getText()+",'"+playerName.getText()+"',";
						if(value.getText().equals(""))
							sql=sql+"'0M'";
						else
							sql=sql+"'"+value.getText()+"'";
						sql=sql+",'"+position.getSelectedItem()+"'";
						sql=sql+",'"+Brithday.getText()+"'";
						sql=sql+",'"+foot.getSelectedItem()+"'";
						if(!teen.getText().equals(""))
							sql=sql+",'"+teen.getText()+"'";
						if(!nation.getText().equals(""))
							sql=sql+",'"+nation.getText()+"'";
						if(!height.getText().equals(""))
							sql=sql+","+height.getText();
						if(!weight.getText().equals(""))
							sql=sql+","+weight.getText();
						sql=sql+")";
						//System.err.println(sql);
							
						Database.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "增加成功！");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}});
		panel_1.add(buttonadd);

		buttonclose = new JButton();
		buttonclose.setText("关闭");
		buttonclose.addActionListener(new CloseActionListener());
		panel_1.add(buttonclose);

		final JLabel label_5 = new JLabel();
		//ImageIcon bookAddIcon=CreatecdIcon.add("newBookorderImg.jpg");
		//label_5.setIcon(bookAddIcon);
		label_5.setPreferredSize(new Dimension(400, 80));
		label_5.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		//getContentPane().add(label_5, BorderLayout.NORTH);
		
		setVisible(true);
		//setVisible(true);											// 显示窗体可关闭－－－必须在添加所有控件之后执行该语句
	}
	/*class ISBNFocusListener extends FocusAdapter {
		public void focusLost(FocusEvent e){
			if(!Dao.selectBookInfo(ISBN.getText().trim()).isEmpty()){
				JOptionPane.showMessageDialog(null, "添加书号重复！");
				return;
			}
		}
	}*/
	class ISBNkeyListener extends KeyAdapter {
		public void keyPressed(final KeyEvent e) {
			if (e.getKeyCode() == 5){
				buttonadd.doClick();
			}
		
		}
	}
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
}
