//��ɵ�½ȷ�� ʹ�õ���User�������
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
				//JOptionPane.showMessageDialog(null,"�յ���s"+ s);
				//JOptionPane.showMessageDialog(null, "�յ���ע����Ϣ");
				ObjectInputStream ii=new ObjectInputStream(s.getInputStream());//������
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
						// JOptionPane.showMessageDialog(null, "�յ���ע������İ�");
						
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
				a =(User)ii.readObject();//�����л�
			    System.out.println("�û�����"+a.getuserId()+"���룺"+a.getpasswd()+s);
			 // JOptionPane.showMessageDialog(null, "�û���"+a.getuserId());
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
					sc.start();	//����������ͻ��˵���ϵ��
					ThreadSaveThread.addClientThread(b.getuserId(), sc);
					//JOptionPane.showMessageDialog(null,"�����ķ������߳�"+ sc);��
					//JOptionPane.showMessageDialog(null, ThreadSaveThread.getClientThread(b.getuserId()));��ֵ
					//sc.s=s;//ֱ�ӽ�ip Զ�̶˿ڱ��ض˿ڴ��ݵ�keep�߳��У�����ᱨ�˿�ռ�á���Ϊ�����Ѿ�ȷ���������ٿ��ˡ�
					sc.ListModel=this.ListModel;
					
					//JOptionPane.showMessageDialog(null, b.getuserId()+"�洢���߳�"+ThreadSaveThread.getClientThread(b.getuserId()));
					
					sc.tongzhi(b);
					//ServerKeepThread.reAlready();
					/*ListModel<String> m=ListModel;
					for(int i=0,len=m.getSize();i<len;i++){
					   JOptionPane.showMessageDialog(null,m.getElementAt(i)+"��½��");//��ӡÿһ��Ԫ��
					}��ȡlist����������
					*/
	       server.textArea.append(b.getNickname()+"��"+new java.util.Date().toString()+"��½��");
					
					
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
	


