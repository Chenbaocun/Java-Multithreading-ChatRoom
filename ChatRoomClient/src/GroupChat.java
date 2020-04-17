import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GroupChat extends JFrame {

	private JPanel contentPane;
    public static 	String nickname;
    public JTextArea textArea;
    public  JTextArea textArea_1;
    public User u;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroupChat frame = new GroupChat(nickname);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param nickname 
	 */
	public GroupChat(final String nickname) {
		setTitle(nickname);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 669, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JButton btnenter = new JButton("\u53D1\u9001|Enter");
		btnenter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource()==btnenter)
				{
					Message msg=new Message();
					msg.setMessageType(MessageType.GroupChat);
					msg.setSender(u);
					msg.setGetter(nickname);
					msg.setSendTime(new java.util.Date().toString());
					msg.setNote(textArea_1.getText().trim());
					if(textArea_1.getText().length()>200)
					{
						JOptionPane.showMessageDialog(null, "消息长度不能超过200");
					}
					else
					{
					textArea.append(new java.util.Date().toString()+":\n"+"我:"+textArea_1.getText().trim()+"\n");
					textArea.setCaretPosition(textArea.getText().length());
					textArea_1.setText(null);
					//JOptionPane.showMessageDialog(null,"收到"+u.getuserId() );
					try {
						ObjectOutputStream os=new ObjectOutputStream(clientSaveThread.getclientKeepThread(u.getuserId())
								.s.getOutputStream());
						os.writeObject(msg);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "发送失败");
						e.printStackTrace();
					}
					}	
				}
				
			}
		});
		btnenter.setBounds(490, 408, 147, 41);
		contentPane.add(btnenter);
	  // contentPane.add(textArea);
		
		JScrollPane sp=new JScrollPane();
		sp.setBounds(0, 33, 637, 237);
		contentPane.add(sp);
		
		textArea = new JTextArea();
		sp.setViewportView(textArea);
		textArea.setLineWrap(true);
		//contentPane.add(textArea_1);
		JScrollPane sp1=new JScrollPane();
		sp1.setBounds(0, 294, 637, 113);
		contentPane.add(sp1);
				textArea_1 = new JTextArea();
				sp1.setViewportView(textArea_1);
				textArea_1.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent arg0) {
						int k=arg0.getKeyCode();
						 if(arg0.isControlDown()&&k==arg0.VK_ENTER)
							{	
								textArea_1.append("\n");
							}
						 else if(k==arg0.VK_ENTER)
						{
						
							btnenter.doClick();	
							arg0.consume();
						
						}
				
						if(k==arg0.VK_ESCAPE)
						{
						dispose();	
						}
						
					}
					
				});
				textArea_1.setLineWrap(true);
		addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e)
			{
			super.windowClosing(e);
			
			int m=	JOptionPane.showConfirmDialog(null, "是否退出", "警告",JOptionPane.YES_NO_OPTION);
			System.out.println(m);//是为0,否为1
			if(m==0)
			{
			GroupChatSave.removeGroupChat("GroupChat");
			}
			else
				setDefaultCloseOperation(GGclient.DO_NOTHING_ON_CLOSE);
			}
		
		});
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);//有内存都将返回到操作系统，并将它们标记为不可显示
	}
}
