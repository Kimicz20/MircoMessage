package com.kimi.service;

import java.util.List;

import com.kimi.bean.Message;
import com.kimi.dao.MessageDao;
/**
 * ��ѯҳ�������
 * @author geek
 *
 */
public class ListService {
	
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

}
