import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.Object;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import java.awt.FlowLayout;
import java.awt.JobAttributes;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JMenuBar;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public  class GGclient extends JFrame {

	private JPanel contentPane;
    public User c;
    public static Socket ss;
	static JButton btnNewButton = new JButton();
	JLabel lblNewLabel_3;
	public static JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					GGclient frame = new GGclient();
					frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public GGclient() {
		setResizable(false);
		GG.sc.ListModel=new DefaultListModel<>();
		//JOptionPane.showMessageDialog(null, b);
		//System.out.println(b);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 442, 865);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		
		final JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(124, 52, 72, 18);
		lblNewLabel.setText("你号");
	
		
		contentPane.add(lblNewLabel);
		
		btnNewButton = new JButton();
	

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNewLabel.setText(c.getNickname());
				lblNewLabel.repaint();
				lblNewLabel_3.setText(Integer.toString(c.getuserId()));
				lblNewLabel_3.repaint();
			}
		});
		btnNewButton.setBounds(24, 443, 113, 27);
		contentPane.add(btnNewButton);
		btnNewButton.setVisible(false);//隐藏按钮
		
		JLabel lblNewLabel_1 = new JLabel("\u5F53\u524D\u7528\u6237\u6635\u79F0\uFF1A");
		lblNewLabel_1.setBounds(14, 52, 105, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5F53\u524D\u7528\u6237\u8D26\u53F7\uFF1A");
		lblNewLabel_2.setBounds(14, 21, 105, 18);
		contentPane.add(lblNewLabel_2);
		
		 lblNewLabel_3 = new JLabel("=");
		lblNewLabel_3.setBounds(124, 21, 72, 18);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u5728\u7EBF\u597D\u53CB\uFF1A");
		lblNewLabel_4.setBounds(24, 104, 87, 18);
		contentPane.add(lblNewLabel_4);
		
	    list = new JList(GG.sc.ListModel);
	    list.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    		if (arg0.getClickCount()==2)
	    		{

	    			if(ChatSave.getclientChatFrame(list.getSelectedValue().toString())==null)
	    			{
	    			ChatFrame sc=new ChatFrame(list.getSelectedValue().toString());
	    			//ChatSave.addClientChatFrame(, );
	    			ChatSave.addClientChatFrame(list.getSelectedValue().toString(), sc);
	    			sc.u=c;
	    			//JOptionPane.showMessageDialog(null, list.getSelectedValue().toString());
	    			//JOptionPane.showMessageDialog(null,""+ChatSave.getclientChatFrame(DBer.getNickname(c.getuserId())) );
	    			sc.setVisible(true);
	    			}
	    			else
	    			{
	    				JOptionPane.showMessageDialog(null, "您与"+list.getSelectedValue().toString()+"的聊天框已经打开，请检查");
	    			}
	    		}
	    	}
	    });
		list.setBounds(97, 113, 147, 271);
		contentPane.add(list);
		
		final JButton btnNewButton_1 = new JButton("\u804A\u5929\u5BA4");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource()==btnNewButton_1)
				{
					if(GroupChatSave.getGroupChat("GroupChat")==null)
					{
					GroupChat sc=new GroupChat("GroupChat");
					sc.u=c;
					sc.setVisible(true);
					GroupChatSave.addGroupChat("GroupChat", sc);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "聊天室已经打开请检查");
					}
			   }
			}
		});
		btnNewButton_1.setFont(new Font("华文行楷", Font.BOLD, 24));
		btnNewButton_1.setBackground(Color.PINK);
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.setBounds(97, 397, 147, 224);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_5 = new JLabel("");
		ImageIcon icon1 = new ImageIcon(getClass().getResource("oldman.jpg"));//在class下找 
		lblNewLabel_5.setIcon(icon1);
		lblNewLabel_5.setBounds(0, 0, 436, 818);
		contentPane.add(lblNewLabel_5);
		addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e)
			{
			super.windowClosing(e);
			
			int m=	JOptionPane.showConfirmDialog(null, "是否退出", "警告",JOptionPane.YES_NO_OPTION);
			System.out.println(m);//是为0,否为1
			if(m==0)
			{
				//JOptionPane.showMessageDialog(null,ss);
				Message exit= new Message();
				exit.setMessageType("1");
				exit.setSender(c);
				//JOptionPane.showMessageDialog(null,exit.getSender().getuserId());
				try {
					ObjectOutputStream a=new ObjectOutputStream(ss.getOutputStream());
					a.writeObject(exit);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            System.exit(0);
				
				
			}
			else
			{
				setDefaultCloseOperation(GGclient.DO_NOTHING_ON_CLOSE);
			}


			}
		});
	}
}
