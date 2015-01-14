package com.pl.iframe.search;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.pl.model.PlayerAb;
import com.pl.model.PlayerPr;
import com.pl.model.TeamInfo;

public class PlayerSearchIFrame extends JInternalFrame {

//	private JComboBox choice;
	private JTextField T;
	final BorderLayout borderLayout = new BorderLayout();
//	private JTextField TTT;
//	private JScrollPane scrollpane_1;
	public PlayerSearchIFrame()
	{
		super();
		
		setSize(800, 600);
		//T1=new JComboBox();
		//T1.addItem("1");

		setTitle("��Ա��ѯ");
		setBounds(100, 100, 600, 400);
		setResizable(true);
		setIconifiable(true);
		setClosable(true);
		setVisible(true);

		final JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(tabbedPane);
		
		////******��Ա������ѯ
		final JPanel panel_1 = AB();
		tabbedPane.addTab("��Ա������ѯ", null, panel_1, "�鿴��Ա�Ļ�����Ϣ");
		
		
		///*****��Ա���ֲ�ѯ
		final JPanel panel_2 = PR();
		
		tabbedPane.addTab("��Ա���ֲ�ѯ��ѯ", null, panel_2, "��ѯ��������Ա����");
		

		
		

		this.setVisible(true);
		
	}
	private JPanel AB(){
		
		JPanel panel_1=new JPanel();
		panel_1.setLayout(new BorderLayout());
		

			///����
		final JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(null, "��Ա����", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1_1.setPreferredSize(new Dimension(0, 50));
		JLabel rtag=new JLabel("���");
		panel_1_1.add(rtag);
		final JComboBox plteam=new JComboBox();
		Vector<TeamInfo>Ti=TeamInfo.teamlist();
		plteam.addItem("Ĭ��");
		for(int i=0;i<Ti.size();i++){
			plteam.addItem(Ti.elementAt(i).getName());
		}
		
		final JComboBox choice=new JComboBox();
		String[] array={"��Ա����","��ԱID","���º�"};//
		for(int i=0;i<array.length;i++){
			choice.addItem(array[i]);
		}
		panel_1_1.add(plteam);
		panel_1_1.add(choice);
		
		final JTextField TTT = new JTextField();
		TTT.setColumns(20);
		panel_1_1.add(TTT);
		panel_1.add(panel_1_1, BorderLayout.NORTH);
		
		final String [] playersearch = { "��ԱID", "����","���",  "����", "λ��","����","����","����","���","��ѵ" };
		Object[][]re={};
		final JTable TJ=new JTable(re,playersearch);
		final JScrollPane scrollpane_1 = new JScrollPane();
		scrollpane_1.setBounds(50,30, 300, 600);
		scrollpane_1.setViewportView(TJ);
		final JButton button_s1 = new JButton();
		button_s1.setText("��ѯ");
		button_s1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
			/////******
				Vector<PlayerAb> vt;
				if(plteam.getSelectedItem().equals("Ĭ��")&&TTT.getText().equals(""))
					vt=PlayerAb.list();
				else vt=PlayerAb.playerAbility(
						plteam.getSelectedItem().toString(), 
						TTT.getText(),
						choice.getSelectedItem().toString());
				if(vt.size()>0)
				{
					Object[][] rt=getselect(vt);
					JTable Tx=new JTable(rt,playersearch);
					Tx.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					scrollpane_1.setViewportView(Tx);
				}
				else
					JOptionPane.showMessageDialog(null, "�޽����");
				///*********
			}
		}
		);
		panel_1_1.add(button_s1);
		final JButton button_q1 = new JButton();
		button_q1.setText("�˳�");
		button_q1.addActionListener(new CloseActionListener());
		panel_1_1.add(button_q1);
		
		
		//���
		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "��ѯ�����ʾ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1.add(panel);

			//�����

		scrollpane_1.setPreferredSize(new Dimension(500, 200));
		panel.add(scrollpane_1);
		
		
		return panel_1;
		
		
		
	}
	private JPanel PR(){

		JPanel panel_1=new JPanel();
		panel_1.setLayout(new BorderLayout());
		

			///����
		final JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(null, "��Ա����", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1_1.setPreferredSize(new Dimension(0, 50));
		JLabel rtag=new JLabel("���");
		panel_1_1.add(rtag);
		final JComboBox plteam=new JComboBox();
		Vector<TeamInfo>Ti=TeamInfo.teamlist();
		plteam.addItem("Ĭ��");
		for(int i=0;i<Ti.size();i++){
			plteam.addItem(Ti.elementAt(i).getName());
		}
		
		final JComboBox choice=new JComboBox();
		String[] array={"��Ա����","��ԱID","���º�"};//
		for(int i=0;i<array.length;i++){
			choice.addItem(array[i]);
		}
		panel_1_1.add(plteam);
		panel_1_1.add(choice);
		
		final JTextField TTT = new JTextField();
		TTT.setColumns(20);
		panel_1_1.add(TTT);
		panel_1.add(panel_1_1, BorderLayout.NORTH);
		
		final String [] playersearch = 
			{ "��ԱID", "����","������",  "������" ,"�������"};
		Object[][]re={};
		final JTable TJ=new JTable(re,playersearch);
		final JScrollPane scrollpane_1 = new JScrollPane();
		scrollpane_1.setBounds(50,30, 300, 400);
		scrollpane_1.setViewportView(TJ);
		final JButton button_s1 = new JButton();
		button_s1.setText("��ѯ");
		button_s1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
			/////******
				Vector<PlayerPr> vt=PlayerPr.playerProfrom(
						plteam.getSelectedItem().toString(), 
						TTT.getText(),
						choice.getSelectedItem().toString());
				if(vt.size()>0)
				{
					Object[][] rt=getprofrom(vt);
					JTable Tx=new JTable(rt,playersearch);
					scrollpane_1.setViewportView(Tx);
				}
				else
					JOptionPane.showMessageDialog(null, "��������û�н�����������ݣ�");
				///*********
			}
		}
		);
		panel_1_1.add(button_s1);
		final JButton button_q1 = new JButton();
		button_q1.setText("�˳�");
		button_q1.addActionListener(new CloseActionListener());
		panel_1_1.add(button_q1);
		
		
		//���
		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "��ѯ�����ʾ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_1.add(panel);

			//�����

		scrollpane_1.setPreferredSize(new Dimension(500, 200));
		panel.add(scrollpane_1);
		
		
		return panel_1;
		
		
		
		
	}
	class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
	
	private Object[][] getselect(Vector vt) {
		Object[][] s = new Object[vt.size()][10];
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
		}
		return s;

	}
	private Object[][]getprofrom(Vector<PlayerPr> vt)
	{
		Object[][] s =new Object[vt.size()][5];
		for(int i=0;i<vt.size();i++)
		{
			PlayerPr ppr=vt.elementAt(i);
			s[i][0]=ppr.getId();
			s[i][1]=ppr.getName();
			s[i][2]=ppr.getGoals();
			s[i][3]=ppr.getAssist();
			s[i][4]=ppr.getTeam();
		}
		return s;
	}
}
