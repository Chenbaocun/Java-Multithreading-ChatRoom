import java.util.*;

//import com.ibm.icu.util.BytesTrie.Iterator;

public class ThreadSaveThread {
	public static HashMap<Integer, ServerKeepThread> hm=new HashMap<Integer,ServerKeepThread>();
	
	public static void addClientThread(int userId,ServerKeepThread sct)
	{
		
		hm.put(userId, sct);
		
		
	}
	public static void removeClientThread(int userId)
	{
		hm.remove(userId);
	}
	public static ServerKeepThread getClientThread(int userId)
	{
		return (ServerKeepThread)hm.get(userId);
	}
	public static String getAllonlineuser()
	{
		java.util.Iterator it= hm.keySet().iterator();
		String str="";
		while(it.hasNext())
		{
			str=str+Integer.parseInt(it.next().toString())+" ";
		
		}
		return str;
		
		
	}
	public static String getAllnickname()
	{
		java.util.Iterator it= hm.keySet().iterator();
		String str="";
		while(it.hasNext())
		{
			str=str+DBer.getNickname(Integer.parseInt(it.next().toString()))+" ";
		
		}
		return str;
		
		
	}

	

}
