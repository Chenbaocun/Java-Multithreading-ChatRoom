import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GG extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
    public Socket s;
    public User u=new User();
    static ClientKeepThread sc=new ClientKeepThread();	
    int temp=0;
  

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GG frame = new GG();
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
	public GG() {
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8D26\u53F7\uFF1A");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel.setBounds(138, 163, 72, 18);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(138, 206, 72, 18);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				  
		                int temp = e.getKeyChar();  
		               // System.out.println(temp);  
		                //�������ǲ�����0~9֮�䣻  
		                        if(temp > 57){  
		                            e.consume();    //�������������key�¼�,Ҳ���ǰ��˼����Ժ�û�з�Ӧ;  
		                        }else if(temp < 48){  
		                            e.consume();  
		                        }
		                        String s = textField.getText();  
		                        if(s.length() >= 8) 
		                        	{e.consume();
		                        	JOptionPane.showMessageDialog(null, "�8λ����");
		                        	}
			}
		});
		textField.setBounds(214, 158, 151, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("\u8BB0\u4F4F\u5BC6\u7801");
		chckbxNewCheckBox.setBounds(146, 250, 133, 27);
		contentPane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("\u81EA\u52A8\u767B\u5F55");
		chckbxNewCheckBox_1.setBounds(321, 250, 133, 27);
		contentPane.add(chckbxNewCheckBox_1);
		
		final JButton button = new JButton("\u767B\u9646");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource()==button)
				{
					int count=contentPane.countComponents();
				     int   mm=0;
					for(int i=0;i<count;i++)
					{
						Object obj = contentPane.getComponent(i);
						if(obj instanceof JTextField)
						{
							if(((JTextField) obj).getText().isEmpty())
							{
								mm++;
							}
						}
						
					}
					if(mm!=0)//�ı����
					{
						JOptionPane.showMessageDialog(null, "���飬������Ϣ���붼��д", "����",JOptionPane.ERROR_MESSAGE);
					}
					else if(passwordField.getText().length()<6||passwordField.getText().length()>10)
					{
						JOptionPane.showMessageDialog(null, "����ָ��Ϊ6-10λ������");
					}
					else
					{
					
					u.setuserId(Integer.parseInt(textField.getText().trim()));
					u.setpasswd(passwordField.getText().trim());
				    System.out.println("�ͻ������룺"+u.getuserId()+"\n"+u.getpasswd());
					//System.out.println("�û���Ϊ��"+u.getuserId());
					//Socket s;
					try {
						s=new Socket("127.0.0.1",9999);
						//JOptionPane.showMessageDialog(null, "������socket s"+s);
						ObjectOutputStream a=new ObjectOutputStream(s.getOutputStream());//�½�һ�����л���a������ΪΪSocket s�����л�
						a.writeObject(u);//���л�
						a.writeObject(u);
						//System.out.println(u.getuserId()+'\n'+u.getpasswd());
						ObjectInputStream b=new ObjectInputStream(s.getInputStream());//�����л�����b
						String m = null;
						try {
							m = (String) b.readObject();//�����л�
							String[] aa=m.split("\\+");
							System.out.println("checkin����ֵ"+m);
							if(aa[0].equals("1"))
							{
								u.setNickname(aa[1]);
								/*Message already=new Message();
								already.setMessageType(MessageType.Already);
								a.writeObject(already);*/
								//JOptionPane.showMessageDialog(null,"��½�ɹ�"); 
								// ClientKeepThread sc=new ClientKeepThread();
								setVisible(false);
								GGclient s1=new GGclient(); 
								s1.c=u;
								s1.ss=s;//����
					            s1.setVisible(true);
					            s1.btnNewButton.doClick();
					           // ClientKeepThread sc=new ClientKeepThread();
					            sc.s=s;
					            sc.userId=u.getuserId();
					            sc.aaa=u;
					   		    sc.start();
					            clientSaveThread.addClientKeepThread(u.getuserId(), sc);
					            
					           
							}
							else if(aa[0].equals("0"))
							{
								JOptionPane.showMessageDialog(null, "��������˺Ŵ���", "GG",JOptionPane.ERROR_MESSAGE);
							temp++;
							if(temp>=3)
							{
								JOptionPane.showMessageDialog(null, "����������Σ����򽫹ر�");
								System.exit(0);
								
							}
							}
							else
								JOptionPane.showMessageDialog(null, "���û��Ѿ���������");
							//GG s2=new GG();
						    //s2.setVisible(true);
							//System.out.println(b.readObject());
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();		
					}	
					}
				}	
			}
		});
		button.setFont(new Font("����", Font.PLAIN, 20));
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(30, 144, 255));
		button.setBounds(197, 295, 168, 33);
		contentPane.add(button);
		final JButton button_1 = new JButton("\u6CE8\u518C");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==button_1)
				{
					register sc=new register();
					sc.setVisible(true);
					
				}
			}
		});
		button_1.setFont(new Font("����", Font.PLAIN, 16));
		button_1.setForeground(new Color(255, 255, 255));
		button_1.setBackground(new Color(30, 144, 255));
		button_1.setBounds(379, 161, 113, 27);
		contentPane.add(button_1);
		ImageIcon icon = new ImageIcon(getClass().getResource("/����.jpg"));//��class���� 
      //  Image img = icon.getImage(); 
		JLabel lblNewLabel_4 = new JLabel(icon);
	
        
	
		lblNewLabel_4.setBounds(0, 0, 592, 386);
		contentPane.add(lblNewLabel_4);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				int k=arg0.getKeyCode();
				if(k==arg0.VK_ENTER)
				{
					button.doClick();	
				}
			}
		});

		passwordField.setBounds(214, 201, 151, 33);
		contentPane.add(passwordField);
		
	
	}
}
