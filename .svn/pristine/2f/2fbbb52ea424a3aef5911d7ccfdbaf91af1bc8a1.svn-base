package com.heisenberg.base.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heisenberg.base.dao.SpringJDBCBaseDao;
import com.heisenberg.base.model.Schedule;
import com.heisenberg.base.service.ScheduleService;
import com.heisenberg.common.resultmodel.BaseResult;
import com.heisenberg.common.util.ObjectRelationalMapper;
@Service
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	private SpringJDBCBaseDao baseDao;
	/**
	 * 
	 * @描述：TODO(添加日程)
	 * @param schedule
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午7:41:05
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午7:41:05
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult addSchedule(Schedule schedule) {
		String id = UUID.randomUUID().toString();
		String Sql = " insert into tb_schedule(id,title,allDay,\"start\",end,userId)values(?,?,?,?,?,?)";
		baseDao.insert(Sql, new Object[]{id,schedule.getTitle(),schedule.getAllDay(),schedule.getStart(),schedule.getEnd(),schedule.getUserId()});
		return new BaseResult("0","成功");
	}
	/**
	 * 
	 * @描述：TODO(获取日程)
	 * @param userId
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年5月14日上午7:41:02
	 * @修改人  ：lrz
	 * @修改时间：2017年5月14日上午7:41:02
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Schedule> getSchedule(String userId) {
		String Sql = " select * from tb_schedule where userId=? ";
		List<Schedule> result= baseDao.queryToList(Sql, new Object[]{userId}, new ObjectRelationalMapper("com.heisenberg.base.model.Schedule"));
		return result;
	}
	@Override
	public Map<String, Object> getSchedleById(String id) {
		String Sql = " select id,title ,\"start\", end from tb_schedule where id = ? ";
		List<Map<String,Object>> result = baseDao.queryMapInList(Sql, new Object[]{id});
		if (result.size() > 0){
			return result.get(0);
		}
		return null;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult modifySchedule(Schedule schedule) {
		BaseResult result = new BaseResult();
		String Sql = " update tb_schedule set title=?, \"start\"=?, end=? where id=? ";
		int count = baseDao.update(Sql, new Object[]{schedule.getTitle(),schedule.getStart(),schedule.getEnd(),schedule.getId()});
		if (count > 0 ){
			result.setMessage("成功");
			result.setResultType("0");
			return result;
		}else{
			result.setMessage("出现未知错误，请重试！");
			result.setResultType("2");
			return result;
		}
		
	}
	@SuppressWarnings("rawtypes")
	@Override
	public BaseResult deleteSchedule(Schedule schedule) {
		BaseResult result = new BaseResult();
		String Sql = " delete from tb_schedule where id=? ";
		int count = baseDao.delete(Sql, new Object[]{schedule.getId()});
		if (count > 0 ){
			result.setMessage("成功");
			result.setResultType("0");
			return result;
		}else{
			result.setMessage("出现未知错误，请重试！");
			result.setResultType("2");
			return result;
		}
	}

	
}
