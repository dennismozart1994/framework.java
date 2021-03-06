package utility;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import utility.APIClient;

public class TestRail {
	
	public static APIClient SetupConnection()
	{
		APIClient client = new APIClient(Constants.TESTRAIL_URL);
		client.setUser(Constants.TESTRAIL_USER);
		client.setPassword(Constants.TESTRAIL_PASS);
		return client;
	}
	
	// Return testResult
	public static Integer GetTestResult(Integer TestID) throws MalformedURLException, IOException, APIException
	{
		APIClient client = SetupConnection();
		JSONObject c = (JSONObject) client.sendGet("/get_test/" + TestID);
		return Integer.parseInt(c.get("status_id").toString());
	}
	
	// Add Result to a test
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public static void AddResult(Integer CaseID, Integer RunID, Integer FinalResult, String Comment) throws Exception
	{	
		// add result to testrail
		APIClient client = SetupConnection();
		Map body = new HashMap();
		body.put("status_id", new Integer(FinalResult));
		body.put("comment", Comment);
		JSONObject c = 
			(JSONObject) client.sendPost
			(
				"/add_result_for_case/" + RunID + "/" + CaseID,
				body
			);
	}
	
	// Verify if a test is already passed into TestRail
	public static boolean ShouldTest(Integer TestID) throws Exception
	{
		// get status
		Integer status = TestRail.GetTestResult(TestID);
		
		if(status == Constants.TESTRAIL_PASSED)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
