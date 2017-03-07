package com.kimi.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.kimi.bean.Message;
import com.kimi.entity.Page;

@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class PageInterceptor implements Interceptor {

	/*
	 * ���ع���
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		/*
		 * ��ȡMybatis�����ļ���SQL����ID ͨ��ID��������Ҫ���ص�SQL���
		 */
		// 1.��ȡStatementHandler
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		// 2.ͨ��MetaObject��������ȡһЩ�������͵Ĳ���
		MetaObject metaObject = (MetaObject) MetaObject.forObject(statementHandler,
				SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
				new DefaultReflectorFactory());
		// 3.��ȡӵ��SQL����ID��MappedStatement����
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		// 4.��ȡSQL����ID ��ͨ��ID��ɸѡ ���ض���
		String id = mappedStatement.getId();

		/*
		 * ͨ��ID��������Ҫ���ص�SQL���
		 */
		if (id.matches(".+ByPage$")) {

			BoundSql boundSql = statementHandler.getBoundSql();

			// 1.��ȡԭʼSQL���
			String sql = boundSql.getSql();
			// 2.��ȡ����
			String countSql = "select count(*) from (" + sql + ")a";
			// 2.1ͨ��connection��ִ�����
			Connection conn = (Connection) invocation.getArgs()[0];
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(countSql);
			
			// 2.2��ȡ����&&���ò���
			/*
			 * ����һ:ͨ��ParameterHandler������
			 */
			ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
			// ��ȡ���еĲ����������ı�ʾ����Map��ʽ
			Map<String, Object> params = (Map<String, Object>) parameterHandler.getParameterObject();
			/*
			 * ����OGNL����ӳ�����������ȡ��������
			 * 		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
			 * 		String propertyName = parameterMapping.getProperty();
			 * ��ͨ��MetaObject,���ոù����ȡ�������ֵ
			 * 		value = metaObject.getValue(propertyName);
			 * ���ͨ��JDBC��PreparedStatement��setString()һ��
			 * 		typeHandler.setParameter(ps, i + 1, value, jdbcType);
			 */
			parameterHandler.setParameters(ps);
			
			//��ʽ ��:ͨ��BoundSql������
//			Map<String, Object> params = (Map<String, Object>) boundSql.getParameterObject();
//			Message message = (Message)params.get("message");
//			List<String> paramList = new ArrayList<String>();
//			if(message.getCommand() !=null && !("".equals(message.getCommand()))){
//				paramList.add(message.getCommand());
//			}
//			if(message.getDescription() !=null && !("".equals(message.getDescription()))){
//				paramList.add(message.getDescription());
//			}
//			for (int i = 0; i < paramList.size(); i++) {
//				ps.setString(i + 1, paramList.get(i));
//			}
			
			Page page = (Page) params.get("page");

			// 2.3ִ�в�ѯ��ȡ������ &&��ֵ��Page����
			ResultSet rs = (ResultSet) ps.executeQuery();

			if (rs.next()) {
				page.setTotalNumber(rs.getInt(1));
			}

			/*
			 * ������ҳ��ѯ���
			 */
			String pageSql = sql + " limit " + page.getDbIndex() + "," + page.getDbNumber();

			/*
			 * �滻SQL���
			 */
			metaObject.setValue("delegate.boundSql.sql", pageSql);

		}
		/*
		 * ִ�����ض���ķ���
		 */
		return invocation.proceed();
	}

	/*
	 * ע��������
	 */
	@Override
	public Object plugin(Object target) {
		/*
		 * ���������StatementHandler��ʵ������ͨ��Statementִ����SQL�����������ݿ�
		 */
		if (target instanceof StatementHandler) {
			/*
			 * ��������ע�ᵽ�ö����ϣ���ִ�ж��󷽷�ǰ�ᱻ���������أ���ִ����������intercept����
			 */
			return Plugin.wrap(target, this);
		}
		return target;
	}

	/*
	 * ��ȡһЩ������Ϣ
	 */
	@Override
	public void setProperties(Properties properties) {
	}

}
