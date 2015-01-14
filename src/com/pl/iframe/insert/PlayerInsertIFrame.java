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
		//setIconifiable(true);							// ���ô������С������������
		//setClosable(true);								// ���ô���ɹرգ���������

		setTitle("��Ա��Ϣ���");						// ���ô�����⣭��������
		setBounds(100, 100, 396, 280);					// ���ô���λ�úʹ�С����������
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
		label_2.setText("��Ա��ţ�");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2);

		PLID = new JTextField("������5λID",5);
		//ISBN.setDocument(new Document()); //��������ı����������ֵΪ13
		
		PLID.setColumns(13);
		PLID.addKeyListener(new ISBNkeyListener());
		//ISBN.addFocusListener(new ISBNFocusListener());
		panel.add(PLID);
		//panel.add(new JTextField());

		/*final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("��ӣ�");
		panel.add(label);

		final JComboBox team=new JComboBox();
		Vector<TeamInfo>Ti=TeamInfo.teamlist();
		team.addItem("Ĭ��");
		for(int i=0;i<Ti.size();i++){
			team.addItem(Ti.elementAt(i).getName());
		}*/
		

		//panel.add(team);

		final JLabel label_1 = new JLabel();
		label_1.setText("������");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1);

		playerName = new JTextField();
		panel.add(playerName);

		final JLabel label_h = new JLabel();
		label_h.setHorizontalAlignment(SwingConstants.CENTER);
		label_h.setText("��ߣ�");
		panel.add(label_h);

		final JTextField height = new JTextField();
		//writer.setDocument(new MyDocument(10));
		panel.add(height);
		
		final JLabel label_w = new JLabel();
		label_w.setHorizontalAlignment(SwingConstants.CENTER);
		label_w.setText("���أ�");
		panel.add(label_w);

		final JTextField weight = new JTextField();
		//writer.setDocument(new MyDocument(10));
		panel.add(weight);
		final JLabel label_3 = new JLabel();
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setText("��ۣ�");
		panel.add(label_3);

		value = new JTextField();
		//writer.setDocument(new MyDocument(10));
		panel.add(value);

		final JLabel label_2_1 = new JLabel();
		label_2_1.setText("λ�ã�");
		label_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_2_1);

		position = new JComboBox();
		String[]array=new String[]{"�Ž�","�к���","�ߺ���","����","ǰ��","�г�","�߷�","�з�"};
		position.setModel(new DefaultComboBoxModel(array));
		panel.add(position);

		final JLabel label_f = new JLabel();
		label_f.setText("���ýţ�");
		label_f.setHorizontalAlignment(SwingConstants.CENTER);;
		panel.add(label_f);
		final JComboBox foot= new JComboBox();
		String[]f=new String[]{"��","��","˫"};
		foot.setModel(new DefaultComboBoxModel(f));
		panel.add(foot);
		
		final JLabel label_4 = new JLabel();
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setText("��ѵ��");
		panel.add(label_4);

		teen = new JTextField();
		//translator.setDocument(new MyDocument(10));
		panel.add(teen);

		final JLabel label_1_1 = new JLabel();
		label_1_1.setText("�������ڣ�");
		label_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_1_1);

		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
		Brithday= new JFormattedTextField(myfmt.getDateInstance());
		Brithday.setValue(new java.util.Date());
		panel.add(Brithday);
		final JLabel label_3_1 = new JLabel();
		label_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_3_1.setText("������");
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
		buttonadd.setText("���");
		buttonadd.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(PLID.getText().equals("")||playerName.getText().equals(""))
					{JOptionPane.showMessageDialog(null, "��������Ա������ID");
						return;
					}
				ResultSet test=Database.executeQuery("select * from players where player_id ="+PLID.getText());
				try {
					if(test.next())
					{
						JOptionPane.showMessageDialog(null, "�ظ�����ԱID!");
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
						JOptionPane.showMessageDialog(null, "���ӳɹ���");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}});
		panel_1.add(buttonadd);

		buttonclose = new JButton();
		buttonclose.setText("�ر�");
		buttonclose.addActionListener(new CloseActionListener());
		panel_1.add(buttonclose);

		final JLabel label_5 = new JLabel();
		//ImageIcon bookAddIcon=CreatecdIcon.add("newBookorderImg.jpg");
		//label_5.setIcon(bookAddIcon);
		label_5.setPreferredSize(new Dimension(400, 80));
		label_5.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		//getContentPane().add(label_5, BorderLayout.NORTH);
		
		setVisible(true);
		//setVisible(true);											// ��ʾ����ɹرգ�����������������пؼ�֮��ִ�и����
	}
	/*class ISBNFocusListener extends FocusAdapter {
		public void focusLost(FocusEvent e){
			if(!Dao.selectBookInfo(ISBN.getText().trim()).isEmpty()){
				JOptionPane.showMessageDialog(null, "�������ظ���");
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
	class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
}
