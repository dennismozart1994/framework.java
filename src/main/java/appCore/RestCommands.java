package appCore;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.simple.JSONObject;
import org.junit.Assert;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itextpdf.text.DocumentException;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import properties.Config;
import utility.Constants;
import utility.ExcelUtils;
import utility.Log;
import utility.PDFCreator;
import utility.TestRail;

public class RestCommands extends Config{
	public static Response response;
	public static String SheetName;
	
	// read config file
	public static void start(String SheetName) throws Exception
	{
		// Open Excel File
		ExcelUtils.setExcelFile(Constants.FILE_PATH + Constants.FILE_NAME, SheetName);
		Constants.logger.trace("Abrindo arquivo Excel");
		RestCommands.SheetName = SheetName;
	}
	
/********************************* CONSTRUCTOR COMMANDS *************************************/
	// Build headers
	public static void BuildHeaders(RequestSpecification request) throws Exception
	{
		int End = Constants.REST_START_CONTENT_LINE;
		addStep("Payload");
		String headers = "";
		while(getCellData(End, Constants.HEADER_TC) != "")
		{
			request.header
			(
				getCellData(End,Constants.HEADER_TC), 
				getCellData(End,Constants.HEADER_CC)
			);
			headers = getCellData(End,Constants.HEADER_TC) + ":" + getCellData(End,Constants.HEADER_CC) + "\n";
			End++;
		}
		addJSON(headers);
	}
	
	// build JSON
	@SuppressWarnings("unchecked")
	public static JSONObject BuildJSON() throws Exception
	{
		JSONObject json = new JSONObject();
		int End = Constants.REST_START_CONTENT_LINE;
		while(getCellData(End, Constants.FIELD_TC) != "")
		{
			json.put
			(
				getCellData(End, Constants.FIELD_TC),
				getCellData(End, Constants.FIELD_CC)
			);
			End++;
		}
		return json;
	}
	
	// print preety JSON
	public static String PreetyJSON(String JSON)
	{
		JsonParser parser = new JsonParser();
		JsonObject json = parser.parse(JSON).getAsJsonObject();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String preetyJson = gson.toJson(json);
		
		return preetyJson;
	}
	
/********************************* ACTIONS COMMANDS ***************************************/
	
	/************** GET METHOD ******************/
	public static void GETCommand(String EndPoint, String Path) throws DocumentException
	{
		// set endpoint parameter of the Rest request
		RestAssured.baseURI = EndPoint;
		
		//get endpoint given in the command before and build the request
		RequestSpecification httpRequest = RestAssured.given();
		// evidence endpoint
		addStep("EndPoint:");
		addJSON(EndPoint);
		// Send request and get the response
		response = httpRequest.request(Method.GET, Path);
	}
	
	/************** POST METHOD  ******************/
	public static void POSTCommand(String EndPoint, String Path) throws Exception
	{
		// Specified the ENDPOINT and PATH
		RestAssured.baseURI = EndPoint;
		RequestSpecification httpRequest = RestAssured.given();
		// evidence endpoint
		addStep("EndPoint:");
		addJSON(EndPoint);
		// ADD headers
		BuildHeaders(httpRequest);
		
		// Construct JSON
		JSONObject json = BuildJSON();
		httpRequest.body(PreetyJSON(json.toJSONString()));
		// evidence payload
		addJSON(PreetyJSON(json.toJSONString()));
		// Send POST
		response = httpRequest.post(Path);
		Log("Enviando requisição ao endpoint ==> " + EndPoint + Path);
		Log(PreetyJSON(json.toJSONString()));
		// evidence response
		addStep("Response:");
		addJSON(printAllHeaders(getHeaders()));
		addJSON(PreetyJSON(json.toJSONString()));
	}
/********************************* GET COMMANDS ***************************************/	
	
	/************** GET ALL HEADERS ******************/
	public static Headers getHeaders()
	{
		return response.getHeaders();
	}
	
	// print headers
	public static String printAllHeaders(Headers headers)
	{
		String h = "";
		for(Header header : headers)
		{
			h = header.getName() + ":" + header.getValue() + "\n";
		}
		return h;
	}
	
	/************** GET ONE SPECIFIC HEADER ******************/
	public static String getHeader(String header)
	{
		return response.getHeader(header);
	}
	
	/*********** GET STATUS CODE FROM RESPONSE ***************/
	public static Integer getStatusCode()
	{
		return response.getStatusCode();
	}
	
	/********* GET STATUS MESSAGE FROM RESPONSE **************/
	public static String getStatusMessage()
	{
		return response.getStatusLine();
	}
	
	/************** GET RESPONSE BODY **********************/
	public static String getResponse()
	{
		return response.getBody().asString();
	}
	
	/************** GET SPECIFIC FIELD OF THE BODY ********/
	public static String getJSONfield(String JSONpath)
	{
		JsonPath jsonPathEvaluator = response.jsonPath();
		return jsonPathEvaluator.get(JSONpath);
	}
	
	public static String getXMLfield(String Xpath)
	{
		XmlPath xmlPathEvaluator = response.xmlPath();
		return xmlPathEvaluator.get(Xpath);
	}
	
	/************** PRINT THE ENTIRE RESPONSE **************/
	public static String printResponse()
	{
		return response.print();
	}
	
/********************************* EVIDENCE COMMANDS ***************************************/
	// Create evidence with the default Header
	public static void createEvidence(String TestCaseName, String Objective, String Environment, String ExpectedResult) throws MalformedURLException, DocumentException, IOException
	{
		// Log4j settings
		Log.startTestCase(TestCaseName);
		Log("Creating test evidence...");
		PDFCreator.createPDF(
			readConfig("Project"),
			TestCaseName,
			Objective,
			Environment,
			readConfig("Sprint"),
			Constants.PROJECT_ED, 
			ExpectedResult
		);
	}
	
	public static void ExceptionThrown(String ErrorToLog, Integer ExcelFileLine) throws Exception
	{
		PDFCreator.logFatal(ErrorToLog);
		
		TestRail.AddResult
		(
			Constants.FILE_PATH + Constants.FILE_NAME,
			SheetName,
			ExcelFileLine, 
			Constants.TESTRAIL_FAILED,
			ErrorToLog
		);
		
		throw new Exception(ErrorToLog);
	}
	
	// Add Step into Evidence
	public static void addStep(String Step) throws DocumentException
	{
		PDFCreator.addStep(Step);
	}
	
	// Add JSON data into Evidence
	public static void addJSON(String JSONData) throws DocumentException
	{
		PDFCreator.addJSON(JSONData);
	}
	
	// Take Screenshot
	public static void TakeScreenshot() throws IOException, DocumentException
	{
		BufferedImage scrFile = null;
		try {
			scrFile = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		PDFCreator.addScreenshot(scrFile);	
	}
	
	// Close PDF Evidence
	public static void FinishEvidence(String TestName)
	{
		PDFCreator.quitPDF();
		Log.endTestCase(TestName);
	}
	
/********************************* LOG COMMANDS ***************************************/
	public static void Log(String Message)
	{
		Constants.logger.error(Message);
	}
/********************************* EXCEL COMMANDS  ***************************************/
	public static String getCellData(Integer Line, Integer Column) throws Exception
	{
		return ExcelUtils.getCellData
		(
			Constants.FILE_PATH + Constants.FILE_NAME,
			SheetName,
			Line,
			Column
		);
	}
	
	// Quit Excel File
	public static void quitExcel() throws IOException
	{
		ExcelUtils.closeExcel(Constants.FILE_PATH + Constants.FILE_NAME);
	}
	
/**************************** ASSERTIONS COMMANDS *************************************/
	public static void ValidateString(Integer RowNum, String expected, String current, String comment) throws Exception
	{
		addStep("Validate:");
		String Comment;
		boolean shouldThrown = false;
		
		try 
		{
			Assert.assertEquals(expected, current);
			addJSON("Content " + current + " is equal to the content " + expected + " expected");
			Comment = comment;
			shouldThrown = false;
		}
		catch(AssertionError e)
		{
			Comment = "Executado via automação com erro: \n" + e.toString();
			shouldThrown = true;
		}
		
		// send result to Testrail
		if(shouldThrown)
		{
			ExceptionThrown(Comment, RowNum);
		}
		else
		{
			TestRail.AddResult
			(
				Constants.FILE_PATH + Constants.FILE_NAME,
				SheetName, 
				RowNum, 
				Constants.TESTRAIL_PASSED,
				Comment
			);
		}
	}
/**************************** TESTRAIL COMMANDS *************************************/
	// shouldTest
	public static boolean ShouldTest(String FileName, Integer RowNum) throws Exception
	{
		// get id
		String Testid = ExcelUtils.getCellData(FileName, SheetName, RowNum, Constants.TEST_ID);
		Integer sub = Integer.parseInt(Testid.substring(1));
		
		// get status
		Integer status = TestRail.GetTestResult(sub);
		
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
