package POJO;

import java.util.List;

public class Get_POJO {
private int page;
private int per_page;
private int total;
private int total_pages;

List<Get_Data_POJO> data;
private Get_Support_POJO support;
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
public List<Get_Data_POJO> getData() {
	return data;
}
public Get_Support_POJO getSupport() {
	return support;
}

}
