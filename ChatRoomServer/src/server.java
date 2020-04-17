import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class server extends JFrame {

	private JPanel contentPane;
	private  JTextField textField;
	private final JButton btnNewButton_2 = new JButton("\u70B9\u51FB\u53D1\u9001");
	public static JTextArea textArea ;
    public 	static JCheckBox  chckbxNewCheckBox;
    public 	static JCheckBox  chckbxNewCheckBox_1;
	public static JList list;
	//private  DefaultListModel ListModel;
    private String b="";
    ServerAcceptThread aa=new ServerAcceptThread();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					server frame = new server();
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
	public server() {
	    aa.ListModel = new DefaultListModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 911, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JButton btnNewButton = new JButton("\u5F00\u59CB\u76D1\u542C");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource()==btnNewButton)
				{
					System.out.println("我是服务器，在9999监听"+"\n");	
		            textArea.setText("开始在9999端口监听"+"\n");
					aa.start();
							//JOptionPane.showMessageDialog(null, a.getuserId());
						   // textArea = new JTextArea();  
					//ListModel.addElement();
		
				
					}
					
				}
				
			
		});
		btnNewButton.setBounds(14, 13, 113, 40);
		contentPane.add(btnNewButton);
		
		final JButton btnNewButton_1 = new JButton("\u505C\u6B62\u76D1\u542C");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource()==btnNewButton_1)
				{
					int n = JOptionPane.showConfirmDialog(null, "停止监听", "标题",JOptionPane.YES_NO_OPTION);//真0假1
					System.out.println(n);
				}
			}
		});
		btnNewButton_1.setBounds(14, 66, 113, 40);
		contentPane.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(91, 335, 218, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u5E7F\u64AD\u6D88\u606F\uFF1A");
		lblNewLabel.setBounds(14, 338, 92, 18);
		contentPane.add(lblNewLabel);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxNewCheckBox.isSelected()==true&&chckbxNewCheckBox_1.isSelected()==false)
				{
					Message msg=new Message();
					msg.setMessageType(MessageType.adminGroupMessage);
					msg.setNote(textField.getText().trim());
					
					 server.textArea.append("管理员发送广播消息"+msg.getNote()+"\n");
					   HashMap hm = ThreadSaveThread.hm;
					     java.util.Iterator it=hm.keySet().iterator();
					     while(it.hasNext())
					     {
					    	 int onlineUserid=(int) it.next();
					    	 try {	 
								ObjectOutputStream trans=new ObjectOutputStream(ThreadSaveThread.getClientThread(onlineUserid)
										.s.getOutputStream());
								trans.writeObject(msg);		
							} catch (IOException e) {
								JOptionPane.showMessageDialog(null, "未知错误,发送失败");
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	    	 
					     }		
					textField.setText("");
					
				}
				else if(chckbxNewCheckBox_1.isSelected()==true&&chckbxNewCheckBox.isSelected()==false)
				{
					
					Message msg=new Message();
					msg.setMessageType(MessageType.adminPrivateMessage);
					msg.setNote(textField.getText().trim());
			    	//JOptionPane.showMessageDialog(null, ""+list.getSelectedValue());
					
	                 
					if(list.getSelectedValue()==null)
					{
						JOptionPane.showMessageDialog(null, "请从列表中选择要发送的对象");
						
					}
					else
					{
						
						try {
							server.textArea.append("管理员向"+list.getSelectedValue().toString()+"发送了广播消息:"+msg.getNote()+"\n");
							ObjectOutputStream ss=new ObjectOutputStream(ThreadSaveThread.getClientThread(DBer.getuserId(list.getSelectedValue().toString()))
									.s.getOutputStream());
							ss.writeObject(msg);
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, "未知错误,发送失败");
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					textField.setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "请检查私聊群聊选择情况");
				}
			}
		});
		btnNewButton_2.setBounds(323, 335, 149, 24);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("\u6D88\u606F\u901A\u8BAF\uFF1A");
		lblNewLabel_1.setBounds(159, 13, 92, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("\u5728\u7EBF\uFF1A");
		label.setBounds(482, 13, 92, 18);
		contentPane.add(label);
		
		 chckbxNewCheckBox = new JCheckBox("\u7FA4\u53D1");
		chckbxNewCheckBox.setBounds(47, 368, 133, 27);
		contentPane.add(chckbxNewCheckBox);
		
		 chckbxNewCheckBox_1 = new JCheckBox("\u79C1\u804A");
		chckbxNewCheckBox_1.setBounds(218, 368, 133, 27);
		contentPane.add(chckbxNewCheckBox_1);
		
		JScrollPane sp=new JScrollPane();
		sp.setBounds(169, 44, 182, 262);
		contentPane.add(sp);
		
		textArea = new JTextArea();
		sp.setViewportView(textArea);
		textArea.setLineWrap(true);
		//textArea = new JTextArea();
		textArea.setBounds(169, 44, 182, 262);
		
		list = new JList(aa.ListModel);
		list.setBounds(461, 54, 113, 268);
		contentPane.add(list);		
				
		   
		
	    
	}
}
