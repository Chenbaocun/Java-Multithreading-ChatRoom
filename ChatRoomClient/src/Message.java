
public class Message  implements java.io.Serializable{
private String MessageType;
private User sender;
private String getter;
private String SendTime;
private String shangxian;
private String note;
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}
public String getMessageType() {
	return MessageType;
}
public void setMessageType(String messageType) {
	MessageType = messageType;
}
public User getSender() {
	return sender;
}
public String getShangxian() {
	return shangxian;
}
public void setShangxian(String shangxian) {
	this.shangxian = shangxian;
}
public void setSender(User sender) {
	this.sender = sender;
}
public String getGetter() {
	return getter;
}
public void setGetter(String getter) {
	this.getter = getter;
}
public String getSendTime() {
	return SendTime;
}
public void setSendTime(String sendTime) {
	SendTime = sendTime;
}

}
