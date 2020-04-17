
public class User  implements java.io.Serializable{
private  	int userId;
private String passwd;
private String nickname;
private String home;
private String phonenum;
private String age;
private String sex;
private String mail;
public String getAge() {
	return age;
}
public void setAge(String age) {
	this.age = age;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getMail() {
	return mail;
}
public void setMail(String mail) {
	this.mail = mail;
}
public  int getuserId()
{
	return userId;
	
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public String getHome() {
	return home;
}
public void setHome(String home) {
	this.home = home;
}
public String getPhonenum() {
	return phonenum;
}
public void setPhonenum(String phonenum) {
	this.phonenum = phonenum;
}
public String getpasswd()
{
	return passwd;
}
public void setuserId(int string)
{
	this.userId=string;
	
}
public void setpasswd(String passwd)
{
	this.passwd=passwd;
}
}