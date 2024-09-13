package POJO;

public class Restful_PostAPI_POJO {

	private String id;
	private String name;
	private Restful_Post_API_DATA_POJO data;
	private String createdAt;
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
		public void setName(String name) {
			this.name = name;
		}
	
	public Restful_Post_API_DATA_POJO getData() {
		return data;
	}
	public void setData(Restful_Post_API_DATA_POJO data) {
		this.data = data;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	
	
}
