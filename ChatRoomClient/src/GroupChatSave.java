import java.util.HashMap;

public class GroupChatSave {
	public static HashMap<String, GroupChat> hm=new HashMap<String,GroupChat>();
	public static void addGroupChat(String a,GroupChat sc)
	{ 
		hm.put(a, sc);
	}
	public static GroupChat getGroupChat(String a)
	{
		return (GroupChat)hm.get(a);
	}
	public static void removeGroupChat(String a)
	{
		hm.remove(a);
	}
	public static String getAllgroupChat()
	{
		java.util.Iterator it= hm.keySet().iterator();
		String str="";
		while(it.hasNext())
		{
			str=str+GroupChatSave.getGroupChat(it.next().toString())+" ";
		
		}
		return str;
		
		
	}

}
