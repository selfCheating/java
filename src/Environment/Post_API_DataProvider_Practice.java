package Environment;

import org.testng.annotations.DataProvider;

public class Post_API_DataProvider_Practice {
	@DataProvider
public Object[][] requestData() {
	return new Object[][] {{"morpheus","leader"},{"Ajit","QA"}};
}
		}
