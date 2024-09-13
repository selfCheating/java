package POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Restful_Post_API_DATA_POJO {
	
	 @JsonProperty("year")
	private int year;
	 @JsonProperty("price")
    private double price;
   @JsonProperty("CPU model")
    private String cpuModel;
    @JsonProperty("Hard disk size")
    private String hardDiskSize;
    
	public int getYear() {
		return year;
	}
	public double getPrice() {
		return price;
	}
	public String getCpuModel() {
		return cpuModel;
	}
	public String getHardDiskSize() {
		return hardDiskSize;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setCpuModel(String cpuModel) {
		this.cpuModel = cpuModel;
	}
	public void setHardDiskSize(String hardDiskSize) {
		this.hardDiskSize = hardDiskSize;
	}
	
}
