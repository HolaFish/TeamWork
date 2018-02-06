package com.heisenberg.base.model;

import com.heisenberg.common.tree.model.Node;

public class Team extends Node{
	private String id;
	private String leaderId;
	private String parentId;
	private String name;
	private int teamLevel;
	private String teamCode;
	private int sort;
	private String remark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getTeamLevel() {
		return teamLevel;
	}
	public void setTeamLevel(int teamLevel) {
		this.teamLevel = teamLevel;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
