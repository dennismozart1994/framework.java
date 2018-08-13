package utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {
	/**************************** PROJECT INFO *************************************/
	public static final String PROJECT_ED = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	
	/**************************** TESTRAIL INFO ***********************************/
	public static final String TESTRAIL_URL = "https://dennismozart.testrail.io";
	public static final String TESTRAIL_USER = "dennis.mozart@sempreit.com.br";
	public static final String TESTRAIL_PASS = "M0z@rt290794";
	
	/**************************** TESTRAIL RESULTS ***********************************/
	public static final Integer TESTRAIL_PASSED = 1;
	public static final Integer TESTRAIL_BLOCKED = 2;
	public static final Integer TESTRAIL_UNTESTED = 3;
	public static final Integer TESTRAIL_RETEST = 4;
	public static final Integer TESTRAIL_FAILED = 5;
	
	/**************** System URL or EndPoint and Path parameters *******************/
	public static final String URL = "http://localhost/ManageTeamSystem/";
	public static final String API_URL = "http://restapi.demoqa.com/customer/";
	public static final String API_PATH = "register";
	
	/*************************** TEST DATA ****************************************/
	/*************************** WEB FILE INFO ***********************************/
	public static final String FILE_PATH = System.getProperty("user.dir") + "\\data\\";
	public static final String FILE_NAME = "massa.xlsx";
	
	// column(_C) and row(_C)
	// User and password
	public static final Integer USER_C = 3;
	public static final Integer PASS_C = 4;
	public static final Integer WEB_START_CONTENT_LINE = 1;
	
	/*************************** REST FILE INFO **********************************/
	
	// Title Column(_TC) and Content Column(_CC)
	// Header and Fields
	public static final Integer HEADER_TC = 3;
	public static final Integer HEADER_CC = 4;
	public static final Integer FIELD_TC = 5;
	public static final Integer FIELD_CC = 6;
	public static final Integer REST_START_CONTENT_LINE = 2;
	
	// TestRail ID's
	public static final Integer RUN_ID = 0;
	public static final Integer TEST_ID = 1;
	public static final Integer CASE_ID = 2;
	
	
	/***************************** LOGGER **************************************/
	public static final Logger logger = LogManager.getLogger(Logger.class);
}
