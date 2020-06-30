package com.djk.common;

import java.util.Date;

public abstract class DataModel<T> extends BaseModel<T> {

	private static final long serialVersionUID = 1L;

	/** 搜索值 */
    private String searchValue;
    
    /**删除标识(0:正常,1:删除) */
	private String delFlag;

	private java.lang.Integer createBy;
 	private java.util.Date createDate;
 	private java.lang.Integer updateBy;
 	private java.util.Date updateDate;
 	private java.lang.String remarks;
	
	
	
    /**
	 * @return the createBy
	 */
	public java.lang.Integer getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy the createBy to set
	 */
	public void setCreateBy(java.lang.Integer createBy) {
		this.createBy = createBy;
	}

	/**
	 * @return the createDate
	 */
	public java.util.Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the updateBy
	 */
	public java.lang.Integer getUpdateBy() {
		return updateBy;
	}

	/**
	 * @param updateBy the updateBy to set
	 */
	public void setUpdateBy(java.lang.Integer updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * @return the updateDate
	 */
	public java.util.Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the remarks
	 */
	public java.lang.String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(java.lang.String remarks) {
		this.remarks = remarks;
	}

	public String getSearchValue()
    {
        return searchValue;
    }

    public void setSearchValue(String searchValue)
    {
        this.searchValue = searchValue;
    }

   
	

    public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	////////////////////////////////////////////////
    public DataModel() {
		super();
		this.delFlag="0";
	}
	public DataModel(java.lang.Integer id) {
		super(id);
	}

	
	/**
	 * 插入之前执行的方法，需要手动调用
	 */
	@Override
	public void preInsert() {
		
		
		/*this.updateBy=ShiroUtils.getUserId().toString();
		this.createBy=ShiroUtils.getUserId().toString();*/
		this.updateBy=1;
		this.createBy=1;
		this.updateDate=new Date();
		this.createDate=this.updateDate;

	}
	/**
	 * 更新之前执行方法，需要手动调用
	 */
	@Override
	public void preUpdate(){
		//获取系统缓存里的User
		/*this.updateBy=ShiroUtils.getUserId().toString();*/
		this.updateBy=1;
        this.updateDate=new Date();
		
	}

}
