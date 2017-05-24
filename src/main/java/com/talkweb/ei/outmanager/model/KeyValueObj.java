package com.talkweb.ei.outmanager.model;

/**简单的键值对对象，用于展示字典和下拉框
 * 
 * 
 * @author zhq
 *
 */
public class KeyValueObj {
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String code;
	
	private String name;
	

}
