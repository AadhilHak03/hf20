package com.qa.testrailmanager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

public class TestRailManager {
	public static String  TEST_RUN_ID = "1";
	public static String  TEST_RAIL_USERNAME = "aadhilhakeem03@gmail.com"
;
	public static String TEST_RAIL_PASSWORD ="Meliodas99@!";
	public static String TEST_RAIL_ENGINE_URL = "https://aadhilrail1.testrail.io/"
;
	public static int TEST_CASE_PASS_STATUS = 1;
	public static int TEST_CASE_FAIL_STATUS = 5;
	
	public static void addResultsForTestcase(String testCaseID, int status, String error)
	{
		String testRunID = TEST_RUN_ID;
		APIClient c = new APIClient(TEST_RAIL_ENGINE_URL);
		c.setUser(TEST_RAIL_USERNAME);
		c.setPassword(TEST_RAIL_PASSWORD);
		
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("status_id", status);
		data.put("comment", "this test is executed" + error);
		
		try {
			c.sendPost("add_result_for_case/"+testRunID+"/"+testCaseID, data);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
		
		
	}
	

}
