package appModule;

import java.util.List;

import org.json.simple.JSONObject;
import org.junit.BeforeClass;

import frameworkProperties.Config;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.Constants;
import utility.ExcelUtils;

public class RestCommands extends Config{
	public static Response response;
	
	// Config
	@BeforeClass
	// read config file
	public static void start() throws Exception
	{
		// Open Excel File
		ExcelUtils.setExcelFile(Constants.PATH + Constants.FILE, "Plan1");
		Constants.logger.trace("Abrindo arquivo Excel");
	}
	
	
	// Build headers
	public static void BuildHeaders(List<String> HeadersT, List<String> HeadersV, RequestSpecification request)
	{
		for(int i=0; i<=HeadersT.size()-1; i++)
		{
			request.header(HeadersT.get(i), HeadersV.get(i));
		}
	}
	
	// Simple GET method
	public static void GETCommand(String EndPoint, String Path)
	{
		// set endpoint parameter of the Rest request
		RestAssured.baseURI = EndPoint;
		//get endpoint given in the command before and build the request
		RequestSpecification httpRequest = RestAssured.given();
		// Send request and get the response
		response = httpRequest.request(Method.GET, Path);
	}
	
	// Simple POST method
	public static void POSTCommand(String EndPoint, String Path, List<String> HeadersT, List<String> HeadersV, JSONObject RequestBody)
	{
		// specified the endpoint and path
		RestAssured.baseURI = EndPoint;
		RequestSpecification httpRequest = RestAssured.given();
		
		// add headers
		BuildHeaders(HeadersV, HeadersV, httpRequest);
		
		// prepare JSON
		httpRequest.body(RequestBody.toJSONString());
		
		// send post
		response = httpRequest.post(Path);
		Constants.logger.error("Enviando requisição ao endpoint ==> " + EndPoint + Path);
		Constants.logger.error(RequestBody.toJSONString());
	}
	
	// return All Headers
	public static Headers getHeaders()
	{
		return response.getHeaders();
	}
	
	// return Specific Header
	public static String getHeader(String header)
	{
		return response.getHeader(header);
	}
	
	// Return Status Code
	public static Integer getStatusCode()
	{
		return response.getStatusCode();
	}
	
	// return Status message
	public static String getStatusMessage()
	{
		return response.getStatusLine();
	}
	
	// Return response body
	public static String getResponse()
	{
		return response.getBody().asString();
	}
}
