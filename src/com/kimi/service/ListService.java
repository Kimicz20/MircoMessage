package com.kimi.service;

import java.util.List;

import com.kimi.bean.Message;
import com.kimi.dao.MessageDao;
/**
 * 查询页面服务类
 * @author geek
 *
 */
public class ListService {
	
	/**
	 * 查询信息
	 * @param command 命令
	 * @param description 描述
	 * @return 
	 */
	public List<Message> queryMessage(String command, String description) {
		MessageDao dao = new MessageDao();
		return dao.queryMessage(command, description);
	}

}
