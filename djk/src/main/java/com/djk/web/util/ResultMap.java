package com.djk.web.util;

public class ResultMap {

	private String code;
	private String msg;
	
	
	public ResultMap() {
		super();
		this.code="0";
		this.msg="操作失败，请联系管理员";
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}