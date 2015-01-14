package com.pl;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.pl.iframe.system.LoginIFrame;


public class PLsystem extends JFrame{
	
	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();
	
	public static LoginIFrame login;
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
			login= new LoginIFrame();//��¼����
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void addIFame(JInternalFrame iframe) { // ����Ӵ���ķ���
		DESKTOP_PANE.add(iframe);
		try {
			iframe.setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PLsystem(boolean isadmin)
	{
		super();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setLocationByPlatform(true);
		setSize(800, 600);
		setSize(800, 600);
		setTitle("Ӣ����Ϣ����ϵͳ");
		JMenuBar menu=createMenu(isadmin);
		setJMenuBar(menu);
		final JLabel label = new JLabel();
		label.setBounds(0, 0, 0, 0);
		label.setIcon(null); 
		
		
		DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				Dimension size = e.getComponent().getSize();
				label.setSize(e.getComponent().getSize());
				label.setText("<html><img width=" + size.width + " height="
						+ size.height + " src='"
						+ this.getClass().getResource("/������.jpg")
						+ "'></html>");
			}
		});
		
		DESKTOP_PANE.add(label,new Integer(Integer.MIN_VALUE));
		getContentPane().add(DESKTOP_PANE);
	}
	
	/*******************
	 *****�˵����Ĵ���******
	 *******************/
	private JMenuBar createMenu(boolean isadmin)
	{
		JMenuBar bar=new JMenuBar();
		JMenu search=new JMenu("��Ϣ��ѯ");
		search.add(MenuActions.PLAYER_SEARCH);
		search.add(MenuActions.TEAM_SEARCH);
		search.add(MenuActions.MATCH_SEARCH);
		search.add(MenuActions.LEAGUE_SEARCH);
		bar.add(search);
		JMenu informationinsert=new JMenu("������Ϣ");
		informationinsert.add(MenuActions.ADD_PLAYER);
		informationinsert.add(MenuActions.ADD_COACH);
		informationinsert.add(MenuActions.ADD_REFEREE);
		JMenu informationupdate=new JMenu("�޸���Ϣ");
			informationupdate.add(MenuActions.PLAYER_UPDATE);
			informationupdate.add(MenuActions.COACH_UPDATE);
			informationupdate.add(MenuActions.MATCH_UPDATE);
		if(isadmin)
			bar.add(informationinsert);
		if(isadmin)
			bar.add(informationupdate);
		
		JMenu sysconfig=new JMenu("ϵͳ����");
		sysconfig.add(MenuActions.RE_LOGIN);
		//sysconfig.add(MenuActions.MODIFY_PASSWORD);
		bar.add(sysconfig);
		return bar;}
}
