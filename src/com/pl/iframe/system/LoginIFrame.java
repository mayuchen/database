package com.pl.iframe.system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.pl.PLsystem;
import com.pl.database.Database;
import com.pl.util.CreatecdIcon;



public class LoginIFrame extends JFrame {

	public static PLsystem frame;
	private class BookResetAction implements ActionListener {
		public void actionPerformed(final ActionEvent e){
			username.setText("");
			password.setText("");
			
		}
	}
	class AdminLoginAction implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			if(Database.check(username.getText(), password.getText()))
				try{
					frame=new PLsystem(true);
					frame.setVisible(true);
					
					LoginIFrame.this.setVisible(false);
				}catch(Exception e1)
			{
					e1.printStackTrace();
			}
			else 
			{
				JOptionPane.showMessageDialog(null,"您不是管理员,请重新尝试登陆");
				password.setText("");
			}
			/*user = Dao.check(username.getText(), password.getText());
			if (user.getName() != null) {

				try {

					Library frame = new Library();
					frame.setVisible(true);
					BookLoginIFrame.this.setVisible(false);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "只有管理员才可以登录！");
				username.setText("");
				password.setText("");
			}*/
		}
	}
	class VistorLoginAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			frame=new PLsystem(false);/////!!!!!!!
			frame.setVisible(true);
			LoginIFrame.this.setVisible(false);
		}
		
	}
	private JPasswordField password;
	private JTextField username;
	private JButton login;
	private JButton reset;
	private JButton visitor;
	//private static Operater user;
	/**
	 * Launch the application
	 * @param args
	 */
	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();

	/**
	 * Create the frame
	 */
	public LoginIFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		borderLayout.setVgap(10);
		getContentPane().setLayout(borderLayout);
		setTitle("英超联赛信息管理系统登录");
		setBounds(100, 100, 285, 194);

		

		
		
		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(panel);

		final JPanel panel_2 = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setHgap(5);
		gridLayout.setVgap(20);
		panel_2.setLayout(gridLayout);
		panel.add(panel_2);

		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setPreferredSize(new Dimension(0, 0));
		label.setMinimumSize(new Dimension(0, 0));
		panel_2.add(label);
		label.setText("用  户  名：");

		username = new JTextField(20);
		username.setPreferredSize(new Dimension(0, 0));
		panel_2.add(username);

		final JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_1);
		label_1.setText("密      码：");

		password = new JPasswordField(20);
		//password.setDocument(new MyDocument(6));
		password.setEchoChar('*');//设置密码框的回显字符
		password.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == 10)
					login.doClick();
			}
		});
		panel_2.add(password);

		final JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);

		login=new JButton();
		login.addActionListener(new AdminLoginAction());
		
		
		login.setText("管理员登录");
		panel_1.add(login);
		
		reset=new JButton();
		reset.addActionListener(new BookResetAction());
		reset.setText("重置");
		panel_1.add(reset);
		
		visitor=new JButton();
		visitor.addActionListener(new VistorLoginAction());
		visitor.setText("游客登录");
		panel_1.add(visitor);

		final JLabel picLabel = new JLabel();
		ImageIcon loginIcon=CreatecdIcon.add("用户登录.jpg");
		picLabel.setIcon(loginIcon);
		picLabel.setOpaque(true);
		picLabel.setBackground(Color.WHITE);
		picLabel.setPreferredSize(new Dimension(260, 60));
		panel.add(picLabel, BorderLayout.NORTH);
		//
		setVisible(true);
		setResizable(false);
		//setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

	}
	//public static Operater getUser() {
	//	return user;
	//}
	//public static void setUser(Operater user) {
	//	BookLoginIFrame.user = user;
	//}

}

