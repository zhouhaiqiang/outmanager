package com.talkweb.ei.outmanager.model;

/**
 * Ê÷½Úµã
 * @author zhq
 *
 */
public class TreeNode {
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//id
	String id;	
	
	//parent id
	String pId;
	
	//display name
	String name;
}
