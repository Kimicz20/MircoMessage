package com.kimi.service;

import java.util.ArrayList;
import java.util.List;

import com.kimi.dao.MessageDao;

/**
 * 所有业务操作
 * 
 * @author geek
 *
 */
public class MaintainService {

	/**
	 * 单条删除
	 * 
	 * @param id
	 */
	public void deleteOneMessage(String id) {
		MessageDao messageDao = new MessageDao();
		if (id != null && !"".equals(id.trim())) {
			messageDao.deleteOneMessage(Integer.valueOf(id));
		}
	}

	/**
	 * 页面传递的多选择数组
	 * 
	 * @param ids
	 */
	public void deleteBatch(String[] ids) {
		MessageDao messageDao = new MessageDao();
		List<Integer> idsList = new ArrayList<Integer>();

		for (String id : ids) {
			System.out.println(id);
			idsList.add(Integer.valueOf(id));
		}
		messageDao.deleteBatch(idsList);
	}
}
