package com.heisenberg.base.model;

import java.util.List;

public class ProjectDetail {
	private String id;
	private String name;
	private String leaderid;
	private String description;
	private String startTime;
	private String endTime;
	private String updateTime;
	private String isOngoing;
	private List<TaskGroup> taskGroups;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLeaderid() {
		return leaderid;
	}
	public void setLeaderid(String leaderid) {
		this.leaderid = leaderid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getIsOngoing() {
		return isOngoing;
	}
	public void setIsOngoing(String isOngoing) {
		this.isOngoing = isOngoing;
	}
	public List<TaskGroup> getTaskGroups() {
		return taskGroups;
	}
	public void setTaskGroups(List<TaskGroup> taskGroups) {
		this.taskGroups = taskGroups;
	}
	
}
