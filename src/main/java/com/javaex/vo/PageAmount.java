package com.javaex.vo;

public class PageAmount {
	
	private int boardAmount; //게시글의 양
	private int page; //페이지
	private int pageNow; //현재페이지
	private int prev; //이전 페이지
	private int next; //다음페이지
	
	public PageAmount(int boardAmount, int page) {
		super();
		this.boardAmount = boardAmount;
		this.page = page;
	}
	public PageAmount() {
		super();
	}
	public int getBoardAmount() {
		return boardAmount;
	}
	public void setBoardAmount(int boardAmount) {
		this.boardAmount = boardAmount;
	}
	public int getPage() {
		pageAmount();
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	public void pageAmount() {
		int page = this.boardAmount/10;
		int pageRound = this.boardAmount%10;
		if(pageRound >0) {
			pageRound = 1;
		}
		this.page = page + pageRound;
	}
	
	public int getPrev() {
		return prev;
	}
	public void setPrev(int prev) {
		this.prev = prev;
	}
	public int getNext() {
		return next;
	}
	public void setNext(int next) {
		this.next = next;
	}
	@Override
	public String toString() {
		return "PageAmount [boardAmount=" + boardAmount + ", page=" + page + ", pageNow=" + pageNow + ", prev=" + prev
				+ ", next=" + next + "]";
	}
	
	
	
	
	
}
