package com.javaex.vo;

public class BoardVo {
	//필드
	private int no;
	private String title;
	private String content;
	private int hit;
	private String regDate;
	private int userNo;
	
	//user
	private String name;
	
	//rownum
	private int rowNo;
	
	//page
	private int firstNum;
	private int lastNum;

	
	//생성자
	
	public BoardVo() {
		this.firstNum = 1;
		this.lastNum = 10;
	}
	
	public BoardVo(int no, String title, String content, int hit, String regDate, int userNo) {
		
		this.no = no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.regDate = regDate;
		this.userNo = userNo;
	}
	
	public BoardVo(int no, String title, String content, int hit, String regDate, int userNo, String name, int rowNo,
			int firstNum, int lastNum) {
		
		this.no = no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.regDate = regDate;
		this.userNo = userNo;
		this.name = name;
		this.rowNo = rowNo;
		this.firstNum = firstNum;
		this.lastNum = lastNum;
	
	}

	public int getFirstNum() {
		return firstNum;
	}

	public void setFirstNum(int firstNum) {
		this.firstNum = firstNum;
	}

	public int getLastNum() {
		return lastNum;
	}

	public void setLastNum(int lastNum) {
		this.lastNum = lastNum;
	}

	

	//메소드 g/s

	public int getRownum() {
		return rowNo;
	}

	public void setRownum(int rowNo) {
		this.rowNo = rowNo;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	//메소드 일반
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", hit=" + hit + ", regDate="
				+ regDate + ", userNo=" + userNo + ", name=" + name + ", rowNo=" + rowNo + ", firstNum=" + firstNum
				+ ", lastNum=" + lastNum +  "]";
	}

	}
