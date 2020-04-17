//完成登陆确认 使用的是User类的数据
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

public class ServerAcceptThread extends Thread{
	private String m;
	public  DefaultListModel ListModel;
	public static JList list;
	 User mm=new User();
	
   public User b=new User();
	public void run()
	{
		try {
			
			ServerSocket ss = new ServerSocket(9999);
			while(true)
				
			{
				//ListModel = new DefaultListModel();
				Socket s= ss.accept();
				//JOptionPane.showMessageDialog(null,"收到的s"+ s);
				//JOptionPane.showMessageDialog(null, "收到了注册信息");
				ObjectInputStream ii=new ObjectInputStream(s.getInputStream());//输入流
				User a=new User();
				Message msg=new Message();	
				if(ii.readObject() instanceof Message)
				{
				 msg=(Message) ii.readObject();
				  ObjectOutputStream sss=new ObjectOutputStream(s.getOutputStream());
				if(msg.getMessageType().equals(MessageType.checknickname))
				   {
					  int b=0;
					  b=DBer.getuserId(msg.getNote());
				
					  if(b!=0)
					  {
						sss.writeObject(1);   
					  }
					  else
						  sss.writeObject(0);
   
				   }
				if(msg.getMessageType().equals(MessageType.register))
				{
					try {
						// JOptionPane.showMessageDialog(null, "收到了注册申请的包");
						
						 DBer.inSert(msg.getSender());
						// ObjectOutputStream ssss=new ObjectOutputStream(s.getOutputStream());
						 User newu=new User();
						
						 newu.setuserId(DBer.getuserId(msg.getSender().getNickname()));
						 newu.setpasswd(msg.getSender().getpasswd());
						 mm=DBer.select(newu);
						 mm.setAge("1");
						 sss.writeObject(mm);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}mm.setAge("0");
					sss.writeObject(mm);
					
				}
				}
				else
				{
				a =(User)ii.readObject();//反序列化
			    System.out.println("用户名："+a.getuserId()+"密码："+a.getpasswd()+s);
			 // JOptionPane.showMessageDialog(null, "用户名"+a.getuserId());
			    b=DBer.select(a);
				ObjectOutputStream bb=new ObjectOutputStream(s.getOutputStream());
				if(ThreadSaveThread.getClientThread(b.getuserId())==null)
				{
				if(DBer.checkin(a)==1)
				{
				    m="1+"+b.getNickname();
				   // JOptionPane.showMessageDialog(null, m);
					bb.writeObject(m);
				    ListModel.addElement(b.getNickname());
					ServerKeepThread sc=new ServerKeepThread(s);
					sc.start();	//开启保持与客户端的联系；
					ThreadSaveThread.addClientThread(b.getuserId(), sc);
					//JOptionPane.showMessageDialog(null,"创建的服务器线程"+ sc);正
					//JOptionPane.showMessageDialog(null, ThreadSaveThread.getClientThread(b.getuserId()));有值
					//sc.s=s;//直接将ip 远程端口本地端口传递到keep线程中，否则会报端口占用。因为连接已经确定，不能再开了。
					sc.ListModel=this.ListModel;
					
					//JOptionPane.showMessageDialog(null, b.getuserId()+"存储的线程"+ThreadSaveThread.getClientThread(b.getuserId()));
					
					sc.tongzhi(b);
					//ServerKeepThread.reAlready();
					/*ListModel<String> m=ListModel;
					for(int i=0,len=m.getSize();i<len;i++){
					   JOptionPane.showMessageDialog(null,m.getElementAt(i)+"登陆了");//打印每一个元素
					}获取list中所有内容
					*/
	       server.textArea.append(b.getNickname()+"在"+new java.util.Date().toString()+"登陆了");
					
					
				}
				else 
				{
					m="0+"+b.getNickname();
					bb.writeObject(m);
					
				}
				}
				else
				{	m="3+";
				    bb.writeObject(m);
				}
				}	
				/*if(m==1)
				{
					break;
				}*/
				
			}
				
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		
	}
}
	


