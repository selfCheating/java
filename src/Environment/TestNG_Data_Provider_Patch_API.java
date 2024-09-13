package Environment;

import org.testng.annotations.DataProvider;

public class TestNG_Data_Provider_Patch_API {

	@DataProvider
	public Object[][] requestData() {
		return new Object[][] {{"pranjal","QA"}, {"Ajit","Leader"},{"Tim","QA"}};
	}
}
