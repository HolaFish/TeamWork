package com.heisenberg.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 将查询结果转为对象
 * bean中的各ge tter和setter方法中，属性首字母大写，数据库中的字段与bean中属性名相同
 * @author 刘润枝
 *
 */

@SuppressWarnings("rawtypes")
public class ObjectRelationalMapper implements RowMapper{
	private String className;//需要映射的对象名 	
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public ObjectRelationalMapper(String className) {
		super();
		this.className = className;
	}


	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		try {
			//实例化映射对象
			Class<?> clazz = Class.forName(this.getClassName());
			Object obj = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			
			//遍历属性，并调用set方法，为对象属性赋值
			for (Field field: fields){
				 String name = field.getName();
		         String methodStr = "set"+name.toUpperCase().substring(0, 1)+name.substring(1);
		         Method method;
				try {
					method = clazz.getMethod(methodStr,new Class[]{field.getType()});
					String paramType = field.getType().getName();
					switch (paramType) {
					case "java.lang.String":
						method.invoke(obj, rs.getString(name));
						break;
					case "int":
						method.invoke(obj, rs.getInt(name));
						break;
					default:
						break;
						
					}
				
				} catch (NoSuchMethodException e) {
					
					e.printStackTrace();
				} catch (SecurityException e) {
					
					e.printStackTrace();
				}
		   }
			return obj;
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			
			e.printStackTrace();
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
}
