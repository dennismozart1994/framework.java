package appModule;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.itextpdf.text.DocumentException;

import frameworkProperties.Config;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utility.Constants;
import utility.ExcelUtils;
import utility.Log;
import utility.PDFCreator;

public class RestCommands extends Config{
	public static Response response;
	
	// Config
	@BeforeClass
	// read config file
	public static void start() throws Exception
	{
		// Open Excel File
		ExcelUtils.setExcelFile(Constants.REST_FILE_PATH + Constants.REST_FILE_NAME, "JSON");
		Constants.logger.trace("Abrindo arquivo Excel");
	}
	
/********************************* CONSTRUCTOR COMMANDS 
 * @throws Exception *************************************/
	// Build headers
	public static void BuildHeaders(RequestSpecification request) throws Exception
	{
		int End = Constants.START_CONTENT_LINE;
		while(getCellData(End, Constants.HEADER_TC) != "")
		{
			request.header
			(
				getCellData(End,Constants.HEADER_TC), 
				getCellData(End,Constants.HEADER_CC)
			);
			End++;
		}
	}
	
	// build JSON
	@SuppressWarnings("unchecked")
	public static JSONObject BuildJSON() throws Exception
	{
		JSONObject json = new JSONObject();
		int End = Constants.START_CONTENT_LINE;
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
	
/********************************* ACTIONS COMMANDS ***************************************/
	
	/************** GET METHOD ******************/
	public static void GETCommand(String EndPoint, String Path)
	{
		// set endpoint parameter of the Rest request
		RestAssured.baseURI = EndPoint;
		//get endpoint given in the command before and build the request
		RequestSpecification httpRequest = RestAssured.given();
		// Send request and get the response
		response = httpRequest.request(Method.GET, Path);
	}
	
	/************** POST METHOD  ******************/
	public static void POSTCommand(String EndPoint, String Path, boolean EvidencePayload) throws Exception
	{
		// Specified the ENDPOINT and PATH
		RestAssured.baseURI = EndPoint;
		RequestSpecification httpRequest = RestAssured.given();
		
		// ADD headers
		BuildHeaders(httpRequest);
		
		// Construct JSON
		JSONObject json = BuildJSON();
		httpRequest.body(json.toJSONString());
		if(EvidencePayload)
		{
			addStep("Pré-Requisito: Create Payload");
			addJSON(json.toJSONString());
		}
		// Send POST
		response = httpRequest.post(Path);
		Log("Enviando requisição ao endpoint ==> " + EndPoint + Path);
		Log(json.toJSONString());
	}
/********************************* GET COMMANDS ***************************************/	
	
	/************** GET ALL HEADERS ******************/
	public static Headers getHeaders()
	{
		return response.getHeaders();
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
	
/********************************* EVIDENCE COMMANDS ***************************************/
	// Create evidence with the default Header
	public static void createEvidence(String TestCaseName, String Objective, String ExpectedResult) throws MalformedURLException, DocumentException, IOException
	{
		// Log4j settings
		Log.startTestCase(TestCaseName);
		Log("Creating test evidence...");
		PDFCreator.createPDF(
			readConfig("Project"),
			TestCaseName,
			Objective,
			readConfig("Environment"),
			readConfig("Sprint"),
			Constants.PROJECT_ED, 
			ExpectedResult
		);
	}
	
	public static void ExceptionThrown(String ErrorToLog) throws DocumentException
	{
		PDFCreator.logFatal(ErrorToLog);
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
	
	@AfterClass
	public static void quit() throws IOException
	{
		ExcelUtils.closeExcel(Constants.REST_FILE_PATH + Constants.REST_FILE_NAME);
	}
/********************************* EXCEL COMMANDS  ***************************************/
	public static String getCellData(Integer Line, Integer Column) throws Exception
	{
		return ExcelUtils.getCellData
		(
			Constants.REST_FILE_PATH + Constants.REST_FILE_NAME,
			"JSON",
			Line,
			Column
		);
	}
}
