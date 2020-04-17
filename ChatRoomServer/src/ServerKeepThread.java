import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

//import com.ibm.icu.util.BytesTrie.Iterator;

//如果一个用户登陆成功了给其开一个线程让其与客户端保持通讯
public class ServerKeepThread extends Thread{
	public  DefaultListModel ListModel;
	Socket s;
	
	public ServerKeepThread(Socket s) 
	{
		
		this.s = s;
	}
	public static void tongzhi(User b)
	{
		 HashMap hm = ThreadSaveThread.hm;
	     java.util.Iterator it=hm.keySet().iterator();
	     while(it.hasNext())
	     {
	    	 Message a=new Message();
	    	 a.setShangxian(b.getNickname());
	    	 a.setMessageType(MessageType.onlineFriend);
	    	 int onlineUserid=(int) it.next();
	    	 //JOptionPane.showMessageDialog(null, "在线id"+ThreadSaveThread.getAllonlineuser());
	    	 //JOptionPane.showMessageDialog(null, 4+"的线程"+ThreadSaveThread.getClientThread(4));
	    	 //JOptionPane.showMessageDialog(null, onlineUserid+"---的线程"+ThreadSaveThread.getClientThread(onlineUserid));
	    	 try {	 
				ObjectOutputStream aa=new ObjectOutputStream(ThreadSaveThread.getClientThread(onlineUserid)
						.s.getOutputStream());
				a.setGetter(DBer.getNickname(onlineUserid));
				aa.writeObject(a);			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	    	 
	     }		
	}
	public static void remove(int userId)
	{
		 HashMap hm = ThreadSaveThread.hm;
	     java.util.Iterator it=hm.keySet().iterator();
	     while(it.hasNext())
	     {
	    	 Message a=new Message();
	    	 a.setShangxian(DBer.getNickname(userId));
	    	 a.setMessageType(MessageType.Remove);
	    	 int onlineUserid=(int) it.next();
	    	 //JOptionPane.showMessageDialog(null, "在线id"+ThreadSaveThread.getAllonlineuser());
	    	 //JOptionPane.showMessageDialog(null, 4+"的线程"+ThreadSaveThread.getClientThread(4));
	    	 //JOptionPane.showMessageDialog(null, onlineUserid+"---的线程"+ThreadSaveThread.getClientThread(onlineUserid));
	    	 try {	 
				ObjectOutputStream aa=new ObjectOutputStream(ThreadSaveThread.getClientThread(onlineUserid)
						.s.getOutputStream());
				a.setGetter(DBer.getNickname(onlineUserid));
				aa.writeObject(a);			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	    	 
	     }		
		
		
	}
	public void run()
	{
		
		//JOptionPane.showMessageDialog(null,"已经在线的是：");
		try {
			
			Message a=new Message();
	    	 String res=ThreadSaveThread.getAllonlineuser();
	    	 String nick=ThreadSaveThread.getAllnickname();
	    	 sleep(100);
	    	// JOptionPane.showMessageDialog(null, res);
	    	 a.setShangxian(res);
	    	 a.setNote(nick);
	    	 a.setMessageType(MessageType.Already); 
	    	// JOptionPane.showMessageDialog(null, ThreadSaveThread.getClientThread(b.getuserId()));
			ObjectOutputStream aa=new ObjectOutputStream(s.getOutputStream());
			aa.writeObject(a);			
			while(true)
			{
			ObjectInputStream ii=new ObjectInputStream(s.getInputStream());
		    Message msg=(Message)ii.readObject();
		    msg.getSender().setNickname(DBer.getNickname(msg.getSender().getuserId()));
		   if(msg.getMessageType().equals(MessageType.exit))
		   {
			String nickname=(DBer.select(msg.getSender())).getNickname();
			//JOptionPane.showMessageDialog(null, nickname+s+"请求下线");
			ThreadSaveThread.removeClientThread(msg.getSender().getuserId());
			//JOptionPane.showMessageDialog(null, ThreadSaveThread.getAllonlineuser());
			this.remove(msg.getSender().getuserId());
			ListModel.removeElement(nickname);
		   }
		   if(msg.getMessageType().equals(MessageType.normal))
		   {
			   server.textArea.append(DBer.getNickname(msg.getSender().getuserId())+"对"+msg.getGetter()+"说"+msg.getNote()+"\n");
			  server.textArea.setCaretPosition(server.textArea.getText().length());
			   ObjectOutputStream trans=new ObjectOutputStream(ThreadSaveThread.getClientThread(DBer.getuserId(msg.getGetter()))
					   .s.getOutputStream());
			   trans.writeObject(msg);
		   }
		   if(msg.getMessageType().equals(MessageType.GroupChat))
		   {
			   server.textArea.append(DBer.getNickname(msg.getSender().getuserId())+"对"+msg.getGetter()+"说"+msg.getNote()+"\n");
			   HashMap hm = ThreadSaveThread.hm;
			     java.util.Iterator it=hm.keySet().iterator();
			     while(it.hasNext())
			     {
			    	 int onlineUserid=(int) it.next();
			    	 try {	 
						ObjectOutputStream trans=new ObjectOutputStream(ThreadSaveThread.getClientThread(onlineUserid)
								.s.getOutputStream());
						a.setGetter(DBer.getNickname(onlineUserid));
						trans.writeObject(msg);		
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	    	 
			     }					  			
		   }
		   
		
		   }

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
