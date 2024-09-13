package POJO;

import java.util.List;

public class Get_API_Parent_POJO_Practice {

	public List<Get_API_Data_Pojo_Practice> getData() {
		return data;
	}
	public Get_API_Support_PoJo_Practice getSupport() {
		return support;
	}
	public int getPages() {
		return pages;
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
	
	private int pages;
	private int per_page;
	private int total;
	private int total_pages;
	List<Get_API_Data_Pojo_Practice> data;
	private Get_API_Support_PoJo_Practice support;
}
