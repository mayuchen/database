package com.pl.iframe.insert;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

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
import com.pl.iframe.insert.PlayerInsertIFrame.CloseActionListener;
import com.pl.iframe.insert.PlayerInsertIFrame.ISBNkeyListener;

public class CoachInsertIFrame extends JInternalFrame{
	private JTextField   nation;
	private JFormattedTextField Brithday;
	private JTextField CAID;
	private JTextField coachName;
	private JButton buttonadd;
	private JButton buttonclose;
	DefaultComboBoxModel TeamModel;
	
	Map map=new HashMap();
	public CoachInsertIFrame() {
		super();
		
		final BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		//setIconifiable(true);							// 设置窗体可最小化－－－必须
		//setClosable(true);								// 设置窗体可关闭－－－必须

		setTitle("教练信息添加");						// 设置窗体标题－－－必须
		setBounds(100, 100, 396, 200);					// 设置窗体位置和大小－－－必须
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
		label_2.setText("教练编号：");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2);

		CAID = new JTextField("请输入5位ID",5);
		//ISBN.setDocument(new Document()); //设置书号文本框最大输入值为13
		
		CAID.setColumns(13);
		CAID.addKeyListener(new ISBNkeyListener());
		//ISBN.addFocusListener(new ISBNFocusListener());
		panel.add(CAID);
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

		coachName = new JTextField();
		panel.add(coachName);

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
				if(CAID.getText().equals("")||coachName.getText().equals(""))
					{JOptionPane.showMessageDialog(null, "请输入球员姓名，ID");
						return;
					}
				ResultSet test=Database.executeQuery("select * from coach where coach_id ="+CAID.getText());
				try {
					if(test.next())
					{
						JOptionPane.showMessageDialog(null, "重复的教练ID!");
					}
					else
					{
						String sql="INSERT INTO coach (coach_id,coach_name,"
								+ "coach_birthday";

						if(!nation.getText().equals(""))
							sql=sql+",coach_nation";
				
						sql=sql+")VALUES("+CAID.getText()+",'"+coachName.getText()+"'";
						
						sql=sql+",'"+Brithday.getText()+"'";
						
						
						if(!nation.getText().equals(""))
							sql=sql+",'"+nation.getText()+"'";
						
						sql=sql+")";
						System.out.println(sql);
							
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
		label_5.setText("新书定购(LOGO图片)");
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
