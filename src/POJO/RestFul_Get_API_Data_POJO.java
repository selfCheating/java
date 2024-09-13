package POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RestFul_Get_API_Data_POJO {

	@JsonProperty("Color")
	private String colorjson;
	@JsonProperty("capacity")
	private String capacity;
	@JsonProperty("price")
	private double price;
	@JsonProperty("generation")
	private String generation;
	@JsonProperty("year")
	private int year;
	@JsonProperty("CPU model")
	private String CPUmodeljson;
	@JsonProperty("Strap Colour")
	private String StrapColour;
	@JsonProperty("Case Size")
	private String CaseSizejson;
	@JsonProperty("Description")
	private String Description;
	@JsonProperty("Generation")
	private String generationGeneration;
	@JsonProperty("capacity GB")
	private String capacityGB;
	@JsonProperty("Capacity")
	private String capacityCapacity;
	public String getColorjson() {
		return colorjson;
	}
	public String getCapacity() {
		return capacity;
	}
	public double getPrice() {
		return price;
	}
	public String getGeneration() {
		return generation;
	}
	public int getYear() {
		return year;
	}
	public String getCPUmodeljson() {
		return CPUmodeljson;
	}
	public String getStrapColour() {
		return StrapColour;
	}
	public String getCaseSizejson() {
		return CaseSizejson;
	}
	public String getDescription() {
		return Description;
	}
	public String getGenerationGeneration() {
		return generationGeneration;
	}
	public String getCapacityGB() {
		return capacityGB;
	}
	public String getCapacityCapacity() {
		return capacityCapacity;
	}
	

}
