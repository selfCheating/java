package POJO;

import java.util.List;

public class Get_API_POJO {

	public List<Get_API_DATA_POJO_IN> getData() {
		return data;
	}
	public void setSupport(Get_API_POJO_Support_IN support) {
		this.support = support;
	}
	public int getPage() {
		return page;
	}
	public int getPer_page() {
		return per_page;
	}
	public int getTotal() {
		return total;
	}
	public int getTotal_pages() {
		return total_pages;
	}
	private int page;
	private int per_page;
	private int total;
	private int total_pages;
	
	List<Get_API_DATA_POJO_IN> data;
	private Get_API_POJO_Support_IN support;
}
