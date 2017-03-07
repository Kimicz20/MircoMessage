package com.kimi.dao;

import java.io.IOException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.kimi.bean.Message;
import com.kimi.db.DBAccess;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
/**
 * ���ݿ������Լ�����
 * @author geek
 *
 */

public class MessageDao {
	
	/**
	 * ��ѯ
	 * @param command
	 * @param description
	 * @return
	 */
	public List<Message> queryMessage(Map<String,Object> parameter) {
		DBAccess access = new DBAccess();
		SqlSession sqlSession = null;
		List<Message> messageList = new ArrayList<Message>();		
		try {
			//1.��ȡSqlSession
			sqlSession = access.getSqlSession();
			//2.ʹ�ýӿڱ�̷�ʽ
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			messageList = imessage.queryMessageListByPage(parameter);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(sqlSession !=null){
				sqlSession.close();
			}
		}
		return messageList;
	}
	/**
	 * ����ɾ��
	 * @param id
	 */
	public void deleteOneMessage(int id){
		DBAccess access = new DBAccess();
		SqlSession sqlSession = null;
		try {
			//1.��ȡSqlSession
			sqlSession = access.getSqlSession();
			//2.ִ��SQL���
//			sqlSession.delete("Message.deleteOne", id);
			//�ӿ�ʽ���
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			imessage.deleteOne(id);
			//3.�����ύ
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(sqlSession !=null){
				sqlSession.close();
			}
		}
	}
	/**
	 * ����ɾ��
	 * @param ids
	 */
	public void deleteBatch(List<Integer> ids){
		DBAccess access = new DBAccess();
		SqlSession sqlSession = null;
		try {
			//1.��ȡSqlSession
			sqlSession = access.getSqlSession();
			//2.ִ��SQL���
//			sqlSession.delete("Message.deleteBatch", ids);
			//�ӿ�ʽ���
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			imessage.deleteBatch(ids);
			//3.�����ύ
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(sqlSession !=null){
				sqlSession.close();
			}
		}
	}
	
}
