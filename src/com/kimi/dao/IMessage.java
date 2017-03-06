package com.kimi.dao;

import java.util.List;
import java.util.Map;

import com.kimi.bean.Message;

public interface IMessage {
	/*
	 * 查询数据库
	 */
	public List<Message> queryMessageList(Map<String, Object> parameter);

	/*
	 * 分页查询数据库
	 */
	public int count(Message message);

	/*
	 * 单条删除
	 */
	public void deleteOne(int id);

	/*
	 * 批量删除
	 */
	public void deleteBatch(List<Integer> ids);
}
