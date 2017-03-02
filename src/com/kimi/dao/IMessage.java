package com.kimi.dao;

import java.util.List;
import com.kimi.bean.Message;

public interface IMessage {
	public List<Message> queryMessage(Message message);
	public void deleteOne(int id);
	public void deleteBatch(List<Integer> ids);
}
