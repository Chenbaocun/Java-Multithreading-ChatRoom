import java.awt.JobAttributes;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class ClientKeepThread extends Thread{
	public Socket s;
	public  DefaultListModel ListModel;
	 int userId;
	 User aaa=new User();
	
	
	
	public void run()
	{
		while(true)
		{
			
			try {
				ObjectInputStream os=new ObjectInputStream(s.getInputStream());
				Message msg= (Message)os.readObject();
				//User b=new User();
				//b.setuserId(userId);
				//JOptionPane.showMessageDialog(null,userId+"�յ�������"+ msg.getShangxian());
				if(msg.getMessageType().equals(MessageType.onlineFriend))
				{
					//JOptionPane.showMessageDialog(null, "100de �̵߳�ַ"+clientSaveThread.getclientKeepThread(100));
					//JOptionPane.showMessageDialog(null, msg.getGetter()+"�̵߳�ַ"+clientSaveThread.getclientKeepThread(msg.getGetter()));
					ListModel.addElement(msg.getShangxian());
				}
				else if(msg.getMessageType().equals(MessageType.Already))
				{
					 String b=Integer.toString(userId);
				     String[] a=msg.getShangxian().split(" ");
				     String[] c=msg.getNote().split(" ");
				     
				    //JOptionPane.showMessageDialog(null, getType(b));��string
					
			       for(int i=0;i<a.length;i++)
			       {
			    	  // JOptionPane.showMessageDialog(null, "�յ����Ѿ����ߵģ�"+a[i]+"��"+b);
					if(a[i].equals(b))
					{
			        	//JOptionPane.showMessageDialog(null, "����");
					}
					else   
					{	
						
					//JOptionPane.showMessageDialog(null,userId+DBer.getNickname(userId));
						ListModel.addElement(c[i]);
					}
			       }
				}
				else if(msg.getMessageType().equals(MessageType.Remove))
				{
					ListModel.removeElement(msg.getShangxian());
				}
				else if(msg.getMessageType().equals(MessageType.normal))
				{
					/* JOptionPane.showMessageDialog(null, "�յ�������ת��������"+msg.getSender().getuserId()+"����"
							   +msg.getSendTime()+"���͸�"+msg.getGetter()+"����Ϣ"+msg.getNote());*/
					 //JOptionPane.showMessageDialog(null,""+ChatSave.getAllChatFrame());
					 if((ChatSave.getclientChatFrame(msg.getSender().getNickname()))==null)//null��==,nullû����ռ䡣""�����˿ռ���Ҫ��equal
					 {
						 int m=	JOptionPane.showConfirmDialog(null, "�յ�����"+msg.getSender().getNickname()+"������Ϣ�Ƿ�򿪴���?", "����Ϣ",JOptionPane.YES_NO_OPTION);
						if(m==0)
						{
						 ChatFrame sc=new ChatFrame((msg.getSender().getNickname()));
			    		ChatSave.addClientChatFrame(msg.getSender().getNickname(), sc);
			    		//JOptionPane.showMessageDialog(null, ChatSave.getAllChatFrame());
			    		aaa.setuserId(userId);
			    		sc.u=aaa;
			    		sc.setVisible(true);
						}
					 }
					 else
					 {
						 
					 }
				
					// JOptionPane.showMessageDialog(null,msg.getSender().getNickname());
					 ChatSave.getclientChatFrame(msg.getSender().getNickname()).textArea.append(new java.util.Date().toString()+":\n"+msg.getSender().getNickname()+":"+msg.getNote()+"\n");
					 ChatSave.getclientChatFrame(msg.getSender().getNickname()).textArea.setCaretPosition( ChatSave.getclientChatFrame(msg.getSender().getNickname()).textArea.getText().length());	
				}
				else if(msg.getMessageType().equals(MessageType.GroupChat))
				{
					if((GroupChatSave.getGroupChat("GroupChat"))==null)//null��==,nullû����ռ䡣""�����˿ռ���Ҫ��equal
					 {
						 int m=	JOptionPane.showConfirmDialog(null, "�յ�����"+msg.getSender().getNickname()+"����+Ⱥ+��Ϣ�Ƿ�򿪴���?", "����Ϣ",JOptionPane.YES_NO_OPTION);
						if(m==0)
						{
						 GroupChat sc=new  GroupChat("GroupChat");
			    		GroupChatSave.addGroupChat("GroupChat", sc);
			    		//JOptionPane.showMessageDialog(null, ChatSave.getAllChatFrame());
			    		aaa.setuserId(userId);
			    		sc.u=aaa;
			    		sc.setVisible(true);
						}
					 }
					 else
					 {
						 
					 }
					if(msg.getSender().getuserId()==userId)
					{
						
					}
					else
					{
				
					// JOptionPane.showMessageDialog(null,msg.getSender().getNickname());
					 GroupChatSave.getGroupChat("GroupChat").textArea.append(new java.util.Date().toString()+":\n"+msg.getSender().getNickname()+":"+msg.getNote()+"\n");
					 GroupChatSave.getGroupChat("GroupChat").textArea.setCaretPosition( GroupChatSave.getGroupChat("GroupChat").textArea.getText().length());	
					}
				
					}
				else if(msg.getMessageType().equals(MessageType.adminGroupMessage))
				{
					JOptionPane.showMessageDialog(null, "�յ�����Ա�Ĺ㲥��Ϣ��"+msg.getNote());
				}
				else if(msg.getMessageType().equals(MessageType.adminPrivateMessage))
				{
					JOptionPane.showMessageDialog(null, "�յ�����Ա��˽��֪ͨ:"+msg.getNote());
				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}


	private Object getType(String a) {
		// TODO Auto-generated method stub
		return "String";
		
	}
	

}
