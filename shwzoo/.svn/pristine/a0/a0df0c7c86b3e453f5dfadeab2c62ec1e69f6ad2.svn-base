package com.yinlian.wssc.web.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

/**
 * PageInterceptor.class
 * 
 * @author massssll
 * 
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PageInterceptor implements Interceptor {
	private static final Log logger = LogFactory.getLog(PageInterceptor.class);
	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	private static String defaultDialect = "mysql";
	private static String defaultPageSqlId = ".*Page$";
	private static String dialect = "mysql";
	private static String pageSqlId = "";

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY,
				DEFAULT_OBJECT_WRAPPER_FACTORY);
		while (metaStatementHandler.hasGetter("h")) {
			Object object = metaStatementHandler.getValue("h");
			metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		}
		while (metaStatementHandler.hasGetter("target")) {
			Object object = metaStatementHandler.getValue("target");
			metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		}
		Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
		dialect = configuration.getVariables().getProperty("dialect");
		if (null == dialect || "".equals(dialect)) {
			logger.warn("Property dialect is not setted,use default 'mysql' ");
			dialect = defaultDialect;
		}
		pageSqlId = configuration.getVariables().getProperty("pageSqlId");
		if (null == pageSqlId || "".equals(pageSqlId)) {
			logger.warn("Property pageSqlId is not setted,use default '.*Page$' ");
			pageSqlId = defaultPageSqlId;
		}
		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");

		if (mappedStatement.getId().matches(pageSqlId)) {
			BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
			Object parameterObject = boundSql.getParameterObject();
			if (parameterObject == null) {
				throw new NullPointerException("parameterObject is null!");
			} else {
				PageBean page = (PageBean) metaStatementHandler.getValue("delegate.boundSql.parameterObject.page");
				String iSortCol = (String) metaStatementHandler
						.getValue("delegate.boundSql.parameterObject.criteria.orderByClause");
				String sSortDir = (String) metaStatementHandler
						.getValue("delegate.boundSql.parameterObject.criteria.sort");
				page.setiSortCol(iSortCol);
				page.setsSortDir(sSortDir);

				Connection connection = (Connection) invocation.getArgs()[0];
				String sql = boundSql.getSql();
				String pageSql = buildPageSql(sql, page);
				metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
				metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
				metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
				if (!mappedStatement.getId().toLowerCase().matches(".*nocount.*")) {
					setPageParameter(sql, connection, mappedStatement, boundSql, page);
				}
			}
		}

		return invocation.proceed();
	}

	/**
	 * 从数据库里查询总的记录数并计算总页数，回写进分页参数<code>PageParameter</code>,这样调用者就可用通过 分页参数
	 * <code>PageParameter</code>获得相关信息。
	 * 
	 * @param sql
	 * @param connection
	 * @param mappedStatement
	 * @param boundSql
	 * @param page
	 */
	private void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement, BoundSql boundSql,
			PageBean page) {

		String templsql = sql.toLowerCase();
		Pattern p = Pattern.compile("select", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(templsql);
		int count = 0;
		while (m.find()) {
			count++;
		}
		String countSql = "";
		if (count >= 2) {
			// 记录总记录数
			countSql = "select count(*) from (" + sql + ") as total";
		} else {
			countSql = sql.substring(templsql.indexOf("from"), templsql.length());
			p = Pattern.compile("order\\s+by", Pattern.CASE_INSENSITIVE);
			m = p.matcher(countSql);
			int orderindex = 0;
			if (m.find()) {
				orderindex = m.start();
			}
			/*
			 * p = Pattern.compile("group\\s+by" ,Pattern.CASE_INSENSITIVE); m =
			 * p.matcher(countSql);
			 */
			int groupindex = 0;
			/*
			 * if(m.find()){ groupindex= m.start(); }
			 */
			if (orderindex > groupindex && groupindex != 0) {
				countSql = countSql.substring(0, groupindex);
			} else if (orderindex != 0) {
				countSql = countSql.substring(0, orderindex);
			}
			p = Pattern.compile("group\\s+by", Pattern.CASE_INSENSITIVE);
			m = p.matcher(countSql);
			if (m.find()) {
				groupindex = m.start();
				countSql = "select count(1) from (select  1  " + countSql + ") as total";
			} else {
				countSql = "select count(1)  " + countSql;
			}
		}
		// 2000
		// String countSql = "select count(0) from ( select a.id from(" + sql +
		// ")a join ("+sql+")b on a.id=b.id ) as total";
		PreparedStatement countStmt = null;

		ResultSet rs = null;
		try {
			countStmt = connection.prepareStatement(countSql);

			BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,
					boundSql.getParameterMappings(), boundSql.getParameterObject());
			setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());
			rs = countStmt.executeQuery();
			int totalCount = 0;
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
			page.setTr(totalCount);
			int totalPage = totalCount / page.getPs() + ((totalCount % page.getPs() == 0) ? 0 : 1);
			page.setTp(totalPage);

		} catch (SQLException e) {
			logger.error("Ignore this exception", e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("Ignore this exception", e);
			}
			try {
				countStmt.close();
			} catch (SQLException e) {
				logger.error("Ignore this exception", e);
			}
		}

	}

	/**
	 * 对SQL参数(?)设值 org.apache.ibatis.executor.parameter.DefaultParameterHandler
	 * 
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws SQLException
	 */
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
			Object parameterObject) throws SQLException {
		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
		parameterHandler.setParameters(ps);

	}

	/**
	 * 根据数据库类型，生成特定的分页sql
	 * 
	 * @param sql
	 * @param page
	 * @return
	 */
	private String buildPageSql(String sql, PageBean page) {
		if (page != null) {
			String pageSql = null;
			if ("mysql".equals(dialect)) {
				pageSql = buildPageSqlForMysql(sql, page);
			} else if ("oracle".equals(dialect)) {
				pageSql = buildPageSqlForOracle(sql, page);
			} else if ("sql2000".equals(dialect)) {
				pageSql = buildPageSqlForSqlServer2000(sql, page);
			} else if ("sql2005".equals(dialect)) {
				pageSql = buildPageSqlForSqlServer2005(sql, page);
			} else {
				return sql;
			}
			return pageSql;
		} else {
			return sql;
		}
	}

	/**
	 * mysql的分页语句
	 * 
	 * @param sql
	 * @param page
	 * @return String
	 */
	public String buildPageSqlForMysql(String sql, PageBean page) {
		StringBuilder pageSql = new StringBuilder(100);
		Integer beginrow = (page.getPc() - 1) * page.getPs();
		pageSql.append(sql);
		pageSql.append(" limit " + beginrow + "," + page.getPs());
		return pageSql.toString();
	}

	/**
	 * sql2005-2008 的分页 @Title: buildPageSqlForSqlServer2005 @Description:
	 * TODO() @param @param sql @param @param page @param @return 设定文件 @return
	 * String 返回类型 @throws
	 */
	public String buildPageSqlForSqlServer2005(String sql, PageBean page) {
		String beginrow = String.valueOf((page.getPc() - 1) * page.getPs());
		String bsql = "select * from(select a.*,row_number() over (order by " + page.getiSortCol() + " "
				+ page.getsSortDir()// 排序
				+ " ) rownum from( " + sql + ") a )b where rownum> " + beginrow + " and rownum <= "
				+ (beginrow + page.getPs());
		return bsql;
	}

	//

	/**
	 * sql 2000的分页 @Title: buildPageSqlForSqlServer2000 @Description:
	 * TODO() @param @param sql @param @param page @param @return 设定文件 @return
	 * String 返回类型 @throws
	 */
	public String buildPageSqlForSqlServer2000(String sql, PageBean page) {
		String beginrow = String.valueOf((page.getPc() - 1) * page.getPs());

		return "select * from (select a.*,rownum=(select count(1) from (" + sql + ")c where c.id<a.id)+1 from(" + sql
				+ ")a)b  where rownum> " + beginrow + " and rownum <= " + (beginrow + page.getPs());
	}

	/**
	 * 参考hibernate的实现完成oracle的分页
	 * 
	 * @param sql
	 * @param page
	 * @return String
	 */
	public String buildPageSqlForOracle(String sql, PageBean page) {
		StringBuilder pageSql = new StringBuilder(100);
		String beginrow = String.valueOf((page.getPc() - 1) * page.getPs());
		String endrow = String.valueOf(page.getPc() * page.getPs());

		pageSql.append("select * from ( select temp.*, rownum row_id from ( ");
		pageSql.append(sql);
		pageSql.append(" ) temp where rownum <= ").append(endrow);
		pageSql.append(") where row_id > ").append(beginrow);
		return pageSql.toString();
	}

	// select * from (select temp.*,rownum row_id from ( select * from book
	// where bname like '%java web%' ) temp where rownum <= ? ) where row_id > ?

	@Override
	public Object plugin(Object target) {
		// 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties properties) {
	}

	public static void main(String[] args) {

		System.out.println("com.yinlian.wssc.web.mapper.OrdersMapper.getUserListOrderNopayPage".matches(".*Nopay.*"));
	}

}
