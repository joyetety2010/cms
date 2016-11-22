package com.franchi.module.pojo.page;

import java.util.ArrayList;
import java.util.List;

public class Pages<T> {

	private long total;
	
	private int offset;
	
	private int currPage;
	
	private List<T> data = new ArrayList<T>();
	
	
	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	
}
