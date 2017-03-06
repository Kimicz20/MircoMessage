package com.kimi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kimi.bean.Message;
import com.kimi.dao.MessageDao;
import com.kimi.entity.Page;
/**
 * ��ѯҳ�������
 * @author geek
 *
 */
public class QueryService {
	
	/**
	 * ��ҳ��ѯ
	 * @param command
	 * @param description
	 * @param page
	 * @return
	 */
	public List<Message> queryMessageWithPage(String command, String description,Page page) {
		MessageDao dao = new MessageDao();
		//1.������Ϣ����
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		//2.�����ѯ����
		int totalNumber = dao.count(message);
		//3.��֯��ҳ��ѯ����
		page.setTotalNumber(totalNumber);
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("message", message);
		parameter.put("page", page);
		return dao.queryMessage(parameter);
	}
	
//	public String autoReply(String command){
//		MessageDao dao = new MessageDao();
//		List<Message> list = dao.queryMessage(command,null);
//		if(list.size() >0){
//			return list.get(0).getContent();
//		}
//		
//		return "";
//	}

}
