package com.kimi.service;

import java.util.List;

import com.kimi.bean.Message;
import com.kimi.dao.MessageDao;
/**
 * ��ѯҳ�������
 * @author geek
 *
 */
public class QueryService {
	
	/**
	 * ��ѯ��Ϣ
	 * @param command ����
	 * @param description ����
	 * @return 
	 */
	public List<Message> queryMessage(String command, String description) {
		MessageDao dao = new MessageDao();
		return dao.queryMessage(command, description);
	}
	
	public String autoReply(String command){
		MessageDao dao = new MessageDao();
		List<Message> list = dao.queryMessage(command,null);
		if(list.size() >0){
			return list.get(0).getContent();
		}
		
		return "";
	}

}
