package com.online_message;

import java.sql.*;

public class OmsgVO {
	private Integer message_id;
	private	Timestamp msg_time;
	private	String msg_content;
	private Timestamp reply_time; 
	private String reply_content;
	
	public Integer getMessage_id() {
		return message_id;
	}
	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}
	public Timestamp getMsg_time() {
		return msg_time;
	}
	public void setMsg_time(Timestamp msg_time) {
		this.msg_time = msg_time;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public Timestamp getReply_time() {
		return reply_time;
	}
	public void setReply_time(Timestamp reply_time) {
		this.reply_time = reply_time;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	
	public String toString() {
		return "message_id: " + message_id + "\n" 
				+ "msg_time: " + msg_time + "\n" 
				+ "msg_content: " + msg_content + "\n"
				+ "reply_time: " + reply_time + "\n"
				+ "reply_content: " + reply_content;
	}
	
}
