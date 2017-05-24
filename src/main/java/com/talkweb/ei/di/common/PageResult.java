package com.talkweb.ei.di.common;

/**
 * 查询分页列表封装json对象，所有返回结果都使用它
 */
public class PageResult<List> {

	private boolean success;// 是否成功标志

	//行数据
	private List rows;// 成功时返回的数据
	
	//结果集总数
	private int total = 0;

	private String error;// 错误信息

	public PageResult() {
	}

	// 成功时的构造器
	public PageResult(boolean success, List data,int total) {
		this.success = success;
		this.setRows(data);
		this.setTotal(total); 
	}

	// 错误时的构造器
	public PageResult(boolean success, String error) {
		this.success = success;
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		
		String str = JsonUtil.toJson(this);
		System.out.println("page data ====== "+str);
		return str;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}	
}