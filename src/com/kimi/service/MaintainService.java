package com.kimi.service;

import java.util.ArrayList;
import java.util.List;

import com.kimi.dao.MessageDao;

/**
 * ����ҵ�����
 * 
 * @author geek
 *
 */
public class MaintainService {

	/**
	 * ����ɾ��
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
	 * ҳ�洫�ݵĶ�ѡ������
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
