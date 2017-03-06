package com.kimi.dao;

import java.util.List;
import java.util.Map;

import com.kimi.bean.Message;

public interface IMessage {
	/*
	 * ��ѯ���ݿ�
	 */
	public List<Message> queryMessageList(Map<String, Object> parameter);

	/*
	 * ��ҳ��ѯ���ݿ�
	 */
	public int count(Message message);

	/*
	 * ����ɾ��
	 */
	public void deleteOne(int id);

	/*
	 * ����ɾ��
	 */
	public void deleteBatch(List<Integer> ids);
}
