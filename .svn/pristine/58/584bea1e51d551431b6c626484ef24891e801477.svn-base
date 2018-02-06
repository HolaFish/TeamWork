package com.heisenberg.base.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.heisenberg.base.dao.SpringJDBCBaseDao;
@Repository
public class SpringJDBCBaseDaoImpl implements SpringJDBCBaseDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
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
	@Override
	public int insert(String sql, Object[] params) {
		
		return this.update(sql, params);
	}
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
	@Override
	public int delete(String sql, Object[] params) {
		
		return this.update(sql, params);
	}
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
	@Override
	public int update(String sql, Object[] params) {
		int count = jdbcTemplate.update(sql, params);
		return count;
	}
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List queryToList(String sql, Object[] params, RowMapper rm) {
	
		return jdbcTemplate.query(sql, params, rm);
	}
	/**
	 * 
	 *
	 * @描述：TODO(查询一个单独的对象)
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
	@Override
	public Object queryUniqueObject(String sql, Object[] params, Class<?> cls){
		return jdbcTemplate.queryForObject(sql, cls, params);
	}
	/**
	 * 
	 * @描述：TODO(将结果转化为map并存放在list中)
	 * @param sql
	 * @param params
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年3月27日上午10:26:30
	 * @修改人  ：lrz
	 * @修改时间：2017年3月27日上午10:26:30
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public List<Map<String,Object>> queryMapInList(String sql, Object[] params) {
		return jdbcTemplate.queryForList(sql, params);
	}
	
}
