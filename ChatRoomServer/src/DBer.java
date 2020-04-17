//import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBer {
private static int s=0;
public static Connection getCon()
{
	Connection conn = null;
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=qq;";
	try {
		conn=DriverManager.getConnection(url, "sa","chenbaocun");
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return conn;
	
	
}
public static void inSert(User u) throws SQLException
{
	//Statement stmt;
	String sql="insert into [User](passwd,nickname,home,phonenum,age,sex,mail) values(?,?,?,?,?,?,?)";
	PreparedStatement ps=DBer.getCon().prepareStatement(sql);
	//ps.setInt(1, u.getuserId());
	ps.setString(1, u.getpasswd());
	ps.setString(2, u.getNickname());
	ps.setString(3, u.getHome());
    ps.setString(4, u.getPhonenum());
    ps.setString(5, u.getAge());
    ps.setString(6, u.getSex());
    ps.setString(7, u.getMail());
    ps.executeUpdate();
    ps.close();
    DBer.getCon().close();
    
	
}
public static int checkin(User u)
{
	Connection conn;
	Statement stmt;
    ResultSet rs;
    s=0;
    String sql="select * from [User] where userId="+u.getuserId()+ " and passwd="+u.getpasswd()+"";
    try {
	conn=DBer.getCon();	
	stmt= conn.createStatement();
	rs=stmt.executeQuery(sql);
	while(rs.next())//执行前指向前一位的地址
	{
     s++;
	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(s!=0) 
	{
		return 1;
	}
	else 
		return 0;
    
	
}
public static User select(User u)
{
	User b = new User();
	Connection conn;
	Statement stmt;
	ResultSet rs;
	conn=DBer.getCon();
    String	sql="select * from [User] where userId="+u.getuserId()+" and passwd="+u.getpasswd()+"";
	try {
		
		stmt=conn.createStatement();
		rs=stmt.executeQuery(sql);
		while(rs.next())
		{
		b.setuserId(rs.getInt("userId"));
		b.setpasswd(rs.getString("passwd"));
		b.setNickname(rs.getString("nickname"));
		b.setHome(rs.getString("home"));
		b.setPhonenum(rs.getString("phonenum"));
		b.setAge(rs.getString("age"));
		b.setSex(rs.getString("sex"));
		b.setMail(rs.getString("mail"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return b;	
}
public static String getNickname(int userId1)
{
	String b = null;
	Connection conn;
	Statement stmt;
	ResultSet rs;
	conn=DBer.getCon();
	String sql="select * from[User] where userId="+userId1+"";
	try {
		stmt=conn.createStatement();
		rs=stmt.executeQuery(sql);
		while(rs.next())
		{
			b=rs.getString("nickname");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return b;
	
	
}
public static int getuserId(String nickname)
{
	int b = 0;
	Connection conn;
	Statement stmt;
	ResultSet rs;
	conn=DBer.getCon();
	String sql="select * from[User] where nickname='"+nickname+"'";
	try {
		stmt=conn.createStatement();
		rs=stmt.executeQuery(sql);
		while(rs.next())
		{
			b=rs.getInt("userId");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return b;
	
	
}

}
