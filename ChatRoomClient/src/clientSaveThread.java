import java.util.HashMap;

public class clientSaveThread {
public static HashMap<Integer, ClientKeepThread> hm=new HashMap<Integer,ClientKeepThread>();
public static void addClientKeepThread(int userId,ClientKeepThread sc)
{ 
	hm.put(userId, sc);
}
public static ClientKeepThread getclientKeepThread(int userId)
{
	return (ClientKeepThread)hm.get(userId);
}


}
