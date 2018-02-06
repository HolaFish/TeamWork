package com.heisenberg.common.tree.model;

public class Node {
	private String id;
	private String parentId;
	private Node[] children;
	private Node[] Nodes;
	private String href;
	private int sort;
	public Node() {
		super();
	}
	
	public Node(String id, String parentId, Node[] children) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.children = children;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Node[] getChildren() {
		return children;
	}

	public void setChildren(Node[] children) {
		this.children = children;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Node[] getNodes() {
		return Nodes;
	}

	public void setNodes(Node[] nodes) {
		Nodes = nodes;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}


}