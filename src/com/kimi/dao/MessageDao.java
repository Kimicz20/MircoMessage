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
 * 数据库连接以及操作
 * @author geek
 *
 */

public class MessageDao {
	
	/**
	 * 查询
	 * @param command
	 * @param description
	 * @return
	 */
	public List<Message> queryMessage(Map<String,Object> parameter) {
		DBAccess access = new DBAccess();
		SqlSession sqlSession = null;
		List<Message> messageList = new ArrayList<Message>();		
		try {
			//1.获取SqlSession
			sqlSession = access.getSqlSession();
			//2.使用接口编程方式
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
	 * 单条删除
	 * @param id
	 */
	public void deleteOneMessage(int id){
		DBAccess access = new DBAccess();
		SqlSession sqlSession = null;
		try {
			//1.获取SqlSession
			sqlSession = access.getSqlSession();
			//2.执行SQL语句
//			sqlSession.delete("Message.deleteOne", id);
			//接口式编程
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			imessage.deleteOne(id);
			//3.事务提交
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
	 * 批量删除
	 * @param ids
	 */
	public void deleteBatch(List<Integer> ids){
		DBAccess access = new DBAccess();
		SqlSession sqlSession = null;
		try {
			//1.获取SqlSession
			sqlSession = access.getSqlSession();
			//2.执行SQL语句
//			sqlSession.delete("Message.deleteBatch", ids);
			//接口式编程
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			imessage.deleteBatch(ids);
			//3.事务提交
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
