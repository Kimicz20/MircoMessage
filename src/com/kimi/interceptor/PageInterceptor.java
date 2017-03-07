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
	 * 拦截功能
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		/*
		 * 获取Mybatis配置文件中SQL语句的ID 通过ID来过滤需要拦截的SQL语句
		 */
		// 1.获取StatementHandler
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		// 2.通过MetaObject对象来获取一些保护类型的参数
		MetaObject metaObject = (MetaObject) MetaObject.forObject(statementHandler,
				SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
				new DefaultReflectorFactory());
		// 3.获取拥有SQL语句的ID的MappedStatement对象
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		// 4.获取SQL语句的ID 并通过ID来筛选 拦截动作
		String id = mappedStatement.getId();

		/*
		 * 通过ID来过滤需要拦截的SQL语句
		 */
		if (id.matches(".+ByPage$")) {

			BoundSql boundSql = statementHandler.getBoundSql();

			// 1.获取原始SQL语句
			String sql = boundSql.getSql();
			// 2.获取总数
			String countSql = "select count(*) from (" + sql + ")a";
			// 2.1通过connection来执行语句
			Connection conn = (Connection) invocation.getArgs()[0];
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(countSql);
			
			// 2.2获取参数&&设置参数
			/*
			 * 方法一:通过ParameterHandler来设置
			 */
			ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
			// 获取所有的参数，参数的表示采用Map形式
			Map<String, Object> params = (Map<String, Object>) parameterHandler.getParameterObject();
			/*
			 * 根据OGNL规则映射情况解析获取参数名称
			 * 		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
			 * 		String propertyName = parameterMapping.getProperty();
			 * 再通过MetaObject,按照该规则获取具体参数值
			 * 		value = metaObject.getValue(propertyName);
			 * 最后通过JDBC的PreparedStatement中setString()一样
			 * 		typeHandler.setParameter(ps, i + 1, value, jdbcType);
			 */
			parameterHandler.setParameters(ps);
			
			//方式 二:通过BoundSql来设置
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

			// 2.3执行查询获取总条数 &&赋值给Page对象
			ResultSet rs = (ResultSet) ps.executeQuery();

			if (rs.next()) {
				page.setTotalNumber(rs.getInt(1));
			}

			/*
			 * 构建分页查询语句
			 */
			String pageSql = sql + " limit " + page.getDbIndex() + "," + page.getDbNumber();

			/*
			 * 替换SQL语句
			 */
			metaObject.setValue("delegate.boundSql.sql", pageSql);

		}
		/*
		 * 执行拦截对象的方法
		 */
		return invocation.proceed();
	}

	/*
	 * 注册拦截器
	 */
	@Override
	public Object plugin(Object target) {
		/*
		 * 如果对象是StatementHandler的实例，即通过Statement执行了SQL语句操作了数据库
		 */
		if (target instanceof StatementHandler) {
			/*
			 * 将拦截器注册到该对象上，在执行对象方法前会被拦截器拦截，并执行拦截器的intercept方法
			 */
			return Plugin.wrap(target, this);
		}
		return target;
	}

	/*
	 * 获取一些配置信息
	 */
	@Override
	public void setProperties(Properties properties) {
	}

}
