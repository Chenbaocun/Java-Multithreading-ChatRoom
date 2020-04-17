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
				//JOptionPane.showMessageDialog(null,userId+"收到了上线"+ msg.getShangxian());
				if(msg.getMessageType().equals(MessageType.onlineFriend))
				{
					//JOptionPane.showMessageDialog(null, "100de 线程地址"+clientSaveThread.getclientKeepThread(100));
					//JOptionPane.showMessageDialog(null, msg.getGetter()+"线程地址"+clientSaveThread.getclientKeepThread(msg.getGetter()));
					ListModel.addElement(msg.getShangxian());
				}
				else if(msg.getMessageType().equals(MessageType.Already))
				{
					 String b=Integer.toString(userId);
				     String[] a=msg.getShangxian().split(" ");
				     String[] c=msg.getNote().split(" ");
				     
				    //JOptionPane.showMessageDialog(null, getType(b));是string
					
			       for(int i=0;i<a.length;i++)
			       {
			    	  // JOptionPane.showMessageDialog(null, "收到的已经上线的："+a[i]+"和"+b);
					if(a[i].equals(b))
					{
			        	//JOptionPane.showMessageDialog(null, "错误");
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
					/* JOptionPane.showMessageDialog(null, "收到服务器转发的来自"+msg.getSender().getuserId()+"的于"
							   +msg.getSendTime()+"发送给"+msg.getGetter()+"的消息"+msg.getNote());*/
					 //JOptionPane.showMessageDialog(null,""+ChatSave.getAllChatFrame());
					 if((ChatSave.getclientChatFrame(msg.getSender().getNickname()))==null)//null用==,null没分配空间。""分配了空间需要用equal
					 {
						 int m=	JOptionPane.showConfirmDialog(null, "收到来自"+msg.getSender().getNickname()+"的新消息是否打开窗口?", "新消息",JOptionPane.YES_NO_OPTION);
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
					if((GroupChatSave.getGroupChat("GroupChat"))==null)//null用==,null没分配空间。""分配了空间需要用equal
					 {
						 int m=	JOptionPane.showConfirmDialog(null, "收到来自"+msg.getSender().getNickname()+"的新+群+消息是否打开窗口?", "新消息",JOptionPane.YES_NO_OPTION);
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
					JOptionPane.showMessageDialog(null, "收到管理员的广播信息："+msg.getNote());
				}
				else if(msg.getMessageType().equals(MessageType.adminPrivateMessage))
				{
					JOptionPane.showMessageDialog(null, "收到管理员的私信通知:"+msg.getNote());
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
