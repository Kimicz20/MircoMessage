package com.kimi.service;

import com.kimi.dao.MessageDao;

public class DeleteOneService {
	public void deleteOneMessage(String id){
		MessageDao messageDao  = new MessageDao();
		if(id != null && !"".equals(id.trim())){
			messageDao.deleteOneMessage(Integer.valueOf(id));
		}
	}
}
