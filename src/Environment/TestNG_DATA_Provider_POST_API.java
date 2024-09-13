package Environment;

import org.testng.annotations.DataProvider;

public class TestNG_DATA_Provider_POST_API {

	@DataProvider
	public Object[][] requestData() {
		return new Object[][] {{"morpheus","leader"},{"jim","qa"}, {"andy","autoqa"}};
	}
}
