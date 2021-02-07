package com.javaex.vo;

public class PageVo {
	private int pageNum;

	public PageVo() {
		super();
	}

	public PageVo(int pageNum) {
		super();
		this.pageNum = pageNum;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	@Override
	public String toString() {
		return "PageVo [pageNum=" + pageNum + "]";
	}

}
