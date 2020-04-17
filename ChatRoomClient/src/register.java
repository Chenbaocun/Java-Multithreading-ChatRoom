import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import com.aliyuncs.DefaultAcsClient;
//import com.aliyuncs.IAcsClient;
//import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
//import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
//import com.aliyuncs.exceptions.ClientException;
//import com.aliyuncs.profile.DefaultProfile;
//import com.aliyuncs.profile.IClientProfile;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class register extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JTextField textField_4;
	private JTextField textField_6;
	private 	int m;
	private JTextField textField_7;
	private 	JComboBox comboBox;
	public static JButton btnNewButton_1;
	private  int b=12345;//存验证码
	private JButton button;
	private int checkname=0;
	public Socket s;
	public Message msg =new Message();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register frame = new register();
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
	public register() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel.setBounds(37, 26, 72, 18);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u6635\u79F0\uFF1A");
		lblNewLabel_1.setBounds(37, 99, 72, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5BB6\u4E61\uFF1A");
		lblNewLabel_2.setBounds(49, 192, 72, 18);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u624B\u673A\u53F7\uFF1A");
		lblNewLabel_3.setBounds(37, 223, 72, 18);
		contentPane.add(lblNewLabel_3);
	
		
		final JButton btnNewButton = new JButton("\u63D0\u4EA4\u7533\u8BF7");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource()==btnNewButton)
				{ 
			
						int count=contentPane.countComponents();
					    m=0;
						for(int i=0;i<count;i++)
						{
							Object obj = contentPane.getComponent(i);
							if(obj instanceof JTextField)
							{
								if(((JTextField) obj).getText().isEmpty() || ((JTextField)obj).getText().length()>50)
								{
									m++;
								}
							}
							
						}
						if(m!=0)//文本框空
						{
							JOptionPane.showMessageDialog(null, "请检查是否有未填信息或者所填信息长度是否超过50", "警告",JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							if(textField.getText().trim().equals(passwordField.getText().trim()))//确认密码是否一致
							{
								if (textField_7.getText().equals(Integer.toString(b)))
								{
									if(Integer.parseInt(textField_4.getText().trim())>10 && Integer.parseInt(textField_4.getText().trim())<100)
									{
										Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
										Matcher matcher = emailPattern.matcher(textField_6.getText().trim());
										if(matcher.find())
										{
											if(textField.getText().trim().length()>=6&& textField.getText().trim().length()<=10)
											{		
											Message a=new Message();
											a.setMessageType(MessageType.register);
											User u=new User();
											u.setpasswd(textField.getText().trim());
											u.setNickname(textField_1.getText().trim());
											u.setHome(textField_2.getText().trim());
											u.setPhonenum(textField_3.getText().trim());
											u.setAge(textField_4.getText().trim());
											u.setSex(comboBox.getSelectedItem().toString());
											u.setMail(textField_6.getText().trim());
											a.setSender(u);
											System.out.println("请等待管理员同意");
											//JOptionPane.showMessageDialog(null, "注册申请已经提交，请等待管理员同意");
											//setVisible(false);
											try {
												s=new Socket("127.0.0.1",9999);
												ObjectOutputStream oo=new ObjectOutputStream(s.getOutputStream());
												oo.writeObject(a);
												oo.writeObject(a);
												ObjectInputStream ii=new ObjectInputStream(s.getInputStream());
												try {
													User dd=new User();
													dd= (User) ii.readObject();
													if(dd.getAge().equals("1"))
													{
														JOptionPane.showMessageDialog(null, "注册成功："+"\n"+"账号:"+dd.getuserId()+"\n"+"密码:"+dd.getpasswd());		
													}
													else
														JOptionPane.showMessageDialog(null, "注册失败请重试");
												} catch (ClassNotFoundException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												
											} catch (IOException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											
										}
										
										else
										{
											JOptionPane.showMessageDialog(null, "密码只能在6-10位，请检查");
										}
										}
										
									
										
										else
										{
											JOptionPane.showMessageDialog(null, "电子邮件格式不正确，请检查");
										}
										
									}
									else
									{
										JOptionPane.showMessageDialog(null, "年龄只允许为10-100，请检查");
									}
									
									
								}
								else
								{
									JOptionPane.showMessageDialog(null, "验证码错误请重试");
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "您输入的两次密码不一致", "警告",JOptionPane.ERROR_MESSAGE);
							}
							
							
							
						}
				
				}
			}
		});
		btnNewButton.setBounds(158, 294, 139, 40);
		contentPane.add(btnNewButton);
		btnNewButton.setEnabled(false);
	
		
		textField = new JTextField();
		textField.setBounds(158, 23, 113, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(158, 96, 113, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(158, 189, 113, 24);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(158, 220, 113, 24);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\u5E74\u9F84\uFF1A");
		lblNewLabel_4.setBounds(285, 26, 72, 18);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\u6027\u522B\uFF1A");
		lblNewLabel_5.setBounds(285, 68, 72, 18);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("\u7535\u5B50\u90AE\u4EF6\uFF1A");
		lblNewLabel_6.setBounds(285, 99, 88, 18);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		lblNewLabel_7.setBounds(14, 68, 88, 18);
		contentPane.add(lblNewLabel_7);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(158, 60, 113, 24);
		contentPane.add(passwordField);
		
		textField_4 = new JTextField();
		textField_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				 int temp = e.getKeyChar();  
	               // System.out.println(temp);  
	                //下面检查是不是在0~9之间；  
	                        if(temp > 57){  
	                            e.consume(); 
	                            JOptionPane.showMessageDialog(null, "只能输入数字");//如果不是则消除key事件,也就是按了键盘以后没有反应;  
	                        }else if(temp < 48){  
	                            e.consume();  
	                            JOptionPane.showMessageDialog(null, "只能输入数字");
	                        }
	                      
			}
		});
		textField_4.setBounds(358, 23, 86, 24);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(358, 96, 145, 24);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
	  btnNewButton_1 = new JButton("\u70B9\u51FB\u83B7\u53D6\u9A8C\u8BC1\u7801");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				b=12345;//为了不影响运行，验证码设置为固定的12345
			}
		});
//		下边这部分代码是在当时做的时候，调用了阿里云的短信验证码，原理是：本地产生一个随机数，然后将这个随机数以短信发送给输入的手机号，然后用短信收到的与本地存储的b进行比较
//		现在这个接口已经没在用了，所以先注释掉，需要的话自行去申请下接口。
//				//设置超时时间-可自行调整
//				System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//				System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//				//初始化ascClient需要的几个参数
//				final String product = "Dysmsapi";//短信API产品名称
//				final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名
//				//替换成你的AK
//				final String accessKeyId = "***";//你的accessKeyId,参考本文档步骤2
//				final String accessKeySecret = "***";//你的accessKeySecret，参考本文档步骤2
//				//初始化ascClient,暂时不支持多region
//				IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
//				accessKeySecret);
//				try {
//					DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
//				} catch (ClientException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				IAcsClient acsClient = new DefaultAcsClient(profile);
//				 //组装请求对象
//				 SendSmsRequest request = new SendSmsRequest();
//				 //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为20个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
//				 request.setPhoneNumbers(textField_3.getText().trim());
//				 //必填:短信签名-可在短信控制台中找到
//				 request.setSignName("陈宝存");
//				 //必填:短信模板-可在短信控制台中找到
//				 request.setTemplateCode("SMS_77595032");
//				 b=(int)((Math.random()*9+1)*100000);
//				// JOptionPane.showMessageDialog(null, b);
//				 String a=Integer.toString(b);
//				 //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//				 request.setTemplateParam("{\"number\":\""+a+"\" }");
//				 //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//				// request.setOutId(b);
//				// request.getOutId();
//				//请求失败这里会抛ClientException异常
//				SendSmsResponse sendSmsResponse = null;
//				try {
//					sendSmsResponse = acsClient.getAcsResponse(request);
//				} catch (ClientException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//				//请求成功
//					//JOptionPane.showMessageDialog(null,sendSmsResponse.getCode());
//			setTime sc=new setTime();
//			sc.start();这两句是按钮点击发送之后，按钮变为60s倒计时并且不能再次点击
//				}
//				else
//					JOptionPane.showMessageDialog(null,"请修改您的手机号");
//
//		
//		
//			}
//		});
		btnNewButton_1.setBounds(305, 216, 152, 32);
		contentPane.add(btnNewButton_1);
		 
		JLabel lblNewLabel_8 = new JLabel("\u9A8C\u8BC1\u7801\uFF1A");
		lblNewLabel_8.setBounds(37, 260, 95, 18);
		contentPane.add(lblNewLabel_8);
		
		textField_7 = new JTextField();
		textField_7.setBounds(157, 257, 114, 24);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		button = new JButton("\u68C0\u67E5\u6635\u79F0\u662F\u5426\u53EF\u7528");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource()==button)
				{
					if(textField_1.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "请输入要检查的昵称");
					}
					else
					{
					try {
					s=new Socket("127.0.0.1",9999);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					//JOptionPane.showMessageDialog(null,s);
					
					msg.setMessageType(MessageType.checknickname);
					msg.setNote(textField_1.getText().trim());
	
					ObjectOutputStream ssc;
					try {
						ssc = new ObjectOutputStream(s.getOutputStream());
						ssc.writeObject(msg);
						//JOptionPane.showMessageDialog(null,msg.getNote());
						ssc.writeObject(msg);
                     ObjectInputStream scc=new ObjectInputStream(s.getInputStream());
						try {
							//JOptionPane.showMessageDialog(null, "您点击了按钮");
							m= (int) scc.readObject();
							if(m==0)
							{
								JOptionPane.showMessageDialog(null, "昵称可用");
								btnNewButton.setEnabled(true);
								btnNewButton.repaint();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "已存在此昵称");
								btnNewButton.setEnabled(false);
								btnNewButton.repaint();
							}
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
		button.setBounds(109, 131, 168, 27);
		contentPane.add(button);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u7537", "\u5973", "\u5176\u4ED6"}));
		comboBox.setBounds(371, 65, 59, 24);
		contentPane.add(comboBox);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);//有内存都将返回到操作系统，并将它们标记为不可显示
	}
}
