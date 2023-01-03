package core;

import constants.Properties;
import libs.java.com.gurock.testrail.APIClient;
import libs.java.com.gurock.testrail.APIException;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestRailHelper {

    private static final String STATUS_ID_PASSED = "1";
    private static final String STATUS_ID_FAILED = "5";

    private final APIClient apiClient;

    public TestRailHelper() {
        apiClient = new APIClient(Properties.TESTRAIL_URL);
        apiClient.setUser(Properties.TESTRAIL_EMAIL);
        apiClient.setPassword(Properties.TESTRAIL_PASSWORD);
    }

    public void addResultToTestRun(String testRunId, String testCaseId, int testRailStatusId) throws APIException, IOException {
        Map data = new HashMap();
        data.put("status_id", new Integer(testRailStatusId));
//        data.put("comment", "This test worked fine!");
        JSONObject r = (JSONObject) apiClient
                .sendPost(String.format("add_result_for_case/%s/%s", testRunId, testCaseId),
                data);

        System.out.printf("fgfh", "sss");
    }

}
