package com.heisenberg.base.dao;



import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public interface SpringJDBCBaseDao {
	/**
	 * 
	 *
	 * @描述：TODO(插入一条数据，sql中的字段位置要和params中值得位置对应)
	 *
	 * @param sql sql语句
	 * @param params 字段值
	 * @return
	 * int 插入条数
	 * @创建人  ：lrz
	 * @创建时间：2017年2月10日下午3:30:37
	 * @修改人  ：lrz
	 * @修改时间：2017年2月10日下午3:30:37
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public int insert(String sql, Object[] params);
	/**
	 * 
	 *
	 * @描述：TODO(删除记录)
	 *
	 * @param sql 
	 * @param Params 
	 * @return
	 * int 删除的条数
	 * @创建人  ：lrz
	 * @创建时间：2017年2月10日下午3:33:38
	 * @修改人  ：lrz
	 * @修改时间：2017年2月10日下午3:33:38
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public int delete(String sql, Object[] params);
	/**
	 * 
	 *
	 * @描述：TODO(更新数据)
	 *
	 * @param sql
	 * @param params
	 * @return
	 * int 更新条数
	 * @创建人  ：lrz
	 * @创建时间：2017年2月10日下午3:39:19
	 * @修改人  ：lrz
	 * @修改时间：2017年2月10日下午3:39:19
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public int update(String sql, Object[] params);
	/**
	 * 
	 *
	 * @描述：TODO(按条件查询所有记录，并转化为对象存放在列表中)
	 *
	 * @param sql
	 * @param params 查询参数
	 * @param rm 关系对象映射
	 * @return
	 * List
	 * @创建人  ：lrz
	 * @创建时间：2017年2月10日下午3:37:21
	 * @修改人  ：lrz
	 * @修改时间：2017年2月10日下午3:37:21
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	public List queryToList(String sql, Object[] params,RowMapper rm);
	/**
	 * 
	 *
	 * @描述：TODO(查询)
	 *
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * Object
	 * @创建人  ：lrz
	 * @创建时间：2017年2月10日下午4:07:03
	 * @修改人  ：lrz
	 * @修改时间：2017年2月10日下午4:07:03
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public Object queryUniqueObject(String sql, Object[] params, Class<?> cls);
	/**
	 * 
	 *
	 * @描述：TODO(将查询结果转化为map，并存放在list中返回)
	 *
	 * @param sql
	 * @param params
	 * @param paramsType
	 * @return
	 * List<Map<String,Object>>
	 * @创建人  ：lrz
	 * @创建时间：2017年3月27日上午10:31:57
	 * @修改人  ：lrz
	 * @修改时间：2017年3月27日上午10:31:57
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public List<Map<String,Object>> queryMapInList(String sql, Object[] params);
}
