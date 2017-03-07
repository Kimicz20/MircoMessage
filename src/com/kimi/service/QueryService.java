package com.kimi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kimi.bean.Message;
import com.kimi.dao.MessageDao;
import com.kimi.entity.Page;
/**
 * 查询页面服务类
 * @author geek
 *
 */
public class QueryService {
	
	/**
	 * 分页查询
	 * @param command
	 * @param description
	 * @param page
	 * @return
	 */
	public List<Message> queryMessageWithPage(String command, String description,Page page) {
		MessageDao dao = new MessageDao();
		//1.构建消息对象
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		Map<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("message", message);
		parameter.put("page", page);
		return dao.queryMessage(parameter);
	}
	
}
