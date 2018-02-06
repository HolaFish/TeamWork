package com.heisenberg.common.tree.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.StringUtils;

import com.heisenberg.common.tree.model.Node;
import com.heisenberg.common.util.ComparatorNode;


public class TreeUtil {
	/**
	 * 
	 *
	 * @描述：TODO(解析森林)
	 *
	 * @param nodes
	 * @return
	 * Node
	 * @创建人  ：lrz
	 * @创建时间：2017年3月21日下午1:34:15
	 * @修改人  ：lrz
	 * @修改时间：2017年3月21日下午1:34:15
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public static Node[] analyzeForest(List<Node> nodes){
		/*
		 *	获取根节点
		 */
		List<Node> rootNodes = TreeUtil.getRootNodes(nodes);
		/*
		 * 递归获取子节点
		 */
		for (Node node : rootNodes){
			node = TreeUtil.analyzeTree(node, nodes);
		}
		return rootNodes.toArray(new Node[rootNodes.size()]);
	}
	
	/**
	 * 
	 *
	 * @描述：TODO(解析树)
	 *
	 * @param node
	 * @param nodes
	 * @return
	 * Node
	 * @创建人  ：lrz
	 * @创建时间：2017年3月21日下午1:53:35
	 * @修改人  ：lrz
	 * @修改时间：2017年3月21日下午1:53:35
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public static Node analyzeTree(Node node, List<Node> nodes){
		List<Node> childNode = new ArrayList<Node>();
		for(int i = 0; i < nodes.size(); i++){
			Node cNode = nodes.get(i);
			String parentId = cNode.getParentId()!=null?cNode.getParentId():"";
			if (node.getId().equals(parentId)){
				//递归查询子节点
				cNode = TreeUtil.analyzeTree(cNode, nodes);
				childNode.add(cNode);
			}
		}
		//排序
		ComparatorNode cn = new ComparatorNode();
		Collections.sort(childNode,cn);
		node.setChildren(childNode.toArray(new Node[childNode.size()]));
		node.setNodes(childNode.toArray(new Node[childNode.size()]));
		return node;
	}
	/**
	 * 
	 *
	 * @描述：TODO(获取根节点)
	 *
	 * @param nodeList
	 * @return
	 * List<Node>
	 * @创建人  ：lrz
	 * @创建时间：2017年3月21日下午1:35:04
	 * @修改人  ：lrz
	 * @修改时间：2017年3月21日下午1:35:04
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public static List<Node> getRootNodes(List<Node> nodes){
		List<Node> rootNodes = new ArrayList<Node>();
		for (Node node : nodes){
			/*
			 * 判断是否有父节点，如果没有，则为根节点
			 */
			if (StringUtils.isEmpty(node.getParentId())){
				rootNodes.add(node);
			}
		}
		return rootNodes;
	}
	/**
	 * 
	 *
	 * @描述：TODO(获取直系子节点)
	 *
	 * @param node
	 * @param nodes
	 * @return
	 * List<Node>
	 * @创建人  ：lrz
	 * @创建时间：2017年3月21日下午1:44:01
	 * @修改人  ：lrz
	 * @修改时间：2017年3月21日下午1:44:01
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public static Node[] getDirectChildNodes(Node node,List<Node> nodes){
		List<Node> directChildNodes = new ArrayList<Node>();
		/*
		 * 判断节点的父节点是否是指定节点的id
		 */
		for (Node childNode : nodes){
			if (node.getId().equals(childNode.getParentId())){
				directChildNodes.add(childNode);
			}
		}
		Node dcNodes[] = new Node[directChildNodes.size()];
		return directChildNodes.toArray(dcNodes); 
	}
	/**
	 * 
	 * @描述：TODO(获取某个节点的根节点)
	 * @param node
	 * @param noses
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月4日下午2:55:47
	 * @修改人  ：lrz
	 * @修改时间：2017年4月4日下午2:55:47
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public static Node getRootNode(Node node,List<Node> nodes){
		Node rootNode = new Node();
		for (Node parentNode : nodes){
			if (parentNode.getId().equals(node.getParentId())){
				if (StringUtils.isEmpty(parentNode.getParentId())){
					rootNode = parentNode;
					break;
				}else{
					rootNode = TreeUtil.getRootNode(parentNode,nodes);
				}
			}
		}
		return rootNode;
	}
	/**
	 * 
	 * @描述：TODO(获取某个节点的全部子节点,包含本身)
	 * @param node
	 * @param nodes
	 * @param childrenNodes
	 * @return
	 * @创建人  ：lrz
	 * @创建时间：2017年4月4日下午3:08:02
	 * @修改人  ：lrz
	 * @修改时间：2017年4月4日下午3:08:02
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	public static List<Node> getChildrenNodes(Node node,List<Node> nodes){
		List<Node> childrenNodes = new ArrayList<Node>();
		childrenNodes.add(node);
		for (Node childrenNode : nodes){
			if (!StringUtils.isEmpty(childrenNode.getParentId()) ){
				if (node.getId().equals(childrenNode.getParentId())){
					childrenNodes.addAll(TreeUtil.getChildrenNodes(childrenNode, nodes));
				}
			}
		}
		return childrenNodes;
	}
}
