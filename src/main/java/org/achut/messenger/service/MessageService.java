package org.achut.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.achut.messenger.database.DatabaseClass;
import org.achut.messenger.model.Message;

public class MessageService {

	private Map<Long,Message> messages = DatabaseClass.getAllMessages();
	 
	public MessageService() {
		messages.put(1L, new Message(1L,"Hello","Achut"));
		messages.put(2L, new Message(2L,"World","Achut"));
	}
	public List<Message> getMessages() {
		return new ArrayList<Message>(messages.values());
	}
	public Message getMessageById(long id) {
		return messages.get(id);
	}
	public Message addMessage(Message message) {
		message.setId(messages.size()+1);
		messages.put(message.getId() , message);
		return message;
	}
	public List<Message> getMessageByYear(int year) {
		Calendar cal = Calendar.getInstance();
		List<Message> ret = new ArrayList<>();
		for(Message message:messages.values()) {
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == year) {
				ret.add(message);
			}
		}
		return ret;
	}
	public List<Message> getMessageBySize(int start, int size) {
		return new ArrayList<Message>(messages.values()).subList(start,start+size);
	}
	
}
