import java.util.HashMap;
public class ChatSave {
	public static HashMap<String, ChatFrame> hm=new HashMap<String,ChatFrame>();
	public static void addClientChatFrame(String a,ChatFrame sc)
	{ 
		hm.put(a, sc);
	}
	public static ChatFrame getclientChatFrame(String a)
	{
		return (ChatFrame)hm.get(a);
	}
	public static void removeChat(String a)
	{
		hm.remove(a);
	}
	public static String getAllChatFrame()
	{
		java.util.Iterator it= hm.keySet().iterator();
		String str="";
		while(it.hasNext())
		{
			str=str+ChatSave.getclientChatFrame(it.next().toString())+" ";
		
		}
		return str;
		
		
	}
}
